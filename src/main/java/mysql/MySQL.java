package mysql;

import java.sql.*;

public class MySQL {

    private static Connection con = null;

    public static void mySQLService() throws SQLException {

        String cmd = "INSERT INTO Test (Tee, Tee1) VALUES (122, 10)";

        Statement stmt = con.createStatement();
        int rs;

        rs = stmt.executeUpdate(cmd);

        ResultSet rst = stmt.executeQuery("SELECT * FROM `Test`");
        rst.next();

        System.out.println("Result: " + rst.getInt("Tee"));

    }

    public static void testMySQL() {
        String dbName = "test";
        String dbUserName = "root";
        String dbPassword = "Collins234!";
        String address = "192.168.178.89:3306";
        String connectionString = "jdbc:mysql://" + address + "/" + dbName + "?user=" + dbUserName + "&password=" + dbPassword;

        try {
            System.out.println("Trying to connect to " + address + " with user " + dbUserName + " ...");
            con = DriverManager.getConnection(connectionString);
            Statement stmt = con.createStatement();
            ResultSet rst = stmt.executeQuery("/* ping */ SELECT 1"); rst.next();
            System.out.println("Connection established! Ping to Server: " + rst.getInt(1) + "ms");
            mySQLService();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}