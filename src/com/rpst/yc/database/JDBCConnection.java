/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rpst.yc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author sanjib
 */
public class JDBCConnection {
    Connection con=null;
    PreparedStatement stmt=null;
    public void SetConnection() throws SQLException,ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/yc","root","");
    }
    
    //String fullname,String address,String phone, String age,String idnumber,String usertype,string username,String password
    public String RegistrationUsers(DatatypeUserRegistration dataobj)throws SQLException,ClassNotFoundException{
        try{
        SetConnection();
        String query="INSERT INTO tbl_registration VALUES (?,?,?,?,?,?,?,?)";
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
    public ResultSet ListUsers()throws SQLException,ClassNotFoundException{
            SetConnection();
            String query="Select fullname,address,phon,age,userid,usertype,username from tbl_registration";
            stmt=con.prepareStatement(query);
            ResultSet rs=stmt.executeQuery();
            return rs;
    }
    
    public static DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException,ClassNotFoundException {

    ResultSetMetaData metaData = rs.getMetaData();

    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = metaData.getColumnCount();
    for (int column = 1; column <= columnCount; column++) {
        columnNames.add(metaData.getColumnName(column));
    }

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            vector.add(rs.getObject(columnIndex));
        }
        data.add(vector);
    }
return new DefaultTableModel(data, columnNames);
    
}
    public ResultSet GetChartValues() throws SQLException,ClassNotFoundException{
        SetConnection();
        ResultSet rs=null;
        String query="Select * from test";
        PreparedStatement stmt=null;
        stmt=con.prepareStatement(query);
        rs=stmt.executeQuery();
        return rs;
    }
}
