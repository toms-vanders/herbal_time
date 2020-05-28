package DB;

import Controller.DataAccessException;
import Model.Produce;
import Model.WorkSite;
import Model.WorkSiteProduce;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to access data about work site produce from the database.
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
public class WorkSiteProduceDB implements WorkSiteProduceDBIF{

    /**
     * Pre-made queries for the program
     */
    private static final String findAll = "SELECT * FROM WorkSiteProduce";
    private static final String findByProduce = "SELECT * FROM WorkSiteProduce WHERE produceName = ?";
    private static final String findByWorkSite = "SELECT * FROM WorkSiteProduce WHERE workSiteID = ?";
    private static final String insertWorkSiteProduce = "INSERT INTO WorkSiteProduce VALUES(?,?)";
    private static final String updateWorkSiteProduce = "UPDATE Produce SET "
                + "workSiteID = ?,"
                + "produceName = ?,"
                + " WHERE workSiteID = ? AND produceName = ?";
    private static final String deleteWorkSiteProduce = "DELETE FROM WorkSiteProduce WHERE workSiteID = ? AND produceName = ?";

    /**
     * Prepared statement declaration for the above queries
     */
    private PreparedStatement PSfindAll;
    private PreparedStatement PSfindByProduce;
    private PreparedStatement PSfindByWorkSite;
    private PreparedStatement PSinsertWorkSiteProduce;
    private PreparedStatement PSupdateWorkSiteProduce;
    private PreparedStatement PSdeleteWorkSiteProduce;

    /**
     * Constructor of WorkSiteProduce
     */
    public WorkSiteProduceDB() {

    }

    /**
     * Initialize DB connection and prepare SQL statements
     *
     * @throws DataAccessException Throw an exception on statements that cannot be prepared
     */
    private void connectToDB() throws DataAccessException{
        DBConnection.connect();
        if (DBConnection.instanceIsNull()) {
            throw new DataAccessException("Couldn't connect and read from database; throwing to GUI", new Exception());
        }
    }

    /**
     * Returns list of all work site produce stored in the database.
     *
     * @param type requires to be of proper type
     * @return list of all work site produce stored in the database if found, otherwise empty list
     * @throws DataAccessException
     */
    @Override
    public List<WorkSiteProduce> findAll(Type type) throws DataAccessException {
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
            rs = this.PSfindAll.executeQuery();
            return buildObjects(rs, type);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Error with fetching all WorkSiteProduce from DB.", e);
        }

    }

    /**
     * Returns list of WorkSiteProduce object that in the database collect produces with specified name.
     *
     * @param produceName name of produce to match with when searching for work site produce
     * @param type requires to be of proper type
     * @return list of WorkSiteProduce objects if found, otherwise empty list
     * @throws DataAccessException
     */
    @Override
    public List<WorkSiteProduce> findByProduce(String produceName, Type type) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindByProduce = con.prepareStatement(findByProduce);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        ResultSet rs;
        try {
            PSfindByProduce.setString(1, produceName);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting query parameters when loading WorkSiteProduce", e);
        }

        try {
            rs = PSfindByProduce.executeQuery();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with retrieving WorkSiteProduce from the database (executeQuery)", e);
        }
        return buildObjects(rs, type);
    }

    /**
     * Returns list of WorkSiteProduce objects by matching on specific work site ID.
     *
     * @param workSiteID ID of work site to match with when searching for work site produce
     * @param type requires to be of proper type
     * @return list of WorkSiteProducts if found, otherwise empty list
     * @throws DataAccessException
     */
    @Override
    public List<WorkSiteProduce> findByWorkSite(int workSiteID, Type type) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindByWorkSite = con.prepareStatement(findByWorkSite);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        ResultSet rs;
        try {
            PSfindByWorkSite.setInt(1, workSiteID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting query parameters when loading WorkSiteProduce", e);
        }

        try {
            rs = PSfindByWorkSite.executeQuery();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with retrieving WorkSiteProduce from the database (executeQuery)", e);
        }
        return buildObjects(rs, type);
    }

    /**
     * Inserts produce into database.
     *
     * @param newWorkSiteProduce instance of new WorkSiteProduce to be inserted into database
     * @param type requires to be of proper type
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public Integer insertWorkSiteProduce(WorkSiteProduce newWorkSiteProduce, Type type) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSinsertWorkSiteProduce = con.prepareStatement(insertWorkSiteProduce);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            // workSiteID, produceName
            PSinsertWorkSiteProduce.setInt(1, newWorkSiteProduce.getWorkSiteID());
            PSinsertWorkSiteProduce.setString(2, newWorkSiteProduce.getProduceName());
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with the WorkSiteProduce being inserted into DB.",e);
        }

        Integer affectedRows;
        try {
            affectedRows = PSinsertWorkSiteProduce.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with inserting WorkSiteProduce into DB.", e);
        }
        return affectedRows;
    }


    /**
     *
     * @param workSiteID
     * @param produceName
     * @param newWorkSiteProduce
     * @param type
     * @return
     * @throws DataAccessException
     */
    @Override
    public Integer updateWorkSiteProduce(int workSiteID, String produceName, WorkSiteProduce newWorkSiteProduce, Type type) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSupdateWorkSiteProduce = con.prepareStatement(updateWorkSiteProduce);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSupdateWorkSiteProduce.setInt(1, newWorkSiteProduce.getWorkSiteID());
            PSupdateWorkSiteProduce.setString(2, newWorkSiteProduce.getProduceName());
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was an error updating the Produce (newData).", e);
        }

        Integer affectedRows;
        try {
            affectedRows = PSupdateWorkSiteProduce.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was an error updating the WorkSiteProduce (applyingData).", e);
        }
        return affectedRows;
    }

    /**
     * Removes work site produce of specific produce name and work site ID from database.
     *
     * @param workSiteID work site ID of work site produce that is going to be removed
     * @param produceName produce name of work site produce that is going to be removed
     * @param type requires to be of proper type
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public Integer deleteWorkSiteProduce(int workSiteID, String produceName, Type type) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSdeleteWorkSiteProduce = con.prepareStatement(deleteWorkSiteProduce);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSdeleteWorkSiteProduce.setInt(1, workSiteID);
            PSdeleteWorkSiteProduce.setString(2, produceName);

        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting update parameters when deleting WorkSiteProduce", e);
        }

        Integer affectedRows;
        try {
            affectedRows = PSdeleteWorkSiteProduce.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was an error deleting the WorkSiteProduce.", e);
        }
        return affectedRows;
    }

    /**
     * Returns list of WorkSiteProduce objects after finding matching cases in database.
     *
     * @param rs ResultSet object returned after executing query
     * @param type requires to be of proper type
     * @return list of WorkSiteProduce objects
     * @throws DataAccessException
     */
    private List<WorkSiteProduce> buildObjects(ResultSet rs, Type type) throws DataAccessException {
        List<WorkSiteProduce> res = new ArrayList<>();
        try {
            while(rs.next()) {
                WorkSiteProduce currentWorkSiteProduce = buildObject(rs, type);
                res.add(currentWorkSiteProduce);
            }
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("buildObjects: There was an Error with building the List.", e);
        }
        return res;
    }

    /**
     * Gets data from the DB and builds a WorkSiteProduce object.
     *
     * @param rs The ResultSet from which a Produce object is to be assembled
     * @param type requires to be of proper type
     * @return an assembled WorkSiteProduce object
     * @throws DataAccessException
     */
    private WorkSiteProduce buildObject(ResultSet rs, Type type) throws DataAccessException {
        WorkSiteProduce currentWorkSiteProduce = null;

        try {
            if (type.equals(WorkSiteProduce.class)) {
                currentWorkSiteProduce = new WorkSiteProduce();
                currentWorkSiteProduce.setWorkSiteID(rs.getInt("workSiteID"));
                currentWorkSiteProduce.setProduceName(rs.getString("produceName"));
            } else {
                throw new DataAccessException("Could not determine type.", new Exception());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("buildObject: There was an Error with the WorkSiteProduce.", e);
        }

        return currentWorkSiteProduce;
    }
}
