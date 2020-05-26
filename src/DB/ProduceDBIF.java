package DB;

import Controller.DataAccessException;
import Model.Produce;

import java.lang.reflect.Type;
import java.util.List;

public interface ProduceDBIF {

    List<Produce> findAll(boolean fullAssociation, Type type) throws DataAccessException;
    Produce findProduce(String produceName, boolean fullAssociation, Type type) throws DataAccessException;
    List<Produce> findWorkSiteProduce(int workSiteID, Type type) throws DataAccessException;
    int insertProduce(Produce newProduce, Type type) throws DataAccessException;
    int updateProduce(String produceName, Produce newProduce, Type type) throws DataAccessException;
    int deleteProduce(String produceName, Type type) throws DataAccessException;
}
