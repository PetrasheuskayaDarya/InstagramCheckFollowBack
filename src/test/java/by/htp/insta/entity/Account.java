package by.htp.insta.entity;

import java.sql.Date;

public class Account {
	private String nikName;
	private java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
	private String checked;
	private String friend;

	public Account() {
		super();
	}

	public Account(String nikName, Date date, String checked, String friend) {

		super();
		this.nikName = nikName;
		this.date = date;
		this.checked = checked;
		this.friend = friend;
	}

	public Account(String nikName, String checked) {

		super();
		this.nikName = nikName;
		this.checked = checked;
	}

	public String getNikName() {
		return nikName;
	}

	public void setNikName(String nikName) {
		this.nikName = nikName;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getFriend() {
		return friend;
	}

	public void setFriend(String friend) {
		this.friend = friend;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checked == null) ? 0 : checked.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((friend == null) ? 0 : friend.hashCode());
		result = prime * result + ((nikName == null) ? 0 : nikName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (checked == null) {
			if (other.checked != null)
				return false;
		} else if (!checked.equals(other.checked))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (friend == null) {
			if (other.friend != null)
				return false;
		} else if (!friend.equals(other.friend))
			return false;
		if (nikName == null) {
			if (other.nikName != null)
				return false;
		} else if (!nikName.equals(other.nikName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [nikName=" + nikName + ", date=" + date + ", checked=" + checked + ", friend=" + friend + "]";
	}

	public String toString2() {
		return "Account [nikName=" + nikName + ", checked=" + checked + " ]";
	}

}
