/**
 * 
 */
package com.processpuzzle.fundamental_types.textformat.domain;

import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.preferences.domain.Preferences;

/** 
 * @author zsolt.zsuffa
 * @uml.annotations
 *     derived_abstraction="platform:/resource/ProcessPuzzle%20Framework%20Models/Design%20Model.emx#_c9qQkN6nEdu_hbIvzgjWfA"
 */
public interface Formattable {
   public String asText(Preferences preference);

   public String asText(ProcessPuzzleLocale locale);

   public Object parse(String source, Preferences preference);

   public Object parse(String source, ProcessPuzzleLocale locale);
}