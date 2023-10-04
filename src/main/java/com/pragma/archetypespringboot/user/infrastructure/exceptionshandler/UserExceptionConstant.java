package com.pragma.archetypespringboot.user.infrastructure.exceptionshandler;


public class UserExceptionConstant {

    private UserExceptionConstant(){}

    public static final String USER_NOT_FOUND_MESSAGE = "User not found";
    public static final String USER_MINOR_MESSAGE = "You cannot register a minor user";
    public static final String INVALID_USER_PARAMETER_MESSAGE = "The field document cannot be less than 7 and more " +
            "than 15 characters and must be only numbers";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "A user already exists with this document";

}
