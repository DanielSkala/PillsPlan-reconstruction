package com.pillsplan.revolware.pillsplan_reconstruction.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.pillsplan.revolware.pillsplan_reconstruction.R;

//

public class MainActivity extends ActionBarActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        Log.d("DEBUG:", "onCreate running_5");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Log.d("DEBUG:", "onCreate running_6");

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        Log.d("DEBUG:", "onCreate running_7");
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        Log.d("DEBUG:", "onCreate running_8");
        drawerFragment.setDrawerListener(this);
        Log.d("DEBUG:", "onCreate running_9");
        // display the first navigation drawer view on app launch
        displayView(0);
        Log.d("DEBUG:", "onCreate running_10");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        /*if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }*/


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }
    //lave menu linky
    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0: //som umiestnil mimo obrazovky
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new HistoryFragment();
                title = getString(R.string.title_history);
                break;
            case 2:
                fragment = new PharmacySearchFragment();
                title = getString(R.string.title_pharmacySearch);
                break;
            case 3:
                fragment = new TutorialFragment();
                title = getString(R.string.title_tutorial);
                break;
            case 4:
                fragment = new SettingsFragment();
                title = getString(R.string.title_settings);
                break;
            case 5:
                fragment = new AboutFragment();
                title = getString(R.string.title_about);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

}


