package io.mine.ft.train.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Currency;

import org.apache.commons.lang.StringUtils;

/**
 * 类MoneyUtil.java的实现描述：金额操作工具类
 * 
 */
public final class MoneyUtil {

	@SuppressWarnings("unused")
	private static final String MONEY_ZERO = "0.00";

	private static final String ZERO = "0";

	private static final String MONEY_FORMAT = "###,##0.00";

	private static final String MONEY_FORMAT_NODECIMAL = "###,##0";

	private static final String MONEY_REGULAR = "0+?$";

	private static final String SEPARATOR = "\\.";

	private static final String ZERO_AMOUNT = "0.00";

	private static final String SEPARATOR_POINT = ".";

	/**
	 * 单元为“元”的金额转化成单位为“分”<br>
	 * 金额 10.32 ——》1032
	 * 
	 * @param yuan
	 *            单元为“元”的金额
	 * @return
	 */
	public static String toCent(String yuan) {
		Money money = new Money(yuan);
		return String.valueOf(money.getCent());
	}

	/**
	 * 依据币种将单元为“元”的金额转化成单位为“分”<br>
	 * RMB 10.32 ——》1032 USD 10.32 ——》1032 JPY 1032 ——》1032
	 * 
	 * @param yuan
	 *            单元为“元”的金额
	 * @param value
	 *            币种ISO 4217标准
	 * @return
	 */
	public static String toCent(String yuan, String value) {

		Currency currency = Currency.getInstance(value);
		Money money = new Money(yuan, currency);
		return String.valueOf(money.getCent());
	}

	/**
	 * 单元为“分”的金额转化成单位为“元”<br>
	 * 金额 1032 ——》10.32
	 * 
	 * @param cent
	 *            单元为“分”的金额
	 * @return
	 */
	public static String toYuan(String cent) {
		Money money = new Money();
		money.setCent(Long.valueOf(cent));
		return money.toString();
	}

	/**
	 * 整数位前补零,小数位补齐2位<br>
	 * 无效格式如123，.10，1.1,<br>
	 * 有效格式如1.00，0.10
	 * 
	 * @param yuan
	 *            单元为“元”的金额
	 * @return
	 */
	public static String formatYuan(String yuan) {
		Money money = new Money(yuan);
		return money.toString();
	}

	/**
	 * 金额千分号<br>
	 * 除去小数点后多余0; 位数不足2位,补2位; 不截取小数位<br>
	 * 288888.99900 -> 288,888.999<br>
	 * 0 -> 0.00<br>
	 * . -> 0.00<br>
	 * .00300 -> 0.003<br>
	 * 12. -> 12.00<br>
	 * 
	 * @param money
	 * @return
	 */
	public static String formatMoney(String money) {

		if (StringUtils.isBlank(money)) {

			return ZERO_AMOUNT;

		}

		java.text.DecimalFormat df = new java.text.DecimalFormat(MONEY_FORMAT);

		String formatMoney = "";

		if (money.indexOf(".") >= 0) {

			money = money.replaceAll(MONEY_REGULAR, "");// 去掉多余的0

			String[] moneySplit = money.split(SEPARATOR);

			if (null != moneySplit && moneySplit.length == 0) {

				return ZERO_AMOUNT;

			} else if (null != moneySplit && moneySplit.length == 1) {

				formatMoney = df.format(new BigDecimal(money));

			} else if (null != moneySplit && moneySplit.length >= 2 && (StringUtils.isBlank(moneySplit[0]))) {

				formatMoney = ZERO + money;

			} else if (null != moneySplit && moneySplit[1].length() < 2) {

				formatMoney = df.format(new BigDecimal(money));

			} else if (null != moneySplit && moneySplit.length >= 2) {

				DecimalFormat nodecimalDf = new DecimalFormat(MONEY_FORMAT_NODECIMAL);

				formatMoney = nodecimalDf.format(new BigDecimal(moneySplit[0])) + SEPARATOR_POINT + moneySplit[1];

			} else {

				formatMoney = money;

			}

		} else {

			formatMoney = df.format(new BigDecimal(money));

		}

		return formatMoney;

	}

	/**
	 * 金额千分号<br>
	 * 除去小数点后多余0; 位数不足2位,补2位; 不截取小数位 <br>
	 * 288888.99900 -> 288,888.999<br>
	 * 0 -> 0.00<br>
	 * .00300 -> 0.003<br>
	 * 12. -> 12.00<br>
	 * 
	 * @param money
	 * @return
	 */
	public static String formatMoney(BigDecimal moneyBigDecimal) {

		if (null == moneyBigDecimal) {

			return ZERO_AMOUNT;

		}

		String money = moneyBigDecimal.toString();

		String formatMoney = formatMoney(money);

		return formatMoney;

	}

	public static String formatMoneyZero(String money) {

		if (StringUtils.isBlank(money)) {

			return ZERO;

		}

		return money.replaceAll(MONEY_REGULAR, "");

	}

	/**
	 * 分转换为元
	 * 
	 * @param fenStr
	 * @return
	 */
	public static String fen2yuan(String fenStr) {

		return new BigDecimal(fenStr).divide(new BigDecimal("100.0")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 分转换为元
	 * 
	 * @param fen
	 * @return
	 */
	public static BigDecimal fen2Yuan(BigDecimal fen) {
		if (fen == null) {
			return null;
		}
		BigDecimal yuan = fen.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);

		return yuan;
	}

	public static String formatMoneyDoinzero(BigDecimal moneyBigDecimal) {

		if (null == moneyBigDecimal) {

			return "0";

		}
		if (moneyBigDecimal.compareTo(BigDecimal.ZERO) == 0) {

			return "0";

		}
		String money = moneyBigDecimal.toString();

		String formatMoney = formatMoney(money);

		return formatMoney;

	}
}
