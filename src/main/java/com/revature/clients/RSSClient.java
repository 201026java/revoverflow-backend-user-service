package com.revature.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.revature.DTOs.RSSAccountDTO;
import com.revature.DTOs.RSSUserDTO;

@FeignClient(name = "Accounts-Service", url = "${environment.rss.login.url}")
public interface RSSClient {

	@PostMapping("/user/login")
	public RSSUserDTO loginUser(RSSUserDTO user);
	
	@PostMapping("/account/accounts")
	public List<RSSAccountDTO> findAccountByUserId(RSSUserDTO user);
}
