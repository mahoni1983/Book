/**
 * Main program
 * 
 * @author Dmitrij Starikovic
 */
public class Program
{
	/**
	 * Main function
	 * @param args console
	 */

    public static void main (String[] args) throws Exception 
    {
        System.out.println(DBase.getInstance().getConnection());
    	Menu menu = new Menu ();
        menu.start();
    }

}
