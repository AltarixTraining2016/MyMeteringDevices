package me.ilich.mymeteringdevices.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ilich.mymeteringdevices.R;
import me.ilich.mymeteringdevices.ui.about.AboutFragment;
import me.ilich.mymeteringdevices.ui.devices.DevicesListFragment;
import me.ilich.mymeteringdevices.ui.meterings.AllMeteringFragment;
import me.ilich.mymeteringdevices.ui.meterings.EditMeteringFragment;
import me.ilich.mymeteringdevices.ui.summary.SummaryFragment;
import me.ilich.mymeteringdevices.ui.types.TypesListFragment;

public class MainActivity extends MeteringActivity implements NavigationView.OnNavigationItemSelectedListener, EditMeteringFragment.Listener {

    private static final String STATE_TITLE = "title";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            replaceContent(SummaryFragment.create());
        } else {
            setTitle(savedInstanceState.getString(STATE_TITLE));
        }

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.addDrawerListener(drawerToggle);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE, String.valueOf(getTitle()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        final boolean b;
        switch (item.getItemId()) {
            case R.id.menu_summary:
                replaceContent(SummaryFragment.create());
                b = true;
                break;
            case R.id.menu_enter_metering:
                replaceContent(EditMeteringFragment.create());
                b = true;
                break;
            case R.id.menu_all_metering:
                replaceContent(AllMeteringFragment.create());
                b = true;
                break;
            case R.id.menu_devices:
                replaceContent(DevicesListFragment.create());
                b = true;
                break;
            case R.id.menu_device_types:
                replaceContent(TypesListFragment.create());
                b = true;
                break;
            case R.id.menu_about:
                replaceContent(AboutFragment.create());
                b = true;
                break;
            default:
                b = false;
                break;
        }
        if (b) {
            drawerLayout.closeDrawers();
        }
        return b;
    }

    private void replaceContent(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_content, fragment).commit();
        if (fragment instanceof Titleable) {
            String title = ((Titleable) fragment).getTitle(this);
            setTitle(title);
        }
    }

    @Override
    public void onMeteringSaved() {
        replaceContent(SummaryFragment.create());
    }
}
