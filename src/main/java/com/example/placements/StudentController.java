package com.example.placements;

import Exception.InvalidDataExc;
import dao.StudentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.Student;
import service.StudentService;

public class StudentController {

    @FXML private TextField idField, nameField, emailField, phoneField, cgpaField, branchField, gradYearField;
    @FXML private Label statusLabel;
    @FXML private TableView<Student> table;
    @FXML private TableColumn<Student, Integer> colId;
    @FXML private TableColumn<Student, String> colName, colEmail, colPhone, colBranch;
    @FXML private TableColumn<Student, Double> colCgpa;
    @FXML private TableColumn<Student, Integer> colGradYear;

    private final StudentService service = new StudentService();
    private final StudentDAO dao = new StudentDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getID()).asObject());
        colName.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName()));
        colEmail.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getEmail()));
        colPhone.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getPhone()));
        colCgpa.setCellValueFactory(data ->
                new SimpleDoubleProperty(data.getValue().getCgpa()).asObject());
        colBranch.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getBranch()));
        colGradYear.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getGradYear()).asObject());

        table.setOnMouseClicked(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) fillForm(selected);
        });

        handleRefresh();
    }

    private void fillForm(Student s) {
        idField.setText(String.valueOf(s.getID()));
        nameField.setText(s.getName());
        emailField.setText(s.getEmail());
        phoneField.setText(s.getPhone());
        cgpaField.setText(String.valueOf(s.getCgpa()));
        branchField.setText(s.getBranch());
        gradYearField.setText(String.valueOf(s.getGradYear()));
    }

    private void clearForm() {
        idField.clear(); nameField.clear(); emailField.clear();
        phoneField.clear(); cgpaField.clear(); branchField.clear(); gradYearField.clear();
    }

    @FXML
    private void handleAdd() {
        try {
            service.registerStudent(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    emailField.getText(),
                    phoneField.getText(),
                    Double.parseDouble(cgpaField.getText()),
                    branchField.getText(),
                    Integer.parseInt(gradYearField.getText())
            );
            statusLabel.setText("Student added.");
            clearForm();
            handleRefresh();
        } catch (InvalidDataExc e) {
            statusLabel.setText(e.getMessage());
        } catch (NumberFormatException e) {
            statusLabel.setText("ID, CGPA and Grad Year must be numbers.");
        }
    }

    @FXML
    private void handleUpdate() {
        try {
            service.updateStudentD(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    emailField.getText(),
                    phoneField.getText(),
                    Double.parseDouble(cgpaField.getText()),
                    branchField.getText(),
                    Integer.parseInt(gradYearField.getText())
            );
            statusLabel.setText("Student updated.");
            clearForm();
            handleRefresh();
        } catch (InvalidDataExc e) {
            statusLabel.setText(e.getMessage());
        } catch (NumberFormatException e) {
            statusLabel.setText("ID, CGPA and Grad Year must be numbers.");
        }
    }

    @FXML
    private void handleDelete() {
        try {
            service.deleteStudent(Integer.parseInt(idField.getText()));
            statusLabel.setText("Student deleted.");
            clearForm();
            handleRefresh();
        } catch (InvalidDataExc e) {
            statusLabel.setText(e.getMessage());
        } catch (NumberFormatException e) {
            statusLabel.setText("ID must be a number.");
        }
    }

    @FXML
    private void handleRefresh() {
        try {
            ObservableList<Student> data = FXCollections.observableArrayList(dao.getAllStudents());
            table.setItems(data);
        } catch (InvalidDataExc e) {
            statusLabel.setText(e.getMessage());
        }
    }
}