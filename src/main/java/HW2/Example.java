package HW2;

import HW2.business.ReturnValue;
import HW2.business.Student;
import HW2.business.Supervisor;
import HW2.business.Test;
import HW2.data.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Example {

    public static void main(String[] args) {

        Solution.dropTables();
        Solution.createTables();

        Test t = new Test();
        t.setId(1);
        t.setSemester(1);
        t.setDay(5);
        t.setTime(1);
        t.setCreditPoints(8);
        t.setRoom(7);

        ReturnValue r= Solution.addTest(t); //OK
        r= Solution.addTest(t); //alredy exists

        t.setId(2);
        r= Solution.addTest(t); //OK

        t.setSemester(5);
        r= Solution.addTest(t); //invalid semester

        r = Solution.deleteTest(12,3); //not exists
        r = Solution.deleteTest(1,3);   //not exists
        r = Solution.deleteTest(12,1); //not exists

        Student s = new Student();
        s.setFaculty("CS");
        s.setName("karen");
        s.setId(345088199);
        s.setCreditPoints(0);

        ReturnValue r2= Solution.addStudent(s); //ok
        r2= Solution.addStudent(s); //alredy exists
        r2 = Solution.deleteStudent(0); //doesnt exists

        s.setId(-1);
        r2= Solution.addStudent(s); //bad params

        s.setId(345088199);
        s.setName(null);
        r2= Solution.addStudent(s); //bad params

        ReturnValue r3 = Solution.studentAttendTest(345088199, 1, 1); //ok
        ReturnValue r4 = Solution.studentAttendTest(345088199, 1, 1); //alredy exists
        ReturnValue r5 = Solution.studentAttendTest(345088199, 2, 1); //ok
        ReturnValue r6 = Solution.studentAttendTest(0, 2, 1); //doesnt exists
        ReturnValue r7 = Solution.studentAttendTest(345088199, 21, 1); //doesnt exists
        ReturnValue r8 = Solution.studentAttendTest(345088199, 1, 3); //doesnt exists
        ReturnValue r9 = Solution.studentAttendTest(345088199, 1, 6); //doesnt exists

        Supervisor su = new Supervisor();
        su.setId(5);
        su.setSalary(8);
        su.setName("aa");

        r2 = Solution.addSupervisor(su); //ok
        su.setId(8);
        r2 = Solution.addSupervisor(su); //ok
        r2 = Solution.addSupervisor(su); //alredy exists
        su.setSalary(-1);

        r2 = Solution.addSupervisor(su); //bad args
        su.setSalary(8);
        su.setName(null);
        r2 = Solution.addSupervisor(su); //bad args

        ReturnValue r33 = Solution.supervisorOverseeTest(5, 1, 1); //ok
        ReturnValue r44 = Solution.supervisorOverseeTest(5, 2, 1); //ok
        ReturnValue r55 = Solution.supervisorOverseeTest(5, 1, 1); //alredy exists
        ReturnValue r66 = Solution.supervisorOverseeTest(0, 2, 1); //doesnt exists
        ReturnValue r77 = Solution.supervisorOverseeTest(5, 21, 1); //doesnt exists
        ReturnValue r88 = Solution.supervisorOverseeTest(5, 1, 3); //doesnt exists

        ReturnValue r333 = Solution.studentWaiveTest(345088199, 1, 1); //ok
        ReturnValue r444 = Solution.studentWaiveTest(345088199, 1, 1); //doesnt exists
        ReturnValue r555 = Solution.supervisorOverseeTest(345088199, 2, 2); //doesnt exists
        ReturnValue r666 = Solution.supervisorOverseeTest(0, 2, 1); //doesnt exists

        ReturnValue w1 = Solution.supervisorStopsOverseeTest(5, 1, 1); //ok
        ReturnValue w2 = Solution.supervisorStopsOverseeTest(345088199, 1, 1); //doesnt exists
        ReturnValue s3 = Solution.supervisorStopsOverseeTest(5, 2, 3); //doesnt exists
        ReturnValue s4 = Solution.supervisorStopsOverseeTest(5, 6, 1); //doesnt exists


        r44 = Solution.supervisorOverseeTest(5, 1, 1); //ok

        int wage = Solution.getWage(5); //16
        int wage2 = Solution.getWage(11); //-1
        ArrayList <Integer>  a = Solution.testsThisSemester(1); //ok

        a = Solution.testsThisSemester(11); //empty arraylist

        int k = Solution.studentCreditPoints(345088199);  //8
        int kk = Solution.studentCreditPoints(459); //0

        su.setSalary(10);
        su.setName("nochi");
        su.setId(123);
        r2 = Solution.addSupervisor(su); //ok

        su.setSalary(100);
        su.setName("dor");
        su.setId(1007);
        r2 = Solution.addSupervisor(su); //ok
        r33 = Solution.supervisorOverseeTest(123, 1, 1); //ok
        r33 = Solution.supervisorOverseeTest(123, 2, 1); //ok
        r33 = Solution.supervisorOverseeTest(1007, 2, 1); //ok
        Float f = Solution.averageTestCost();
        t.setId(3);
        t.setSemester(2);
        r= Solution.addTest(t);
        f =Solution.averageTestCost();
        t.setId(4);
        t.setSemester(2);
        r= Solution.addTest(t);
        f =Solution.averageTestCost();
//        selectFromTable();
//        System.out.println("inserting main.data into table");
//        insertIntoTable();
//        selectFromTable();
//        System.out.println("updating main.data in table");
//        updateTable();
//        selectFromTable();
//        System.out.println("deleting main.data from table");
//        deleteFromTable();
//        selectFromTable();
//        dropTable();

    }

    private static void deleteFromTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(
                    "DELETE FROM hello_world " +

                            "where id = ?");
            pstmt.setInt(1,1);

            int affectedRows = pstmt.executeUpdate();
            System.out.println("deleted " + affectedRows + " rows");
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

    private static void javaStringExample() {
        System.out.println("in order to concat two strings you can use the + operator, for example:");
        System.out.println("    String hello = \"hello\";\n" +
                "   String world = \" world\";\n" +
                "   System.out.println(hello + world);");
        System.out.println("will yield:");
        String hello = "hello";
        String world = " world";
        System.out.println(hello + world);
        System.out.println("you can concat any object to a string, for example, an integer:");
        System.out.println("    System.out.println(hello + world + 1);");
        System.out.println("will yield:");
        System.out.println(hello + world + 1);
        System.out.println("good luck!");
        System.out.println();
    }

    private static void arrayListExample() {
        System.out.println("in order to create a new arraylist, for example, an arraylist of Integers you need to call");
        System.out.println("    ArrayList<Integer> myArrayList = new ArrayList<>();");
        ArrayList<Integer> myArrayList = new ArrayList<>();
        System.out.println("This is how it looks like: " +myArrayList);
        System.out.println("in order to add an item to the arraylist you need to call the add function");
        System.out.println("    myArrayList.add(1);");
        myArrayList.add(1);
        System.out.println("This is how it looks like: " +myArrayList);
        System.out.println("calling myArrayList.add(2); \nwill yield: ");
        myArrayList.add(2);
        System.out.println(myArrayList);
        System.out.println("please note that arraylist keeps insertion order, and allows duplications");
        System.out.println("calling\n   myArrayList.add(2); \nwill yield: ");
        myArrayList.add(2);
        System.out.println(myArrayList);
        System.out.println("in order to get a value from an array list you need to use the function\n get(int index) ");
        System.out.println("recall that array start with 0 :)");
        System.out.println("    int i = myArrayList.get(0);\nwill yield:");
        int i = myArrayList.get(0);
        System.out.println("    i = " + i );
        System.out.println("good luck!");
        System.out.println();

    }

    private static void updateTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(
                    "UPDATE hello_world " +
                            "SET short_text = ? " +
                            "where id = ?");
            pstmt.setInt(2,1);
            pstmt.setString(1, "hi world!");
            int affectedRows = pstmt.executeUpdate();
            System.out.println("changed " + affectedRows + " rows");
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

    private static void dropTable()
    {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS hello_world");
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

    private static void createTable()
    {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {

            pstmt = connection.prepareStatement("CREATE TABLE hello_world\n" +
                    "(\n" +
                    "    id integer,\n" +
                    "    short_text text ,\n" +
                    "    PRIMARY KEY (id),\n" +
                    "    CHECK (id > 0)\n" +
                    ")");
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

    private static void insertIntoTable()
    {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("INSERT INTO hello_world" +
                    " VALUES (?, ?), (?, ?)");
            pstmt.setInt(1,1);
            pstmt.setString(2, "hello world!");
            pstmt.setInt(3,2);
            pstmt.setString(4,"goodbye world!");


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

    private static void selectFromTable()
    {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("SELECT * FROM hello_world");
            ResultSet results = pstmt.executeQuery();
            DBConnector.printResults(results);
            results.close();

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

}
