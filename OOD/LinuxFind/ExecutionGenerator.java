package OOD.LinuxFind;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
or/and/not的优先级（结合性）是依次升高的，就和加减乘除一样，一个表达式不一定是从左往右计算。比如说 A -or B -and C 的运算顺序是 A -or ( B -and C )，而不是 (A -or B) -and C

现在我们要写一个parser来正确地解析A -or B -and C这样的表达式，那么这个parser其实有若干种写法：
--
(1) 可以用解LeetCode上计算器题目的那种stack的方法，其本质上是一个下推自动机（但是扩展性不好不适合处理复杂情况）
(2) 可以写一个LR parser，主要是构造并使用一个shift-reduce跳转表
(3) 可以写一个LL parser，也叫“递归下降解析器”，主要是写一堆递归方法层层调用..
--

然后上面代码用的方法是(3)。至于为什么是parseOr()里面调用parseAnd()，然后parseAnd()里面调用parseNot().. 这和LL文法的结构有关，可以参考一下这篇知乎文章：
https://zhuanlan.zhihu.com/p/31271879

里面提到了一个算数表达式的例子（也就是计算器了）可以仔细看一下，+/* 本质上和 or/and 是一样的处理
 */

class ExecutionGenerator {
    private static Map<String, Parser> optionParserRegistry = new HashMap<>();

    private static void Register(Parser parser) {
        optionParserRegistry.put(parser.getName(), parser);
    }

    // 在这里注册所有的OptionParser子类
    static {
        Register(new MaxDepthOptionParser());
        Register(new FollowSymbolicLinkOptionParser());
        Register(new FileTypeFilterParser());
        Register(new FileNameFilterParser());
        Register(new FileSizeFilterParser());
        Register(new WriteToFileActionParser());
    }

    private Stack<String> tokens;

    // 给定输入参数，生成执行器
    // 这里包含了一个简易的LL(1) recursive descent parser
    public Executor generateExecutor(String[] args) {
        tokens = new Stack<>();
        for (int i = args.length - 1; i >= 0; --i) {
            tokens.push(args[i]);
        }

        if (tokens.empty()) {
            throw new RuntimeException("Requires at least one path argument");
        }
        final Path filePath = Paths.get(tokens.pop());

        ArrayList<Option> options = new ArrayList<>();
        ArrayList<Predicate> predicates = new ArrayList<>();
        ArrayList<Action> actions = new ArrayList<>();

        while (!tokens.empty()) {
            PlanNode node = parseOr();
            switch (node.getKind()) {
                case OPTION: options.add((Option)node); break;
                case PREDICATE: predicates.add((Predicate)node); break;
                case ACTION: actions.add((Action)node); break;
                default:
                    throw new RuntimeException("Unsupported enum value " + node.getKind().name());
            }
        }

        Predicate filterPredicate = null;
        if (predicates.size() == 1) {
            filterPredicate = predicates.get(0);
        } else if (predicates.size() > 1) {
            filterPredicate = new LogicalAnd(predicates);
        }

        tokens = null;
        return new Executor(filePath, options, filterPredicate, actions);
    }

    // 解析 "... -or ..." 这样的输入
    private PlanNode parseOr() {
        ArrayList<PlanNode> operands = new ArrayList<>();
        operands.add(parseAnd());
        while (nextTokenIs("-or") || nextTokenIs("-o")) {
            tokens.pop();
            operands.add(parseAnd());
        }
        if (operands.size() == 1) {
            return operands.get(0);
        }
        ArrayList<Predicate> predicates = new ArrayList<>();
        for (PlanNode node : operands) {
            if (node.getKind() != PlanNodeKind.PREDICATE) {
                throw new RuntimeException("Logical OR can only be applied to predicates");
            }
            predicates.add((Predicate)node);
        }
        return new LogicalOr(predicates);
    }

    // 解析 "... -and ..." 这样的输入
    private PlanNode parseAnd() {
        ArrayList<PlanNode> operands = new ArrayList<>();
        operands.add(parseNot());
        while (nextTokenIs("-and") || nextTokenIs("-a")) {
            tokens.pop();
            operands.add(parseNot());
        }
        if (operands.size() == 1) {
            return operands.get(0);
        }
        ArrayList<Predicate> predicates = new ArrayList<>();
        for (PlanNode node : operands) {
            if (node.getKind() != PlanNodeKind.PREDICATE) {
                throw new RuntimeException("Logical AND can only be applied to predicates");
            }
            predicates.add((Predicate)node);
        }
        return new LogicalAnd(predicates);
    }

    // 解析 "-not ..." 这样的输入
    private PlanNode parseNot() {
        boolean negate = false;
        if (nextTokenIs("-not") || nextTokenIs("-n")) {
            tokens.pop();
            negate = true;
        }
        PlanNode operand = parseAtom();
        if (!negate) {
            return operand;
        }
        if (operand.getKind() != PlanNodeKind.PREDICATE) {
            throw new RuntimeException("Logical NOT can only be applied to a predicate");
        }
        return new LogicalNot((Predicate)operand);
    }

    // 解析括号表达式 "( ... )" 或者是基础的 Option / Filter / Action
    private PlanNode parseAtom() {
        if (nextTokenIs("(")) {
            tokens.pop();
            PlanNode node = parseOr();
            if (!nextTokenIs(")")) {
                throw new RuntimeException("Unmatched parenthesis");
            }
            tokens.pop();
            return node;
        }
        if (tokens.isEmpty()) {
            throw new RuntimeException("Unexpected end of input stream");
        }
        if (!tokens.peek().startsWith("-")) {
            throw new RuntimeException("Unexpected token " + tokens.peek());
        }
        // 这个name就是参数名，例如-type的"type"，-size的"size"
        final String name = tokens.pop().substring(1);
        // 在registry中找到与参数名对应的parser
        final Parser parser = optionParserRegistry.get(name);
        if (parser == null) {
            throw new RuntimeException("Unrecognized option " + name);
        }
        // Parser各自的parse()方法用于解析参数的arguments
        // 例如 -size +1M，那么"size"所对应的parser应当知道如何解析"+1M"
        return parser.parse(tokens);
    }

    private boolean nextTokenIs(String value) {
        return !tokens.isEmpty() && value.equals(tokens.peek());
    }
}