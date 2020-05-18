package DB;

import Controller.DataAccessException;
import Model.Client;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDB implements ClientDBIF {

    /**
     * Pre-made queries for the program
     */
    private static final String findAll = "SELECT * FROM Client";
    private static final String findClientByCVR = "SELECT * FROM Client WHERE cvr = ?";
    private static final String insertClient = "INSERT INTO Client VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String updateClient = "UPDATE Client SET "
            + "cvr = ?,"
            + "clientName = ?,"
            + "email = ?,"
            + "phoneNum = ?,"
            + "streetName = ?,"
            + "streetNum = ?,"
            + "zip = ?,"
            + "countryCode = ?,"
            + "Country = ?,"
            + "dateStart = ?,"
            + "dateEnd = ?"
            + " WHERE cvr = ?";
    private static final String deleteClientByCVR = "DELETE FROM Client WHERE cvr = ?";

    /**
     * Prepared statement declaration for the above queries
     */
    private PreparedStatement PSfindAll;
    private PreparedStatement PSfindClientByCVR;
    private PreparedStatement PSinsertClient;
    private PreparedStatement PSupdateClient;
    private PreparedStatement PSdeleteClientByCVR;


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
            PSfindClientByCVR = con.prepareStatement(findClientByCVR);
            PSinsertClient = con.prepareStatement(insertClient);
            PSupdateClient = con.prepareStatement(updateClient);
            PSdeleteClientByCVR = con.prepareStatement(deleteClientByCVR);
        } catch (SQLException e) {
            throw new DataAccessException("ClientDB could not initialize.", e);
        }
    }



    @Override
    public List<Client> findAll(boolean fullAssociation, Type type) throws DataAccessException {
        ResultSet rs;
        try {
            rs = this.PSfindAll.executeQuery();
        } catch (SQLException e) {
            throw new DataAccessException("Error with fetching all Clients from DB.", e);
        }
        return buildObjects(rs,fullAssociation,type);
    }

    @Override
    public Client findClientByCVR(String clientCVR, boolean fullAssociation, Type type) throws DataAccessException {
        ResultSet rs;
        try {
            PSfindClientByCVR.setString(1, clientCVR);
            rs = PSfindClientByCVR.executeQuery();
        } catch (SQLException e) {
            throw new DataAccessException("There was an error finding the Client by their CVR number.", e);
        }
        return buildObject(rs, fullAssociation, type);

    }

    @Override
    public int insertClient(Client newClient, Type type) throws DataAccessException {
        Integer affectedRows;
        try {
            // cvr, name, email, phoneNum, streetName, streetNum, zip, countryCode, country, dateStart, dateEnd
            PSinsertClient.setString(1, newClient.getCvr());
            PSinsertClient.setString(2, newClient.getName());
            PSinsertClient.setString(3, newClient.getEmail());
            PSinsertClient.setString(4, newClient.getPhoneNum());
            PSinsertClient.setString(5, newClient.getStreetName());
            PSinsertClient.setString(6, newClient.getStreetNum());
            PSinsertClient.setString(7, newClient.getZip());
            PSinsertClient.setString(8, newClient.getCountryCode());
            PSinsertClient.setString(9, newClient.getCountry());
            PSinsertClient.setDate(10, newClient.getDateStart());
            PSinsertClient.setDate(11, newClient.getDateEnd());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("There was a problem with the client being inserted into DB.",e);
        }
        try {
            affectedRows = PSinsertClient.executeUpdate();
            PSinsertClient.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("There was a problem with inserting client into DB.", e);
        }
        return affectedRows;
    }

    @Override
    public int updateClient(String clientCVR, Client newClient, Type type) throws DataAccessException {
        Integer affectedRows;
        try {
            PSupdateClient.setString(1, newClient.getCvr());
            PSupdateClient.setString(2, newClient.getName());
            PSupdateClient.setString(3, newClient.getEmail());
            PSupdateClient.setString(4, newClient.getPhoneNum());
            PSupdateClient.setString(5, newClient.getStreetName());
            PSupdateClient.setString(6, newClient.getStreetNum());
            PSupdateClient.setString(7, newClient.getZip());
            PSupdateClient.setString(8, newClient.getCountryCode());
            PSupdateClient.setString(9, newClient.getCountry());
            PSupdateClient.setDate(10, newClient.getDateStart());
            PSupdateClient.setDate(11, newClient.getDateEnd());
            PSupdateClient.setString(12, clientCVR);
        } catch (SQLException e){
            e.printStackTrace();
            throw new DataAccessException("There was an error updating the Client (newData).", e);
        }
        try {
            affectedRows = PSupdateClient.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw new DataAccessException("There was an error updating the Client (applyingData).", e);
        }
        return affectedRows;
    }

    @Override
    public int deleteClient(String clientCVR, Type type) throws DataAccessException {
        Integer affectedRows;
        try {
            PSdeleteClientByCVR.setString(1, clientCVR);
            affectedRows = PSdeleteClientByCVR.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("There was an error deleting the Client.", e);
        }
        return affectedRows;
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
            throw new DataAccessException("buildObjects: There was an Error with building the List.", e);
        }
        return res;
    }


    private Client buildObject(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException {
        Client currentClient = null;

        try {
            if (type.equals(Client.class)) {
                currentClient = new Client();
                currentClient.setCvr(rs.getString("cvr"));
                currentClient.setName(rs.getString("clientName"));
                currentClient.setEmail(rs.getString("email"));
                currentClient.setPhoneNum(rs.getString("phoneNum"));
                currentClient.setStreetName(rs.getString("streetName"));
                currentClient.setStreetNum(rs.getString("streetNum"));
                currentClient.setStreetName(rs.getString("streetName"));
                currentClient.setZip(rs.getString("zip"));
                currentClient.setCountryCode(rs.getString("countryCode"));
                currentClient.setCountry(rs.getString("Country"));
                currentClient.setDateStart(rs.getDate("dateStart"));
                currentClient.setDateEnd(rs.getDate("dateEnd"));

                if (fullAssociation) {
                    //todo
                }
            } else {
                throw new DataAccessException("Could not determine type.", new Exception());
            }
        } catch (SQLException e) {
            e.printStackTrace();
           throw new DataAccessException("buildObject: There was an Error with the Client.", e);
        }

        return currentClient;
    }
}
