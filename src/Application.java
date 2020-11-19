import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        State e0 = new State("E0");
        State h1 = new State("H1");
        State h2 = new State("H2");
        State h = new State("H");
        State m1 = new State("M1");
        State m2 = new State("M2");
        State m = new State("M");

        e0.addTransition('0', h1);
        e0.addTransition('1', h1);
        e0.addTransition('2', h2);
        // etc...

        Automate automateTest = new Automate("HH:MM", e0, m);
        automateTest.addState(e0);
        automateTest.addState(h1);
        //etc..


        Automate hhmmm = Automate.createAutomateFromFile("hhmm.csv", "HH:MM", ',', "#");
        System.out.println(hhmmm.verify("00:00"));



    }
}
