package com.processpuzzle.persistence.query.domain;

public interface Criterion {
   
  public String renderAsOQL(Criteria criteria); 

}
