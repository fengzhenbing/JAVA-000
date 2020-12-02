import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

/**
 * @description: 32s
 * @author: fzb
 * @create: 2020-11-18 16:57
 */
public class HikariBatchInsert {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/my_shop?useSSL=false&rewriteBatchedStatements=true&autoReconnect=true&allowMultiQueries=true&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "";
    static final String sql = "INSERT INTO `my_shop`.`order`" +
            " ( `member_id`,`order_sn`,`create_time`,`member_username`,`total_amount`,`pay_amount`,`freight_amount`,`pay_type`,`source_type`,`status`,`order_type`,`auto_confirm_day`,`note`,`confirm_status`,`delete_status`,`use_integration`,`payment_time`,`modify_time` ) values ";
    static final int batchCnt = Runtime.getRuntime().availableProcessors()*2;
    static final int batchSize = 1000000/batchCnt;

    private HikariDataSource dataSource;

    public static void main(String[] args) throws SQLException {
        HikariBatchInsert hikariBatchInsert = new HikariBatchInsert();
        hikariBatchInsert.initDataSource();
        Long startTime = System.currentTimeMillis();
        for (int batchNo = 0; batchNo < batchCnt; batchNo++) {
            hikariBatchInsert.getConnection();

            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = hikariBatchInsert.getConnection();
                connection.setAutoCommit(false);
                StringBuilder sb = new StringBuilder().append(sql);
                for (int i = 0; i < batchSize; i++) {
                    Timestamp now = new Timestamp(System.currentTimeMillis());
                    sb.append("(1,")
                            .append("'order_1',")
                            .append("'" + now + "',")
                            .append("'user_1',")
                            .append("100.00,")
                            .append("100.00,")
                            .append("10.00,")
                            .append("1,")
                            .append("1,")
                            .append("1,")
                            .append("1,")
                            .append("15,")
                            .append("'note_1',")
                            .append("1,")
                            .append("0,")
                            .append("10,")
                            .append("'" + now + "',")
                            .append("'" + now + "')");
                    if (i < batchSize - 1) {
                        sb.append(",");
                    }

                }
                // System.out.println(sb.toString());
                ps = connection.prepareStatement(sb.toString());
                ps.addBatch();
                ps.executeBatch();
                ps.clearBatch();
                connection.commit();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                 if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }

        Long endTime = System.currentTimeMillis();
        System.out.println("耗时："+(endTime-startTime)/1000);

        hikariBatchInsert.closeConnection();
    }



    public void initDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(JDBC_DRIVER);
        config.setJdbcUrl(DB_URL);
        config.setUsername(USER);
        config.setPassword(PASS);
        config.setMaximumPoolSize(50);
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
