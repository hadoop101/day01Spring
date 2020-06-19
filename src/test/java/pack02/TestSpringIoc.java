package pack02;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pack01.bean.Account;
import pack01.service.AccountService;
import pack01.service.impl.AccountServiceImpl;
import pack01.util.MyBeanFactory;

public class TestSpringIoc {
    @Test
    public void test01(){
        System.out.println("test01");

        //1:使用maven依赖了spring基本包

        //2:初始化spring ioc容器
        //BeanFactory满足基本功能的容器:顶层接口
        //ApplicationContext高级功能的容器:子接口
        //ClassPathXmlApplicationContext实现类
        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("beans.xml");

        //3:获取service对应的
        AccountService accountService = (AccountService) beanFactory.getBean("service");
        AccountService accountService2 = (AccountService) beanFactory.getBean("service");
        System.out.println(accountService);
        System.out.println(accountService2);
        //4:调用方法
        accountService.saveAccount(null);

    }
    @Test
    public void test02Di(){
        System.out.println("test01");

        //1:使用maven依赖了spring基本包

        //2:初始化spring ioc容器

        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("beans.xml");

        //3:获取account1对应的
        Account account1 = (Account) beanFactory.getBean("account1");
        Account account2 = (Account) beanFactory.getBean("account2");
        //4:调用方法
        System.out.println(account2);


    }
}
