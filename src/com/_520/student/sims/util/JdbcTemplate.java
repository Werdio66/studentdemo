package com._520.student.sims.util;

import com._520.student.sims.dao.IResultSetHandler;
import com._520.student.sims.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD模板
 */
public class JdbcTemplate {
    /**
     * 增删改操作         DML
     * @param sql         sql语句
     * @param obj         要设置的参数值
     */
    public static void upDate(String sql, Object ... obj){
        Connection con = null;
        PreparedStatement ps = null;
        try {
            //1.加载注册驱动
            //2.获取连接对象
            con = JdbcUtil.getConnection();
            //3.创建语句对象
            ps = con.prepareStatement(sql);
            //设置参数值
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i+1,obj[i]);
            }
            //4.执行sql语句
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        //5.释放资源
        JdbcUtil.close(con, ps, null);
    }

    /**
     * 查询操作         DQL
     * @param sql       sql语句
     * @param rsh       结果集处理器
     * @param obj       查询的id
     * @param <T>       自定义泛型，由实现类定义数据类型
     * @return          自定义类型
     */
    public static <T>T query(String sql,IResultSetHandler<T> rsh, Object ... obj){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1.加载注册驱动
            //2.获取连接对象
            con = JdbcUtil.getConnection();
            //3.创建语句对象
            ps = con.prepareStatement(sql);
            //设置参数
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i+1,obj[i]);
            }
            //4.执行sql语句
            rs = ps.executeQuery();
            //交给结果集处理器处理
            return rsh.handle(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //5.释放资源
        JdbcUtil.close(con, ps, rs);
        return null;
    }
}
