package com.example.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.RestService;

// @RestController 各メソッドの戻り値にhtmlファイル以外を指定できるようになるアノテーション
@RestController
public class UserRestController {
	
	@Autowired
	RestService service;
	
	// ユーザー全件取得
	@GetMapping("/rest/get")
	public List<User> getUserMany() {
		
		return service.selectMany();
	}
	
	// ユーザー1件取得
	@GetMapping("/rest/get/{id:.+}")
	public User getUserOne(@PathVariable("id") String userId) {
		
		return service.selectOne(userId);
	}
	
	// @RequestBody HTTPリクエストのボディ部分を引数にマッピングしてくれるアノテーション
	@PostMapping("/rest/insert")
	public String postUserOne(@RequestBody User user) {
		
		// ユーザー1件を登録
		boolean result = service.insert(user);
		
		String str = "";
		
		if(result == true) {
			str = "{\"result\":\"ok\"}";
		} else {
			str = "{\"result\":\"error\"}";
		}
		
		// 結果用の文字列をリターン
		return str;
	}
	
	// @PutMapping PUTメソッドを使う場合のアノテーション
	@PutMapping("/rest/update")
	public String putUserOne(@RequestBody User user) {
		
		// ユーザーを1件更新
		boolean result = service.update(user);
		
		String str = "";
		
		if(result == true) {
			str = "{\"result\":\"ok\"}";
		} else {
			str = "{\"result\":\"error\"}";
		}
		
		// 結果用の文字列をリターン
		return str;
	}
	
	// @DaleteMapping DELETEメソッドを利用する際のアノテーション
	@DeleteMapping("/rest/delete/{id:.+}")
	public String deleteUserOne(@PathVariable("id") String userId) {
		
		// ユーザーを1件削除
		boolean result = service.delete(userId);
		
		String str = "";
		
		if(result == true) {
			str = "{\"result\":\"ok\"}";
		} else {
			str = "{\"result\":\"error\"}";
		}
		
		// 結果用の文字列をリターン
		return str;
	}
	
}
