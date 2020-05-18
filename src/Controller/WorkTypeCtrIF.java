package Controller;

import Model.*;

import java.util.List;

public interface WorkTypeCtrIF {
    List<WorkType> findAllWorkTypesOfWorkSite(int workSiteID) throws DataAccessException;
    int insertWorkType(int workSiteID, WorkType newWorkType) throws DataAccessException;
    int updateWorkType(String workTypeID, WorkType newWorkType) throws DataAccessException;

}
