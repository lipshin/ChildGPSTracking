package com.example.ChildGPSTracking.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ChildGPSTracking.Interfaces.MyCallback;
import com.example.ChildGPSTracking.Models.Person;
import com.example.ChildGPSTracking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSignup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSignup extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseAuth mAuth;

    private String userId;

    public FragmentSignup() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSignup.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSignup newInstance(String param1, String param2) {
        FragmentSignup fragment = new FragmentSignup();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        mAuth = FirebaseAuth.getInstance();

        Button btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regFunc(view);
            }
        });


        return view;
    }

    public void regFunc(View view) {

        String email = ((EditText)view.findViewById(R.id.signupEmail)).getText().toString();
        String password = ((EditText)view.findViewById(R.id.signupPassword)).getText().toString();
        String name = ((EditText)view.findViewById(R.id.signupName)).getText().toString();
        String id = ((EditText)view.findViewById(R.id.signupID)).getText().toString();
        Boolean isAdult = ((CheckBox)view.findViewById(R.id.checkBox)).isChecked();
        Person person = new Person(name,id,email,isAdult);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getActivity(), "reg ok", Toast.LENGTH_LONG).show();
                            userId = mAuth.getCurrentUser().getUid();
                            addData(person, view);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getActivity(), "reg failed", Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });

    }

    public void addData(Person person, View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        myRef.child(userId).setValue(person, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                readData(new MyCallback() {
                    @Override
                    public void onCallback(Person person) {
                        if (person.isAdult()) {
                            Navigation.findNavController(view).navigate(R.id.action_signup_to_location);
                        } else {
                            Navigation.findNavController(view).navigate(R.id.action_signup_to_childLocation);
                        }
                    }
                });
            }
        });

    }

    public void readData(MyCallback myCallback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(userId);
        myRef.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Person value = dataSnapshot.getValue(Person.class);
                myCallback.onCallback(value);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {}
        });
    }
}