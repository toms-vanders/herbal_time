package Controller;

import Model.Employee;

import java.util.List;

public interface EmployeeCtrIF {
    List<Employee> findAllEmployees() throws DataAccessException;
    Employee findEmployeeByCPR(String cpr) throws DataAccessException;
    int insertEmployee(Employee newSeasonalWorker) throws DataAccessException;
    int updateEmployee(String cpr, Employee newEmployee) throws DataAccessException;
    void deleteEmployee(String cpr) throws DataAccessException;
}
