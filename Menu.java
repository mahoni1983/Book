import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * menu and user interface.
 * 
 * @author Eugene
 * 
 */
public class Menu {
	Scanner scanner = new Scanner(System.in);

	public void show() {
		System.out.println();
		System.out.println("=== MENIU ===");
		System.out.println("1. Registruoti nauja kursa.");
		System.out.println("2. Perziureti visus kursus.");
		System.out.println("3. Registruoti nauja studenta.");
		System.out.println("4. Perziureti studentu sarasa.");
		System.out.println("5. Ivesti nauja pazyma.");
		System.out.println("0. Iseiti");
	}

	public int getMenuItem() {
		System.out.println("Please choose an option");
		// Scanner scanner = new Scanner(System.in);
		int a = -1;
		while (true) {
			try {
				a = Integer.parseInt(scanner.nextLine());
				if ((a >= 0) && (a < 6)) {
					System.out.println("Your choice is: " + a);
					break;
				}
			} catch (Exception e) {
				a = -1;
			}
			System.out.println("Wrong choice, try again");
		}

	//	scanner.close();
		return a;
	}

	public int getItem() {
		return 0;
	}

	public void start() throws Exception {
		show();
		int menuItem = getMenuItem();
		switch (menuItem) {
		case 1:
			doAddCourse();
			break;
		case 2:
			doShowCourses();
			break;
		case 3:
			doAddStudent();
			break;
		case 4:
			doShowStudents();
			break;
		case 5:
			doAddStudent();
			break;
		case 0:
			break;
		}
	}

	private void doAddCourse() throws Exception {
		System.out.println("Registruoti nauja kursa.");
		System.out.println("Type in a new course title");
		Course newCourse = new Course();
		newCourse.title = scanner.nextLine();
		CourseSQL courseSQL = new CourseSQL();
		if (!courseSQL.isTitle(newCourse.title)) {
			newCourse.course_id = courseSQL.addCourse(newCourse);
			if (newCourse.course_id != -1)
				System.out.println("Course " + newCourse.title
						+ " has been added successfully, the id is: "
						+ newCourse.course_id);
		}
		else System.out.println("Course " + newCourse.title + " already exists");
	}
/**
 * to show all courses 
 * @throws Exception
 */
	private void doShowCourses() throws Exception {
		System.out.println("Perziureti visus kursus.");
		CourseSQL courseSQL = new CourseSQL();
		ArrayList<Course> courseList = new ArrayList<Course>();
		courseList = courseSQL.getCourses();
		System.out.println("Id\tTitle" );
		for (Course course: courseList)
		{
			System.out.println(course.course_id + "\t" + course.title);
		}
	}
	/**
	 * to add a student to DB
	 * @throws Exception
	 */
	private void doAddStudent() throws Exception {
		System.out.println("Registruoti nauja studenta.");
		Student newStudent = new Student();
		System.out.println("Type student's name");
		newStudent.name = scanner.nextLine();
		System.out.println("Type student's email");
		newStudent.email = scanner.nextLine();
		StudentSQL studentSQL = new StudentSQL();
		if (!studentSQL.isEmail(newStudent.email))
		{	
			studentSQL.addStudent(newStudent);
			System.out.println("The new student has been added.");
		}
		else System.out.println("There is a student with this email.");
	}
/**
 * show student list
 * @throws Exception
 */
	private void doShowStudents() throws Exception {
		System.out.println("Perziureti studentu sarasa.");
		StudentSQL studentSQL = new StudentSQL();
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList = studentSQL.getStudents();
		System.out.println("student_id\tname\temail\tavg_point");
		float maxAvgPoint = -1;
		for (Student student: studentList)
		{
			System.out.println("" + student.student_id + "\t" + student.name + "\t"
					+ student.email + "\t" + student.avg_point + "\t");
			if (student.avg_point > maxAvgPoint) maxAvgPoint = student.avg_point;
		}
		System.out.println("The maximum avg point is " + maxAvgPoint);
	}

	private void doAddPoint() {
		System.out.println("Ivesti nauja pazyma.");
	}

	private String getErrorText(String error) {
		switch (error) {
		case "course not found":
			return "Kursas nerastas";
		case "student not found":
			return "Studentas nerastas";
		case "invalid point":
			return "Pazyma turi buti nuo 1 iki 10";
		case "ok":
			return "Pazyma uzregistruota";
		default:
			return "Nepazistama klaida";
		}
	}

}
