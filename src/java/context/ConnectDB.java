package context;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author MrEnd
 */
public class ConnectDB {

    private static ConnectDB instance;

    public ConnectDB() {
    }

    public Connection openConnection() throws Exception {
        String connectionUrl = "jdbc:sqlserver://127.0.0.1:1433;" +
                "databaseName=PRJ321_YourID;User=sa;Password=123;";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection(connectionUrl);
        return con;
    }

    // Get instance of dbms only one time
    public static ConnectDB getInstance() {
        if (instance == null) instance = new ConnectDB();
        return instance;
    }
}
