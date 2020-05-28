package DB;

import Model.WorkType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to access data from the WorkType table in the database.
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
public class WorkTypeDB implements WorkTypeDBIF {

    /**
     * Query skeletons for the program
     */
    private static final String findAll = "SELECT * FROM WorkType";
    private static final String findByID = "SELECT * FROM WorkType where workTypeID = ?";
    private static final String findByWorkSite = "SELECT * FROM WorkType where workSiteID = ?";
    private static final String findWorkTypeIDByDescription = "SELECT TOP 1 * FROM WorkType where descOfJob = ?";
    private static final String insertWorkType = "INSERT INTO WorkType VALUES(?,?,?,?,?)";
    private static final String updateWorkType = "UPDATE WorkType SET "
            + "descOfJob = ?,"
            + "typeOfProduce = ?,"
            + "salaryType = ?,"
            + "pay = ?"
            + " WHERE workTypeID = ?";
    private static final String deleteWorkType = "DELETE FROM WorkType WHERE workTypeID = ?";

    /**
     * PreparedStatement declaration for the above queries
     */
    private PreparedStatement PSfindAll;
    private PreparedStatement PSfindByID;
    private PreparedStatement PSinsertWorkType;
    private PreparedStatement PSupdateWorkType;
    private PreparedStatement PSdeleteWorkType;
    private PreparedStatement PSfindByWorkSite;
    private PreparedStatement PSfindWorkTypeIDByDescription;

    /**
     * Constructor of WorkTypeDB
     */
    public WorkTypeDB()  {

    }

    /**
     * Initializes DB connection and prepares SQL statements.
     *
     * @throws DataAccessException On statements that cannot be prepared
     */
    private void connectToDB() throws DataAccessException{
        DBConnection.connect();
        if (DBConnection.instanceIsNull()) {
            throw new DataAccessException("Couldn't connect and read from database; throwing to GUI", new Exception());
        }
    }

    /**
     * Returns list of all WorkTypes stored in the database.
     *
     * @return list of all workTypes stored in the database if found, otherwise empty list
     * @throws DataAccessException
     */
    @Override
    public List<WorkType> findAll() throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindAll = con.prepareStatement(findAll);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        ResultSet rs;
        try {
            rs = PSfindAll.executeQuery();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with retrieving work types from the database (executeQuery)", e);
        }
        return buildObjects(rs);
    }

    /**
     * Queries database for all work types within work site.
     *
     * @param workSiteID      ID of work site
     * @return if found - an ArrayList of work types within the work site; otherwise an empty ArrayList
     * @throws DataAccessException
     */
    @Override
    public List<WorkType> findAllWorkTypesOfWorkSite(Integer workSiteID) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindByWorkSite = con.prepareStatement(findByWorkSite);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSfindByWorkSite.setInt(1, workSiteID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when loading work types.", e);
        }

        ResultSet rs;
        try {
            rs = PSfindByWorkSite.executeQuery();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with retrieving work types from the database (executeQuery)", e);
        }
        return buildObjects(rs);
    }

    /**
     * Returns WorkType object with a specific ID stored in the database.
     *
     * @param workTypeID ID of work type to be searched for
     * @return built WorkType object if database query found a match, otherwise null
     * @throws DataAccessException
     */
    @Override
    public WorkType findWorkTypeByID(int workTypeID) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindByID = con.prepareStatement(findByID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSfindByID.setInt(1, workTypeID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when loading work types.", e);
        }

        ResultSet rs;

        try {
            rs = PSfindByID.executeQuery();
            if (rs.next()) {
                WorkType res = buildObject(rs);
                DBConnection.disconnect();
                return res;
            } else {
                DBConnection.disconnect();
                return null;
            }
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with retrieving work types from the database (executeQuery)", e);
        }
    }

    /**
     * Only used for TestWorkType
     *
     * @param jobDescription String to search the descOfJob column
     * @return workTypeID of WorkType with that descOfJob
     * @throws DataAccessException
     */
    @Override
    public int findWorkTypeIDByDescription(String jobDescription) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindWorkTypeIDByDescription = con.prepareStatement(findWorkTypeIDByDescription);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSfindWorkTypeIDByDescription.setString(1, jobDescription);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when searching work types by descOfJob.", e);
        }
        ResultSet rs;
        try {
            rs = PSfindWorkTypeIDByDescription.executeQuery();
            if (rs.next()) {
                DBConnection.disconnect();
                return rs.getInt(1);
            } else {
                DBConnection.disconnect();
                return 0;
            }
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with retrieving work types by descOfJob from the database (executeQuery)", e);
        }
    }

    /**
     * Inserts work type into database and associates it with work site
     *
     * @param workSiteID  ID of worksite
     * @param newWorkType WorkType object to be added to database
     * @return true if insertion was successful, otherwise - false
     * @throws DataAccessException
     */
    @Override
    public int insertWorkType(Integer workSiteID, WorkType newWorkType) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSinsertWorkType = con.prepareStatement(insertWorkType);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        int affectedRows;
        try {
            PSinsertWorkType.setString(1, newWorkType.getDescOfJob());
            PSinsertWorkType.setString(2, newWorkType.getTypeOfProduce());
            PSinsertWorkType.setString(3, newWorkType.getSalaryType());
            PSinsertWorkType.setDouble(4, newWorkType.getPay());
            PSinsertWorkType.setInt(5, workSiteID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when adding new workType", e);
        }

        try {
            affectedRows = PSinsertWorkType.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with inserting work type to database", e);
        }

        return affectedRows;
    }

    /**
     * Updates record of work type with a specific ID number stored in database.
     *
     * @param workTypeID ID number of the work type that is going to be updated
     * @param newWorkType a new instance of WorkType to be updated into database where the old record was
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int updateWorkType(int workTypeID, WorkType newWorkType) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSupdateWorkType = con.prepareStatement(updateWorkType);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        int affectedRows;
        try {
            PSupdateWorkType.setString(1, newWorkType.getDescOfJob());
            PSupdateWorkType.setString(2, newWorkType.getTypeOfProduce());
            PSupdateWorkType.setString(3, newWorkType.getSalaryType());
            PSupdateWorkType.setDouble(4, newWorkType.getPay());
            PSupdateWorkType.setInt(5, workTypeID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when updating WorkType", e);
        }

        try {
            affectedRows = PSupdateWorkType.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with updating work type to database", e);
        }
        return affectedRows;
    }

    /**
     * Removes work site of specific ID number from database.
     *
     * @param workTypeID ID number of work type that is going to be removed
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int deleteWorkType(int workTypeID) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSdeleteWorkType = con.prepareStatement(deleteWorkType);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        int affectedRows;
        try {
            PSdeleteWorkType.setInt(1, workTypeID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when deleting workType", e);
        }

        try {
            affectedRows = PSdeleteWorkType.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with deleting work type from database", e);
        }
        return affectedRows;
    }

    /**
     * Generates and returns WorkType objects based on the ResultSet returned by the query
     *
     * @param rs              ResultSet object filled with results of a query
     * @return ArrayList of built objects
     * @throws DataAccessException
     */
    private List<WorkType> buildObjects(ResultSet rs) throws DataAccessException {
        List<WorkType> res = new ArrayList<>();
        try {
            while (rs.next()) {
                WorkType currentWorkType = buildObject(rs);
                res.add(currentWorkType);
            }
            DBConnection.disconnect();
            return  res;
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with loading work types from database (in buildObjects)", e);
        }
    }

    /**
     * Builds a single WorkType object
     *
     * @param rs              ResultSet object filled with results of a query

     * @return new WorkType object
     * @throws DataAccessException
     */
    private WorkType buildObject(ResultSet rs) throws DataAccessException {
        WorkType currentWorkType;
        try {
            currentWorkType = new WorkType();
//            if(rs.next()){
            currentWorkType.setWorkTypeID(rs.getInt("workTypeID"));
            currentWorkType.setDescOfJob(rs.getString("descOfJob"));
            currentWorkType.setTypeOfProduce(rs.getString("typeOfProduce"));
            currentWorkType.setSalaryType(rs.getString("salaryType"));
            currentWorkType.setPay(rs.getDouble("pay"));
//            currentWorkType.setWorkSiteID(rs.getInt("workSiteID"));

//            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Issue with loading work type from the database (in buildObject)", e);
        }
        return currentWorkType;
    }
}
