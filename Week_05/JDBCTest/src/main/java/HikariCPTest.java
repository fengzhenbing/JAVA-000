import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

/**
 * @description:
 * @author: fzb
 * @create: 2020-11-18 16:57
 */
public class HikariCPTest {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://106.14.76.240:3306/demo?useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&allowMultiQueries=true&serverTimezone=UTC";
    static final String USER = "mk";
    static final String PASS = "mokeTestEnv01";

    private HikariDataSource dataSource;

    public static void main(String[] args) throws SQLException {
        HikariCPTest hikariCPTest = new HikariCPTest();
        hikariCPTest.initDataSource();

        ResultSet rs = hikariCPTest.execQuery("select id,name,full from `demo`.`address` where id = 2");
        while (rs.next()){
            System.out.println(rs.getString("full"));
        }
        rs.close();

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hikariCPTest.closeConnection();
    }

    public ResultSet execQuery(String sql) {
        ResultSet rs = null;
        try {
            Statement statement = getConnection().createStatement();
            statement.execute(sql);
            rs = statement.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void execInsert(String sql) {
        try {
            Statement statement = getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(JDBC_DRIVER);
        config.setJdbcUrl(DB_URL);
        config.setUsername(USER);
        config.setPassword(PASS);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection() {
        if (dataSource != null) {
            dataSource.close();
        }
    }


}
