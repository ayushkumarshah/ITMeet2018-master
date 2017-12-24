package np.edu.ku.itmeet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.special.ResideMenu.ResideMenu;


public class HomeFragment extends Fragment implements View.OnClickListener {

    Context context;
    Activity activity;
    private ResideMenu resideMenu;
    ViewPager mViewPager;
    CustomPagerAdapter mCustomPagerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.home, container, false);
        context = parentView.getContext();
        activity = getActivity();
        ImageButton verisk, yomari, neo, nta,gbd, sunway, doit, ekantipur, worldlink, ittn, prime;
        verisk = (ImageButton) parentView.findViewById(R.id.verisk);
        verisk.setOnClickListener(this);
        yomari = (ImageButton) parentView.findViewById(R.id.yomari);
        yomari.setOnClickListener(this);
        neo = (ImageButton) parentView.findViewById(R.id.neo);
        neo.setOnClickListener(this);
        nta = (ImageButton) parentView.findViewById(R.id.nta);
        nta.setOnClickListener(this);
        gbd = (ImageButton) parentView.findViewById(R.id.gbd);
        gbd.setOnClickListener(this);
        sunway = (ImageButton) parentView.findViewById(R.id.sunway);
        sunway.setOnClickListener(this);
        doit = (ImageButton) parentView.findViewById(R.id.doit);
        doit.setOnClickListener(this);
        ekantipur = (ImageButton) parentView.findViewById(R.id.ekantipur);
        ekantipur.setOnClickListener(this);
        worldlink = (ImageButton) parentView.findViewById(R.id.worldlink);
        worldlink.setOnClickListener(this);
        ittn = (ImageButton) parentView.findViewById(R.id.ittn);
        ittn.setOnClickListener(this);
        prime = (ImageButton) parentView.findViewById(R.id.prime);
        prime.setOnClickListener(this);
        mCustomPagerAdapter = new CustomPagerAdapter(container.getContext());

        mViewPager = (ViewPager) parentView.findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        return parentView;
    }

    @Override
    public void onClick(View ho) {
        switch (ho.getId()) {
            case R.id.verisk:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.verisk.com/")));
                break;

            case R.id.yomari:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yomari.com.np/")));
                break;

            case R.id.neo:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://neosphere.com.np/")));
                break;

            case R.id.nta:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nta.gov.np/")));
                break;

            case R.id.gbd:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.growbydata.com/")));
                break;

            case R.id.sunway:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://sunway.edu.np/")));
                break;

            case R.id.doit:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://doit.gov.np/")));
                break;

            case R.id.ekantipur:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ekantipur.com/")));
                break;

            case R.id.worldlink:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://worldlink.com.np/")));
                break;

            case R.id.ittn:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ittrainingnepal.com/")));
                break;

            case R.id.prime:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.primefm.com.np/")));
                break;
        }
    }

}