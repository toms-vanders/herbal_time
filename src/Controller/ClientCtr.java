package Controller;

import DB.ClientDB;
import Model.Client;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

public class ClientCtr implements ClientCtrIF {

    ClientDB clientDB;

    public ClientCtr() throws DataAccessException {
        try {
            clientDB = new ClientDB();
        } catch (DataAccessException e) {
            throw new DataAccessException("Unable to obtain client database instance.", e);
        }
    }

    public List<Client> findAllClients() throws DataAccessException {
        try {
            return clientDB.findAll(false,Client.class);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("ClientCtr error.", e);
        }
    }//

    @Override
    public Client findClientByCVR(String cvr, boolean fullAssociation, Type type) throws DataAccessException {
        try {
            return clientDB.findClientByCVR(cvr, fullAssociation, type);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("ClientCtr error.", e);
        }
    }

    public int insertClient(Client newClient) throws DataAccessException {
        try {
            return clientDB.insertClient(newClient, Client.class);
        } catch(DataAccessException e) {
            throw new DataAccessException("ClientCtr error.", e);
        }
    }

    public int updateClient(String clientCVR, Client newClient) throws DataAccessException {
        try {
            return clientDB.updateClient(clientCVR,newClient, Client.class);
        } catch(DataAccessException e) {
            throw new DataAccessException("ClientCtr error.", e);
        }
    }

    @Override
    public int deleteClient(String clientCVR) throws DataAccessException {
        try {
            return clientDB.deleteClient(clientCVR, Client.class);
        } catch (DataAccessException e) {
            throw new DataAccessException("ClientCtr error.", e);
        }
    }
}
