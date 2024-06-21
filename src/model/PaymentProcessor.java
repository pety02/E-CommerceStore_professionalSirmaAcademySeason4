package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * Payment processor class
 */
public class PaymentProcessor implements Serializable {
    private static int paymentNo = 0;
    private int ID;
    private LocalDateTime dateTime;
    private User user;
    private LinkedList<DebitCard> userDebitCards;
    private LinkedList<PaypalWallet> userPaypalWallets;
    private Order order;
    private PaymentType paymentType;
    private boolean isSuccess;

    private void payByDebitCard(String IBAN) {
        for(DebitCard currentDebitCard : this.userDebitCards) {
            if(currentDebitCard.getIBAN().equals(IBAN)) {
                try {
                    currentDebitCard.pay(this.order.getTotalPrice());
                } catch (RuntimeException ex) {
                    ex.fillInStackTrace();
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    private void payByPaypalWallet(String IBAN) {
        for(PaypalWallet currentPaypalWallet : this.userPaypalWallets) {
            if(currentPaypalWallet.getIBAN().equals(IBAN)) {
                try {
                    currentPaypalWallet.pay(this.order.getTotalPrice());
                } catch (IllegalArgumentException ex) {
                    ex.fillInStackTrace();
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    /**
     * Constructs payment processor object by given dateTime, customer, customer's debit cards,
     * customer's paypal wallets, order object and payment type, preferred by the customer.
     * @param dateTime the given dateTime
     * @param user the given customer
     * @param debitCards the given customer's debit cards list
     * @param paypalWallets the given customer's paypal wallets list
     * @param order the given order object
     * @param paymentType the given payment type, preferred by the customer
     */
    public PaymentProcessor(LocalDateTime dateTime, User user, LinkedList<DebitCard> debitCards, LinkedList<PaypalWallet> paypalWallets, Order order, PaymentType paymentType) {
        this.setID();
        this.setDateTime(dateTime);
        this.setUser(user);
        this.setUserDebitCards(debitCards);
        this.setUserPaypalWallets(paypalWallets);
        this.setOrder(order);
        this.isSuccess = false;
        this.setPaymentType(paymentType);
    }

    /**
     * Payment processor id Getter
     * @return the current payment processor object's id.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the current payment processor object's id as a next payment number.
     */
    public void setID() {
        this.ID = ++PaymentProcessor.paymentNo;
    }

    /**
     * Payment processor object's user Getter
     * @return the current payment processor object's user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the current payment processor object's user.
     * @param user the given user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Payment processor object's order Getter
     * @return the current payment processor object's order.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the current payment processor object's order.
     * @param order the given order.
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Payment processor object's payment type Getter
     * @return the current payment processor object's payment type.
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the current payment processor object's payment type
     * @param paymentType the given payment type.
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Payment processor object's dateTime Getter
     * @return the current payment processor object's fateTime.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets the current payment processor object's dateTime.
     * @param dateTime the given dateTime.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Payment processor object's isSuccess Getter
     * @return the current payment processor object's isSuccess.
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * Payment processor object's user's debit cards list Getter.
     * @return the current payment processor object's user's debit cards list.
     */
    public LinkedList<DebitCard> getUserDebitCards() {
        return userDebitCards;
    }

    /**
     * Sets the current payment processor object's user's debit cards list.
     * @param userDebitCards the given user's debit cards list.
     */
    public void setUserDebitCards(LinkedList<DebitCard> userDebitCards) {
        this.userDebitCards = userDebitCards;
    }

    /**
     * Payment processor object's user's paypal wallets list Getter
     * @return the current payment processor object's user's paypal wallets list.
     */
    public LinkedList<PaypalWallet> getUserPaypalWallets() {
        return userPaypalWallets;
    }

    /**
     * Sets the current payment processor object's user's paypal wallets list.
     * @param userPaypalWallets the given user's paypal wallets list
     */
    public void setUserPaypalWallets(LinkedList<PaypalWallet> userPaypalWallets) {
        this.userPaypalWallets = userPaypalWallets;
    }

    /**
     * Makes an paying transaction by given IBAN.
     * @param IBAN the given IBAN
     */
    public void makeTransaction(String IBAN) {
        if(this.user.getTotalMoney() <= this.order.getTotalPrice()) {
            if(this.paymentType == PaymentType.DEBIT_CARD) {
                this.payByDebitCard(IBAN);
            } else if(this.paymentType == PaymentType.PAYPAL) {
                this.payByPaypalWallet(IBAN);
            }
            this.isSuccess = true;
        } else {
            this.isSuccess = false;
        }
    }

    /**
     * Predefine toString() method
     * @return the representation of the payment processor object as a string.
     */
    @Override
    public String toString() {
        return "PaymentProcessor { " +
                "ID: " + ID +
                ", dateTime: " + dateTime +
                ", user: " + user +
                ", userDebitCards: " + userDebitCards +
                ", userPaypalWallets: " + userPaypalWallets +
                ", order: " + order +
                ", paymentType: " + paymentType +
                ", isSuccess: " + isSuccess +
                " }";
    }
}