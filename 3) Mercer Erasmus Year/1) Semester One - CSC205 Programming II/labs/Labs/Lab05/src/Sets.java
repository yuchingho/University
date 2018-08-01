import java.util.*;

public class Sets	{

	private static final int BOUND = 8;

	public static void main(String[] args)	{

		BitSet set1 = new BitSet(BOUND);
		BitSet set2 = new BitSet(BOUND);
		
		for (int i = 1; i < BOUND; i *= 2)
		set1.set(i);

		for (int i = BOUND - 1; i > 0; i /= 2)
		set2.set(i);

		System.out.println("Set1 = " + set1);
		System.out.println("Set2 = " + set2);

		System.out.println("Inverse of Set1 = " + inverse(set1));
		System.out.println("Inverse of Set2 = " + inverse(set2));
	}

	public static void print(BitSet set)	{
		for (int i = 0; i < BOUND; i++)	{
			if (set.get(i) == true)
				System.out.print("1");
			else
				System.out.print("0");
		}
	}

	private static BitSet inverse(BitSet set)	{
		for (int i = 0; i < BOUND; i++)	{
			if (set.get(i) == true)
				set.clear(i);
			else
				set.set(i);
		}
		return set;
	}
}