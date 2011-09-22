package com.processpuzzle.artifact_management.control;

import java.io.IOException;

import com.processpuzzle.application.control.control.CommandDispatcher;

public class ReleaseArtifactCommand extends ArtifactActionCommand {
   public static String COMMAND_NAME = "ReleaseArtifact";

   // private String method = "";
   // private String status = "";
   // private String fileSize = "";

   protected void retrieveRequestParameters(CommandDispatcher request) {
      super.retrieveRequestParameters(request);
      // method = request.getParameter("method");
      // status = request.getParameter("status");
      // fileSize = request.getParameter("fileSize");
   }

   public String getName() {
      return "ReleaseArtifact";
   }

   protected void doAction() {
   // String locale =
   // loggedInUser.getSystemUser().getPrefferedLocale().getLanguage();
   // ArtifactRepository theArtifactRepository =
   // (ArtifactRepository)Configuration.getInstance().getRepository(ArtifactRepository.class);
   // String message = "";
   // if (method.equals("canRelease"))
   // {
   // message = "0";
   // if(subjectArtifact == null)
   // message =
   // Configuration.getText("ui.reserverelease.message.notExistDocument",
   // locale);
   // else if(!subjectArtifact.isReserved())
   // message = Configuration.getText("ui.release.message.notReserved", locale);
   // else
   // if(!subjectArtifact.getReservedVersion().getResponsible().getId().equals(loggedInUser.getId()))
   // message = Configuration.getText("ui.reserve.message.reservedBy",
   // locale)+subjectArtifact.getReservedVersion().getResponsible().getFormattedName()+".";
   // }
   /*
    * else if(method.equals("released")) { if(status != null &&
    * status.equals("ok")) { FileStorage fileStorage = (FileStorage)
    * dispatcher.getRequest().getSession(true).getAttribute(subjectArtifact.getName()+"_FileStorage");
    * message = (String)
    * dispatcher.getRequest().getSession(true).getAttribute(subjectArtifact.getName()+"_Message");
    * if( message == null ) { try { if( fileStorage != null ) { if(
    * fileStorage.getSize().equals(new Long(fileSize) ) ) {
    * subjectArtifact.release(loggedInUser);
    * ((Document)subjectArtifact).setFileStorage(fileStorage);
    * theArtifactRepository.updateArtifact(subjectArtifact); message = "0"; }
    * else { File file = new File(fileStorage.getSource()); if( file != null &&
    * file.exists() ) { file.delete(); } message =
    * Configuration.getText("ui.reserverelease.message.ioError", locale); } }
    * else { if( ( subjectArtifact instanceof Document) && (
    * ((Document)subjectArtifact).getFileStorage() != null ) ) { message =
    * Configuration.getText("ui.reserverelease.message.ioError", locale); } else {
    * subjectArtifact.release(loggedInUser);
    * theArtifactRepository.updateArtifact(subjectArtifact); message = "0"; } } }
    * catch (VersionControlException e) { message =
    * Configuration.getText("ui.release.message.reservedByOther",
    * locale)+subjectArtifact.getReservedVersion().getResponsible().getFormattedName()+"."; } } }
    * else message = Configuration.getText("ui.reserverelease.message.error",
    * locale);
    * dispatcher.getRequest().getSession(true).removeAttribute(subjectArtifact.getName()+"_FileStorage");
    * dispatcher.getRequest().getSession(true).removeAttribute(subjectArtifact.getName()+"_Message"); }
    * if(method.equals("release")) { try { File folder = new
    * File((String)Configuration.getProperty(Configuration.FRAMEWORK_LOCATION));
    * File file = File.createTempFile("file",
    * ((Document)subjectArtifact).getFileStorage().getOriginalFileName() +
    * ".data", folder); BufferedOutputStream outputStream = new
    * BufferedOutputStream(new FileOutputStream(file)); BufferedInputStream
    * inputStream = new
    * BufferedInputStream(dispatcher.getRequest().getInputStream()); int start =
    * 0; int length = 1024; int offset = -1; byte[] buffer = new byte[length];
    * while ((offset = inputStream.read(buffer, start, length)) != -1) {
    * outputStream.write(buffer, start, offset); } inputStream.close();
    * outputStream.close(); FileStorage fileStorage = new
    * FileStorage(((Document)subjectArtifact).getFileStorage().getOriginalFileName());
    * fileStorage.setSource(file.getPath());
    * fileStorage.setContentType("application/msword");
    * fileStorage.setUploadDate(new Date(System.currentTimeMillis()));
    * fileStorage.setSize(new Long(file.length()));
    * //dispatcher.getRequest().getSession(true).setAttribute(subjectArtifact.getName()+"_FileStorage",
    * fileStorage); } catch (IOException e){
    * dispatcher.getRequest().getSession(true).setAttribute(subjectArtifact.getName()+"_Message",
    * Configuration.getText("ui.reserverelease.message.ioError", locale)); } }
    * else //write message {
    * dispatcher.getResponse().setContentType("text/plain");
    * dispatcher.getResponse().reset(); try {
    * dispatcher.getResponse().getWriter().println(URLEncoder.encode(message,
    * "UTF-8")); } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
    * catch (IOException e) { e.printStackTrace(); }
    * 
    * try { dispatcher.getResponse().getWriter().close(); } catch (IOException
    * e) { e.printStackTrace(); } }
    */
   }

   protected void buildResponse(CommandDispatcher dispatcher) throws IOException {}
}