package com.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lrc_session")
public class SessionTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int ussn_id;
	int user_id;
	String ussn_sessionid;
	LocalDateTime ussn_cdate;
	String ussn_key;
	String ussn_host;
	LocalDateTime ussn_exptime;
	String ussn_status;

	public int getUssn_id() {
		return ussn_id;
	}

	public void setUssn_id(int ussn_id) {
		this.ussn_id = ussn_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUssn_sessionid() {
		return ussn_sessionid;
	}

	public void setUssn_sessionid(String ussn_sessionid) {
		this.ussn_sessionid = ussn_sessionid;
	}

	public LocalDateTime getUssn_cdate() {
		return ussn_cdate;
	}

	public void setUssn_cdate(LocalDateTime ussn_cdate) {
		this.ussn_cdate = ussn_cdate;
	}

	public String getUssn_key() {
		return ussn_key;
	}

	public void setUssn_key(String ussn_key) {
		this.ussn_key = ussn_key;
	}

	public String getUssn_host() {
		return ussn_host;
	}

	public void setUssn_host(String ussn_host) {
		this.ussn_host = ussn_host;
	}

	public LocalDateTime getUssn_exptime() {
		return ussn_exptime;
	}

	public void setUssn_exptime(LocalDateTime ussn_exptime) {
		this.ussn_exptime = ussn_exptime;
	}

	public String getUssn_status() {
		return ussn_status;
	}

	public void setUssn_status(String ussn_status) {
		this.ussn_status = ussn_status;
	}
}
