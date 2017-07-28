package com.mengzhidu.test;

import junit.framework.TestCase;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;

/**
 * 对用户业务的测试
 * 这里为了做到每个测试用例互不干扰，这里有如下约定:
 * 1.不统一初始化数据，每个测试用例都初始化自己的数据
 * 很多时候统一初始化数据会更方便，这个要看测试的场景而定
 * 而初始化数据的层次上，可以是在测试启动时就初始化数据
 * 也可以在某个类里面进行初始化数据，这个看具体要测试的内容
 * 2.不提交事务，每次都进行rollback，确保不会产生干扰数据
 * 这一点在统计类的测试中比较重要，对于非统计类通常影响不大
 */
public class TestUser extends TestCase {

    private QueryRunner queryRunner;

    private User user;

    private Connection connection;


    /**
     * 初始化操作
     * @throws Exception 抛出初始化时未处理的异常
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
        queryRunner = new QueryRunner();
        connection = TestUtil.getDataSource().getConnection();
        queryRunner.update(connection, TestUtil.getCreateUserSql());

        // 实例化User类
        // 并且用H2的数据库连接来代替MySQL的数据库连接
        user = new User();
        user.setQueryRunner(queryRunner);
        user.setConnection(connection);
    }

    /**
     * 测试获取最大值的功能
     * @throws Exception 抛出未捕获的异常
     */
    @Test
    public void testGetMaxId() throws Exception {
        connection.setAutoCommit(false);
        queryRunner.update(connection,"INSERT INTO t_user VALUES (3, 'Jack')");
        queryRunner.update(connection, "INSERT INTO t_user VALUES (9, 'Liya')");
        assertEquals(9, user.getMaxId());
        queryRunner.update(connection, "INSERT INTO t_user VALUES (222, 'Luka')");
        assertEquals(222, user.getMaxId());
        connection.rollback();
    }

    /**
     * 测试插入用户数据的功能
     * 这里需要确保用户数据被正确的插入一条
     * 因此这里测试的要点有如下几个:
     * 1.用户数据被插入了，不会插入null或者不相干的数据
     * 2.只插入了一条用户数据，而不会插入两条或者更多数据
     * 这里对一些临界情况没有做太多的测试，在实际的测试中应该再次进行完善
     * @throws Exception
     */
    @Test
    public void testSaveUserInfo() throws Exception {
        connection.setAutoCommit(false);
        Integer maxid = queryRunner.query(connection, "SELECT max(id) FROM t_user", new ScalarHandler<Integer>());
        maxid = maxid != null ? maxid : 0;
        String name = "XinXing";
        assertEquals(1, user.saveUserInfo(name));
        Long count = queryRunner.query(connection, "SELECT count(1) FROM t_user WHERE id > ? AND name = ?",
                new ScalarHandler<Long>(), maxid, name);
        assertEquals(count, new Long(1));
        connection.rollback();
    }

    /**
     * 清理操作
     * @throws Exception 抛出清理时未处理的异常
     */
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        queryRunner.update(connection, TestUtil.getDropUserSql());
        if (connection != null) {
            connection.close();
        }
    }
}
