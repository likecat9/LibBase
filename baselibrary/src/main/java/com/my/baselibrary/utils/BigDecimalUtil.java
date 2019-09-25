package com.my.baselibrary.utils;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static java.math.BigDecimal.ROUND_UNNECESSARY;

public class BigDecimalUtil {
    public static final String DIVIDE = "divide";
    public static final String ADD = "add";
    public static final String SUBTRACT = "subtract";
    public static final String MULTIPY = "multipy";
    public static final String ABS = "abs";

    @StringDef({DIVIDE, ADD, SUBTRACT, MULTIPY, ABS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Operator {
    }


    private BigDecimalUtil() {
    }

    private static @Operator
    String operator;
    private static DecimalFormat decimalFormat;
    private static int pointWith = 4;

    public static BigDecimal create(String value) {
        return new BigDecimal(value);
    }

    public static void setPointWith(int pointWidth) {
        BigDecimalUtil.pointWith = pointWidth;
        createDecimalFormart(pointWidth);
    }

    public static String getValue(BigDecimal bigDecimal) {
        if (decimalFormat == null) {
            createDecimalFormart(pointWith);
        }
        return decimalFormat.format(bigDecimal);
    }

    public static BigDecimal computation(String value1, @Operator String operator, String value2) {
        switch (operator) {
            case DIVIDE:
                return create(value1).divide(create(value2), pointWith, RoundingMode.HALF_UP);
            case ADD:
                return create(value1).add(create(value2));
            case SUBTRACT:
                return create(value1).subtract(create(value2));
            case MULTIPY:
                return create(value1).multiply(create(value2));
        }
        return create("0");
    }

    public static String getComputationValue(String value1, @Operator String operator, String value2) {
        return getValue(computation(value1, operator, value2));
    }

    private static void createDecimalFormart(int pointWidth) {
        StringBuilder sb = new StringBuilder("#.");
        for (int i = 0; i < pointWidth; i++) {
            sb.append("#");
        }
        decimalFormat = new DecimalFormat(sb.toString());
    }


}
