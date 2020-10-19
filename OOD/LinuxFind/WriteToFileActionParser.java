package OOD.LinuxFind;

import java.util.Stack;

class WriteToFileActionParser extends Parser {
    @Override
    public String getName() {
        return "writetofile";
    }
    @Override
    public PlanNode parse(Stack<String> args) {
        return new WriteToFileAction(args.pop());
    }
}
