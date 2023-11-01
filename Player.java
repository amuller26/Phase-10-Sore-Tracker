public class Player {
    private final String name;
    private int score;
    private int phase;

    public Player(String name) {
        this.name = name;
        score = 0;
        phase = 1;
    }

    public void addScore(int num) {
        score += num;
    }

    public void addPhase() {
        phase++;
    }

    public int getScore() {
        return score;
    }

    public int getPhase() {
        return phase;
    }

    public String toString() {
        return name;
    }
}
