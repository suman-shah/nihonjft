package com.japan.nihonjft;

import android.util.ArrayMap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.japan.nihonjft.ui.category.CategoryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DbQuery {
    public static FirebaseFirestore g_firestore;
    public static List<CategoryModel> g_catList = new ArrayList<>();
    public static int g_selected_cat_index = 0;
    public static List<TestModel> g_testList = new ArrayList<>();

    public static void createUserData(String email, String name, MyCompleteListener completeness) {
        Map<String, Object> userData = new ArrayMap<>();
        userData.put("EMAIL_ID", email);
        userData.put("NAME", name);
        userData.put("TOTAL_SCORE", 0);

        DocumentReference userDoc = g_firestore.collection("USERS").document(FirebaseAuth.getInstance().getCurrentUser().getUid());

        WriteBatch batch = g_firestore.batch();
        batch.set(userDoc, userData);

        DocumentReference countDoc = g_firestore.collection("USERS").document("TOTAL_USERS");
        batch.update(countDoc, "COUNT", FieldValue.increment(1));
        batch.commit()
                .addOnSuccessListener(unused -> completeness.onSuccess())
                .addOnFailureListener(e -> completeness.onFailure());
    }

    public static void loadCategories(MyCompleteListener completeListener) {
        g_catList.clear();

        g_firestore.collection("QUIZ").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    Log.d("DbQuery", "Documents fetched: " + queryDocumentSnapshots.size());

                    Map<String, QueryDocumentSnapshot> docList = new ArrayMap<>();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        docList.put(doc.getId(), doc);
                        Log.d("DbQuery", "Fetched Document ID: " + doc.getId());
                    }

                    QueryDocumentSnapshot catlistDoc = docList.get("Categories");
                    if (catlistDoc == null) {
                        Log.e("DbQuery", "Categories document not found!");
                        completeListener.onFailure();
                        return;
                    }

                    long catCount = catlistDoc.getLong("COUNT");
                    Log.d("DbQuery", "Total Categories: " + catCount);

                    for (int i = 1; i <= catCount; i++) {
                        String catID = catlistDoc.getString("CAT" + i + "_ID");
                        if (catID == null) {
                            Log.e("DbQuery", "Category ID for CAT" + i + " is null.");
                            continue;
                        }

                        QueryDocumentSnapshot catDoc = docList.get(catID);
                        if (catDoc == null) {
                            Log.e("DbQuery", "Category document for ID " + catID + " not found.");
                            continue;
                        }

                        String catName = catDoc.getString("NAME");
                        Long noOfTestLong = catDoc.getLong("NO_OF_TEST");

                        if (catName != null && noOfTestLong != null) {
                            int noOfTest = noOfTestLong.intValue();
                            g_catList.add(new CategoryModel(catID, catName, noOfTest));
                            Log.d("DbQuery", "Added Category: " + catName + " with " + noOfTest + " tests.");
                        } else {
                            Log.e("DbQuery", "Missing data for Category ID: " + catID);
                        }
                    }

                    completeListener.onSuccess();
                })
                .addOnFailureListener(e -> {
                    Log.e("DbQuery", "Error fetching QUIZ data", e);
                    completeListener.onFailure();
                });
    }

    public static void loadTestData(final MyCompleteListener completeListener) {
        g_testList.clear();
        g_firestore.collection("QUIZ").document(g_catList.get(g_selected_cat_index).getDocID())
                .collection("TEST_LIST").document("TESTS_INFO")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        int noOfTests = g_catList.get(g_selected_cat_index).getNoOfTests();
                        for (int i = 1; i <= noOfTests; i++) {
                            g_testList.add(new TestModel(
                                    documentSnapshot.getString("TEST" + String.valueOf(i) + "_ID"),
                                    0,
                                    documentSnapshot.getLong("TEST" + String.valueOf(i) + "_TIME").intValue()
                            ));
                        }

                        completeListener.onSuccess();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                        completeListener.onFailure();

                    }
                });





    }






}
