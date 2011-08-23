package com.processpuzzle.application.control.control;

import com.processpuzzle.application.configuration.control.CommandMapping;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.user_session.domain.UserRequestManager;

class CommandFactory {

   private CommandFactory() {}

   private static CommandMapping getCommandMapping() {
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      String commandMappingClassName = (String) applicationContext.getProperty( PropertyKeys.COMMAND_MAPPING.getDefaultKey() );

      if( commandMappingClassName == null )
         return null;

      Class<?> commandMappingClass = null;
      try{
         commandMappingClass = Class.forName( commandMappingClassName );
      }catch( ClassNotFoundException e1 ){
         e1.printStackTrace();
         return null;
      }

      CommandMapping commandMapping = null;
      try{
         commandMapping = (CommandMapping) commandMappingClass.newInstance();
      }catch( InstantiationException e1 ){
         e1.printStackTrace();
         return null;
      }catch( IllegalAccessException e1 ){
         e1.printStackTrace();
         return null;
      }

      return commandMapping;
   }

   public static CommandInterface getCommand( String action ) {

      CommandMapping commandMapping = getCommandMapping();

      Class<UnknownFrontCommand> newCommandClass = null;
      if( commandMapping != null )
         newCommandClass = (Class<UnknownFrontCommand>) commandMapping.getDomainClassRepositoryMappings().get( action );
      if( newCommandClass == null )
         newCommandClass = UnknownFrontCommand.class;
      ;

      CommandInterface newCommand = null;
      if( newCommandClass != null ){
         try{
            newCommand = (CommandInterface) newCommandClass.newInstance();
         }catch( Exception e ){
            newCommand = new UnknownFrontCommand( "Unable to get an instance of: '" + action + "'Command class." );
         }
      }else{
         newCommand = new UnknownFrontCommand( "Unable to find the class of action: '" + action + "'." );
      }
      return newCommand;
   }
}