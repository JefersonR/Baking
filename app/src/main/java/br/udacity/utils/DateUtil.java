package br.udacity.utils;

import android.annotation.SuppressLint;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
@SuppressLint("SimpleDateFormat")
public class DateUtil 
{
	public static Timestamp getCurrentDate()
	{
		Calendar cal = Calendar.getInstance();
		return new Timestamp(cal.getTimeInMillis());
	}
	public static Timestamp getCurrentDate(int day)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, day);
		return new Timestamp(cal.getTimeInMillis());
	}
	public static int getDay(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.DATE);
	}

	public static int getMonth(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.MONTH) + 1;
	}

	public static int getYear(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.YEAR);
	}

	public static int getHour(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.MINUTE);
	}	

	public static int getDay(Timestamp time)
	{
		Calendar cal = Calendar.getInstance();
		Date date = new Date(time.getTime());
		cal.setTime(date);

		return cal.get(Calendar.DATE);
	}

	public static int getMonth(Timestamp time)
	{
		Calendar cal = Calendar.getInstance();
		Date date = new Date(time.getTime());
		cal.setTime(date);

		return cal.get(Calendar.MONTH) + 1;
	} 

	public static int getYear(Timestamp time)
	{
		Calendar cal = Calendar.getInstance();
		Date date = new Date(time.getTime());
		cal.setTime(date);

		return cal.get(Calendar.YEAR);
	}	

	public static int getHour(Timestamp time)
	{
		Calendar cal = Calendar.getInstance();
		Date date = new Date(time.getTime());
		cal.setTime(date);

		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(Timestamp time)
	{
		Calendar cal = Calendar.getInstance();
		Date date = new Date(time.getTime());
		cal.setTime(date);

		return cal.get(Calendar.MINUTE);
	}
	@SuppressLint("SimpleDateFormat")
	public static String getFormattedDateWithoutHour(long time)
	{
		String date = new SimpleDateFormat("dd/MM/yyyy").format(new Timestamp(time));
		return date;
	}

	@SuppressLint("SimpleDateFormat")
	public static String getFormattedDateComplete(Timestamp time)
	{
		String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(time);
		return date;
	}
	@SuppressLint("SimpleDateFormat")
	public static String getFormattedDateComplete2(Timestamp time)
	{
		String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(time);
		return date;
	}
	@SuppressLint("SimpleDateFormat")
	public static String getFormattedDate(Timestamp time)
	{
		String date = new SimpleDateFormat("dd/MM/yyyy").format(time);
		return date;
	}

	public static String getMonthName(int m)
	{
		String month = "";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();

		if (m >= 1 && m <= 12)  month = months[m - 1];
		return month.toUpperCase(Locale.getDefault());
	}

	@SuppressLint("SimpleDateFormat")
	public static String getDayName(String input_date) throws ParseException
	{
		SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date myDate = newDateFormat.parse(input_date);
		newDateFormat.applyPattern("EEEE");
		String dayWeek = newDateFormat.format(myDate);

		return dayWeek;
	}

	@SuppressLint("SimpleDateFormat")
	public static String getDate(String input_date) throws ParseException
	{
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = newDateFormat.parse(input_date);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return  sdf.format(myDate);
	}
	@SuppressLint("SimpleDateFormat")
	public static String getHourByStrDate(String input_date) throws ParseException
	{
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate = newDateFormat.parse(input_date);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return  sdf.format(myDate);
	}
	@SuppressLint("SimpleDateFormat")
	public static int getIntHourByStrDate(String input_date) throws ParseException
	{
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate = newDateFormat.parse(input_date);
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		return  Integer.valueOf(sdf.format(myDate));
	}

	@SuppressLint("SimpleDateFormat")
	public static int getIntMonthByStrDate(String input_date) throws ParseException
	{
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate = newDateFormat.parse(input_date);
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		return  Integer.valueOf(sdf.format(myDate));
	}

	public static String convertFormat(String input_date) throws ParseException
	{
		SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date myDate = newDateFormat.parse(input_date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return  sdf.format(myDate);
	}
	public static String convertFormat2(String input_date) throws ParseException
	{
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = newDateFormat.parse(input_date);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return  sdf.format(myDate);
	}
	@SuppressLint("SimpleDateFormat")
	public static int getIntMinByStrDate(String input_date) throws ParseException
	{
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate = newDateFormat.parse(input_date);
		SimpleDateFormat sdf = new SimpleDateFormat("mm");
		return  Integer.valueOf(sdf.format(myDate));
	}

	@SuppressLint("SimpleDateFormat")
	public static String getDay(String input_date) throws ParseException
	{
		SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate = newDateFormat.parse(input_date);
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		return  sdf.format(myDate);
	}
	@SuppressLint("SimpleDateFormat")
	public static String convertUnixTimestamp(String input_unix_time)
	{		
		long dateValue = Long.valueOf(input_unix_time);// its need to be in milisecond
		Date datef = new Date(dateValue);
		String format = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(datef);

		return format;
	}

	public static String convertUnixTimestamp2(String input_unix_time)
	{
		long dateValue = Long.valueOf(input_unix_time);// its need to be in milisecond
		Date datef = new Date(dateValue);
		String format = new SimpleDateFormat("yyyy-MM-dd").format(datef);

		return format;
	}
	@SuppressLint("SimpleDateFormat")
	public static String convertUnixTimestampNormal(String input_unix_time)
	{		
		long dateValue = Long.valueOf(input_unix_time);// its need to be in milisecond
		Date datef = new Date(dateValue);
		String format = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(datef);

		return format;
	}

	@SuppressLint("SimpleDateFormat")
	public static String convertUnixTimestampNoHour(String input_unix_time)
	{
		long dateValue = Long.valueOf(input_unix_time);// its need to be in milisecond
		Date datef = new Date(dateValue);
		String format = new SimpleDateFormat("dd/MM/yyyy").format(datef);

		return format;
	}
	@SuppressLint("SimpleDateFormat")
	public static String convertUnixTimestampNormal2(String input_unix_time)
	{		
		long dateValue = Long.valueOf(input_unix_time);// its need to be in milisecond
		Date datef = new Date(dateValue);
		String format = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(datef);  

		return format;
	}
	@SuppressLint("SimpleDateFormat")
	public static Date convertUnixForDate(String input_unix_time)
	{		
		long dateValue = Long.valueOf(input_unix_time);// its need to be in milisecond
		Date datef = new Date(dateValue);
		return datef;
	}
	@SuppressLint("SimpleDateFormat")
	public static String convertUnixTimestampDate(String input_unix_time)
	{		
		long dateValue = Long.valueOf(input_unix_time);// its need to be in milisecond
		Date datef = new Date(dateValue);
		String format = new SimpleDateFormat("dd/MM/yyyy").format(datef);

		return format;
	}
	@SuppressLint("SimpleDateFormat")
	public static String convertUnixTimestampDay(String input_unix_time)
	{		
		long dateValue = Long.valueOf(input_unix_time);// its need to be in milisecond
		Date datef = new Date(dateValue);
		String format = new SimpleDateFormat("dd").format(datef);

		return format;
	}
	@SuppressLint("SimpleDateFormat")
	public static String convertUnixTimestampMonth(String input_unix_time)
	{		
		long dateValue = Long.valueOf(input_unix_time);// its need to be in milisecond
		Date datef = new Date(dateValue);
		String format = new SimpleDateFormat("MM").format(datef);

		return format;
	}
	@SuppressLint("SimpleDateFormat")
	public static String convertUnixTimestampHour(String input_unix_time)
	{		
		long dateValue = Long.valueOf(input_unix_time);// its need to be in milisecond
		Date datef = new Date(dateValue);
		String format = new SimpleDateFormat("HH:mm").format(datef);

		return format;
	}
}
