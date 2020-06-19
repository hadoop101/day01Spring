package pack03;

import net.sf.cglib.proxy.Callback;
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

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

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

    @Test
    public void test03(){//装饰者模式

        //1:定义一个新类
        AccountService accountService = new AccountServiceProxy();
        //2:新类里面  业务对象 与  事务管理对象
        accountService.translate(1001L,1002L,100D);
        //3:调用新类的方法

    }

    @Test
    public void test04(){//JDK动态的代理 Proxy
        //增强对象
        final MyTxManager myTxManager = new MyTxManager();
        //目标对象
        final AccountService accountService = new AccountServiceImpl();
        //1:定义一个新类
        ClassLoader cl =accountService.getClass().getClassLoader();
        Class [] interfaces = accountService.getClass().getInterfaces();
        InvocationHandler handler = new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("----------此处编写逻辑会影响代理类对象的所有方法");
                //参1 proxy 代理对象 不要调用会出现死循环
                //参2 method 代理对象的方法
                //参3 args 代理对象的方法的参数
                System.out.println(method);
                System.out.println(Arrays.toString(args));
                Object returnValue = null;
                if(method.getName().startsWith("save")||method.getName().startsWith("update")||method.getName().startsWith("translate")){

                    //save update translate 需要增加事务管理的调用代理try...catch.finally
                    System.out.println("-------------需要加事务");
                    try{
                        myTxManager.start();//前置
                        returnValue= method.invoke(accountService,args);
                        myTxManager.commit();//后置
                    }catch (Exception exception){
                        myTxManager.rollback();//异常
                    }finally {//最终
                        myTxManager.release();
                    }
                }else{
                    //默认执行
                    returnValue= method.invoke(accountService,args);
                }
                return returnValue;
            }
        } ;
        AccountService accountService2 = (AccountService) Proxy.newProxyInstance(cl,interfaces,handler);//产生代理类的对象
        //2:新类里面  业务对象 与  事务管理对象
        Account account = new Account(1001L,"jack",1000D);
        accountService2.translate(1001L,1002L,100D);
        // System.out.println(accountService2.toString());
        //3:调用新类的方法

    }

    @Test
    public void test05(){//Cglib动态的代理
        //增强对象
        final MyTxManager myTxManager = new MyTxManager();
        //创建一个基于父类的新类（代理类）
        Class clz = AccountServiceImpl.class;
        MethodInterceptor callBack = new MethodInterceptor() {
            //类似于 jdk动态代理里面的invoke方法，此方法内修改逻辑，影响到所有的方法
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("intercept 影响所有的方法");
                //参1 o 代理对象 不要碰会形成死循环
                //参2  代理对象的方法
                //参3  代理对象的方法的参数
                //参4  代理类的方法methodProxy
                System.out.println(method);
                System.out.println(Arrays.toString(objects));
                System.out.println(methodProxy);
                //判断是save,update,translate方法，添加事务逻辑，否则默认执行
                Object returnValue = null;
                if(method.getName().startsWith("save")||method.getName().startsWith("update")||method.getName().startsWith("translate")){

                    //save update translate 需要增加事务管理的调用代理try...catch.finally
                    System.out.println("-------------需要加事务");
                    try{
                        myTxManager.start();
                        returnValue= methodProxy.invokeSuper(o,objects);
                        myTxManager.commit();
                    }catch (Exception exception){
                        myTxManager.rollback();
                    }finally {
                        myTxManager.release();
                    }
                }else{
                    //默认执行
                    returnValue=  methodProxy.invokeSuper(o,objects);
                }
                return returnValue;
            }
        };
        AccountServiceImpl accountService= (AccountServiceImpl) Enhancer.create(clz,callBack);//返回一个父类的子类对象（动态生成）
        //3:调用新类的方法
        Account account = new Account(1001L,"jack",1000D);
        accountService.translate(1001L,1002L,100D);

    }
}
