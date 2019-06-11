public class Test {
    public static void main(String[] args) {
        String h = 5 + "horse";
        System.out.println(h);           // prints out 5horse
        System.out.println(5 + "10");    // prints out 510 
        System.out.println(5 + 10);      // prints out 15
    }
}

/*
Java is strongly typed, if you tell it h is a string,
it can concatenate the elements and give you a string.
But if h is an int, it can't concatenate a number and a string
and give you a number.
*/
