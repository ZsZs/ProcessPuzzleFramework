package com.processpuzzle.persistence.domain;

public abstract class SessionContext {
   abstract void open();
   abstract void close();
   abstract void discard();
}
