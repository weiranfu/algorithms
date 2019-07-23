import java.util.Comparator;

public class Dog implements Comparable<Dog> {
	private int size;
	private String name;

	public Dog(String n, int s) {
		name = n;
		size = s;
	}

	public void bark() {
		System.out.println(name + " says: bark!");
	}

	@Override
	/** Return -1 if the size of this dog is less than the dog pointed by o, and so forth. */
	public int compareTo(Dog thatDog) {
		// casting o so that we can get its size.
		return size - thatDog.size;
	}

	/** A nested class to implement Comparator<Dog>. */
	// Why a static class? Because we don't need to instantiate a Dog to get a NameComparator.
	private static class NameComparator implements Comparator<Dog> {
		@Override
		public int compare(Dog d1, Dog d2) {
			return d1.name.compareTo(d2.name);
		}
	}

	/** Return the instantiated nested class NameComparator.
	  * In this way, we can use method in nested class. */
	public static Comparator<Dog> getNameComparator() {
		return new NameComparator();
	}
}