package Controller;

import Model.*;

import java.lang.reflect.Type;
import java.util.List;

public interface WorkTypeCtrIF {
    List<WorkType> findAll() throws DataAccessException;
    List<WorkType> findAllWorkTypesOfWorkSite(int workSiteID) throws DataAccessException;
    WorkType findWorkTypeByID(int worktypeID) throws DataAccessException;
    boolean insertWorkType(int workSiteID, WorkType newWorkType) throws DataAccessException;
    int updateWorkType(String workTypeID, WorkType newWorkType) throws DataAccessException;
    int deleteWorkType(int workTypeID) throws DataAccessException;

}
