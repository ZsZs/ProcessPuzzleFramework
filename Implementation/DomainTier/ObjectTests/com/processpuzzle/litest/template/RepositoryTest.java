package com.processpuzzle.litest.template;

public interface RepositoryTest {
   public void testAdd_ForOwnedAttributesAndComponents() throws Exception;
   public void testAdd_ForReferencedAggregateRoots() throws Exception;
   public void testUpdate_ForOwnedAttributesAndComponents() throws Exception;
   public void testUpdate_ForReferencedAggregateRoots() throws Exception;
   public void testDelete_ForOwnedAttributesAndComponents() throws Exception;
   public void testFindById() throws Exception;
   public void testFindById_ForEagerLoadedComponents() throws Exception;
   public void testFindById_ForLazyLoadedComponents() throws Exception;
   public void testFindAll_ForResultCount() throws Exception;
   public void testFindByQuery_ForComponentAttributes() throws Exception;
   public void testFindByQuery_ForDirectAttributes() throws Exception;
}
