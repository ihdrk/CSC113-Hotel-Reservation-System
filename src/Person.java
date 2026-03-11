abstract class Person 
{
    private  String name;
    private String id;
    private String phone;

    public Person(String name , String id , String phone)
    {
        this.name = name;
        this.id = id;
        this.phone = phone;
    }

    public String getName() 
    {
        return name;
    }

    public String getId() 
    {
        return id;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public abstract String getRole();

    public String toString()
    {
        return "\nName: "+name+"\nID: "+id+"\nPhone: "+phone;
    }
    

    
}
