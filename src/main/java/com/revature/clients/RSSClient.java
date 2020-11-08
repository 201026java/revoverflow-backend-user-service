package com.revature.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "RevOverflow-UserService")
public interface RSSClient {

	@GetMapping
	public String getPort();
}
