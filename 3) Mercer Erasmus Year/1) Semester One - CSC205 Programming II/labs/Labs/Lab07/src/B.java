class B extends A	{
	
	private char d;

	public B(char ch1, char ch2)	{
		super(ch1);
		this.d = ch2;
	}
	
	public void write()	{
		super.write();
		System.out.print(d);
	}
}	