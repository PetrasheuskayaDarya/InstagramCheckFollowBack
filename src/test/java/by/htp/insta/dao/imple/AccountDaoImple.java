package by.htp.insta.dao.imple;


import static by.htp.insta.dao.util.MySqlPropertyManager.getLogin;
import static by.htp.insta.dao.util.MySqlPropertyManager.getPass;
import static by.htp.insta.dao.util.MySqlPropertyManager.getUrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import by.htp.insta.dao.AccountDao;
import by.htp.insta.entity.Account;

public class AccountDaoImple implements AccountDao {
	private static final String SELECT_ACCOUNT_BY_ID = "SELECT * FROM account WHERE id = ?";
	private static final String INSERT_ACCOUNT = "INSERT INTO account(nikName, followDate, checked, friend) VALUES (?, ?, ?, ?)";
	private static final String CHECK_FOLLOW_BACK_ACCOUNTS = "SELECT * FROM account WHERE followDate = DATE_ADD(CURDATE(), INTERVAL -3 DAY)";
	private static final String CHANGES_IF_FOLLOW_BACK = "UPDATE account SET checked = ?, friend = ? WHERE nikName = ?";
	private static final String CHANGES_IF_NOT_FOLLOW_BACK = "UPDATE account SET checked = ?, friend = ? WHERE nikName = ?";
	private static final String SELECT_ONLY_CHECKED_TABLE = "SELECT checked,nikName FROM account WHERE checked = 'false' AND followDate = DATE_ADD(CURDATE(), INTERVAL -7 DAY)";

	@Override
	public Account readById(int id) {
		Account account = null;
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_ACCOUNT_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				account = buildAccount(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public List<Account> selectCheckedTable() {
		List<Account> listCheckedTable = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(SELECT_ONLY_CHECKED_TABLE);
			while (result.next()) {
				String nikName = result.getString("nikName");
				String checked = result.getString("checked");
				listCheckedTable.add(new Account(nikName, checked));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCheckedTable;

	}

	@Override
	public void addWeFollowedAccount(Account account) {
		String nikName = account.getNikName();
		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
		String checked = account.getChecked();
		String friend = account.getFriend();
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(INSERT_ACCOUNT);
			ps.setString(1, nikName);
			ps.setDate(2, date);
			ps.setString(3, checked);
			ps.setString(4, friend);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Account> list() {
		List<Account> listOfAccountsWeFollow12DaysAgo = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(CHECK_FOLLOW_BACK_ACCOUNTS);
			while (result.next()) {
				String nikName = result.getString("nikName");
				Account b = new Account();
				b.setNikName(nikName);
				listOfAccountsWeFollow12DaysAgo.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfAccountsWeFollow12DaysAgo;
	}

	@Override
	public void updateIfFollowBack(Account account) {
		String nikName = account.getNikName();
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(CHANGES_IF_FOLLOW_BACK);

			ps.setString(1, "true");
			ps.setString(2, "true");
			ps.setString(3, nikName);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateIfNotFollowBack(Account account) {
		String nikName = account.getNikName();
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(CHANGES_IF_NOT_FOLLOW_BACK);
			ps.setString(1, "true");
			ps.setString(2, "false");
			ps.setString(3, nikName);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	private static final ResourceBundle rb;
//
//	static {
//		rb = ResourceBundle.getBundle("db_config");
//	}
//
//	public static String getUrl() {
//		return rb.getString("db.url");
//	}
//
//	public static String getLogin() {
//		return rb.getString("db.login");
//	}
//
//	public static String getPass() {
//		return rb.getString("db.pass");
//	}
//
	private Account buildAccount(ResultSet rs) throws SQLException {
		Account account = new Account();
		account.setNikName(rs.getString("nikName"));
		account.setDate(rs.getDate(1));
		return account;
	}

}

