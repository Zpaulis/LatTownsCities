package com.example.lattownscities

import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

const val TAG = "Firestore*************"
fun loadTowns(myCallback: MainActivity.MyCallback) {
// Access a Cloud Firestore instance from your Activity
    val db = Firebase.firestore
    Log.d(TAG, "FIRESTORE INITIALIZED")

    db.collection("towns")
        .orderBy("name", Query.Direction.ASCENDING)
        .get()
        .addOnSuccessListener { result ->
            townData = mutableListOf<TownData>()
            for (document in result) {
                Log.d(TAG, "${document.id} => ${document.data}")
                val town = document.toObject<TownData>(TownData::class.java)
                townData.add(town)
                Log.d(TAG, "$town")
            }
            Log.d(TAG, townData.size.toString())
            myCallback.onCallback(townData)
        }

        .addOnFailureListener { exception ->
            Log.d(TAG, "Kautkas slikts", exception)
        }
}