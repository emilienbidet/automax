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
}
