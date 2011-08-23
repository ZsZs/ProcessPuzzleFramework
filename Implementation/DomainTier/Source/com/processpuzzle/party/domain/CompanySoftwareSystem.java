package com.processpuzzle.party.domain;

public class CompanySoftwareSystem {
   private Integer id;
   private Company company;
   private SoftwareSystem softwareSystem;

   public Company getCompany() {
      return company;
   }

   public void setCompany(Company company) {
      this.company = company;
   }

   public Integer getId() {
      return id;
   }

   public SoftwareSystem getSoftwareSystem() {
      return softwareSystem;
   }

   public void setSoftwareSystem(SoftwareSystem softwareSystem) {
      this.softwareSystem = softwareSystem;
   }

   public CompanySoftwareSystem() {
      super();
      // TODO Auto-generated constructor stub
   }

   public CompanySoftwareSystem(Company company, SoftwareSystem softwareSystem) {
      // TODO Auto-generated constructor stub
      super();
      this.company = company;
      this.softwareSystem = softwareSystem;
      // company.addCompanySoftwareSystem(this);
      softwareSystem.addCompanySoftwareSystem(this);
   }

}
