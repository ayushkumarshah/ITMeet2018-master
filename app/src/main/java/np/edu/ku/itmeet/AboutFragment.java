package np.edu.ku.itmeet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AboutFragment extends Fragment implements View.OnClickListener{
    Context context;
    Activity activity;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootview= inflater.inflate(R.layout.about, container, false);

            context = rootview.getContext();
            activity = getActivity();
            Button SambadGP, SambadG, SambadT, AyushGP, AyushG, AyushT, DipeshGP,DipeshG, DipeshT, SuyogGP, SuyogG, SuyogT;
            SambadGP=(Button) rootview.findViewById(R.id.buttongp1);
            SambadGP.setOnClickListener(this);
            SambadG=(Button) rootview.findViewById(R.id.buttong1);
            SambadG.setOnClickListener(this);
            SambadT=(Button) rootview.findViewById(R.id.buttona1);
            SambadT.setOnClickListener(this);
            AyushGP=(Button) rootview.findViewById(R.id.buttongp2);
            AyushGP.setOnClickListener(this);
            AyushG=(Button) rootview.findViewById(R.id.buttong2);
            AyushG.setOnClickListener(this);
            AyushT=(Button) rootview.findViewById(R.id.buttona2);
            AyushT.setOnClickListener(this);
            DipeshGP=(Button) rootview.findViewById(R.id.buttongp3);
            DipeshGP.setOnClickListener(this);
            DipeshG=(Button) rootview.findViewById(R.id.buttong3);
            DipeshG.setOnClickListener(this);
            DipeshT=(Button) rootview.findViewById(R.id.buttona3);
            DipeshT.setOnClickListener(this);
            SuyogGP=(Button) rootview.findViewById(R.id.buttongp4);
            SuyogGP.setOnClickListener(this);
            SuyogG=(Button) rootview.findViewById(R.id.buttong4);
            SuyogG.setOnClickListener(this);
            SuyogT=(Button) rootview.findViewById(R.id.buttona4);
            SuyogT.setOnClickListener(this);
        return rootview;
        }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View dev) {
        // default method for handling onClick Events..
        switch (dev.getId()) {
            case R.id.buttongp1:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100000956408193")));

                break;

            case R.id.buttong1:
                // do your code
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/sambadbidari")));
                //String gprofile ="https://github.com/sambadbidari";
                break;
            case R.id.buttona1:
                // do your code
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/i_bidari")));

                break;
            case R.id.buttongp2:
                // do your code
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100001039167019")));
                break;

            case R.id.buttong2:
                // do your code
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/cronaldo07")));
                break;
            case R.id.buttona2:
                // do your code
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Ayushkumarshah7")));

                break;
            case R.id.buttongp3:
                // do your code
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100010725151010")));
                break;

            case R.id.buttong3:
                // do your code
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/")));
                break;
            case R.id.buttona3:
                // do your code
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(" https://twitter.com/")));
                break;
            case R.id.buttongp4:
                // do your code
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100000155435271")));
                break;

            case R.id.buttong4:
                // do your code
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/")));
                break;
            case R.id.buttona4:
                // do your code
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(" https://twitter.com/leosuyog")));

                break;
        }
        }
    }

