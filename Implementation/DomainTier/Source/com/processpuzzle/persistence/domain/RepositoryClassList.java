package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.Repository;

import java.util.ArrayList;
import java.util.List;

public abstract class RepositoryClassList {
   protected List<Class<? extends Repository<?>>> repositories = new ArrayList<Class<? extends Repository<?>>>();
   
   public RepositoryClassList() {
      defineRepositories();
   }
   
   public List<Class<? extends Repository<?>>> gerRepositories() {
      return repositories;
   }
   
   protected abstract void defineRepositories();
}
