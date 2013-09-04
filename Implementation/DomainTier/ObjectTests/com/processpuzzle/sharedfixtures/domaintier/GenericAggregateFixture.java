package com.processpuzzle.sharedfixtures.domaintier;

import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.litest.fixture.GenericTestFixture;
import com.processpuzzle.litest.fixture.PersistentFreshFixture;


public abstract class GenericAggregateFixture<A extends AggregateRoot> extends GenericTestFixture<A> implements PersistentFreshFixture<A> {

}
