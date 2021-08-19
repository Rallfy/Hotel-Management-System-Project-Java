package gui_pages;

public class Model {
    String idEmployee,firstName,lastName,gender,jobPosition,salary;

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Model(String string, String name, String lastName, String employee, String idEmployee, String firstName) {
        this.idEmployee = idEmployee;
        this.firstName = firstName;
        this.lastName = this.lastName;
        this.gender = gender;
        this.jobPosition = jobPosition;
        this.salary = salary;


    }
}
