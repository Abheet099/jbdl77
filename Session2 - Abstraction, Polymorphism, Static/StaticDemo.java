class Student{
    String name;
    int rollNo;

    //static variable
    static String collegeName;
    static int rollNumber = 1;

    static void setCollegeName(String collegeName){
        Student.collegeName = collegeName;
//        collegeName = collegeName;
    }
    static int setRollNo(){
        rollNumber++;
        return rollNumber;
    }
}

public class StaticDemo {
    static{
//        Student.collegeName = "IIT";
        Student.setCollegeName("IIT");
    }
    public static void main(String[] args) {
       System.out.println(Student.collegeName);
    }

    static class nested{
    }
}
