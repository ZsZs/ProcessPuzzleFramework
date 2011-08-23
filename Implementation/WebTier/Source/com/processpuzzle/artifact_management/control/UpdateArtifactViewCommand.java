package com.processpuzzle.artifact_management.control;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class UpdateArtifactViewCommand extends ArtifactViewCommand {
   public static final String COMMAND_NAME = "UpdateView";

   public void init( CommandDispatcher dispatcher ) {
      super.init( dispatcher );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Class<?> viewTypeClass = null;
      try{
         viewTypeClass = Class.forName( subjectArtifactView.getClass().getName() );
      }catch( ClassNotFoundException e ){
         e.printStackTrace();
      }
      Method idMethod = null;
      try{
         String idParameter = dispatcher.getRequest().getParameter( "id" );
         if( idParameter != null && !idParameter.equals( "" ) ){
            idMethod = viewTypeClass.getDeclaredMethod( "setId", new Class[] { String.class } );
            try{
               idMethod.invoke( subjectArtifactView, new Object[] { idParameter } );
            }catch( IllegalArgumentException e ){
               e.printStackTrace();
            }catch( IllegalAccessException e ){
               e.printStackTrace();
            }catch( InvocationTargetException e ){
               e.printStackTrace();
            }
         }
      }catch( SecurityException e1 ){
         e1.printStackTrace();
      }catch( NoSuchMethodException e1 ){
         e1.printStackTrace();
      }

      Method meth = null;
      String methodParameter = dispatcher.getRequest().getParameter( "method" );
      try{

         if( methodParameter != null && !methodParameter.equals( "" ) ){
            meth = viewTypeClass.getSuperclass().getDeclaredMethod( "setMethod", new Class[] { String.class } );
         }
      }catch( SecurityException e ){
         e.printStackTrace();
      }catch( NoSuchMethodException e ){
         try{
            meth = viewTypeClass.getSuperclass().getSuperclass().getDeclaredMethod( "setMethod", new Class[] { String.class } );
         }catch( SecurityException e1 ){
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }catch( NoSuchMethodException e1 ){
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
      }
      try{
         if( meth != null )
            meth.invoke( subjectArtifactView, new Object[] { methodParameter } );
      }catch( IllegalArgumentException e ){
         e.printStackTrace();
      }catch( IllegalAccessException e ){
         e.printStackTrace();
      }catch( InvocationTargetException e ){
         e.printStackTrace();
      }
      // todo: rewrite
      List<Method> methods = new ArrayList<Method>();
      Method[] selfmethods = viewTypeClass.getDeclaredMethods();
      Method[] ancestorMethods = viewTypeClass.getSuperclass().getDeclaredMethods();
      Method[] aancestorMethods = viewTypeClass.getSuperclass().getSuperclass().getDeclaredMethods();

      methods.addAll( Arrays.asList( selfmethods ) );
      methods.addAll( Arrays.asList( ancestorMethods ) );
      methods.addAll( Arrays.asList( aancestorMethods ) );
      for( Iterator<Method> iter = methods.iterator(); iter.hasNext(); ){
         Method method = iter.next();

         if( (method.getName().indexOf( "set" ) != -1) && (method.getName().indexOf( "id" ) == -1) && (method.getName().indexOf( "performAction" ) == -1)
               && (method.getName().indexOf( "method" ) == -1) ){
            String requestParameterName = method.getName().substring( 3, 4 ).toLowerCase() + method.getName().substring( 4, method.getName().length() );

            for( Iterator<?> i = dispatcher.getRequest().getParameterMap().entrySet().iterator(); i.hasNext(); ){
               Map.Entry e = (Map.Entry) i.next();
               String key = e.getKey().toString();
               {
                  if( key.equals( requestParameterName ) ){
                     for( int j = 0; j < ((String[]) e.getValue()).length; j++ ){
                        String value = ((String[]) e.getValue())[j];

                        if( (value != null) && (!value.equals( "" )) ){
                           try{
                              method.invoke( subjectArtifactView, new Object[] { value } );
                           }catch( IllegalArgumentException ex ){
                              ex.printStackTrace();
                           }catch( IllegalAccessException ex ){
                              ex.printStackTrace();
                           }catch( InvocationTargetException ex ){
                              ex.printStackTrace();
                           }
                        }
                     }
                  }
               }
            }

         }
      }

      Method performActionMethod = null;
      try{
         performActionMethod = viewTypeClass.getDeclaredMethod( "performAction", new Class[] {} );
      }catch( SecurityException e ){
         e.printStackTrace();
      }catch( NoSuchMethodException e ){
         System.out.println( "performAction method not found!" );
      }
      try{
         if( performActionMethod != null )
            performActionMethod.invoke( subjectArtifactView, new Object[] {} );
      }catch( IllegalArgumentException e ){
         e.printStackTrace();
      }catch( IllegalAccessException e ){
         e.printStackTrace();
      }catch( InvocationTargetException e ){
         e.printStackTrace();
      }

      Method forwardMethod = null;
      try{
         String forwardParameter = dispatcher.getRequest().getParameter( "forward" );
         if( forwardParameter != null && !forwardParameter.equals( "" ) ){
            forwardMethod = viewTypeClass.getSuperclass().getSuperclass().getDeclaredMethod( "setForward", new Class[] { String.class } );
            try{
               forwardMethod.invoke( subjectArtifactView, new Object[] { forwardParameter } );
            }catch( IllegalArgumentException e ){
               e.printStackTrace();
            }catch( IllegalAccessException e ){
               e.printStackTrace();
            }catch( InvocationTargetException e ){
               e.printStackTrace();
            }
         }
      }catch( SecurityException e1 ){
         e1.printStackTrace();
      }catch( NoSuchMethodException e1 ){
         e1.printStackTrace();
      }

      DefaultArtifactRepository artifactRepository = (DefaultArtifactRepository) applicationContext.getRepository( DefaultArtifactRepository.class );
      artifactRepository.update( work, subjectArtifact );
      work.finish();
      // artifactView = (ArtifactView)
      // subjectArtifact.getAvailableViews().get(artifactViewIdentifier);
      // artifactView.setCodeBase(dispatcher.getRequest().getRequestURL().toString());
      // System.out.println(artifactView.getCodeBase());
      // dispatcher.getRequest().setAttribute("artifactView", artifactView);
   }

   public String execute( CommandDispatcher dispatcher ) throws Exception {
      return subjectArtifactView.getType().getPresentationUri();
   }

   //
   // protected void retrieveRequestParameters(CommandDispatcher dispatcher) {}
   //
   protected void retrieveResponseDocument() {}

}
