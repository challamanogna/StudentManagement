package service;
import model.Student;
import Exception.InvalidDataExc;
import dao.StudentDAO;
import java.util.*;
public class StudentService {
    private StudentDAO dao = new StudentDAO();
 public Student registerStudent(int student_ID,String name,String email,String phone,double CGPA,String branch,int gradYear) throws InvalidDataExc{
     Student info = new Student(student_ID,name,email,phone,CGPA,branch,gradYear);
       dao.addstudent(info);
        return info;
 }
   public void displayStudents() throws InvalidDataExc {
     ArrayList<Student> students = dao.getAllStudents();
       for (Student student : students) {
           student.displayProfile();
       }
   }
     public Student findById(int student_ID) throws InvalidDataExc{
         return dao.findById(student_ID);
       }
    public void updateStudentD(int student_ID, String name,
                               String email,
                               String phone, double newCGPA,String branch,
                               int gradYear)
            throws InvalidDataExc {
        Student student = dao.findById(student_ID);
        if (student != null) {
            student.setName(name);
            student.setEmail(email);
            student.setPhone(phone);
            student.setCgpa(newCGPA);
            student.setBranch(branch);
            student.setGradYear(gradYear);

            dao.updateStudent(student);
            System.out.println("Student details updated successfully");
        } else {
            System.out.println("Student not found");
        }
    }
    public void deleteStudent(int student_ID) throws InvalidDataExc{
     Student student = dao.findById(student_ID);
        if(student!=null){
            dao.removeStudent(student);
            System.out.println("Student deleted successfully");
        }
        else{
            System.out.println("Student not found");
        }


    }

}
