package com.example.hirata;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.example.hirata.repositories.MyDataRepository;

@Service
public class MyService {
	//欲しいのは並べ替えシステムと、Nullのやつｗ
//    @Autowired
//    private MyDataRepository myDataRepository;
    //Nullのやつ
//    public Integer getMaxResults(int tabId) {
//        Optional<Integer> maxListPositionOptional = myDataRepository.getMaxResults(tabId);
//        return maxListPositionOptional.orElse(0); // デフォルト値を設定
//        // またはエラーハンドリングなどを行う
//    }
    
    //これ改造して並べ替え作りたいね。
    public String doSomething() {
        return "Service is doing something...";
    }
}
//サービスのメソッドを呼び出し方
//String result = myService.doSomething();