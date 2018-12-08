package es.uniovi.interactive_cubes.fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import es.uniovi.interactive_cubes.R;
import es.uniovi.interactive_cubes.logic.Game;


public class CombFragment extends Fragment {

    private View view;

    private MediaPlayer mp;

    public CombFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.combinacion_correcta, container, false);

        int rawID = view.getResources().getIdentifier("audio"+Game.getInstance().getInfoName(),"raw",view.getContext().getPackageName());
        mp = android.media.MediaPlayer.create(view.getContext(),rawID);


        setInfo();
        setImage();
        asignFuncionality();

        return view;
    }

    public void setInfo() {

        int rawID = view.getContext().getResources().getIdentifier("obra_" + Game.getInstance().getInfoName(), "raw", view.getContext().getPackageName());


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


        TextView name = view.findViewById(R.id.txNameCom);
        name.setText(sB.toString().split(";")[0]);

        TextView txInfo = view.findViewById(R.id.txtInfo);
        txInfo.setText(sB.toString().split(";")[1]);


    }

    private void play(){

        if(mp.isPlaying()){
            mp.stop();
            try {
                mp.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {


            int rawID = view.getResources().getIdentifier("audio"+Game.getInstance().getInfoName(),"raw",view.getContext().getPackageName());
            mp = android.media.MediaPlayer.create(view.getContext(),rawID);


            mp.start();

        }



    }

    private void setImage(){

        ImageView img = view.findViewById(R.id.imgAutor);
        int imgId = view.getResources().getIdentifier("autor_"+Game.getInstance().getInfoName(),"drawable",view.getContext().getPackageName());

        img.setBackgroundResource(imgId);

    }


    private void asignFuncionality(){

        Button btnPlay = view.findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           play();
                                       }
                                   }
        );


    }


}
