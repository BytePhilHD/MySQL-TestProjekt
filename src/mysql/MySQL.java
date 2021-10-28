package mysql;

import java.io.*;
import java.nio.file.Files;
import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class MySQL {

    private static Connection con = null;

    public static void main(String[] args) throws SQLException {
        startMySQL();
        //new MySQL().addFile("test.txt");
    }

    public static void mySQLService() throws SQLException {
        Scanner scanner = new Scanner(System.in);

      /*  String cmd = "INSERT INTO Test (Tee, Tee1) VALUES (122, 10)";

        int rs;

        rs = stmt.executeUpdate(cmd);
        */
        Statement stmt = con.createStatement();

        ResultSet rst = stmt.executeQuery("SELECT * FROM `Test`");
        rst.next();

        System.out.println("Result: " + rst.getInt("Tee"));
        System.out.println("");
        new MySQL().addFile("test.txt", stmt);
        //addFile();
        /*
        System.out.println("Type something to put in the Hallo table.");
        String context = scanner.next();
        //stmt.executeUpdate("INSERT INTO Test (Tee, Hallo) VALUES (10, '" + context + "')");
        stmt.executeUpdate("INSERT INTO Test (Hallo) VALUES ('" + context + "')");
        */
    }

    public void addFile(String name, Statement stmt) {
        byte[] array = null;

        try {
            File file = new File(getClass().getClassLoader().getResource(name).getFile());
            array = Files.readAllBytes(file.toPath());
            //System.out.println("Bytes: " + Arrays.toString(array));
            //stmt.executeUpdate("INSERT INTO Files (Filename, File) VALUES ('TestFile', '" + Arrays.toString(array) + "')");
        } catch (Exception e1) {}
    }

    public void downloadFile(Statement stmt) throws SQLException {
        ResultSet rst = stmt.executeQuery("SELECT * FROM `Files`");
        rst.next();
        byte[] array = rst.getString("File").getBytes();
       // Files.write(getClass().getClassLoader().getResource("test1.txt"), array);
    }

    public static void startMySQL() {
        String dbName = "test";
        String dbUserName = "root";
        String dbPassword = "Collins234!";
        String address = "192.168.178.89:3306";
        String connectionString = "jdbc:mysql://" + address + "/" + dbName + "?user=" + dbUserName + "&password=" + dbPassword;

        try {
            System.out.println("Trying to connect to " + address + " with user " + dbUserName + " ...");
            con = DriverManager.getConnection(connectionString);
            Statement stmt = con.createStatement();
            ResultSet rst = stmt.executeQuery("/* ping */ SELECT 1");
            rst.next();
            System.out.println("Connection established! Ping to Server: " + rst.getInt(1) + "ms");
            mySQLService();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}