package com.processpuzzle.party.domain;

import java.util.HashSet;
import java.util.Set;

public class SoftwareSystem {
   private Integer id;
   private String name;
   private String description;
   private Set<CompanySoftwareSystem> companySoftwareSystems = new HashSet<CompanySoftwareSystem>();

   public Set getCompanySoftwareSystems() {
      return companySoftwareSystems;
   }

   public void setCompanySoftwareSystems(Set<CompanySoftwareSystem> companySoftwareSystems) {
      this.companySoftwareSystems = companySoftwareSystems;
   }

   public Integer getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public SoftwareSystem(String name) {
      super();
      // TODO Auto-generated constructor stub
      this.name = name;
   }

   public SoftwareSystem() {
      super();
      // TODO Auto-generated constructor stub
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void addCompanySoftwareSystem(CompanySoftwareSystem system) {
      this.companySoftwareSystems.add(system);
   }

}