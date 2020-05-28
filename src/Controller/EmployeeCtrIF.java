package Controller;

import DB.Exception.DataAccessException;
import Model.Employee;

import java.util.List;

public interface EmployeeCtrIF {
    List<Employee> findAllEmployees() throws DataAccessException;
    Employee findEmployeeByCPR(String cpr) throws DataAccessException;
    boolean insertEmployee(Employee newSeasonalWorker) throws DataAccessException;
    int updateEmployee(String cpr, Employee newEmployee) throws DataAccessException;
    int deleteEmployee(String cpr) throws DataAccessException;
}
