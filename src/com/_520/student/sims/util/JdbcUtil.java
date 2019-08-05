package com._520.student.sims.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class JdbcUtil {
    //声明资源文件对象
    private static Properties p = new Properties();
    private JdbcUtil() {
    }

    /**
     * 获取连接对象
     * @return     连接对象
     */
    public static Connection getConnection(){
        //1.加载注册驱动
        try {
            //加载资源文件
            InputStream inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("druid.properties");
            p.load(inputStream);
            Class.forName(p.getProperty("diverClassName"));
            //2.获取连接对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(p);
            return dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭资源
     * @param con       连接对象
     * @param ps        语句对象
     * @param rs        结果集对象
     */
    public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (con != null)
                        con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
