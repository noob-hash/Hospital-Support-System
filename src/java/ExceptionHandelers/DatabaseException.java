package ExceptionHandelers;

public class DatabaseException extends Exception{

    public void NonExistentDatabase(String name) throws DatabaseException{
        System.out.println("Database " + name + " doesn't exist.");
    }

    
    
}
