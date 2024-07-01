package com.example.hirata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hirata.TabData;

public interface TabDataRepository extends JpaRepository<TabData, Long>{
	//タブリストの長さを取得。
	@Query("SELECT COALESCE(MAX(t.tabposition), 0) FROM TabData t")
	public int tabListLength();

//タブの順番からタブのIDを返す。
 @Query("select t.tabid from TabData t where t.tabposition = :tabposision")	
 public long listToID(int tabposision);
	
}