package ipg.sdk;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by che19 on 2017/9/29.
 */

public class InputsValidateTests {
    InputsValidate validate = new InputsValidate();

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void valid_ExpireDate() {
        assertEquals(validate.isValidExpiryDate("37", "11"), true);
        assertEquals(validate.isValidExpiryDate("22", "01"), true);
        assertEquals(validate.isValidExpiryDate("1900", "01"), false);
        assertEquals(validate.isValidExpiryDate("00", "01"), false);
        assertEquals(validate.isValidExpiryDate("9999", "01"), false);
        assertEquals(validate.isValidExpiryDate("T_T", "01"), false);
        assertEquals(validate.isValidExpiryDate("QAQ", "T_T"), false);
        assertEquals(validate.isValidExpiryDate("QAQ", "^_^"), false);
    }

    @Test
    public void valid_Luhn() {
        assertEquals(validate.isValidLuhn("378734493671000"), true);
        assertEquals(validate.isValidLuhn("6011000990139424"), true);
        assertEquals(validate.isValidLuhn("3566002020360505"), true);
        assertEquals(validate.isValidLuhn("5105105105105100"), true);
        assertEquals(validate.isValidLuhn("4012888888881881"), true);
    }

    @Test
    public void valid_CVV() {
        assertEquals(validate.isValidCVV("123"), true);
        assertEquals(validate.isValidCVV("1234"), true);
        assertEquals(validate.isValidCVV("1234455345"), false);
        assertEquals(validate.isValidCVV("QAQ"), false);
    }
}
