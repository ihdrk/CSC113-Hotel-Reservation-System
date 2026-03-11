public class Employee extends Person
{
    private String position;
    private double salary;

    public Employee(String name, String id, String phone, String position, double salary)
    {
        super(name, id, phone);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() 
    {
        return position;
    }

    public double getSalary() 
    {
        return salary;
    }

    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getRole()
    {
        return "Employee - "+position;
    }

    public String toString()
    {return "\nName: "+getName()+"\nID: "+getId()+"\nPhone: "+getPhone()+"\nRole: "+getRole()+"\nSalary: "+ salary;}

    
}
