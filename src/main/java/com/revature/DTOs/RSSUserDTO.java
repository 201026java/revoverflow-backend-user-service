package com.revature.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @ NoArgsConstructor
public class RSSUserDTO {

	private int userId;
    private String email;
    private String password;
    private byte[] profilePic;
    private String firstName;
    private String lastName;
    private boolean admin;
    private boolean userDiscounted;
    private int userDiscount;
}
