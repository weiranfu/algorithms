/**
 * A Max function that can take in an array of general data type Objects and
 * return the maximum object in the array
 */
public class MaxGeneralObject {
    public static OurComparable max(OurComparable[] items) {
        int maxIndex = 0;
        for (int i = 0; i < items.length; i += 1) {
            if (items[maxIndex].compareTo(items[i]) < 0) {
                maxIndex = i;
            }
        }
        return items[maxIndex];
    }

    public static void main(String[] args) {
        Dog[] dogs = {new Dog("Elyse", 3), new Dog("Sture", 9), new Dog("Benjamin", 15)};
        Dog maxDog = (Dog) max(dogs);
        maxDog.bark();
    }
}
