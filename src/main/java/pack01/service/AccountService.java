package pack01.service;

import pack01.bean.Account;

public interface AccountService {
    void saveAccount(Account account);

    void updateAccount(Account account);

    Account findAccount(long l);
}
