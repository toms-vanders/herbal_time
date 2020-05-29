package GUI.DBStatus;

import DB.DataAccessException;
import DB.DBConnection;
import GUI.MainScreen;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A Status thread, used to check on the database connection every 5 seconds
 * and update the status bar in the application's MainScreen instance.
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
public class StatusThread extends Thread {
    private DBConnection dbConnection;
    private static final String checkTables = "SELECT * FROM INFORMATION_SCHEMA.TABLES";
    private PreparedStatement PSCheckTables;
    private Status status;
    private Status lastChangedStatus;
    private final JLabel statusLabel = MainScreen.getConnectionStatusLabel();
    private final JLabel statusIcon = MainScreen.getConnectionStatusIcon();
    public AtomicBoolean connectionStatus = new AtomicBoolean(false);

    public StatusThread() {
        this.status = Status.UNCHECKED;
    }

    public AtomicBoolean getConnectionStatus(){
        return connectionStatus;
    }

    @SuppressWarnings({"InfiniteLoopStatement", "BusyWait"})
    @Override
    public void run() {
        while (true) {
            try {
                checkDBConnection();
            } catch (DataAccessException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Fetches the icon to be displayed.
     *
     * @param status Choose icon depending on the status
     * @return Image of the icon to be displayed
     */
    private ImageIcon getIcon(Status status) {
        if (status == Status.WORKING) {
            connectionStatus.set(true);
            return new ImageIcon(getClass().getResource("/icons8_connection_status_on_24px.png"));
        } else if (status == Status.UNABLE_TO_READ) {
            connectionStatus.set(false);
            return new ImageIcon(getClass().getResource("icons8_connection_status_meh_24px.png"));
        } else {
            connectionStatus.set(false);
            return new ImageIcon(getClass().getResource("/icons8_connection_status_off_24px.png"));
        }
    }

    /**
     * Tries to connect with database and test connection.
     * Changes application's MainScreen status bar.
     *
     * @throws DataAccessException
     */
    public void checkDBConnection() throws DataAccessException {
        Connection con;

        dbConnection = DBConnection.getInstance();
        DBConnection.connect();
        con = dbConnection.getConnection();

        if (DBConnection.instanceIsNull()) {
            if (this.status != Status.NO_CONNECTION) {
                this.status = Status.NO_CONNECTION;
            }

            statusLabel.setText(status.toString().replaceAll("_", " "));
            statusIcon.setIcon(getIcon(status));
            return;
        }
//        else {
//            System.out.println("Acquired connection: " + con.toString());
//        }

        try {
            PSCheckTables = con.prepareStatement(checkTables);
        } catch (SQLException e) {
            dbConnection.disconnect();
            throw new DataAccessException("Issue preparing statement (status).", e);
        }

        ResultSet rs;
        try {
            rs = PSCheckTables.executeQuery();
        } catch (SQLException e) {
            if (this.status != Status.NO_CONNECTION) {
                this.status = Status.NO_CONNECTION;
                statusLabel.setText(status.toString().replaceAll("_", " "));
                statusIcon.setIcon(getIcon(status));
            }
            dbConnection.disconnect();
            throw new DataAccessException("Issue connecting to the database (status).", e);
        }

        try {
            if (rs.next()) {
                if (this.status != Status.WORKING) {
                    this.status = Status.WORKING;
                    statusLabel.setText(status.toString().replaceAll("_", " "));
                    statusIcon.setIcon(getIcon(status));
                }
            } else {
                if (this.status != Status.UNABLE_TO_READ) {
                    this.status = Status.UNABLE_TO_READ;
                    statusLabel.setText(status.toString().replaceAll("_", " "));
                    statusIcon.setIcon(getIcon(status));
                }
            }
        } catch (SQLException e) {
            if (this.status != Status.UNKNOWN_ERROR) {
                this.status = Status.UNKNOWN_ERROR;
                statusLabel.setText(status.toString().replaceAll("_", " "));
                statusIcon.setIcon(getIcon(status));
            }
            dbConnection.disconnect();
            throw new DataAccessException("Issue reading from the result set (status).", e);
        }

        // TODO
        // notify about accquiring connection only when the connection was REgained.
        if (lastChangedStatus != status) {
            // Accquired connection here?
            // System.out.println("Acquired connection: " + con.toString());
        }

        statusLabel.setText(status.toString().replaceAll("_", " "));
        statusIcon.setIcon(getIcon(status));
        dbConnection.disconnect();
    }
}
