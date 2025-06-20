package com.example.cheas_stoeckli.data.repositories

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NewsRepository {

    private val db = Firebase.firestore
    private val collection = db.collection("announcements")




}