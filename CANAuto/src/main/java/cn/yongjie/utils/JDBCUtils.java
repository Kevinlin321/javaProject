package cn.yongjie.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class JDBCUtils {

    private JDBCUtils() {}

    private static Properties prop = null;
    private static JDBCUtils jdbcInstance = null;

    static{
        try {

            prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
            Class.forName(prop.getProperty("driver"));
            jdbcInstance = new JDBCUtils();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static JDBCUtils getInstance(){

        return jdbcInstance;
    }

    public Connection connection() throws Exception{

        return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
    }

    public void execute(Connection connection, String sqlText, ArrayList<Object> paramList) {

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlText);
            if (paramList != null) {
                for (int i = 0; i < paramList.size(); i++) {
                    preparedStatement.setObject(i + 1, paramList.get(i));
                }
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(Connection connection, String sqlText, ArrayList<Object> paramList) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sqlText);
            if (paramList != null) {
                for (int i = 0; i < paramList.size(); i++) {
                    preparedStatement.setObject(i + 1, paramList.get(i));
                }
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void Close(ResultSet resultSet, Statement statement, Connection connection){

        try {
            try {
                if(resultSet!=null) resultSet.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }finally {
                try {
                    if(statement!=null) statement.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }finally {
                    try {
                        if(connection!=null) connection.close();
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
