package DB;

import DB.Exception.DataAccessException;
import Model.WorkSite;
import Model.WorkType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to access data about work site from the database.
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
public class WorkSiteDB implements WorkSiteDBIF {
    /**
     * Query skeletons for the program
     */
    private static final String findAll = "SELECT * FROM WorkSite";
    private static final String findByID = "SELECT * FROM WorkSite WHERE workSiteID = ?";
    private static final String findByName = "SELECT * FROM WorkSite WHERE siteName = ?";
    private static final String findWorkSitesOfClient = "SELECT * FROM WorkSite WHERE cvr = ?";
    private static final String insertWorkSite = "INSERT INTO WorkSite(siteName, siteDescription, streetName, streetNum," +
            " zip, typeOfJob, pricePerWorker, cvr) VALUES (?,?,?,?,?,?,?,?)";
    private static final String updateWorkSite = "UPDATE WorkSite SET "
            + "siteName = ?,"
            + "siteDescription = ?,"
            + "streetName = ?,"
            + "streetNum = ?,"
            + "zip = ?,"
            + "typeOfJob = ?,"
            + "pricePerWorker = ? "
            + "WHERE workSiteID = ?";
    private static final String updateWorkSiteByName = "UPDATE WorkSite SET "
            + "siteName = ?,"
            + "siteDescription = ?,"
            + "streetName = ?,"
            + "streetNum = ?,"
            + "zip = ?,"
            + "typeOfJob = ?,"
            + "pricePerWorker = ? "
            + "WHERE siteName = ?";
    private static final String deleteWorkSite = "DELETE FROM Worksite WHERE workSiteID = ?";
    private static final String findWorkSiteByCPR = "SELECT * FROM WorkSite " +
            "WHERE workSiteID = " +
            "(SELECT wSiteID FROM SeasonalWorker " +
            "WHERE SeasonalWorker.cpr = ?)";
    private static final String deleteWorkSiteByName = "DELETE FROM Worksite WHERE siteName = ?";
    /**
     * PreparedStatement declarations for the above queries
     */

    private PreparedStatement PSfindAll;
    private PreparedStatement PSfindByID;
    private PreparedStatement PSfindByName;
    private PreparedStatement PSinsertWorkSite;
    private PreparedStatement PSupdateWorkSite;
    private PreparedStatement PSupdateWorkSiteByName;
    private PreparedStatement PSdeleteWorkSite;
    private PreparedStatement PSfindWorkSitesOfClient;
    private PreparedStatement PSfindWorkSiteByCPR;
    private PreparedStatement PSdeleteWorkSiteByName;

    /**
     * Constructor of WorkSiteDB
     * @throws DataAccessException
     */
    public WorkSiteDB() {

    }

    /**
     * Initializes DB connection and prepares SQL statements
     *
     * @throws DataAccessException On statements that cannot be prepared
     */
    private void connectToDB() throws DataAccessException {
        DBConnection.connect();
        if (DBConnection.instanceIsNull()) {
            throw new DataAccessException("Couldn't connect and read from database; throwing to GUI", new Exception());
        }
    }

    /**
     * Queries database for all work sites.
     *
     * @param fullAssociation specifies whether to build results with the objects the foreign keys point to or not
     * @return if found - an ArrayList of all work sites in the database, otherwise an empty ArrayList
     * @throws DataAccessException
     */
    @Override
    public List<WorkSite> findAll(boolean fullAssociation) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindAll = con.prepareStatement(findAll);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        List<WorkSite> res;
        ResultSet rs;

        try {
            rs = PSfindAll.executeQuery();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with retrieving work sites from the database (executeQuery)", e);
        }
        res = buildObjects(rs, fullAssociation);
        return res;
    }

    /**
     * Queries database for all work sites of a client.
     *
     * @param cvr             CVR of client
     * @param fullAssociation specifies whether to build results with the objects the foreign keys point to or not
     * @return if found - an ArrayList of work sites a client has, otherwise an empty ArrayList
     * @throws DataAccessException
     */
    @Override
    public List<WorkSite> findWorkSitesOfClient(String cvr, boolean fullAssociation) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindWorkSitesOfClient = con.prepareStatement(findWorkSitesOfClient);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        List<WorkSite> res;
        ResultSet rs;
        try {
            PSfindWorkSitesOfClient.setString(1, cvr);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting query parameters when loading work types", e);
        }

        try {
            rs = PSfindWorkSitesOfClient.executeQuery();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with retrieving work sites from the database (executeQuery)", e);
        }
        res = buildObjects(rs, fullAssociation);
        return res;
    }

    /**
     * Returns WorkSite object on which a seasonal worker with a specific CPR number works on (inside the database).
     *
     * @param cpr CPR number of seasonal worker who works at the work site that is searched for
     * @param fullAssociation if True builds also work site's WorkTask and SeasonalWorker objects
     *                        and associates them with returned object
     * @return built WorkSite object if database query found a match, otherwise null
     * @throws DataAccessException
     */
    @Override
    public WorkSite findByWorkerCPR(String cpr, boolean fullAssociation) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindWorkSiteByCPR = con.prepareStatement(findWorkSiteByCPR);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSfindWorkSiteByCPR.setString(1, cpr);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when loading work site.", e);
        }

        ResultSet rs;
        try {
            rs = PSfindWorkSiteByCPR.executeQuery();
            if (rs.next()) {
                WorkSite res = buildObject(rs, fullAssociation);
                if (!DBConnection.instanceIsNull()) {
                    DBConnection.disconnect();
                }
                return res;
            } else {
                DBConnection.disconnect();
                return null;
            }
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Error with fetching a specific SeasonalWorker from DB.", e);
        }
    }

    /**
     * Returns WorkSite object with a specific ID stored in the database.
     *
     * @param workSiteID ID of work site to be searched for
     * @param fullAssociation if True builds also work site's WorkTask and SeasonalWorker objects
     *                        and associates them with returned object
     * @return built WorkSite object if database query found a match, otherwise null
     * @throws DataAccessException
     */
    @Override
    public WorkSite findByID(int workSiteID, boolean fullAssociation) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindByID = con.prepareStatement(findByID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSfindByID.setInt(1, workSiteID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("WorkSiteDB, findByID prepare error.", e);
        }


        ResultSet rs;
        try {
            rs = PSfindByID.executeQuery();
            if (rs.next()) {
                WorkSite res = buildObject(rs, fullAssociation);
                DBConnection.disconnect();
                return res;
            } else {
                DBConnection.disconnect();
                return null;
            }
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("WorkSiteDB, findByID execute error.", e);
        }
    }

    /**
     * Returns WorkSite object with a specific name stored in the database.
     *
     * @param siteName name of work site to be searched for
     * @param fullAssociation if True builds also work site's WorkTask and SeasonalWorker objects
     *                        and associates them with returned object
     * @return built WorkSite object if database query found a match, otherwise null
     * @throws DataAccessException
     */
    @Override
    public WorkSite findByName(String siteName, boolean fullAssociation) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindByName = con.prepareStatement(findByName);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSfindByName.setString(1, siteName);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("WorkSiteDB, findByID prepare error.", e);
        }

        ResultSet rs;
        try {
            rs = PSfindByName.executeQuery();
            if (rs.next()) {
                WorkSite res = buildObject(rs, fullAssociation);
                DBConnection.disconnect();
                return res;
            } else {
                return null;
            }
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("WorkSiteDB, findByID execute error.", e);
        }
    }

    /**
     * Inserts work site into database and associates it with client.
     *
     * @param cvr         CVR of client
     * @param newWorkSite WorkSite object to be added to database
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int insertWorkSite(String cvr, WorkSite newWorkSite) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSinsertWorkSite = con.prepareStatement(insertWorkSite);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        int affectedRows;
        try {
            PSinsertWorkSite.setString(1, newWorkSite.getName());
            PSinsertWorkSite.setString(2, newWorkSite.getDescription());
            PSinsertWorkSite.setString(3, newWorkSite.getStreetName());
            PSinsertWorkSite.setString(4, newWorkSite.getStreetNum());
            PSinsertWorkSite.setString(5, newWorkSite.getZip());
            PSinsertWorkSite.setString(6, newWorkSite.getTypeOfJob());
            PSinsertWorkSite.setDouble(7, newWorkSite.getPricePerWorker());
            PSinsertWorkSite.setString(8, cvr);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when adding new work site", e);
        }

        try {
            affectedRows = PSinsertWorkSite.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with inserting work site to database", e);
        }
        return affectedRows;
    }

    /**
     * Updates record of work site with a specific ID number stored in database.
     *
     * @param workSiteID ID number of the work site that is going to be updated
     * @param newWorkSite a new instance of WorkSite to be updated into database where the old record was
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int updateWorkSite(int workSiteID, WorkSite newWorkSite) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSupdateWorkSite = con.prepareStatement(updateWorkSite);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        int affectedRows;
        try {
            PSupdateWorkSite.setString(1, newWorkSite.getName());
            PSupdateWorkSite.setString(2, newWorkSite.getDescription());
            PSupdateWorkSite.setString(3, newWorkSite.getStreetName());
            PSupdateWorkSite.setString(4, newWorkSite.getStreetNum());
            PSupdateWorkSite.setString(5, newWorkSite.getZip());
            PSupdateWorkSite.setString(6, newWorkSite.getTypeOfJob());
            PSupdateWorkSite.setDouble(7, newWorkSite.getPricePerWorker());
            PSupdateWorkSite.setInt(8, workSiteID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when updating new work site", e);
        }

        try {
            affectedRows = PSinsertWorkSite.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with updating work site to database", e);
        }
        return affectedRows;
    }

    @Override
    public int updateWorkSiteByName(String workSiteName, WorkSite newWorkSite) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSupdateWorkSiteByName = con.prepareStatement(updateWorkSiteByName);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        int affectedRows;
        try {
            PSupdateWorkSiteByName.setString(1, newWorkSite.getName());
            PSupdateWorkSiteByName.setString(2, newWorkSite.getDescription());
            PSupdateWorkSiteByName.setString(3, newWorkSite.getStreetName());
            PSupdateWorkSiteByName.setString(4, newWorkSite.getStreetNum());
            PSupdateWorkSiteByName.setString(5, newWorkSite.getZip());
            PSupdateWorkSiteByName.setString(6, newWorkSite.getTypeOfJob());
            PSupdateWorkSiteByName.setDouble(7, newWorkSite.getPricePerWorker());
            PSupdateWorkSiteByName.setString(8, workSiteName);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when updating work site by name", e);
        }

        try {
            affectedRows = PSupdateWorkSiteByName.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with updating work site by name", e);
        }
        return affectedRows;
    }

    /**
     * Removes work site of specific ID number from database.
     *
     * @param workSiteID ID number of work site that is going to be removed
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int deleteWorkSite(int workSiteID) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSdeleteWorkSite = con.prepareStatement(deleteWorkSite);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSdeleteWorkSite.setInt(1, workSiteID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when deleting a work site", e);
        }

        int affectedRows;
        try {
            affectedRows = PSdeleteWorkSite.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with deleting work site from database", e);
        }
        return affectedRows;
    }

    /**
     * Deletes a WorkSite from the database based on it's unique name field
     * @param workSiteName name field of WorkSite to be deleted
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int deleteWorkSiteByName(String workSiteName) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSdeleteWorkSiteByName = con.prepareStatement(deleteWorkSiteByName);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSdeleteWorkSiteByName.setString(1, workSiteName);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when deleting a work site by name", e);
        }

        int affectedRows;
        try {
            affectedRows = PSdeleteWorkSiteByName.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with deleting work site by name from database", e);
        }
        return affectedRows;
    }

    /**
     * Generates and returns WorkSite objects based on the ResultSet returned by the query
     *
     * @param rs              ResultSet object filled with results of a query
     * @param fullAssociation if True builds also work site's WorkTask and SeasonalWorker objects
     *                        and associates them with returned object
     * @return list of  of built objects
     * @throws DataAccessException
     */
    private List<WorkSite> buildObjects(ResultSet rs, boolean fullAssociation) throws DataAccessException {
        List<WorkSite> res = new ArrayList<>();
        try {
            while (rs.next()) {
                WorkSite currentWorkSite = buildObject(rs, fullAssociation);
                res.add(currentWorkSite);
            }
            if (!DBConnection.instanceIsNull()) {
                DBConnection.disconnect();
            }
            return res;
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with loading work sites from database (in buildObjects", e);
        }
    }

    /**
     * Builds a single WorkSite object
     *
     * @param rs              ResultSet object filled with results of a query
     * @param fullAssociation if True builds also work site's WorkTask and SeasonalWorker objects
     *                        and associates them with returned object
     * @return new WorkSite object
     * @throws DataAccessException
     */
    private WorkSite buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
        WorkSite currentWorkSite;
        try {
            currentWorkSite = new WorkSite();
            currentWorkSite.setWorkSiteID(rs.getInt("workSiteID"));
            currentWorkSite.setName(rs.getString("siteName"));
            currentWorkSite.setDescription(rs.getString("siteDescription"));
            currentWorkSite.setStreetName(rs.getString("streetName"));
            currentWorkSite.setStreetNum(rs.getString("streetNum"));
            currentWorkSite.setZip(rs.getString("zip"));
            currentWorkSite.setCountryCode(rs.getString("countryCode"));
            currentWorkSite.setCountry(rs.getString("country"));
            currentWorkSite.setTypeOfJob(rs.getString("typeOfJob"));
            currentWorkSite.setPricePerWorker(rs.getDouble("pricePerWorker"));
//                currentWorkSite.setClientCvr(rs.getString("cvr"));
            if (fullAssociation) {
                WorkTypeDB wtDB = new WorkTypeDB();
                ArrayList<WorkType> workTypes = new ArrayList<>(wtDB.findAllWorkTypesOfWorkSite(
                        rs.getInt("workSiteID")));
                if (!workTypes.isEmpty()) {
                    currentWorkSite.setWorkTypes(workTypes);
                } else {
                    currentWorkSite.setWorkTypes(new ArrayList<>());
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Issue with loading work site from the database (in buildObject", e);
        }
        return currentWorkSite;
    }
}
