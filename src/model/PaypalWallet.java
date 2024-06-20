package model;

import interfaces.PaymentTool;

import java.io.Serializable;
import java.util.Random;

/**
 *
 */
public class PaypalWallet implements PaymentTool, Serializable {
    private static int cardNo;
    private int ID;
    private String IBAN;
    private String name;
    private double balance;
    private User owner;

    /**
     *
     * @param name
     * @param balance
     * @param owner
     */
    public PaypalWallet(String name, double balance, User owner) {
        this.setID();
        this.generateIBAN();
        this.setName(name);
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
        this.ID = ++PaypalWallet.cardNo;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
     * @param total
     * @throws IllegalArgumentException
     */
    @Override
    public void pay(double total) throws IllegalArgumentException {
        if(total <= this.balance) {
            this.balance -= total;
            System.out.printf("Successful paypal wallet payment transaction with IBAN: %s and wallet name: %s.%n", this.IBAN, this.name);
        } else {
            throw new RuntimeException("Cannot execute transaction! No enough money.");
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "PaypalWallet { " +
                "ID: " + ID +
                ", IBAN: '" + IBAN + '\'' +
                ", name: '" + name + '\'' +
                ", balance: " + balance +
                ", owner: " + owner +
                " }";
    }
}