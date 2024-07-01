package com.example.hirata;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "mydata")
public class MyData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	@Column
	private int listposition;
	
	@Column
	private int listcheck;
	
	@Column
	private int listhierarky;
	
	@Column
	private long tabid;
	
	@Column
	private String memo;
	
	@Column
	private int timer;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public int getListposition() {
		return listposition;
	}

	public void setListposition(int listposition) {
		this.listposition = listposition;
	}

	public int getListcheck() {
		return listcheck;
	}

	public void setListcheck(int listcheck) {
		this.listcheck = listcheck;
	}

	public int getListhierarky() {
		return listhierarky;
	}

	public void setListhierarky(int listhierarky) {
		this.listhierarky = listhierarky;
	}

	public long getTabid() {
		return tabid;
	}

	public void setTabid(long tabid) {
		this.tabid = tabid;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}
	
}
