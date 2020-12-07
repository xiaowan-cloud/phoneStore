package dao;

import bean.Account;

public interface AccountDao {
    //取钱
    void withdraw(Account account,double amt);
    //存钱
    void deposit(Account account,double amt);
}
