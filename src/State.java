import java.util.HashMap;
import java.util.Set;

public class State {

    private String name;
    private HashMap<Character, State> transistions;

    public State(String name){
        this.name = name;
        this.transistions = new HashMap<>();
    }

    public boolean equals(Object o) {
        return o instanceof State && ((State) o).name.equals(this.name);
    }

    public String toString() {
        String state = name + " : | ";
        Set<Character> keys = transistions.keySet();
        for (Character c : keys) {
            state += c.toString() + " => " + transistions.get(c).name + " | ";
        }
       return state;
    }

    public void addTransition(Character value, State state) {
        this.transistions.put(value, state);
    }


}
