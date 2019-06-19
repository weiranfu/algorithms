public class DogArray {
    public static void main(String[] args) {
        Dog[] dogs = new Dog[3];
        dogs[0] = new Dog(15);
        dogs[1] = new Dog(35);
        dogs[2] = new Dog(5);

        // All dogs make a noise.
        for (Dog d: dogs) {
            d.makeNoise();
        }
    }
}
