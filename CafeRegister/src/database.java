 
/**
 * Wissahickon High School Cafe Register
 */
 
import java.sql.*;
public class database 
{
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    database()
    {
        try{
             	
            //MAKE SURE YOU KEEP THE mysql_connector.jar file in java/lib folder
            //ALSO SET THE CLASSPATH
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/SSUsers","root","schoolstore");
                        pst=con.prepareStatement("select * from user where loginID=?");
             
           }
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
        //ip:username,password
        //return boolean
    public Boolean checkLogin(int loginID)
    {
        try {
                        
            pst.setInt(1, loginID); //this replaces the 1st  "?" in the query for login
            //executes the prepared statement
            rs=pst.executeQuery();
            if(rs.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Validation Error: " + e);
            return false; 
        }
    }
}																				