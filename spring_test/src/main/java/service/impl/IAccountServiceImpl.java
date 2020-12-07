package service.impl;

import bean.Account;
import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import service.IAccountService;

import javax.annotation.Resource;
@Service
public class IAccountServiceImpl implements IAccountService {

    Account account;
    AccountDao accountDao;

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void bankAction() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Account account = (Account) ac.getBean("account");
        AccountDao accountDao = (AccountDao) ac.getBean("accountDao");
        accountDao.withdraw(account,500);
        accountDao.deposit(account,600);
    }

}
