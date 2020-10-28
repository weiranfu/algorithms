package OOD.LinuxFindBasic;

class MinsizeFilter extends Filter {

    int minSize;

    public MinsizeFilter(int minSize) {
        this.minSize = minSize;
    }

    @Override
    boolean evaluate(File file) {
        return file.getSize() > minSize;
    }
}
