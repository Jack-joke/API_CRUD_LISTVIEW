package com.example.api_into_listview_;

import java.io.Serializable;

public class Employee implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    private String FirstName;
    private String LastName;
    private String Gender;
    private String Salary;

    public Employee(int id, String firstName, String lastName, String gender, String salary) {
        this.id = id;
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        Salary = salary;
    }
}
