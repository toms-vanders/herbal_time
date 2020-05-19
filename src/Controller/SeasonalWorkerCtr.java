package Controller;

import Model.*;
import DB.*;

import javax.xml.crypto.Data;
import java.util.List;

public class SeasonalWorkerCtr implements SeasonalWorkerCtrIF{

    SeasonalWorkerIF seasonalWorkerDB;

    public SeasonalWorkerCtr() throws DataAccessException {
        try {
            seasonalWorkerDB = new SeasonalWorkerDB();
        }catch (DataAccessException e){
            throw new DataAccessException("Unable to obtain seasonal worker database instance.", e);
        }
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
