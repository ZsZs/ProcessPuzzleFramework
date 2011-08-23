/*
 * Created on Dec 1, 2006
 */
package com.processpuzzle.workflow.protocol.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import hu.itkodex.litest.template.RepositoryTestTemplate;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class ProtocolRepositoryTest extends RepositoryTestTemplate<ProtocolRepository, ProtocolRepositoryTestFixture, Protocol> {

   public ProtocolRepositoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   @Test
   public void testAdd_ForOwnedAttributesAndComponents() {
      assertEquals( fixture.getXpLifecycle().getName(), databaseSpy.retrieveColumnFromRow( "T_LIFECYCLE_PROTOCOL", fixture.getXpLifecycle().getId(), String.class, "name" ) );
      assertEquals( fixture.getXpLifecycle().isMandatory(), databaseSpy.retrieveColumnFromRow( "T_LIFECYCLE_PROTOCOL", fixture.getXpLifecycle().getId(), Boolean.class, "mandatory" ) );
   }

   @Override
   @Ignore
   public void testAdd_ForReferencedAggregateRoots() {
   }

   @Override
   @Ignore
   @Test
   public void testDelete_ForOwnedAttributesAndComponents() {
      repository.delete( testWork, root );
      testWork.finish();
      assertNull( databaseSpy.retrieveColumnFromRow( "T_PROTOCOL", root.getId(), Boolean.class, "mandatory" ) );
   }

   @Override
   @Ignore
   public void testFindAll_ForResultCount() {
   // TODO Auto-generated method stub

   }

   @Override
   @Test
   public void testFindById() {
      assertNotNull( fixture.getXpLifecycle().getId() );
   }

   @Override
   @Ignore
   public void testFindById_ForEagerLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   @Ignore
   public void testFindById_ForLazyLoadedComponents() {
   // repository.getSupportedAggregateRootClass();
   // System.out.println(repository.getSupportedAggregateRootClass());
   // testWork.finish();
   }

   @Override
   @Ignore
   public void testFindByQuery_ForComponentAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   @Ignore
   public void testFindByQuery_ForDirectAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   @Ignore
   @Test
   public void testUpdate_ForOwnedAttributesAndComponents() {
      root.setMandatory( false );
      repository.update( testWork, root );
      testWork.finish();
      assertEquals( root.isMandatory(), databaseSpy.retrieveColumnFromRow( "T_PROTOCOL", root.getId(), Boolean.class, "mandatory" ) );
   }

   @Override
   @Ignore
   public void testUpdate_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub

   }

   // private static ProtocolTestFixture testFixture =
   // ProtocolTestFixture.getInstance();
   // private static ProtocolRepository repository = null;
   // private LifecycleProtocol lifecycle = null;

   // @Before
   // public void setUp() throws Exception {
   // UnitOfWork work = new UnitOfWork(true);
   // testFixture.setUp();
   // repository = OPConfigurationFixture.getProtocolRepository();
   // lifecycle = testFixture.getLifeCycle();
   // repository.addLifecycle(work, lifecycle);
   // work.finish();
   // }
   //
   // @After
   // public void tearDown() throws Exception {
   // repository.deleteLifecycle(lifecycle);
   // testFixture.tearDown();
   // repository = null;
   // lifecycle = null;
   // }
   //
   // @Ignore
   // @Test
   // public final void testAddLifecycle() {
   // UnitOfWork work = new UnitOfWork(true);
   // repository.addLifecycle(work, lifecycle);
   // work.finish();
   // }

}
