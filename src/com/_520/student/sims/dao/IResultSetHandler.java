package com._520.student.sims.dao;

import com._520.student.sims.domain.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//结果集处理器规范
public interface IResultSetHandler<T> {
    /**
     * 结果集处理器
     * @param rs        结果集
     * @return          自定义类型
     * @throws Exception
     */
    T handle(ResultSet rs) throws Exception;
}
