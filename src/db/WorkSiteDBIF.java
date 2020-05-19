package DB;

import Controller.DataAccessException;
import Model.WorkSite;

import java.lang.reflect.Type;
import java.util.List;

public interface WorkSiteDBIF {
    List<WorkSite> findAll(boolean fullAssociation, Type type) throws DataAccessException;
    List<WorkSite> findWorkSitesOfClient(String cvr, boolean fullAssociation, Type type) throws DataAccessException;
    WorkSite findByID(int workSiteID, boolean fullAssociation, Type type) throws DataAccessException;
    Integer insertWorkSite(String cvr, WorkSite newWorkSite) throws DataAccessException;
    Integer updateWorkSite(int workSiteID, WorkSite newWorkSite, Type type) throws DataAccessException;
    Integer deleteWorkSite(int workSiteID) throws DataAccessException;
}
