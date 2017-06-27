import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * to work with students
 * @author Eugene
 *
 */
public class StudentSQL
{
	/**
	 * add a new student to DB
	 * @param student
	 * @return
	 * @throws SQLException
	 */
    public int addStudent (Student student) throws SQLException
    {
    	try 
		{
			String query = 
					"insert into student (name, email) values(?, ?);";
			PreparedStatement ps = DBase
					.getInstance()
					.getConnection()
					.prepareStatement(query, 1);
			ps.setString(1, student.name);
			ps.setString(2, student.email);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) student.student_id = rs.getInt(1);
				return student.student_id;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
        return -1;
    }
    
    public boolean isEmail (String email) throws SQLException
    {
    	try 
		{
			String query = 
					"select * from student where email =?;";
			PreparedStatement ps = DBase
					.getInstance()
					.getConnection()
					.prepareStatement(query);
			ps.setString(1, email);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("CourseSQL isTitle error");
		}
        return false;
    }
    
    public boolean isStudentID (int student_id) throws SQLException
    {
        return false;
    }
    /**
     * get student list from DB
     * @return
     * @throws SQLException
     */
    public ArrayList<Student> getStudents() throws SQLException
    {
    	ArrayList<Student> studentList = new ArrayList<Student>();
    	try 
		{
			String query = 
					"select * from student order by name;";
			PreparedStatement ps = DBase
					.getInstance()
					.getConnection()
					.prepareStatement(query);
			ps.execute();
			ResultSet rs = ps.getResultSet();
		//	int rowCount = 0;
	         while(rs.next()) {   // Move the cursor to the next row, return false if no more row
	        	 Student student = new Student ();
	        	 student.student_id= rs.getInt("student_id");
	            student.name = rs.getString("name");
	            student.email = rs.getString("email");
	            String a =rs.getString("avg_point");
	    //        System.out.println("a: " + a);
	            if (a != null)             	
	            	student.avg_point = Float.parseFloat(a);
	    //        System.out.println("" + student.student_id + "\t\t" +  student.name);

	            studentList.add(student);
	     //       ++rowCount;
	         }
	      //   System.out.println("Total number of records = " + rowCount);
	 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("StudentSQL getStudents error");
			return null;
		}
        return studentList;
        
    }
    
    public double getAvgPoints (int student_id) throws SQLException
    {
        return 0.0;        
    }

    public void updateAvgPoints (int student_id, double avg) throws SQLException
    {
    }

}
