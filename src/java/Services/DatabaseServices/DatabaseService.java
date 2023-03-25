/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.DatabaseServices;

import Models.Doctor;
import Models.TableData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Subin
 */
public class DatabaseService implements DatabaseConn {
    Connection con = new DatabaseConnection().ConnectionEstablishment();
    
    @Override
    public ResultSet GetData(String TableName) {
        ResultSet resultSet = null;
        try {
            String statement = "Select * from " + TableName +" ;";
            PreparedStatement preparedStatement = con.prepareStatement(statement);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        return resultSet;
    }

    @Override
    public ResultSet GetData(String Table, String ... ColumnNames){
        ResultSet resultSet = null;
        try {
            String statement = "Select ";
            for (int i = 0; i<ColumnNames.length; i++) {
                statement += ColumnNames[i];
                if(i != ColumnNames.length){
                    statement += ", ";
                }
            }
            statement += "from " + Table +" ;";
            PreparedStatement preparedStatement = con.prepareStatement(statement);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        return resultSet;
    }

    @Override
    public ResultSet GetData(String Table, TableData... conditions){
        ResultSet resultSet = null;
        try {
            String statement = "Select * ";
            statement += "from " + Table +" where ";
            for(int i = 0; i < conditions.length; i++){
                statement += conditions[i].columnName + " = ?";
                if(i != conditions.length - 1){
                    statement += " AND ";
                }else{
                    statement += ";";
                }
            }
            PreparedStatement preparedStatement = con.prepareStatement(statement);
            for(int i = 0; i < conditions.length; i++){
                preparedStatement.setString(i + 1, conditions[i].value);
            }
                        System.out.println(preparedStatement);

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        return resultSet;
    }

    @Override
    public ResultSet GetData(String Table, String data, TableData... conditions){
        ResultSet resultSet = null;
        try {
            String statement = "Select " + data;
            statement += " from " + Table +" where ";
            for(int i = 0; i < conditions.length; i++){
                statement += conditions[i].columnName + " = " + conditions[i].value;
                if(i != conditions.length - 1){
                    statement += "AND";
                } else{
                    statement += ";";
                }
            }
            PreparedStatement preparedStatement = con.prepareStatement(statement);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        return resultSet;
    }

    @Override
    public ResultSet GetData(String Table, TableData condition, String ... ColumnNames){
        ResultSet resultSet = null;
        try {
            String statement = "Select ";
            for (int i = 0; i<ColumnNames.length; i++) {
                statement += ColumnNames[i];
                if(i != ColumnNames.length - 1){
                    statement += ", ";
                }
            }
            statement += " from " + Table +" where " + condition.columnName + " = ? " + " ;";
            PreparedStatement preparedStatement = con.prepareStatement(statement);
            preparedStatement.setString(1, condition.value);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return resultSet;
    }
    

    @Override
    public void InsertData(String Table, String... values) {
        try{
            String statement = "Insert into " + Table + "(";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Show columns in " + Table + ";");
            int j=1;
            while(rs.next()){
                if(rs.getString(j).contains("Id") || rs.getString(j).contains("id")){
                }else if(rs.isLast()){
                    statement += (rs.getString(j) + ") ");
                }else{
                    statement += (rs.getString(j) + ", ");
                }
            }

            statement += " values (";

            for (int i = 0; i<values.length; i++) {
                statement += "?";
                if(i != (values.length - 1)){
                    statement += ", ";
                }else{
                    statement += ");";
                }
            }
            PreparedStatement preparedStatement = con.prepareStatement(statement);
            int i = 1;
            for (String val : values) {
                preparedStatement.setString(i, val);
                i++;
            }
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void InsertData(String Table, boolean data, String... values) {
        try{
            String statement = "Insert into " + Table + "(";
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Show columns in " + Table + ";");
            int j=1;
            while(rs.next()){
                if(rs.getString(j).contains("Id")){
                }else if(rs.isLast()){
                    statement += (rs.getString(j) + ") ");
                }else{
                    statement += (rs.getString(j) + ", ");
                }
            }

            statement += " values (";

            for (int i = 0; i<values.length; i++) {
                statement += "?";
                if(i != (values.length)){
                    statement += ", ";
                }
            }
            statement += " ?);";
            PreparedStatement preparedStatement = con.prepareStatement(statement);
            int k = 1;
            for (String val : values) {
                preparedStatement.setString(k, val);
                k++;
            }
            
            preparedStatement.setBoolean(k, data);
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateData(String Table,TableData condition, TableData... data) {
        try{
            String statement = "Update " + Table + " set ";
            
            for (int i = 0; i<data.length; i++) {
                statement += (data[i].columnName + " = ?" );
                if(i != data.length - 1){
                    statement += ", ";
                }
            }
            statement +=  " where " + condition.columnName + " = ?"  + ";";
            PreparedStatement preparedStatement = con.prepareStatement(statement);
            for (int i = 0; i<data.length; i++) {
                preparedStatement.setString(i + 1, data[i].value);
                if(i == data.length - 1){
                    preparedStatement.setString(i + 2, condition.value);
                }
            }
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }     
    }

    @Override
    public void RemoveData(String Table,TableData... condition) {
        try{
            String statement = "Delete from " + Table + " where ";
            
            for (int i = 0; i<condition.length; i++) {
                statement += (condition[i].columnName + " = ?" );
                if(i != condition.length - 1){
                    statement += " or ";
                }else{
                    statement += ";";
                }
            }
            PreparedStatement preparedStatement = con.prepareStatement(statement);
            for (int i = 0; i<condition.length; i++) {
                preparedStatement.setString(i + 1, condition[i].value);
            }
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }     
    }

    @Override
    public boolean CheckDataExists(String TableName, TableData... conditions){
        boolean returnValue = false;
        try {
            ResultSet rs = GetData(TableName, conditions);
            returnValue = (rs.next());
        } catch (Exception e) {
        }
        return returnValue;
    }

    @Override
    public void SechduledData(String[] Tables, String[] columns) {
        try{
            String statement = "Select * from " ;
            for(int i=0; i< Tables.length; i++){
                statement += Tables[i];
                
                if(i != Tables.length){
                    statement += " Outer Join ";
                }else{
                    statement += " where ";
                }
                
            }
            for(int i=0; i< columns.length; i++){
                
                if(i != Tables.length){
                    statement += Tables[i] + "." + columns[i] + " = ";
                }else{
                    statement += Tables[i] + "." + columns[i];
                }
                
            }
            PreparedStatement preparedStatement = con.prepareStatement(statement);
            System.out.println(preparedStatement);
            
            
        }catch(SQLException e){
            e.printStackTrace();
        }     
    }
    
    @Override
    public ResultSet DoctorInfo(String[] Tables, String[] columns, TableData condition) {
        ResultSet result = null;
        try{
            String statement = "Select * from " ;
            for(int i=0; i< Tables.length; i++){
                statement += Tables[i];
                
                if(i != Tables.length - 1){
                    statement += " Inner Join ";
                }else{
                    statement += " on ";
                }
                
            }
            for(int i=0; i< columns.length; i++){
                
                if(i != Tables.length-1){
                    statement += Tables[i] + "." + columns[i] + " = ";
                }else{
                    statement += Tables[i] + "." + columns[i];
                }
                
            }
            statement+= " where " + condition.columnName + " = ?";
            PreparedStatement preparedStatement = con.prepareStatement(statement);
            preparedStatement.setString(1, condition.value);
            System.out.println(preparedStatement);
            
            result = preparedStatement.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }     
        return result;
    }
    
    public void addDoctor(Doctor doctor){
        InsertData("user",false,doctor.getName(),doctor.getGender().toString(), doctor.getDob().toString(), doctor.getPhone(), doctor.getEmail(), doctor.getAddress(),"D");
        InsertData("doctor",doctor.getSpeacialization(),doctor.getEducation());
    }




// Hospital Service System
}
