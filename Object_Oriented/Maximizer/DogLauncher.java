public class DogLauncher {
    public static void main(String[] args) {
        Dog d1 = new Dog("Elyse", 3);
        Dog d2 = new Dog("Sture", 9);
        Dog d3 = new Dog("Benjamin", 15);
        Dog[] dogs = new Dog[]{d1, d2, d3};
        System.out.println(Maximizer.max(dogs));

        // Compare dogs in natural order.
        Dog maxDog = (Dog) Maximizer.max(dogs);   // casting OurComparable to Dog.
        maxDog.bark();

        // Compare dogs by alphabetical order of their names.
        Comparator<Dog> nc = Dog.getNameComparator();
        Dog maxNameDog = (Dog) Maximizer.maxC(dogs, nc);
        maxNameDog.bark();
    }
}
