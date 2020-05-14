package DB;

import Controller.DataAccessException;
import Model.WorkTask;

import java.util.List;

public interface WorkTaskDBIF {
    List<WorkTask> findAll(boolean fullAssociation) throws DataAccessException;
    WorkTask findByID(Integer id, boolean fullAssociation) throws DataAccessException;
    Integer insertWorkTask(WorkTask workTask) throws DataAccessException;
    Integer updateWorkTask(WorkTask workTask) throws DataAccessException;
    void removeWorkTask(Integer id) throws DataAccessException;
}

