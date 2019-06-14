public class Dog {
    public int weightInPounds;

    // Dog Constructor.
    public Dog(int w) {
        weightInPounds = w;
    }
    
    public void makeNoise() {
        if (weightInPounds < 10) {
            System.out.println("Yel!");
        } else if (weightInPounds < 30) {
            System.out.println("Bark!");
        } else {
            System.out.println("Woooooof!");
        }
    }
}
