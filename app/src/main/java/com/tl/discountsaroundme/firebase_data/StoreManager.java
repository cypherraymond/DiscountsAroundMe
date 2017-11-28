package com.tl.discountsaroundme.firebase_data;


import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tl.discountsaroundme.entities.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreManager {
    private static ArrayList<Store> storeArrayList = new ArrayList<>();

    public StoreManager() {
        DatabaseReference databaseStores = FirebaseDatabase.getInstance().getReference("shops");
        databaseStores.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot storesSnapshot : dataSnapshot.getChildren()) {
                    try {
                        Store store = storesSnapshot.getValue(Store.class);
                        storeArrayList.add(store);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Gets a String Array filled with Store names
     *
     * @return storeStrings
     */
    public List<String> getStoreStrings() {
        List<String> storeStrings = new ArrayList<>();
        for (Store store : storeArrayList)
            storeStrings.add(store.getName());
        return storeStrings;
    }

    public Store getStoreFromName(String name) {
        for (Store store : storeArrayList) {
            if (store.getName().equals(name))
                return store;
        }
        return null;
    }

    /**
     * Gets all the stores that are within a radius of maxDistance
     *
     * @param lat         user's current latitude
     * @param lng         user's current longitude
     * @param maxDistance max distance between user and any store
     * @return all stores that are within the maxDistance
     */
    public ArrayList<Store> getNearbyStores(double lat, double lng, double maxDistance) {
        ArrayList<Store> nearbyStores = new ArrayList<>();

        for (Store store : storeArrayList) {
            LatLng latLng = new LatLng(store.getLat(), store.getLng());
            double distance = measure(latLng.latitude, latLng.longitude, lat, lng);
            if (distance <= maxDistance)
                nearbyStores.add(store);
        }
        return nearbyStores;
    }

    public ArrayList<Store> getStores() {
        return storeArrayList;
    }

    /**
     * Measures the distance between 2 locations in meters
     *
     * @param lat1 first location latitude
     * @param lng1 first location longitude
     * @param lat2 second location latitude
     * @param lng2 second location longitude
     * @return the distance in meters
     */
    public double measure(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (float) (earthRadius * c);
    }


}
