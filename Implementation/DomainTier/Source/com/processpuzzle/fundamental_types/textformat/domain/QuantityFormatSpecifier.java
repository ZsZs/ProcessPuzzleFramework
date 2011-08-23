package com.processpuzzle.fundamental_types.textformat.domain;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParsePosition;
import java.util.Locale;

import com.processpuzzle.fundamental_types.domain.ProcessPuzzleParseException;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.QuantityHelper;
import com.processpuzzle.fundamental_types.quantity.domain.UnknownUnitException;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;

/** 
 * @author zsolt.zsuffa
 * @uml.annotations
 *     derived_abstraction="platform:/resource/ProcessPuzzle%20Framework%20Models/Design%20Model.emx#_rCFUEN-YEduYfNRDNb1GEw"
 */
public class QuantityFormatSpecifier extends FormatSpecifier {
   protected int minimumIntegerDigits = 0;
   protected int maximumIntegerDigits = 0;
   protected int minimumFractionDigits = 0;
   protected int maximumFractionDigits = 0;
   protected char decimalSeparator;
   protected char groupingSeparator;

   public QuantityFormatSpecifier() {
      this(new ProcessPuzzleLocale(Locale.getDefault()));
   }

   /**
    * @param locale
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public QuantityFormatSpecifier(ProcessPuzzleLocale locale) {
      // begin-user-code
      super(locale);

      //defaultPattern = (new DecimalFormat()).toPattern();
      initializeFormattingProperties();
      // end-user-code
   }

   /**
    * @param source
    * @return
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public String toString(Quantity source) {
      // begin-user-code
      DecimalFormatSymbols formatSymbols=getFormatSymbols();
      DecimalFormat formatter = new DecimalFormat(defaultPattern, formatSymbols);
      return formatter.format(source.getAmount()) + " " + source.getUnit().getSymbol();
      // end-user-code
   }



/**
    * @param source
    * @return
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public Quantity parse(String source) {

		minimumIntegerDigits = 1;

		DecimalFormat format = getDecimalFormat();
		ParsePosition pos = new ParsePosition(0);
		
		// The Java locale sometimes defines the default separator as
		// \u00A0 but we get \u0020 from the input
		if (format.getDecimalFormatSymbols().getGroupingSeparator()=='\u00A0')
			source = source.replace('\u0020', '\u00A0');
		
		double amount = format.parse(source, pos).doubleValue();
		
//		if (!validateGrouping(source.substring(0,pos.getIndex()),format.getDecimalFormatSymbols().getGroupingSeparator(),format.getGroupingSize())){
//			throw new ProcessPuzzleParseException(source,"Grouping size:"+format.getGroupingSize(),null);
//		}
		
		// System.out.println("Amount:"+amount+"("+source+")DEcimal:'"+decimalSeparator+"' Grouping Separator:'"+groupingSeparator+"' "+format.toPattern());
		// Though, trim (@ unitFinder()) doesn't recognize \u00A0 as whitespace
		// :P
		String unitStr = source.substring(pos.getIndex()).replace('\u00A0',
				'\u0020');
		try {
			return new Quantity(amount, QuantityHelper.unitFinder(unitStr));
		} catch (UnknownUnitException e) {
			throw new ProcessPuzzleParseException(source, "Decimal Separator:'"
					+ decimalSeparator + "' Grouping Separator:'"
					+ groupingSeparator + "' Pattern: " + format.toPattern(), e);
		}

	}



//Getters and Setters

   public char getGroupingSeparator() {
	   return groupingSeparator;
   }

   public char getDecimalSeparator() {
      return decimalSeparator;
   }

   public int getMinimumFractionDigits() {
      return minimumFractionDigits;
   }

   public int getMaximumFractionDigits() {
      return maximumFractionDigits;
   }

   public int getMinimumIntegerDigits() {
      return minimumIntegerDigits;
   }

   public int getMaximumIntegerDigits() {
      return maximumIntegerDigits;
   }

   public void setGroupingSeparator(char groupingSeparator) {
	   this.groupingSeparator = groupingSeparator;
   }
   
   public void setDecimalSeparator(char decimalSeparator) {
      this.decimalSeparator = decimalSeparator;
   }

   public void setMinimumFractionDigits(int minimumFractionDigits) {
      this.minimumFractionDigits = minimumFractionDigits;
   }

   public void setMaximumFractionDigits(int maximumFractionDigits) {
      this.maximumFractionDigits = maximumFractionDigits;
   }

   public void setMinimumIntegerDigits(int minimumIntegerDigits) {
      this.minimumIntegerDigits = minimumIntegerDigits;
   }

   public void setMaximumIntegerDigits(int maximumIntegerDigits) {
      this.maximumIntegerDigits = maximumIntegerDigits;
   }
   
   //Private methods
//   private boolean validateGrouping(String source,char separator,int size){
//	   source=source.trim();
//	   System.out.println("Validating:'"+source+"'");
//	   if (source.indexOf(separator)==-1) {
//		   System.out.println("No separators.");
//		   return true;
//	   }
//	   
//	   int last=0;
//	   boolean atBegin=true;
//	   boolean ended=false;
//	   for(int i=0;i<source.length() && !ended;i++){
//		   if ((Character.valueOf(source.charAt(i))==separator) || 
//				   (Character.isDigit(source.charAt(i)) )){
//			   
//		   }else if (!atBegin)
//	   }
//	   return true;
//   }
   private DecimalFormatSymbols getFormatSymbols() {
	   DecimalFormatSymbols formatSymbols = null;
	   formatSymbols = new DecimalFormatSymbols(locale.getJavaLocale());
	   formatSymbols.setDecimalSeparator(decimalSeparator);
	   formatSymbols.setGroupingSeparator(groupingSeparator);
	   return formatSymbols;
   }
   
   private DecimalFormat getDecimalFormat(){
	   DecimalFormat format=new DecimalFormat(defaultPattern,getFormatSymbols());
	   
	   format.setMaximumIntegerDigits(maximumIntegerDigits);
	   format.setMinimumIntegerDigits(minimumIntegerDigits);
	   format.setMaximumFractionDigits(maximumFractionDigits);
	   format.setMinimumFractionDigits(minimumFractionDigits);
	   
	   return format;
   }
   
   private void initializeFormattingProperties() {
	  DecimalFormatSymbols formatSymbols = null;
	  formatSymbols = new DecimalFormatSymbols(locale.getJavaLocale());
      decimalSeparator = formatSymbols.getDecimalSeparator();
      groupingSeparator = formatSymbols.getGroupingSeparator();
      
      DecimalFormat format=new DecimalFormat();
      defaultPattern=format.toPattern();
      maximumIntegerDigits=format.getMaximumIntegerDigits();
      minimumIntegerDigits=format.getMinimumIntegerDigits();
      maximumFractionDigits=format.getMaximumFractionDigits();
      minimumFractionDigits=format.getMinimumFractionDigits();
            
   }
}