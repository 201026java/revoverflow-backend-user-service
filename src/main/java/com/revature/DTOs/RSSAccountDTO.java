package com.revature.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class RSSAccountDTO {

	private int accId;
    private int userId;
    private int accTypeId;
	private int points;
	
	public RSSAccountDTO(RSSAccountDTO rssAccountDTO) {
		this.accId = rssAccountDTO.getAccId();
		this.userId = rssAccountDTO.getUserId();
		this.accTypeId = rssAccountDTO.getAccTypeId();
		this.points = rssAccountDTO.getPoints();
	}
}
