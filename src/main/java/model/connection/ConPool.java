package model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ConPool {

    private static List<Connection> freeDbConnections = new LinkedList<Connection>();

    static {
        freeDbConnections = new LinkedList<Connection>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public ConPool() {
        freeDbConnections = new LinkedList<Connection>();
    }

    private static synchronized Connection createDBConnection() throws SQLException {

        String url = "jdbc:sqlserver://universita.database.windows.net:1433;database=movietipsdb;" +
                "user=unisaStudents@universita;password=(StudentiUnisa2021);encrypt=true;" +
                "trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        Connection newConnection = DriverManager.getConnection(url);
        newConnection.setAutoCommit(false);
        return newConnection;
    }

    public static synchronized Connection getConnection() throws SQLException {
        Connection connection;

        if (!freeDbConnections.isEmpty()) {
            connection = (Connection) freeDbConnections.get(0);
            freeDbConnections.remove(0);

            try {
                if (connection.isClosed())
                    connection = getConnection();
            } catch (SQLException e) {
                connection.close();
                connection = getConnection();
            }
        } else {
            connection = createDBConnection();
        }

        return connection;
    }

    public static synchronized void releaseConnection(Connection connection) throws SQLException {
        if (connection != null)
            freeDbConnections.add(connection);
    }
}