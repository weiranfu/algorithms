package OOD.LinuxFind;

import java.io.IOException;
import java.util.Stack;

/*
    Linux的find命令其实还是相当复杂的一个命令，参数种类很多
    options：affect overall operation rather than the processing of a specific file;
    tests：return a true or false value, depending on the file's attributes;
    actions：have side effects and return a true or false value; and
    operators：connect the other arguments and affect when and whether they are evaluated.

    总体设计上：
(1) 首先要设计对所存储数据的access path，也就你怎么访问/遍历这些数据 —— 对应文件系统就是简单粗暴的walk file tree
(2) 然后需要将各种参数转化为一定的中间表示（IR，intermediate representation），比如说上面提到的option，predicate和action都可以是一个抽象类，然后针对每个参数继承其中一个并实现相应功能
(3) 得到了中间表示，还可以设计优化器对其进行优化。比如说Linux find是可以启动优化功能的（-O1或者-O2或者-O3），会reorder过滤条件的执行顺序。举个简单例子，考虑过滤条件“找到文本中包含Hello world并且后缀为.cpp的文件”，那么我们肯定希望先执行后缀名.cpp的这个过滤，然后在满足后缀的文件中再查找Hello wolrd关键词
(4) 最后需要生成一个执行器，根据优化后的中间表示（一般称为执行计划Execution Plan），walk file tree并且执行相应的filter和action。
 */



// 需要通过命令行提供参数，可以先打包成jar文件然后通过命令行执行
// 例如：
// java -jar find.jar . -name data.txt
// java -jar find.jar . -name \*.txt
// java -jar find.jar /Some/Path -maxdepth 3 -name \*.h -or -name \*.cpp
// java -jar find.jar /Some/Path \( -name \*.h -or -name \*.cpp \) -and -size +100K
public class Question {
    public static void main(String[] args) throws IOException {
        Executor exec = new ExecutionGenerator().generateExecutor(args);
        exec.Execute();
    }
}














