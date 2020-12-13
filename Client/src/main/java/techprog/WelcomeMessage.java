package techprog;

public class WelcomeMessage {
    private String color;
    private int noPlayers;
    private String firstPlayer;

    public WelcomeMessage(String color, int noPlayers, String firstPlayer) {
        this.color = color;
        this.noPlayers = noPlayers;
        this.firstPlayer = firstPlayer;
    }

    public String getColor() {
        return color;
    }

    public int getNoPlayers() {
        return noPlayers;
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }
}