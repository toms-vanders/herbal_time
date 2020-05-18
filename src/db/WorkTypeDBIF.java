package DB;

import Controller.DataAccessException;
import Model.WorkSite;
import Model.WorkTask;
import Model.WorkType;

import javax.xml.crypto.Data;
import java.lang.reflect.Type;
import java.util.List;

public interface WorkTypeDBIF {
    List<WorkType> findAll(boolean fullAssociation, Type type) throws DataAccessException;
    List<WorkType> findAllWorkTypesOfWorkSite(Integer workSiteID, boolean fullAssociation, Type type) throws DataAccessException;
    WorkType findByID(Integer id, boolean fullAssociation) throws DataAccessException;
    Integer insertWorkType(Integer workSiteID, WorkType newWorkType) throws DataAccessException;
    Integer updateWorkType(int workTypeID, WorkType newWorkType, Type type) throws DataAccessException;
    Integer deleteWorkType(int workTypeID) throws DataAccessException;
}
