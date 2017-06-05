package by.andreiblinets.constant;


public final class MyQuery {
    public static final String GET_ALL_USERS = "from User";
    public static final String GET_ALL_ACCOUNT = "from Account";
    public static final String GET_ALL_PAYMENT =  "from Payment";
    public static final String GET_ALL_PERIODICAL_EDITION =  "from PeriodicalEdition";
    public static final String GET_ALL_SUBSCRIPTION =  "from Subscription";
    public static final String GET_USER_BY_LOGIN_AND_PASSWORD =  "from Account where login = :login and hashpassword = :password ";
    public static final String CHEKING_LOGIN =  "select * from Account where login = :login";
    public static final String CHEKING_CAMELCASE =  "select * from PeriodicalEdition where name = :name";
    public static final String GET_ALL_NEWS = "from News";
    public static final String GET_ALL_REDACTOR = "from Editor";
    public static final String GET_EDITOR_BY_ID = "from Editor where user.id = :id";
    public static final String GET_NEWS_BY_ID_PREIODICAL_EDITION = GET_ALL_NEWS + " where periodicalEdition.id = :id";
    public static final String GET_SUBSCRIPTION_BY_ID_USER = GET_ALL_SUBSCRIPTION + " where user.id = :idUser";
}
