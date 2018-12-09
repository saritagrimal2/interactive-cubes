package es.uniovi.interactive_cubes.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import es.uniovi.interactive_cubes.R;
import es.uniovi.interactive_cubes.logic.Entities.User;
import es.uniovi.interactive_cubes.logic.Game;


public class RankingFragment extends Fragment {

    private View view;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    private Map<String,Integer> data = new HashMap<>();
    List<String> rank = new ArrayList<>();

    public RankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ranking, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.child("users").child(FirebaseAuth.getInstance().getUid()).child("aux").setValue(0);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String valor = dataSnapshot.child("users").getValue().toString();

                JSONObject jsonObject = null;

                    try {
                        jsonObject = new JSONObject(valor);

                        Iterator<String> keys = jsonObject.keys();

                        while (keys.hasNext()){
                            String str_Name=keys.next();
                            JSONObject uid = jsonObject.getJSONObject(str_Name);

                            Log.i("KEY", uid.getString("email"));
                            Log.i("VALUE", uid.getString("goodCombinations"));

                            data.put(uid.getString("email"),Integer.parseInt(uid.getString("goodCombinations")));

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    draw();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("CANCEL", "Failed to read value.", error.toException());
            }
        });


            return view;
    }

    private  void draw(){
        LinearLayout lScroll = (LinearLayout) view.findViewById(R.id.linearRank);
        lScroll.removeAllViews();

        //Propiedades para los botones
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        lp.setMargins(0, 10, 0, 0);

        int cont=99;

        Map<String, Integer> sortedMap = sortByValue(data);

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {

            Button button = new Button(view.getContext());
            button.setId(cont++);
            //Asignamos propiedades de layout al boton
            button.setLayoutParams(lp);
            //Asignamos Texto al botón
            button.setText(entry.getKey()+": "+entry.getValue());
            //Alineación
            button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            //Aumentamos el tamaño de la letra
            button.setTextSize(16);
            button.setTextColor(getResources().getColor(R.color.negro));


            button.setBackgroundColor(getResources().getColor(R.color.blanco));
            button.setEnabled(false);

            lScroll.addView(button);
        }
    }

    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();

        Collections.reverse(list);

        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }


        return sortedMap;
    }

}
