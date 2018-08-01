public class Person	{

	private String name;
 	private int id;
  	private static int personCount = 0;

  	// constructor
 	public Person(String pname)	{
   		name = pname;
    	personCount++;
    	id = 100 + personCount;
  	}

	// static/class method
  	public static int getCount()	{
  		return personCount;
  	} 

	public String toString()	{
   		return "name: " + name + "  id: " + id + "  (Person count: " + personCount + ")";
  	}
	
	public Person()	{
		name = "N/A";
		personCount++;
    	id = -1;
  	}

	public void reset(String getName, int getId)	{
    	this.name = "Yu-Ching";
		this.id = 1111;
  	}

	public String getName()	{
    	return name;
  	}

	public int getId()	{
   		return id;
  	} 		
}