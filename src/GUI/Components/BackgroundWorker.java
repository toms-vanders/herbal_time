package GUI.Components;

import javax.swing.*;
import java.awt.*;

public class BackgroundWorker {

    public BackgroundWorker(BackgroundOperation operation,String title,String msg) {
        SwingWorker<Void,Void> loadingSwingWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                EventQueue.invokeLater(operation::backgroundExecute);
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
