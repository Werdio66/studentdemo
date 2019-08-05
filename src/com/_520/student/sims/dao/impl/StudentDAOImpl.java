package com._520.student.sims.dao.impl;

import com._520.student.sims.dao.IResultSetHandler;
import com._520.student.sims.dao.IStudentDAO;
import com._520.student.sims.domain.Student;
import com._520.student.sims.util.JdbcTemplate;
import com._520.student.sims.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//student实现类
public class StudentDAOImpl implements IStudentDAO {

    @Override
    public void save(Student obj) {
        String sql = ("INSERT INTO t_student " +
                "(name,age,math,computer,english) VALUES (?,?,?,?,?)");
        Object[] params = new Object[]{obj.getName(),obj.getAge(),obj.getMath(),
                obj.getComputer(),obj.getEnglish()};
        JdbcTemplate.upDate(sql,params);
    }

    @Override
    public void upDate(Student obj, Long id) {
        String sql = "UPDATE t_student SET name = ?,age = ?,math = ?," +
                "computer = ?,english = ? WHERE id = ?";
        Object[] params = new Object[]{obj.getName(),obj.getAge(),obj.getMath(),
                obj.getComputer(),obj.getEnglish(),id};
        JdbcTemplate.upDate(sql,params);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM t_student WHERE id = ?";
        JdbcTemplate.upDate(sql,id);
    }

    @Override
    public Student get(Long id) {
        String sql = "SELECT * FROM t_student WHERE id = ?";
        List<Student> list = JdbcTemplate.query(sql,new StudentResultSetHandler(),id);
        return list.size() == 1 ? list.get(0) : null;
    }

    @Override
    public List<Student> getAll() {
        String sql = "SELECT * FROM t_student";
        return JdbcTemplate.query(sql,new StudentResultSetHandler());
    }

    /**
     * student对象的结果集处理器
     */
    class StudentResultSetHandler implements IResultSetHandler<List<Student>>{

        @Override
        public List<Student> handle(ResultSet rs) throws Exception {
            List<Student> list = new ArrayList<>();
            while (rs.next()){
                Student stu = new Student();
                list.add(stu);
                stu.setId(rs.getLong("id"));
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("age"));
                stu.setMath(rs.getInt("math"));
                stu.setComputer(rs.getInt("computer"));
                stu.setEnglish(rs.getInt("english"));
                stu.setAverage(stu.getAverage());
            }
            return list;
        }
    }
}
