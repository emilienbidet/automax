import java.io.IOException;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) throws IOException {

        // HH:MM automate
        Automate hhmm = Automate.createAutomateFromFile("hhmm.csv", "HH:MM", ',', "#");
        System.out.println(hhmm.verify("21:30")); // Time to sleep

        // Smiley automate
        Automate smileys = Automate.createAutomateFromFile("smileys.csv", "Smileys", ',', "#");
        System.out.println(smileys.verify(":-)")); // Be happy

        // JJ/MM/AAAA automate
        Automate jjmmaa = Automate.createAutomateFromFile("jjmmaa.csv", "JJ/MM/AAAA", ',', "#");
        System.out.println(jjmmaa.verify("02/07/2052")); // There may be aliens 👽

        // HH:MM:SS automate
        Automate hhmmss = Automate.createAutomateFromFile("hhmmss.csv", "HH:MM:SS", ',', "#");
        System.out.println(hhmmss.verify("16:00:00")); // Time to snack ^^

        // email automate
        Automate email = Automate.createAutomateFromFile("email.csv", "email", ',', "#");
        System.out.println(email.verify("emilien-bidet@gmail.com")); // Secret agent email

        // email list automate
        Automate emailList = Automate.createAutomateFromFile("emailList.csv", "emailList", ',', "#");
        System.out.println(emailList.verify("emilien-bidet@gmail.com;aRealSpy@gouv.fr")); // Secret agent emails

        // polynomials
        Automate polynomials = Automate.createAutomateFromFile("polynomials.csv", "polynomials", ',', "#");
        System.out.println(polynomials.verify("4X**2 + 2X - 14")); // Love potion formula
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

        // Declare the automate
        Automate automateTest = new Automate("00 or 11", e0, m);

        // Finaly, add all states to the automate declared before
        automateTest.addState(e0);
        automateTest.addState(h1);
        automateTest.addState(h2);
        automateTest.addState(m);
    }
}
