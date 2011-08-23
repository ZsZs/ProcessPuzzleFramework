package com.processpuzzle.persistence.domain;

import java.util.HashSet;
import java.util.Set;

public class Composite extends Component {
   private Set<Component> components = new HashSet<Component>();
   
   protected Composite() {
      super();
   }
   
   public Composite( String name ) {
      super( name );
   }
   
   public Set<Component> getComponents() { return components; }

   public void addComponent( Component component ) {
      components.add( component );
      component.setParent( this );
   }

}
