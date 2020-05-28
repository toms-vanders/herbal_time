package GUI.Components;

import DB.DataAccessException;

public interface BackgroundOperation {
    void backgroundExecute() throws DataAccessException;
}
