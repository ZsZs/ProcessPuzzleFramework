package com.processpuzzle.util.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MfDate implements Comparable 
{
	private SimpleDateFormat _formatter = (SimpleDateFormat) DateFormat.getDateInstance();
	private static MfDate today;

	public static MfDate PAST = new MfDate(new GregorianCalendar(0,1,1));
	public static MfDate FUTURE = new MfDate(new GregorianCalendar(10000,1,1));

//<codeFragment name = "data">
    private GregorianCalendar _base;
	public MfDate() {
		this(new GregorianCalendar());
	}
	public MfDate(int year, int month, int day) {
		initialize (new GregorianCalendar(year, month - 1, day));
	}
    private void initialize (GregorianCalendar arg) {
        _base = trimToDays(arg);
    }
    private GregorianCalendar trimToDays(GregorianCalendar arg) {
        GregorianCalendar result = arg;
        result.set(Calendar.HOUR_OF_DAY,0);
        result.set(Calendar.MINUTE, 0);
        result.set(Calendar.SECOND, 0);
        result.set(Calendar.MILLISECOND, 0);
        return result;
    }
//</codeFragment>


	public MfDate (Date arg) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(arg);
		initialize(gc);
	}
	public MfDate (GregorianCalendar arg) {
		initialize(arg);
	}
//<codeFragment name = "addDays">
	public MfDate addDays(int arg) {
		return new MfDate(new GregorianCalendar(getYear(), getMonth(), getDayOfMonth() + arg));
	}
    public MfDate minusDays(int arg) {
        return addDays(-arg);
    }
//</codeFragment>
//<codeFragment name = "compare">
	public boolean after (MfDate arg) {
		return getTime().after(arg.getTime());
	}
	public boolean before (MfDate arg) {
		return getTime().before(arg.getTime());
	}
	public int compareTo(Object arg) {
		MfDate other = (MfDate) arg;
		return getTime().compareTo(other.getTime());
	}
	public boolean equals(Object arg) {
		if (! (arg instanceof MfDate)) return false;
		MfDate other = (MfDate) arg;
		return (_base.equals(other._base));
	}
    public Date getTime() {
        return _base.getTime();
    }
//</codeFragment>
	public String formattedString() {
		return _formatter.format(getTime());
	}
	public GregorianCalendar getCalendar() {
		return _base;
	}
	public int getDayOfMonth() {
		return _base.get(Calendar.DAY_OF_MONTH);
	}
	public String getMediumString() {
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return df.format(getTime());
	}
	public int getMonth() {
		return _base.get(Calendar.MONTH);
	}
	public String getSqlString() {
		_formatter.applyPattern("#M/d/yyyy#");
		return  _formatter.format(getTime());
	}
	public int getYear() {
		return _base.get(Calendar.YEAR);
	}
	public int hashCode() {
		return _base.hashCode();
	}
public static MfDate past() {
	GregorianCalendar greg = new GregorianCalendar(0,1,1);
	return new MfDate(greg);
}
	public String rawString() {
		return _base.getTime().toString();
	}
public static void setToday(int year, int month, int day) {
	setToday(new MfDate(year, month, day));
}
public static void setToday(MfDate arg) {
	today = arg;
}
public static MfDate today() {
	return (today == null) ?
		new MfDate():
		today;
}
	public String toString() {
		return formattedString();
	}
}