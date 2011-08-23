/**
 * 
 */
package com.processpuzzle.fundamental_types.textformat.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.processpuzzle.fundamental_types.domain.ProcessPuzzleParseException;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.fundamental_types.quantity.domain.TimeValue;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;

/** 
 * @author zsolt.zsuffa
 * @uml.annotations
 *     derived_abstraction="platform:/resource/ProcessPuzzle%20Framework%20Models/Design%20Model.emx#_W-i2gN6WEdu_hbIvzgjWfA"
 */
public class DateFormatSpecifier extends FormatSpecifier {
   private String fullDatePattern;
   private String longDatePattern;
   private String mediumDatePattern;
   private String shortDatePattern;
   
   private String fullTimePattern;
   private String longTimePattern;
   private String mediumTimePattern;
   private String shortTimePattern;
   
   protected DateFormatSpecifier() {
      super();
   }

   /**
    * @param locale
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public DateFormatSpecifier(ProcessPuzzleLocale locale) {
      super(locale);
      
      Locale javaLocale=locale.getJavaLocale();
      SimpleDateFormat dateFormat;
 
      dateFormat=(SimpleDateFormat)DateFormat.getDateInstance(DateFormat.FULL,javaLocale);
      fullDatePattern=dateFormat.toPattern();
      dateFormat=(SimpleDateFormat)DateFormat.getDateInstance(DateFormat.LONG,javaLocale);
      longDatePattern=dateFormat.toPattern();
      dateFormat=(SimpleDateFormat)DateFormat.getDateInstance(DateFormat.MEDIUM,javaLocale);
      mediumDatePattern=dateFormat.toPattern();
      dateFormat=(SimpleDateFormat)DateFormat.getDateInstance(DateFormat.SHORT,javaLocale);
      shortDatePattern=dateFormat.toPattern();
      
      dateFormat=(SimpleDateFormat)DateFormat.getTimeInstance(DateFormat.FULL,javaLocale);
      fullTimePattern=dateFormat.toPattern();
      dateFormat=(SimpleDateFormat)DateFormat.getTimeInstance(DateFormat.LONG,javaLocale);
      longTimePattern=dateFormat.toPattern();
      dateFormat=(SimpleDateFormat)DateFormat.getTimeInstance(DateFormat.MEDIUM,javaLocale);
      mediumTimePattern=dateFormat.toPattern();
      dateFormat=(SimpleDateFormat)DateFormat.getTimeInstance(DateFormat.SHORT,javaLocale);
      shortTimePattern=dateFormat.toPattern();
   }

   /**
    * @param source
    * @return
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public String toString(TimeValue source) {
      return mediumDatePattern;
   }

   public TimePoint parse(String source) {
      return parse(source,DateFormat.DEFAULT);
   }
   public TimePoint parse(String source,int style){
        SimpleDateFormat format = getSimpleDateFormat(style);
        return parse(source,format);
        
   }
   public TimePoint parse(String source, int dateStyle, int timeStyle) {
      SimpleDateFormat format = getSimpleDateTimeFormat(dateStyle, timeStyle);
      return parse(source,format);
   }
   
   public TimePoint parse(String source,SimpleDateFormat format){

      try {
         format.setLenient(false);
         Date d = format.parse(source);
         return new TimePoint(d);
      } catch (ParseException e) {
         throw new ProcessPuzzleParseException(source, format.toPattern(), e);
      }
   }

   public String getPattern() {
      return mediumDatePattern;
   }

   public void setDatePattern(String pattern) {
      this.mediumDatePattern = pattern;
   }
   
   public void setDatePattern(String pattern, int style) {
      switch (style) {
         case DateFormat.FULL:
            this.fullDatePattern = pattern;
            break;
         case DateFormat.LONG:
            this.longDatePattern = pattern;
            break;
         case DateFormat.SHORT:
            this.shortDatePattern = pattern;
            break;
         default:
            this.mediumDatePattern = pattern;
      }
   }
   public void setTimePattern(String pattern) {
      this.mediumTimePattern = pattern;
   }
   
   public void setTimePattern(String pattern, int style) {
      switch (style) {
         case DateFormat.FULL:
            this.fullTimePattern = pattern;
            break;
         case DateFormat.LONG:
            this.longTimePattern = pattern;
            break;
         case DateFormat.SHORT:
            this.shortTimePattern = pattern;
            break;
         default:
            this.mediumTimePattern = pattern;
      }
   }
   public SimpleDateFormat getSimpleDateFormat(){
      SimpleDateFormat f=new SimpleDateFormat(mediumDatePattern);
      f.setLenient(false);
      return f;
   }
   
   public SimpleDateFormat getSimpleDateFormat(int style) {
      String pattern;
      switch (style) {
         case DateFormat.FULL:
            pattern = fullDatePattern;
            break;
         case DateFormat.LONG:
            pattern = longDatePattern;
            break;
         case DateFormat.SHORT:
            pattern = shortDatePattern;
            break;
         default:
            pattern = mediumDatePattern;
      }
      SimpleDateFormat f=new SimpleDateFormat(pattern);
      f.setLenient(false);
      return f;
   }
   
   public SimpleDateFormat getSimpleTimeFormat(){
      return new SimpleDateFormat(mediumTimePattern);
   }
   
   public SimpleDateFormat getSimpleTimeFormat(int style) {
      String pattern;
      switch (style) {
         case DateFormat.FULL:
            pattern = fullTimePattern;
            break;
         case DateFormat.LONG:
            pattern = longTimePattern;
            break;
         case DateFormat.SHORT:
            pattern = shortTimePattern;
            break;
         default:
            pattern = mediumTimePattern;
      }
      //return new SimpleDateFormat(pattern);
      SimpleDateFormat f=new SimpleDateFormat(pattern);
      f.setLenient(false);
      return f;
   }

   public SimpleDateFormat getSimpleDateTimeFormat(){
      //return new SimpleDateFormat(mediumDatePattern+" "+mediumTimePattern);
      SimpleDateFormat f=new SimpleDateFormat(mediumDatePattern+" "+mediumTimePattern);
      f.setLenient(false);
      return f;
   }
   
   public SimpleDateFormat getSimpleDateTimeFormat(int dateStyle,int timeStyle) {
      String timePattern;
      String datePattern;
      
      switch (timeStyle) {
         case DateFormat.FULL:
            timePattern = fullTimePattern;
            break;
         case DateFormat.LONG:
            timePattern = longTimePattern;
            break;
         case DateFormat.SHORT:
            timePattern = shortTimePattern;
            break;
         default:
            timePattern = mediumTimePattern;
      }
      
      switch (dateStyle) {
         case DateFormat.FULL:
            datePattern = fullDatePattern;
            break;
         case DateFormat.LONG:
            datePattern = longDatePattern;
            break;
         case DateFormat.SHORT:
            datePattern = shortDatePattern;
            break;
         default:
            datePattern = mediumDatePattern;
      }

      SimpleDateFormat f=new SimpleDateFormat(datePattern+" "+timePattern);
      f.setLenient(false);
      return f;
   }
   
   
   public String format(Date date){
      return format(date,DateFormat.MEDIUM);
   }
   public String format(Date date,int style){
      SimpleDateFormat format=getSimpleDateFormat(style);
      return format(date,format);
   }
   public String format(Date date,int dateStyle,int timeStyle){
      SimpleDateFormat format=getSimpleDateTimeFormat(dateStyle,timeStyle);
      return format(date,format);
   }
   public String format(Date date,SimpleDateFormat format){
      return format.format(date);
   }

}