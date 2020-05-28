package DB;

import Model.Produce;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to access data from the Produce table in the database.
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
public class ProduceDB implements ProduceDBIF {

    /**
     * Pre-made queries for the program
     */
    private static final String findAll = "SELECT * FROM Produce";
    private static final String findProduce = "SELECT * FROM Produce WHERE produceName = ?";
    private static final String findWorkSiteProduce = "SELECT Produce.produceName,workSiteID FROM Produce LEFT JOIN " +
            "WorkSiteProduce WSP on Produce.produceName = WSP.produceName where workSiteID = ?";
    private static final String insertProduce = "INSERT INTO Produce VALUES(?)";
    private static final String updateProduce = "UPDATE Produce SET "
            + "produceName = ?"
            + " WHERE produceName = ?";
    private static final String deleteProduce = "DELETE FROM Produce WHERE produceName = ?";

    /**
     * Prepared statement declaration for the above queries
     */
    private PreparedStatement PSfindAll;
    private PreparedStatement PSfindProduce;
    private PreparedStatement PSfindWorkSiteProduce;
    private PreparedStatement PSinsertProduce;
    private PreparedStatement PSupdateProduce;
    private PreparedStatement PSdeleteProduce;

    /**
     * Constructor for ProduceDB
     * @throws DataAccessException
     */
    public ProduceDB() {

    }

    /**
     * Initializes DB connection and prepares SQL statements.
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
     * Returns list of all produces stored in the database.
     *
     * @param type requires to be the proper type.
     * @return list of all produces stored in the database if found, otherwise empty list
     * @throws DataAccessException
     */
    @Override
    public List<Produce> findAll(Type type) throws DataAccessException {
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
            throw new DataAccessException("Error with fetching all Produce from DB.", e);
        }

    }

    /**
     * Returns Produce object with a specific name stored in the database.
     *
     * @param produceName name of produce to be searched for
     * @param type requires to be a proper type
     * @return built Produce object if database query found a match, otherwise null
     * @throws DataAccessException
     */
    @Override
    public Produce findProduce(String produceName,Type type) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindProduce = con.prepareStatement(findProduce);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSfindProduce.setString(1, produceName);
        } catch (SQLException e) {
            throw new DataAccessException("Issue with setting up query parameters when loading produce.", e);
        }

        ResultSet rs;
        try {
            rs = PSfindProduce.executeQuery();
            if (rs.next()) {
                Produce res = buildObject(rs, type);
                DBConnection.disconnect();
                return res;
            } else {
                DBConnection.disconnect();
                return null;
            }
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue retrieving produce info from database.", e);
        }
    }

    /**
     * Returns list of produces collected on worksite with a specific ID.
     *
     * @param workSiteID ID of worksite to look for produces in the database
     * @param type requires to be of proper type
     * @return list of found Produce objects if query found matches, otherwise empty list
     * @throws DataAccessException
     */
    @Override
    public List<Produce> findWorkSiteProduce(int workSiteID, Type type) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindWorkSiteProduce = con.prepareStatement(findWorkSiteProduce);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSfindWorkSiteProduce.setInt(1, workSiteID);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting query parameters when loading WorkSiteProduce", e);
        }

        ResultSet rs;
        try {
            rs = PSfindWorkSiteProduce.executeQuery();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with retrieving WorkSiteProduce from the database (executeQuery)", e);
        }
        return buildObjects(rs, type);
    }

    /**
     * Inserts produce into database.
     *
     * @param newProduce instance of new Employee to be inserted into database
     * @param type requires to be of proper type
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int insertProduce(Produce newProduce, Type type) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSinsertProduce = con.prepareStatement(insertProduce);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            // produceName
            PSinsertProduce.setString(1, newProduce.getProduceName());
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with the produce being inserted into DB.",e);
        }

        int affectedRows;
        try {
            affectedRows = PSinsertProduce.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with inserting produce into DB.", e);
        }
        return affectedRows;
    }

    /**
     * Updates record of produce with a specific name stored in database.
     *
     * @param produceName name of the produce that is going to be updated
     * @param newProduce a new instance of Produce to be updated into database where the old record was
     * @param type requires to be of proper type
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int updateProduce(String produceName, Produce newProduce, Type type) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSupdateProduce = con.prepareStatement(updateProduce);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSupdateProduce.setString(1, newProduce.getProduceName());
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was an error updating the WorkSiteProduce (newData).", e);
        }

        int affectedRows;
        try {
            affectedRows = PSupdateProduce.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was an error updating the Produce (applyingData).", e);
        }
        return affectedRows;
    }

    /**
     * Removes produce of specific name from database.
     *
     * @param produceName name of produce that is going to be removed
     * @param type requires to be of proper type
     * @return count of affected rows in database after executing operation
     * @throws DataAccessException
     */
    @Override
    public int deleteProduce(String produceName, Type type) throws DataAccessException {
        connectToDB();
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSdeleteProduce = con.prepareStatement(deleteProduce);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue preparing statement", e);
        }

        try {
            PSdeleteProduce.setString(1, produceName);
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue with setting update parameters when deleting produce", e);
        }

        int affectedRows;
        try {
            affectedRows = PSdeleteProduce.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was an error deleting the Produce.", e);
        }
        return affectedRows;
    }

    /**
     * Returns list of Produce objects after finding matching cases in database.
     *
     * @param rs ResultSet object returned after executing query
     * @param type requires to be of proper type
     * @return list of Produce objects
     * @throws DataAccessException
     */
    private List<Produce> buildObjects(ResultSet rs, Type type) throws DataAccessException {
        List<Produce> res = new ArrayList<>();
        try {
            while(rs.next()) {
                Produce currentProduce = buildObject(rs, type);
//                System.out.println(currentClient.getCountry());
//                System.out.println(currentClient.toString());
                res.add(currentProduce);
            }
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("buildObjects: There was an Error with building the List.", e);
        }
        return res;
    }

    /**
     * Gets data from the DB and builds a Produce object.
     *
     * @param rs The ResultSet from which a Produce object is to be assembled
     * @param type requires to be of proper type
     * @return an assembled Employee object
     * @throws DataAccessException
     */
    private Produce buildObject(ResultSet rs, Type type) throws DataAccessException {
        Produce currentProduce = null;

        try {
            if (type.equals(Produce.class)) {
                currentProduce = new Produce();
                currentProduce.setProduceName(rs.getString("produceName"));
            } else {
                throw new DataAccessException("Could not determine type.", new Exception());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("buildObject: There was an Error with the produce.", e);
        }

        return currentProduce;
    }
}
