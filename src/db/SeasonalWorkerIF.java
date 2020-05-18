package DB;

import Controller.*;
import Model.*;

import java.lang.reflect.Type;
import java.util.*;

public interface SeasonalWorkerIF {
    List<SeasonalWorker> findAll(boolean fullAssociation, Type type) throws DataAccessException;
    SeasonalWorker findSeasonalWorkerByCPR(String seasonalWorkerCPR, boolean fullAssociation, Type type) throws DataAccessException;
    int insertSeasonalWorker(SeasonalWorker newSeasonalWorker, Integer workSiteID, Type type) throws DataAccessException;
    int updateSeasonalWorker(String seasonalWorkerCPR, SeasonalWorker newSeasonalWorker, Type type) throws DataAccessException;
    int deleteSeasonalWorker(String seasonalWorkerCPR, Type type) throws DataAccessException;
}
