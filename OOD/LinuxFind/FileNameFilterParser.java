package OOD.LinuxFind;

import java.nio.file.FileSystems;
import java.util.Stack;

class FileNameFilterParser extends Parser {
    @Override
    public String getName() {
        return "name";
    }
    @Override
    public PlanNode parse(Stack<String> args) {
        return new FileNameFilter(FileSystems.getDefault().getPathMatcher("glob:" + args.pop()));
    }
}
