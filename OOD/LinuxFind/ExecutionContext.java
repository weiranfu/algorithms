package OOD.LinuxFind;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

class ExecutionContext {
    // Options
    private int maxDepth = Integer.MAX_VALUE;
    private boolean followSymbolicLink = false;

    // Runtime attributes
    private Path filePath;
    private BasicFileAttributes fileAttr;

    // Getters and setters
    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }
    public int getMaxDepth() {
        return maxDepth;
    }
    public void setFollowSymbolicLink() {
        followSymbolicLink = true;
    }
    public boolean shouldFollowSymbolicLink() {
        return followSymbolicLink;
    }
    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }
    public Path getFilePath() {
        return filePath;
    }
    public void setBasicFileAttributes(BasicFileAttributes fileAttr) {
        this.fileAttr = fileAttr;
    }
    public BasicFileAttributes getBasicFileAttributes() {
        return fileAttr;
    }
}
