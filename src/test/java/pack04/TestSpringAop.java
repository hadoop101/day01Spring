package pack04;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pack01.bean.Account;
import pack01.proxy01.AccountServiceProxy;
import pack01.service.AccountService;
import pack01.service.impl.AccountServiceImpl;
import pack01.tx.MyTxManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class TestSpringAop {
    @Test
    public void test01(){

        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("beans_aop.xml");

        //3:获取service对应的
        AccountService accountService = (AccountService) beanFactory.getBean("service");

 //       Account account = new Account(1001L,"jack",1000D);
//        accountService.saveAccount(account);
//        accountService.updateAccount(account);
//        accountService.translate(1001L,1002L,100D);
        Account account=  accountService.findAccount(1001L);
        System.out.println(account);
    }

}
