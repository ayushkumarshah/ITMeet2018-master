package np.edu.ku.itmeet18;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import np.edu.ku.itmeet18.FirebaseAuthentication.Login;
import np.edu.ku.itmeet18.FirebaseAuthentication.Profile;
import np.edu.ku.itmeet18.bus_route.bus_route;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemNews;
    private ResideMenuItem itemEvents;
    private ResideMenuItem itemEventsReg;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemShare;
    private ResideMenuItem itemVisit;
    private ResideMenuItem itemAbout;
    private ResideMenuItem busInfo;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpMenu();
        if( savedInstanceState == null )
            changeFragment(new Login());

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                changeFragment(new Login());

    }

    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);

        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.setShadowVisible(true);
        resideMenu.attachToActivity(this);
        //valid scale factor is between 0.0f and 1.0f. left menu'width is 150dip.
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.icon_home,     "Home");
        itemNews  = new ResideMenuItem(this, R.drawable.icon_news,  "News");
        itemEvents  = new ResideMenuItem(this, R.drawable.icon_events,  "Events");
        itemEventsReg  = new ResideMenuItem(this, R.drawable.accountplus,  "Register");
        itemProfile = new ResideMenuItem(this, R.drawable.icon_profile,  "Profile");
        itemShare = new ResideMenuItem(this, R.drawable.icon_share, "Share");
        itemVisit = new ResideMenuItem(this, R.drawable.icon_visit, "Visit Us");
        itemAbout = new ResideMenuItem(this, R.drawable.icon_about, "About Us");
        busInfo = new ResideMenuItem(this, R.drawable.bus, "Bus Route");

        itemHome.setOnClickListener(this);
        itemNews.setOnClickListener(this);
        itemEvents.setOnClickListener(this);
        itemEventsReg.setOnClickListener(this);
        itemProfile.setOnClickListener(this);
        itemVisit.setOnClickListener(this);
        itemShare.setOnClickListener(this);
        itemAbout.setOnClickListener(this);
        busInfo.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemNews, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemEvents, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemEventsReg, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemVisit, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemShare, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemAbout, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(busInfo, ResideMenu.DIRECTION_LEFT);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemHome){
            changeFragment(new HomeFragment());
        }else if (view == itemNews) {
            changeFragment(new NewsFragment());
        }else if (view == itemEventsReg) {
            changeFragment(new registration());
        }else if (view == itemProfile) {
            changeFragment(new Profile());
        }else if (view == busInfo){
            changeFragment(new bus_route());
        }else if (view == itemEvents){
            changeFragment(new EventFragment());
        }else if (view == itemShare){
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, "Check out IT Meet 2018 App at: https://play.google.com/store/apps/details?id=" + getPackageName());
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        }else if (view == itemVisit) {Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://itmeet.ku.edu.np/"));
            startActivity(browserIntent);
        }else if (view == itemAbout){
            changeFragment(new AboutFragment());
        }

        resideMenu.closeMenu();
    }



    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
}


