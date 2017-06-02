package by.andreiblinets.constant;


public final class MyQuery {
    public static final String GET_ALL_USERS = "from User";
    public static final String GET_ALL_ACCOUNT = "from Account";
    public static final String GET_ALL_PAYMENT =  "from Payment";
    public static final String GET_ALL_CAMEL_CASE =  "from PeriodicalEdittion";
    public static final String GET_ALL_SUBSCRIPTION =  "from Subscription";
    public static final String GET_USER_BY_LOGIN_AND_PASSWORD =  "select Account.id from Account where login = :login and hashpassword = :password ";
    public static final String CHEKING_LOGIN =  "select * from Account where login = :login";
    public static final String CHEKING_CAMELCASE =  "select * from PeriodicalEdittion where name = :name";
    public static final String GET_ALL_NEWS = "from News";
    public static final String GET_ALL_REDACTOR = "from Editor";
    public static final String GET_ID_CAMELCASE = "select Editor.camelcase from Editor where user = :id";
    public static final String GET_NEWS_BY_ID_CAMEL_CASE = GET_ALL_NEWS + " where camelcase = :id";
    public static final String GET_SUBSCRIPTION_BY_ID_USER = GET_ALL_SUBSCRIPTION + " where user.id = :idUser";
}
