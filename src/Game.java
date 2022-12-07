import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players;
    private ArrayList<Card> cards;

    Public Game() {
        players = new ArrayList<Player>();
        Scanner s = new Scanner(System.in);
        System.out.println("Enter name: ");
        Player user = new Player(s.nextLine());
        Player dealer = new Player("Dealer");
        players.add(user);
        players.add(dealer);
        cards = new Deck({"A", "2", "3", "4", "5", "6", "7", "8", "8", "10", "J", "Q", "K"}, {"Hearts", "Diamonds", "Clubs", "Spaids"}, {"1", "2", "3", "4", "5", "6", "7", "8", "8", "10", "10", "10", "10"});
    }

    public static void main(String[] args) {
        Game a = new Game();
        playGame();
    }

    public void playGame() {
        printInstructions();
        for (int i = 0; i < 5; i++) {
            //play a round, if it is the fifth round print winner
            if (i == 4) {
                winner();
            }
            else {
                round();
            }
            System.out.println("The dealer has drawn two cards. Their first card is a " + dealer.hand[0] + ".");
            System.out.println("You were dealt two cards: " + user.hand[0] + " and " + user.hand[1] ".");
            System.out.println(getTotal(user.getHand()));
            Scanner s = new Scanner(System.in);
            System.out.println("Would you like to hit (type 'h') or stay (type 's')?");
            if (s.nextLine().equals("h")) {
                user.addCard(cards.deal());
                System.out.println(getTotal(user.getHand()));


            }

        }
    }

    public void round() {
        dealCards();
        System.out.println("The dealer has drawn two cards. Their first card is a " + dealer.hand[0] + ".");
        System.out.println("You were dealt two cards: " + user.hand[0] + " and " + user.hand[1] ".");
        System.out.println(getTotal(user.getHand()));
        Scanner s = new Scanner(System.in);
        if (s.nextLine().equals("h")) {
            user.addCard(cards.deal());
            System.out.println(getTotal(user.getHand()));
        }
    }

    public void dealCards() {
        dealer.addCard(cards.deal());
        dealer.addCard(cards.deal());
        user.addCard(cards.deal());
        user.addCard(cards.deal());
    }
    public void printInstructions() {
        System.out.println("< B L A C K     J A C K >\n\nInstructions: ");
    }

    public String getTotal(Card[] cards) {
        int total = 0;
        for (int i = 0; i < cards.length; i++) {
            total += cards[i].getRank();
        }
        if (total > 21) {
            return "You busted with a total of " + total "!";
        }
        else if (total == 21) {
            return "Black Jack! You win!";
        }
        else if (total < 21) {
            return "You have a total of " + total + ". Would you like to hit (h) or stay (s)?";
        }
    }

    public void winner() {
        if (user.getPoints() > dealer.getPoints) {
            System.out.println("You beat the dealer by " + (user.getPoints() - dealer.getPoints()) + " rounds!");
        }
        else {
            System.out.println("You lost to the dealer by " + (dealer.getPoints() - user.getPoints()) + " rounds.");
        }
    }


}
