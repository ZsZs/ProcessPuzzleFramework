package com.processpuzzle.fitnesse.fundamental_types;

import java.util.Collection;

import com.processpuzzle.fundamental_types.quantity.domain.Unit;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.RowFixture;

public class UnitList extends RowFixture {

   public UnitList() { }

   @Override
   public Class<UnitListRowElement> getTargetClass() {
      return UnitListRowElement.class;
   }

   @Override
   public Object[] query() throws Exception {
      
      int i = 0;
      
      Collection<Unit> foundUnits = UserRequestManager.getInstance().getApplicationContext().getMeasurementContext().findAllUnits();
      Object[] objectArray = new Object[foundUnits.size()];
      
      for (Unit unit : foundUnits) {
         UnitListRowElement targetData = new UnitListRowElement(unit.getSymbol(), unit.getName(), unit.getClass().getSimpleName());
         objectArray[i++] = targetData;
      }
      return objectArray;
   }
}