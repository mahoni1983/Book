import java.util.Scanner;
/**
 * menu and user interface.
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

		scanner.close();
		return a;
	}

	public int getItem() {
		return 0;
	}

	public void start() throws Exception {
		show();
		int menuItem = getMenuItem();
		switch (menuItem)
		{
			case 1: doAddCourse(); break;		
			case 2: doShowCourses(); break;
			case 3: doShowCourses(); break;
			case 4: doShowStudents(); break;	
			case 5: doAddStudent(); break;	
			case 0: break;
		}	
	}

	private void doAddCourse() throws Exception {
		System.out.println("Registruoti nauja kursa.");
	}

	private void doShowCourses() throws Exception {
		System.out.println("Perziureti visus kursus.");
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
