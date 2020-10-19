package OOD.LinuxFind;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;

class Executor {
    private Path startPath;
    private ArrayList<Option> options;
    private Predicate filterPredicate;
    private ArrayList<Action> actions;
    private ExecutionContext context;

    // 这里利用了Java本身提供的Files.walkFileTree()方法以及FileVisitor回调接口
    // 参考这里的介绍：[url]https://docs.oracle.com/javase/tutorial/essential/io/walk.html[/url]
    private class NodeVistor implements FileVisitor<Path> {
        private FileVisitResult visitFileOrDirectory(Path fileOrDir, BasicFileAttributes attr) {
            // 检查是不是Symbolic link
            if (attr.isSymbolicLink() && !context.shouldFollowSymbolicLink()) {
                return FileVisitResult.SKIP_SUBTREE;
            }
            context.setFilePath(fileOrDir);
            context.setBasicFileAttributes(attr);
            // 谓词（predicate，类似SQL中where语句）求值
            // 如果值为false的话表示当前文件不满足用户设定的过滤条件，那么跳过当前文件
            if (filterPredicate != null && !filterPredicate.evaluate(context)) {
                return FileVisitResult.CONTINUE;
            }
            // 满足过滤条件，首先打印当前路径
            System.out.println(fileOrDir.toString());
            // 然后执行所有actions
            for (Action action : actions) {
                action.invoke(context);
            }
            return FileVisitResult.CONTINUE;
        }

        // 访问每一个文件时的回调
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            return visitFileOrDirectory(file, attrs);
        }
        // 访问每一个目录之前的回调
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            return visitFileOrDirectory(dir, attrs);
        }
        // 访问每一个目录之后的回调
        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
            return FileVisitResult.CONTINUE;
        }
        // 遇到错误时的回调
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }
    }

    // 构造函数
    public Executor(Path startPath, ArrayList<Option> options,
                    Predicate filterPredicate, ArrayList<Action> actions) {
        this.startPath = startPath;
        this.options = options;
        this.filterPredicate = filterPredicate;
        this.actions = actions;
    }

    public void Execute() throws IOException {
        context = new ExecutionContext();
        // 首先用options初始化context（例如maxdepth参数）
        for (Option option : options) {
            option.setup(context);
        }
        // 然后初始化actions（例如打开输出文件）
        for (Action action : actions) {
            action.initialize();
        }
        // Walk file tree，利用NodeVistor处理回调
        Files.walkFileTree(startPath, EnumSet.of(FileVisitOption.FOLLOW_LINKS),
                context.getMaxDepth(), new NodeVistor());
        // actions的完结处理（例如flush并关闭输出文件）
        for (Action action : actions) {
            action.finalize();
        }
    }
}
