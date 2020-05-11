package tests;
import controller.*;
import model.*;
import db.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

public class TestDBConection {

    private DBConnection dbConnection;

    @BeforeEach
    public void testDBConnection() {
        System.out.println("Initiaing DB Connection");
        dbConnection = DBConnection.getInstance();
        System.out.println(dbConnection.toString());
    }

    @Test
    public void testCreateNewClient() {
        LocalDate start = LocalDate.of(2020,06,11);
        LocalDate end = LocalDate.of(2025,06,11);
        Client c = new Client("74879488", "Basil Factory", "basil@leaves.dk", "+4512457898", "Rørdalsvej", "44", 9220, "DK", "Denmark", start, end);
        dbConnection = DBConnection.getInstance();
    }

    @Test
    public void testListAllClients() throws DataAccessException {
        ClientDB cDB = new ClientDB();
        cDB.findAll(false, Client.class);
    }
}
