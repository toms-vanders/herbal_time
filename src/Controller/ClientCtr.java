package Controller;

import DB.ClientDB;
import DB.ClientDBIF;
import DB.DataAccessException;
import Model.Client;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Used to interface between the GUI layer and ClientDB
 *
 * @author Daniel Zoltan Ban
 * @author Mikuláš Dobrodej
 * @author Adrian Mihai Dohot
 * @author Damian Hrabąszcz
 * @author Toms Vanders
 * @version 1.0 (29.05.2020)
 *
 * Date: 29.05.2020
 */
public class ClientCtr implements ClientCtrIF {

    final ClientDBIF clientDB;

    public ClientCtr() {
        clientDB = new ClientDB();
    }

    /**
     * Returns a list of all Clients loaded in the database
     * @param fullAssociation if true, returns also the Client's associated WorkSites
     * @return a list of all Clients in the database
     * @throws DataAccessException
     */
    public List<Client> findAllClients(boolean fullAssociation) throws DataAccessException {
        try {
            return clientDB.findAll(fullAssociation,Client.class);
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
            return clientDB.updateClient(clientCVR, newClient, Client.class);
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
            return clientDB.deleteClient(clientCVR);
        } catch (DataAccessException e) {
            throw new DataAccessException("ClientCtr error.", e);
        }
    }
}
