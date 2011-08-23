package com.processpuzzle.artifact.domain;

import java.util.Calendar;

import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.party.domain.Person;

public class Comment extends HTMLText {
   private Person author;
   private String title;
   private TimePoint creationDate;

   public Comment( Person author, String title, String text) {
      this.author = author;
      this.title = title;
      super.setText(text);
      this.creationDate = new TimePoint(Calendar.getInstance().getTime());
   }

   protected Comment() {
      super();
   }

   public Person getAuthor() {
      return author;
   }

   public void setAuthor(Person author) {
      this.author = author;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public TimePoint getCreationDate() {
      return creationDate;
   }

   public void setCreationDate(TimePoint creationDate) {
      this.creationDate = creationDate;
   }
}