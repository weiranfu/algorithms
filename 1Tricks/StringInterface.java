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
