package com.processpuzzle.party.domain;

import com.processpuzzle.address.domain.Settlement;
import com.processpuzzle.address.domain.ZipCode;

public class GeographicAddress extends Address {
   private String buildingNumber;
   private String street;
   private String floor;
   private String door;
   private String topographicalNumber;
   private Settlement settlement;
   private ZipCode zipCode;

   protected GeographicAddress() {
      super();
   }

   public GeographicAddress(String street, String buildingNumber, ZipCode zipCode, Settlement settlement) {
      this.street = street;
      this.buildingNumber = buildingNumber;
      this.settlement = settlement;
      this.zipCode = zipCode;
   }

   public GeographicAddress(String topographicalNumber) {
      this.topographicalNumber = topographicalNumber;
   }

   public GeographicAddress(Settlement settlement) {
      this.settlement = settlement;
   }

   public Settlement getSettlement() {
      return settlement;
   }

   public void setSettlement(Settlement settlement) {
      this.settlement = settlement;
   }

   public ZipCode getZipCode() {
      return zipCode;
   }

   public void setZipCode(ZipCode zipCode) {
      this.zipCode = zipCode;
   }

   public int hashCode() {
      int streetHash;
      int buildingNumberHash;
      int floorHash;
      int doorHash;
      int settlementHash = 0;
      int zipCodeHash;
      if (street == null)
         streetHash = 0;
      else
         streetHash = street.hashCode();
      if (buildingNumber == null)
         buildingNumberHash = 0;
      else
         buildingNumberHash = buildingNumber.hashCode();
      if (floor == null)
    	  floorHash = 0;
      else
    	  floorHash = floor.hashCode();
      if (door == null)
    	  doorHash = 0;
      else
    	  doorHash = door.hashCode();
      if (settlement == null)
         settlementHash = 0;
      else
         settlement.hashCode();
      if (zipCode == null)
         zipCodeHash = 0;
      else
         zipCodeHash = zipCode.hashCode();
      return streetHash * 10000 + buildingNumberHash * 1000 + floorHash * 1000 + doorHash * 1000 + settlementHash * 1000 + zipCodeHash;
   }

   public int compareTo(Object o) {
      if (!(o instanceof GeographicAddress))
         return -1;
      GeographicAddress n = (GeographicAddress) o;
      int c;
      if ((c = street.compareTo(n.getStreet())) != 0)
         return c;
      if ((c = buildingNumber.compareTo(n.getBuildingNumber())) != 0)
         return c;
      if ((c = floor.compareTo(n.getFloor())) != 0)
      	return c;
      if ((c = door.compareTo(n.getDoor())) != 0)
      	return c;
      if ((c = settlement.compareTo(n.getSettlement())) != 0)
         return c;
      if ((c = zipCode.compareTo(n.getZipCode())) != 0)
         return c;
      return 0;
   }

   public boolean equals(Object o) {
      if (!(o instanceof GeographicAddress))
         return false;
      GeographicAddress g = (GeographicAddress) o;
      return ((this.street.equals(g.getStreet())) && (this.buildingNumber.equals(g.getBuildingNumber()))
            && (this.zipCode.equals(g.getZipCode())) && (this.settlement.equals(g.getSettlement())));
   }

   public String getTopographicalNumber() {
      return topographicalNumber;
   }

   public void setTopographicalNumber(String topographicalNumber) {
      this.topographicalNumber = topographicalNumber;
   }

   public String getStreet() {
      return street;
   }

   public void setStreet(String street) {
      this.street = street;
   }

   public String getBuildingNumber() {
      return buildingNumber;
   }

   public void setBuildingNumber(String buildingNumber) {
      this.buildingNumber = buildingNumber;
   }

   public String toString() {
      String returnValue = "";
      if(zipCode!=null && zipCode.getZipCode()!=null)
         returnValue += zipCode.getZipCode().toString();
      if(settlement!=null) {
         if(!returnValue.equals("")) returnValue += " ";
         returnValue += settlement.getName();
      }
      if(!returnValue.equals("")) returnValue += ", ";
      if(street!=null)returnValue += street;
      if(!returnValue.equals("")) returnValue += " ";
      if(buildingNumber!=null)returnValue += buildingNumber;
      return  returnValue;
   }

   public String getDoor() {
      return door;
   }

   public void setDoor(String door) {
      this.door = door;
   }

   public String getFloor() {
      return floor;
   }

   public void setFloor(String floor) {
      this.floor = floor;
   }
   
}