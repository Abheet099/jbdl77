public class Employee {

    private String name;
    private int age;
    private int salary;
    private String dob;

    public Employee(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public Employee setSalary(int salary) {
        // perform some action
        this.salary = salary;
        return this;
    }

    public String getDob() {
        return dob;
    }

    public Employee setDob(String dob) {
        this.dob = dob;
//        this.age = functionToCalculateAge(dob);
        return this;
    }
}
