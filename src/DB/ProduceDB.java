package DB;

import Controller.DataAccessException;
import Model.Produce;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduceDB implements ProduceDBIF{

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

    public ProduceDB() throws DataAccessException {
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
            PSfindProduce = con.prepareStatement(findProduce);
            PSfindWorkSiteProduce = con.prepareStatement(findWorkSiteProduce);
            PSinsertProduce = con.prepareStatement(insertProduce);
            PSupdateProduce = con.prepareStatement(updateProduce);
            PSdeleteProduce = con.prepareStatement(deleteProduce);
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("ProduceDB could not initialize.", e);
        }
    }

    @Override
    public List<Produce> findAll(boolean fullAssociation, Type type) throws DataAccessException {
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
            throw new DataAccessException("Error with fetching all Produce from DB.", e);
        }

    }

    @Override
    public Produce findProduce(String produceName, boolean fullAssociation, Type type) throws DataAccessException {
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
            rs.next();
            Produce res = buildObject(rs, fullAssociation, type);
            DBConnection.disconnect();
            return res;
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("Issue retrieving produce info from database.", e);
        }
    }

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
        return buildObjects(rs, false, type);

    }

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

        Integer affectedRows;
        try {
            affectedRows = PSinsertProduce.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was a problem with inserting produce into DB.", e);
        }
        return affectedRows;
    }

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

        Integer affectedRows;
        try {
            affectedRows = PSupdateProduce.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was an error updating the Produce (applyingData).", e);
        }
        return affectedRows;
    }

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

        Integer affectedRows;
        try {
            affectedRows = PSdeleteProduce.executeUpdate();
            DBConnection.disconnect();
        } catch (SQLException e) {
            DBConnection.disconnect();
            throw new DataAccessException("There was an error deleting the Produce.", e);
        }
        return affectedRows;
    }

    private List<Produce> buildObjects(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException {
        List<Produce> res = new ArrayList<>();
        try {
            while(rs.next()) {
                Produce currentProduce = buildObject(rs,fullAssociation,type);
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


    private Produce buildObject(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException {
        Produce currentProduce = null;

        try {
            if (type.equals(Produce.class)) {
                currentProduce = new Produce();
                currentProduce.setProduceName(rs.getString("produceName"));

                if (fullAssociation) {

                }
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
