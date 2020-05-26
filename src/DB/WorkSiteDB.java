package DB;

import Controller.DataAccessException;
import Model.SeasonalWorker;
import Model.WorkSite;
import Model.WorkType;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.xml.crypto.Data;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
//            + "countryCode = ?,"
//            + "country = ?,"
            + "typeOfJob = ?,"
            + "pricePerWorker = ? "
//            + "cvr = ? "
            + "WHERE workSiteID = ?";
    private static final String deleteWorkSite = "DELETE FROM Worksite WHERE workSiteID = ?";
    private static final String findWorkSiteByCPR = "SELECT * FROM WorkSite " +
            "WHERE workSiteID = " +
            "(SELECT wSiteID FROM SeasonalWorker " +
            "WHERE SeasonalWorker.cpr = ?)";
    /**
     * PreparedStatement declarations for the above queries
     */

    private PreparedStatement PSfindAll;
    private PreparedStatement PSfindByID;
    private PreparedStatement PSfindByName;
    private PreparedStatement PSinsertWorkSite;
    private PreparedStatement PSupdateWorkSite;
    private PreparedStatement PSdeleteWorkSite;
    private PreparedStatement PSfindWorkSitesOfClient;
    private PreparedStatement PSfindWorkSiteByCPR;

    public WorkSiteDB() throws DataAccessException {
//        init();
    }

    private void connectToDB() throws DataAccessException {
        DBConnection.connect();
        if (DBConnection.instanceIsNull()) {
            throw new DataAccessException("Couldn't connect and read from database; throwing to GUI", new Exception());
        }
    }

//    // This should be made obsolete ASAP
//    // In order to do that, find methods where preparing statements wasn't yet moved into corresponding bodies,
//    // and move it there
//    private void init() throws DataAccessException {
//        connectToDB();
//        Connection con = DBConnection.getInstance().getConnection();
//        try {
//            PSfindAll = con.prepareStatement(findAll);
//            PSfindByID = con.prepareStatement(findByID);
//            PSfindByName = con.prepareStatement(findByName);
//            PSinsertWorkSite = con.prepareStatement(insertWorkSite);
//            PSupdateWorkSite = con.prepareStatement(updateWorkSite);
//            PSdeleteWorkSite = con.prepareStatement(deleteWorkSite);
//            PSfindWorkSitesOfClient = con.prepareStatement(findWorkSitesOfClient);
//            PSfindWorkSiteByCPR = con.prepareStatement(findWorkSiteByCPR);
//            DBConnection.disconnect();
//        } catch (SQLException e) {
//            DBConnection.disconnect();
//            throw new DataAccessException("Issue with preparing database statements", e);
//        }
//    }


    /**
     * Queries database for all work sites
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
     * Queries database for all work sites of a client
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
            rs.next();
            WorkSite res = buildObject(rs, fullAssociation);
            if (!DBConnection.instanceIsNull()) {
                DBConnection.disconnect();
            }
            return res;
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Error with fetching a specific SeasonalWorker from DB.", e);
        }
    }

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
            rs.next();
            WorkSite res = buildObject(rs, fullAssociation);
            DBConnection.disconnect();
            return res;
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("WorkSiteDB, findByID execute error.", e);
        }
    }

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
     * Inserts work site into database and associates it with client
     *
     * @param cvr         CVR of client
     * @param newWorkSite WorkSite object to be added to database
     * @return true if insertion was successful, otherwise - false
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
//            PSupdateWorkSite.setString(6, newWorkSite.getCountryCode());
//            PSupdateWorkSite.setString(7, newWorkSite.getCountry());
            PSupdateWorkSite.setString(6, newWorkSite.getTypeOfJob());
            PSupdateWorkSite.setDouble(7, newWorkSite.getPricePerWorker());
            PSupdateWorkSite.setInt(8, workSiteID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting up query parameters when adding new work site", e);
        }

        try {
            affectedRows = PSinsertWorkSite.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with inserting work type to database", e);
        }
        return affectedRows;
    }

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
     * Generates and returns WorkSite objects based on the ResultSet returned by the query
     *
     * @param rs              ResultSet object filled with results of a query
     * @param fullAssociation specifies whether to build results with the objects the foreign keys point to or not
     * @return ArrayList of built objects
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
     * @param fullAssociation specifies whether to build results with the objects the foreign keys point to or not
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
                List<WorkType> workTypes = new ArrayList<>(wtDB.findAllWorkTypesOfWorkSite(
                        rs.getInt("workSiteID")));
                if (!workTypes.isEmpty()) {
                    currentWorkSite.setWorkTypes((ArrayList<WorkType>) workTypes);
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
