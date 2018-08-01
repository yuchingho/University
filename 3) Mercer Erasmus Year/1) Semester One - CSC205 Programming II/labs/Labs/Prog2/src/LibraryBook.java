import java.util.ArrayList;

public class LibraryBook	{

    private static int numBooks = 0;
    private String title;
    private String author;
    private int copyright;
    private double price;
    private String genre;
    
    public LibraryBook (String booktitle, String authorname, int cpyright, double bookprice, String ngenre)	{	// Constructor for book.
        numBooks++;
        title = booktitle;
        author = authorname;
        copyright = cpyright;
        price = bookprice;
        genre = ngenre;
    }
    
    public String getTitle()	{
        return title;
    }
    
    public String getAuthor()	{
       	return author;
    }
    
    public int getCopyright()	{
       	return copyright;
    }
    
    public double getPrice()	{
       	return price;
    }
    
    public String getGenre()	{
       	return genre;
    }
    
    public int compareTo (LibraryBook book2)	{
       	return title.compareTo(book2.getTitle());
    }
}