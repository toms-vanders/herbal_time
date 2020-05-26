package Controller;

import DB.ClientDB;
import Model.Client;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

/**
 * Used to interface between the database and the GUI
 */
public class ClientCtr implements ClientCtrIF {

    ClientDB clientDB;

    public ClientCtr() throws DataAccessException {
        clientDB = new ClientDB();
    }

    /**
     * Returns a list of all Clients loaded in the database
     * @return a list of all Clients in the database
     * @throws DataAccessException
     */
    public List<Client> findAllClients() throws DataAccessException {
        try {
            return clientDB.findAll(false,Client.class);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("ClientCtr error.", e);
        }
    }

    /**
     * Returns a Client with the appropriate CVR number
     * @param cvr CVR of the Client to return
     * @param fullAssociation If true, returns also the Client's associated WorkSites
     * @param type Requires to be the proper type
     * @return Client object with the appropriate CVR number
     * @throws DataAccessException
     */
    @Override
    public Client findClientByCVR(String cvr, boolean fullAssociation, Type type) throws DataAccessException {
        try {
            return clientDB.findClientByCVR(cvr, fullAssociation, type);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("ClientCtr error.", e);
        }
    }

    /**
     * Inserts a new Client into the database
     * @param newClient The new Client object to be inserted
     * @return Number of rows affected
     * @throws DataAccessException
     */
    public boolean insertClient(Client newClient) throws DataAccessException {
        try {
            return (clientDB.insertClient(newClient, Client.class)==1);
        } catch(DataAccessException e) {
            throw new DataAccessException("ClientCtr error.", e);
        }
    }

    /**
     * Make changes to Clients already in the database
     * @param clientCVR CVR of the Client wished to be changed
     * @param newClient new Client object to replace the original one
     * @return Number of rows affected
     * @throws DataAccessException
     */
    public int updateClient(String clientCVR, Client newClient) throws DataAccessException {
        try {
            return clientDB.updateClient(clientCVR,newClient, Client.class);
        } catch(DataAccessException e) {
            throw new DataAccessException("ClientCtr error.", e);
        }
    }

    /**
     * Deletes a Client from the database
     * @param clientCVR CVR of the Client wished to be deleted
     * @return Number of rows affected
     * @throws DataAccessException
     */
    @Override
    public int deleteClient(String clientCVR) throws DataAccessException {
        try {
            return clientDB.deleteClient(clientCVR, Client.class);
        } catch (DataAccessException e) {
            throw new DataAccessException("ClientCtr error.", e);
        }
    }
}
