package GUI.DBStatus;

/**
 * A Status enum used mainly by the StatusThread class

 * <pre>
 *     Possible states:
 *     UNCHECKED - At the start of the application
 *     NO_CONNECTION - When there is no Internet connection
 *     UNABLE_TO_READ - When a query of INFORMATION_SCHEMA.TABLES returns an empty ResultSet
 *     WORKING - When the connection test passed
 *     UNKNOWN_ERROR - Unknown error while testing database connection
 * </pre>
 *
 * @author Daniel Zoltan Ban
 * @author Mikuláš Dobrodej
 * @author Adrian Mihai Dohot
 * @author Damian Hrabąszcz
 * @author Toms Vanders
 * @version 1.0 (29.05.2020)
 * @see GUI.DBStatus.StatusThread
 *
 */
public enum Status {
    UNCHECKED, NO_CONNECTION, UNABLE_TO_READ, WORKING, UNKNOWN_ERROR
}
