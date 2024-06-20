package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 *
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
     *
     * @param dateTime
     * @param user
     * @param debitCards
     * @param paypalWllets
     * @param order
     * @param paymentType
     */
    public PaymentProcessor(LocalDateTime dateTime, User user, LinkedList<DebitCard> debitCards, LinkedList<PaypalWallet> paypalWllets, Order order, PaymentType paymentType) {
        this.setID();
        this.setDateTime(dateTime);
        this.setUser(user);
        this.userDebitCards = debitCards;
        this.userPaypalWallets = paypalWllets;
        this.setOrder(order);
        this.isSuccess = false;
        this.setPaymentType(paymentType);
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
        this.ID = ++PaymentProcessor.paymentNo;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    public Order getOrder() {
        return order;
    }

    /**
     *
     * @param order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     *
     * @return
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     *
     * @param paymentType
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     *
     * @param dateTime
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     *
     * @return
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     *
     * @return
     */
    public LinkedList<DebitCard> getUserDebitCards() {
        return userDebitCards;
    }

    /**
     *
     * @param userDebitCards
     */
    public void setUserDebitCards(LinkedList<DebitCard> userDebitCards) {
        this.userDebitCards = userDebitCards;
    }

    /**
     *
     * @return
     */
    public LinkedList<PaypalWallet> getUserPaypalWallets() {
        return userPaypalWallets;
    }

    /**
     *
     * @param userPaypalWallets
     */
    public void setUserPaypalWallets(LinkedList<PaypalWallet> userPaypalWallets) {
        this.userPaypalWallets = userPaypalWallets;
    }

    /**
     *
     * @param IBAN
     */
    public void makeTransaction(String IBAN) {
        if(this.user.getTotalMoney() <= this.order.getTotalPrice()) {
            if(this.paymentType == PaymentType.DEBIT_CARD) {
                this.payByDebitCard(IBAN);
            } else if(this.paymentType == PaymentType.PAYPAL) {
                this.payByPaypalWallet(IBAN);
            } else {
                isSuccess = false;
                throw new IllegalArgumentException("Unknown payment type.");
            }
            this.isSuccess = true;
        } else {
            this.isSuccess = false;
        }
    }

    /**
     *
     * @return
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