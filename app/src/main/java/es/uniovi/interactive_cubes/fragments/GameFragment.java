package es.uniovi.interactive_cubes.fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.uniovi.interactive_cubes.logic.Entities.User;
import es.uniovi.interactive_cubes.logic.Game;
import es.uniovi.interactive_cubes.R;



public class GameFragment extends Fragment {

    private View view;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    private final int REQUEST_ACCESS_FINE =0;

    public GameFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_game, container, false);

        final TextView txLevel = (TextView) view.findViewById(R.id.txLevel);
        txLevel.setText("Nivel: "+Game.getInstance().getLevel());

        txLevel.setTextColor(getResources().getColor(android.R.color.black));

        setFunctionality();
        drawCubePanel();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        if(!haveCameraPermissons())
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, REQUEST_ACCESS_FINE);

        return view;

    }

    /**
     * Asigna funcionalidad a los botones del fragment
     */
    private void setFunctionality() {

        Button addLevelButton = (Button) view.findViewById(R.id.btnAddLevel);
        Button subsLevelButton = (Button) view.findViewById(R.id.btnSubsLevel);

        Button verifyButton = (Button) view.findViewById(R.id.btnVerify);

        final TextView txLevel = (TextView) view.findViewById(R.id.txLevel);

        addLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txLevel.setText("Nivel: "+Game.getInstance().incrementLevel());
                drawCubePanel();
            }
        });

        subsLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game.getInstance().removeActualComn();
                txLevel.setText("Nivel: "+Game.getInstance().decrementLevel());
                drawCubePanel();
            }
        });

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String finalComb = "";
               for(String s:Game.getInstance().getActualCombs()){
                   finalComb+=s;
               }

               if(Game.getInstance().checkCombination(finalComb) != null){
                   addValidComb();
                   FragmentManager fm = getFragmentManager();
                   fm.beginTransaction().replace(R.id.escenario, new CombFragment()).commit();
               }else{

                   final Dialog info = new Dialog(getActivity());
                   info.setContentView(R.layout.combinacion_incorrecta);
                   info.show();

                   Button btn = info.findViewById(R.id.btnTryAgain);
                   btn.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                            info.dismiss();
                       }
                   });


                   info.setOnDismissListener(new DialogInterface.OnDismissListener() {
                       @Override
                       public void onDismiss(DialogInterface dialogInterface) {
                           Game.getInstance().playAgain();
                           drawCubePanel();
                       }
                   });

               }

            }
        });


    }

    public void drawCubePanel(){
        //Obtenemos el linear layout del scroll
        LinearLayout lScroll = (LinearLayout) view.findViewById(R.id.downLinear);
        lScroll.removeAllViews();

        //Propiedades para los botones
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        lp.setMargins(0, 10, 0, 0);

        //Creaación de los botones
        for (int i = 1; i < Game.getInstance().getLevel()+2; i++) {

            Button verifyButton = (Button) view.findViewById(R.id.btnVerify);
            verifyButton.setVisibility(View.VISIBLE);

            Button button = new Button(view.getContext());
            button.setId(i);
            //Asignamos propiedades de layout al boton
            button.setLayoutParams(lp);
            //Asignamos Texto al botón
            button.setText("Cubo "+Game.getInstance().getCubeName(""+i));
            //Alineación
            button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            //Aumentamos el tamaño de la letra
            button.setTextSize(20);

            //Asignamos los listener
            button.setOnClickListener(new GameFragment.ButtonsOnClickListener(i));

            if( i-1<Game.getInstance().getActualCombsSize() && Game.getInstance().checkCube(Game.getInstance().getActualComb(i-1),""+i)){
                button.setEnabled(false);
            }else {
                verifyButton.setVisibility(View.INVISIBLE);


                button.setPadding(60,60,60,60);

                switch (i) {


                    case 1:
                        button.setBackgroundColor(getResources().getColor(R.color.verde));
                        break;
                    case 2:
                        button.setBackgroundColor(getResources().getColor(R.color.naranja));
                        break;
                    case 3:
                        button.setBackgroundColor(getResources().getColor(R.color.rosa));
                        break;
                    case 4:
                        button.setBackgroundColor(getResources().getColor(R.color.azul));
                        break;
                    case 5:
                        button.setBackgroundColor(getResources().getColor(R.color.amarillo));
                        break;


                }
            }


            lScroll.addView(button);

        }


    }

    private boolean haveCameraPermissons(){

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ) {
            return false;
        }
        return true;
    }


    class ButtonsOnClickListener  implements View.OnClickListener {

        private int index;

        public ButtonsOnClickListener(int index){
            this.index = index;
        }

        @Override
        public void onClick(View v) {

           FragmentManager fm = getFragmentManager();
           fm.beginTransaction().replace(R.id.escenario, new QRFragment()).commit();
           QRFragment.setIndex(this.index);
        }
    }

    private void addValidComb(){

       int num = Integer.parseInt(Game.getInstance().getUser().getGoodCombinations());


        mDatabase.child("users").child(Game.getInstance().getUser().getEmail()).child("goodCombinations").setValue(""+num+1);

    }

}
