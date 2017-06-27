import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * 
 * @author Dmitrij Starikovic
 *
 */
public class StudentSQL {
	/**
	 * 
	 * @param student
	 * @return
	 * @throws SQLException
	 */
	public boolean addUser(Student student) throws SQLException {
		String query = "INSERT INTO student (name, email) VALUES (?, ?)";

		try {
			PreparedStatement ps = DBase.getInstance().getConnection().prepareStatement(query, 1);
			ps.setString(1, student.name);
			ps.setString(2, student.email);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (!rs.next())
				return false;
			student.student_id = rs.getInt(1);
			return true;
		} catch (SQLException e) {
			System.out.println(query);
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean isEmail(String email) throws SQLException // not completed
	{
		return false;
	}

	public boolean isStudentID(int student_id) throws SQLException // not
																	// completed
	{
		return false;
	}
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */

	public ArrayList<Student> getStudents() throws SQLException {
		ArrayList<Student> list = new ArrayList<Student>();
		String query = "SELECT * FROM student";
		Statement st;
		try {
			st = DBase.getInstance().getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Student student = new Student();
				student.student_id = rs.getInt(1);
				student.name = rs.getString(2);
				student.email = rs.getString(3);
				student.avg_point = rs.getDouble(4);
				list.add(student);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	public double getAvgPoints(int student_id) throws SQLException // not
																	// completed
	{
		return 0.0;
	}

	public void updateAvgPoints(int student_id, double avg) throws SQLException // not
																				// completed
	{
	}
	/**
	 * 
	 * @param list
	 * @throws SQLException
	 */
	public void doShowStudentsList(ArrayList<Student> list) throws SQLException {

		System.out.println("student_id |     name           |   email          |  avg_point  ");
		System.out.println("-----------+--------------------+------------------+--------------");

		for (Student student : list)
			System.out.println("\t" + student.student_id + "\t" + student.name + "\t\t" + student.email + "\t\t"
					+ student.avg_point);

	}
	/**
	 * 
	 * @param student
	 * @throws SQLException
	 */
	public void showAVGStudent(Student student) throws SQLException {
		PreparedStatement ps;
		String query = "SELECT name, AVG (point) FROM student JOIN point WHERE point.student_id = ?";
		ps = DBase.getInstance().getConnection().prepareStatement(query, 1);
		ps.setInt(1, student.student_id);
		ps.execute();
		ResultSet rs = ps.executeQuery();
		while (rs.next())
			System.out.println("Studentas -> " + rs.getString(1) + "\n" + "Studentu vidurkis -> " + rs.getDouble(2));
	}

}
