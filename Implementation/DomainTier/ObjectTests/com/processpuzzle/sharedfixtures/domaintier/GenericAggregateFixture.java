package com.processpuzzle.sharedfixtures.domaintier;

import hu.itkodex.commons.persistence.AggregateRoot;
import hu.itkodex.litest.fixture.GenericTestFixture;
import hu.itkodex.litest.fixture.PersistentFreshFixture;

public abstract class GenericAggregateFixture<A extends AggregateRoot> extends GenericTestFixture<A> implements PersistentFreshFixture<A> {

}
