public class Dog implements OurComparable {
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
	public int compareTo(Object o) {
		Dog thatDog = (Dog) o;
		if (size < thatDog.size) {
			return -1;
		} else if (size == thatDog.size) {
			return 0;
		} else {
			return 1;
		}
	}
}