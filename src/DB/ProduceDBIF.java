package DB;

import Model.Produce;

import java.lang.reflect.Type;
import java.util.List;

/**
 * User of this interface implements access to data from the Produce table in the database.
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
public interface ProduceDBIF {

    List<Produce> findAll(Type type) throws DataAccessException;
    Produce findProduce(String produceName,Type type) throws DataAccessException;
    List<Produce> findWorkSiteProduce(int workSiteID, Type type) throws DataAccessException;
    int insertProduce(Produce newProduce, Type type) throws DataAccessException;
    int updateProduce(String produceName, Produce newProduce, Type type) throws DataAccessException;
    int deleteProduce(String produceName, Type type) throws DataAccessException;
}
