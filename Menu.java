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
			doShowCourses();
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

	private void doAddStudent() throws Exception {
		System.out.println("Registruoti nauja studenta.");
	}

	private void doShowStudents() throws Exception {
		System.out.println("Perziureti studentu sarasa.");
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
