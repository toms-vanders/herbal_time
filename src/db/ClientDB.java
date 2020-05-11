package db;

import controller.DataAccessException;
import model.Client;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDB  implements ClientDBIF{
    /**
     * notes ->
     * Started writing this class based on ws_persistence -mikulas
     */

    /**
     * Pre-made queries for the program
     */
    private static final String findAll = "SELECT * FROM Client";

    /**
     * Prepared statement declaration for the above queries
     */
    private PreparedStatement PSfindAll;

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
        } catch (SQLException e) {
            throw new DataAccessException("ClientDB error.", e);
        }
    }



    @Override
    public List<Client> findAll(boolean fullAssociation, Type type) throws DataAccessException {
        ResultSet rs = null;
        try {
            rs = this.PSfindAll.executeQuery();
            PSfindAll.close();
            this.PSfindAll.close();
        } catch (SQLException e) {
            throw new DataAccessException("resultset error", e);
        }
        System.out.println(rs);
        List<Client> res = buildObjects(rs,fullAssociation,type);
        return res;
    }

    private List<Client> buildObjects(ResultSet rs, boolean fullAssociation, Type type) throws DataAccessException {
        List<Client> res = new ArrayList<>();
        try {
            while(rs.next()) {
                Client currentClient = buildObject(rs,fullAssociation,type);
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
                ((Client) currentClient).setZip(Integer.parseInt(rs.getString("zip")));
                ((Client) currentClient).setCountryCode(rs.getString("countryCode"));
                ((Client) currentClient).setCountry(rs.getString("Country"));
                // for the dates, find a way to make string -> LocalDate
//                ((Client) currentClient).setDateStart(rs.getString("dateStart"));
//                ((Client) currentClient).setDateEnd(rs.getString("dateEnd"));

            } else {
                throw new DataAccessException("Could not determine type.", new Exception());
            }
        } catch (SQLException e) {
            throw new DataAccessException("ClientDB error.", e);
        }

        return currentClient;
    }
}
