package UDP;

import java.io.*;

public class Employee implements Serializable{

    private static final long serialVersionUID = 20260517L;

    private String name;
    private int salary;
    private String hireDate;

    public Employee() {
    }

    public Employee(String name, int salary, String hireDate) {
        this.name = name;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }
}
