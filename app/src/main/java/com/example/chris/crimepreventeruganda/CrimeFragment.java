package com.example.chris.crimepreventeruganda;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeFragment extends Fragment implements View.OnClickListener {

    private Button buttonChoose;
    private Button buttonUpload;

    private ImageView imageView;

    private EditText categories, details, phonenumber;

    private Bitmap bitmap;
    private static final int CAMERA_REQUEST = 1888;

    //private String UPLOAD_URL ="https://e-ror.com/crimepreventer/upload.php";
    private String UPLOAD_URL ="https://e-ror.com/crimepreventer/upload.php";

    private String KEY_IMAGE = "image";
    private String KEY_CATEGORIES = "category";
    private String KEY_DETAILS = "details";
    private String KEY_NUMBER = "phone";
    public CrimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.fragment_crime, container, false);

        buttonChoose = (Button)layout.findViewById(R.id.buttonChoose);
        buttonUpload = (Button)layout.findViewById(R.id.buttonUpload);

        categories = (EditText)layout.findViewById(R.id.category);
        details = (EditText)layout.findViewById(R.id.details);
        phonenumber = (EditText)layout.findViewById(R.id.phonenumber);

        imageView  = (ImageView)layout.findViewById(R.id.imageView);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
        // Inflate the layout for this fragment
        return layout;
    }


    @Override
    public void onClick(View v) {
        if(v == buttonChoose){
            showFileChooser();
        }
        if(v == buttonUpload){
            uploadImage();

        }
    }
    private void showFileChooser() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private void uploadImage(){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(getActivity(), volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name
                //String name = editTextName.getText().toString().trim();
                String cats = categories.getText().toString().trim();
                String detls = details.getText().toString().trim();
                String fon = phonenumber.getText().toString().trim();


                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_CATEGORIES, cats);
                params.put(KEY_DETAILS, detls);
                params.put(KEY_NUMBER, fon);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
}
