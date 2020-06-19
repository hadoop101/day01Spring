package pack03;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pack01.bean.Account;
import pack01.service.AccountService;
import pack01.tx.MyTxManager;

public class TestAop {
    @Test
    public void test01(){

        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("beans.xml");

        //3:获取service对应的
        AccountService accountService = (AccountService) beanFactory.getBean("service");

        //4:调用方法 转账
        //从A转出100块  -100
        //转入给B  +100
        //必须在同一个事务中执行
        accountService.translate(1001L,1002L,100D);

    }
    @Test
    public void test02(){

        //1：定义一个事务管理类
        MyTxManager txManager = new MyTxManager();

        //2：编写 start  commit  rollback 释放连接
        txManager.start();
        txManager.commit();
        txManager.rollback();
        txManager.release();

    }
}
