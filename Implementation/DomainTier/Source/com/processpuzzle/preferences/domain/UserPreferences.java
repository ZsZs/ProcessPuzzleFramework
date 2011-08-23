/**
 * 
 */
package com.processpuzzle.preferences.domain;

import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;

/** 
 * @author zsolt.zsuffa
 * @uml.annotations
 *     derived_abstraction="platform:/resource/ProcessPuzzle%20Framework%20Models/Design%20Model.emx#_YGwbAN4CEdu0_KhhHrWiwA"
 */
public class UserPreferences extends Preferences {
   public UserPreferences(ProcessPuzzleLocale locale) {
      super(locale);
   }
}