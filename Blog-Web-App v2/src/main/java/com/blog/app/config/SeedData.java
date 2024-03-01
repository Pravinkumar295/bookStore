package com.blog.app.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.blog.app.models.Account;
import com.blog.app.models.Authority;
import com.blog.app.models.Post;
import com.blog.app.service.AccountService;
import com.blog.app.service.AuthorityService;
//import com.blog.app.service.AccountService;
import com.blog.app.service.PostService;

import utilConstants.Privillages;
import utilConstants.Roles;

@Component
public class SeedData implements CommandLineRunner{

	
	  @Autowired private AccountService accountService;
	 
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Override
	public void run(String... args) throws Exception {
		
		for(Privillages auth : Privillages.values()) {
			Authority authority = new Authority();
			authority.setId(auth.getId());
			authority.setName(auth.getPrivillage());
			authorityService.save(authority);
		}
		
		Account account01 = new Account();
		Account account02 = new Account();
		Account account03 = new Account();
		Account account04 = new Account();
		
		account01.setEmail("account01@gmail.com");
		account01.setPassword("pass@123");
		account01.setFirstName("user");
		account01.setLastName("lastname");
		
		account02.setEmail("admin@admin.com");
		account02.setPassword("pass@123");
		account02.setFirstName("admin");
		account02.setLastName("lastname");
		account02.setRole(Roles.ADMIN.getRole());
		
		account03.setEmail("editor@editor.com");
		account03.setPassword("pass@123");
		account03.setFirstName("editor");
		account03.setLastName("lastname");
		account03.setRole(Roles.EDITOR.getRole());
		
		account04.setEmail("super_editor@editor.com");
		account04.setPassword("pass@123");
		account04.setFirstName("super_editor");
		account04.setLastName("lastname");
		account04.setRole(Roles.EDITOR.getRole());
		Set<Authority> authorities = new HashSet<>();
		authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);
		authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);
		account04.setAuthorities(authorities);
		
		accountService.save(account01);
		accountService.save(account02);
		  accountService.save(account03); accountService.save(account04);
		 
		// TODO Auto-generated method stub
		List<Post> posts = postService.getAll();
		if(posts.size()== 0) {
			Post post01 = new Post();
			post01.setTitle("Post 01...");
			post01.setBody("Post01 body....");
			post01.setAccount(account03);
			postService.save(post01);
			
			Post post02 = new Post();
			post02.setTitle("Post 02...");
			post02.setBody("Post01 body....");
			post02.setAccount(account04);
			postService.save(post02);
			
			Post post03 = new Post();
			post03.setTitle("Post 03...");
			post03.setBody("Post01 body....");
			post03.setAccount(account04);
			postService.save(post03);
		}
	}

}
