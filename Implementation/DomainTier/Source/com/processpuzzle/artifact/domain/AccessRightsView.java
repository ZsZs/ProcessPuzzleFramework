package com.processpuzzle.artifact.domain;

import hu.itkodex.commons.persistence.RepositoryResultSet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.AccessRight;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.party.domain.PersonRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class AccessRightsView extends ArtifactView<Artifact<?>> {
   private String rightId;
   private String userId = null;
   private UserRepository userRepository;
   private PersonRepository personRepository;

   public AccessRightsView( Artifact<?> artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
      userRepository = applicationContext.getRepository( UserRepository.class );
      personRepository = applicationContext.getRepository( PersonRepository.class );
   }

   public void initializeView() {}

   public String getRightId() {
      return rightId;
   }

   public void setUserId( String userId ) {
      this.userId = userId;
   }

   public Map<String, AccessRight> getRightsForArtifact() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      UserRepository userRepository = (UserRepository) ProcessPuzzleContext.getInstance().getRepository( UserRepository.class );
      Map<String, AccessRight> accessRights = new HashMap<String, AccessRight>();

      if( loggedInUser != null ){
         if( loggedInUser.getUserName().equals( "Administrator" ) ){
            for( Iterator<User> pIt = userRepository.findAllUser( work ).iterator(); pIt.hasNext(); ){
               User user = (User) pIt.next();
               AccessRight right = getRightByUserAndAccessControlledObject( work, user, parentArtifact.getName() );
               if( right != null ){
                  accessRights.put( user.getId().toString(), right );
               }else{
                  accessRights.put( user.getId().toString(), (AccessRight) ((ArtifactType) parentArtifact.getType()).getDefaultRightByUserRole( "Administrator" ) );
               }
            }
         }else{
            AccessRight right = getRightByUserAndAccessControlledObject( work, loggedInUser, parentArtifact.getName() );
            if( right != null ){
               accessRights.put( loggedInUser.getId().toString(), right );
            }else{
               accessRights.put( loggedInUser.getId().toString(), (AccessRight) ((ArtifactType) parentArtifact.getType()).getDefaultRightByUserRole( "User" ) );
            }
         }
      }
      work.finish();
      return accessRights;
   }

   private AccessRight getRightByUserAndAccessControlledObject( DefaultUnitOfWork work, User user, String name ) {
      RepositoryResultSet<User> users = userRepository.findUserByAccessRightName( work, parentArtifact.getName() );

      AccessRight right = null;
      for( Iterator<User> i = users.iterator(); i.hasNext(); ){
         User u = (User) i.next();
         if( u.getId() == user.getId() ){
            right = u.getRightByName( parentArtifact.getId() );
            break;
         }
      }
      return right;
   }

   public String getUserName() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Person person = personRepository.findByUserId( work, userId );
      work.finish();
      return person.getPartyName().getName();
   }

   public void setCanModify( String newValue ) {
   // StringTokenizer tokenizer = new StringTokenizer(newValue, "_");
   // boolean canModify = new Boolean(tokenizer.nextToken()).booleanValue();
   // UnitOfWork work = new UnitOfWork(true);
   // User user = userRepository.findUserById(work, new Integer(tokenizer.nextToken()));
   // //DefaultAccessRight right = getRight(tokenizer.nextToken());
   // User defaultUser=userRepository.findUserByAccessRightId(work, new Integer(tokenizer.nextToken()));
   // DefaultAccessRight right=defaultUser.getRightById(new Integer(tokenizer.nextToken()));
   // DefaultAccessRight defRight = ((ArtifactType)
   // parentArtifact.getType()).getDefaultRightByUserRole("Administrator");
   // if (right.isDefault()) {
   // if (right.getCanModify() != canModify) {
   // AccessRight aRight = new AccessRight(user, parentArtifact, defRight.getCanRead(), defRight.getCanCreate(),
   // canModify, defRight
   // .getCanDelete());
   // //arRepository.addAccessRight(work, aRight);
   // user.addAccessRight(aRight);
   // userRepository.updateUser(work, defaultUser);
   // }
   // } else {
   // if (right.getCanModify() != canModify) {
   // right.setCanModify(canModify);
   // userRepository.updateUser(work, defaultUser);
   // }
   // }
   // work.finish();
   }

   public void setCanCreate( String newValue ) {
   // UnitOfWork work = new UnitOfWork(true);
   // StringTokenizer tokenizer = new StringTokenizer(newValue, "_");
   // boolean canCreate = new Boolean(tokenizer.nextToken()).booleanValue();
   // User user = userRepository.findUserById(work, new Integer(tokenizer.nextToken()));
   // User defaultUser=userRepository.findUserByAccessRightId(work, new Integer(tokenizer.nextToken()));
   // DefaultAccessRight right=defaultUser.getRightById(new Integer(tokenizer.nextToken()));
   // DefaultAccessRight defRight = ((ArtifactType)
   // parentArtifact.getType()).getDefaultRightByUserRole("Administrator");
   // if (right.isDefault()) {
   // if (right.getCanCreate() != canCreate) {
   // AccessRight aRight = new AccessRight(user, parentArtifact, defRight.getCanRead(), canCreate,
   // defRight.getCanModify(), defRight
   // .getCanDelete());
   // user.addAccessRight(aRight);
   // userRepository.updateUser(work, defaultUser);
   // }
   // } else {
   // if (right.getCanCreate() != canCreate) {
   // right.setCanCreate(canCreate);
   // //arRepository.updateAccessRight(work, right);
   // userRepository.updateUser(work, defaultUser);
   // }
   // }
   // work.finish();
   }

   public void setCanDelete( String newValue ) {
   // UnitOfWork work = new UnitOfWork(true);
   // StringTokenizer tokenizer = new StringTokenizer(newValue, "_");
   // boolean canDelete = new Boolean(tokenizer.nextToken()).booleanValue();
   // User user = userRepository.findUserById(work, new Integer(tokenizer.nextToken()));
   //      
   // User defaultUser=userRepository.findUserByAccessRightId(work, new Integer(tokenizer.nextToken()));
   // DefaultAccessRight right=defaultUser.getRightById(new Integer(tokenizer.nextToken()));
   //      
   // DefaultAccessRight defRight = ((ArtifactType)
   // parentArtifact.getType()).getDefaultRightByUserRole("Administrator");
   // if (right.isDefault()) {
   // if (right.getCanDelete() != canDelete) {
   // AccessRight aRight = new AccessRight(user, parentArtifact, defRight.getCanRead(), defRight.getCanCreate(),
   // defRight
   // .getCanModify(), canDelete);
   // //arRepository.addAccessRight(work, aRight);
   // user.addAccessRight(aRight);
   // userRepository.updateUser(work, defaultUser);
   // }
   // } else {
   // if (right.getCanDelete() != canDelete) {
   // right.setCanDelete(canDelete);
   // //arRepository.updateAccessRight(work, right);
   // userRepository.updateUser(work, defaultUser);
   // }
   // }
   // work.finish();
   }

   public void setCanRead( String newValue ) {
   // UnitOfWork work = new UnitOfWork(true);
   // StringTokenizer tokenizer = new StringTokenizer(newValue, "_");
   // boolean canRead = new Boolean(tokenizer.nextToken()).booleanValue();
   // User user = userRepository.findUserById(work, new Integer(tokenizer.nextToken()));
   //      
   // User defaultUser=userRepository.findUserByAccessRightId(work, new Integer(tokenizer.nextToken()));
   // DefaultAccessRight right=defaultUser.getRightById(new Integer(tokenizer.nextToken()));
   //      
   // DefaultAccessRight defRight = ((ArtifactType)
   // parentArtifact.getType()).getDefaultRightByUserRole("Administrator");
   // if (right.isDefault()) {
   // if (right.getCanRead() != canRead) {
   // AccessRight aRight = new AccessRight(user, parentArtifact, canRead, defRight.getCanCreate(),
   // defRight.getCanModify(), defRight
   // .getCanDelete());
   // //arRepository.addAccessRight(work, aRight);
   // user.addAccessRight(aRight);
   // userRepository.updateUser(work, defaultUser);
   // }
   // } else {
   // if (right.getCanRead() != canRead) {
   // right.setCanRead(canRead);
   // //arRepository.updateAccessRight(work, right);
   // userRepository.updateUser(work, defaultUser);
   // }
   // }
   // work.finish();
   }

   // public DefaultAccessRight getRight(String rightId) {
   // UnitOfWork work = new UnitOfWork(true);
   // UserRepository userRepository = (UserRepository)
   // ProcessPuzzleContext.getInstance().getRepository(UserRepository.class);
   // DefaultAccessRight right = userRepository.getRight(work, rightId);
   // work.finish();
   // return right;
   // }
}