package GUI.DBStatus;

import Controller.DataAccessException;
import DB.DBConnection;
import GUI.MainScreen;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusThread extends Thread {
    private DBConnection dbConnection;
    private static final String checkTables = "SELECT * FROM INFORMATION_SCHEMA.TABLES";
    private PreparedStatement PSCheckTables;
    private Status status;
    private Status lastChangedStatus;
    private JLabel statusLabel = MainScreen.getConnectionStatusLabel();
    private JLabel statusIcon = MainScreen.getConnectionStatusIcon();

    public StatusThread() {
        this.status = Status.UNCHECKED;
    }

    @Override
    public void run() {
//        System.out.println("Current connection status: " + this.status);
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

    private ImageIcon getIcon(Status status) {
        if (status == Status.WORKING) {
            return new ImageIcon(getClass().getResource("/icons8_connection_status_on_24px.png"));
        } else if (status == Status.UNABLE_TO_READ) {
            return new ImageIcon(getClass().getResource("icons8_connection_status_meh_24px.png"));
        } else {
            return new ImageIcon(getClass().getResource("/icons8_connection_status_off_24px.png"));
        }
    }


    public void checkDBConnection() throws DataAccessException {
        ResultSet rs;
        Connection con;

//        System.out.println("Checking DB connection status:");
        dbConnection = DBConnection.getInstance();
        DBConnection.connect();
        con = dbConnection.getConnection();

        if (DBConnection.instanceIsNull()) {
            if (this.status != Status.NO_CONNECTION) {
                this.status = Status.NO_CONNECTION;
            }
//            System.out.println("Current connection status: " + this.status);
            statusLabel.setText(String.valueOf(status));
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

        try {
            rs = PSCheckTables.executeQuery();
        } catch (SQLException e) {
            if (this.status != Status.NO_CONNECTION) {
                this.status = Status.NO_CONNECTION;
//                System.out.println("Current connection status: " + this.status);
                statusLabel.setText(String.valueOf(status));
                statusIcon.setIcon(getIcon(status));
            }
            dbConnection.disconnect();
            throw new DataAccessException("Issue connecting to the database (status).", e);
        }

        try {
            if (rs.next()) {
                if (this.status != Status.WORKING) {
                    this.status = Status.WORKING;
//                    System.out.println("Current connection status: " + this.status);
                    statusLabel.setText(String.valueOf(status));
                    statusIcon.setIcon(getIcon(status));
                }
            } else {
                if (this.status != Status.UNABLE_TO_READ) {
                    this.status = Status.UNABLE_TO_READ;
//                    System.out.println("Current connection status: " + this.status);
                    statusLabel.setText(String.valueOf(status));
                    statusIcon.setIcon(getIcon(status));
                }
            }
        } catch (SQLException e) {
            if (this.status != Status.UNKNOWN_ERROR) {
                this.status = Status.UNKNOWN_ERROR;
//                System.out.println("Current connection status: " + this.status);
                statusLabel.setText(String.valueOf(status));
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

//        System.out.println("Current connection status: " + this.status);
        statusLabel.setText(String.valueOf(status));
        statusIcon.setIcon(getIcon(status));
        dbConnection.disconnect();
    }
}
