public class StaticTest		{

    public static void main(String args[])	{
    
        Person tom = new Person("Tom Jones");
        System.out.println("Person.getCount(): " + Person.getCount());
        System.out.println(tom);
        System.out.println();

        Person sue = new Person("Susan Top");
        System.out.println("Person.getCount(): " + Person.getCount());
        System.out.println(sue);
        System.out.println();

        Person fred = new Person("Fred Shoe");
        System.out.println("Person.getCount(): " + Person.getCount());
        System.out.println(fred);
        System.out.println();

        Person samplePerson = new Person();
        System.out.println("Person.getCount(): " + Person.getCount());
        samplePerson.reset(null, 0);
        System.out.println(samplePerson);
        System.out.println();

        System.out.println("tom.getCount(): " + Person.getCount());
        //System.out.println("tom.getCount(): " + tom.getCount());
        System.out.println("sue.getCount(): " + Person.getCount());
        //System.out.println("sue.getCount(): " + sue.getCount());
        System.out.println("fred.getCount(): " + Person.getCount());
        //System.out.println("fred.getCount(): " + fred.getCount());
        System.out.println("Person.getCount(): " + Person.getCount());
    }
}