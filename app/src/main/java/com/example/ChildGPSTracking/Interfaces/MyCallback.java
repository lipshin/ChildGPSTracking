package com.example.ChildGPSTracking.Interfaces;

import com.example.ChildGPSTracking.Models.Person;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

public interface MyCallback {
    //this is for callbacks
    public void onCallback(Person person);

}
