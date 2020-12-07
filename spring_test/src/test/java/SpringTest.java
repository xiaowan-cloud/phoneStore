import bean.Account;
import bean.People;
import config.MyConfig;
import dao.AccountDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.IAccountService;

public class SpringTest {

    @Test
    public  void testMethod(){

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        IAccountService service=(IAccountService) context.getBean("proxy");

        service.bankAction();
    }
    @Test
    public void testMethod1(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountDao accountDao = (AccountDao) context.getBean("accountDao");
        System.out.println(accountDao);
    }
    @Test
    public void testMyConfig(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Account account = (Account) context.getBean("account");
        System.out.println(account);
        People people = (People) context.getBean("people");
        System.out.println(people);
    }
}
