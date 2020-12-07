package bean;

import org.springframework.beans.factory.annotation.Autowired;

public class People {
    Integer tall;
    @Autowired
    Account account;

    public People(Integer tall) {
        this.tall = tall;
    }

    public Integer getTall() {
        return tall;
    }

    public Account getAccount() {
        return account;
    }

    public void setTall(Integer tall) {
        this.tall = tall;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "People{" +
                "tall=" + tall +
                ", account=" + account +
                '}';
    }
}
