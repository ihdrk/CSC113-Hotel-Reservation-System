public abstract class Person 
{   // private attributes only accessed directly inside class
    private  String name;
    private String id;
    private String phone;

    public Person(String name , String id , String phone)//constructor with parameters initializing attributes
    {
        this.name = name;
        this.id = id;
        this.phone = phone;
    }

    public String getName() //returns name  (no parameters)
    {
        return name;
    }

    public String getId() //returns id number (no parameters)
    {
        return id;
    }

    public String getPhone() //returns phone number (no parameters)
    {
        return phone;
    }

    public void setPhone(String phone) //Sets new phone number and has no return
    {
        this.phone = phone;
    }

    public abstract String getRole(); // abstract method later used in Class Customer and Employee

    public String toString() // returns info in string format (no parameters)
    {
        return "\nName: "+name+"\nID: "+id+"\nPhone: "+phone;
    }
    

    
}
