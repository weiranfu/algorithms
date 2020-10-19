package OOD.LinuxFind;

import java.util.Stack;

// Option/Filter/Action的解析器基类
abstract class Parser {
    // 该解析器所处理的参数名，例如"maxdepth"，"type"，"size"
    public abstract String getName();
    // 解析逻辑的实现
    public abstract PlanNode parse(Stack<String> args);
}
