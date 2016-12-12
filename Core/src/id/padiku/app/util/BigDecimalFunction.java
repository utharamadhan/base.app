package id.padiku.app.util;

import id.padiku.app.SystemConstant;

import java.math.BigDecimal;

public class BigDecimalFunction {

	public static BigDecimal divide(BigDecimal var1, BigDecimal var2) {
		return var1.divide(var2, SystemConstant.BIGDECIMAL_SCALE, SystemConstant.BIGDECIMAL_ROUNDING);
	}
	
}
