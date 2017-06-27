/**
 * 
 * @author Dmitrij Starikovic
 *
 */
public class Student
{
	/**
	 * student
	 */
	int student_id;
	String name;
	String email;
	double avg_point;
	@Override
	public String toString() {
		return "Student [student_id=" + student_id + ", name=" + name + ", email=" + email + ", avg_point=" + avg_point
				+ "]";
	}
}
