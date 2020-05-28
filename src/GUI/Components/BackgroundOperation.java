package GUI.Components;

import Controller.DataAccessException;

public interface BackgroundOperation {
    public void backgroundExecute() throws DataAccessException;
}
