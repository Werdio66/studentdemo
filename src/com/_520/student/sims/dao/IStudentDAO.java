package com._520.student.sims.dao;

import com._520.student.sims.domain.Student;

import java.util.List;

//student接口，规范
public interface IStudentDAO {
    /**
     * 增加操作
     * @param obj   要增加的对象
     */
    void save(Student obj);

    /**
     * 修改
     * @param obj   修改的内容
     * @param id    修改对象的id
     */
    void upDate(Student obj, Long id);

    /**
     * 删除
     * @param id    删除对象的id
     */
    void delete(Long id);

    /**
     * 查询单个对象
     * @param id    查询对象的id
     * @return      返回这个学生对象
     */
    Student get(Long id);

    /**
     * 查询所有学生信息
     * @return      所有list集合存放学生信息
     */
    List<Student> getAll();
}
