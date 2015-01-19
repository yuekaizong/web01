package kaizone.android.b89.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	private Utils() {
	};

	public static boolean isNumber(char c) {
		if ((int) c >= 48 && (int) c <= 57)
			return true;
		return false;
	}

	public static boolean isDecimal(char c) {
		if ((int) c == 46)
			return true;
		return false;
	}

	public static int StringToInt(String str) {
		if (str == null)
			return 0;
		StringBuilder sb = new StringBuilder();
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (isNumber(c[i]))
				sb.append(c[i]);
		}
		return Integer.valueOf(sb.toString());
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9.]*");
		return pattern.matcher(str).matches();
	}

	public static String numeric(String str) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	public static String nonNull(String str) {
		if (str == null)
			return "";
		if (str.equals("null"))
			return "";
		return str;
	}

	public static String date() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		Calendar c = Calendar.getInstance();
		String date = format.format(c.getTime());
		return date;
	}

	public static String date2() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		String date = format.format(c.getTime());
		return date;
	}

	public static String date3() {
		SimpleDateFormat format = new SimpleDateFormat("MM-dd");
		Calendar c = Calendar.getInstance();
		String date = format.format(c.getTime());
		return date;
	}

	public static String dateHZ() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String date = format.format(new Date());
		return date;
	}

	public static String monthHZ() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		return month + "月";
	}

	public static String dateAfter(String datestring, int after) {
		String afterDateString = "";
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = df.parse(datestring);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, after);
			date = calendar.getTime();
			afterDateString = df.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return afterDateString;
	}

	public static String dateAfter3(String datestring, int after) {
		String afterDateString = "";
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("MM-dd");
		try {
			date = df.parse(datestring);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, after);
			date = calendar.getTime();
			afterDateString = df.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return afterDateString;
	}

	public static String dateAfterHZ(String datestring, int after) {
		String afterDateString = "";
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		try {
			date = df.parse(datestring);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, after);
			date = calendar.getTime();
			afterDateString = df.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return afterDateString;
	}

	public static Calendar stringToCalendar(String datestring) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = df.parse(datestring);
			calendar.setTime(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return calendar;
	}

	public static String getWeekOfDateHZ(Date date) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	public static int compareToDay(String datestring) {
		Calendar today = Calendar.getInstance();
		Calendar moday = stringToCalendar(datestring);
		return today.compareTo(moday);
	}

	public static String percentage(double f) {
		NumberFormat format = NumberFormat.getInstance();
		String str = format.format(f * 100);
		return str + "%";
	}

	public static String percentage(float f) {
		NumberFormat format = NumberFormat.getInstance();
		String str = format.format(f * 100);
		return str + "%";
	}

	public static String timeMMSS(int second) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(second * 1000);
		SimpleDateFormat format = new SimpleDateFormat("mm:ss");
		String time = format.format(gc.getTime());
		return time;
	}

	public static String time() {
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		Calendar c = Calendar.getInstance();
		String date = format.format(c.getTime());
		return date;
	}

	public static String time2() {
		SimpleDateFormat format = new SimpleDateFormat("hh:mm");
		Calendar c = Calendar.getInstance();
		String date = format.format(c.getTime());
		return date;
	}

	public static String timeAfter(String datestring, int after) {
		String afterDateString = "";
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("hh:mm");
		try {
			date = df.parse(datestring);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, after);
			date = calendar.getTime();
			afterDateString = df.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return afterDateString;
	}

	public static String streamToString(InputStream is, final String enc)
			throws Exception {
		if (is == null)
			return "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				enc));
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String streamToString(InputStream is) {
		if (is == null)
			return "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static Date StringToDate(String datestring) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = null;
		try {
			date = df.parse(datestring);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static double doubleTo00(double d) {
		BigDecimal b = new BigDecimal(d);
		double d00 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d00;
	}

	public static float floatTo00(float f) {
		BigDecimal b = new BigDecimal(f);
		float d00 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		return d00;
	}

	public static float floatTo(float f, int num) {
		BigDecimal b = new BigDecimal(f);
		float d00 = b.setScale(num, BigDecimal.ROUND_HALF_UP).floatValue();
		return d00;
	}

	public static int getGapCount(Date startDate, Date endDate) {
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(startDate);
		fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fromCalendar.set(Calendar.MINUTE, 0);
		fromCalendar.set(Calendar.SECOND, 0);
		fromCalendar.set(Calendar.MILLISECOND, 0);

		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(endDate);
		toCalendar.set(Calendar.HOUR_OF_DAY, 0);
		toCalendar.set(Calendar.MINUTE, 0);
		toCalendar.set(Calendar.SECOND, 0);
		toCalendar.set(Calendar.MILLISECOND, 0);

		return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime()
				.getTime()) / (1000 * 60 * 60 * 24));
	}

	public static String randomNum() {
		long random = System.currentTimeMillis();
		return String.valueOf(random);
	}

	/**
	 * 进行MD5加密
	 * 
	 * @param info
	 *            要加密的信息
	 * @return String 加密后的字符串
	 */
	public static String encryptToMD5(String info) {
		byte[] digesta = null;
		try {
			// 得到一个md5的消息摘要
			MessageDigest alga = MessageDigest.getInstance("MD5");
			// 添加要进行计算摘要的信息
			alga.update(info.getBytes());
			// 得到该摘要
			digesta = alga.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 将摘要转为字符串
		String rs = byte2hex(digesta);
		return rs;
	}

	/**
	 * 将二进制转化为16进制字符串
	 * 
	 * @param b
	 *            二进制字节数组
	 * @return String
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	public static <T> T[] arrayAdd(T[] src, T[] attach) {
		if (src == null)
			return null;
		if (attach == null)
			return src;

		int src_len = src.length;
		int add_len = attach.length;
		int count = src_len + add_len;
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) Array.newInstance(src.getClass().getComponentType(),
				count);
		System.arraycopy(src, 0, temp, 0, src_len);
		System.arraycopy(attach, 0, temp, src_len, add_len);
		attach = null;
		src = null;
		return temp;
	}

	public static float[] arraySortAsc(float[] src) {
		if (src == null)
			return null;
		int len = src.length;
		int i, j, k;
		for (i = 0; i < len; i++) {
			k = i;
			for (j = i + 1; j < len; j++) {
				if (src[k] > src[j])
					k = j;
			}
			if (i != k) {
				float tmp;
				tmp = src[i];
				src[i] = src[k];
				src[k] = tmp;
			}
		}
		return src;
	}
}
