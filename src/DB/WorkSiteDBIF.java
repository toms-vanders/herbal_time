package DB;

import Controller.DataAccessException;
import Model.WorkSite;

import javax.xml.crypto.Data;
import java.lang.reflect.Type;
import java.util.List;

public interface WorkSiteDBIF {
    List<WorkSite> findAll(boolean fullAssociation) throws DataAccessException;
    List<WorkSite> findWorkSitesOfClient(String cvr, boolean fullAssociation) throws DataAccessException;
    WorkSite findByWorkerCPR(String cpr, boolean fullAssociation) throws DataAccessException;
    WorkSite findByID(int workSiteID, boolean fullAssociation) throws DataAccessException;
    WorkSite findByName(String siteName, boolean fullAssociation) throws DataAccessException;
    int insertWorkSite(String cvr, WorkSite newWorkSite) throws DataAccessException;
    int updateWorkSite(int workSiteID, WorkSite newWorkSite) throws DataAccessException;
    int deleteWorkSite(int workSiteID) throws DataAccessException;
}
