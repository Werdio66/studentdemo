import com._520.student.sims.dao.IStudentDAO;
import com._520.student.sims.dao.impl.StudentDAOImpl;
import com._520.student.sims.domain.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StudentDAOImplTest {
    private IStudentDAO studentDAO = new StudentDAOImpl();
    @Test
    public void save() {
        Student stu = studentDAO.get(56L);
        System.out.println(stu);
        stu.setName("高");
        stu.setAge(22);
        studentDAO.save(stu);
        System.out.println(stu);
    }

    @Test
    public void upDate() {
        Student stu = studentDAO.get(65L);
        System.out.println(stu);
        stu.setName("高");
        stu.setAge(22);
        studentDAO.upDate(stu,65L);
        System.out.println(stu);

    }

    @Test
    public void delete() {
        studentDAO.delete(71L);
    }

    @Test
    public void get() {
        System.out.println(studentDAO.get(56L));
    }

    @Test
    public void getAll() {
        List<Student> list = studentDAO.getAll();
        for (Student stu:list
        ) {
            System.out.println(stu.toString());
        }
    }
}