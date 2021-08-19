package service;


import dao.EmployeeDao;
import module.Employee;

import javax.persistence.Persistence;
import java.util.List;

public class EmployeeService<T> {
    private EmployeeDao employeeDao;
    private Class<T> entityClass;

    public EmployeeService() {
        try {
            employeeDao = new EmployeeDao(Persistence.createEntityManagerFactory("default"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addEmployee(Employee newEmployee) {
        employeeDao.create(newEmployee);
    }

    public void updateEmployee(Employee updatedEmployee) {
        employeeDao.update(updatedEmployee);
    }

    public void removeEmployee(Employee removeEmployee, int employeeId) {
        employeeDao.remove(removeEmployee, removeEmployee.getIdEmployee());
    }

    public List<Employee> getAllEmployee() {
        return employeeDao.findAll();
    }

    public T find(int id) {
        T x = (T) employeeDao.find(id);
        return x;
    }

}

