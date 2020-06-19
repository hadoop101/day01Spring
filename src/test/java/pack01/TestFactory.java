package pack01;

import org.junit.Test;
import pack01.bean.Account;
import pack01.service.AccountService;
import pack01.service.impl.AccountServiceImpl;

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
}
