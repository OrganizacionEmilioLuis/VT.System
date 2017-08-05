package vt.system.Activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;


import vt.system.Activity.FragmentConsulta;
import vt.system.R;
import vt.system.SplashScreen;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentConsulta extends Fragment {

    FloatingActionButton fab_plus,fab_1,fab_2;
    Animation FabOpen,FabClose,FabRClockwise,FabRanticlockwise;
    boolean isOpen = false;

    private final String TAG =this.getClass().getName();

    Button btnreg;
    Button btncancel;
    EditText editTextName,editTextTelefono,editTextObservacion;
    ImageView ivCamera,ivGallery,ivUpload,ivImage;
    Spinner spinnerDistrito,spinnerInteres;
    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;

    final int CAMERA_REQUEST=13323;
    final int GALLERY_REQUEST=22131;

    String selectedPhoto="";

    String idProveedor;
    String msg;


    private OnFragmentInteractionListener mListener;

    public FragmentConsulta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consulta, container, false);

        cameraPhoto=new CameraPhoto(getApplicationContext());
        galleryPhoto=new GalleryPhoto(getApplicationContext());

        ivImage=(ImageView)view.findViewById(R.id.ivImage);
        ivCamera=(ImageView)view.findViewById(R.id.ivCamera);
        ivGallery=(ImageView)view.findViewById(R.id.ivGallery);
        ivUpload=(ImageView)view.findViewById(R.id.ivUpload);
        editTextName=(EditText)view.findViewById(R.id.editTextName);
        editTextTelefono=(EditText)view.findViewById(R.id.editTextTelefono);
        editTextObservacion=(EditText)view.findViewById(R.id.editTextObservacion);

        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(),CAMERA_REQUEST);
                    cameraPhoto.addToGallery();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"Something Wrong while taking photos",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(galleryPhoto.openGalleryIntent(),GALLERY_REQUEST);
            }
        });





        return view;
    }

    private Context getApplicationContext() {
        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            if(requestCode==CAMERA_REQUEST){
                String photoPath= cameraPhoto.getPhotoPath();
                selectedPhoto=photoPath;
                Bitmap bitmap;


                try {
                    bitmap= ImageLoader.init().from(photoPath).requestSize(512,512).getBitmap();

                    File imageFile = new File(photoPath);
                    ExifInterface exif = null;
                    try {
                        exif = new ExifInterface(imageFile.getAbsolutePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                    int rotate = 360;
                    switch (orientation) {
                        case ExifInterface.ORIENTATION_ROTATE_270:
                            rotate = 270;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_180:
                            rotate = 180;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_90:
                            rotate = 90;
                            break;
                    }


                    Matrix matrix = new Matrix();
                    matrix.postRotate(rotate);
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap,512,512,true);
                    Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap , 0, 0, scaledBitmap .getWidth(), scaledBitmap .getHeight(), matrix, true);

                    ivImage.setImageBitmap(rotatedBitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),"Something Wrong while loading photos",Toast.LENGTH_SHORT).show();
                }
                Log.d(TAG,photoPath);
            }
            else if(requestCode==GALLERY_REQUEST){
                Uri uri=data.getData();
                galleryPhoto.setPhotoUri(uri);
                String photoPath=galleryPhoto.getPath();
                selectedPhoto=photoPath;
                Bitmap bitmap;
                try {
                    bitmap= ImageLoader.init().from(photoPath).requestSize(512,512).getBitmap();

                    File imageFile = new File(photoPath);
                    ExifInterface exif = null;
                    try {
                        exif = new ExifInterface(imageFile.getAbsolutePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                    int rotate = 360;
                    switch (orientation) {
                        case ExifInterface.ORIENTATION_ROTATE_270:
                            rotate = 270;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_180:
                            rotate = 180;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_90:
                            rotate = 90;
                            break;
                    }


                    Matrix matrix = new Matrix();
                    matrix.postRotate(rotate);
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap,512,512,true);
                    Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap , 0, 0, scaledBitmap .getWidth(), scaledBitmap .getHeight(), matrix, true);

                    ivImage.setImageBitmap(rotatedBitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),"Something Wrong while choosing photos",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
