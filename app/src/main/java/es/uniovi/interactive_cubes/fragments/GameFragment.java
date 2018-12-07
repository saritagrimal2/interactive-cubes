package es.uniovi.interactive_cubes.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import es.uniovi.interactive_cubes.logic.Game;
import es.uniovi.interactive_cubes.R;



public class GameFragment extends Fragment {

    private View view;

    private boolean canVerify = false;

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
                   Toast.makeText(getContext(),"Combinacion correcta", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(getContext(),"Combinacion incorrecta", Toast.LENGTH_SHORT).show();
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
            button.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            //Aumentamos el tamaño de la letra
            button.setTextSize(20);

            //Asignamos los listener
            button.setOnClickListener(new GameFragment.ButtonsOnClickListener(i));

            if( i-1<Game.getInstance().getActualCombsSize() && Game.getInstance().checkCube(Game.getInstance().getActualComb(i-1),""+i)){
                button.setEnabled(false);
            }else{
                verifyButton.setVisibility(View.INVISIBLE);
            }

            lScroll.addView(button);

        }

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

}
