public class Application {
    public static void main(String[] args) {
        State e0 = new State("E0");
        State h1 = new State("H1");
        State h2 = new State("H2");
        State h = new State("H");
        State m1 = new State("M1");
        State m2 = new State("M2");
        State m = new State("M");

        e0.addTransition('0', h1);
        // etc...

        Automate hhmm = new Automate("HH:MM", e0, m);
        hhmm.addState(e0);
        hhmm.addState(h1);

    }
}
