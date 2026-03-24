public class Employee extends Person
{   // private attributes only accessed directly inside class
    private String position;
    private double salary;

    //constructor with parameters initializing attributes , uses constructor of parent class (Person) with super method
    public Employee(String name, String id, String phone, String position, double salary) 
    {
        super(name, id, phone);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() //returns position  (no parameters)
    {
        return position;
    }

    public double getSalary() //returns Salary  (no parameters)
    {
        return salary;
    }

    public void setPosition(String position) //Sets new position and has no return
    {
        this.position = position;
    }

    public String getRole()  //returns role  (no parameters) method taken from abstract class Person
    {
        return "Employee - "+position;
    }

    public String toString() // returns info in string format (no parameters)
    {return "\nName: "+getName()+
             "\nID: "+getId()+
            "\nPhone: "+getPhone()+
            "\nRole: "+getRole()+
            "\nSalary: "+ salary;
    }

    
}
