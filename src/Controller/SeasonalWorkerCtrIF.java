package Controller;

import Model.*;

import java.util.List;

public interface SeasonalWorkerCtrIF {
    List<SeasonalWorker> findAllSeasonalWorkers() throws DataAccessException;
    SeasonalWorker findSeasonalWorkerByCPR(String cpr) throws DataAccessException;
    SeasonalWorker findSeasonalWorkerByWorkTask(int workTaskID) throws DataAccessException;
    int insertSeasonalWorker(SeasonalWorker newSeasonalWorker, int workSiteID) throws DataAccessException;
    int updateSeasonalWorker(String cpr, SeasonalWorker newSeasonalWorker) throws DataAccessException;
    void deleteSeasonalWorker(String cpr) throws DataAccessException;
}
