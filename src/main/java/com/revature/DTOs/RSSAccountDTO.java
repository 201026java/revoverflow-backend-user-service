package com.revature.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class RSSAccountDTO {

	private int accId;
    private int userId;
    private int accTypeId;
	private int points;
}
