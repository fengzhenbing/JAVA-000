import java.sql.*;

/**
 * @description:
 * @author: fzb
 * @create: 2020-11-18 15:45
 */
public class PrepareStatementTest {
    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://106.14.76.240:3306/demo?useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&allowMultiQueries=true&serverTimezone=UTC";
    static final String USER = "mk";
    static final String PASS = "mokeTestEnv01";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,USER,PASS);

            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()){
                System.out.println(rs.getString("full"));
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            rs.close();
            statement.close();
            connection.close();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement !=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
            if(connection !=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
