package DB;

import Controller.DataAccessException;
import Model.Employee;

import java.util.List;

public interface EmployeeDBIF {
    List<Employee> findAll() throws DataAccessException;
    Employee findEmployeeByCPR(String employeeCPR) throws DataAccessException;
    int insertEmployee(Employee newEmployee) throws DataAccessException;
    int updateEmployee(String employeeCPR, Employee newEmployee) throws DataAccessException;
    int deleteEmployee(String employeeCPR) throws DataAccessException;
}
