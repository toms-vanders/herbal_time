package DB;
import Model.Client;

import java.lang.reflect.Type;
import java.util.List;

/**
 * User of this interface implements access to data from the Client table in the database.
 *
 * @author Daniel Zoltan Ban
 * @author Mikuláš Dobrodej
 * @author Adrian Mihai Dohot
 * @author Damian Hrabąszcz
 * @author Toms Vanders
 * @version 1.0
 *
 * Date: 29.05.2020
 */
public interface ClientDBIF {
    List<Client> findAll(boolean fullAssociation, Type type) throws DataAccessException;
    Client findClientByCVR(String clientCVR, boolean fullAssociation, Type type) throws DataAccessException;
    int insertClient(Client newClient, Type type) throws DataAccessException;
    int updateClient(String clientCVR, Client newClient, Type type) throws DataAccessException;
    int deleteClient(String clientCVR) throws DataAccessException;
}
