package pack05;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import pack01.bean.Account;
import pack01.service.AccountService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestJdbcTemplate {
    @Test
    public void test01(){
        //1:获取连接  从spring提供的数据源中获取

        //2:使用jdbcTemplate
        ApplicationContext applicationContext=  new ClassPathXmlApplicationContext("beans_jt.xml");

        //3:调用update ,query 完成增删改查
        JdbcTemplate jt = (JdbcTemplate) applicationContext.getBean("jt");
        System.out.println(jt);
        //jt.update("insert into account  values(?,?,?)",1001L,"jack",1000D);//参1 sql 参2 参数
       // jt.update("insert into account  values(?,?,?)",1002L,"rose",1000D);

        //参1，查询sql  参2转换器（结果集ResultSet ->javaBean) 参3 数据
        RowMapper<Account> convert = new RowMapper<Account>() {
            //查询结果中有几条记录，mapRow就需要执行几次
            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                System.out.println("mapRow");
                //将一条记录保存在account对象
                Account account = new Account();
                //从结果中取数据
                account.setId(resultSet.getLong("id"));
                account.setAccount(resultSet.getString("account"));
                account.setMoney(resultSet.getDouble("money"));
                return account;
            }
        };
       List<Account> accountList= jt.query("select * from account where money > ?",convert ,100D);
        System.out.println(accountList);
    }

}
