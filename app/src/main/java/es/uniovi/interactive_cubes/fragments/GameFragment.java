package es.uniovi.interactive_cubes.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import es.uniovi.interactive_cubes.logic.Game;
import es.uniovi.interactive_cubes.R;



public class GameFragment extends Fragment {

    private View view;
    private Game game;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_game, container, false);

        game = Game.getInstance();

        final TextView txLevel = (TextView) view.findViewById(R.id.txLevel);
        txLevel.setText("Nivel: "+game.getLevel());

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

        final TextView txLevel = (TextView) view.findViewById(R.id.txLevel);

        addLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txLevel.setText("Nivel: "+game.incrementLevel());
                drawCubePanel();
            }
        });

        subsLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txLevel.setText("Nivel: "+game.decrementLevel());
                drawCubePanel();
            }
        });


    }

    private void drawCubePanel(){
        //Obtenemos el linear layout del scroll
        LinearLayout lScroll = (LinearLayout) view.findViewById(R.id.downLinear);
        lScroll.removeAllViews();

        //Propiedades para los botones
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);



        //Creaación de los botones
        for (int i = 1; i < game.getLevel()+2; i++) {

            Button button = new Button(view.getContext());
            button.setId(i);
            //Asignamos propiedades de layout al boton
            button.setLayoutParams(lp);
            //Asignamos Texto al botón
            button.setText("Cubo "+i);
            //Alineación
            button.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            //Aumentamos el tamaño de la letra
            button.setTextSize(20);

            //Asignamos los listener
            button.setOnClickListener(new GameFragment.ButtonsOnClickListener());

            lScroll.addView(button);

        }

    }

    class ButtonsOnClickListener  implements View.OnClickListener {


        @Override
        public void onClick(View v) {

           FragmentManager fm = getFragmentManager();
           fm.beginTransaction().replace(R.id.escenario, new QRFragment()).commit();
        }
    }



}
