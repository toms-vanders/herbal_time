package DB;

import Controller.DataAccessException;
import Model.WorkSite;
import Model.WorkTask;
import Model.WorkType;

import javax.xml.crypto.Data;
import java.lang.reflect.Type;
import java.util.List;

/**
 * User of this interface implements access to data from the WorkType table in the database.
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
public interface WorkTypeDBIF {
    List<WorkType> findAll() throws DataAccessException;
    List<WorkType> findAllWorkTypesOfWorkSite(Integer workSiteID) throws DataAccessException;
    WorkType findWorkTypeByID(int workTypeID) throws DataAccessException;
    int findWorkTypeIDByDescription(String jobDescription) throws DataAccessException;
    int insertWorkType(Integer workSiteID, WorkType newWorkType) throws DataAccessException;
    int updateWorkType(int workTypeID, WorkType newWorkType) throws DataAccessException;
    int deleteWorkType(int workTypeID) throws DataAccessException;
}
