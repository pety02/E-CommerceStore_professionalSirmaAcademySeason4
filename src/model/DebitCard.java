package model;

import interfaces.PaymentTool;

import java.io.Serializable;
import java.util.Random;

/**
 * Debit card class
 */
public class DebitCard implements PaymentTool, Serializable {
    private static int cardNo;
    private int ID;
    private String IBAN;
    private double balance;
    private User owner;

    /**
     * Constructs debit card object by given balance nad owner.
     * @param balance the given balance
     * @param owner the given owner
     */
    public DebitCard(double balance, User owner) {
        this.setID();
        this.generateIBAN();
        try {
            this.setBalance(balance);
        } catch (IllegalArgumentException ex) {
            ex.fillInStackTrace();
            this.setBalance(0.00);
        }
        this.setOwner(owner);
    }

    /**
     * ID Getter
     * @return the id of the current debit card.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the id of the current debit card as next debit cards number.
     */
    public void setID() {
        this.ID = ++DebitCard.cardNo;
    }

    /**
     * IBAN Getter
     * @return the current debit card's IBAN.
     */
    public String getIBAN() {
        return IBAN;
    }

    /**
     * Generates new IBAN as sequence of "BGN" and 10 random digits.
     * It is not the right way for generating IBANs because you can
     * easily receive identical IBANs but it is enough for educational
     * purposes.
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
     * Balance Getter
     * @return the current debit card's balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the given balance to the current debit card's balance.
     * @param balance the given balance
     * @throws IllegalArgumentException if the given balance is negative floating point number.
     */
    public void setBalance(double balance) throws IllegalArgumentException {
        if(0.00 <= balance) {
            this.balance = balance;
        } else {
            throw new IllegalArgumentException("Balance should be positive floating point number.");
        }
    }

    /**
     * Owner Getter
     * @return the owner of the current debit card.
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the current debit card.
     * @param owner the given owner
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Paying an amount of money.
     * @param total the given amount of money
     * @throws IllegalArgumentException when the amount of money that should be
     * paid is bigger than the owner debit card's balance.
     */
    @Override
    public void pay(double total) throws RuntimeException {
        if(total <= this.balance) {
            this.balance -= total;
            System.out.printf("Successful debit card payment transaction with IBAN: %s. Your current balance is: %.2f%n", this.IBAN, this.balance);
        } else {
            throw new RuntimeException("Cannot execute transaction! No enough money.");
        }
    }

    /**
     * Predefined toString method
     * @return the representation of the debit card as a string.
     */
    @Override
    public String toString() {
        return "DebitCard { " +
                "ID: " + ID +
                ", IBAN: '" + IBAN + '\'' +
                ", balance: " + balance +
                ", owner: " + owner +
                " }";
    }
}