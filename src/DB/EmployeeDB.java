package DB;

import Controller.DataAccessException;
import Model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to access data from the Employee table in the database.
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
public class EmployeeDB implements EmployeeDBIF {

    /**
     * Pre-made queries for the program
     */
    private static final String findAll = "SELECT * FROM Employee emp JOIN PERSON ps ON emp.cpr = ps.cpr";
    private static final String findEmployeeByCPR = "SELECT * FROM Employee WHERE cpr = ?";
    private static final String insertEmployee = "INSERT INTO Employee VALUES(?,?,?,?)";
    private static final String updateEmployee = "UPDATE Employee SET "
            + "cpr = ?,"
            + "regNum = ?,"
            + "kontoNum = ?,"
            + "salary = ?"
            + " WHERE cpr = ?";
    private static final String deleteEmployeeByCPR = "DELETE FROM Employee WHERE cpr = ?";

    /**
     * Prepared statement declaration for the above queries
     */
    private PreparedStatement PSfindAll;
    private PreparedStatement PSfindEmployeeByCPR;
    private PreparedStatement PSinsertEmployee;
    private PreparedStatement PSupdateEmployee;
    private PreparedStatement PSdeleteEmployeeByCPR;

    /**
     * Constructor for EmployeeDB
     */
    public EmployeeDB() {

    }

    /**
     * Initialize DB connection and prepare SQL statements.
     *
     * @throws DataAccessException Throw an exception on statements that cannot be prepared
     */
    private void connectToDB() throws DataAccessException {
        DBConnection.connect();
        if (DBConnection.instanceIsNull()) {
            throw new DataAccessException("Couldn't connect and read from database; throwing to GUI", new Exception());
        }
    }

    /**
     * Returns list of all employees stored in the database.
     *
     * @return list of employees if found, otherwise an empty List
     * @throws DataAccessException
     */
    @Override
    public List<Employee> findAll() throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindAll = con.prepareStatement(findAll);
        } catch (SQLException e) {
            throw new DataAccessException("Issue preparing statement", e);
        }

        ResultSet rs;
        try {
            rs = this.PSfindAll.executeQuery();
            return buildObjects(rs);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Error with fetching all Employees from DB.", e);
        }
    }

    /**
     * Returns Employee object with a specific CPR number stored in the database.
     *
     * @param employeeCPR CPR of employee to be searched for
     * @return Employee object with a specific CPR number if found, otherwise null
     * @throws DataAccessException
     */
    @Override
    public Employee findEmployeeByCPR(String employeeCPR) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindEmployeeByCPR = con.prepareStatement(findEmployeeByCPR);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSfindEmployeeByCPR.setString(1, employeeCPR);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Could not prepare statement.", e);
        }

        ResultSet rs;
        try {
            rs = this.PSfindEmployeeByCPR.executeQuery();
            if (rs.next()) {
                Employee res = buildObject(rs);
                DBConnection.disconnect();
                return res;
            } else {
                DBConnection.disconnect();
                return null;
            }
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Error with fetching a specific Employee from DB.", e);
        }
    }

    /**
     * Inserts employee into database.
     *
     * @param newEmployee instance of new employee to be inserted into database
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int insertEmployee(Employee newEmployee) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSinsertEmployee = con.prepareStatement(insertEmployee);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        int affectedRows;
        try {
            PSinsertEmployee.setString(1, newEmployee.getCpr());
            PSinsertEmployee.setInt(2, newEmployee.getRegNum());
            PSinsertEmployee.setInt(3, newEmployee.getKontoNum());
            PSinsertEmployee.setBigDecimal(4, newEmployee.getSalary());
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Could not prepare statement.", e);
        }

        try {
            affectedRows = PSinsertEmployee.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Employee insertion failed, unable to retrieve update information.", e);
        }
        return affectedRows;

    }

    /**
     * Updates record of employee with a specific CPR stored in database.
     *
     * @param employeeCPR CPR number of the employee that is going to be updated
     * @param newEmployee a new instance of employee to be updated into database where the old record was
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int updateEmployee(String employeeCPR, Employee newEmployee) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSupdateEmployee = con.prepareStatement(updateEmployee);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Unable to prepare update statement.", e);
        }

        int affectedRows;
        try {
            PSupdateEmployee.setString(1, newEmployee.getCpr());
            PSupdateEmployee.setInt(2, newEmployee.getRegNum());
            PSupdateEmployee.setInt(3, newEmployee.getKontoNum());
            PSupdateEmployee.setBigDecimal(4, newEmployee.getSalary());
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Could not set statement variables.", e);
        }

        try {
            affectedRows = PSupdateEmployee.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Employee update failed, unable to execute update.", e);
        }
        return affectedRows;
    }

    /**
     * Removes employee of specific CPR number from database.
     *
     * @param employeeCPR CPR number of employee that is going to be removed
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int deleteEmployee(String employeeCPR) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSdeleteEmployeeByCPR = con.prepareStatement(deleteEmployeeByCPR);
        } catch(SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Unable to prepare update statement.", e);
        }

        int affectedRows;
        try {
            PSdeleteEmployeeByCPR.setString(1,employeeCPR);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Could not set statement variables.", e);
        }

        try {
            affectedRows = PSdeleteEmployeeByCPR.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Employee deletion failed to execute.", e);
        }
        return affectedRows;

    }

    /**
     * Returns list of Employee objects after finding matching cases in database.
     *
     * @param rs ResultSet object returned after executing query
     * @return list of Employee objects
     * @throws DataAccessException
     */
    private List<Employee> buildObjects(ResultSet rs) throws DataAccessException {
        List<Employee> res = new ArrayList<>();
        try {
            while (rs.next()) {
                Employee currentEmployee = buildObject(rs);
                res.add(currentEmployee);
            }
            DBConnection.disconnect();
            return res;
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("buildObjects: There was an Error with building the List.", e);
        }
    }

    /**
     * Gets data from the DB and builds an Employee object.
     *
     * @param rs The ResultSet from which an Employee object is to be assembled
     * @return An assembled Employee object
     * @throws DataAccessException
     */
    private Employee buildObject(ResultSet rs) throws DataAccessException {
        Employee currentEmployee;
        try {
            currentEmployee = new Employee(rs.getString("cpr"),
                    rs.getString("fname"),
                    rs.getString("lname"),
                    rs.getDate("dateOfBirth"),
                    rs.getString("sex").charAt(0),
                    rs.getString("email"),
                    rs.getString("phoneNum"),
                    rs.getString("streetName"),
                    rs.getString("streetNum"),
                    rs.getString("zip"),
                    rs.getString("countryCode"),
                    rs.getString("country"),
                    rs.getInt("regNum"),
                    rs.getInt("kontoNum"),
                    rs.getBigDecimal("salary"));
            return currentEmployee;
        } catch (SQLException e) {
            throw new DataAccessException("buildObject: There was an Error with building the Employee object.", e);
        }
    }
}
