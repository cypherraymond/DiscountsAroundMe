package com.tl.discountsaroundme;


import android.location.LocationManager;

import com.tl.discountsaroundme.Controllers.CheckController;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TestCheckController {
    @Test
    public void testCheckControllerWithNoGps() {
        CheckController cc = new CheckController();
        LocationManager locationManager = Mockito.mock(LocationManager.class);
        when(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)).thenReturn(false);
        when(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)).thenReturn(true);
        assertEquals(false, cc.areGPSandNetworkEnabled(locationManager));
    }

    @Test
    public void testCheckControllerWithNoNetwork() {
        CheckController cc = new CheckController();
        LocationManager locationManager = Mockito.mock(LocationManager.class);
        when(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)).thenReturn(true);
        when(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)).thenReturn(false);
        assertEquals(false, cc.areGPSandNetworkEnabled(locationManager));
    }

    @Test
    public void testCheckControllerWithAll() {
        CheckController cc = new CheckController();
        LocationManager locationManager = Mockito.mock(LocationManager.class);
        when(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)).thenReturn(true);
        when(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)).thenReturn(true);
        assertEquals(true, cc.areGPSandNetworkEnabled(locationManager));
    }

    @Test
    public void testCheckControllerWithNothing() {
        CheckController cc = new CheckController();
        LocationManager locationManager = Mockito.mock(LocationManager.class);
        when(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)).thenReturn(false);
        when(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)).thenReturn(false);
        assertEquals(false, cc.areGPSandNetworkEnabled(locationManager));
    }
}
