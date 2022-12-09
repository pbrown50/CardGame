import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players;
    private Deck cards;
    private Player user;
    private Player dealer;
    private boolean stay = false;

    public Game() {
        players = new ArrayList<Player>();
        Scanner s = new Scanner(System.in);
        System.out.println("Enter name: ");
        user = new Player(s.nextLine());
        dealer = new Player("Dealer");
        players.add(user);
        players.add(dealer);
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "8", "10", "J", "Q", "K"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        cards = new Deck(ranks, suits, values);
    }

    public static void main(String[] args) {
        Game a = new Game();
        a.playGame();
    }

    public void playGame() {
        printInstructions();
        for (int i = 0; i < 5; i++) {
            dealCards();
            printCards();
            int count = 0;
            while(!isOver()) {
                round();
                count++;
            }
            printRoundWinner(count);
            clearHand();
        }
    }

    public void printResult() {
        if (user.getPoints() > dealer.getPoints()) {
            System.out.println("You beat the dealer by " + (user.getPoints() - dealer.getPoints()) + " rounds!");
        } else {
            System.out.println("You lost to the dealer by " + (dealer.getPoints() - user.getPoints()) + " rounds.");
        }
    }

    public boolean isOver() {
        if (stay == true) {
            return true;
        }
        else if (getTotal(user.getHand()) >= 21) {
            return true;
        }
        else {
            return false;
        }
    }

    public void dealCards() {
        dealer.addCard(cards.deal());
        dealer.addCard(cards.deal());
        user.addCard(cards.deal());
        user.addCard(cards.deal());
    }

    public void round() {
        Scanner s = new Scanner(System.in);
        System.out.println("\nWould you like to hit (type 'h') or stay (type 's')?");
        if (s.nextLine().equals("h")) {
            user.addCard(cards.deal());
        }
        else if (s.nextLine().equals("s")) {
            stay = true;
        }
    }

    public void printRoundWinner(int count) {
        if (count != 0) {
            if (getTotal(user.getHand()) > 21) {
                if (getTotal(dealer.getHand()) > 21) {
                    System.out.println("You and the dealer both busted! Draw!");
                }
                else {
                    System.out.println("You Busted! Dealer wins!");
                    dealer.addPoints(1);
                }
            }
            else if (getTotal(user.getHand()) == 21) {
                if (getTotal(dealer.getHand()) == 21) {
                    System.out.println("You and the dealer both hit 21! Draw!");
                }
                else {
                    System.out.println("Blackjack! You hit 21!");
                    user.addPoints(1);
                }
            }
            else if (getTotal(user.getHand()) < 21) {
                if (getTotal(user.getHand()) > getTotal(dealer.getHand())) {
                    System.out.println("You win!");
                    user.addPoints(1);
                }
                else if (getTotal(user.getHand()) == getTotal(dealer.getHand())) {
                    System.out.println("Draw!");
                }
                else {
                    System.out.println("Dealer wins!");
                    dealer.addPoints(1);
                }
            }
        }
        //print winner of round
        //increase points of winner
    }

    public void printCards() {
        System.out.println("\nDealers first card:\n" + dealer.getHand().get(0));
        System.out.println("\nYour Cards:\n" + user.getHand());
    }
    public void clearHand() {
        for (int i = 0; i < user.getHand().size(); i++) {
            user.getHand().remove(i) ;
        }
        stay = false;
    }
    public void printInstructions() {
        System.out.println("< B L A C K     J A C K >\nInstructions: https://en.wikipedia.org/wiki/Blackjack\n\n");
    }

    public int getTotal(ArrayList<Card> hand) {
        int total = 0;
        for (int i = 0; i < hand.size(); i++) {
            total += hand.get(i).getPoint();
        }
        return total;
    }
}
            //for each round
                //if last round print winner
                //if not last round continue with round
                    //deal cards to dealer and user and tell them their cards and one card of dealers hand
                    //check for bust or blackjack and end round if these occur and tell them and add points
                    //if still below 21 as hit or stay
                        //if hit, add a card to their hand and print
                            //check total again and repeat
                        //if stay check total vs dealer and add points and move onto next round