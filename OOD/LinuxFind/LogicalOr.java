package OOD.LinuxFind;

import java.util.ArrayList;

// 逻辑“或”
class LogicalOr extends Predicate {
    private ArrayList<Predicate> operands;
    public LogicalOr(ArrayList<Predicate> operands) {
        this.operands = operands;
    }
    // “或”操作返回true当且仅当任意一个子谓词返回true
    @Override
    public boolean evaluate(ExecutionContext context) {
        for (final Predicate operand : operands) {
            if (operand.evaluate(context)) {
                return true;
            }
        }
        return false;
    }
}
