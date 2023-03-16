package Services.DatabaseServices;

import java.sql.ResultSet;

import Models.TableData;

public interface DatabaseConn {
    public final static String url = "jdbc:mysql://localhost:";
    public final static int port = 3307;
    public final static String databaseName = "hms";

    // save to database
    // abstract void InsertData(String TableName, String... Values);

    // update values in existing tuple
    abstract void UpdateData(String TableName, TableData condition, TableData... Values);
    

    // removes data
    // if you do not want to erase the data but instead do not want it to appear duting query make existis value to false in database
    abstract void RemoveData(String TableName, TableData... condition);
    

    // well depending on what you need to get from table
    abstract ResultSet GetData(String TableName);

    abstract ResultSet GetData(String TableName, String... ColumnName);

    abstract ResultSet GetData(String TableName, TableData... Condition);

    abstract ResultSet GetData(String Table, String data, TableData... conditions);
    
    abstract ResultSet GetData(String TableName, TableData condition, String... ColumnName);
    
    abstract void InsertData(String Table, String... values);

    abstract void InsertData(String Table, boolean data, String... values);

    abstract boolean CheckDataExists(String TableName, TableData... conditions);
    
    public void SechduledData(String[] Tables, String[] columns);
    
    public ResultSet DoctorInfo(String[] Tables, String[] columns, TableData condition);

}
