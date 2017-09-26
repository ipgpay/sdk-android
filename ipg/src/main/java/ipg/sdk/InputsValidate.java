package ipg.sdk;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IPG
 * Created by Victor CC on 2017/9/20.
 * Copyright © 2017 Victor CC. All rights reserved.
 */

class InputsValidate {
    private static final String CVV_VALIDATE_PATTERN = "^[0-9]{3,4}$";
    private static final String NUMBER_VALIDATE_PATTERN = "^\\+?([0-9]\\d*)$";
    private static final String EXPYEAR_VALIDATE_PATTERN = "^[0-9]{2}$";
    private static final String EXPMONTH_VALIDATE_PATTERN = "^(0[1-9]|1[012])$";

    /// Base RegEx Validate.
    ///
    /// - Parameters:
    ///   - pattern: Regex pattern.
    ///   - value: Validate String
    private static boolean validate(String pattern, String value) {
        if (!value.isEmpty() && value != null) {
            value = value.replaceAll("/\\s+/g", "");
            value = value.replaceAll("/-+/g", "");
            Pattern parttern = Pattern.compile(pattern);
            Matcher matcher = parttern.matcher(value);
            return matcher.matches();
        } else {
            return false;
        }
    }

    /// Check if the input string are valid numbers or not.
    ///
    /// - Parameter text: The string needs to be checked, this should be cc-cvv, cc-pan etc.
    /// - Returns: Returns true if the input string contains only numbers, otherwise false.
    public static boolean isValidInteger(String value) {
        return validate(NUMBER_VALIDATE_PATTERN, value);
    }

    /// Check if the cvv number of the redit card valid.
    ///
    /// - Parameter cvvNum: The string needs to be checked, this should be the cvv number.
    /// - Returns: Returns true if the input string is valid, false otherwise.
    public static boolean isValidCVV(String value) {
        return validate(CVV_VALIDATE_PATTERN, value);
    }

    /// Check if the expiry date of the redit card valid, support 2 digits year and 2 digits month.
    ///
    /// - Parameters:
    ///   - expYear: The credit card expiry year.
    ///   - expMonth: The credit card expory month.
    /// - Returns: Returns true if expiry date is valid (in the future), false otherwise.
    public static boolean isValidExpiryDate(String expYear, String expMonth) {
        if (validate(EXPYEAR_VALIDATE_PATTERN, expYear)) {
            if (validate(EXPMONTH_VALIDATE_PATTERN, expMonth)) {
                Calendar currentDate = Calendar.getInstance();
                int currentYear = currentDate.get(Calendar.YEAR);
                int currentMonth = currentDate.get(Calendar.MONTH) + 1;
                if ((Integer.parseInt(expYear) + 2000) <= currentYear) {
                    if (Integer.parseInt(expMonth) <= currentMonth) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /// Check if the credit card number is valid according to luhn algorithm.
    ///
    /// - Parameter input: The string needs to be checked, this should be the credit card number.
    /// - Returns: Returns true if the input string is valid, false otherwise.
    public static boolean isValidLuhn(String input) {
        if (isValidInteger(input)) {
            input = input.trim();
            int sum = 0;
            int numdigits = input.length();
            int parity = numdigits % 2;
            for (int i = 0; i < numdigits; i++) {
                int digit = Integer.parseInt(String.valueOf(input.charAt(i)));
                if (i % 2 == parity) digit *= 2;
                if (digit > 9) digit -= 9;
                sum += digit;
            }
            return ((sum != 0) && (sum % 10) == 0);
        } else {
            return false;
        }
    }
}
