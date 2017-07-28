package com.mengzhidu.test;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 为了更好的实现单测，这里的依赖项都是应该被注入的
 * 它不应该自己去新建依赖项，这样代码更加耦合，难以测试和重构
 * 而且连接的事务应该由上层开启，它只负责业务，不负责事务的开启和关闭
 * 它也不必负责关闭连接，是否使用连接池、连接的生命周期都是和它无关的
 * 不管是否基于Spring等拥有该类设计理念的框架，我们都应该有类似的实现机制
 */
public class User {
    private QueryRunner queryRunner;

    private Connection connection;

    /**
     * 设置queryRunner
     * @param queryRunner 查询执行器
     */
    public void setQueryRunner(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    /**
     * 设置connection
     * @param connection 数据库连接
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * 获取t_user表中的最大的ID的值
     * 如果还没有数据，则返回0
     * @return 返回的t_user表中的id字段的最大值
     * @throws SQLException 可能存在的数据库操作异常
     */
    public int getMaxId() throws SQLException {
        String sql = "SELECT max(id) FROM t_user";
        Integer result = queryRunner.query(connection, sql, new ScalarHandler<Integer>());
        return result != null ? result : 0;
    }

    /**
     * 向t_user表中插入数据
     * 这里使用的是自增的id
     * @param name 插入的用户名
     * @return 返回插入的行数
     * @throws SQLException 可能存在的数据库操作异常
     */
    public int saveUserInfo(String name) throws  SQLException {
        String sql = "INSERT INTO t_user (name) VALUES (?)";
        Integer result = queryRunner.insert(connection, sql, new ScalarHandler<Integer>(), name);
        return result != null ? result : 0;
    }
}
