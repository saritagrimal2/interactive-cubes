package es.uniovi.interactive_cubes.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.uniovi.interactive_cubes.R;
import es.uniovi.interactive_cubes.logic.Entities.User;


public class StadisticsFragment extends Fragment {

    private View view;

    private DatabaseReference mDatabase;

    public StadisticsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_stadistics, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User value = dataSnapshot.child("users").child(FirebaseAuth.getInstance().getUid()).getValue(User.class);
                TextView textView = view.findViewById(R.id.textView2);
                textView.setText("Combinaciones correctas: "+value.getGoodCombinations());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("CANCEL", "Failed to read value.", error.toException());
            }
        });

        auxilio();

        return view;
    }

    private void auxilio(){
        mDatabase.child("users").child(FirebaseAuth.getInstance().getUid()).child("aux").setValue(0);
    }



}
