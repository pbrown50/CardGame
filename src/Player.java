import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> cards;
    private Card[] hand;
    private int points;

    Public player(String name) {
        this.name = name;
        points = 0;
    }

    Public player(String name, Card[] hand) {
        this.name = name;
        for (int i = 0; i < hand.length; i++) {
            this.hand[i] = hand[i];
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card[] getHand() { return hand; }

    public int getPoints() { return points; }

    public void addPoints(int points) {
        this.points += points;
    }

    public void addCard(Card card) { hand[hand.length] = card; }

    public String toString() {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }
}
