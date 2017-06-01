package by.andreiblinets.dao.constant;


public final class MyQuery {
    public static final String GET_ALL_USERS = "from User";
    public static final String GET_ALL_ACCOUNT = "from Account";
    public static final String GET_ALL_PAYMENT =  "from Payment";
    public static final String GET_ALL_CAMEL_CASE =  "from CamelCase";
    public static final String GET_ALL_SUBSCRIPTION =  "from Subscription";
    public static final String GET_USER_BY_LOGIN_AND_PASSWORD =  "select Account.id from Account where login = :login and hashpassword = :password ";
    public static final String CHEKING_LOGIN =  "select * from Account where login = :login";
    public static final String CHEKING_CAMELCASE =  "select * from CamelCase where name = :name";
    public static final String GET_ALL_NEWS = "from News";
    public static final String GET_ALL_REDACTOR = "from Redactor";
}
