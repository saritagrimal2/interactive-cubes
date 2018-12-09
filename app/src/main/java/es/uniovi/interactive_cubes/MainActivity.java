package es.uniovi.interactive_cubes;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import es.uniovi.interactive_cubes.fragments.GameFragment;
import es.uniovi.interactive_cubes.fragments.StadisticsFragment;
import es.uniovi.interactive_cubes.logic.Game;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Game game;

    private Intent galIntent,cropIntent;

    private final int REQUEST_ACCESS_FINE =0;
    private static int RESULT_LOAD_IMAGE = 1;
    private static int RESULT_CROP_IMAGE = 2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.escenario, new GameFragment()).commit();

        game = Game.getInstance();


        View headView = navigationView.getHeaderView(0);

        TextView name = (TextView) headView.findViewById(R.id.textName);

        name.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        name.setTextSize(20);
        name.setTextColor(getResources().getColor(R.color.blanco));


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menubtn) {

            final Dialog help = new Dialog(this);
            help.setContentView(R.layout.help);
            help.show();

            Button btnAccept = help.findViewById(R.id.btnAcept);
            btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    help.dismiss();
                }
            });


        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.game) {

            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.escenario, new GameFragment()).commit();

        } else if (id == R.id.stadistics) {

            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.escenario, new StadisticsFragment()).commit();

        } else if (id == R.id.ranking) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();

            cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(selectedImage,"image/*");
            cropIntent.putExtra("crop",true);
            cropIntent.putExtra("outputX",180);
            cropIntent.putExtra("outputY",180);
            cropIntent.putExtra("aspectX",3);
            cropIntent.putExtra("aspectY",4);
            cropIntent.putExtra("scaleUpIfneeded",false);
            cropIntent.putExtra("return-data",true);


            startActivityForResult(cropIntent,RESULT_CROP_IMAGE);
        }

        if (requestCode == 0 ) {

            Bundle bundle = data.getExtras();

            Bitmap bitmap = bundle.getParcelable("data");

          //  ImageView imageView = (ImageView) findViewById(R.id.imageView);
         //   imageView.setImageBitmap(bitmap);

        }

    }

}
