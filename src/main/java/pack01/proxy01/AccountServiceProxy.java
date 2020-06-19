package pack01.proxy01;

import pack01.bean.Account;
import pack01.service.AccountService;
import pack01.service.impl.AccountServiceImpl;
import pack01.tx.MyTxManager;

//1：新类实现业务接口
public class AccountServiceProxy implements AccountService {
    //2:原来的业务对象，事务管理对象
    private AccountService accountService = new AccountServiceImpl();
    private MyTxManager txManager = new MyTxManager();
    public void saveAccount(Account account) {
        try{
            //3：调用逻辑
            txManager.start();

            accountService.saveAccount(account);

            txManager.commit();
        }catch (Exception e){
            txManager.rollback();
        }finally{
            txManager.release();
        }
    }

    public void updateAccount(Account account) {
        try{
            txManager.start();

            accountService.updateAccount(account);

            txManager.commit();
        }catch (Exception e){
            txManager.rollback();
        }finally{
            txManager.release();
        }
    }

    public Account findAccount(long l) {
        return accountService.findAccount(l);
    }

    public void translate(long from, long into, double value) {
        try{
            txManager.start();

            accountService.translate(from,into,value);
            txManager.commit();
        }catch (Exception e){
            txManager.rollback();
        }finally{
            txManager.release();
        }
    }
}
