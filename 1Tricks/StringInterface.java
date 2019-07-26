String a, b;

// Get the length of a string.
length = a.length();

// Compare two strings.
a.compareTo(b) < 0;   // If a < b:
a.compareTo(b) == 0;  // If a == b:
a.compareTo(b) > 0;   // if a > b:

// Get the ith character in a string.
char c = a.charAt(index);

// Concatenates the string str at the end of the current string.
String s = "book";  String str = "is";
s = s.concat(str).concat("good");
// now s is "bookisgood".


// Returns the index within this string of the first occurrence of the specified substring.
s.indexOf(String substr);
if substr does not exist in s, return -1;
if substr.length() > s.length(), return -1.
if substr = "", returns 0.


// Returns true if string contains another string.
s.contains(String s);

// Converts this string to a new character array.
char[] a = s.toCharArray();

// Returns a string that is a substring of this string.
String ss = s.substring(int beginIndex, int endIndex);
the substring is [begin, end), length = end - begin

Public String [ ] split ( String regex, int limit )
/**
arameters:
regex - a delimiting regular expression
Limit - the result threshold

Returns:
An array of strings computed by splitting the given string.

limit > 0 : If this is the case then the pattern will be
            applied at most limit-1 times, the resulting 
            array’s length will not be more than n, and 
            the resulting array’s last entry will contain
            all input beyond the last matched pattern.
limit < 0 : In this case, the pattern will be applied as
            many times as possible, and the resulting 
            array can be of any size.
limit = 0 : In this case, the pattern will be applied as 
            many times as possible, the resulting array can 
            be of any size, and trailing empty strings will
            be discarded.
*/
String s = "Geekss for geeks";
> String[] arrOfstr = s.split(" ", 2);  
["Geekss", "for geeks"]
> String[] arrOfstr = s.split(" ");
["Geekss", "for", "geeks"]
