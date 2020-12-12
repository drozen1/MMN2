package HW2;

import HW2.business.*;
import HW2.data.DBConnector;

import java.sql.*;

import java.util.ArrayList;

import static HW2.business.ReturnValue.*;


public class Solution {
    public static void createTables() {
        InitialState.createInitialState();
        //create your tables here
        create_test_table();
        create_student_table();
        create_supervisor_table();
        create_takes_table();
        create_oversees_table();
        create_supervisor_and_oversees();
        create_oversees_and_takes();
        create_student_and_creditpoints();
        create_takes_and_students();
        create_takes_and_students_and_tests();
        create_takes_and_tests();
    }


    private static void create_takes_and_tests() {

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("CREATE VIEW Takes_and_tests as\n" +
                    "SELECT B.id AS testid, B.semester, B.time,B.room,B.day, B.credit_points, A.studentid \n" +
                    "FROM Takes AS A INNER JOIN Test AS B ON A.testid = B.id AND A.semester = B.semester");

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return;
    }

    private static void create_takes_and_students() {

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("CREATE VIEW Takes_and_students as\n" +
                    "SELECT S.id AS studentid, S.faculty, S.student_name, S.credit_points, T.semester, T.testid \n" +
                    "FROM Takes AS T INNER JOIN Student AS S ON T.studentid = S.id");

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return;
    }

    private static void create_takes_and_students_and_tests() {

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("CREATE VIEW Takes_and_students_and_tests as\n" +
                    "SELECT A.studentid, A.faculty, A.student_name, A.credit_points AS student_credit_points, A.semester, A.testid , B.credit_points \n" +
                    "FROM Takes_and_students AS A INNER JOIN Test AS B ON A.testid = B.id AND A.semester = B.semester");

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return;
    }

    private static void create_supervisor_and_oversees() {

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("CREATE VIEW Supervisor_and_oversees as\n" +
                    "SELECT S.supervisorid, S.salary, S.supervisor_name, O.testid, O.semester \n" +
                    "FROM Supervisor AS S INNER JOIN Oversees AS O ON S.supervisorid = O.supervisorid");

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return;
    }

    private static void create_oversees_and_takes() {

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("CREATE VIEW Oversees_and_takes as\n" +
                    "SELECT O.supervisorid, O.testid, O.semester, T.studentid \n" +
                    "FROM Oversees AS O INNER JOIN Takes AS T ON O.testid = T.testid AND O.semester=T.semester");

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return;
    }

    private static void create_student_and_creditpoints() {

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("CREATE VIEW Student_and_creditpoints as\n" +
                    "SELECT S.id AS studentid, S.student_name, S.faculty, S.credit_points AS actualcreditpoints, C.Points AS totalcreditpoints\n" +
                    "FROM Student AS S INNER JOIN CreditPoints AS C ON S.faculty = C.faculty ");

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return;
    }

    public static void create_test_table() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("CREATE TABLE IF NOT EXISTS test\n" +
                    "(\n" +
                    "    id integer,\n" +
                    "    semester integer,\n" +
                    "    time integer NOT NULL,\n" +
                    "    room integer NOT NULL,\n" +
                    "    day integer NOT NULL,\n" +
                    "    credit_points integer NOT NULL,\n" +
                    "    PRIMARY KEY (id,semester),\n" +
                    "    CHECK (id > 0),\n" +
                    "    CHECK (room > 0),\n" +
                    "    CHECK (semester > 0),\n" +
                    "    CHECK (semester < 4),\n" +
                    "    CHECK (time > 0),\n" +
                    "    CHECK (time < 4),\n" +
                    "    CHECK (day > 0),\n" +
                    "    CHECK (day < 32),\n" +
                    "    CHECK (credit_points > 0)\n" +
                    ")");
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void create_student_table() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Student\n" +
                    "(\n" +
                    "    id integer NOT NULL,\n" +
                    "    student_name text NOT NULL,\n" +
                    "    faculty text NOT NULL,\n" +
                    "    credit_points integer NOT NULL,\n" +
                    "    PRIMARY KEY (id),\n" +
                    "    CHECK (id > 0),\n" +
                    "    CHECK (credit_points >= 0)\n" +
                    ")");
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void create_supervisor_table() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Supervisor\n" +
                    "(\n" +
                    "    supervisorid integer NOT NULL,\n" +
                    "    supervisor_name text NOT NULL,\n" +
                    "    salary integer NOT NULL,\n" +
                    "    PRIMARY KEY (supervisorid),\n" +
                    "    CHECK (supervisorid > 0),\n" +
                    "    CHECK (salary >= 0)\n" +
                    ")");
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void create_takes_table() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("CREATE TABLE IF NOT EXISTS takes\n" +
                    "(\n" +
                    "    testid integer NOT NULL,\n" +
                    "    studentid integer NOT NULL,\n" +
                    "    semester integer NOT NULL,\n" +
                    "    PRIMARY KEY (studentid, testid, semester),\n" + //CHANGE
                    "    FOREIGN KEY (studentid) \n" +
                    "    REFERENCES Student(id)\n" +
                    "    ON DELETE CASCADE, \n"+
                    "    FOREIGN KEY (testid, semester) \n" +
                    "    REFERENCES Test(id,semester)\n" +
                    "    ON DELETE CASCADE \n"+
                    ")");

            /////////////////////////////////////////////////////////////can be BUG : semester isnot foreign key
            //I check, it works :)

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void create_oversees_table() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Oversees\n" +
            "(\n" +
                    "    testid integer NOT NULL,\n" +
                    "    supervisorid integer NOT NULL,\n" +
                    "    semester integer NOT NULL,\n" +
                    "    PRIMARY KEY (supervisorid, testid, semester),\n" + //CHANGE
                    "    FOREIGN KEY (supervisorid) \n" +
                    "    REFERENCES Supervisor(supervisorid)\n" +
                    "    ON DELETE CASCADE, \n"+
                    "    FOREIGN KEY (testid, semester) \n" +
                    "    REFERENCES Test(id,semester)\n" +
                    "    ON DELETE CASCADE \n"+
                    ")");

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void clearTables() {
        //clear your tables here
        clearTakesTable();
        clearOverseesTable();
        clearTestTable();
        clearStudentTable();
        clearSupervisorTable();
    }

    public static void clearTestTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DELETE FROM Test");

            pstmt.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static void clearStudentTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DELETE FROM Student");

            pstmt.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static void clearSupervisorTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DELETE FROM Supervisor");

            pstmt.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static void clearTakesTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DELETE FROM Takes");

            pstmt.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static void clearOverseesTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DELETE FROM Oversees");

            pstmt.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static void dropTables() {

        //drop your tables here
        drop_supervisor_and_oversees();  /// drop view od oversees+ supervisors
        drop_oversees_and_takes();
        drop_student_and_creditpoints();
        drop_takes_and_students_and_test();
        drop_takes_and_students();
        drop_takes_and_tests();

        InitialState.dropInitialState();
        dropTablesTakes();
        dropTablesOversees();
        dropTablesTest();
        dropTablesStudent();
        dropTablesSupervisor();

    }

    private static void drop_takes_and_students() {

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP VIEW Takes_and_students");
            pstmt.execute();

        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    private static void drop_takes_and_students_and_test() {

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP VIEW Takes_and_students_and_tests");
            pstmt.execute();

        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    private static void drop_takes_and_tests() {

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP VIEW Takes_and_tests");
            pstmt.execute();

        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    private static void drop_student_and_creditpoints() {

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP VIEW Student_and_creditpoints");
            pstmt.execute();

        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    private static void drop_oversees_and_takes() {

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP VIEW Oversees_and_takes");
            pstmt.execute();

        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    private static void drop_supervisor_and_oversees() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP VIEW Supervisor_and_oversees");
            pstmt.execute();

        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static void dropTablesTest() {

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS Test");
            pstmt.execute();

        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static void dropTablesStudent() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS Student");
            pstmt.execute();

        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static void dropTablesSupervisor() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS Supervisor");
            pstmt.execute();

        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static void dropTablesTakes() {

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS Takes");
            pstmt.execute();

        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static void dropTablesOversees() {

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS Oversees");
            pstmt.execute();

        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static ReturnValue addTest(Test test) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {

            pstmt = connection.prepareStatement("INSERT INTO Test(id,semester, time, room, day, credit_points) " +
                    "VALUES (?, ?, ?, ?, ?, ?);");

            pstmt.setInt(1, test.getId());
            pstmt.setInt(2, test.getSemester());
            pstmt.setInt(3, test.getTime());
            pstmt.setInt(4, test.getRoom());
            pstmt.setInt(5, test.getDay());
            pstmt.setInt(6, test.getCreditPoints());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            //e.printStackTrace()();
            if(Integer.valueOf(e.getSQLState()) == 23514) { //CHECK_VIOLIATION (23514)
                return BAD_PARAMS;
            }
            if(Integer.valueOf(e.getSQLState()) == 23505) { //UNIQUE_VIOLATION(23505),
                return ALREADY_EXISTS;
            }
            return ERROR;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static Test getTestProfile(Integer testID, Integer semester) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("SELECT * FROM Test " +
                    "WHERE id = ? AND semester = ?");
            pstmt.setInt(1, testID);
            pstmt.setInt(2, semester);
            ResultSet results = pstmt.executeQuery();
            results.next();
            return result_set_to_test(results);
        } catch (SQLException e) {
            //e.printStackTrace()();
            return Test.badTest();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static Test result_set_to_test (ResultSet results) throws SQLException {
        int id = results.getInt(1);
        int sem = results.getInt(2);
        int time = results.getInt(3);
        int room = results.getInt(4);
        int day = results.getInt(5);
        int cred_points = results.getInt(6);
        results.close();
        Test t = new Test();
        t.setId(id);
        t.setSemester(sem);
        t.setDay(day);
        t.setTime(time);
        t.setCreditPoints(cred_points);
        t.setRoom(room);
        return t;
    }

    public static ReturnValue deleteTest(Integer testID, Integer semester) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        int res = 0;
        try {
            pstmt = connection.prepareStatement(
                    "DELETE FROM Test " +
                            "WHERE id = ? AND semester = ?");
            pstmt.setInt(1, testID);
            pstmt.setInt(2, semester);
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            //e.printStackTrace()();
            return ERROR;
        }
        finally {
            try {
                if (res == 0){
                    return NOT_EXISTS;
                }
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static ReturnValue addStudent(Student student) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("INSERT INTO Student(id,student_name, faculty, credit_points) " +
                    "VALUES (?, ?, ?, ?);");

            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getFaculty());
            pstmt.setInt(4, student.getCreditPoints());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            //e.printStackTrace()();
            if(Integer.valueOf(e.getSQLState()) == 23514 || //CHECK_VIOLIATION (23514)
                    Integer.valueOf(e.getSQLState()) ==23502) {//NOT_NULL_VIOLATION (23502),
                return BAD_PARAMS;
            }
            if(Integer.valueOf(e.getSQLState()) == 23505) { //UNIQUE_VIOLATION(23505),
                return ALREADY_EXISTS;
            }
            return ERROR;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static Student getStudentProfile(Integer studentID) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("SELECT * FROM Student " +
                    "WHERE id = ?");
            pstmt.setInt(1, studentID);
            ResultSet results = pstmt.executeQuery();
            results.next();
            return result_set_to_student(results);
        } catch (SQLException e) {
            //e.printStackTrace()();
            return Student.badStudent();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static Student result_set_to_student (ResultSet results) throws SQLException {
        int id = results.getInt(1);
        String name = results.getString(2);
        String faculty = results.getString(3);
        int cred_points = results.getInt(4);

        results.close();
        Student s = new Student();
        s.setId(id);
        s.setName(name);
        s.setFaculty(faculty);
        s.setCreditPoints(cred_points);
        return s;
    }

    public static ReturnValue deleteStudent(Integer studentID) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        int res = 0;
        try {
            pstmt = connection.prepareStatement(
                    "DELETE FROM Student " +
                            "WHERE id = ? ");
            pstmt.setInt(1, studentID);
            res = pstmt.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace()();
            return ERROR;
        }
        finally {
            try {
                if (res == 0){
                    return NOT_EXISTS;
                }
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static ReturnValue addSupervisor(Supervisor supervisor) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("INSERT INTO Supervisor(supervisorid,supervisor_name, salary) " +
                    "VALUES (?, ?, ?);");
            pstmt.setInt(1, supervisor.getId());
            pstmt.setString(2, supervisor.getName());
            pstmt.setInt(3, supervisor.getSalary());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            //e.printStackTrace()();
            if(Integer.valueOf(e.getSQLState()) == 23514 || //CHECK_VIOLIATION (23514)
                    Integer.valueOf(e.getSQLState()) ==23502) {//NOT_NULL_VIOLATION (23502),
                return BAD_PARAMS;
            }
            if(Integer.valueOf(e.getSQLState()) == 23505) { //UNIQUE_VIOLATION(23505),
                return ALREADY_EXISTS;
            }
            return ERROR;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static Supervisor getSupervisorProfile(Integer supervisorID) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("SELECT * FROM Supervisor " +
                    "WHERE supervisorid = ?");
            pstmt.setInt(1, supervisorID);
            ResultSet results = pstmt.executeQuery();
            results.next();

            return result_set_to_supervisor(results);
        } catch (SQLException e) {
            //e.printStackTrace()();
            return Supervisor.badSupervisor();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static Supervisor result_set_to_supervisor (ResultSet results) throws SQLException {
        int id = results.getInt(1);
        String name = results.getString(2);
        int salary = results.getInt(3);

        results.close();
        Supervisor s = new Supervisor();
        s.setId(id);
        s.setName(name);
        s.setSalary(salary);
        return s;
    }

    public static ReturnValue deleteSupervisor(Integer supervisorID) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        int res = 0;
        try {
            pstmt = connection.prepareStatement(
                    "DELETE FROM Supervisor " +
                            "WHERE supervisorid = ? ");
            pstmt.setInt(1, supervisorID);
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            //e.printStackTrace()();
            return ERROR;
        }
        finally {
            try {
                if (res == 0){
                    return NOT_EXISTS;
                }
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static ReturnValue studentAttendTest(Integer studentID, Integer testID, Integer semester) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = connection.prepareStatement("INSERT INTO Takes (testid,studentid, semester) " +
                    "VALUES (?, ?, ?);");
            pstmt.setInt(1, testID);
            pstmt.setInt(2, studentID);
            pstmt.setInt(3, semester);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace()();
            if(Integer.valueOf(e.getSQLState()) == 23503) { //FOREIGN_KEY_VIOLATION(23503),
                return NOT_EXISTS;
            }
            if(Integer.valueOf(e.getSQLState()) == 23505) { //UNIQUE_VIOLATION(23505),
                return ALREADY_EXISTS;
            }
            return ERROR;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static ReturnValue studentWaiveTest(Integer studentID, Integer testID, Integer semester) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        int res = 0;
        try {
            pstmt = connection.prepareStatement(
                    "DELETE FROM Takes " +
                            "WHERE testid = ? AND studentid = ? AND semester = ?");
            pstmt.setInt(1, testID);
            pstmt.setInt(2, studentID);
            pstmt.setInt(3, semester);
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            //e.printStackTrace()();
            return ERROR;
        }
        finally {
            try {
                if (res == 0){
                    return NOT_EXISTS;
                }
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static ReturnValue supervisorOverseeTest(Integer supervisorID, Integer testID, Integer semester) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("INSERT INTO Oversees (testid,supervisorid, semester) " +
                    "VALUES (?, ?, ?);");
            pstmt.setInt(1, testID);
            pstmt.setInt(2, supervisorID);
            pstmt.setInt(3, semester);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace()();
            if(Integer.valueOf(e.getSQLState()) == 23503) { //FOREIGN_KEY_VIOLATION(23503),
                return NOT_EXISTS;
            }
            if(Integer.valueOf(e.getSQLState()) == 23505) { //UNIQUE_VIOLATION(23505),
                return ALREADY_EXISTS;
            }
            return ERROR;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static ReturnValue supervisorStopsOverseeTest(Integer supervisorID, Integer testID, Integer semester) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        int res = 0;
        try {
            pstmt = connection.prepareStatement(
                    "DELETE FROM Oversees " +
                            "WHERE testid = ? AND supervisorid = ? AND semester = ?");
            pstmt.setInt(1, testID);
            pstmt.setInt(2, supervisorID);
            pstmt.setInt(3, semester);
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            //e.printStackTrace()();
            return ERROR;
        }
        finally {
            try {
                if (res == 0){
                    return NOT_EXISTS;
                }
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static Float averageTestCost() {
        return 0.0f;
    }

    public static Integer getWage(Integer supervisorID) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("SELECT COUNT(testid) * salary AS wage FROM Supervisor_and_oversees " +
                    "WHERE supervisorid = ? " +
                    "GROUP BY salary");

            pstmt.setInt(1, supervisorID);
            ResultSet results = pstmt.executeQuery();
            results.next();
            int wage = results.getInt(1);
            results.close();
            return wage;
        } catch (SQLException e) {
            //e.printStackTrace()();
            return -1;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static ArrayList<Integer> supervisorOverseeStudent() {
        return new ArrayList<Integer>();
    }

    public static ArrayList<Integer> result_to_arraylist(ResultSet results) throws SQLException{
        ArrayList <Integer> ret =  new ArrayList<Integer>();

        while(results.next()){
            ret.add(results.getInt(1));
        }
        results.close();
        return ret;
    }

    public static ArrayList<Integer> testsThisSemester(Integer semester) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("SELECT id FROM Test " +
                    "WHERE semester = ? ORDER BY id DESC LIMIT 5" );

            pstmt.setInt(1, semester);
            ResultSet results = pstmt.executeQuery();
            return result_to_arraylist(results);
        } catch (SQLException e) {
            //e.printStackTrace()();
            return new ArrayList<Integer>();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static Boolean studentHalfWayThere(Integer studentID) {
        return true;
    }

    public static Integer studentCreditPoints(Integer studentID) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("SELECT SUM(credit_points) + student_credit_points AS total_credit_points " +
                    "FROM Takes_and_students_and_tests " +
                    "WHERE studentid = ? "+
                    "GROUP BY student_credit_points");

            pstmt.setInt(1, studentID);
            ResultSet results = pstmt.executeQuery();
            results.next();
            int total_credit_points = results.getInt(1);
            results.close();
            return total_credit_points;
        } catch (SQLException e) {
            //e.printStackTrace()();
            return 0;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static Integer getMostPopularTest(String faculty) {
        return 0;
    }

    public static ArrayList<Integer> getConflictingTests() {
        return new ArrayList<Integer>();
    }

    public static ArrayList<Integer> graduateStudents() {
        return new ArrayList<Integer>();
    }

    public static ArrayList<Integer> getCloseStudents(Integer studentID) {
        return new ArrayList<Integer>();
    }
}