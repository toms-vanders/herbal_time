package DB;

import Controller.DataAccessException;
import Model.WorkSite;

import javax.xml.crypto.Data;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkSiteDB implements WorkSiteDBIF {
    /**
     * Query skeletons for the program
     */
    private static final String findAll = "SELECT * FROM WorkSite";
    private static final String findByID = "SELECT * FROM WorkSite WHERE workSiteID = ?";
    private static final String findWorkSitesOfClient = "SELECT * FROM WorkSite WHERE cvr = ?";
    private static final String insertWorkSite = "INSERT INTO WorkSite VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String updateWorkSite = "UPDATE WorkSite SET "
            + "siteName = ?,"
            + "siteDescription = ?,"
            + "streetName = ?,"
            + "streetNum = ?,"
            + "zip = ?,"
            + "countryCode = ?,"
            + "country = ?,"
            + "typeOfJob = ?,"
            + "pricePerWorker = ?,"
//            + "cvr = ? "
            + "WHERE workSiteID = ?";
    private static final String deleteWorkSite = "DELETE FROM Worksite WHERE workSiteID = ?";
    /**
     * PreparedStatement declarations for the above queries
     */

    private PreparedStatement PSfindAll;
    private PreparedStatement PSfindByID;
    private PreparedStatement PSinsertWorkSite;
    private PreparedStatement PSupdateWorkSite;
    private PreparedStatement PSdeleteWorkSite;
    private PreparedStatement PSfindWorkSitesOfClient;

    public WorkSiteDB() throws DataAccessException {
        init();
    }

    // TODO
    // This should be made obsolete ASAP
    // In order to do that, find methods where preparing statements wasn't yet moved into corresponding bodies,
    // and move it there
    private void init() throws DataAccessException {
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindAll = con.prepareStatement(findAll);
            PSfindByID = con.prepareStatement(findByID);
            PSinsertWorkSite = con.prepareStatement(insertWorkSite);
            PSupdateWorkSite = con.prepareStatement(updateWorkSite);
            PSdeleteWorkSite = con.prepareStatement(deleteWorkSite);
            PSfindWorkSitesOfClient = con.prepareStatement(findWorkSitesOfClient);
        } catch (SQLException e) {
            throw new DataAccessException("Issue with preparing database statements", e);
        }
    }


    /**
     * Queries database for all work sites
     *
     * @param fullAssociation specifies whether to build results with the objects the foreign keys point to or not
     * @return if found - an ArrayList of all work sites in the database, otherwise an empty ArrayList
     * @throws DataAccessException
     */
    @Override
    public List<WorkSite> findAll(boolean fullAssociation) throws DataAccessException {
        List<WorkSite> res;
        ResultSet rs;

        try {
            rs = PSfindAll.executeQuery();
        } catch (SQLException e) {
            throw new DataAccessException("Issue with retrieving work sites from the database (executeQuery)", e);
        }
        res = buildObjects(rs, fullAssociation);
        return res;
    }

    /**
     * Queries database for all work sites of a client
     *
     * @param cvr CVR of client
     * @param fullAssociation specifies whether to build results with the objects the foreign keys point to or not
     * @return if found - an ArrayList of work sites a client has, otherwise an empty ArrayList
     * @throws DataAccessException
     */
    @Override
    public List<WorkSite> findWorkSitesOfClient(String cvr, boolean fullAssociation) throws DataAccessException {
        List<WorkSite> res;
        ResultSet rs;
        try {
            PSfindWorkSitesOfClient.setString(1, cvr);
        } catch (SQLException e) {
            throw new DataAccessException("Issue with setting query parameters when loading work types", e);
        }

        try {
            rs = PSfindWorkSitesOfClient.executeQuery();
        } catch (SQLException e) {
            throw new DataAccessException("Issue with retrieving work sites from the database (executeQuery)", e);
        }
        res = buildObjects(rs, fullAssociation);
        return res;
    }

    @Override
    public WorkSite findByID(int workSiteID, boolean fullAssociation) throws DataAccessException {
        WorkSite res;
        ResultSet rs;
        try {
            PSfindByID.setInt(1, workSiteID);
        } catch (SQLException e) {
            throw new DataAccessException("WorkSiteDB, findByID prepare error.", e);
        }
        try {
            rs = PSfindByID.executeQuery();
        } catch (SQLException e) {
            throw new DataAccessException("WorkSiteDB, findByID execute error.", e);
        }
        res = buildObject(rs, fullAssociation);
        return res;
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
    public Integer insertWorkSite(String cvr, WorkSite newWorkSite) throws DataAccessException {
        Integer affectedRows;
        try {
            PSinsertWorkSite.setString(1, newWorkSite.getName());
            PSinsertWorkSite.setString(2, newWorkSite.getDescription());
            PSinsertWorkSite.setString(3, newWorkSite.getStreetName());
            PSinsertWorkSite.setString(4, newWorkSite.getStreetNum());
            PSinsertWorkSite.setString(5, newWorkSite.getZip());
            PSinsertWorkSite.setString(6, newWorkSite.getCountryCode());
            PSinsertWorkSite.setString(7, newWorkSite.getCountry());
            PSinsertWorkSite.setString(8, newWorkSite.getTypeOfJob());
            PSinsertWorkSite.setDouble(9, newWorkSite.getPricePerWorker());
            PSinsertWorkSite.setString(10, cvr);
        } catch (SQLException e) {
            throw new DataAccessException("Issue with setting up query parameters when adding new work site", e);
        }

        try {
            affectedRows = PSinsertWorkSite.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Issue with inserting work site to database", e);
        }
        return affectedRows;
    }

    @Override
    public Integer updateWorkSite(int workSiteID, WorkSite newWorkSite) throws DataAccessException {
        Integer affectedRows;
        try {
            PSupdateWorkSite.setString(1, newWorkSite.getName());
            PSupdateWorkSite.setString(2, newWorkSite.getDescription());
            PSupdateWorkSite.setString(3, newWorkSite.getStreetName());
            PSupdateWorkSite.setString(4, newWorkSite.getStreetNum());
            PSupdateWorkSite.setString(5, newWorkSite.getZip());
            PSupdateWorkSite.setString(6, newWorkSite.getCountryCode());
            PSupdateWorkSite.setString(7, newWorkSite.getCountry());
            PSupdateWorkSite.setString(8, newWorkSite.getTypeOfJob());
            PSupdateWorkSite.setDouble(9, newWorkSite.getPricePerWorker());
//            PSupdateWorkSite.setString(10, newWorkSite.()); // todo - no getCVR method?

        } catch (SQLException e) {
            throw new DataAccessException("Issue with setting up query parameters when adding new work site", e);
        }

        try {
            affectedRows = PSinsertWorkSite.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Issue with inserting work type to database", e);
        }
        return affectedRows;
    }

    @Override
    public Integer deleteWorkSite(int workSiteID) throws DataAccessException {
        Integer affectedRows;
        try {
            PSdeleteWorkSite.setInt(1, workSiteID);
        } catch (SQLException e) {
            throw new DataAccessException("Issue with setting up query parameters when deleting a work site", e);
        }

        try {
            affectedRows = PSdeleteWorkSite.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Issue with deleting work site from database", e);
        }
        return affectedRows;
    }

    /**
     * Generates and returns WorkSite objects based on the ResultSet returned by the query
     *
     * @param rs ResultSet object filled with results of a query
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
        } catch (SQLException e) {
            throw new DataAccessException("Issue with loading work sites from database (in buildObjects", e);
        }
        return res;
    }

    /**
     * Builds a single WorkSite object
     *
     * @param rs ResultSet object filled with results of a query
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

                }
        } catch (SQLException e) {
            throw new DataAccessException("Issue with loading work site from the database (in buildObject", e);
        }
        return  currentWorkSite;
    }
}
