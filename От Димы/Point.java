/**
 * 
 * @author Dmitrij Starikovic
 *
 */
public class Point {
	/**
	 * point
	 */
	int point_id;
	int course_id;
	int student_id;
	int point;
	@Override
	/**
	 * string
	 */
	public String toString() {
		return "Point [point_id=" + point_id + ", course_id=" + course_id + ", student_id=" + student_id + ", point="
				+ point + ", point_date=" + point_date + "]";
	}
	String point_date;
}
