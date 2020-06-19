package pack01.dao.impl;

import pack01.bean.Account;
import pack01.dao.AccountDao;
import pack01.db.Db;

public class AccountDaoImpl implements AccountDao {
    public void save(Account account) {
        System.out.println("insert...save");
    }

    public void update(Account account) {
        System.out.println("update...update");
    }

    public Account find(long id) {
        System.out.println("select...find");
        return Db.get(id);
    }
}
