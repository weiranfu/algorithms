public class Dog implements OurComparable<Dog> {
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
}