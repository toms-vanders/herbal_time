package DB;

import Model.WorkType;

import java.util.List;

/**
 * User of this interface implements access to data from the WorkType table in the database.
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
public interface WorkTypeDBIF {
    List<WorkType> findAll() throws DataAccessException;
    List<WorkType> findAllWorkTypesOfWorkSite(Integer workSiteID) throws DataAccessException;
    WorkType findWorkTypeByID(int workTypeID) throws DataAccessException;
    int findWorkTypeIDByDescription(String jobDescription) throws DataAccessException;
    int insertWorkType(Integer workSiteID, WorkType newWorkType) throws DataAccessException;
    int updateWorkType(int workTypeID, WorkType newWorkType) throws DataAccessException;
    int deleteWorkType(int workTypeID) throws DataAccessException;
}
