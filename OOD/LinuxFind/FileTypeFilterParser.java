package OOD.LinuxFind;

import java.util.Stack;

class FileTypeFilterParser extends Parser {
    @Override
    public String getName() {
        return "type";
    }
    @Override
    public PlanNode parse(Stack<String> args) {
        final String param = args.pop();
        switch (param) {
            case "f":
                return new FileTypeFilter(FileTypeFilter.FileType.FILE);
            case "d":
                return new FileTypeFilter(FileTypeFilter.FileType.DIRECTORY);
        }
        throw new RuntimeException("Unsupport file type: " + param);
    }
}
