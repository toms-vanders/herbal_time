package Controller;

import DB.EmployeeDB;
import DB.EmployeeDBIF;
import Model.Employee;

import java.util.List;

/**
 * Used to interface between the database and the GUI
 */
public class EmployeeCtr implements EmployeeCtrIF {

    EmployeeDBIF employeeDB;

    public EmployeeCtr() throws DataAccessException{
        employeeDB = new EmployeeDB();
    }

    /**
     * Returns a List of all Employees in the database
     * @returns a List of all Employees in the database
     * @throws DataAccessException
     */
    @Override
    public List<Employee> findAllEmployees() throws DataAccessException {
        try {
            return employeeDB.findAll();
        } catch (DataAccessException e) {
            throw new DataAccessException("Employee controller unable to execute findAll method.", e);
        }
    }

    /**
     * Returns an Employee with the appropriate CPR number
     * @param cpr CPR number of the Employee that is wanted
     * @return Employee object with appropriate CPR number
     * @throws DataAccessException
     */
    @Override
    public Employee findEmployeeByCPR(String cpr) throws DataAccessException {
        try {
            return employeeDB.findEmployeeByCPR(cpr);
        } catch (DataAccessException e) {
            throw new DataAccessException("Employee controller unable to execute findEmployeeByCPR.", e);
        }
    }

    /**
     * Inserts a new Employee into the database
     * @param newEmployee the new Employee object to be inserted
     * @return Number of rows affected
     * @throws DataAccessException
     */
    @Override
    public boolean insertEmployee(Employee newEmployee) throws DataAccessException {
        try {
            return (employeeDB.insertEmployee(newEmployee)==1);
        } catch (DataAccessException e) {
            throw new DataAccessException("Employee controller unable to execute insertEmployee.", e);
        }
    }

    /**
     * Make changes to Employees already in the database
     * @param cpr CPR of the Employee wished to be changed
     * @param newEmployee new Employee object to replace the original one
     * @return Number of rows affected
     * @throws DataAccessException
     */
    @Override
    public int updateEmployee(String cpr, Employee newEmployee) throws DataAccessException {
        try {
            return employeeDB.updateEmployee(cpr, newEmployee);
        } catch (DataAccessException e) {
            throw new DataAccessException("Employee controller unable to execute updateEmployee.", e);
        }
    }

    /**
     * Deletes an Employee from the database
     * @param cpr CPR of the Employee wished to be deleted
     * @return Number of rows affected
     * @throws DataAccessException
     */
    @Override
    public int deleteEmployee(String cpr) throws DataAccessException {
        try {
            return employeeDB.deleteEmployee(cpr);
        } catch (DataAccessException e) {
            throw new DataAccessException("Employee controller unable to execute deleteEmployee.", e);
        }
    }
}
