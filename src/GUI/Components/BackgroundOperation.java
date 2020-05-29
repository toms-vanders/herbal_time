package GUI.Components;

import DB.DataAccessException;

/**
 * Functional interface to pass operations for background execution.
 */
public interface BackgroundOperation {
    void backgroundExecute() throws DataAccessException;
}
