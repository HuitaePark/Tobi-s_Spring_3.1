package com.dasom.tobi.Junit;

import com.dasom.tobi.dao.DaoFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringJUnitConfig(DaoFactory.class)
public class JunitTest {
    @Autowired
    ApplicationContext applicationContext;

    static Set<JunitTest> testObject = new HashSet<JunitTest>();
    static ApplicationContext contextObject = null;


    @Test
    public void test1() {
        assertThat(testObject).isNotSameAs(hasItem(this));
        testObject.add(this);
        assertThat(contextObject == null || contextObject == this.applicationContext).isEqualTo(true);
        contextObject = this.applicationContext;
    }
    @Test
    public void test2() {
        assertThat(testObject).isNotSameAs(hasItem(this));
        testObject.add(this);

        assertTrue(contextObject == null || contextObject == this.applicationContext);
        contextObject = this.applicationContext;
    }
    @Test
    public void test3() {
        assertThat(testObject).isNotSameAs(hasItem(this));
        testObject.add(this);

        assertThat(contextObject, either(is(nullValue())).or(is(applicationContext)));
        contextObject = this.applicationContext;
    }
}