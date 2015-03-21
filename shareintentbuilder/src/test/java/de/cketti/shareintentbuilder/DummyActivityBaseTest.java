package de.cketti.shareintentbuilder;


import android.app.Activity;
import android.content.ComponentName;

import org.junit.Before;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class DummyActivityBaseTest {
    protected static final String DEMO_PACKAGE_NAME = "com.example.demo";
    protected static final ComponentName DEMO_COMPONENT_NAME = new ComponentName(DEMO_PACKAGE_NAME, "DummyActivity");

    protected Activity activity;

    @Before
    public void createActivity() {
        activity = mock(Activity.class);
        when(activity.getPackageName()).thenReturn(DEMO_PACKAGE_NAME);
        when(activity.getComponentName()).thenReturn(DEMO_COMPONENT_NAME);
    }
}
