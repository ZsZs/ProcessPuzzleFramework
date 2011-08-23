/*
 * Created on Dec 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.fundamental_types.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.fundamental_types.quantity.domain.TimeValue;
import com.processpuzzle.fundamental_types.textformat.domain.DateFormatSpecifier;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.preferences.domain.Preferences;
import com.processpuzzle.user_session.domain.UserRequestManager;

/**
 * @author zsolt.zsuffa
 */
public class TimePoint implements Comparable<TimePoint> {
   private static TimePoint today;
   private int year;
   private int month;
   private int day;
   private int hour;
   private int minute;
   private int second;
   private int millisecond;
   private Date value = null;
   private TimePrecision precision;
   private GregorianCalendar aCalendar = new GregorianCalendar();

   //Constructors
   public TimePoint( Date date ) {
      this.value = date;
      this.precision = TimePrecision.MILLISECOND;
      determineDateFragments();
   }

   public TimePoint( Date date, TimePrecision precission ) {
      this.value = date;
      this.precision = precission;
      determineDateFragments();
   }

   public TimePoint( Integer year, Integer month, Integer day, Integer hour, Integer min, Integer sec, Integer msec ) {
      // Java Calendar is dumb...
      if(( aCalendar.isLeapYear( year ) && month == 2 && day > 29) || (!aCalendar.isLeapYear( year ) && month == 2 && day > 28) )
         throw new IllegalArgumentException();

      aCalendar.clear();
      aCalendar.setLenient( false );

      this.year = year != null ? year.intValue() : 0;
      this.month = month != null ? month.intValue() : 0;
      this.day = day != null ? day.intValue() : 0;
      this.hour = hour != null ? hour.intValue() : 0;
      this.minute = min != null ? min.intValue() : 0;
      this.second = sec != null ? sec.intValue() : 0;
      this.millisecond = msec != null ? msec.intValue() : 0;
      
      aCalendar.set( this.year, this.month - 1, this.day, this.hour, this.minute, this.second );
      aCalendar.set( Calendar.MILLISECOND, this.millisecond );

      this.precision = determinePrecision( year, month, day, hour, min, sec, msec ) ;
      this.value = aCalendar.getTime();
   }

   public TimePoint( Integer year, Integer month, Integer day, Integer hour, Integer min, Integer sec ) {
      this( year, month, day, hour, min, sec, null );
   }

   public TimePoint( Integer year, Integer month, Integer day, Integer hour, Integer min ) {
      this( year, month, day, hour, min, null, null );
   }

   public TimePoint( Integer year, Integer month, Integer day ) {
      this( year, month, day, null, null, null, null );
   }

   // for anzso compatibility
   public TimePoint( String date, ProcessPuzzleLocale locale ) {
      try{
         SimpleDateFormat format = new SimpleDateFormat( "yyyy.mm.dd", locale.getJavaLocale() );
         format.setLenient( false );
         this.value = format.parse( date );
         this.precision = TimePrecision.DAY;
      }catch( ParseException e ){
         throw new ProcessPuzzleParseException( date, "yyyy.mm.dd", e );
      }
   }
   
   protected TimePoint() {}

   //Public acessor and mutator methods
   public void add( TimeValue value ) {
      aCalendar.clear();
      if( value.getAmount() == 0 )
         return;
      aCalendar.setTime( this.value );

      if( value.getUnit().getSymbol().equals( "msec" ) ){
         aCalendar.add( Calendar.MILLISECOND, value.getAmount().intValue() );
      }else if( value.getUnit().getSymbol().equals( "s" ) ){
         aCalendar.add( Calendar.SECOND, value.getAmount().intValue() );
      }else if( value.getUnit().getSymbol().equals( "min" ) ){
         aCalendar.add( Calendar.MINUTE, value.getAmount().intValue() );
      }else if( value.getUnit().getSymbol().equals( "h" ) ){
         aCalendar.add( Calendar.HOUR, value.getAmount().intValue() );
      }else if( value.getUnit().getSymbol().equals( "d" ) ){
         aCalendar.add( Calendar.DATE, value.getAmount().intValue() );
      }else if( value.getUnit().getSymbol().equals( "wk" ) ){
         aCalendar.add( Calendar.WEEK_OF_MONTH, value.getAmount().intValue() );
      }else if( value.getUnit().getSymbol().equals( "mth" ) ){
         aCalendar.add( Calendar.MONTH, value.getAmount().intValue() );
      }else if( value.getUnit().getSymbol().equals( "yr" ) ){
         aCalendar.add( Calendar.YEAR, value.getAmount().intValue() );
      }
      this.value = aCalendar.getTime();
   }

   public boolean after( TimePoint arg ) {
      return value.after( arg.value );
   }

   public boolean before( TimePoint arg ) {
      return value.before( arg.value );
   }

   public TimePoint clone() {
      return new TimePoint( this.value );
   }

   public int compareTo( TimePoint other ) {
      return value.compareTo( other.getValue() );
   }

   public int dayOfMonth() {
      aCalendar.setTime( this.value );
      return aCalendar.get( GregorianCalendar.DAY_OF_MONTH );
   }

   public boolean equals( Object objectToCheck ) {
      if( !( objectToCheck instanceof TimePoint )) return false;
      else{
         TimePoint anotherTimePoint = (TimePoint) objectToCheck;
         if( !this.precision.equals( anotherTimePoint.getPrecision() ))
            return false;
         else return anotherTimePoint.getValue().equals( value );
      }
   }

   public String format( Preferences preferences ) {
      return format( preferences.getLocale() );
   }

   public String format( ProcessPuzzleLocale locale ) {
      return locale.getDateFormat().format( value );
   }

   public String format( ProcessPuzzleLocale locale, int dateStyle ) {
      return locale.getDateFormat().format( value, dateStyle );
   }

   public String format( ProcessPuzzleLocale locale, int dateStyle, int timeStyle ) {
      return locale.getDateFormat().format( value, dateStyle, timeStyle );
   }

   public String getAsFormattedString( Locale locale ) {
      DateFormat formatter = SimpleDateFormat.getDateTimeInstance( SimpleDateFormat.SHORT, SimpleDateFormat.SHORT, locale );
      return formatter.format( value );
   }

   public String getAsFormattedString() {
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      if( applicationContext != null ){

         DateFormatSpecifier formatter = applicationContext.getInternalizationContext().getDefaultLocale().getDateFormat();
         return formatter.format( value );
      }
      return null;
   }

   public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash( result, value );

      return result;
   }

   public boolean isLeapYear() {
      aCalendar.setTime( this.value );
      int year = aCalendar.get( GregorianCalendar.YEAR );
      if( year % 400 == 0 )
         return true;
      else if( year % 100 == 0 )
         return false;
      else if( year % 4 == 0 )
         return true;
      else
         return false;
   }

   public static TimePoint parse( String source ) {
      return parse( source, UserRequestManager.getInstance().getApplicationContext().getDefaultLocale() );
   }
   
   public static TimePoint parse( String source, Preferences preference ) {
      return parse( source, preference.getLocale() );
   }

   public static TimePoint parse( String source, ProcessPuzzleLocale locale ) {
      return locale.getDateFormat().parse( source );
   }

   public static TimePoint parse( String source, ProcessPuzzleLocale locale, int dateStyle ) {
      return locale.getDateFormat().parse( source, dateStyle );
   }

   public static TimePoint parse( String source, ProcessPuzzleLocale locale, int dateStyle, int timeStyle ) {
      return locale.getDateFormat().parse( source, dateStyle, timeStyle );
   }

   //Properties
   public int getDay() { return day; }
   public int getMinute() { return minute; }
   public int getMillisecond() { return millisecond; }
   public int getMonth() { return month; }
   public int getHour() { return hour; }
   public TimePrecision getPrecision() { return precision; }
   public int getSecond() { return second; }
   public static TimePoint getToday() { return today; }
   public int getYear() { return year; }
   public Date getValue() { return value; }

   public static void setToday( Integer year, Integer month, Integer day ) {
      setToday( new TimePoint( year, month, day ) );
   }

   public static void setToday( TimePoint arg ) {
      today = arg;
   }

   public void setValue( String dateString, Locale locale ) throws ParseException {
      SimpleDateFormat formatter = new SimpleDateFormat( "yyyy.MM.dd" );
      value = formatter.parse( dateString );
   }

   public static TimePoint today() {
      return (today == null) ? new TimePoint() : today;
   }
   
   private void determineDateFragments(){
      Calendar calendar = Calendar.getInstance();
      calendar.setTime( value );
      if( precision.compare( TimePrecision.YEAR ) == 1 ){
         year = calendar.get( Calendar.YEAR );
      }
      if( precision.compare( TimePrecision.MONTH ) == 1 ) {
         month = calendar.get( Calendar.MONTH );
      }
      if( precision.compare( TimePrecision.DAY ) == 1 ) {
         day = calendar.get( Calendar.DAY_OF_MONTH );
      }
      if( precision.compare( TimePrecision.HOUR ) == 1 ) {
         hour = calendar.get( Calendar.HOUR );
      }
      if( precision.compare( TimePrecision.MINUTE ) == 1 ) {
         minute = calendar.get( Calendar.MINUTE );
      }
      if( precision.compare( TimePrecision.SECOND ) == 1 ) {
         second = calendar.get( Calendar.SECOND );
      }
      if( precision.compare( TimePrecision.MILLISECOND ) == 1 ) {
         millisecond = calendar.get( Calendar.MILLISECOND );
      }
   }

   private TimePrecision determinePrecision( Integer year, Integer month, Integer day, Integer hour, Integer min, Integer sec, Integer msec ) {
      if( year == null ) throw new IllegalArgumentException( "Year paramater can't be 0 or null." );
      else if( month == null ) return TimePrecision.YEAR;
      else if( day == null ) return TimePrecision.MONTH;
      else if( hour == null ) return TimePrecision.DAY;
      else if( min == null ) return TimePrecision.HOUR;
      else if( sec == null ) return TimePrecision.MINUTE;
      else if( msec == null ) return TimePrecision.SECOND;
      else return TimePrecision.MILLISECOND;
   }
}