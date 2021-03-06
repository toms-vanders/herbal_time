package DB;

import Model.WorkSite;

import java.util.List;

/**
 * User of this interface implements access to data from the WorkSite table in the database.
 * @author Daniel Zoltan Ban
 * @author Mikuláš Dobrodej
 * @author Adrian Mihai Dohot
 * @author Damian Hrabąszcz
 * @author Toms Vanders
 * @version 1.0 (29.05.2020)
 *
 * Date: 29.05.2020
 */
public interface WorkSiteDBIF {
    List<WorkSite> findAll(boolean fullAssociation) throws DataAccessException;
    List<WorkSite> findWorkSitesOfClient(String cvr, boolean fullAssociation) throws DataAccessException;
    WorkSite findByWorkerCPR(String cpr, boolean fullAssociation) throws DataAccessException;
    WorkSite findByID(int workSiteID, boolean fullAssociation) throws DataAccessException;
    WorkSite findByName(String siteName, boolean fullAssociation) throws DataAccessException;
    int insertWorkSite(String cvr, WorkSite newWorkSite) throws DataAccessException;
    int updateWorkSite(int workSiteID, WorkSite newWorkSite) throws DataAccessException;
    int updateWorkSiteByName(String workSiteName, WorkSite newWorkSite) throws DataAccessException;
    int deleteWorkSite(int workSiteID) throws DataAccessException;
    int deleteWorkSiteByName(String workSiteName) throws DataAccessException;
}
