package com.processpuzzle.file.control;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.FileStorage;
import com.processpuzzle.artifact.domain.FileStorageRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class DownloadFile extends HttpServlet {

   private static final long serialVersionUID = -5495466682128172989L;

   public void init(ServletConfig config) throws ServletException {
      super.init();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      BufferedInputStream inputFile = null;
      FileStorage fileStorage = null;
      if (request.getParameter("fileId") != null) {
         try {
            Integer id = Integer.valueOf((String) request.getParameter("fileId"));
            ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
            FileStorageRepository fileStorageRepository = applicationContext.getRepository( FileStorageRepository.class);
            fileStorage = fileStorageRepository.findFileStorageById(work, id);

            inputFile = new BufferedInputStream(new FileInputStream(new java.io.File(fileStorage.getSource())));

            response.setContentType(fileStorage.getContentType());
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileStorage.getOriginalFileName());
            response.setDateHeader("UploadedDate", fileStorage.getUploadDate().getTime());
            response.setDateHeader("DownloadedDate", Calendar.getInstance().getTimeInMillis());

            if (fileStorage.getSize().longValue() > Integer.MAX_VALUE)
               response.setContentLength(Integer.MAX_VALUE); // 2MB is the max
            else
               response.setContentLength(fileStorage.getSize().intValue());

            OutputStream outputStream = response.getOutputStream();
            int start = 0;
            int length = 1024;
            int offset = -1;
            byte[] buffer = new byte[length];

            while ((offset = inputFile.read(buffer, start, length)) != -1) {
               outputStream.write(buffer, start, offset);
            }

            response.getOutputStream().flush();

         } catch (Exception e) {
            inputFile = null;
         } finally {
            if (inputFile != null) {
               inputFile.close();
            }
         }
      }
      work.finish();      
   }
}