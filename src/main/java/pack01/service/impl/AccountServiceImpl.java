package pack01.service.impl;

import pack01.bean.Account;
import pack01.dao.AccountDao;
import pack01.dao.impl.AccountDaoImpl;
import pack01.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao  = null;


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
}
