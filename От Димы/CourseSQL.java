
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *Course model
 * 
 * @author Dmitrij Starikovic
 */
public class CourseSQL {
	/**
	 * Add new record
	 * @return true if added successfully
	 */

	public boolean addCourse(Course course) throws SQLException {
		PreparedStatement ps;
		String query = "INSERT INTO course (title) VALUES (?)";

		try {
			ps = DBase.getInstance().getConnection().prepareStatement(query, 1);
			ps.setString(1, course.title);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (!rs.next())
				return false;
			course.course_id = rs.getInt(1);
			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	/**
	 * 
	 * @param title
	 */
	public void isTitle(String title) {

		PreparedStatement ps;
		String query = "SELECT * FROM course WHERE title = ? ";
		try {
			ps = DBase.getInstance().getConnection().prepareStatement(query);
			ps.setString(1, title);
			ResultSet result = ps.executeQuery();
			if (result.next())
				System.out.println("Kursas ->" + result.getString(2) + "<- yra sarase");
			else
				System.out.println("Atsiprasome, bet tokio kurso (" + title + ") nera sarase");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean isCourseID(int course_id) throws SQLException // not
																	// completed
	{
		return false;
	}
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Course> getCourses() throws SQLException {
		ArrayList<Course> list = new ArrayList<Course>();
		String query = "SELECT * FROM course";
		Statement st;
		try {
			st = DBase.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Course course = new Course();
				course.course_id = rs.getInt(1);
				course.title = rs.getString(2);
				list.add(course);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	/**
	 * 
	 * @param list
	 * @throws SQLException
	 */
	public void doShowCoursesList(ArrayList<Course> list) throws SQLException {

		System.out.println("course_id | title");
		System.out.println("----------+--------");

		for (Course course : list)
			System.out.println(course.course_id + "\t\t" + course.title);
	}

}
