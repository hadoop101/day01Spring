package pack01.tx;

public class MyTxManager {
    public void start() {
        System.out.println("获取连接开启事务");
    }

    public void commit() {
        System.out.println("提交事务");
    }

    public void rollback() {
        System.out.println("出现异常回滚事务");
    }

    public void release() {
        System.out.println("释放连接");
    }
}
