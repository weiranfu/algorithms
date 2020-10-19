package OOD.LinuxFind;

// Filter也就是GNU find官方文档上所说的"tests"
// 例如：
//   -name a.txt
//   -size +1MB
//   -type f
// 这些都是Filter
// 继承这个类以添加一个新的Filter
abstract class Filter extends Predicate {}