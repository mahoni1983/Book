import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Dmitrij Starikovic
 *
 */
public class PointSQL
{
	/**
	 * 
	 * @param point
	 * @return
	 * @throws SQLException
	 */
   
	public boolean addPoint (Point point) throws SQLException
    {
        PreparedStatement ps;
        String query = "INSERT INTO point (course_id, student_id, point, point_date) VALUES (?, ?, ?, NOW())";
        ps = DBase.getInstance().getConnection().prepareStatement(query, 1);
        ps.setInt(1, point.course_id);
        ps.setInt(2, point.student_id);
        ps.setInt(3, point.point);
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        if (!rs.next())
        	return false;
        point.point_id = rs.getInt(1);

    	return true;
    }
    	/**
    	 * 
    	 * @param course_id
    	 * @param student_id
    	 * @param point
    	 * @return
    	 */
    public String point (int course_id, int student_id, int point) // ???
    {
        return "unknown error";
    }
}


