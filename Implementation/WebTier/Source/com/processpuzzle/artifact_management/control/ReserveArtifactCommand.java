package com.processpuzzle.artifact_management.control;

import java.io.IOException;

import com.processpuzzle.application.control.control.CommandDispatcher;

public class ReserveArtifactCommand extends ArtifactActionCommand {
   public static String COMMAND_NAME = "ReserveArtifact";
   public static String COMMENT_NAME_PARAM = "reservationComment";

   // private String comment = "";
   // private String method = "";
   // private String status = "";

   protected void retrieveRequestParameters(CommandDispatcher request) {
      super.retrieveRequestParameters(request);
      // comment = (String) request.getAttribute(COMMENT_NAME_PARAM);
      // method = request.getParameter("method");
      // status = request.getParameter("status");
   }

   public String getName() {
      return "ReserveArtifact";
   }

   protected void doAction() {
      System.out.println("doAction()");

      // String locale =
      // loggedInUser.getSystemUser().getPrefferedLocale().getLanguage();
      // ArtifactRepository theArtifactRepository =
      // (ArtifactRepository)Configuration.getInstance().getRepository(ArtifactRepository.class);
      // String message = "";
      /*
       * if (method.equals("canReserve")) { message = "0"; //ez jelenti azt,
       * hogy rendben, le lehet lefoglalni if (subjectArtifact == null) message =
       * Configuration.getText("ui.reserverelease.message.notExistDocument",
       * locale); else { if (subjectArtifact.isReserved()) message =
       * Configuration.getText("ui.reserve.message.reservedBy",
       * locale)+subjectArtifact.getReservedVersion().getResponsible().getFormattedName()+".";
       * else if (subjectArtifact instanceof Document &&
       * ((Document)subjectArtifact).getFileStorage() != null) { File file =
       * null; try { file = new
       * File(((Document)subjectArtifact).getFileStorage().getSource()); } catch
       * (Exception e) { file = null; } if (file == null || !file.exists())
       * message =
       * Configuration.getText("ui.reserve.message.missingFileFromServer",
       * locale)+((Document)subjectArtifact).getFileStorage().getOriginalFileName()+")."; } } }
       * else if (method.equals("reserved")) { if( ( status != null ) && (
       * status.equals("ok") ) ) { try {
       * subjectArtifact.reserve(loggedInUser,comment);
       * theArtifactRepository.updateArtifact(subjectArtifact); message = "0"; }
       * catch (VersionControlException e) { message =
       * Configuration.getText("ui.reserve.message.reservedBy",
       * locale)+subjectArtifact.getReservedVersion().getResponsible().getFormattedName()+"."; } }
       * else message = Configuration.getText("ui.reserverelease.message.error",
       * locale); } if (method.equals("reserve")) { try { File file = new
       * File(((Document)subjectArtifact).getFileStorage().getSource());
       * BufferedOutputStream outputStream = new
       * BufferedOutputStream(dispatcher.getResponse().getOutputStream());
       * BufferedInputStream inputStream = new BufferedInputStream(new
       * FileInputStream(file));
       * dispatcher.getResponse().setContentType("application/msword");
       * dispatcher.getResponse().setContentLength(new
       * Long(file.length()).intValue()); int start = 0; int length = 1024; int
       * offset = -1; byte[] buffer = new byte[length]; while ((offset =
       * inputStream.read(buffer, start, length)) != -1) {
       * outputStream.write(buffer, start, offset); } inputStream.close();
       * outputStream.close(); } catch (IOException e) { message =
       * Configuration.getText("ui.reserverelease.message.ioError", locale); } }
       * else // write message {
       * dispatcher.getResponse().setContentType("text/plain");
       * dispatcher.getResponse().reset(); try {
       * dispatcher.getResponse().getWriter().println(URLEncoder.encode(message,
       * "UTF-8")); } catch (UnsupportedEncodingException e) {
       * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
       * 
       * try { dispatcher.getResponse().getWriter().close(); } catch
       * (IOException e) { e.printStackTrace(); } }
       * 
       * actionResponse.setOutcome(true);
       */
   }

   protected void buildResponse(CommandDispatcher dispatcher) throws IOException {}
}