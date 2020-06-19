package pack01.service;

import pack01.bean.Account;

public interface AccountService {
    void saveAccount(Account account);

    void updateAccount(Account account);

    Account findAccount(long l);

    //转账方法
    void translate(long from, long into, double value);
}
