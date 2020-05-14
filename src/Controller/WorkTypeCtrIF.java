package Controller;

import Model.*;

import java.util.List;

public interface WorkTypeCtrIF {
    List<WorkType> findallWorkTypes() throws DataAccessException;
    int insertWorkType(WorkType newWorkType) throws DataAccessException;
    int updateWorkType(String workTypeID, WorkType newWorkType) throws DataAccessException;

}
