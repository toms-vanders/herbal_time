package Controller;

import Model.*;

import java.lang.reflect.Type;
import java.util.List;

public interface ClientCtrIF {
    List<Client> findAllClients() throws DataAccessException;
    Client findClientByCVR(String cvr, boolean fullAssociation, Type type) throws DataAccessException;
    int insertClient(Client newClient) throws DataAccessException;
    int updateClient(String clientCVR, Client newClient) throws DataAccessException;
    int deleteClient(String clientCVR) throws DataAccessException;
}
