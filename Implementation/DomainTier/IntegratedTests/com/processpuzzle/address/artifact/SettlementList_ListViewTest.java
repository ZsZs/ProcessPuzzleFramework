package com.processpuzzle.address.artifact;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.address.artifact.SettlementList_ListView;

public class SettlementList_ListViewTest {
	private SettlementListTestFixture settlementListFixture = null;
	private SettlementList_ListView listView = null;

	@Before
    public void setUp() throws Exception {
		settlementListFixture = SettlementListTestFixture.getInstance();
		settlementListFixture.setUp();
		listView = (SettlementList_ListView) settlementListFixture.getSettlementList().getListView();
	}

    @After
	public void tearDown() throws Exception {
		settlementListFixture.tearDown();
		listView = null;
	}

    @Ignore
    @Test
	public void testGetData() {
//		assertNotNull(listView.getData(null, null));
//		System.out.println(listView.getData(null, null));
	}
}
