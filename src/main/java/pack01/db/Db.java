package pack01.db;

import pack01.bean.Account;

import java.util.HashMap;
import java.util.Map;

public class Db {
    //模拟两条数据
    private  static  Map<Long,Account> map;
    static {
        Account account1=new Account(1001L,"jack",1000D);
        Account account2=new Account(1002L,"rose",1000D);
         map=new HashMap<Long, Account>();
        map.put(1001L,account1);
        map.put(1002L,account2);
    }
    public static Account get(long id) {
        return map.get(id);
    }
}
