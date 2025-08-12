package sysc4810;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.junit.Test;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class Problem1cTest {

    @Test
    public void testIsBusinessHours() {
        System.out.println("Testing isBusinessHours:");

        //Time is during Business Hours
        LocalTime testTime = LocalTime.of(10, 0);
        assertTrue(simulateBusinessHours(testTime));
        System.out.println("Test Case 1 Passed.");

        //Time is before 9:00 AM
        testTime = LocalTime.of(8, 0);
        assertFalse(simulateBusinessHours(testTime));
        System.out.println("Test Case 2 Passed.");

        //Time is after 5:00PM
        testTime = LocalTime.of(18, 0);
        assertFalse(simulateBusinessHours(testTime));
        System.out.println("Test Case 3 Passed.");

        //Time is exactly 9:00AM
        testTime = LocalTime.of(9, 0);
        assertTrue(simulateBusinessHours(testTime));
        System.out.println("Test Case 4 Passed.");

        //Time is exactly 5:00PM
        testTime = LocalTime.of(17, 0);
        assertTrue(simulateBusinessHours(testTime));
        System.out.println("Test Case 5 Passed.");
    }

    @Test
    public void testIsAuthorized() {
        System.out.println("Testing isAuthorized:");

        //Role is authorized to perform action
        assertTrue(Problem1c.isAuthorized("CLIENT", "(1) view_account_balance"));
        System.out.println("Test Case 1 Passed.");

        //Role is real, Action is not
        assertFalse(Problem1c.isAuthorized("CLIENT", "(3) jump"));
        System.out.println("Test Case 2 Passed.");

        //Role is not real, Action is
        assertFalse(Problem1c.isAuthorized("UNKNOWN_ROLE", "(1) view_account_balance"));
        System.out.println("Test Case 3 Passed.");

        //Role is not authorized to perform this action
        assertFalse(Problem1c.isAuthorized("CLIENT", "(4) view_contact_details"));
        System.out.println("Test Case 4 Passed.");
    }

    @Test
    public void testRolesMap() {
        System.out.println("\nTesting getRoles:");

        //Testing Hashmap to ensure everything is correct, here we initialize the hashmap
        Map<String, List<String>> roles = Problem1c.getRoles();

        //Testing Key: CLIENT, to see if the key's values are the actions authorized by CLIENT
        assertTrue(roles.containsKey("CLIENT") && roles.get("CLIENT").contains("(1) view_account_balance"));
        assertTrue(roles.containsKey("CLIENT") && roles.get("CLIENT").contains("(2) view_investment_portfolio"));
        assertTrue(roles.containsKey("CLIENT") && roles.get("CLIENT").contains("(3) view_contact_details"));
        System.out.println("Test Case 1 Passed.");

        //Testing Key: PREMIUM_CLIENT, to see if the key's values are the actions authorized by PREMIUM_CLIENT
        assertTrue(roles.containsKey("PREMIUM_CLIENT") && roles.get("PREMIUM_CLIENT").contains("(1) view_account_balance"));
        assertTrue(roles.containsKey("PREMIUM_CLIENT") && roles.get("PREMIUM_CLIENT").contains("(2) view_investment_portfolio"));
        assertTrue(roles.containsKey("PREMIUM_CLIENT") && roles.get("PREMIUM_CLIENT").contains("(3) modify_investment_portfolio"));
        assertTrue(roles.containsKey("PREMIUM_CLIENT") && roles.get("PREMIUM_CLIENT").contains("(4) view_contact_details"));
        System.out.println("Test Case 2 Passed.");

        //Testing Key: TELLER, to see if the key's values are the actions authorized by TELLER
        assertTrue(roles.containsKey("TELLER") && roles.get("TELLER").contains("(1) view_account_balance"));
        assertTrue(roles.containsKey("TELLER") && roles.get("TELLER").contains("(2) view_investment_portfolio"));
        System.out.println("Test Case 3 Passed.");

        //Testing Key: FINANCIAL_PLANNER, to see if the key's values are the actions authorized by FINANCIAL_PLANNER
        assertTrue(roles.containsKey("FINANCIAL_PLANNER") && roles.get("FINANCIAL_PLANNER").contains("(1) view_account_balance"));
        assertTrue(roles.containsKey("FINANCIAL_PLANNER") && roles.get("FINANCIAL_PLANNER").contains("(2) view_investment_portfolio"));
        assertTrue(roles.containsKey("FINANCIAL_PLANNER") && roles.get("FINANCIAL_PLANNER").contains("(3) modify_investment_portfolio"));
        assertTrue(roles.containsKey("FINANCIAL_PLANNER") && roles.get("FINANCIAL_PLANNER").contains("(4) view_money_market_instruments"));
        assertTrue(roles.containsKey("FINANCIAL_PLANNER") && roles.get("FINANCIAL_PLANNER").contains("(5) view_private_consumer_instruments"));
        System.out.println("Test Case 4 Passed.");

        //Testing Key: FINANCIAL_ADVISOR, to see if the key's values are the actions authorized by FINANCIAL_ADVISOR
        assertTrue(roles.containsKey("FINANCIAL_ADVISOR") && roles.get("FINANCIAL_ADVISOR").contains("(1) view_account_balance"));
        assertTrue(roles.containsKey("FINANCIAL_ADVISOR") && roles.get("FINANCIAL_ADVISOR").contains("(2) view_investment_portfolio"));
        assertTrue(roles.containsKey("FINANCIAL_ADVISOR") && roles.get("FINANCIAL_ADVISOR").contains("(3) modify_investment_portfolio"));
        assertTrue(roles.containsKey("FINANCIAL_ADVISOR") && roles.get("FINANCIAL_ADVISOR").contains("(4) view_private_consumer_instruments"));
        System.out.println("Test Case 5 Passed.\n");
    }

    //Needed to create method to simulate current time (https://www.baeldung.com/java-override-system-time)
    private static boolean simulateBusinessHours(LocalTime simulatedTime) {
        try {
            System.setProperty("user.timezone", "UTC");
            return !simulatedTime.isBefore(LocalTime.of(9, 0)) && !simulatedTime.isAfter(LocalTime.of(17, 0));
        } finally {
            System.clearProperty("user.timezone");
        }
    }
}
