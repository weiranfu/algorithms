package OOD.LinuxFind;

import java.util.Stack;

class FileSizeFilterParser extends Parser {
    @Override
    public String getName() {
        return "size";
    }
    @Override
    public PlanNode parse(Stack<String> args) {
        String param = args.pop();
        FileSizeFilter.OpType op = FileSizeFilter.OpType.EQUAL;
        int digitStart = 0;
        if (param.startsWith("+")) {
            op = FileSizeFilter.OpType.GREATER_EQUAL;
            digitStart = 1;
        } else if (param.startsWith("-")) {
            op = FileSizeFilter.OpType.LESS_EQUAL;
            digitStart = 1;
        }
        int digitEnd = digitStart;
        while (digitEnd < param.length() && Character.isDigit(param.charAt(digitEnd))) {
            ++digitEnd;
        }
        if (digitEnd == digitStart) {
            throw new RuntimeException("Invalid file size specification: " + param);
        }
        long targetFileSize = Long.parseLong(param.substring(digitStart, digitEnd));
        if (digitEnd != param.length()) {
            final String unit = param.substring(digitEnd);
            switch (unit.toLowerCase()) {
                case "k":
                case "kb":
                    targetFileSize *= 1024;
                    break;
                case "m":
                case "mb":
                    targetFileSize *= 1024 * 1024;
                    break;
                case "g":
                case "gb":
                    targetFileSize *= 1024 * 1024 * 1024;
                    break;
                default:
                    throw new RuntimeException("Invalid file size unit: " + unit);
            }
        }
        return new FileSizeFilter(op, targetFileSize);
    }
}
