package DB;

import Controller.DataAccessException;
import Model.WorkType;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkTypeDB implements WorkTypeDBIF {

    /**
     * Query skeletons for the program
     */
    private static final String findAll = "SELECT * FROM WorkType";
    private static final String findByID = "SELECT * FROM WorkType where workTypeID = ?";
    private static final String findByWorkSite = "SELECT * FROM WorkType where workSiteID = ?";
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

    public WorkTypeDB() throws DataAccessException {
//        init();
    }


//    /**
//     * Initialize DB connection and prepare SQL statements
//     *
//     * @throws DataAccessException Throw an exception on statements that cannot be prepared
//     */
//    private void init() throws DataAccessException {
//        connectToDB();
//        Connection con = DBConnection.getInstance().getConnection();
//        try {
//            PSfindAll = con.prepareStatement(findAll);
//            PSfindByID = con.prepareStatement(findByID);
//            PSinsertWorkType = con.prepareStatement(insertWorkType);
//            PSupdateWorkType = con.prepareStatement(updateWorkType);
//            PSdeleteWorkType = con.prepareStatement(deleteWorkType);
//            PSfindByWorkSite = con.prepareStatement(findByWorkSite);
//        } catch (SQLException e) {
//            throw new DataAccessException("Issue with preparing database statement", e);
//        }
//    }

    private void connectToDB() throws DataAccessException{
        DBConnection.connect();
        if (DBConnection.instanceIsNull()) {
            throw new DataAccessException("Couldn't connect and read from database; throwing to GUI", new Exception());
        }
    }

    @Override
    public List<WorkType> findAll(boolean fullAssociation) throws DataAccessException {
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
        return buildObjects(rs, fullAssociation);
    }

    /**
     * Queries database for all work types within work site.
     *
     * @param workSiteID      ID of work site
     * @param fullAssociation specifies whether to build results with the objects the foreign keys point to or not
     * @return if found - an ArrayList of work types within the work site; otherwise an empty ArrayList
     * @throws DataAccessException
     */
    @Override
    public List<WorkType> findAllWorkTypesOfWorkSite(Integer workSiteID, boolean fullAssociation) throws DataAccessException {
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
        return buildObjects(rs, fullAssociation);
    }

    @Override
    public WorkType findWorkTypeByID(int workTypeID, boolean fullAssociation) throws DataAccessException {
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
            rs.next();
            WorkType res = buildObject(rs, fullAssociation);
            DBConnection.disconnect();
            return res;
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with retrieving work types from the database (executeQuery)", e);
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
    public Integer insertWorkType(Integer workSiteID, WorkType newWorkType) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSinsertWorkType = con.prepareStatement(insertWorkType);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        Integer affectedRows;
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

    @Override
    public Integer updateWorkType(int workTypeID, WorkType newWorkType) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSupdateWorkType = con.prepareStatement(updateWorkType);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        Integer affectedRows;
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

    @Override
    public Integer deleteWorkType(int workTypeID) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSdeleteWorkType = con.prepareStatement(deleteWorkType);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        Integer affectedRows;
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
     * @param fullAssociation specifies whether to build results with the objects the foreign keys point to or not
     * @return ArrayList of built objects
     * @throws DataAccessException
     */
    private List<WorkType> buildObjects(ResultSet rs, boolean fullAssociation) throws DataAccessException {
        List<WorkType> res = new ArrayList<>();
        try {
            while (rs.next()) {
                WorkType currentWorkType = buildObject(rs, fullAssociation);
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
     * @param fullAssociation specifies whether to build results with the objects the foreign keys point to or not
     * @return new WorkType object
     * @throws DataAccessException
     */
    private WorkType buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
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
            if (fullAssociation) {
                //
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Issue with loading work type from the database (in buildObject)", e);
        }
        return currentWorkType;
    }
}
