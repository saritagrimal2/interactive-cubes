package es.uniovi.interactive_cubes.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import es.uniovi.interactive_cubes.R;
import es.uniovi.interactive_cubes.logic.Game;


public class CombFragment extends Fragment {

    private View view;

    public CombFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_game, container, false);

        return view;
    }

    public StringBuilder getInfo() {

        int rawID = view.getContext().getResources().getIdentifier("Obra_" + Game.getInstance().getInfoName(), "raw", view.getContext().getPackageName());


        BufferedReader bR = new BufferedReader(new InputStreamReader(view.getResources().openRawResource(rawID)));
        StringBuilder sB = new StringBuilder();
        String linea = null;

        try {
            while ((linea = bR.readLine()) != null) {
                sB.append(linea).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sB;
    }


}
