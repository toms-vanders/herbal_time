package DB;

import Controller.DataAccessException;
import Model.WorkType;

import java.lang.reflect.Type;
import java.util.List;

public interface WorkTypeDBIF {
    List<WorkType> findAll(Integer workSiteID, boolean fullAssociation, Type type) throws DataAccessException;
    Boolean insertWorkType(Integer workSiteID, WorkType newWorkType) throws DataAccessException;
}
