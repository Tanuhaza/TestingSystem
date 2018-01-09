package ua.kiyv.training.testingSystem.utils.constants;

/**
 * This class contains paths to jsp pages and URLs, which are supported
 */
public final class PagesPath {
    public static final String REDIRECTED = "REDIRECTED";
    public static final String FORWARD = "FORWARD";

    public static final String VIEW_JSP_CLASSPATH = "/WEB-INF/view/jsp/";

    public static final String HOME_PATH = "/home";
    public static final String ADMIN_PATH = "/admin";
    public static final String LOGIN_PATH = "/login";
    public static final String REGISTER_PATH = "/register";
    public static final String LOGOUT_PATH = "/logout";



    public static final String HOME_PAGE = VIEW_JSP_CLASSPATH + "homePage.jsp";
    public static final String ADMIN_HOME_PAGE = VIEW_JSP_CLASSPATH + "admin/dashboard.jsp";
    public static final String LOGIN_PAGE = VIEW_JSP_CLASSPATH + "loginPage.jsp";
    public static final String CARDS_PAGE = VIEW_JSP_CLASSPATH + "user/cardsManagment.jsp";
    public static final String PAYMENTS_PAGE = VIEW_JSP_CLASSPATH + "user/paymentsManagment.jsp";
    public static final String ERROR_PAGE = VIEW_JSP_CLASSPATH + "error.jsp";
    public static final String CONFIRMATION_PAGE = VIEW_JSP_CLASSPATH + "confirmation.jsp";
    public static final String ACCESS_DENIED_PAGE = VIEW_JSP_CLASSPATH + "accessDenied.jsp";
    public static final String REFILL_CARD_PAGE = VIEW_JSP_CLASSPATH + "user/cardRefill.jsp";

    public static final String CARDS_ADMINISTRATION_PAGE = VIEW_JSP_CLASSPATH + "admin/cards.jsp";
    public static final String PAYMENTS_ADMINISTRATION_PAGE = VIEW_JSP_CLASSPATH + "admin/payments.jsp";
    public static final String PAYMENT_DETAILS_PAGE = VIEW_JSP_CLASSPATH + "admin/paymentDetails.jsp";
}