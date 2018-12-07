package es.uniovi.interactive_cubes.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import es.uniovi.interactive_cubes.R;
import es.uniovi.interactive_cubes.logic.Game;


public class QRFragment extends Fragment {

    private View view;
    private CodeScanner mCodeScanner;

    private static String index;

    private final int REQUEST_ACCESS_FINE =0;


    public QRFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();
        view = inflater.inflate(R.layout.fragment_qr, container, false);
        CodeScannerView scannerView = view.findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(activity, scannerView);

        mCodeScanner.startPreview();

        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if( Game.getInstance().checkCube(result.getText(),index)){
                           Game.getInstance().addCombination(result.getText());
                         }else {
                            Toast.makeText(activity,"Cubo incorrecto", Toast.LENGTH_SHORT).show();
                        }

                            FragmentManager fm = getFragmentManager();
                            fm.beginTransaction().replace(R.id.escenario, new GameFragment()).commit();

                    }
                });
            }
        });


        if(!haveCameraPermissons())
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, REQUEST_ACCESS_FINE);

        return view;
    }


    private boolean haveCameraPermissons(){

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ) {
            return false;
        }
        return true;
    }

    public static void setIndex(int index1){
        index = ""+index1;
    }

}
