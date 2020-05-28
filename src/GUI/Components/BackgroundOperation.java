package GUI.Components;

import DB.Exception.DataAccessException;

public interface BackgroundOperation {
    void backgroundExecute() throws DataAccessException;
}
