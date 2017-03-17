package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
                static{
                                try{
                                     Class.forName("oracle.jdbc.driver.OracleDriver");
                                }catch(ClassNotFoundException e){
                                                e.printStackTrace();
                                }
                }
                public static Connection getOracleConnection() throws SQLException{
                                String url = "jdbc:oracle:thin:@localhost:1521/XE";
                                Connection con = DriverManager.getConnection(url,"system","sys");
                                return con;
                }
                
                public static void cleanup(Statement st, Connection con){
                                try{
                                                if(st!=null) st.close();
                                                if(con!=null) con.close();
                                }catch(Exception e){
                                                e.printStackTrace();
                                }
                }
                
                public static void cleanup(Statement st, Connection con, ResultSet rs){
                                try{
                                                if(st!=null) st.close();
                                                if(con!=null) con.close();
                                                if(rs!=null) rs.close();
                                }catch(Exception e){
                                                e.printStackTrace();
                                }
                }


}

