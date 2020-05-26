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


    public WorkSiteProduceDB() throws DataAccessException {
        init();
    }

    private void connectToDB() throws DataAccessException{
        DBConnection.connect();
        if (DBConnection.instanceIsNull()) {
            throw new DataAccessException("Couldn't connect and read from database; throwing to GUI", new Exception());
        }
    }

    /**
     * Initialize DB connection and prepare SQL statements
     *
     * @throws DataAccessException Throw an exception on statements that cannot be prepared
     */
    private void init() throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindAll = con.prepareStatement(findAll);
            PSfindByProduce = con.prepareStatement(findByProduce);
            PSfindByWorkSite = con.prepareStatement(findByWorkSite);
            PSinsertWorkSiteProduce = con.prepareStatement(insertWorkSiteProduce);
            PSupdateWorkSiteProduce = con.prepareStatement(updateWorkSiteProduce);
            PSdeleteWorkSiteProduce = con.prepareStatement(deleteWorkSiteProduce);
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("WorkSiteProduce could not initialize.", e);
        }
    }

    @Override
    public List<WorkSiteProduce> findAll(boolean fullAssociation, Type type) throws DataAccessException {
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
            return buildObjects(rs,fullAssociation,type);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Error with fetching all WorkSiteProduce from DB.", e);
        }

    }

    @Override
    public List<WorkSiteProduce> findByProduce(String produceName, boolean fullAssociation, Type type) throws DataAccessException {
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
        return buildObjects(rs, fullAssociation, type);
    }

    @Override
    public List<WorkSiteProduce> findByWorkSite(int workSiteID, boolean fullAssociation, Type type) throws DataAccessException {
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
        return buildObjects(rs, fullAssociation, type);
    }

    @Override
    public Integer insertWorkSiteProduce(int workSiteID, String produceName, WorkSiteProduce newWorkSiteProduce, Type type) throws DataAccessException {
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

    private List<WorkSiteProduce> buildObjects(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException {
        List<WorkSiteProduce> res = new ArrayList<>();
        try {
            while(rs.next()) {
                WorkSiteProduce currentWorkSiteProduce = buildObject(rs,fullAssociation,type);
//                System.out.println(currentClient.getCountry());
//                System.out.println(currentClient.toString());
                res.add(currentWorkSiteProduce);
            }
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("buildObjects: There was an Error with building the List.", e);
        }
        return res;
    }


    private WorkSiteProduce buildObject(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException {
        WorkSiteProduce currentWorkSiteProduce = null;

        try {
            if (type.equals(WorkSiteProduce.class)) {
                currentWorkSiteProduce = new WorkSiteProduce();
                currentWorkSiteProduce.setWorkSiteID(rs.getInt("workSiteID"));
                currentWorkSiteProduce.setProduceName(rs.getString("produceName"));

                if (fullAssociation) {

                }
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
