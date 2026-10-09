package dao;

import Exception.InvalidDataExc;
import model.Student;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    public void addstudent(Student student) throws InvalidDataExc {
        String sql = "insert into student(student_ID, name, email, phone, cgpa, branch, gradYear) values(?,?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, student.getID());
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getPhone());
            ps.setDouble(5, student.getCgpa());
            ps.setString(6, student.getBranch());
            ps.setInt(7, student.getGradYear());
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new InvalidDataExc("A student with that ID already exists.");
        } catch (SQLException e) {
            throw new InvalidDataExc("Database error: " + e.getMessage());
        }
    }

    public Student findById(int student_ID) throws InvalidDataExc {
        String sql = "SELECT * FROM student WHERE student_ID = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, student_ID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("student_ID"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getDouble("cgpa"),
                            rs.getString("branch"),
                            rs.getInt("gradYear")
                    );
                }
            }
        } catch (SQLException e) {
            throw new InvalidDataExc("Database error: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Student> getAllStudents() throws InvalidDataExc {
        ArrayList<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("student_ID"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDouble("cgpa"),
                        rs.getString("branch"),
                        rs.getInt("gradYear")
                ));
            }
        } catch (SQLException e) {
            throw new InvalidDataExc("Database error: " + e.getMessage());
        }
        return students;
    }

    public void removeStudent(Student student) throws InvalidDataExc {
        String sql = "DELETE FROM student WHERE student_ID = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, student.getID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidDataExc("Database error: " + e.getMessage());
        }
    }

    public void updateStudent(Student student) throws InvalidDataExc {
        String sql = "UPDATE student SET name = ?, email = ?, phone = ?, "
                + "cgpa = ?, branch = ?, gradYear = ? WHERE student_ID = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getPhone());
            ps.setDouble(4, student.getCgpa());
            ps.setString(5, student.getBranch());
            ps.setInt(6, student.getGradYear());
            ps.setInt(7, student.getID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidDataExc("Database error: " + e.getMessage());
        }
    }
}