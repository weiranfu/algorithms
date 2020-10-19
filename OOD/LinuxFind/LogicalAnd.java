package OOD.LinuxFind;

import java.util.ArrayList;

// 逻辑“与”
class LogicalAnd extends Predicate {
    private ArrayList<Predicate> operands;
    public LogicalAnd(ArrayList<Predicate> operands) {
        this.operands = operands;
    }
    // “与”操作返回true当且仅当所有子谓词都返回true
    @Override
    public boolean evaluate(ExecutionContext context) {
        for (final Predicate operand : operands) {
            if (!operand.evaluate(context)) {
                return false;
            }
        }
        return true;
    }
}