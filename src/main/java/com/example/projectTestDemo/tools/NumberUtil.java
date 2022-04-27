package com.example.projectTestDemo.tools;

import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class NumberUtil {
    public static BigDecimal convertStringToBigDecimal(String value) {
        return NumberUtils.isParsable(value)?new BigDecimal(value):null;
    }

    public static BigDecimal convertStringToBigInteger(String value) {
        return NumberUtils.isDigits(value)?new BigDecimal(value):null;
    }

    public static String removeDot(String value) {
        return StringUtils.remove(value, ".");
    }
}
