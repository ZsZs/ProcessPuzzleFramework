package com.processpuzzle.application.configuration.domain;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.common.collect.Lists;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.domain.TestEntitySubclass;
import com.processpuzzle.user_session.domain.UserRequestManager;

@RunWith( PowerMockRunner.class )
@PrepareForTest( UserRequestManager.class )
public class RunTimeClassHierarchyAnalyserTest {
   private User currentUser;
   private RunTimeClassHierarchyAnalyser hierarchyAnalyser;
   private UserRequestManager userRequestManager;
   
   @Before public void beforeEachTest() {
      currentUser = mock( User.class );
      userRequestManager = mock( UserRequestManager.class );
      PowerMockito.mockStatic( UserRequestManager.class );
      when( UserRequestManager.getInstance() ).thenReturn( userRequestManager );
      when( userRequestManager.currentUser() ).thenReturn( currentUser );
      
      UserRequestManager.getInstance().currentUser();
      
      hierarchyAnalyser = new RunTimeClassHierarchyAnalyser();
   }
   
   @Test public void checkIfIsChildOf_SholdInvestigeteAllAncestors() {
      assertThat( hierarchyAnalyser.checkIfIsChildOf( TestEntitySubclass.class, TestEntity.class ), is( true ));
      assertThat( hierarchyAnalyser.checkIfIsChildOf( TestEntitySubclass.class, GenericEntity.class ), is( true ));
   }
   
   @Test public void findSubClasses_WhenTargetPackageIsGiven_ReturnsSubclasses() throws FileNotFoundException {
      List<String> targetPackages = Lists.newArrayList();
      targetPackages.add( "com.processpuzzle.application.configuration.domain" );

      Set<Class<?>> foundClasses = hierarchyAnalyser.findSubClasses( targetPackages, RunTimeClassHierarchyAnalyser.class.getName() );
      Class<?> classLookedFor = ClassAnalyserSubclass.class;
      assertThat( foundClasses, hasItem( classLookedFor ) );
   }
   
}
