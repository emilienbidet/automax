import java.util.HashMap;

public class State {

    private String name;
    private HashMap<Character, State> transistions;

    public State(String name){
        this.name = name;
        this.transistions = new HashMap<>();
    }

    public void addTransition(Character value, State state) {
        this.transistions.put(value, state);
    }
}
