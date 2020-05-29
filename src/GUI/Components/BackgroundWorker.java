package GUI.Components;

import DB.DataAccessException;

import javax.swing.*;
import java.awt.*;

/**
 * A background operation execution lambda handler, used to display loading screens while operations are being made
 * through swing worker.
 *
 * @author Daniel Zoltan Ban
 * @author Mikuláš Dobrodej
 * @author Adrian Mihai Dohot
 * @author Damian Hrabąszcz
 * @author Toms Vanders
 * @version 1.0 (29.05.2020)
 *
 * Date: 29.05.2020
 */

public class BackgroundWorker {

    /**
     * Receives a lambda function as an operation to be passed to the functional interface
     * and executed in the background through the swing worker as the client is notified of the operation
     * taking place
     * @param operation Lambda function to be executed
     * @param title String for the status dialogs title
     * @param msg String for the status dialogs message body
     */
    public BackgroundWorker(BackgroundOperation operation,String title,String msg) {
        SwingWorker<Void,Void> loadingSwingWorker = new SwingWorker<Void,Void>() {
            @Override
            protected Void doInBackground() throws DataAccessException {
                EventQueue.invokeLater(() -> {
                    try {
                        operation.backgroundExecute();
                    } catch (DataAccessException e) {
                        e.printStackTrace();
                    }
                });
                return null;
            }
        };
        final StatusDialog loadingTask = new StatusDialog(new JFrame(),false,
                StatusDialog.LOADING,
                title,
                msg);

        loadingSwingWorker.addPropertyChangeListener(evt -> {
            if(evt.getPropertyName().equals("state")){
                if(evt.getNewValue() == SwingWorker.StateValue.DONE){
                    loadingTask.dispose();
                }
            }
        });
        loadingSwingWorker.execute();
    }
}
