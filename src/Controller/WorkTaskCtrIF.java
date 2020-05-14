package Controller;

import Model.*;

import java.util.List;

public interface WorkTaskCtrIF {
    List<WorkTask> findallWorkTasks() throws DataAccessException;
    int insertWorkTask(WorkTask newWorkTask) throws DataAccessException;
    int updateWorkTask(String workTaskID, WorkTask newWorkTask) throws DataAccessException;
}
