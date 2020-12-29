package techprog.client;

/**
 * Represents message which client receives at the beginning of the game.
 * Contains information about color, number of players and who begins the game.
 */
public class WelcomeMessage {
    private final String color;
    private final int noPlayers;
    private final String firstPlayer;

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
