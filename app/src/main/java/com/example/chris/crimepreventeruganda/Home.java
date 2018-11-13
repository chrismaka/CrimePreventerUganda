package com.example.chris.crimepreventeruganda;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.chris.crimepreventeruganda.PressRelease.PressActivity;
import com.example.chris.crimepreventeruganda.lostandfound.Found;
import com.example.chris.crimepreventeruganda.wanted.Main3Activity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment implements View.OnClickListener {


    public Home() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       RelativeLayout layout = (RelativeLayout)inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView homerecycler = (RecyclerView)layout.findViewById(R.id.home_recycler);
        String[]homeitemnames = new String[4];
        for(int i=0; i<homeitemnames.length; i++){
            homeitemnames[i] = HomeItems.homeitem[i].getName();
        }
        int[]homeitemimages = new int[4];
        for(int i=0; i<homeitemimages.length; i++){
            homeitemimages[i] = HomeItems.homeitem[i].getImageResourceId();
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        homerecycler.setLayoutManager(layoutManager);

        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(homeitemnames, homeitemimages);
        homerecycler.setAdapter(adapter);

        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            public void onClick(int position) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(getActivity(),Found.class);
                        getActivity().startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getActivity(), Myway.class);
                        getActivity().startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getActivity(),Main3Activity.class);
                        getActivity().startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getActivity(),PressActivity.class);
                        getActivity().startActivity(intent3);
                        break;
                }
            }
        });
        Button button = (Button)layout.findViewById(R.id.button2);
        button.setOnClickListener(this);
        return layout;
    }

    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {

                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 999));

            getActivity().startActivity(intent);
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {
                    Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }


    }


    @Override
    public void onClick(View v) {
        isPermissionGranted();

    }

}
