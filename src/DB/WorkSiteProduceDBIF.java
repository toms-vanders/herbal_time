package DB;

import Controller.DataAccessException;
import Model.Produce;
import Model.WorkSiteProduce;

import java.lang.reflect.Type;
import java.util.List;

public interface WorkSiteProduceDBIF {
    List<WorkSiteProduce> findAll(boolean fullAssociation, Type type) throws DataAccessException;
    List<WorkSiteProduce> findByProduce(String produceName, boolean fullAssociation, Type type) throws DataAccessException;
    List<WorkSiteProduce> findByWorkSite(int workSiteID, boolean fullAssociation, Type type) throws DataAccessException;
    Integer insertWorkSiteProduce(int workSiteID, String produceName, WorkSiteProduce newWorkSiteProduce, Type type) throws DataAccessException;
    Integer updateWorkSiteProduce(int workSiteID, String produceName, WorkSiteProduce newWorkSiteProduce, Type type) throws DataAccessException;
    Integer deleteWorkSiteProduce(int workSiteID, String produceName, Type type) throws DataAccessException;
}
