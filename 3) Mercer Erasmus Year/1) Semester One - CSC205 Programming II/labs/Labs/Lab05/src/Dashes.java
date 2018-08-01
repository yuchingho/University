public class Dashes	{

	public static void main(String[] args)	{

		String word = "Two-Thousand-And-Sixteen";
		System.out.println(removeDashes(word));	
	}

	public static String removeDashes(String word)	{
		StringBuffer newWord = new StringBuffer();
		
		for(int i = 0; i < word.length(); i++)	{
			if(word.charAt(i) == '-')
				newWord.append(" ");
			else
				newWord.append(word.charAt(i));
		}
		String changedWord = newWord.toString();
		return changedWord;
	}
}