import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Automate {

    private String name;
    private State initialState;
    private State finalState;
    private ArrayList<State> delta;

    public Automate(String name, State initialState, State finalState) {
        this.name = name;
        this.initialState = initialState;
        this.finalState = finalState;
        this.delta = new ArrayList<>();
    }

    public void addState(State state) {
        delta.add(state);
    }

    public void addAllStates(ArrayList<State> states) {
        delta.addAll(states);
    }

    /**
     * Method to create an Automate from the file given.
     * @param file the name of the file
     * @param name the name of the automate
     * @param separator the separator of the file
     *                  ex: ',' for csv files
     * @param transitionSeparator the separator of transitions
     *                            ex: "=>"
     *                            mean: X=>Y for X it's state Y
     * @return the automate
     * @throws IOException if there is a problem with opening the file
     */
    public static Automate createAutomateFromFile(String file, String name, Character separator, String transitionSeparator) throws IOException {

        // Create and add all states to the delta array.
        CSVReader reader = new CSVReader(new FileReader(file), separator);
        ArrayList<State> delta = new ArrayList<>();
        String[] line = null;
        while ((line = reader.readNext()) != null){
            delta.add(new State(line[0].toUpperCase()));
        }

        // For each states created, add all they transitions
        State state;
        State transState;
        String[] buffer;
        reader = new CSVReader(new FileReader(file), separator);
        int lineCounter = 0;
        while ((line = reader.readNext()) != null) {
            state = delta.get(lineCounter);
            // Add all transistions
            for (int i = 1; i < line.length; i++) {
                buffer = line[i].split(transitionSeparator);
                transState = new State(buffer[1]);
                for (State s:delta) {
                    if (s.equals(transState)){
                        transState = s;
                    }
                }
                state.addTransition(buffer[0].charAt(0), transState);
            }
            lineCounter++;
        }

        // Create the automate and return it
        Automate automate = new Automate(name, delta.get(0), delta.get(delta.size()-1));
        automate.addAllStates(delta);
        return automate;
    }
}
