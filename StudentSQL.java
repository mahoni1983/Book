import java.sql.SQLException;
import java.util.ArrayList;

public class StudentSQL
{
    public int addUser (Student student) throws SQLException
    {
        return -1;
    }
    
    public boolean isEmail (String email) throws SQLException
    {
        return false;
    }
    
    public boolean isStudentID (int student_id) throws SQLException
    {
        return false;
    }
    
    public ArrayList<Student> getStudents() throws SQLException
    {
        return null;
    }
    
    public double getAvgPoints (int student_id) throws SQLException
    {
        return 0.0;        
    }

    public void updateAvgPoints (int student_id, double avg) throws SQLException
    {
    }

}
