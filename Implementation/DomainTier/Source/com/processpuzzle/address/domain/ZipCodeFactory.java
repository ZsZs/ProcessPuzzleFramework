package com.processpuzzle.address.domain;

import java.util.Set;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.fundamental_types.domain.InvalidParameterListException;
import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;
import com.processpuzzle.persistence.domain.GenericFactory;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class ZipCodeFactory extends GenericFactory<ZipCode> {

   private ZipCodeRepository zipCodeRepository = (ZipCodeRepository) ProcessPuzzleContext.getInstance().getRepository(ZipCodeRepository.class);

   public ZipCode createZipCode(Integer zipCode, Settlement settlement) {
      DefaultUnitOfWork work=new DefaultUnitOfWork(true);
      if (zipCodeRepository.findByIdentityExpression(work,zipCode, settlement.getName()) == null) {
         ZipCode code = new ZipCode(zipCode);
         settlement.addZipCode(code);
         code.setSettlement(settlement);
         work.finish();
         return code;
      } else{
         work.finish();
         throw new EntityIdentityCollitionException(ZipCode.class.getName(), zipCode.toString(), ZipCode.class.getSimpleName());
      }
   }
   
   public ZipCode createZipCode(Integer zipCode, District district) {
      Set<ZipCode> zipCodes = district.getZipCodes();
      for ( ZipCode zip : zipCodes) {
         if ( zip.getZipCode() == zipCode ) {
            throw new EntityIdentityCollitionException(ZipCode.class.getName(), zipCode.toString(), ZipCode.class.getSimpleName());
         }
      }
      ZipCode code = new ZipCode(zipCode);
      district.addZipCode(code);
      return code;
      
   }

   public ZipCode create(Object[] params){
      if (params.length>=2 && (params[0] instanceof Integer) && (params[1] instanceof Settlement)){
         return createZipCode((Integer)params[0],(Settlement)params[1]);
      }else throw new InvalidParameterListException(ZipCodeFactory.class,params,null);
   }
}
