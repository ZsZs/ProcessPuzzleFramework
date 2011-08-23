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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

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

public class ReserveApplet extends Applet {

   class MyDOMAction implements DOMAction {
    //  private ArrayList selectedCheckBoxes = null;
      private HTMLInputElement subjectElement = null;
      private ArrayList<?> images = null;
      private ArrayList<?> messages = null;
      private ArrayList<?> modifierCells = null;
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
         showHideButton = (HTMLInputElement) document.getElementsByName("showHideButton").item(0);// pB
         stepButton = (HTMLInputElement) document.getElementsByName("stepButton").item(0);// pB
         resetButton = (HTMLInputElement) document.getElementsByName("resetButton").item(0);// pB
         stepInterval = (HTMLInputElement) document.getElementsByName("stepInterval").item(0);// pB
         infoLine = (HTMLElement) document.getElementById("infoLine");// pB
         imgSelectorButton = (HTMLInputElement) document.getElementsByName("imageSelectorButton").item(0);// dRw
         imgSelector = (HTMLInputElement) document.getElementsByName("imageSelector").item(0);// dRw
         resetPageButton = (HTMLInputElement) document.getElementsByName("resetPageButton").item(0);// dRw
     //    NodeList checkBoxes = document.getElementsByName("selectCheckbox");// jsp
     //    selectedCheckBoxes = new ArrayList();
         subjectElement = (HTMLInputElement) document.getElementById("subjectElement");
         
         images = new ArrayList();
         messages = new ArrayList();
         modifierCells = new ArrayList();

//         for (int i = 0; i < checkBoxes.getLength(); i++) {
//            HTMLInputElement elem = (HTMLInputElement) checkBoxes.item(i);
//            if (elem.getChecked()) {
//               selectedCheckBoxes.add(elem);
//               images.add((HTMLImageElement) document.getElementById("img" + elem.getValue()));
//               messages.add((HTMLInputElement) document.getElementById("msg" + elem.getValue()));
//               modifierCells.add((HTMLTableCellElement) document.getElementById("modifier" + elem.getValue()));
//            }
//         }
         return null;
      }

      public HTMLDocument getDocument() {
         return document;
      }

//      public ArrayList getSelectedCheckBoxes() {
//         return selectedCheckBoxes;
//      }

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

      public ArrayList<?> getImages() {
         return images;
      }

      public ArrayList<?> getMessages() {
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

      protected ArrayList<?> getModifierCells() {
         return modifierCells;
      }

      public HTMLInputElement getSubjectElement() {
         return subjectElement;
      }
   };

   private static final long serialVersionUID = 8566762977388286405L;
   private String message = "";
   private Button okButton;
   private File folder;
   private File file;
   private Applet ja = this;
   private MyDOMAction dAction;

   public ReserveApplet() throws HeadlessException {
      super();
   }

   public void init() {
      setLayout(new GridLayout(0, 1));
      okButton = new Button(getParameter("buttonText"));
      okButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            receiveFile();
         }
      });
      add(okButton);
   }

   private void receiveFile() {
      System.out.println("oksa");
      URL url;
      folder = new File(getParameter("location"));
      if (!(folder.isDirectory())) {
         folder.mkdir();
      }
      System.out.println(folder);
      String host = getCodeBase().getHost();
      String protocol = getCodeBase().getProtocol();
      int port = getCodeBase().getPort();
      System.out.println(host+""+protocol+""+port);
//      ArrayList selectedArtifactNames = getSelectedArtifactNames();
   //   resetPage();
  //    showHideProgressBar();
  //    for (int i = 0; i < selectedArtifactNames.size(); i++) {
         String status = "ok";
     //    HTMLInputElement element = (HTMLInputElement) selectedArtifactNames.get(i);
         HTMLInputElement element = (HTMLInputElement) getSubjectElement();
         System.out.println(element);
         HttpURLConnection con = null;
         String artifactName = element.getValue();
         String fileName = element.getId();
         System.out.println(artifactName+" "+fileName);
         String parameters = "?action=ReserveArtifact&method=canReserve&subjectArtifactName=" + artifactName;
         String urlSuffix = "/AnzsoBrowserInterface/CommandControllerServlet" + parameters;
         System.out.println(urlSuffix);
       //  resetProgressBar(fileName);
         try {
            url = new URL(protocol, host, port, urlSuffix); System.out.println(url);
            con = (HttpURLConnection) url.openConnection(); System.out.println(con);
            file = new File(getParameter("location") + "\\" + fileName); System.out.println(file);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            InputStream result = con.getInputStream();
            byte buffer[] = new byte[1024];
            message = "";
            int length;
            while ((length = result.read(buffer)) != -1) {
               message += new String(buffer, 0, length);
            }
            con.disconnect();
            if (message.length() == 3) {
               if ((fileName != null) && !(fileName.equals(""))) {
                  parameters = "?action=ReserveArtifact&method=reserve&subjectArtifactName=" + artifactName;
                  urlSuffix = "/ADIBrowserInterface/CommandControllerServlet" + parameters;
                  url = new URL(protocol, host, port, urlSuffix);
                  con = (HttpURLConnection) url.openConnection();
                  con.setRequestProperty("Content-Type", "text/plain");
                  con.setRequestMethod("POST");
                  con.setDoInput(true);
                  BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                  BufferedInputStream fr = new BufferedInputStream(con.getInputStream());
                  int blocks = 300;
                  if ((con.getContentLength() / 1024 > 0) && (con.getContentLength() != 0)) {
                     blocks = 300 / (con.getContentLength() / 1024);
                  }
                  if (blocks <= 0)
                     blocks = 1;
              //    setStepInterval(new Integer(blocks).toString());
                  int start = 0;
                  length = 1024;
                  int offset = -1;
                  while ((offset = fr.read(buffer, start, length)) != -1) {
                     out.write(buffer, start, offset);
                   //  executeProgressBar();
                  }
                  out.close();
                  con.disconnect();
                  if ((new Integer(con.getContentLength())).longValue() != file.length()) {
                     status = "wrong";
                  }
               }
               parameters = "?action=ReserveArtifact&method=reserved&subjectArtifactName=" + artifactName + "&status=" + status;
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
         } catch (IOException e) {
            if (con != null)
               con.disconnect();
            message = getParameter("ioErrorMessage");
         }
//         try {
//            setMessage(URLDecoder.decode(message, "UTF-8"), 1 /*was i*/, artifactName);
//         } catch (UnsupportedEncodingException e) {}
    //  }
  //    showHideProgressBar();
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
         ((HTMLImageElement) dAction.getImages().get(index)).setSrc("./images/icons/ok.JPG");
         ((HTMLInputElement) dAction.getMessages().get(index)).setValue(getParameter("acceptedText"));
         ((HTMLTableCellElement) dAction.getModifierCells().get(index)).setAttribute("innerHTML", getParameter("modifierName"));
      } else {
         ((HTMLImageElement) dAction.getImages().get(index)).setSrc("./images/icons/error.JPG");
         ((HTMLInputElement) dAction.getMessages().get(index)).setValue(message);
      }
      dAction.getImgSelector().setValue(artifactName);
      dAction.getImgSelectorButton().click();
   }

//   private ArrayList getSelectedArtifactNames() {
//      dAction = new MyDOMAction();
//      try {
//         DOMService service = DOMService.getService(this);
//         service.invokeAndWait(dAction);
//      } catch (DOMAccessException dae) {
//         dae.printStackTrace();
//      } catch (DOMUnsupportedException use) {
//         use.printStackTrace();
//      }
//      return dAction.getSelectedCheckBoxes();
//   }
   
   private HTMLInputElement getSubjectElement() {
      dAction = new MyDOMAction();
      try {
         DOMService service = DOMService.getService(this);
         service.invokeAndWait(dAction);
      } catch (DOMAccessException dae) {
         dae.printStackTrace();
      } catch (DOMUnsupportedException use) {
         use.printStackTrace();
      }
      return dAction.getSubjectElement();
   }

   private void resetPage() {
      dAction.getResetPageButton().click();
   }
}
