import java.sql.*;

/**
 * @description:
 * @author: fzb
 * @create: 2020-11-18 15:45
 */
public class JdbcTest {
    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://106.14.76.240:3306/demo?useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&allowMultiQueries=true&serverTimezone=UTC";
    static final String USER = "mk";
    static final String PASS = "mokeTestEnv01";

    private Connection connection;
    private Statement statement;

    public static void main(String[] args) throws SQLException {
        JdbcTest jdbcTest = new JdbcTest();
        jdbcTest.initConnection();
        ResultSet rs =jdbcTest.execQuery("select id,name,full from `demo`.`address` where id = 2");
        while (rs.next()){
            System.out.println(rs.getString("full"));
        }
        rs.close();
        jdbcTest.closeConnection();
    }

    public ResultSet execQuery(String sql){
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            rs = statement.getResultSet();
         } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void execInsert(String sql){
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }

    public Connection initConnection(){
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(){
        if(statement !=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
