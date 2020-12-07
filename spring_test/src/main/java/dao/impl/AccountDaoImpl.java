package dao.impl;

import bean.Account;
import dao.AccountDao;

public class AccountDaoImpl implements AccountDao {
    Account account;

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void withdraw(Account account, double amt) {
        double balance = account.getBalance();
        if (balance>=amt){
            balance-=amt;
            System.out.println("取出"+amt+"元，剩余"+balance+"元！");
        }else {
            System.out.println("余额不足！！！");
        }
    }

    public void deposit(Account account, double amt) {
        double balance = account.getBalance();
           balance+=amt;
        System.out.println("存入"+amt+"元，剩余"+balance+"元！");
    }

    @Override
    public String toString() {
        return "AccountDaoImpl{" +
                "account=" + account +
                '}';
    }
}
