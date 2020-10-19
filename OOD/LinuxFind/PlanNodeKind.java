package OOD.LinuxFind;

public enum PlanNodeKind {
    OPTION,      // 全局的configuration
    PREDICATE,   // 各种 filter 和 operations（“与或非”逻辑联结词）可以合称为谓词 predicate，类似于SQL中的where语句
    ACTION       // 一些side effect操作
}
