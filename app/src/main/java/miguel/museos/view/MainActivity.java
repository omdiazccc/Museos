package miguel.museos.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.ArrayList;

import miguel.museos.R;
import miguel.museos.data.Data;
import miguel.museos.data.Museum;
import miguel.museos.data.News;
import miguel.museos.data.Passport;
import miguel.museos.enums.Enum_tabs;
import miguel.museos.view.middleFragments.MiddleMuseumViewFragment;
import miguel.museos.view.middleFragments.MiddleMuseumsFragment;
import miguel.museos.view.middleFragments.MiddleNewsFragment;
import miguel.museos.view.middleFragments.MiddlePassportFragment;

public class MainActivity extends AppCompatActivity implements TopFragment.OnFragmentInteractionListener,
        MiddlePassportFragment.OnFragmentInteractionListener, MiddleNewsFragment.OnFragmentInteractionListener,
        MiddleMuseumsFragment.OnFragmentInteractionListener, MiddleMuseumViewFragment.OnFragmentInteractionListener,
        ButtomFragment.OnFragmentInteractionListener

{


    public ArrayList<Museum> getMuseumList(){

        return  data.getMuseumList();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ourInstance= this;
        data = Data.getInstance();
        getSupportActionBar().hide();

        topFragment = new TopFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.topframeLayout,topFragment).commit();
        changeTab(Enum_tabs.NEWS);


    }


    public static MainActivity getInstance(){
        return ourInstance;
    }

    private static MainActivity ourInstance;

    @Override
    public void changeTab(Enum_tabs tab) {
        switch (tab) {
            case NEWS:
                layout = (FrameLayout) findViewById(R.id.middleFragmentLayout);
                layout.removeAllViewsInLayout();

                news = new MiddleNewsFragment();


                getSupportFragmentManager().beginTransaction().add(R.id.middleFragmentLayout,news).commit();
                break;
            case MUSEUMS:

                layout = (FrameLayout) findViewById(R.id.middleFragmentLayout);
                layout.removeAllViewsInLayout();



                museums= new MiddleMuseumsFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.middleFragmentLayout,museums).commit();

                break;
            case PASSPORT:

                layout = (FrameLayout) findViewById(R.id.middleFragmentLayout);
                layout.removeAllViewsInLayout();
                passport = new MiddlePassportFragment();

                getSupportFragmentManager().beginTransaction().replace(R.id.middleFragmentLayout,passport).commit();

                break;
        }
    }

    @Override
    public void onMuseumCLick(Museum item) {
        museumView= new MiddleMuseumViewFragment();
        museumView.setMuseum(item);

        layout = (FrameLayout) findViewById(R.id.middleFragmentLayout);
        layout.removeAllViewsInLayout();


        getSupportFragmentManager().beginTransaction().add(R.id.middleFragmentLayout,museumView).commit();

        onMuseum=true;

    }

    @Override
    public void closeMuseumVIew() {

        changeTab(Enum_tabs.MUSEUMS);
    }




    @Override
    public void onBackPressed()
    {
        if(onMuseum){
            closeMuseumVIew();
            onMuseum=false;
        }
        else{
            moveTaskToBack(true);
        }
//
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private static void comunication(String c) {
        Log.d("console_MainActivity", c);
    }


    public ArrayList<News> getNewsList() {
        return data.getNewsList();
    }

    public ArrayList<Passport> getPassportList() {
        return data.getPassportList();
    }


    private boolean onMuseum=false;
    private FrameLayout layout;
    private TopFragment topFragment;
    private Fragment news=null,museums=null,passport=null;
    MiddleMuseumViewFragment museumView;
    private static Data data;

}
