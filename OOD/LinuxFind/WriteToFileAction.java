package OOD.LinuxFind;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// 将结果写到某个文件中，例如：
// -writetofile output.txt
class WriteToFileAction extends Action {
    private String fileName;
    private PrintWriter pw;
    public WriteToFileAction(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public void initialize() {
        try {
            pw = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void finalize() {
        pw.close();
    }
    @Override
    public void invoke(ExecutionContext context) {
        pw.println(context.getFilePath());
    }
}
