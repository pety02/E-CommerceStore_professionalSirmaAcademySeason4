package model;

import java.io.Serializable;

/**
 *
 */
public class User implements Serializable {
    private static int userNo = 0;
    private int ID;
    private String nickname;
    private double totalMoney;

    /**
     *
     * @param nickname
     */
    public User(String nickname) {
        this.setID();
        this.setNickname(nickname);
        this.totalMoney = 0.00;
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
        this.ID = ++User.userNo;
    }

    /**
     *
     * @return
     */
    public String getNickname() {
        return nickname;
    }

    /**
     *
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     *
     * @return
     */
    public double getTotalMoney() {
        return totalMoney;
    }

    /**
     *
     * @param money
     */
    public void updateMoney(double money) {
        this.totalMoney = money;
    }

    @Override
    public String toString() {
        return "User { " +
                "ID: " + ID +
                ", nickname: '" + nickname + '\'' +
                ", totalMoney: " + totalMoney +
                " }";
    }
}