import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> cards;
    private ArrayList<Card> hand;
    private int points;

    public Player(String name) {
        this.name = name;
        points = 0;
        cards = new ArrayList<Card>();
        hand = new ArrayList<Card>();
    }

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = hand;
        cards = new ArrayList<Card>();
        points = 0;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Card> getHand() { return hand; }

    public int getPoints() { return points; }

    public void addPoints(int points) {
        this.points += points;
    }

    public void addCard(Card card) { hand.add(card); }

    public String toString() {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }
}
