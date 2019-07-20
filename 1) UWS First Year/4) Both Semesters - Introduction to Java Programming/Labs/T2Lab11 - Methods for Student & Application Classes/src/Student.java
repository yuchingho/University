public class Student {
	
	public String name;
	public double test1, test2, test3;
	
	public double getAverage() {
		return (test1 + test2 + test3) / 3;
	}
	
	public String toString() {
		return name + ": Test1 = " + test1 + ", Test2 = " + test2 + ", Test3 = " + test3;
	}
}