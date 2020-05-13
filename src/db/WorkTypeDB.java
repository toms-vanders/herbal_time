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
     * Pre-made queries for the program
     */
    private static final String findAll = "SELECT * FROM WorkType where workSiteID = ?";
    private static final String insertWorkType = "INSERT INTO WorkType VALUES(?,?,?,?,?)";

    /**
     * Prepared statement declaration for the above queries
     */

    private PreparedStatement PSfindAll;
    private PreparedStatement PSinsertWorkType;

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
            PSinsertWorkType = con.prepareStatement(insertWorkType);
        } catch (SQLException e) {
            throw new DataAccessException("Issue with preparing database statement", e);
        }
    }

    @Override
    public List<WorkType> findAll(Integer workSiteID, boolean fullAssociation, Type type) throws DataAccessException {
        List<WorkType> res = new ArrayList<WorkType>();
        ResultSet rs;
        try {
            PSfindAll.setInt(1, workSiteID);
        } catch (SQLException e) {
            throw new DataAccessException("Issue setting up the parameters when loading work types.", e);
        }
        try {
            rs = PSfindAll.executeQuery();
        } catch (SQLException e) {
            throw new DataAccessException("Issue loading work types from the database (executeQuery)", e);
        }
        res = buildObjects(rs, fullAssociation, type);
        return res;
    }

    @Override
    public Boolean insertWorkType(Integer workSiteID, WorkType newWorkType, Type type) throws DataAccessException {
        Boolean res = false;
        try {
            PSinsertWorkType.setString(1, newWorkType.getDescOfJob());
            PSinsertWorkType.setString(2, newWorkType.getTypeOfProduce());
            PSinsertWorkType.setString(3, newWorkType.getSalaryType());
            PSinsertWorkType.setDouble(4, newWorkType.getPay());
            PSinsertWorkType.setInt(5, workSiteID);
        } catch (SQLException e) {
            throw new DataAccessException("Issue setting up the parameters when adding new workType", e);
        }

        try {
            Integer affectedRows = PSinsertWorkType.executeUpdate();
            res = affectedRows > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Issue inserting the workType to database", e);
        }
        return res;
    }

    private List<WorkType> buildObjects(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException {
        List<WorkType> res = new ArrayList<>();
        try {
            while (rs.next()) {
                WorkType currentWorkType = buildObject(rs, fullAssociation, type);
                res.add(currentWorkType);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Issue loading workTypes from the database (in buildObjects)", e);
        }
        return res;
    }

    private WorkType buildObject(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException {
        WorkType currentWorkType = null;
        try {
            if (type.equals(WorkType.class)) {
                currentWorkType = new WorkType();
                currentWorkType.setWorkTypeID(rs.getInt("workTypeID"));
                currentWorkType.setDescOfJob(rs.getString("descOfJob"));
                currentWorkType.setTypeOfProduce(rs.getString("typeOfProduce"));
                currentWorkType.setSalaryType(rs.getString("salaryType"));
                currentWorkType.setPay(rs.getDouble("pay"));
                currentWorkType.setWorkSiteID(rs.getInt("workSiteID"));
            } else {
                throw new DataAccessException("Issue could not determine type.", new Exception());
            }
        } catch (SQLException e) {
            throw new DataAccessException("Issue loading workType from the database (in buildObject)", e);
        }
        return currentWorkType;
    }
}
