package com.processpuzzle.application.administration.control;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.control.control.CommandInterface;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.util.domain.OPDomainStrings;

public class DatabaseAdministrationCommand implements CommandInterface {
   public static final String COMMAND_NAME = "DatabaseAdministration";
   public static final String PARTY_ROLE_TYPE_AGGREGATE = "PartyRoleTypeAggregates";
   public static final String PARTY_RELATIONSHIP_TYPE_AGGREGATE = "PartyRelationshipTypeAggregates";
   public static final String RESOURCE_TYPE_AGGREGATE = "ResourceTypeAggregates";

   private String filePath = (String) UserRequestManager.getInstance().getApplicationContext().getProperty(ProcessPuzzleContext.UPLOADED_FILES_FOLDER) + "\\"
         + OPDomainStrings.BACKUP_DIR_NAME + "\\";

   public void init(CommandDispatcher dispatcher) {

   }

   public String getName() {
      return "DatabaseAdministration";
   }

   public String execute(CommandDispatcher dispatcher) throws Exception {
//      HttpServletRequest request = dispatcher.getRequest();
//      UserSession userSession = (UserSession) ((HttpServletRequest) request).getSession().getAttribute(
//            OPDomainStrings.LOGGED_USER_ATTRIBUTE_NAME_IN_SESSION);

      // if( ( userSession != null ) &&
      // ( dispatcher.getServletContext().getAttribute("maintainerUserId") !=
      // null ) &&
      // (
      // userSession.getId().equals((String)dispatcher.getServletContext().getAttribute("maintainerUserId"))
      // ) &&
      // (
      // ((String)dispatcher.getServletContext().getAttribute("haltApplication")).equals("true")
      // ) )
      // {
      // String action = request.getParameter("method");
      // String version = request.getParameter("version");
      // String date = request.getParameter("date");
      // if(action.equals("backUp")) {
      // backUp();
      // dispatcher.getRequest().setAttribute("messageKey", "backedUp");
      // }
      // else if( (action.equals("restore") && (version != null) &&
      // (!version.equals("")) && (date != null ) && (!date.equals(""))) ) {
      // restore(version, date);
      // dispatcher.getRequest().setAttribute("messageKey", "restored");
      // }
      // dispatcher.getServletContext().setAttribute("maintainerUserId", null);
      // dispatcher.getServletContext().setAttribute("haltApplication",
      // "false");
      // }
      // else
      // {
      // dispatcher.getRequest().setAttribute("messageKey", "error");
      // }
      return "/ProcessInstantiation/SystemAdministration/SystemAdministrationMessage.jsp";
   }

   public void backUp() {
   // filePath +=
   // (String)Configuration.getProperty(Configuration.FRAMEWORK_VERSION)+"\\"+GeneralService.todayDateToString();
   // File f = new File(filePath);
   // if(!f.exists()) f.mkdirs();
   // boolean created = false;
   // f = new File(filePath+"\\AdiDB.xml");
   // System.out.println(created);
   // try {
   // created = f.createNewFile();
   // } catch (IOException e) {
   // e.printStackTrace();
   // }
   //      
   // org.dom4j.Document doc = DocumentHelper.createDocument();
   //	   
   // Element rootElement = new DefaultElement("adi_db");
   // rootElement.add(backUpPartyRoleTypeAggregate());
   // rootElement.add(backUpPartyRelationshipType());
   // rootElement.add(backUpResourceType());
   // doc.add(rootElement);
   //
   // OutputFormat format = OutputFormat.createPrettyPrint();
   // FileOutputStream fOut = null;
   // try {
   // fOut = new FileOutputStream(f);
   // XMLWriter writer = null;
   // writer = new XMLWriter(fOut, format);
   // writer.write(doc);
   // writer.close();
   // } catch (IOException e) {
   // e.printStackTrace();
   // }
   //     
   }

}
