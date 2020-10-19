package OOD.LinuxFind;

import java.util.Stack;

class MaxDepthOptionParser extends Parser {
    @Override
    public String getName() {
        return "maxdepth";
    }

    @Override
    public PlanNode parse(Stack<String> args) {
        return new MaxDepthOption(Integer.parseInt(args.pop()));
    }
}
