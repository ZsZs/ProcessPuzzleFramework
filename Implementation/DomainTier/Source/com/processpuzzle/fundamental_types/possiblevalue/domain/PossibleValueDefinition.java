package com.processpuzzle.fundamental_types.possiblevalue.domain;

import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;

public abstract class PossibleValueDefinition {
   public abstract boolean checkValue(Object value);

   public abstract void addPossibleValue(Object value);
   
   public abstract String asText(ProcessPuzzleLocale locale);
   
}
