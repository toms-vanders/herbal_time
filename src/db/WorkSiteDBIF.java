package DB;

import Controller.DataAccessException;
import Model.WorkSite;

import java.lang.reflect.Type;
import java.util.List;

public interface WorkSiteDBIF {
    List<WorkSite> findAll(String cvr, boolean fullAssociation, Type type) throws DataAccessException;
    Boolean insertWorkSite(String cvr, WorkSite newWorkSite) throws DataAccessException;
}
