package com.maple.entities;

public class Student {
    private int id;
    private String studentID;
    private String name;
    private int age;
    private String academy;
    private String grade;
    private String specialty;
    private String contact;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student { id: " + this.getId()
                + ", StudentId: " + this.getStudentID()
                + ", Name: " + this.getName()
                + ", Age: " + this.getAge()
                + ", Academy: " + this.getAcademy()
                + ", Grade: " + this.getGrade()
                + ", Specialty: " + this.getSpecialty()
                + ", Contact: " + this.getContact()
                + ", Status: " + this.getStatus()
                + " }";
    }
}
