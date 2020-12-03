import java.util.HashMap;
import java.util.Set;

public class State {

    private String name;
    private HashMap<String, State> transistions;

    /**
     * Method to declare a new empty state (without any transitions)
     * @param name the name of the State
     *             ex : "E0" for the initial state
     */
    public State(String name){
        this.name = name;
        this.transistions = new HashMap<>();
    }

    /**
     * Method to get the next state by taking tansition c
     * @param s transition to take
     * @return null if they is no transistion else the state
     */
    public State getNextState(String s) {
        return this.transistions.get(s);
    }

    /**
     * Method to compare to know if the given state is equals to the current one
     * @param o state to compare
     * @return true if both have same name
     */
    public boolean equals(Object o) {
        return o instanceof State && ((State) o).name.equals(this.name);
    }

    /**
     * Method to get the string value of the current state
     * @return string value of the current state
     */
    public String toString() {
        String state = this.name + " : | ";
        Set<String> keys = this.transistions.keySet();
        for (String c : keys) {
            state += c + " => " + this.transistions.get(c).name + " | ";
        }
       return state;
    }

    /**
     * Method to add a transition between the current state and the state by taking the value
     * @param value value for the transition
     * @param state state of the transition
     */
    public void addTransition(String value, State state) {
        this.transistions.put(value, state);
    }


}
