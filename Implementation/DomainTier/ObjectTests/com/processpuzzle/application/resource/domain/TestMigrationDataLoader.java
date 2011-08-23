/*
 * Created on Feb 13, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.resource.domain;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.resource.domain.HardCodedDataLoader;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.domain.TestEntityRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class TestMigrationDataLoader extends HardCodedDataLoader {

   public void loadData() {
      super.loadData();
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      TestEntityRepository repository = (TestEntityRepository) applicationContext.getRepository( TestEntityRepository.class );
      TestEntity anEntity = new TestEntity("test_entity_2");

      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      repository.add(work, anEntity);
      work.finish();
   }
}
