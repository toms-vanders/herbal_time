package DB;

import Controller.DataAccessException;
import Model.WorkType;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        init();
    }

    /**
     * Initialize DB connection and prepare SQL statements
     *
     * @throws DataAccessException Throw an exception on statements that cannot be prepared
     */
    private void init() throws DataAccessException {
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindAll = con.prepareStatement(findAll);
            PSfindByID = con.prepareStatement(findByID);
            PSinsertWorkType = con.prepareStatement(insertWorkType);
            PSupdateWorkType = con.prepareStatement(updateWorkType);
            PSdeleteWorkType = con.prepareStatement(deleteWorkType);
            PSfindByWorkSite = con.prepareStatement(findByWorkSite);
        } catch (SQLException e) {
            throw new DataAccessException("Issue with preparing database statement", e);
        }
    }

    @Override
    public List<WorkType> findAll(boolean fullAssociation, Type type) throws DataAccessException {
        ResultSet rs;
        try {
            rs = PSfindAll.executeQuery();
        } catch (SQLException e) {
            throw new DataAccessException("Issue with retrieving work types from the database (executeQuery)", e);
        }
        return buildObjects(rs, fullAssociation, type);
    }

    /**
     * Queries database for all work types within work site.
     *
     * @param workSiteID ID of work site
     * @param fullAssociation specifies whether to build results with the objects the foreign keys point to or not
     * @param type type of object in the code the query looks for in the database
     * @return if found - an ArrayList of work types within the work site; otherwise an empty ArrayList
     * @throws DataAccessException
     */
    @Override
    public List<WorkType> findAllWorkTypesOfWorkSite(Integer workSiteID, boolean fullAssociation, Type type) throws DataAccessException {
        ResultSet rs;
        try {
            PSfindByWorkSite.setInt(1, workSiteID);
        } catch (SQLException e) {
            throw new DataAccessException("Issue with setting up query parameters when loading work types.", e);
        }

        try {
            rs = PSfindByWorkSite.executeQuery();
        } catch (SQLException e) {
            throw new DataAccessException("Issue with retrieving work types from the database (executeQuery)", e);
        }
        return buildObjects(rs, fullAssociation, type);
    }

    @Override
    public WorkType findWorkTypeByID(int workTypeID, boolean fullAssociation, Type type) throws DataAccessException {
        ResultSet rs;
        try {
            PSfindByID.setInt(1, workTypeID);
        } catch (SQLException e) {
            throw new DataAccessException("Issue with setting up query parameters when loading work types.", e);
        }

        try {
            rs = PSfindByID.executeQuery();
        } catch (SQLException e) {
            throw new DataAccessException("Issue with retrieving work types from the database (executeQuery)", e);
        }
        return buildObject(rs, fullAssociation, type);
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
        Integer affectedRows;
        try {
            PSinsertWorkType.setString(1, newWorkType.getDescOfJob());
            PSinsertWorkType.setString(2, newWorkType.getTypeOfProduce());
            PSinsertWorkType.setString(3, newWorkType.getSalaryType());
            PSinsertWorkType.setDouble(4, newWorkType.getPay());
            PSinsertWorkType.setInt(5, workSiteID);
        } catch (SQLException e) {
            throw new DataAccessException("Issue with setting up query parameters when adding new workType", e);
        }

        try {
            affectedRows = PSinsertWorkType.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Issue with inserting work type to database", e);
        }

        return affectedRows;
    }

    @Override
    public Integer updateWorkType(int workTypeID, WorkType newWorkType, Type type) throws DataAccessException {
        Integer affectedRows;
        try {
            PSupdateWorkType.setString(1,newWorkType.getDescOfJob());
            PSupdateWorkType.setString(2,newWorkType.getTypeOfProduce());
            PSupdateWorkType.setString(3,newWorkType.getSalaryType());
            PSupdateWorkType.setDouble(4,newWorkType.getPay());
            PSupdateWorkType.setInt(5,workTypeID);
        } catch (SQLException e) {
            throw new DataAccessException("Issue with setting up query parameters when updating WorkType", e);
        }
        try {
            affectedRows = PSupdateWorkType.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Issue with updating work type to database", e);
        }
        return affectedRows;
    }

    @Override
    public Integer deleteWorkType(int workTypeID) throws DataAccessException {
        Integer affectedRows;
        try {
            PSdeleteWorkType.setInt(1, workTypeID);
        } catch (SQLException e) {
            throw new DataAccessException("Issue with setting up query parameters when deleting workType", e);
        }

        try {
            affectedRows = PSdeleteWorkType.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Issue with deleting work type from database", e);
        }
        return affectedRows;
    }

    /**
     * Generates and returns WorkType objects based on the ResultSet returned by the query
     *
     * @param rs ResultSet object filled with results of a query
     * @param fullAssociation specifies whether to build results with the objects the foreign keys point to or not
     * @param type type of the object that is going to be built
     * @return ArrayList of built objects
     * @throws DataAccessException
     */
    private List<WorkType> buildObjects(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException {
        List<WorkType> res = new ArrayList<>();
        try {
            while (rs.next()) {
                WorkType currentWorkType = buildObject(rs, fullAssociation, type);
                res.add(currentWorkType);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Issue with loading work types from database (in buildObjects)", e);
        }
        return res;
    }

    /**
     * Builds a single WorkType object
     * TODO fullAssociation is unneeded here?
     * TODO also fullAssociation description is wacky, needs to be fixed for every method that has it
     *
     * @param rs ResultSet object filled with results of a query
     * @param fullAssociation specifies whether to build results with the objects the foreign keys point to or not
     * @param type type of the object that is going to be built
     * @return new WorkType object
     * @throws DataAccessException
     */
    private WorkType buildObject(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException {
        WorkType currentWorkType;
        try {
            if (type.equals(WorkType.class)) {
                currentWorkType = new WorkType();
                currentWorkType.setWorkTypeID(rs.getInt("workTypeID"));
                currentWorkType.setDescOfJob(rs.getString("descOfJob"));
                currentWorkType.setTypeOfProduce(rs.getString("typeOfProduce"));
                currentWorkType.setSalaryType(rs.getString("salaryType"));
                currentWorkType.setPay(rs.getDouble("pay"));
//                currentWorkType.setWorkSiteID(rs.getInt("workSiteID"));

                if (fullAssociation) {
                    //todo
                }
            } else {
                throw new DataAccessException("Issue: could not determine type.", new Exception());
            }
        } catch (SQLException e) {
            throw new DataAccessException("Issue with loading work type from the database (in buildObject)", e);
        }
        return currentWorkType;
    }
}
