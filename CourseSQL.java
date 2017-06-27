import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * to work with courses from DB
 * @author Eugene
 *
 */
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
			return -1;
		}
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

    public boolean isCourseID (int course_id) throws SQLException
    {
        return false;
    }
    /**
     * to get all courses from DB
     * @return
     * @throws SQLException
     */
    public ArrayList<Course> getCourses () throws SQLException
    {
    	ArrayList<Course> courseList = new ArrayList<Course>();
    	try 
		{
			String query = 
					"select * from course order by title;";
			PreparedStatement ps = DBase
					.getInstance()
					.getConnection()
					.prepareStatement(query);
			ps.execute();
			ResultSet rs = ps.getResultSet();
		//	int rowCount = 0;
	         while(rs.next()) {   // Move the cursor to the next row, return false if no more row
	            String title = rs.getString("title");
	            int    course_id   = rs.getInt("course_id");
	     //       System.out.println("" + course_id + "\t\t" + title);
	            Course course = new Course();
	            course.course_id = course_id;
	            course.title = title;
	            courseList.add(course);
	     //       ++rowCount;
	         }
	      //   System.out.println("Total number of records = " + rowCount);
	 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("CourseSQL getCourses error");
		}
        return courseList;
    }
	

}
