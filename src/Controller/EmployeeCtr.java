package Controller;

import DB.EmployeeDB;
import DB.EmployeeDBIF;
import Model.Employee;

import java.util.List;

public class EmployeeCtr implements EmployeeCtrIF {

    EmployeeDBIF employeeDB;

    public EmployeeCtr() throws DataAccessException{
        try{
            employeeDB = new EmployeeDB();
        }catch(DataAccessException e){
            throw new DataAccessException("Unable to obtain employee database connection instance." , e);
        }
    }

    @Override
    public List<Employee> findAllEmployees() throws DataAccessException {
        try {
            return employeeDB.findAll();
        } catch (DataAccessException e) {
            throw new DataAccessException("Employee controller unable to execute findAll method.", e);
        }
    }

    @Override
    public Employee findEmployeeByCPR(String cpr) throws DataAccessException {
        try {
            return employeeDB.findEmployeeByCPR(cpr);
        } catch (DataAccessException e) {
            throw new DataAccessException("Employee controller unable to execute findEmployeeByCPR.", e);
        }
    }

    @Override
    public int insertEmployee(Employee newEmployee) throws DataAccessException {
        try {
            return employeeDB.insertEmployee(newEmployee);
        } catch (DataAccessException e) {
            throw new DataAccessException("Employee controller unable to execute insertEmployee.", e);
        }
    }

    @Override
    public int updateEmployee(String cpr, Employee newEmployee) throws DataAccessException {
        try {
            return employeeDB.updateEmployee(cpr, newEmployee);
        } catch (DataAccessException e) {
            throw new DataAccessException("Employee controller unable to execute updateEmployee.", e);
        }
    }

    @Override
    public void deleteEmployee(String cpr) throws DataAccessException {
        try {
            employeeDB.deleteEmployee(cpr);
        } catch (DataAccessException e) {
            throw new DataAccessException("Employee controller unable to execute deleteEmployee.", e);
        }
    }
}
