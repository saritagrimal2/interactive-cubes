package es.uniovi.interactive_cubes.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User value = dataSnapshot.child("users").child("1").getValue(User.class);
                TextView textView = view.findViewById(R.id.textView2);
                textView.setText(value.toString());
            //   Log.d("CHANGE", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("CANCEL", "Failed to read value.", error.toException());
            }
        });


        Button button = view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewUser("1","Pablo","paaa@es.com","5");
            }
        });


        return view;
    }

    private void writeNewUser(String userId, String name, String email,String goodCombinations) {
        User user = new User(name, email,goodCombinations);
        mDatabase.child("users").child(userId).setValue(user);

    }



}
