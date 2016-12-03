package bsr.home;

/**
 * Created by marcin on 03.12.16.
 */
public class Account {
    String accountNbr;
    Integer balance;

    public Account(String accountNbr, Integer balance) {
        this.accountNbr = accountNbr;
        this.balance = balance;
    }

    public String getAccountNbr() {
        return accountNbr;
    }

    public void setAccountNbr(String accountNbr) {
        this.accountNbr = accountNbr;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString(){
        return accountNbr;
    }
}
