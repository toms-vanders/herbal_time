package DB;

import Controller.DataAccessException;
import Model.WorkSite;

import java.lang.reflect.Type;
import java.util.List;

public interface WorkSiteDBIF {
    List<WorkSite> findAll(boolean fullAssociation) throws DataAccessException;
    List<WorkSite> findWorkSitesOfClient(String cvr, boolean fullAssociation) throws DataAccessException;
    WorkSite findByID(int workSiteID, boolean fullAssociation) throws DataAccessException;
    Integer insertWorkSite(String cvr, WorkSite newWorkSite) throws DataAccessException;
    Integer updateWorkSite(int workSiteID, WorkSite newWorkSite) throws DataAccessException;
    Integer deleteWorkSite(int workSiteID) throws DataAccessException;
}
