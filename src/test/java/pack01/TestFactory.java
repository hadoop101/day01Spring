package pack01;

import org.junit.Test;
import pack01.bean.Account;
import pack01.service.AccountService;
import pack01.service.impl.AccountServiceImpl;
import pack01.util.MyBeanFactory;

public class TestFactory {
    @Test
    public void test01(){
        System.out.println("test01");

        //1:定义一个业务类：账户

        AccountService accountService =  new AccountServiceImpl();

        //2:创建一个账户数据
        Account account =  new Account(1001L,"jack",1000.00D);

        //3:账户数据 添加，更新，查找
        accountService.saveAccount(account);
        accountService.updateAccount(account);
        Account account2 = accountService.findAccount(1001L);
        System.out.println(account2);
    }

    @Test
    public void test02(){
        System.out.println("test01");

        //1:定义一个业务类：账户

        AccountService accountService = (AccountService) MyBeanFactory.getBean("service","prototype");
        AccountService accountService2 = (AccountService) MyBeanFactory.getBean("service","prototype");
        System.out.println(accountService);
        System.out.println(accountService2);
        //2:创建一个账户数据
        Account account =  new Account(1001L,"jack",1000.00D);

        //3:账户数据 添加，更新，查找
        accountService.saveAccount(account);
        accountService.updateAccount(account);
        Account account2 = accountService.findAccount(1001L);
        System.out.println(account2);
    }
}
