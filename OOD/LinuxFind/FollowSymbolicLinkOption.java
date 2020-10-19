package OOD.LinuxFind;

// 是否处理symbolic link：
// -L
class FollowSymbolicLinkOption extends Option {
    @Override
    public void setup(ExecutionContext context) {
        context.setFollowSymbolicLink();
    }
}