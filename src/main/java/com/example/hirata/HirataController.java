package com.example.hirata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.hirata.repositories.MyDataRepository;
import com.example.hirata.repositories.TabDataRepository;

@Controller
public class HirataController {
	
	@Autowired
	MyDataRepository repository;
	
	@Autowired
	TabDataRepository tabrepos;
	
	@Autowired
	MyService myservice;
	
	int tabselect =0;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	@RequestMapping(value = "/todo", method = RequestMethod.GET)
	public ModelAndView todo(
	@ModelAttribute("formModel") MyData mydata,
	ModelAndView mav) {
		mav.setViewName("todo");
		mav.addObject("formModel",mydata);
		Iterable<TabData> tablist = tabrepos.findAll();
		Iterable<MyData> list = repository.findlist(tabselect);
		mav.addObject("tabselect", tabselect);
		mav.addObject("datalist" , list);
		mav.addObject("tablist", tablist);
		return mav;
	}	
	
	//タブの切り替え処理
//	@RequestMapping(value = "/tab", method = RequestMethod.POST)
//	public ModelAndView tabChange(@RequestParam("tabid") int tabid,
//	ModelAndView mav) {
//		tabselect = tabid;
//		mav.setViewName("index");
//		Iterable<TabData> tablist = tabrepos.findAll();
//		Iterable<MyData> list = repository.findlist(tabid);
//		mav.addObject("tabselect", tabselect);
//		mav.addObject("datalist" , list);
//		mav.addObject("tablist", tablist);
//		return mav;
//	}
	//タブの名前変更
	
	
	//タブの新規作成
//	@PostMapping(value = "/newtab")
//	public ModelAndView newTab() {
//		TabData newtab = new TabData();
//		int tl = tabrepos.tabListLength();
//		newtab.setTabname("タブ"+(tl+1));
//		newtab.setTabposition(tl);
//		tabrepos.saveAndFlush(newtab);
//		
//		MyData listone = new MyData();
//		listone.setListposition(0);
//		listone.setTabid(tl);
//		repository.saveAndFlush(listone);
//
//		tabselect = tabrepos.tabListLength();
//		return new ModelAndView("redirect:/");
//	}
//	//★★★★タブの書き込み処理★★★★
//	@PostMapping(value = "/writetab")
//	public ModelAndView writeTab(@RequestParam("memo") String memo,
//			@RequestParam("id") int id) {
//		TabData tablist = new TabData();
//		tablist.setTabposition(id);
//		tablist.setTabname(memo);
//		tabrepos.saveAndFlush(tablist);
//		return new ModelAndView("redirect:/");
//	}
//	
//	//タブの削除
//	@PostMapping(value = "/deletetab")
//	public ModelAndView deleteTab(@RequestParam("tabposision") int tabposision) {
//		Iterable<TabData> tablists = tabrepos.findAll();
//		Iterable<MyData> list = repository.findlist(tabposision);
//		int i=0;
//		for(TabData tablist : tablists) {
//			tablist.setTabposition(i);
//			i++;
//		}
//		tabrepos.saveAllAndFlush(tablists);
//		long tabid = tabrepos.listToID(tabposision);
//		tabrepos.deleteById(tabid);
//		
//		repository.deleteAll(list);
//		tabselect = tabrepos.tabListLength();
//		return new ModelAndView("redirect:/");
//	}
	
	
	//リストの書き込み処理
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public ModelAndView writeData(@RequestParam("memo") String memo,
	                               @RequestParam("id") int id,
	                               ModelAndView mav) {
		//リストのIDからIDを取得。
		long id2 = repository.retId(id,tabselect);
		// IDで既存のエンティティを取得。
	    MyData existingData = repository.findById(id2).orElse(null);
	    if (existingData != null) {
	        // 既存のエンティティに変更を適用
	        existingData.setMemo(memo);
	        repository.saveAndFlush(existingData); // 変更を保存
	    }
	    return new ModelAndView("redirect:/");
	}
	
	//リストの新規作成
	@PostMapping(value = "/newlist")
	public ModelAndView newList() {
		
		int max = repository.getMaxResults(tabselect);
		  MyData existingData = new MyData();
		  existingData.setTabid(tabselect);
		   existingData.setListposition(max+1);
		  repository.saveAndFlush(existingData);
		  return new ModelAndView("redirect:/");
	}

	//リストを動かしたときの処理。
	@PostMapping(value = "/move")
	public ModelAndView updateOrder(@RequestParam("sortedIDs") int[] sortedIDs) {
	    List<MyData> dataList = repository.findAllSortTab(tabselect);
	    int i = 0;
	   
	    for (MyData data : dataList) {
	    	for(int j= 0; j < sortedIDs.length; j++) {
	    		if(i == sortedIDs[j]) {
	    			data.setListposition(j);
	    		}
	    	}
	    	i++;
	    }

	    repository.saveAllAndFlush(dataList);
	    
	    return new ModelAndView("redirect:/");
	}

	//チェックの書き込み
	@PostMapping(value = "/check")
	public ModelAndView check(@RequestParam("check") int check,
					  @RequestParam("rowid") int rowid) {
	    List<MyData> dataList = repository.findAllSortTab(tabselect);

	    for(MyData data: dataList) {
	    	if(data.getListposition()==rowid) {
	    		data.setListcheck(check);
	    		break;
	    	}
	    }
		
		repository.saveAllAndFlush(dataList);
		return new ModelAndView("redirect:/");
	}
	
	//リストの削除
	@PostMapping(value = "deletelist")
	public ModelAndView deleteList( @RequestParam("rowid") int rowid) {
		
		long id2 = repository.retId(rowid,tabselect);
		repository.deleteById(id2);
		 List<MyData> dataList = repository.findlist(tabselect);
		int i = 0;
		for(MyData data : dataList) {
			data.setListposition(i);
			i++;
		}
		repository.saveAllAndFlush(dataList);
		return new ModelAndView("redirect:/");
}
	

	
}
