package com.revature.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.revature.DTOs.RSSUserDTO;

@FeignClient(name = "Accounts-Service", url = "${environment.rss.url}")
public interface RSSClient {

	@PostMapping("/login")
	public RSSUserDTO loginUser(RSSUserDTO user);
}
