package pack01.service.impl;

import pack01.bean.Account;
import pack01.dao.AccountDao;
import pack01.dao.impl.AccountDaoImpl;
import pack01.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao  = null;

    //添加设置方法

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public AccountServiceImpl() {
        accountDao = new AccountDaoImpl();
    }

    public void saveAccount(Account account) {
        accountDao.save(account);
    }

    public void updateAccount(Account account) {
        accountDao.update(account);
    }

    public Account findAccount(long id) {
        return  accountDao.find(id);
    }

    public void translate(long from, long into, double value) {
        //查询转出账号
        Account fromAccount =  accountDao.find(from);
        //查询转入账号
        Account intoAccount =  accountDao.find(into);

        //A减钱，B加钱
        fromAccount.setMoney(fromAccount.getMoney()-value);
        intoAccount.setMoney(intoAccount.getMoney()+value);

        //更新
        accountDao.update(fromAccount);
        //System.out.println(1/0);
        accountDao.update(intoAccount);
    }
}
