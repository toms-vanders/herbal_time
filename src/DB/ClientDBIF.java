package DB;
import Controller.*;
import Model.Client;

import java.lang.reflect.Type;
import java.util.List;

public interface ClientDBIF {
    List<Client> findAll(boolean fullAssociation, Type type) throws DataAccessException;
    Client findClientByCVR(String clientCVR, boolean fullAssociation, Type type) throws DataAccessException;
    int insertClient(Client newClient, Type type) throws DataAccessException;
    int updateClient(String clientCVR, Client newClient, Type type) throws DataAccessException;
    int deleteClient(String clientCVR, Type type) throws DataAccessException;
}
