import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Application class made to run the menu and test automates
 */
public class Application {
    public static void main(String[] args) throws IOException, ParseException {

        // Add automatons to the list
        ArrayList<Automate> automatons = new ArrayList<>();
        automatons.add(Automate.createAutomateFromCSVFile("automatons/csv/hhmm.csv", ',', "#"));
        automatons.add(Automate.createAutomateFromCSVFile("automatons/csv/smileys.csv", ',', "#"));
        automatons.add(Automate.createAutomateFromCSVFile("automatons/csv/jjmmaa.csv", ',', "#"));
        automatons.add(Automate.createAutomateFromCSVFile("automatons/csv/hhmmss.csv", ',', "#"));
        automatons.add(Automate.createAutomateFromCSVFile("automatons/csv/email.csv", ',', "#"));
        automatons.add(Automate.createAutomateFromCSVFile("automatons/csv/emailList.csv", ',', "#"));
        automatons.add(Automate.createAutomateFromCSVFile("automatons/csv/polynomials.csv", ',', "#"));

        // Uncomment to test automatons from tp with different types of files
        // testCsvautomatons();
        // testJsonautomatons();

        // Run the application
        runApplication(automatons);
    }

    /**
     * Method to run the application
     * @param automatons automatons to list
     */
    public static void runApplication(ArrayList<Automate> automatons) {
        // Get the user's choice
        int choice = getAutomateChoice(getMainMenu(automatons), 1, automatons.size() + 1);
        String stringToAnalyse = "";
        Automate currentAutomate;
        // Analyse the string with the choosed automate
        // Stop the application if the user ask
        while (choice != automatons.size() + 1) {
            currentAutomate = automatons.get(choice - 1);
            stringToAnalyse = getStringToAnalyse(getAutomateMenu(currentAutomate));
            System.out.println("Résultat : " + (currentAutomate.verify(stringToAnalyse) ? "OK": "KO"));
            choice = getAutomateChoice(getMainMenu(automatons), 1, automatons.size() + 1);
        }
        System.out.println("Bye.");
    }

    /**
     * Method to get the user's choice depend on the min and max int choices
     * @param choices the menu to print for the user
     * @param min int min choice
     * @param max int max choice
     * @return
     */
    private static int getAutomateChoice(String choices, int min, int max) {
        Scanner sc = new Scanner(System.in);

        // Print choices and let user write a first answer
        System.out.println(choices);
        String input = sc.nextLine();

        // Loop while the answer is not an expected answer
        // Print choices and let user write another answer
        while (!isIntegerBetween(input, min, max)) {
            System.out.println(choices);
            input = sc.nextLine();
        }
        return Integer.parseInt(input);
    }

    /**
     * Method to get the string to analyse and to print the automate sub menu.
     * @return string to analyse
     */
    private static String getStringToAnalyse(String message) {
        String answer = "";
        while (answer == "") {
            System.out.println(message);
            Scanner sc = new Scanner(System.in);
            answer = sc.nextLine();
        }
        return answer;
    }

    /**
     * Method to retunr the tp's menu
     * For each line you have a different automate
     * @param automatons automatons
     * @return main menu
     */
    public static String getMainMenu(ArrayList<Automate> automatons){
        String menu = "";
        menu += "---------------- Menu de mon TP ----------------\n";
        for (Automate automate : automatons) {
            menu += (automatons.indexOf(automate) + 1) + " - " + automate.getName() + "\n";
        }
        menu += (automatons.size() + 1) + " - Arrêt\n";
        menu += "Votre choix ?\n";
        menu += "Je vous demanderais ensuite la chaîne à analyser. Merci\n";
        menu += "------------------------------------------------";
        return menu;
    }

    /**
     * Metho to get the automate menu
     * @param automate automate
     * @return the menu of the automate
     */
    public static String getAutomateMenu(Automate automate) {
        String menu = "";
        menu += "---------------- Automate " + automate.getName() + " ----------------\n";
        menu += "Quelle est la chaîne à analyser svp ? \n";
        menu += "------------------------------------------------";
        return menu;
    }

    /**
     * Method to verify if the string is an integer between the two limits included
     * @param s string to verify
     * @param min low limit
     * @param max height limit
     * @return
     */
    private static boolean isIntegerBetween(String s, int min, int max) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        int value = Integer.parseInt(s);
        return value >= min && value <= max;
    }

    /**
     * Exemple method to show how to build an Automate
     */
    public static void myFirstAutomate() {

        // Creating all states
        State e0 = new State("E0");
        State h1 = new State("H1");
        State h2 = new State("H2");
        State m = new State("M");

        // Adding all transistions between states
        e0.addTransition("0", h1);
        e0.addTransition("1", h2);
        h1.addTransition("0", m);
        h2.addTransition("1", m);

        // Declare the automate with his initial state
        Automate automateTest = new Automate("00 or 11", e0);

        // Add automate's final states
        automateTest.addFinalState(m);

        // Finaly, add all states to the automate declared before
        automateTest.addState(e0);
        automateTest.addState(h1);
        automateTest.addState(h2);
        automateTest.addState(m);
    }

    /**
     * Method to test tp's automatons with csv files
     * @throws IOException the file can't be find
     */
    public static void testCsvAutomatons() throws IOException {
        // HH:MM automate
        Automate hhmm = Automate.createAutomateFromCSVFile("automatons/csv/hhmm.csv", ',', "#");
        System.out.println(hhmm.verify("21:30")); // Time to sleep

        // Smiley automate
        Automate smileys = Automate.createAutomateFromCSVFile("automatons/csv/smileys.csv", ',', "#");
        System.out.println(smileys.verify(":-)")); // Be happy

        // JJ/MM/AAAA automate
        Automate jjmmaa = Automate.createAutomateFromCSVFile("automatons/csv/jjmmaa.csv", ',', "#");
        System.out.println(jjmmaa.verify("02/07/2052")); // There may be aliens 👽

        // HH:MM:SS automate
        Automate hhmmss = Automate.createAutomateFromCSVFile("automatons/csv/hhmmss.csv", ',', "#");
        System.out.println(hhmmss.verify("16:00:00")); // Time to snack ^^

        // email automate
        Automate email = Automate.createAutomateFromCSVFile("automatons/csv/email.csv", ',', "#");
        System.out.println(email.verify("emilien-bidet@gmail.com")); // Secret agent email

        // email list automate
        Automate emailList = Automate.createAutomateFromCSVFile("automatons/csv/emailList.csv", ',', "#");
        System.out.println(emailList.verify("emilien-bidet@gmail.com;aRealSpy@gouv.fr")); // Secret agent emails

        // polynomials
        Automate polynomials = Automate.createAutomateFromCSVFile("automatons/csv/polynomials.csv", ',', "#");
        System.out.println(polynomials.verify("14X**2 + 25X - 25")); // Love potion formula
    }

    /**
     * Method to test tp's automatons with json files
     * @throws IOException the file can't be find
     * @throws ParseException the file is not build properly
     */
    public static void testJsonAutomatons() throws IOException, ParseException {
        // HH:MM automate
        Automate hhmm = Automate.createAutomateFromJsonFile("automatons/json/hhmm.json");
        System.out.println(hhmm.verify("21:30")); // Time to sleep
    }
}
