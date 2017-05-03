package by.andreiblinets.dao.constant;


public final class Query {
    public static final String GET_ALL_USERS = "from User";
    public static final String GET_ALL_ACCOUNT = "from Account";
    public static final String GET_ALL_PAYMENT =  "from Payment";
    public static final String GET_ALL_PERIODICAL_EDITION =  "from PeriodicalEdition";
    public static final String GET_ALL_SUBSCRIPTION =  "from Subscription";
    public static final String GET_USER_BY_LOGIN_AND_PASSWORD =  "select * from Account where login = ? and password =? ";
    public static final String CHEKING_LOGIN =  "select * from Account where login = ?";
}
