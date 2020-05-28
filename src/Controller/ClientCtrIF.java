package Controller;

import DB.Exception.DataAccessException;
import Model.Client;

import java.lang.reflect.Type;
import java.util.List;

public interface ClientCtrIF {
    List<Client> findAllClients(boolean fullAssociation) throws DataAccessException;
    Client findClientByCVR(String cvr, boolean fullAssociation, Type type) throws DataAccessException;
    boolean insertClient(Client newClient) throws DataAccessException;
    int updateClient(String clientCVR, Client newClient) throws DataAccessException;
    int deleteClient(String clientCVR) throws DataAccessException;
}
