package db;
import model.*;
import controller.*;

import java.lang.reflect.Type;
import java.util.List;

public interface ClientDBIF {
    List<Client> findAll(boolean fullAssociation, Type type) throws DataAccessException;
    Boolean insertClient(Client newClient, Type type) throws DataAccessException;
}
