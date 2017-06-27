import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseSQL
{
    public int addCourse (Course course) throws SQLException
    {
    	try 
		{
			String query = 
					"insert into course(title) values(?);";
			PreparedStatement ps = DBase
					.getInstance()
					.getConnection()
					.prepareStatement(query, 1);
			ps.setString(1, course.title);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) course.course_id = rs.getInt(1);
				return course.course_id;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		}
    	return -1;
    }
    
    public boolean isTitle (String title) throws SQLException
    {
    	try 
		{
			String query = 
					"select * from course where title = ?;";
			PreparedStatement ps = DBase
					.getInstance()
					.getConnection()
					.prepareStatement(query);
			ps.setString(1, title);
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

  /*  public boolean isCourseID (int course_id) throws SQLException
    {
        return false;
    }
    
    public ArrayList<Course> getCourses () throws SQLException
    {
        return null;
    }
	*/

}
