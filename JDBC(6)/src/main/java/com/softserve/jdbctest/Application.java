package com.softserve.jdbctest;

import java.sql.*;
import java.util.Scanner;

public class Application {
    private static final String url = "jdbc:mysql://localhost:3306/db_jdbc";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet rs = null;

    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, password);

            statement = connection.createStatement();

            boolean bool = true;

            while (bool) {
                System.out.println("\n1 - READ DATA FROM THE DB");
                System.out.println("\n2 - INSERT SOME DATA INTO THE DB");
                System.out.println("\n3 - UPDATE SOME DATA FROM THE DB");
                System.out.println("\n4 - DELETE SOME DATA FROM THE DB");

                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        readData();
                        break;
                    case 2:
                        System.out.println("\n1 - INSERT DATA INTO THE University Table");
                        System.out.println("\n2 - INSERT DATA INTO THE Student Table");
                        System.out.println("\n3 - INSERT DATA INTO THE Commission Table");
                        int choice2 = scanner.nextInt();
                        switch (choice2) {
                            case 1:
                                insertDataUniversity();
                                break;
                            case 2:
                                insertDataStudent();
                                break;
                            case 3:
                                insertDataCommission();
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("\n1 - UPDATE DATA FROM THE University Table");
                        System.out.println("\n2 - UPDATE DATA FROM THE Student Table");
                        System.out.println("\n3 - UPDATE DATA FROM THE Commission Table");
                        int choice3 = scanner.nextInt();
                        switch (choice3) {
                            case 1:
                                updateDataUniversity();
                                break;
                            case 2:
                                updateDataStudent();
                                break;
                            case 3:
                                updateDataCommission();
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("\n1 - DELETE DATA FROM THE University Table");
                        System.out.println("\n2 - DELETE DATA FROM THE Student Table");
                        System.out.println("\n3 - DELETE DATA FROM THE Commission Table");
                        int choice4 = scanner.nextInt();
                        switch (choice4) {
                            case 1:
                                deleteDataUniversity();
                                break;
                            case 2:
                                deleteDataStudent();
                                break;
                            case 3:
                                deleteDataCommission();
                                break;
                        }
                        break;
                    default:
                        System.out.println("BYE-BYE");
                        bool = false;
                        break;
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MS SQL Server Driver is not loaded");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
            }
            if (statement != null) try {
                statement.close();
            } catch (SQLException e) {
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }

    private static void readData() throws SQLException {
        rs = statement.executeQuery("SELECT COUNT(*) FROM student");

        while (rs.next()) {
            int count = rs.getInt(1);

            System.out.format("\ncount: %d\n", count);
        }

        rs = statement.executeQuery("SELECT * FROM student");

        System.out.format("\nTable Student --------------------\n");
        System.out.format("%3s %-12s %-12s %-10s %s %s\n", "id_student", "surname", "name", "commission_number", "university_name", "email");
        while (rs.next()) {
            int id_student = rs.getInt("id_student");
            String surname = rs.getString("surname");
            String name = rs.getString("name");
            String commission_number = rs.getString("commission_number");
            String university_name = rs.getString("university_name");
            String email = rs.getString("email");

            System.out.format("%3d %-12s %-12s %-10s %s %s\n", id_student, surname, name, commission_number, university_name, email);
        }

        rs = statement.executeQuery("SELECT * FROM Commission");

        System.out.format("\nTable Commission --------------------\n");
        System.out.format("%3s %-18s %-18s %s\n", "id_commission", "name", "student", "teacher");
        while (rs.next()) {
            int id_commission = rs.getInt("id_commission");
            String name = rs.getString("name");
            String student = rs.getString("student");
            String teacher = rs.getString("teacher");

            System.out.format("%3d %-18s %-18s %s\n", id_commission, name, student, teacher);
        }

        rs = statement.executeQuery("SELECT * FROM University");

        System.out.format("\nTable University --------------------\n");
        System.out.format("%s\n", "University");
        while (rs.next()) {
            String university_name = rs.getString("university_name");
            System.out.format("%s\n", university_name);
        }

        String query = "Select " +
                "(SELECT surname FROM student WHERE id_student=P.id_student) AS surname, " +
                "(SELECT name FROM Commission WHERE id_commission=P.id_commission1) AS name " +
                "FROM Teacher AS P";
        rs = statement.executeQuery(query);

        System.out.format("\nJoining Table Teacher --------------------\n");
        System.out.format("%-15s %s\n", "surname", "name");
        while (rs.next()) {
            String surname = rs.getString("surname");
            String name = rs.getString("name");

            System.out.format("%-15s %s\n", surname, name);
        }
    }

    private static void updateDataUniversity() throws SQLException {
        rs = statement.executeQuery("SET foreign_key_checks = 0; ");
        Scanner input = new Scanner(System.in);
        System.out.println("Input name university what you want to update: ");
        String universityName = input.next();
        System.out.println("Input new name university for %s: " + universityName);
        String univercityNewName = input.next();

        statement.execute("UPDATE University SET university_name='" + univercityNewName + "' WHERE university_name='" + universityName + "';");
        rs = statement.executeQuery("SET foreign_key_checks = 1; ");
    }

    private static void updateDataCommission() throws SQLException {
        rs = statement.executeQuery("SET foreign_key_checks = 0; ");
        Scanner input = new Scanner(System.in);

        System.out.println("Input a commission id: ");
        String commissionId = input.next();

        System.out.println("Input a new commission name: ");
        String newCommissionName = input.next();
        System.out.println("Input a new student: ");
        String newStudent = input.next();
        System.out.println("Input a new teacher: ");
        String newTeacher = input.next();

        statement.execute("UPDATE Commission SET name='" + newCommissionName + "', student='" + newStudent + "', teacher='" + newTeacher + "' WHERE id_commission='" + commissionId + "';");
        rs = statement.executeQuery("SET foreign_key_checks = 1; ");
    }

    private static void updateDataStudent() throws SQLException {
        rs = statement.executeQuery("SET foreign_key_checks = 0; ");
        Scanner input = new Scanner(System.in);

        System.out.println("Input a student id: ");
        String studentId = input.next();

        System.out.println("Input a new student surname: ");
        String newStudentSurname = input.next();
        System.out.println("Input a new student name: ");
        String newStudentName = input.next();
        System.out.println("Input a new student commission number: ");
        String newStudentCommissionNumber = input.next();
        System.out.println("Input a new student university name: ");
        String newStudentUniversityName = input.next();
        System.out.println("Input a new student email: ");
        String newStudentEmail = input.next();

        statement.execute("UPDATE Student SET surname='" + newStudentSurname + "', name='" + newStudentName + "', commission_number='" + newStudentCommissionNumber + "', university_name='" + newStudentUniversityName + "', email='" + newStudentEmail + "' WHERE id_student='" + studentId + "';");
        rs = statement.executeQuery("SET foreign_key_checks = 1; ");
    }

    private static void insertDataUniversity() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a new university name: ");
        String newUniversity = input.next();

        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT University VALUES (?)");
        preparedStatement.setString(1, newUniversity);
        int n = preparedStatement.executeUpdate();
        System.out.println("Count rows that inserted: " + n);
    }

    private static void insertDataStudent() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a new student id: ");
        String newStudentId = input.next();
        System.out.println("Input a new student surname: ");
        String newStudentSurname = input.next();
        System.out.println("Input a new student name: ");
        String newStudentName = input.next();
        System.out.println("Input a new student commission number: ");
        String newStudentCommissionNumber = input.next();
        System.out.println("Input a new student university name: ");
        String newStudentUniversityName = input.next();
        System.out.println("Input a new student email: ");
        String newStudentEmail = input.next();

        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT Student VALUES (?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, newStudentId);
        preparedStatement.setString(2, newStudentSurname);
        preparedStatement.setString(3, newStudentName);
        preparedStatement.setString(4, newStudentCommissionNumber);
        preparedStatement.setString(5, newStudentUniversityName);
        preparedStatement.setString(6, newStudentEmail);
        int n = preparedStatement.executeUpdate();
        System.out.println("Count rows that inserted: " + n);
    }

    private static void insertDataCommission() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a new commission id: ");
        String newCommissionId = input.next();
        System.out.println("Input a new commission name: ");
        String newCommissionName = input.next();
        System.out.println("Input a new student: ");
        String newStudent = input.next();
        System.out.println("Input a new teacher: ");
        String newTeacher = input.next();

        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT Commission VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, newCommissionId);
        preparedStatement.setString(2, newCommissionName);
        preparedStatement.setString(3, newStudent);
        preparedStatement.setString(4, newTeacher);

        int n = preparedStatement.executeUpdate();
        System.out.println("Count rows that inserted: " + n);
    }

    private static void deleteDataUniversity() throws SQLException {
        rs = statement.executeQuery("SET foreign_key_checks = 0; ");
        Scanner input = new Scanner(System.in);
        System.out.println("Input a name university for delete: ");
        String university = input.next();

        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE FROM University WHERE university_name=?");
        preparedStatement.setString(1, university);
        int n = preparedStatement.executeUpdate();
        rs = statement.executeQuery("SET foreign_key_checks = 1; ");
        System.out.println("Count rows that deleted: " + n);
    }

    private static void deleteDataCommission() throws SQLException {
        rs = statement.executeQuery("SET foreign_key_checks = 0; ");
        Scanner input = new Scanner(System.in);
        System.out.println("Input a commission id for delete: ");
        String commission = input.next();

        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE FROM Commission WHERE id_commission=?");
        preparedStatement.setString(1, commission);
        int n = preparedStatement.executeUpdate();
        rs = statement.executeQuery("SET foreign_key_checks = 1; ");
        System.out.println("Count rows that deleted: " + n);
    }

    private static void deleteDataStudent() throws SQLException {
        rs = statement.executeQuery("SET foreign_key_checks = 0; ");
        Scanner input = new Scanner(System.in);
        System.out.println("Input a student id for delete: ");
        String student = input.next();

        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE FROM Student WHERE id_student=?");
        preparedStatement.setString(1, student);
        int n = preparedStatement.executeUpdate();
        rs = statement.executeQuery("SET foreign_key_checks = 1; ");
        System.out.println("Count rows that deleted: " + n);
    }
}
