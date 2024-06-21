package model;

import interfaces.PaymentTool;

import java.io.Serializable;
import java.util.Random;

/**
 * Paypal wallet class
 */
public class PaypalWallet implements PaymentTool, Serializable {
    private static int cardNo;
    private int ID;
    private String IBAN;
    private String name;
    private double balance;
    private User owner;

    /**
     * Constructs paypal wallet object by name, balance and wallet.
     * @param name the given name
     * @param balance the given balance
     * @param owner the given owner
     */
    public PaypalWallet(String name, double balance, User owner) {
        this.setID();
        this.generateIBAN();
        this.setName(name);
        this.setBalance(balance);
        this.setOwner(owner);
    }

    /**
     * Id Getter
     * @return the paypal wallet's id.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the paypal wallet's id.
     */
    public void setID() {
        this.ID = ++PaypalWallet.cardNo;
    }

    /**
     * Name Getter
     * @return the paypal wallet's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the paypal wallet's name.
     * @param name the name of the paypal wallet.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Balance Getter
     * @return the balance of the paypal wallet's.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the paypal wallet's.
     * @param balance the give balance.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Owner Getter
     * @return the owner of the paypal wallet's/
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the paypal wallet's.
     * @param owner the owner of the paypal wallet's.
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * IBAN Getter
     * @return the IBAN of the paypal wallet's.
     */
    public String getIBAN() {
        return IBAN;
    }

    /**
     * Generates new IBAN as sequence of "BGN" and 10 random digits.
     * It is not the right way for generating IBANs because you can
     * easily receive identical IBANs, but it is enough for educational
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
     * Paying an amount of money.
     * @param total the given amount of money
     * @throws IllegalArgumentException when the amount of money that should be
     * paid is bigger than the owner debit card's balance.
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
     * Predefined toString() method
     * @return the representation of the paypal wallet as a string.
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