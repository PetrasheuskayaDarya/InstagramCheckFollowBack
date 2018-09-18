package by.htp.insta.dao;

import java.util.List;

import by.htp.insta.entity.Account;

public interface AccountDao {
	Account readById(int id);

	void addWeFollowedAccount(Account account);

	public List<Account> list();

	public void updateIfFollowBack(Account account);

	public void updateIfNotFollowBack(Account account);

	public List<Account> selectCheckedTable();

}
