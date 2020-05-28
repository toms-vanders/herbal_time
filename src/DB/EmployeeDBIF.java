package DB;

import Model.Employee;

import java.util.List;

/**
 * User of this interface implements access to data from the Employee table in the database.
 *
 * @author Daniel Zoltan Ban
 * @author Mikuláš Dobrodej
 * @author Adrian Mihai Dohot
 * @author Damian Hrabąszcz
 * @author Toms Vanders
 * @version 1.0
 *
 * Date: 29.05.2020
 */
public interface EmployeeDBIF {
    List<Employee> findAll() throws DataAccessException;
    Employee findEmployeeByCPR(String employeeCPR) throws DataAccessException;
    int insertEmployee(Employee newEmployee) throws DataAccessException;
    int updateEmployee(String employeeCPR, Employee newEmployee) throws DataAccessException;
    int deleteEmployee(String employeeCPR) throws DataAccessException;
}
