public class Leaves {

	public static void main(String[] args) {
		TextIO.put("How many leaves do you have? ");
		int leafCount = TextIO.getlnInt();

		switch (leafCount) {
		case 0:
			TextIO.putln("No leaves");
			break;
		case 1:
			TextIO.putln("One leaf");
			break;
		case 2:
			TextIO.putln("A couple of leaves");
			break;
		case 3:
		case 4:
		case 5:
			TextIO.putln("A handful of leaves");
			break;
		default:
			TextIO.putln("More than a handful of leaves");
			break;
		}
	}
}