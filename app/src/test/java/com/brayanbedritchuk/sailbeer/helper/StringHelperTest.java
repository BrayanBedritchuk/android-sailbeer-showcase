package com.brayanbedritchuk.sailbeer.helper;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class StringHelperTest {

    @Mock
    Context mMockContext;

    @Before
    public void initComponents() {

    }

    @Test
    public void isEmpty() {
        assertTrue(StringHelper.isEmpty(null));
        assertTrue(StringHelper.isEmpty("    "));
        assertFalse(StringHelper.isEmpty("a"));
    }

    @Test
    public void hasContent() {
        assertFalse(StringHelper.hasContent(null));
        assertFalse(StringHelper.hasContent("    "));
        assertTrue(StringHelper.hasContent("a"));
    }

}
