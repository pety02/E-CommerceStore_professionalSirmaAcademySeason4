package model;

import interfaces.PaymentTool;

import java.util.Random;

/**
 *
 */
public class DebitCard implements PaymentTool {
    private static int cardNo;
    private int ID;
    private String IBAN;
    private double balance;
    private User owner;

    /**
     *
     * @param balance
     * @param owner
     */
    public DebitCard(double balance, User owner) {
        this.setID();
        this.generateIBAN();
        this.setBalance(balance);
        this.setOwner(owner);
    }

    /**
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     *
     */
    public void setID() {
        this.ID = ++DebitCard.cardNo;
    }

    /**
     *
     * @return
     */
    public String getIBAN() {
        return IBAN;
    }

    /**
     *
     */
    public void generateIBAN() {
        StringBuilder sb = new StringBuilder("BGN");
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }

        this.IBAN = sb.toString();
    }

    /**
     *
     * @return
     */
    public double getBalance() {
        return balance;
    }

    /**
     *
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     *
     * @return
     */
    public User getOwner() {
        return owner;
    }

    /**
     *
     * @param owner
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     *
     * @param total
     * @throws IllegalArgumentException
     */
    @Override
    public void pay(double total) throws IllegalArgumentException {
        if(total <= this.balance) {
            this.balance -= total;
            System.out.printf("Successful debit card payment transaction with IBAN: %s.%n", this.IBAN);
        } else {
            throw new RuntimeException("Cannot execute transaction! No enough money.");
        }
    }
}