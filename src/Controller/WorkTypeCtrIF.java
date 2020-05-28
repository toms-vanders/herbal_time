package Controller;

import DB.DataAccessException;
import Model.WorkType;

import java.util.List;

public interface WorkTypeCtrIF {
    List<WorkType> findAll() throws DataAccessException;
    List<WorkType> findAllWorkTypesOfWorkSite(int workSiteID) throws DataAccessException;
    WorkType findWorkTypeByID(int worktypeID) throws DataAccessException;
    boolean insertWorkType(int workSiteID, WorkType newWorkType) throws DataAccessException;
    int updateWorkType(String workTypeID, WorkType newWorkType) throws DataAccessException;
    int deleteWorkType(int workTypeID) throws DataAccessException;

}
