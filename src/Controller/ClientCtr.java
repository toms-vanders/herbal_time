package Controller;

import DB.ClientDB;
import Model.Client;

import java.util.List;

public class ClientCtr implements ClientCtrIF{

    ClientDB clientDB;

    public List<Client> findAllClients() throws DataAccessException {
        try {
            return clientDB.findAll(false,Client.class);
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
}
