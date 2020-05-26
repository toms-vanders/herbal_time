package DB;

import Controller.DataAccessException;
import Model.WorkSite;
import Model.WorkTask;
import Model.WorkType;

import javax.xml.crypto.Data;
import java.lang.reflect.Type;
import java.util.List;

public interface WorkTypeDBIF {
    List<WorkType> findAll() throws DataAccessException;
    List<WorkType> findAllWorkTypesOfWorkSite(Integer workSiteID) throws DataAccessException;
    WorkType findWorkTypeByID(int workTypeID) throws DataAccessException;
    int insertWorkType(Integer workSiteID, WorkType newWorkType) throws DataAccessException;
    int updateWorkType(int workTypeID, WorkType newWorkType) throws DataAccessException;
    int deleteWorkType(int workTypeID) throws DataAccessException;
}
