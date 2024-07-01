package com.example.hirata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hirata.MyData;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long>{
	//tabIDをゲットしてリストの並び順に返す。
	@Query("select m from MyData m where m.tabid= :id ORDER BY m.listposition ASC")
	public List<MyData> findlist(@Param("id") long l);
	
	//すべてのデータを取得し、リストポジション順に並べて返す。
	@Query("select m from MyData m ORDER BY m.listposition ASC")
	public List<MyData> findAllSort();	
	
	//タブを取得し、リストポジション順に並べて返す。
	@Query("select m from MyData m where tabid = :tabId ORDER BY m.listposition ASC")
	public List<MyData> findAllSortTab(@Param("tabId") int tabId);
	
	//taｂ内で一番下のリストの番号を返す。nullの時の処理を追加中
	@Query("select COALESCE(MAX(m.listposition), 0) from MyData m where tabid = :tabId")
	public int getMaxResults(@Param("tabId") int tabId);

	//リストのIDからIDを返す
	@Query("select m.id from MyData m where listposition = :listid AND tabid = :tabid")
	public long retId(@Param("listid") int listid, @Param("tabid") int tabid);
	
}

