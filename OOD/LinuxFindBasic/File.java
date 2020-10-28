package OOD.LinuxFindBasic;

import java.util.HashMap;
import java.util.Map;

public class File {
    private String name;
    private int size;
    private FileType type;
    private boolean isDirectory;
    private Map<String, File> children;

    public File(String name, int size, FileType type, boolean isDirectory) {
        this.name = name;
        this.size = size;
        this.type = type;
        this.isDirectory = isDirectory;
        children = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public FileType getType() {
        return type;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public Map<String, File> getChildren() {
        return children;
    }
}
