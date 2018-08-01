import java.util.*;

public class LabArrayList	{

	public static void main(String[] args)	{
	
		ArrayList<String> a = new ArrayList<String>(3);
		System.out.println(a.size());

		a.add("jazz");
		a.add("rock");
		a.add("top40");
		a.add(2, "metal");
		a.set(1, "classical");
		a.remove("jazz");
		a.add("country");
		a.add(a.get(a.size()-1));

		System.out.println(a.size());
		
		for (int i = 0; i < a.size(); i++)
		System.out.print(a.get(i) + " ");
		
		System.out.println();
		System.out.println(a.indexOf("metal"));
		System.out.println(a.contains("jazz"));

		String str = (String) a.get(0);  
		Object obj = a.get(a.size()-1);
		System.out.println(str + " " + obj);
		System.out.println(a.size());
	}
}