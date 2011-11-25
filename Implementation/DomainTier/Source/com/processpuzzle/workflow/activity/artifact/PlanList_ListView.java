/*
Name: 
    - PlanList_ListView 

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
 * Created on Sep 7, 2006
 */
package com.processpuzzle.workflow.activity.artifact;

import java.util.Map;

import com.processpuzzle.artifact.domain.ArtifactListView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class PlanList_ListView extends ArtifactListView<PlanList> {

   public PlanList_ListView( PlanList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public void initializeView() {
   }

   public String getData( String method, Map parameters ) {
      // ProtocolRepository protocolRep = (ProtocolRepository)
      // ProcessPuzzleContext.getInstance().getRepository(ProtocolRepository.class);
      //
      // StringBuffer responseXml = new StringBuffer();
      // UnitOfWork work = new UnitOfWork(true);
      // if (method.equals("getLifecycles")) {
      // List lifecycles = (List) protocolRep.findAll(work);
      // responseXml.append("<lifecycles>");
      // for (Iterator iter = lifecycles.iterator(); iter.hasNext();) {
      // LifecycleProtocol lifecycle = (LifecycleProtocol) iter.next();
      // responseXml.append("<lifecycleName id=\"" + lifecycle.getId().toString() + "\">");
      // responseXml.append(ProcessPuzzleContext.getInstance().getText("ui.pantokrator.protocol." + lifecycle.getName(),
      // getPrefferredLanguage()));
      // responseXml.append("</lifecycleName>");
      // }
      // responseXml.append("</lifecycles>");
      // } else if (method.equals("getProtocolName")) {
      // String selectedValue = (String) parameters.get("par0");
      // responseXml.append("<protocol>");
      // responseXml.append("<protocolName>");
      // responseXml.append(protocolRep.findById(work,selectedValue).getName());
      // responseXml.append("</protocolName>");
      // responseXml.append("</protocol>");
      // } else if (method.equals("getIdentifier")) {
      //         
      // String selectedValue = (String) parameters.get("par0");
      //         
      // LifecycleProtocol lProt = (LifecycleProtocol)protocolRep.findById(work,selectedValue);
      //         
      // PlanRepository aRep = (PlanRepository) ProcessPuzzleContext.getInstance().getRepository(PlanRepository.class);
      // ProcessPlanName pPlanName = aRep.getLastProcessPlanName();
      //
      // int currentYear = new GregorianCalendar().get(Calendar.YEAR);
      //
      // Integer year = null;
      // Integer count = null;
      //
      // if (pPlanName.getYear().intValue() < currentYear) {
      // year = new Integer(currentYear);
      // count = new Integer(1);
      // } else if (pPlanName.getYear().intValue() == currentYear) {
      // year = pPlanName.getYear();
      // count = new Integer(pPlanName.getCount().intValue() + 1);
      // }
      //
      // String countString = null;
      // if (count.intValue() / 100 >= 1)
      // countString = "" + count.intValue();
      // else if (count.intValue() / 10 >= 1)
      // countString = "0" + count.intValue();
      // else
      // countString = "00" + count.intValue();
      //
      // responseXml.append("<identifier>");
      // responseXml.append(lProt.getPrefix()+"-" + year.intValue() + "-" + countString);
      // responseXml.append("</identifier>");
      //
      // } else if (method.equals("updateIdentifier")) {
      //
      // PlanRepository aRep = (PlanRepository) ProcessPuzzleContext.getInstance().getRepository(PlanRepository.class);
      // ProcessPlanName pPlanName = aRep.getLastProcessPlanName(work);
      //
      // int currentYear = new GregorianCalendar().get(Calendar.YEAR);
      //
      // Integer year = null;
      // Integer count = null;
      //
      // if (pPlanName.getYear().intValue() < currentYear) {
      // year = new Integer(currentYear);
      // count = new Integer(1);
      // } else if (pPlanName.getYear().intValue() == currentYear) {
      // year = pPlanName.getYear();
      // count = new Integer(pPlanName.getCount().intValue() + 1);
      // }
      //
      // pPlanName.setYear(year);
      // pPlanName.setCount(count);
      // aRep.updateProcessPlanName(work,pPlanName);
      //
      // }
      // work.finish();
      // return responseXml.toString();
      return null;
   }

}
