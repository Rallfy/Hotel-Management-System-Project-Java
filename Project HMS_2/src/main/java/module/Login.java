package module;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Login {
    private int idEmployee;
    private String userName;
    private String password;
    private Employee employeeByIdEmployee;

    @Id
    @Column(name = "idEmployee")
    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    @Basic
    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return idEmployee == login.idEmployee && Objects.equals(userName, login.userName) && Objects.equals(password, login.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmployee, userName, password);
    }

    @OneToOne
    @JoinColumn(name = "idEmployee", referencedColumnName = "idEmployee", nullable = false)
    public Employee getEmployeeByIdEmployee() {
        return employeeByIdEmployee;
    }

    public void setEmployeeByIdEmployee(Employee employeeByIdEmployee) {
        this.employeeByIdEmployee = employeeByIdEmployee;
    }

}
