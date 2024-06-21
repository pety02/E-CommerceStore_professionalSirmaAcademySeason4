package model;

import java.io.Serializable;

/**
 * User class
 */
public class User implements Serializable {
    private static int userNo = 0;
    private int ID;
    private String nickname;
    private double totalMoney;

    /**
     * Construct the user object by given nickname.
     * @param nickname the given nickname
     */
    public User(String nickname) {
        this.setID();
        this.setNickname(nickname);
        this.totalMoney = 0.00;
    }

    /**
     * Id Getter
     * @return the current user's id.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the current user's id to next user number.
     */
    public void setID() {
        this.ID = ++User.userNo;
    }

    /**
     * Nickname Getter
     * @return the current user's nickname.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets the current user's nickname to the given nickname.
     * @param nickname the given nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Total money Getter
     * @return the current user's total money.
     */
    public double getTotalMoney() {
        return totalMoney;
    }

    /**
     * Updates the current user's total money by a given amount of money.
     * @param money the given amount of money
     */
    public void updateMoney(double money) {
        this.totalMoney = money;
    }

    /**
     * Predefine toString() method
     * @return the representation of an user object as a string.
     */
    @Override
    public String toString() {
        return "User { " +
                "ID: " + ID +
                ", nickname: '" + nickname + '\'' +
                ", totalMoney: " + totalMoney +
                " }";
    }
}