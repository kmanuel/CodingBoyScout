package com.klowdscy;

import com.klowdscy.testcategories.IntegrationTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CodingBoyScoutApplication.class})
@WebAppConfiguration
@Category(IntegrationTest.class)
public class CodingBoyScoutApplicationIntTest {

	@Autowired
	private WebApplicationContext wac;

	@Test
	public void contextLoads() {
		assertThat(
				wac,
				is(notNullValue()));
	}

}
