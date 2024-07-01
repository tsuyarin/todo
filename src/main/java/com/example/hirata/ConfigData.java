package com.example.hirata;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "configdata")
public class ConfigData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int tabposition;

	public int getTabposition() {
		return tabposition;
	}

	public void setTabposition(int tabposition) {
		this.tabposition = tabposition;
	}
	
	
}
