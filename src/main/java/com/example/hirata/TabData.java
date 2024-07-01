package com.example.hirata;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tabdata")
public class TabData {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column
		private long tabid;
		
		@Column
		private int tabposition;
		
		@Column
		private String tabname;


		public long getTabid() {
			return tabid;
		}

		public void setTabid(long tabid) {
			this.tabid = tabid;
		}

		
		public String getTabname() {
			return tabname;
		}

		public void setTabname(String tabname) {
			this.tabname = tabname;
		}

		public int getTabposition() {
			return tabposition;
		}

		public void setTabposition(int tabposition) {
			this.tabposition = tabposition;
		}



		
}
