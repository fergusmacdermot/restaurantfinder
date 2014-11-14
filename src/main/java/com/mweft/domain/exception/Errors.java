package com.mweft.domain.exception;

public enum Errors {
	// member related exceptions 100-200
	USERNAME_EXISTS(100), MULTIPLE_USERNAMES_EXISTS(101), EMAIL_FOR_THIS_MEMBER_EXISTS(
			102), TOO_MANY_EMAIL_REMINDERS(103), TOO_SOON_TO_SEND_REMINDER(104), NO_SUCH_MEMBER(
			105), EMAIL_EXISTS(106), RELATIONSHIP_EXISTS_AND_ACCEPTED(107), TOO_MANY_REMINDERS(
			108),
	// integration point errors 200-299

	EMAIL_SEND_PROBLEM(200),

	// validation errors 300 - 399
	KEYWORD_NOT_PROVIDED(300);

	private int code;

	private Errors(int c) {
		code = c;
	}

	public int getCode() {
		return code;
	}

}
