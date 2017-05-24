package by.andreiblinets.dao.constant;


public final class MyQuery {
    public static final String GET_ALL_USERS = "from User";
    public static final String GET_ALL_ACCOUNT = "from Account";
    public static final String GET_ALL_PAYMENT =  "from Payment";
    public static final String GET_ALL_CAMEL_CASE =  "from CamelCase";
    public static final String GET_ALL_SUBSCRIPTION =  "from Subscription";
    public static final String GET_USER_BY_LOGIN_AND_PASSWORD =  "select * from Account where login = :login and password = :password ";
    public static final String CHEKING_LOGIN =  "select * from Account where login = :login";
}
