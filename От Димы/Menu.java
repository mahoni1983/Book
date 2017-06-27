import java.sql.SQLException;
import java.util.Scanner;


/**
 * Menu class
 * 
 * @author Dmitrij Starikovic
 */
public class Menu 
{
    Scanner scanner = new Scanner (System.in);

	/**
	 * Show
	 */
    public void show ()
    {
        System.out.println();
        System.out.println("=== MENIU ===");
        System.out.println("1. Registruoti nauja kursa.");
        System.out.println("2. Patikrinti kursa sarase");
        System.out.println("3. Perziureti visus kursus.");
        System.out.println("4. Registruoti nauja studenta.");
        System.out.println("5. Perziureti studentu sarasa.");
        System.out.println("6. Ivesti nauja pazyma.");
        System.out.println("7. Isvesti pasirinkto studento avg pazyma.");
        System.out.println("0. Iseiti");
    }
    
    /**
	 * getItem
	 * @return selected item
	 */
    
	public int getItem () {
        int item = 0;
        do {
        	System.out.println("Iveskite punkta >");
        	item = scanner.nextInt();
        	scanner.nextLine();
        } while (item < 0 || item > 7);
        
		return item;
    }
	/**
	 * start main loop
	 */
    public void start() throws Exception  {
    	int item;
    	do {
    		show();
    		item = getItem ();
    		switch (item) {
    		case 1 : doAddCourse(); break;
    		case 2 : doShowCourseByName(); break;
    		case 3 : doShowCourses(); break;
    		case 4 : doAddStudent(); break;
    		case 5 : doShowStudents(); break;
    		case 6 : doAddPoint(); break;
    		case 7 : doShowAVGStudent(); break;
    		}
    	}while ( item != 0);
    		System.out.println("Program is shutomg down. Good Bye");
    }
    
    /**
	 * add new record
	 */

	private void doAddCourse() throws Exception
    {
        Course course = new Course();
        System.out.print("Uzregistruokite nauja kursa > ");
        course.title = scanner.nextLine();
        
		CourseSQL sql = new CourseSQL();
		if (sql.addCourse(course)) {
			System.out.println("Added new record #" + course.course_id);
			
			System.out.println(course);
		}
		else
			System.out.println("Error");
    	
    }

	/**
	 * show course name
	 */
    private void doShowCourseByName () throws Exception 
    {
    	CourseSQL sql = new CourseSQL();
    	System.out.print("Iveskite kurso pavadinima, kuri norite patikrinti sarase > ");
    	String title = scanner.nextLine();
    	sql.isTitle(title);
    
    }	
    /**
	 * show courses
	 */
    private void doShowCourses () throws SQLException 
    {
    	System.out.println("Visi kursai");
    	CourseSQL sql = new CourseSQL();
    	sql.doShowCoursesList (sql.getCourses());
    }
    /**
     *  add student
     */
    private void doAddStudent() throws Exception
    {
    	Student student = new Student();
        System.out.println("Uzregistruokite nauja studenta");
        System.out.print("Iveskite Varda > ");
        student.name = scanner.nextLine();
        System.out.print("Iveskite emaila > ");
        student.email = scanner.nextLine();
                
		StudentSQL sql = new StudentSQL();
		if (sql.addUser(student)) {
			System.out.println("Added new record # " + student.student_id);
		}
		else
			System.out.println("Error");
    }
    /**
     * show students
     */
    private void doShowStudents() throws Exception 
    {
        System.out.println("Perziureti studentu sarasa.");
        StudentSQL sql = new StudentSQL();
        sql.doShowStudentsList(sql.getStudents());
    }
    /**
     * add point
     */
    private void doAddPoint() throws SQLException 
    {
        System.out.println("Ivesti nauja pazyma.");
        Point point = new Point();
        System.out.print("Iveskite nauja pazyma > ");
        point.point = scanner.nextInt();
        System.out.println("\n");
        System.out.print("Iveskine student_id, kam norite ivesti pazyma > ");
        point.student_id = scanner.nextInt();
        System.out.println("\n");
        System.out.println("Iveskite course_id, kokiam kursui norite ivesti pazyma > ");
        point.course_id = scanner.nextInt();
        
        PointSQL sql = new PointSQL();
        sql.addPoint(point);
        System.out.println(point);
    }
    /**
     *  show avg student
     */
	private void doShowAVGStudent() throws SQLException {
		Student student = new Student();
		System.out.println("Pasirinkti studetnto ID, vidurkio tikrinimui");
		System.out.print("Iveskite studento ID > ");
		student.student_id = scanner.nextInt();
		
		StudentSQL sql = new StudentSQL();
		sql.showAVGStudent(student);
		
		
	}

    
    @SuppressWarnings("unused")
	private String getErrorText (String error)
    {
        switch (error)
        {
            case "course not found"  : return "Kursas nerastas"; 
            case "student not found" : return "Studentas nerastas";
            case "invalid point"     : return "Pazyma turi buti nuo 1 iki 10";
            case "ok"                : return "Pazyma uzregistruota";
            default                  : return "Nepazistama klaida";
        }
    }
    

}
