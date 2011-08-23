package com.processpuzzle.persistence.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import hu.itkodex.litest.template.RepositoryTest;
import hu.itkodex.litest.template.RepositoryTestTemplate;

import org.junit.Test;

import com.processpuzzle.fundamental_types.domain.AssertionException;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

import fitlibrary.specify.workflow.ParserDelegateMethod.Date;

public class TestEntityRepositoryTest extends RepositoryTestTemplate<TestEntityRepository, TestEntityRepositoryTestFixture, TestEntity> implements RepositoryTest {

   public TestEntityRepositoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Test
   public void testAddChildEntity_ForProperType() {
      TestEntitySubclass child = new TestEntitySubclass("child");
      DefaultUnitOfWork addChildWork = new DefaultUnitOfWork(true);
      repository.addChildEntity(addChildWork, child);
      addChildWork.finish();

      DefaultUnitOfWork findWork = new DefaultUnitOfWork(true);
      TestEntitySubclass childEntity = repository.findSubClassById( findWork, child.getId() );
      assertTrue("Reposiotry correctly instantiates a", childEntity instanceof TestEntitySubclass );
      findWork.finish();
   }

   @Test public void testAdd_ForOwnedAttributesAndComponents() {
      assertEquals(root.getTextAttribute(), databaseSpy.retrieveColumnFromRow("T_TEST_ENTITY", root.getId(), String.class, "textAttribute"));
      assertEquals("'testEntity_1' name is persited in database.", root.getName(), databaseSpy.retrieveColumnFromRow("T_TEST_ENTITY", root.getId(), String.class, "name"));
      assertEquals("'root' textAttribute is persited in database.", root.getTextAttribute(), databaseSpy.retrieveColumnFromRow("T_TEST_ENTITY", root.getId(), String.class, "textAttribute"));

      assertEquals(root.getNumberAttribute(), databaseSpy.retrieveColumnFromRow("T_TEST_ENTITY", root.getId(), Integer.class, "numberAttribute"));

      assertEquals(root.getTimePoint().getValue(), databaseSpy.retrieveColumnFromRow("T_TEST_ENTITY", root.getId(), Date.class, "timePoint"));

      assertEquals(root.getTimePeriod().getBegin().getValue(), getColumnValue( Date.class, "timePeriodBegin"));
      assertEquals(root.getTimePeriod().getEnd().getValue(), getColumnValue( Date.class, "timePeriodEnd"));
      
      assertEquals( root.getQuantity().getAmount(), getColumnValue( Double.class, "quantityAmount" ));
      
      String expectedUnitSymbol = root.getQuantity().getUnit().getSymbol();
      String savedUnitSymbol = (String) getColumnValue( String.class, "QUANTITYUNIT" );
      assertThat( savedUnitSymbol, equalTo( expectedUnitSymbol ));

      assertEquals("'root' enitiyComponentWithCascade is persisted in database.", root.getEnitiyComponentWithCascade().getId(),
            databaseSpy.retrieveColumnFromRow("T_TEST_ENTITY_COMPONENT", root.getEnitiyComponentWithCascade().getId(), Integer.class, "id"));
   }

   @Override
   @Test(expected = UnsavedObjectException.class)
   public void testAdd_ForReferencedAggregateRoots() {
      // Note: The 'testEntityWithoutCascade' serves here as referenced neighbour aggregate root.
      // These other aggregate roots should't be added/updated/deleted by cascade.
      // It's the client's responsibility to perform theese actions 'manually'
      TestEntity anotherTestEntity = new TestEntity("AnotherTestEntity");
      TestEntityComponent neighbourRoot = new TestEntityComponent("TestEntity_1_Component_2");
      anotherTestEntity.setEnitiyComponentWithoutCascade(neighbourRoot);

      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      repository.add(work, anotherTestEntity);
      work.finish();
   }
   
   @Override
   public void testUpdate_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testUpdate_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testDelete_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById_ForEagerLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById_ForLazyLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindAll_ForResultCount() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindByQuery_ForComponentAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindByQuery_ForDirectAttributes() {
   // TODO Auto-generated method stub

   }

   // Note these test just checks if GenericRepository checks assertions.

   @Test(expected = AssertionException.class)
   public void testAdd_ForAssertion() {
      repository.add( fixture.getUnstartedWork(), root);
   }

   private <D> D getColumnValue( Class<D> dataType, String columnName) {
      return databaseSpy.retrieveColumnFromRow( "T_TEST_ENTITY", root.getId(), dataType, columnName );
   }

   // @Ignore
   // @Test
   // public void cascade() {
   // TestEntityComponent sameEntityComponent =
   // testRepository.findTestEntityComponentByName("TestComponent2");
   // testEntity.setEnitiyComponentWithoutCascade(sameEntityComponent);
   // boolean wasException;
   //      
   // wasException = false;
   // try{
   // testRepository.update(testEntity);
   // } catch (HibernateException he) {
   // wasException = true;
   // }
   // assertFalse("",wasException);
   //
   //      
   // testEntity.setEnitiyComponentWithoutCascade(null);
   // testRepository.update(testEntity);
   //      
   // testEntity.addComponent(sameEntityComponent);
   //      
   // wasException = false;
   // try{
   // testRepository.update(testEntity);
   // } catch (HibernateException he) {
   // wasException = true;
   // }
   // assertTrue("",wasException);
   //      
   // testEntity.getComponents().remove(sameEntityComponent);
   // testRepository.update(testEntity);
   // }

   /**************************************************************************************************************************************************
    * 
    * @Test public void find() { assertNotNull("We can find the previously stored entity.", repository.findTestEntityByName( work, "TestEntity")); }
    * 
    * @Test public void find_InCycle() { UnitOfWork work = new UnitOfWork(true); for (int i = 0; i < 10; i++) assertNotNull("We can find the
    *       previously stored entity.", repository.findTestEntityByName( work, "TestEntity")); work.finish(); }
    * 
    * @Ignore
    * @Test public void findByName_AndUpdate_InCycle() { UnitOfWork work = new UnitOfWork(true);
    * 
    * for (int i = 0; i < 10; i++) { String suffix = (i == 0) ? "" : (new Integer(i - 1)).toString(); TestEntity testEntity =
    * repository.findTestEntityByName( work, "TestEntity" + suffix); testEntity.setTextAttribute("TestEntity" + i); repository.update( work,
    * testEntity ); assertNotNull("We can find the previously stored entity.", repository.findTestEntityByName( work, "TestEntity" + i )); }
    * work.finish(); }
    * 
    * @Ignore
    * @Test public void findById_AndUpdate_InCycle() { TestEntity testEntity = null; Integer id = testEntity.getId(); UnitOfWork work = new
    *       UnitOfWork(true);
    * 
    * for (int i = 0; i < 10; i++) { repository = (TestEntityRepository) ProcessPuzzleContext.getInstance().getRepository(TestEntityRepository.class);
    * testEntity = repository.findById(work, id); String suffix = (i == 0) ? "" : (new Integer(i - 1)).toString(); assertEquals("The entity's name
    * should be the previously given name.", "TestEntity" + suffix, testEntity.getName()); testEntity.setTextAttribute("TestEntity" + i);
    * repository.update(work, testEntity); } work.finish(); }
    * 
    * @Test public void findById_ForAddedComponents_InCycle() { TestEntity testEntity = null; Integer id = testEntity.getId(); int db = 10; UnitOfWork
    *       work = new UnitOfWork(true);
    * 
    * for (int i = 1; i <= db; i++) { testEntity = repository.findById(work, id); TestEntityComponent testEntityComponent = new
    * TestEntityComponent("TestComponent" + i); testEntity.getComponents().add(testEntityComponent); repository.update(work, testEntity); }
    * work.finish(); assertEquals("The number of components is:", 1 + db, testEntity.getComponents().size()); }
    * 
    * @Test public void forModificationAComponent() { TestEntity testEntity = null; Integer id = testEntity.getId(); TestEntityComponent
    *       testComponent; UnitOfWork work = new UnitOfWork(true);
    * 
    * for (int i = 0; i < 100; i++) { repository = (TestEntityRepository)
    * ProcessPuzzleContext.getInstance().getRepository(TestEntityRepository.class); testEntity = repository.findById(work, id); testComponent =
    * testEntity.getFirstComponent(); String suffix = (i == 0) ? "" : (new Integer(i - 1)).toString(); assertEquals("The components's name should be
    * the previously given name.", "TestComponent" + suffix, testComponent.getName()); testComponent.setName("TestComponent" + i);
    * repository.update(work, testEntity); } work.finish(); }
    */
}