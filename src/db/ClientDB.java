package DB;

import Controller.DataAccessException;
import Model.Client;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDB implements ClientDBIF {
    /**
     * notes ->
     * Started writing this class based on ws_persistence -mikulas
     */

    /**
     * Pre-made queries for the program
     */
    private static final String findAll = "SELECT * FROM Client";
    private static final String insertClient = "INSERT INTO Client VALUES(?,?,?,?,?,?,?,?,?,?,?)";

    /**
     * Prepared statement declaration for the above queries
     */
    private PreparedStatement PSfindAll;
    private PreparedStatement PSinsertClient;


    public ClientDB() throws DataAccessException{
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
            PSinsertClient = con.prepareStatement(insertClient);
        } catch (SQLException e) {
            throw new DataAccessException("ClientDB error.", e);
        }
    }



    @Override
    public List<Client> findAll(boolean fullAssociation, Type type) throws DataAccessException {
        ResultSet rs;
        try {
            rs = this.PSfindAll.executeQuery();
        } catch (SQLException e) {
            throw new DataAccessException("resultset error", e);
        }
        System.out.println(rs);
        List<Client> res = buildObjects(rs,fullAssociation,type);
        return res;
    }

    /**
     * @return true if inserted correctly, otherwise false
     */
    @Override
    public Boolean insertClient(Client newClient, Type type) throws DataAccessException {
        ResultSet rs = null;
        Integer generatedKey = null; //todo - return the row ID of client Inserted
        try {
            System.out.println("Attempting to insert a Client to the DB.");
            // cvr, name, email, phoneNum, streetName, streetNum, zip, countryCode, country, dateStart, dateEnd
            PSinsertClient.setString(1, ((Client) newClient).getCvr());
            PSinsertClient.setString(2, ((Client) newClient).getName());
            PSinsertClient.setString(3, ((Client) newClient).getEmail());
            PSinsertClient.setString(4, ((Client) newClient).getPhoneNum());
            PSinsertClient.setString(5, ((Client) newClient).getStreetName());
            PSinsertClient.setString(6, ((Client) newClient).getStreetNum());
            PSinsertClient.setString(7, ((Client) newClient).getZip());
            PSinsertClient.setString(8, ((Client) newClient).getCountryCode());
            PSinsertClient.setString(9, ((Client) newClient).getCountry());
            PSinsertClient.setDate(10, ((Client) newClient).getDateStart());
            PSinsertClient.setDate(11, ((Client) newClient).getDateEnd());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("There was a problem with the client being inserted into DB.",e);
        }
        try {
            System.out.println("1");
            System.out.println(PSinsertClient.toString());
            PSinsertClient.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("There was a problem with inserting client into DB.", e);
        }

        return null;
    }

    // clientCVR is the CVR of client to be changed
    @Override
    public Boolean updateClient(int clientCVR, Client newClient, Type type) throws DataAccessException {
        return null;
    }

    private List<Client> buildObjects(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException {
        List<Client> res = new ArrayList<>();
        try {
            while(rs.next()) {
                Client currentClient = buildObject(rs,fullAssociation,type);
                System.out.println(currentClient.getCountry());
                System.out.println(currentClient.toString());
                res.add(currentClient);
            }
        } catch (SQLException e) {
            throw new DataAccessException("buildObjects: problem with resultset", e);

        }
        return res;
    }


    private Client buildObject(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException, SQLException {
        Client currentClient = null;
        try {
            if (type.equals(Client.class)) {
                currentClient = new Client();
                ((Client) currentClient).setCvr(rs.getString("cvr"));
                ((Client) currentClient).setName(rs.getString("clientName"));
                ((Client) currentClient).setEmail(rs.getString("email"));
                ((Client) currentClient).setPhoneNum(rs.getString("phoneNum"));
                ((Client) currentClient).setStreetName(rs.getString("streetName"));
                ((Client) currentClient).setStreetNum(rs.getString("streetNum"));
                ((Client) currentClient).setStreetName(rs.getString("streetName"));
                ((Client) currentClient).setZip(rs.getString("zip"));
                ((Client) currentClient).setCountryCode(rs.getString("countryCode"));
                ((Client) currentClient).setCountry(rs.getString("Country"));
                ((Client) currentClient).setDateStart(rs.getDate("dateStart"));
                ((Client) currentClient).setDateEnd(rs.getDate("dateEnd"));
            } else {
                throw new DataAccessException("Could not determine type.", new Exception());
            }
        } catch (SQLException e) {
            throw new DataAccessException("buildObject: Error.", e);
        }

        return currentClient;
    }
}
