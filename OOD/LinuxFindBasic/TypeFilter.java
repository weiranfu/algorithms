package OOD.LinuxFindBasic;

class TypeFilter extends Filter {

    FileType type;

    public TypeFilter(FileType type) {
        this.type = type;
    }

    @Override
    boolean evaluate(File file) {
        return file.getType() == type;
    }
}
