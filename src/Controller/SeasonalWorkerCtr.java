package Controller;

import Model.*;
import DB.*;

import java.util.List;

/**
 * Used to interface between the GUI layer and SeasonalWorkerDB
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
public class SeasonalWorkerCtr implements SeasonalWorkerCtrIF{

    final SeasonalWorkerDBIF seasonalWorkerDB;

    public SeasonalWorkerCtr() {
        seasonalWorkerDB = new SeasonalWorkerDB();
    }

    /**
     * Returns a list of all SeasonalWorkers loaded in the database
     * @return a list of all SeasonalWorkers loaded in the database
     * @throws DataAccessException
     */
    @Override
    public List<SeasonalWorker> findAllSeasonalWorkers() throws DataAccessException {
        try {
            return seasonalWorkerDB.findAll(false, SeasonalWorker.class);
        } catch (DataAccessException e) {
            throw new DataAccessException("SeasonalWorkerCtr error.", e);
        }
    }

    /**
     * Return a SeasonalWorker with the appropriate CPR number
     * @param cpr CPR of the SeasonalWorker to return
     * @return SeasonalWorker object with the appropriate CPR number
     * @throws DataAccessException
     */
    @Override
    public SeasonalWorker findSeasonalWorkerByCPR(String cpr) throws DataAccessException {
        try {
            return seasonalWorkerDB.findSeasonalWorkerByCPR(cpr, false, SeasonalWorker.class);
        } catch (DataAccessException e) {
            throw new DataAccessException("SeasonalWorkerCtr error.", e);
        }
    }

    /**
     * Return a SeasonalWorker with the appropriate Work Task ID
     * @param workTaskID WorkTask ID of the SeasonalWorker to return
     * @return SeasonalWorker object with the appropriate Work Task ID
     * @throws DataAccessException
     */
    @Override
    public SeasonalWorker findSeasonalWorkerByWorkTask(int workTaskID) throws DataAccessException {
        try{
            return seasonalWorkerDB.findSeasonalWorkerByWorkTask(workTaskID,false,SeasonalWorker.class);
        }catch(DataAccessException e){
            throw new DataAccessException("Unable to retrieve seasonal worker through work task ID",e);
        }
    }

    /**
     * Inserts a new SeasonalWorker into the database
     * @param newSeasonalWorker the new SeasonalWorker  to be inserted
     * @param workSiteID WorkSite ID of the SeasonalWorker
     * @return Number of rows affected
     * @throws DataAccessException
     */
    @Override
    public boolean insertSeasonalWorker(SeasonalWorker newSeasonalWorker, int workSiteID) throws DataAccessException {
        try {
            return (seasonalWorkerDB.insertSeasonalWorker(newSeasonalWorker, workSiteID, SeasonalWorker.class)==1);
        } catch (DataAccessException e) {
        throw new DataAccessException("SeasonalWorkerCtr error.", e);
    }
    }

    /**
     * Make changes to SeasonalWorkers already in the database
     * @param cpr CPR number of the SeasonalWorker wished to be changed
     * @param newSeasonalWorker new SeasonalWorker object to replace the original one
     * @return Number of rows affected
     * @throws DataAccessException
     */
    @Override
    public int updateSeasonalWorker(String cpr, SeasonalWorker newSeasonalWorker) throws DataAccessException {
        try {
            return seasonalWorkerDB.updateSeasonalWorker(cpr, newSeasonalWorker, SeasonalWorker.class);
        } catch (DataAccessException e) {
            throw new DataAccessException("SeasonalWorkerCtr error.", e);
        }
    }

    /**
     * Deletes a SeasonalWorker from the database
     * @param cpr CPR number of SeasonalWorker to be deleted
     * @return Number of rows affected
     * @throws DataAccessException
     */
    @Override
    public int deleteSeasonalWorker(String cpr) throws DataAccessException {
        try {
            return seasonalWorkerDB.deleteSeasonalWorker(cpr, SeasonalWorker.class);
        } catch (DataAccessException e) {
            throw new DataAccessException("SeasonalWorkerCtr error.", e);
        }
    }
}
