package HW2;

import HW2.business.*;
import HW2.data.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import HW2.data.PostgreSQLErrorCodes;

import java.util.ArrayList;

import static HW2.business.ReturnValue.*;


public class Solution {
    public static void createTables() {
        InitialState.createInitialState();

        //create your tables here
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        create_test_table();
        create_student_table();
        create_supervisor_table();
        create_takes_table();

    }

    public static void create_test_table() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            //TEST
            pstmt = connection.prepareStatement("CREATE TABLE test\n" +
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
            //TEST
            pstmt = connection.prepareStatement("CREATE TABLE Student\n" +
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
            //TEST
            pstmt = connection.prepareStatement("CREATE TABLE Supervisor\n" +
                    "(\n" +
                    "    id integer NOT NULL,\n" +
                    "    supervisor_name text NOT NULL,\n" +
                    "    salary integer NOT NULL,\n" +
                    "    PRIMARY KEY (id),\n" +
                    "    CHECK (id > 0),\n" +
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
            //TEST
            pstmt = connection.prepareStatement("CREATE TABLE takes\n" +
                    "(\n" +
                    "    testid integer NOT NULL,\n" +
                    "    salary integer NOT NULL,\n" +
                    "    PRIMARY KEY (testid),\n" +
                    "    CHECK (testid > 0),\n" +
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

    public static void clearTables() {
        //clear your tables here
    }

    public static void dropTables() {
        InitialState.dropInitialState();
        //drop your tables here
        dropTablesTest();
        dropTablesStudent();
        dropTablesSupervisor();
        dropTablesTakes();
        dropTablesOversees();
    }

    public static void dropTablesTest() {
        InitialState.dropInitialState();
        //drop your tables here

        //TEST
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
        InitialState.dropInitialState();
        //drop your tables here

        //TEST
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
        InitialState.dropInitialState();
        //drop your tables here

        //TEST
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
        InitialState.dropInitialState();
        //drop your tables here

        //TEST
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
        InitialState.dropInitialState();
        //drop your tables here

        //TEST
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

        return OK;
    }

    public static Test getTestProfile(Integer testID, Integer semester) {
        return new Test();
    }

    public static ReturnValue deleteTest(Integer testID, Integer semester) {
        return OK;
    }

    public static ReturnValue addStudent(Student student) {
        return OK;
    }

    public static Student getStudentProfile(Integer studentID) {
        return new Student();
    }

    public static ReturnValue deleteStudent(Integer studentID) {
        return OK;
    }

    public static ReturnValue addSupervisor(Supervisor supervisor) {
        return OK;
    }

    public static Supervisor getSupervisorProfile(Integer supervisorID) {
        return new Supervisor();
    }

    public static ReturnValue deleteSupervisor(Integer supervisorID) {
        return OK;
    }

    public static ReturnValue studentAttendTest(Integer studentID, Integer testID, Integer semester) {
        return OK;
    }

    public static ReturnValue studentWaiveTest(Integer studentID, Integer testID, Integer semester) {
        return OK;
    }

    public static ReturnValue supervisorOverseeTest(Integer supervisorID, Integer testID, Integer semester) {
        return OK;
    }

    public static ReturnValue supervisorStopsOverseeTest(Integer supervisorID, Integer testID, Integer semester) {
        return OK;
    }

    public static Float averageTestCost() {
        return 0.0f;
    }

    public static Integer getWage(Integer supervisorID) {
        return 0;
    }

    public static ArrayList<Integer> supervisorOverseeStudent() {
        return new ArrayList<Integer>();
    }

    public static ArrayList<Integer> testsThisSemester(Integer semester) {
        return new ArrayList<Integer>();
    }

    public static Boolean studentHalfWayThere(Integer studentID) {
        return true;
    }

    public static Integer studentCreditPoints(Integer studentID) {
        return 0;
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