package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    private final Properties props;
    public DBUtils(Properties props){
        this.props = props;
    }
    private Connection instance = null;

    private Connection getNewConnection(){
        String url = props.getProperty("jdbc.url");
        String user = props.getProperty("jdbc.user");
        String pass = props.getProperty("jdbc.pass");

        Connection con = null;

        try {
            if (user != null && pass != null)
                con = DriverManager.getConnection(url, user, pass);
            else
                con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("error getting connection: " + e);
        }

        return con;
    }

    public Connection getConnection(){
        try {
            if (instance == null || instance.isClosed())
                instance = getNewConnection();
        } catch (SQLException e) {
            System.out.println("db error: " + e);
        }

        return instance;
    }
}
