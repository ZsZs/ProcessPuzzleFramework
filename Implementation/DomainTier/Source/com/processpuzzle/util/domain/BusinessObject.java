package com.processpuzzle.util.domain;

import hu.itkodex.commons.persistence.AggregateRoot;

import com.processpuzzle.party.domain.Company;
import com.processpuzzle.party.domain.OrganizationUnit;

public class BusinessObject implements AggregateRoot{

   private Integer id;
   private Company commissionerCompany = null;
   private OrganizationUnit commissionerOrganizationUnit = null;

   public Company getCommissionerCompany() {
      return commissionerCompany;
   }

   public void setCommissionerCompany(Company comissionerCompany) {
      this.commissionerCompany = comissionerCompany;
   }

   public OrganizationUnit getCommissionerOrganizationUnit() {
      return commissionerOrganizationUnit;
   }

   public void setCommissionerOrganizationUnit(OrganizationUnit commissionerOrganizationUnit) {
      this.commissionerOrganizationUnit = commissionerOrganizationUnit;
   }

   public BusinessObject() {}

   public Integer getId() {
      return id;
   }

}
