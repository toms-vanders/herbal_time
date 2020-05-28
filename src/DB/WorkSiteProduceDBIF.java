package DB;

import Model.WorkSiteProduce;

import java.lang.reflect.Type;
import java.util.List;

/**
 * User of this interface implements access to data from the WorkSiteProduce table in the database.
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
public interface WorkSiteProduceDBIF {
    List<WorkSiteProduce> findAll(Type type) throws DataAccessException;
    List<WorkSiteProduce> findByProduce(String produceName, Type type) throws DataAccessException;
    List<WorkSiteProduce> findByWorkSite(int workSiteID, Type type) throws DataAccessException;
    Integer insertWorkSiteProduce(WorkSiteProduce newWorkSiteProduce, Type type) throws DataAccessException;
    Integer updateWorkSiteProduce(int workSiteID, String produceName, WorkSiteProduce newWorkSiteProduce, Type type) throws DataAccessException;
    Integer deleteWorkSiteProduce(int workSiteID, String produceName, Type type) throws DataAccessException;
}
