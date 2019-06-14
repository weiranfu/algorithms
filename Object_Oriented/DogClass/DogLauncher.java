public class DogLauncher {
    public static void main(String[] args) {
        Dog d = new Dog(15);
        Dog d2 = new Dog(35);
        // Dog bigger = d.maxDog(d2);
        Dog bigger = Dog.maxDog(d, d2);
        /* Bigger one will bark! */
        bigger.makeNoise();
    }
}
