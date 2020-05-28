package DB;

import Controller.*;
import Model.*;

import java.lang.reflect.Type;
import java.util.*;

/**
 * User of this interface implements access to data about seasonal workers from the database.
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
public interface SeasonalWorkerIF {
    List<SeasonalWorker> findAll(boolean fullAssociation, Type type) throws DataAccessException;
    SeasonalWorker findSeasonalWorkerByCPR(String seasonalWorkerCPR, boolean fullAssociation, Type type) throws DataAccessException;
    SeasonalWorker findSeasonalWorkerByWorkTask(int workTaskID, boolean fullAssociation, Type type) throws DataAccessException;
    int findWorkSiteIDOfSeasonalWorker(String seasonalWorkerCPR) throws DataAccessException;
    int insertSeasonalWorker(SeasonalWorker newSeasonalWorker, Integer workSiteID, Type type) throws DataAccessException;
    int updateSeasonalWorker(String seasonalWorkerCPR, SeasonalWorker newSeasonalWorker, Type type) throws DataAccessException;
    int deleteSeasonalWorker(String seasonalWorkerCPR, Type type) throws DataAccessException;
    int attachWorkSiteToSeasonalWorker(String seasonalWorkerCPR, int wSiteID) throws DataAccessException;
}
