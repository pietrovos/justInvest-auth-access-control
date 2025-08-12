package sysc4810;

import java.time.LocalTime;
import java.util.*;

public class Problem1c {
    //Creating Roles as keys and Permissions as values (in a list)
    private static final Map<String, List<String>> ROLES = new HashMap<>();

    static {
        ROLES.put("CLIENT", Arrays.asList("(1) view_account_balance", "(2) view_investment_portfolio", "(3) view_contact_details"));
        ROLES.put("PREMIUM_CLIENT", Arrays.asList("(1) view_account_balance", "(2) view_investment_portfolio", "(3) modify_investment_portfolio", "(4) view_contact_details"));
        ROLES.put("TELLER", Arrays.asList("(1) view_account_balance", "(2) view_investment_portfolio"));
        ROLES.put("FINANCIAL_ADVISOR", Arrays.asList("(1) view_account_balance", "(2) view_investment_portfolio", "(3) modify_investment_portfolio", "(4) view_private_consumer_instruments"));
        ROLES.put("FINANCIAL_PLANNER", Arrays.asList("(1) view_account_balance", "(2) view_investment_portfolio", "(3) modify_investment_portfolio", "(4) view_money_market_instruments", "(5) view_private_consumer_instruments"));
    }

    //For TELLER system use times, creating start and cut off time for accessing the system
    private static final LocalTime BUSINESS_START = LocalTime.of(9, 0);
    private static final LocalTime BUSINESS_END = LocalTime.of(17, 0);

    //Checks if the current time (on TELLER's PC) and checks if they're allowed to access system
    public static boolean isBusinessHours() {
        LocalTime now = LocalTime.now();
        return !now.isBefore(BUSINESS_START) && !now.isAfter(BUSINESS_END);
    }

    //Checks if role is authorized to perform an action
    public static boolean isAuthorized(String role, String action) {
        List<String> permissions = ROLES.get(role);
        if (permissions == null) {
            return false;
        }

        return permissions.contains(action);
    }

    //Getter for ROLES attribute
    public static Map<String, List<String>> getRoles() {
        return ROLES;
    }




}
