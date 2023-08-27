package org.gt.headhunterkingdoms.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLManager {
    final String username="root";
    final String password="";
    final String url = "jdbc:mysql://127.0.0.1/GT?user=root&password=&serverTimezone=UTC";

    static Connection connection;

    public boolean isConnected(){
        return (connection == null ? false : true);
    }
    public void setConnection() throws SQLException, ClassNotFoundException{
        if(!isConnected()){
            connection = DriverManager.getConnection(url, username, password);
        }
    }
    public void removeConnection() throws SQLException, ClassNotFoundException{
        if(isConnected()){
            try {
                connection.close();
            }
            catch (SQLException er){
                er.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
