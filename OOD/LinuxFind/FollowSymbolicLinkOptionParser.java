package OOD.LinuxFind;

import java.util.Stack;

class FollowSymbolicLinkOptionParser extends Parser {
    @Override
    public String getName() {
        return "L";
    }
    @Override
    public PlanNode parse(Stack<String> args) {
        return new FollowSymbolicLinkOption();
    }
}
