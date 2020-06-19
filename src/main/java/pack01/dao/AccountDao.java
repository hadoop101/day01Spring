package pack01.dao;

import pack01.bean.Account;

public interface AccountDao {
    void save(Account account);

    void update(Account account);

    Account find(long id);
}
