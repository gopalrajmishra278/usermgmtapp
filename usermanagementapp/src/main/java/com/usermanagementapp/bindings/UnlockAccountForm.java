package com.usermanagementapp.bindings;

import lombok.Data;

@Data
public class UnlockAccountForm {
	
	private String tempPwd;
	private String newPwd;
	private String confirmPwd;

}
