package com.mengzhidu.test;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import java.util.Properties;

/**
 * 测试工具类
 * 这里只是掩饰如何去实现类似情形下的单测，因此实现的比较简单
 */
public class TestUtil {
    private static  BasicDataSource dataSource;

    /**
     * 直接实例化数据源
     */
    static {
        Properties properties = new Properties();
        properties.setProperty("username", "sa");
        properties.setProperty("password", "");
        properties.setProperty("driverClassName", "org.h2.Driver");
        properties.setProperty("url", "jdbc:h2:/Users/xinguimeng/test/h2");
        properties.setProperty("validationQuery", "SELECT 1");
        properties.setProperty("maxTotal", "10");
        properties.setProperty("connectionInitSqls", "SET NAMES 'utf8mb4'");
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据源
     * @return 获取h2database对应的数据源
     */
    public static BasicDataSource getDataSource() {
        return dataSource;
    }

    /**
     * 返回创建t_user表的SQL语句
     * @return 建表语句
     */
    public static String getCreateUserSql() {
        return "CREATE TABLE t_user(id int primary key auto_increment, name varchar(32))";
    }

    /**
     * 返回销毁t_user表的SQL语句
     * @return 销毁语句
     */
    public static String getDropUserSql() {
        return "DROP TABLE t_user";
    }
}
