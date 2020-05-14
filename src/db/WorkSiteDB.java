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
    private static final String findAll = "SELECT * FROM WorkSite WHERE cvr = ?";
    private static final String insertWorkSite = "INSERT INTO WorkSite VALUES (?,?,?,?,?,?,?,?,?,?)";

    /**
     * PreparedStatement declarations for the above queries
     */

    private PreparedStatement PSfindAll;
    private PreparedStatement PSinsertWorkSite;

    public WorkSiteDB() throws DataAccessException {
        init();
    }

    private void init() throws DataAccessException {
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindAll = con.prepareStatement(findAll);
            PSinsertWorkSite = con.prepareStatement(insertWorkSite);
        } catch (SQLException e) {
            throw new DataAccessException("Issue with preparing database statements", e);
        }
    }

    /**
     * Queries database for all work sites of a client
     *
     * @param cvr CVR of client
     * @param fullAssociation specifies whether to build results with the objects the foreign keys point to or not
     * @param type type of object in the code the query looks for in the database
     * @return if found - an ArrayList of work sites a client has, otherwise an empty ArrayList
     * @throws DataAccessException
     */
    @Override
    public List<WorkSite> findAll(String cvr, boolean fullAssociation, Type type) throws DataAccessException {
        List<WorkSite> res;
        ResultSet rs;
        try {
            PSfindAll.setString(1, cvr);
        } catch (SQLException e) {
            throw new DataAccessException("Issue with setting query parameters when loading work types", e);
        }

        try {
            rs = PSfindAll.executeQuery();
        } catch (SQLException e) {
            throw new DataAccessException("Issue with retrieving work sites from the database (executeQuery", e);
        }
        res = buildObjects(rs, fullAssociation, type);
        return res;
    }

    /**
     * Inserts work site into database and associates it with client
     *
     * @param cvr CVR of client
     * @param newWorkSite WorkSite object to be added to database
     * @return true if insertion was successful, otherwise - false
     * @throws DataAccessException
     */
    @Override
    public Boolean insertWorkSite(String cvr, WorkSite newWorkSite) throws DataAccessException {
        Boolean res;
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
            Integer affectedRows = PSinsertWorkSite.executeUpdate();
            res = affectedRows > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Issue with inserting work type to database", e);
      }

        return res;
    }

    /**
     * Generates and returns WorkSite objects based on the ResultSet returned by the query
     *
     * @param rs ResultSet object filled with results of a query
     * @param fullAssociation specifies whether to build results with the objects the foreign keys point to or not
     * @param type type of the object that is going to be built
     * @return ArrayList of built objects
     * @throws DataAccessException
     */
    private List<WorkSite> buildObjects(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException {
        List<WorkSite> res = new ArrayList<>();
        try {
            while (rs.next()) {
                WorkSite currentWorkSite = buildObject(rs, fullAssociation, type);
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
     * @param type type of the object that is going to be built
     * @return new WorkSite object
     * @throws DataAccessException
     */
    private WorkSite buildObject(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException {
        WorkSite currentWorkSite;
        try {
            if (type.equals(WorkSite.class)) {
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
                currentWorkSite.setClientCvr(rs.getString("cvr"));
            } else {
                throw new DataAccessException("Issue: could not determine type.", new Exception());
            }
        } catch (SQLException e) {
            throw new DataAccessException("Issue with loading work site from the database (in buildObject", e);
        }
        return  currentWorkSite;
    }
}
