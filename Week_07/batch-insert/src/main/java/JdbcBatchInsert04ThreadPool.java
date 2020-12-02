import java.sql.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * JdbcBatchInsert04ThreadPool   15s
 *
 * @author: fengzhenbing
 */
public class JdbcBatchInsert04ThreadPool {
    /**
     * MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
     */
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/my_shop?useSSL=false&&rewriteBatchedStatements=true&autoReconnect=true&allowMultiQueries=true&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "";
    static final String sql = "INSERT INTO `my_shop`.`order`" +
            " ( `member_id`,`order_sn`,`create_time`,`member_username`,`total_amount`,`pay_amount`,`freight_amount`,`pay_type`,`source_type`,`status`,`order_type`,`auto_confirm_day`,`note`,`confirm_status`,`delete_status`,`use_integration`,`payment_time`,`modify_time` ) values ";
    static final int batchCnt = Runtime.getRuntime().availableProcessors()*2;
    static final int batchSize = 1000000/batchCnt;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(batchCnt);
        CountDownLatch latch = new CountDownLatch(batchCnt);

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Long startTime = System.currentTimeMillis();

        for (int batchNo = 0; batchNo < batchCnt; batchNo++) {

            executorService.execute(() -> {
                Connection connection = null;
                PreparedStatement ps = null;
                try {

                    connection = DriverManager.getConnection(DB_URL, USER, PASS);
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
                    latch.countDown();
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


            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long endTime = System.currentTimeMillis();

        System.out.println("耗时：" + (endTime - startTime) / 1000);
        executorService.shutdown();

    }
}
