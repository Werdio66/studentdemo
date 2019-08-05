package com._520.student.sims.web.servlet;

import com._520.student.sims.dao.IStudentDAO;
import com._520.student.sims.dao.impl.StudentDAOImpl;
import com._520.student.sims.domain.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    IStudentDAO studentDAO;
    Student stu;
    @Override
    public void init() throws ServletException {
        studentDAO = new StudentDAOImpl();
        stu = new Student();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //接收请求参数
        String cmd = req.getParameter("cmd");
        if ("save".equals(cmd)){
            this.saveOrUpdate(req,resp);
        }else if ("edit".equals(cmd)){
            this.edit(req,resp);
        }else if ("delete".equals(cmd)){
            this.delete(req,resp);
        }else {
            this.list(req,resp);
        }
    }
    //显示学生列表
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收请求参数，封装成对象

        //2.处理业务
        List<Student> list = studentDAO.getAll();
        System.out.println(list);
        req.setAttribute("students",list);
        //3.控制界面跳转
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req,resp);
    }
    //编辑
    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        System.out.println("edit sid = " + sid);
        if (isEmply(sid)){
            Long id = Long.valueOf(sid);
            stu = studentDAO.get(id);
            System.out.println(stu);
            req.setAttribute("student",stu);
        }
        //3.控制界面跳转
        req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req,resp);
    }
    //删除学生信息
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收请求参数，封装成对象
        String id = req.getParameter("id");
        //2.处理业务
        studentDAO.delete(Long.valueOf(id));
        //3.控制界面跳转
        resp.sendRedirect("/student");
    }
    //增加或修改学生信息
    protected void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收请求参数，封装成对象
        String sid = req.getParameter("id");
        stu = getStu(req);
        //2.处理业务
        if (isEmply(sid)){
            Long id = Long.valueOf(sid);
            studentDAO.upDate(stu,id);
        }else {
            studentDAO.save(stu);
        }
        //3.控制界面跳转
        resp.sendRedirect("/student");
    }
    public static boolean isEmply(String id){
        if ("".equals(id) || id == null){
            return false;
        }
        return true;
    }
    public Student getStu(HttpServletRequest req){
        String name = req.getParameter("name");
        Integer age = Integer.valueOf(req.getParameter("age"));
        Integer math = Integer.valueOf(req.getParameter("math"));
        Integer computer = Integer.valueOf(req.getParameter("computer"));
        Integer english = Integer.valueOf(req.getParameter("english"));
        //2.处理业务
        stu.setName(name);
        stu.setEnglish(english);
        stu.setComputer(computer);
        stu.setMath(math);
        stu.setAge(age);
        return stu;
    }
}
