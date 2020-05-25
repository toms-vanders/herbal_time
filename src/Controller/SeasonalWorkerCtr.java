package Controller;

import Model.*;
import DB.*;

import java.util.List;

public class SeasonalWorkerCtr implements SeasonalWorkerCtrIF{

    SeasonalWorkerIF seasonalWorkerDB;

    public SeasonalWorkerCtr() throws DataAccessException {
        seasonalWorkerDB = new SeasonalWorkerDB();
    }

    @Override
    public List<SeasonalWorker> findAllSeasonalWorkers() throws DataAccessException {
        try {
            return seasonalWorkerDB.findAll(false, SeasonalWorker.class);
        } catch (DataAccessException e) {
            throw new DataAccessException("SeasonalWorkerCtr error.", e);
        }
    }

    @Override
    public SeasonalWorker findSeasonalWorkerByCPR(String cpr) throws DataAccessException {
        try {
            return seasonalWorkerDB.findSeasonalWorkerByCPR(cpr, false, SeasonalWorker.class);
        } catch (DataAccessException e) {
            throw new DataAccessException("SeasonalWorkerCtr error.", e);
        }
    }

    @Override
    public SeasonalWorker findSeasonalWorkerByWorkTask(int workTaskID) throws DataAccessException {
        try{
            return seasonalWorkerDB.findSeasonalWorkerByWorkTask(workTaskID,false,SeasonalWorker.class);
        }catch(DataAccessException e){
            throw new DataAccessException("Unable to retrieve seasonal worker through work task ID",e);
        }
    }

    @Override
    public int insertSeasonalWorker(SeasonalWorker newSeasonalWorker, int workSiteID) throws DataAccessException {
        try {
            return seasonalWorkerDB.insertSeasonalWorker(newSeasonalWorker, workSiteID, SeasonalWorker.class);
        } catch (DataAccessException e) {
        throw new DataAccessException("SeasonalWorkerCtr error.", e);
    }
    }

    @Override
    public int updateSeasonalWorker(String cpr, SeasonalWorker newSeasonalWorker) throws DataAccessException {
        try {
            return seasonalWorkerDB.updateSeasonalWorker(cpr, newSeasonalWorker, SeasonalWorker.class);
        } catch (DataAccessException e) {
            throw new DataAccessException("SeasonalWorkerCtr error.", e);
        }
    }

    @Override
    public void deleteSeasonalWorker(String cpr) throws DataAccessException {
        try {
            seasonalWorkerDB.deleteSeasonalWorker(cpr, SeasonalWorker.class);
        } catch (DataAccessException e) {
            throw new DataAccessException("SeasonalWorkerCtr error.", e);
        }
    }
}
