
public class Test{ 
   public static void pupAge(){
      int age = 0;
      age = age + 7;
      System.out.println(" My age is :-> " + age);
   }
   
   public static void main(String args[]){
      Test test = new Test();
      Test.pupAge();
   }
}
/*
import java.io.*;

class Employee{
   // this instance variable is visible for any child class.
   public String name;
   
   // salary  variable is visible in Employee class only.
   private double salary;
   
   // The name variable is assigned in the constructor. 
   public Employee (String empName){
      name = empName;
   }

   // The salary variable is assigned a value.
   public void setSalary(double empSal){
      salary = empSal;
   }
   
   // This method prints the employee details.
   public void printEmp(){
      System.out.println("Name  : " + name );
      System.out.println("Salary :" + salary);
   }

   public static void main(String args[]){
      Employee empOne = new Employee("Pragati");
      empOne.setSalary(10000);
      empOne.printEmp();
   }
}

*/
/*
import java.io.*;

class Employee{
   // salary  variable is a private static variable
   private static double salary;

   // DEPARTMENT is a constant
   public static final String DEPARTMENT = "Development";

   public static void main(String args[]){
      salary = 1000;
      System.out.println(DEPARTMENT+"average salary:"+salary);
   }
}


*/