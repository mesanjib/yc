/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rpst.yc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author sanjib
 */
public class JDBCConnection {
    
    //String fullname,String address,String phone, String age,String idnumber,String usertype,string username,String password
    public String RegistrationUsers(DatatypeUserRegistration dataobj)throws SQLException,ClassNotFoundException{
        try{
        Connection con=null;
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/yc","root","");
        String query="INSERT INTO tbl_registration VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement stmt=null;
        stmt=con.prepareStatement(query); 
        stmt.setString(1,dataobj.getFullname());
        stmt.setString(2,dataobj.getAddress());
        stmt.setString(3,dataobj.getPhon());
        stmt.setString(4,dataobj.getAge());
        stmt.setString(5,dataobj.getUserid());
        stmt.setString(6,dataobj.getUsertype());
        stmt.setString(7,dataobj.getUsername());
        stmt.setString(8,dataobj.getPassword());
        stmt.execute();
    }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        
            return "Registered";
 
}
}
