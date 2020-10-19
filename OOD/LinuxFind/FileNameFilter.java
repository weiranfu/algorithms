package OOD.LinuxFind;

import java.nio.file.Path;
import java.nio.file.PathMatcher;

// 添加一个新的Filter / Action大部分情况下只需要继承并实现相应的基类以及 Parser基类
// 添加一个新的Option可能还需要修改ExecutionContext并在Executor中做对应修改

// 设定文件名glob pattern，例如：
// -name a.txt
// -name \*.txt
// -name \*data\*.txt
// 注意命令行下不能直接写-name *.txt，否则glob会把*.txt展开为当前目录下的所有match的文件
class FileNameFilter extends Filter {
    private PathMatcher pathMatcher;
    public FileNameFilter(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }
    @Override
    public boolean evaluate(ExecutionContext context) {
        final Path filePath = context.getFilePath();
        return pathMatcher.matches(filePath) ||
                pathMatcher.matches(filePath.getFileName());
    }
}
