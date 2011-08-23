/*
 * Created on Dec 6, 2005
 */
package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.AggregateRoot;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.fundamental_types.possiblevalue.domain.PossibleValueDefinition;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

/**
 * @author zsolt.zsuffa
 */
public class TestEntity extends GenericEntity<TestEntity> implements AggregateRoot {
   private String name = null;
   private String textAttribute = null;
   private Integer numberAttribute = null;
   private Date dateAttribute = null;
   private TimePoint timePoint = null;
   private TimePeriod timePeriod = null;
   private Quantity quantity = null;
   private Set<TestEntityComponent> components = new HashSet<TestEntityComponent>();
   private TestEntityComponent enitiyComponentWithoutCascade;
   private TestEntityComponent enitiyComponentWithCascade;
   private PossibleValueDefinition possibleValues = null;

//Constructors
   public TestEntity(String name) {
      this.name = name;
   }

   protected TestEntity() {}

//Public accessors
   @SuppressWarnings("unchecked")
   @Override public TestEntityIdentity getDefaultIdentity() {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor("name", name );
      TestEntityIdentity identity = new TestEntityIdentity(context);
      return identity;
   }

   public @Override boolean equals( Object object ) {
      TestEntity otherEntity = (TestEntity) object;
      if( this.name.equals( otherEntity.name ) && 
          this.textAttribute.equals( otherEntity.textAttribute ) && 
          this.numberAttribute.equals( otherEntity.numberAttribute ) &&
          this.dateAttribute.equals( otherEntity.dateAttribute ) &&
          this.enitiyComponentWithCascade.equals( otherEntity.enitiyComponentWithCascade ))
         return true;
      else return false;
   }
   
//Public mutators
   public void rename(String newName) {
      this.name = newName;
   }

//Properties
   public String getName() { return name; }
   
   public Date getDateAttribute() { return dateAttribute; }
   public void setDateAttribute(Date dateAttribute) { this.dateAttribute = dateAttribute; }
   
   public TimePoint getTimePoint() { return timePoint; }
   public void setTimePoint( TimePoint timePoint ) { this.timePoint = timePoint; }
   
   public TimePeriod getTimePeriod() { return timePeriod; }
   public void setTimePeriod( TimePeriod timePeriod ) { this.timePeriod = timePeriod; }
   
   public Integer getNumberAttribute() { return numberAttribute; }
   public void setNumberAttribute(Integer numberAttribute) { this.numberAttribute = numberAttribute; }
   
   public String getTextAttribute() { return textAttribute; }
   public void setTextAttribute(String textAttribute) { this.textAttribute = textAttribute; }
   
   public Quantity getQuantity() { return quantity; }
   public void setQuantity( Quantity quantity ) { this.quantity = quantity; }
   
   public Set<TestEntityComponent> getComponents() { return components; }
   public void setComponents(Set<TestEntityComponent> components) { this.components = components; }
   
   public TestEntityComponent getEnitiyComponentWithoutCascade() { return enitiyComponentWithoutCascade; }
   public void setEnitiyComponentWithoutCascade(TestEntityComponent enitiyComponentWithoutCascade) { 
      this.enitiyComponentWithoutCascade = enitiyComponentWithoutCascade; 
   }
   public TestEntityComponent getEnitiyComponentWithCascade() { return enitiyComponentWithCascade; }
   public void setEnitiyComponentWithCascade(TestEntityComponent enitiyComponentWithCascade) {
      this.enitiyComponentWithCascade = enitiyComponentWithCascade;
   }

   public void addComponent(TestEntityComponent testEntityComponent) {
      components.add(testEntityComponent);
   }

   public TestEntityComponent getFirstComponent() {
      if( components.size() > 0 ) return components.iterator().next();
      else return null;
   }

   public PossibleValueDefinition getPossibleValues() {
      return possibleValues;
   }

   public void setPossibleValues(PossibleValueDefinition possibleValues) {
      this.possibleValues = possibleValues;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }


//Protected, private helper methods
}