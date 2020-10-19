package OOD.LinuxFind;

// 设定查找的是文件还是目录，例如：
// -type f
// -type d
class FileTypeFilter extends Filter {
    enum FileType {
        DIRECTORY,
        FILE
    }
    private FileType targetFileType;
    public FileTypeFilter(FileType targetFileType) {
        this.targetFileType = targetFileType;
    }
    @Override
    public boolean evaluate(ExecutionContext context) {
        switch (targetFileType) {
            case FILE:
                return context.getBasicFileAttributes().isRegularFile();
            case DIRECTORY:
                return context.getBasicFileAttributes().isDirectory();
        }
        throw new RuntimeException("Unsupported enum value: " + targetFileType.name());
    }
}
