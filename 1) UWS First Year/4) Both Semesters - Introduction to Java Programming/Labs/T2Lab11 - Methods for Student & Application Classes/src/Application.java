public class Application {
	
	public static void main(String[] args) {
		final int MAX_SIZE = 19;
		Student[] register = new Student[MAX_SIZE];
		int count = 0;
		TextIO.putln("A class can have 20 students.");
		TextIO.put("Enter a student name or press <Enter> again to grade tests: ");
		String name = TextIO.getln();
      
		while (name.length() > 0 && count < MAX_SIZE) {
			register[count] = new Student();
			register[count].name = name;
			count++;
			TextIO.put("Enter a student name or press <Enter> again to grade tests: ");
			name = TextIO.getln();
		}
		TextIO.putln("");
		for (int student = 0; student < count; student++) {
			TextIO.put("Enter the mark for Test1 for " + register[student].name + ": ");
			register[student].test1 = TextIO.getDouble();
		}
		TextIO.putln("");
		for (int student = 0; student < count; student++) {
			TextIO.put("Enter the mark for Test2 for " + register[student].name + ": ");
			register[student].test2 = TextIO.getDouble();
		}
		TextIO.putln("");
		for (int student = 0; student < count; student++) {
			TextIO.put("Enter the mark for Test3 for " + register[student].name + ": ");
			register[student].test3 = TextIO.getDouble();
		}
		displayStudents(register, count);
	}
   
	public static void displayStudents(Student[] moduleList, int count) {
		TextIO.putln("\nThe class has these " + count + " students and their marks are:");
		for (int i = 0; i < count; i++) {
			TextIO.putln(moduleList[i]);
		}
	}
}