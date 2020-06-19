package pack01.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import pack01.bean.Account;
import pack01.dao.AccountDao;
import pack01.db.Db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDaoJtImpl implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    //通过di赋值
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Account account) {
        System.out.println("insert...save");
        jdbcTemplate.update("insert into account  values(?,?,?)",account.getId(),account.getAccount(),account.getMoney());
    }

    public void update(Account account) {
        System.out.println("update...update");
        jdbcTemplate.update("update   account  set account = ? , money = ? where id = ?",account.getAccount(),account.getMoney(),account.getId());
    }
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
    public Account find(long id) {
        System.out.println("select...find");
        List<Account> list= jdbcTemplate.query("select * from account where id = ?",convert ,id);
        if( list != null && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
}
