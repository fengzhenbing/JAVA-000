import java.math.BigDecimal;
import java.sql.*;

/**
 * JdbcBatchInsert 36s
 *
 * @author: fengzhenbing
 */
public class JdbcBatchInsert02 {
    /**
     * MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
     */
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/my_shop?useSSL=false&&rewriteBatchedStatements=true&autoReconnect=true&allowMultiQueries=true&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "";
    static final String sql = "INSERT INTO `my_shop`.`order`" +
            " ( `member_id`,`order_sn`,`create_time`,`member_username`,`total_amount`,`pay_amount`,`freight_amount`,`pay_type`,`source_type`,`status`,`order_type`,`auto_confirm_day`,`note`,`confirm_status`,`delete_status`,`use_integration`,`payment_time`,`modify_time` ) " +
            " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connection.setAutoCommit(false);
            Long startTime = System.currentTimeMillis();
            ps = connection.prepareStatement(sql);
            for(int batchNo =0;batchNo<100;batchNo++){
                for (int i = 0; i < 10000; i++) {
                    int index = 1;
                    Timestamp now = new Timestamp(System.currentTimeMillis());
                    ps.setInt(index++, 1);
                    ps.setString(index++, "order_" + 1);
                    ps.setTimestamp(index++, now);
                    ps.setString(index++, "user" + 1);
                    ps.setBigDecimal(index++, new BigDecimal(100.00));
                    ps.setBigDecimal(index++, new BigDecimal(100.00));
                    ps.setBigDecimal(index++, new BigDecimal(10.00));
                    ps.setInt(index++, 1);
                    ps.setInt(index++, 1);
                    ps.setInt(index++, 1);
                    ps.setInt(index++, 1);
                    ps.setInt(index++, 15);
                    ps.setString(index++, "note" + 1);
                    ps.setInt(index++, 1);
                    ps.setInt(index++, 0);
                    ps.setInt(index++, 10);
                    ps.setTimestamp(index++, now);
                    ps.setTimestamp(index++, now);
                    ps.addBatch();
                }
                ps.executeBatch();
                ps.clearBatch();
                connection.commit();
             }

            Long endTime = System.currentTimeMillis();

            System.out.println("耗时："+(endTime-startTime)/1000);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
