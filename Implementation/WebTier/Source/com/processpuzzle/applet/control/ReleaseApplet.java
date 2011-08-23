package com.processpuzzle.applet.control;

import java.applet.Applet;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLDocument;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLImageElement;
import org.w3c.dom.html.HTMLInputElement;
import org.w3c.dom.html.HTMLTableCellElement;

import com.sun.java.browser.dom.DOMAccessException;
import com.sun.java.browser.dom.DOMAccessor;
import com.sun.java.browser.dom.DOMAction;
import com.sun.java.browser.dom.DOMService;
import com.sun.java.browser.dom.DOMUnsupportedException;

public class ReleaseApplet extends Applet {

   public class MyDOMAction implements DOMAction {
      private ArrayList<HTMLInputElement> selectedCheckBoxes = null;
      private ArrayList<HTMLImageElement> images = null;
      private ArrayList<HTMLInputElement> messages = null;
      private ArrayList<HTMLTableCellElement> modifierCells = null;
      private HTMLDocument document = null;
      private HTMLInputElement stepButton;
      private HTMLInputElement resetButton;
      private HTMLInputElement stepInterval;
      private HTMLInputElement showHideButton;
      private HTMLInputElement imgSelectorButton;
      private HTMLInputElement imgSelector;
      private HTMLInputElement resetPageButton;
      private HTMLElement infoLine;

      public Object run(DOMAccessor accessor) {
         document = (HTMLDocument) accessor.getDocument(ja);
         showHideButton = (HTMLInputElement) document.getElementsByName("showHideButton").item(0);
         stepButton = (HTMLInputElement) document.getElementsByName("stepButton").item(0);
         resetButton = (HTMLInputElement) document.getElementsByName("resetButton").item(0);
         stepInterval = (HTMLInputElement) document.getElementsByName("stepInterval").item(0);
         imgSelectorButton = (HTMLInputElement) document.getElementsByName("imageSelectorButton").item(0);
         imgSelector = (HTMLInputElement) document.getElementsByName("imageSelector").item(0);
         resetPageButton = (HTMLInputElement) document.getElementsByName("resetPageButton").item(0);
         infoLine = (HTMLElement) document.getElementById("infoLine");
         NodeList checkBoxes = document.getElementsByName("artifactNames");
         selectedCheckBoxes = new ArrayList<HTMLInputElement>();
         images = new ArrayList<HTMLImageElement>();
         messages = new ArrayList<HTMLInputElement>();
         modifierCells = new ArrayList<HTMLTableCellElement>();

         for (int i = 0; i < checkBoxes.getLength(); i++) {
            HTMLInputElement elem = (HTMLInputElement) checkBoxes.item(i);
            if (elem.getChecked()) {
               selectedCheckBoxes.add(elem);
               images.add((HTMLImageElement) document.getElementById("img" + elem.getValue()));
               messages.add((HTMLInputElement) document.getElementById("msg" + elem.getValue()));
               modifierCells.add((HTMLTableCellElement) document.getElementById("modifier" + elem.getValue()));
            }
         }
         return null;
      }

      public HTMLDocument getDocument() {
         return document;
      }

      public ArrayList<HTMLInputElement> getSelectedCheckBoxes() {
         return selectedCheckBoxes;
      }

      public HTMLInputElement getStepButton() {
         return stepButton;
      }

      public HTMLInputElement getResetButton() {
         return resetButton;
      }

      public HTMLInputElement getStepInterval() {
         return stepInterval;
      }

      public HTMLInputElement getShowHideButton() {
         return showHideButton;
      }

      public ArrayList<HTMLImageElement> getImages() {
         return images;
      }

      public ArrayList<HTMLInputElement> getMessages() {
         return messages;
      }

      public HTMLInputElement getImgSelector() {
         return imgSelector;
      }

      public HTMLInputElement getImgSelectorButton() {
         return imgSelectorButton;
      }

      public HTMLInputElement getResetPageButton() {
         return resetPageButton;
      }

      public HTMLElement getInfoLine() {
         return infoLine;
      }

      protected ArrayList<HTMLTableCellElement> getModifierCells() {
         return modifierCells;
      }
   };

   private static final long serialVersionUID = 8566762977388286405L;
   private String message = "";
   private Button okButton;
   private File file;
   private Applet ja = this;
   private MyDOMAction dAction;

   public ReleaseApplet() throws HeadlessException {
      super();
   }

   public void init() {
      setLayout(new GridLayout(0, 1));
      okButton = new Button(getParameter("buttonText"));
      okButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            sendFile();
         }
      });
      add(okButton);
   }

   private void sendFile() {
      URL url;
      String host = getCodeBase().getHost();
      String protocol = getCodeBase().getProtocol();
      int port = getCodeBase().getPort();
      ArrayList<HTMLInputElement> selectedArtifactNames = getSelectedArtifactNames();
      resetPage();
      showHideProgressBar();
      for (int i = 0; i < selectedArtifactNames.size(); i++) {
         String status = "ok";
         HTMLInputElement element = selectedArtifactNames.get(i);
         HttpURLConnection con = null;
         String artifactName = element.getValue();
         String fileName = element.getId();
         message = "";
         String parameters = "?action=ReleaseArtifact&method=canRelease&subjectArtifactName=" + artifactName;
         String urlSuffix = "/ADIBrowserInterface/CommandControllerServlet" + parameters;
         resetProgressBar(fileName);
         try {
            boolean l = true;
            url = new URL(protocol, host, port, urlSuffix);
            con = (HttpURLConnection) url.openConnection();
            if ((fileName != null) && !(fileName.equals(""))) {
               file = new File(getParameter("location") + "\\" + fileName);
               if ((file == null) || !(file.exists())) {
                  message = getParameter("missingFileMessage") + getParameter("location") + "\\" + fileName + ").";
                  setMessage(message, i, artifactName);
                  l = false;
               }
            }
            if (l) {
               con = (HttpURLConnection) url.openConnection();
               con.setDoInput(true);
               con.setDoOutput(true);
               con.setUseCaches(false);
               InputStream result = con.getInputStream();
               byte buffer[] = new byte[1024];
               int length;
               message = "";
               while ((length = result.read(buffer)) != -1) {
                  message += new String(buffer, 0, length);
               }
               con.disconnect();
               if (message.length() == 3) {
                  if ((fileName != null) && !(fileName.equals(""))) {
                     parameters = "?action=ReleaseArtifact&method=release&subjectArtifactName=" + artifactName;
                     urlSuffix = "/ADIBrowserInterface/CommandControllerServlet" + parameters;
                     url = new URL(protocol, host, port, urlSuffix);
                     con = (HttpURLConnection) url.openConnection();
                     con.setRequestProperty("Content-Type", "application/msword");
                     con.setRequestMethod("POST");
                     con.setDoOutput(true);
                     con.setDoInput(true);
                     con.setUseCaches(false);
                     BufferedOutputStream out = new BufferedOutputStream(con.getOutputStream());
                     BufferedInputStream fr = new BufferedInputStream(new FileInputStream(file));
                     int blocks = 300;
                     if ((file.length() / 1024 > 0) && (file.length() != 0)) {
                        blocks = 300 / ((new Long(file.length()).intValue()) / 1024);
                     }
                     if (blocks <= 0)
                        blocks = 1;
                     setStepInterval(new Integer(blocks).toString());
                     int start = 0;
                     length = 1024;
                     int offset = -1;
                     while ((offset = fr.read(buffer, start, length)) != -1) {
                        out.write(buffer, start, offset);
                        executeProgressBar();
                     }
                     out.close();
                     con.getInputStream();
                     fr.close();
                     con.disconnect();
                     message = (new Long(file.length())).toString();
                  } else
                     message = "";
                  parameters = "?action=ReleaseArtifact&method=released&subjectArtifactName=" + artifactName + "&status=" + status
                        + "&fileSize=" + message;
                  urlSuffix = "/ADIBrowserInterface/CommandControllerServlet" + parameters;
                  url = new URL(protocol, host, port, urlSuffix);
                  con = (HttpURLConnection) url.openConnection();
                  con.setRequestProperty("Content-Type", "text/plain");
                  con.setRequestMethod("POST");
                  con.setDoInput(true);
                  con.setDoOutput(true);
                  con.setUseCaches(false);
                  result = con.getInputStream();
                  message = "";
                  while ((length = result.read(buffer)) != -1) {
                     message += new String(buffer, 0, length);
                  }
                  con.disconnect();
                  message = URLDecoder.decode(message, "UTF-8");
               }
            }
         } catch (IOException e) {
            if (con != null)
               con.disconnect();
            message = getParameter("ioErrorMessage");
         }
         try {
            setMessage(URLDecoder.decode(message, "UTF-8"), i, artifactName);
         } catch (UnsupportedEncodingException e) {}
         if (message.length() == 3) {
            if ((file != null) && (file.exists())) {
               file.delete();
            }
         }
      }
      showHideProgressBar();
   }

   private void resetProgressBar(String fileName) {
      dAction.getResetButton().click();
      dAction.getInfoLine().setAttribute("innerHTML", getParameter("buttonText") + ": " + fileName);
   }

   private void executeProgressBar() {
      dAction.getStepButton().click();
   }

   private void setStepInterval(String step) {
      dAction.getStepInterval().setValue(step);
   }

   private void showHideProgressBar() {
      dAction.getShowHideButton().click();
   }

   private void setMessage(String message, int index, String artifactName) {
      if (message.length() == 3) {
         dAction.getImages().get(index).setSrc("./images/icons/ok.JPG");
         dAction.getMessages().get(index).setValue(getParameter("acceptedText"));
         dAction.getModifierCells().get(index).setAttribute("innerHTML", "");
      } else {
         dAction.getImages().get(index).setSrc("./images/icons/error.JPG");
         dAction.getMessages().get(index).setValue(message);
      }
      dAction.getImgSelector().setValue(artifactName);
      dAction.getImgSelectorButton().click();
   }

   private ArrayList<HTMLInputElement> getSelectedArtifactNames() {
      dAction = new MyDOMAction();
      try {
         DOMService service = DOMService.getService(this);
         service.invokeAndWait(dAction);
      } catch (DOMAccessException dae) {
         dae.printStackTrace();
      } catch (DOMUnsupportedException use) {
         use.printStackTrace();
      }
      return dAction.getSelectedCheckBoxes();
   }

   private void resetPage() {
      dAction.getResetPageButton().click();
   }
}
