import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Automate {

    private String name;
    private State initialState;
    private ArrayList<State> finalStates;
    private ArrayList<State> delta;

    /**
     * Method to declare a new automate
     * @param name name of the automate
     * @param initialState initial state of the automate
     */
    public Automate(String name, State initialState) {
        this.name = name;
        this.initialState = initialState;
        this.finalStates = new ArrayList<>();
        this.delta = new ArrayList<>();
        this.delta.add(initialState);
    }

    /**
     * Method to get the automate's name
     * @return name of the automate
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to add state to the current delta
     * @param state state to add
     */
    public void addState(State state) {
        this.delta.add(state);
    }

    /**
     * Method to add final state to the current automate
     * @param state state to add
     */
    public void addFinalState(State state) {
        this.finalStates.add(state);
        this.delta.add(state);
    }

    /**
     * Method to veify if s is accepted by the automate
     * @param s string to verify
     * @return true if if s is accepted by the automate
     */
    public boolean verify(String s) {
        State currentState = initialState;

        char[] word = s.toCharArray();

        for (int i = 0; i < word.length; i++) {
            currentState = currentState.getNextState(String.valueOf(word[i]));
            // System.out.println(currentState); // Uncomment to print the path
            if (currentState == null) {
                return false;
            }
        }
        return this.finalStates.contains(currentState);
    }

    /**
     * Method to create an Automate from a csv file given.
     * @param file name of the file
     * @param separator the separator of the file
     *                  ex: ',' for csv files
     * @param transitionSeparator the separator of transitions
     *                            ex: "=>"
     *                            mean: X=>Y for X it's state Y
     * @return the automate
     * @throws IOException if there is a problem with opening and reading the file
     */
    public static Automate createAutomateFromCSVFile(String file, Character separator, String transitionSeparator) throws IOException {

        CSVReader reader = new CSVReader(new FileReader(file), separator);

        // Create the final automate and add all his initial state
        Automate automate = new Automate(reader.readNext()[0], new State(reader.readNext()[0].toUpperCase()));

        // Add final states
        for (String state : reader.readNext()) {
            automate.addFinalState(automate.getStateByName(state.toUpperCase()));
        }

        // Add states
        for (String state : reader.readNext()) {
            automate.addState(automate.getStateByName(state.toUpperCase()));
        }

        // For each states created, add all they transitions
        State fromState;
        String[] buffer, line;
        while ((line = reader.readNext()) != null){
            fromState = automate.getStateByName(line[0].toUpperCase());
            // Add all transistions
            for (int i = 1; i < line.length; i++) {
                buffer = line[i].split(transitionSeparator);
                fromState.addTransition(buffer[0],automate.getStateByName(buffer[1].toUpperCase()));
            }
        }
        return automate;
    }

    /**
     * Method to create an Automate from a Json file given.
     * @param file name of the file
     * @return the automate
     * @throws IOException if there is a problem with opening and reading the json file
     * @throws ParseException if the json file doesn't respect the json format
     */
    public static Automate createAutomateFromJsonFile(String file) throws IOException, ParseException {
        JSONObject automateObject = (JSONObject)new JSONParser().parse(new FileReader(file));

        // Name of the automate
        String name = (String) automateObject.get("name");

        // Initial state of the automate
        State initialState = new State(((String) automateObject.get("initialState")).toUpperCase());

        // Create the final automate with his initia state and name
        Automate automate = new Automate(name, initialState);

        // Add final states to the automate
        for (Object finalStateObject: (JSONArray) automateObject.get("finalStates")) {
            automate.addFinalState(automate.getStateByName(((String) finalStateObject).toUpperCase()));
        }

        // Add states to the automate
        for (Object stateObject: (JSONArray) automateObject.get("states")) {
            automate.addState(automate.getStateByName(((String) stateObject).toUpperCase()));
        }

        // Add all transistions between states
        State fromState, toState;
        for (Object stateObject: (JSONArray) automateObject.get("delta")) {
            JSONObject stateJSONObject = (JSONObject) stateObject;
            fromState = automate.getStateByName((String) stateJSONObject.get("fromState"));
            for (Object transitionObject: (JSONArray) stateJSONObject.get("to")) {
                JSONObject transitionJSONObject = (JSONObject) transitionObject;
                toState = automate.getStateByName((String) transitionJSONObject.get("toState"));
                for (Object valueObject: (JSONArray) transitionJSONObject.get("by")) {
                    fromState.addTransition((String) valueObject, toState);
                }
            }
        }
        return automate;
    }

    /**
     * Method to get a state by his name
     * @param name name of the state expected
     * @return the state expected or a new state if he doesn't exist yet
     */
    private State getStateByName(String name){
        for (State s:this.delta) {
            if (s.equals(new State(name))){
                return s;
            }
        }
        return new State(name);
    }
}
