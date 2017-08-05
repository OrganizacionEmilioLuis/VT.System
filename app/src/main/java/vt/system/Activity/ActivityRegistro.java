package vt.system.Activity;

import android.app.Activity;
//import android.support.design.widget.FloatingActionButton;
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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;


import vt.system.Navigation_Drawer;
import vt.system.R;
import vt.system.SplashScreen;

public class ActivityRegistro extends AppCompatActivity {

    com.getbase.floatingactionbutton.FloatingActionButton fabCamera,fabGallery,fabUpload,fabGenerate;
    Animation FabOpen,FabClose,FabRClockwise,FabRanticlockwise;
    boolean isOpen = false;

    private final String TAG =this.getClass().getName();

    Button btnreg;
    Button btncancel;
    EditText editTextNombre_Cliente,editTextTelefono_Cliente,editTextMarca_Smartphone,editTextModelo_Smartphone,editTextImei1_Smartphone,editTextImei2_Smartphone,editTextObservacion;
    ImageView ivCamera,ivGallery,ivUpload,ivImage,ivImageQr;
    Spinner spinnerDistrito,spinnerInteres;
    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;

    final int CAMERA_REQUEST=13323;
    final int GALLERY_REQUEST=22131;

    String selectedPhoto="";

    String idProveedor;
    String msg;
    String text2Qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_registro);

        cameraPhoto=new CameraPhoto(getApplicationContext());
        galleryPhoto=new GalleryPhoto(getApplicationContext());

        ivImage=(ImageView)findViewById(R.id.ivImage);
        ivImageQr=(ImageView)findViewById(R.id.ivImageQr);
        fabCamera = (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.fabCamera);
        fabGallery= (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.fabGallery);
        fabUpload = (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.fabUpload);
        fabGenerate = (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.fabGenerate);
        editTextNombre_Cliente=(EditText)findViewById(R.id.editTextNombre_Cliente);
        editTextTelefono_Cliente=(EditText)findViewById(R.id.editTextTelefono_Cliente);
        editTextMarca_Smartphone=(EditText)findViewById(R.id.editTextMarca_Smartphone);
        editTextModelo_Smartphone=(EditText)findViewById(R.id.editTextModelo_Smartphone);
        editTextImei1_Smartphone=(EditText)findViewById(R.id.editTextImei1_Smartphone);
        editTextImei2_Smartphone=(EditText)findViewById(R.id.editTextImei2_Smartphone);
        editTextObservacion=(EditText)findViewById(R.id.editTextObservacion);

        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(),CAMERA_REQUEST);
                    cameraPhoto.addToGallery();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"Algo equivocado al tomar fotos",Toast.LENGTH_SHORT).show();
                }
            }
        });

        fabGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(galleryPhoto.openGalleryIntent(),GALLERY_REQUEST);
            }
        });

        fabUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String nombre_cliente = editTextNombre_Cliente.getText().toString();
                    String telefono_cliente = editTextTelefono_Cliente.getText().toString();
                    String marca_smartphone = editTextMarca_Smartphone.getText().toString();
                    String modelo_smartphone = editTextModelo_Smartphone.getText().toString();
                    String imei1_smartphone = editTextImei1_Smartphone.getText().toString();
                    String imei2_smartphone = editTextImei1_Smartphone.getText().toString();
                    String observacion = editTextObservacion.getText().toString();

                    if (!nombre_cliente.equals("") && !telefono_cliente.equals("") && !marca_smartphone.equals("") && !modelo_smartphone.equals("") && !imei1_smartphone.equals("") && !imei2_smartphone.equals("") && !observacion.equals("")){
                        if (!selectedPhoto.equals("")|| selectedPhoto!=null){

                            Bitmap bitmap= ImageLoader.init().from(selectedPhoto).requestSize(500,500).getBitmap();
                            String encodedImage = ImageBase64.encode(bitmap);
                            Log.d(TAG, encodedImage);

                            HashMap<String,String> postData=new HashMap<String, String>();
                            postData.put("imagen_smartphone", encodedImage);
                            postData.put("nombre_cliente",nombre_cliente);
                            postData.put("telefono_cliente",telefono_cliente);
                            postData.put("marca_smartphone",marca_smartphone);
                            postData.put("modelo_smartphone",modelo_smartphone);
                            postData.put("imei1_smartphone",imei1_smartphone);
                            postData.put("imei2_smartphone",imei2_smartphone);
                            postData.put("observacion",observacion);

                            PostResponseAsyncTask task = new PostResponseAsyncTask(ActivityRegistro.this, postData, new AsyncResponse() {
                                @Override
                                public void processFinish(String s) {
                                    if (s.contains("Registrado")){
                                        Toast.makeText(getApplicationContext(), "Registro Exitoso.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "Error al subir.",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                            //task.execute("http://192.168.137.1/WebService_VargasTecniFour/upload.php");
                            task.execute("https://emilioluisprogramadores.000webhostapp.com/WebService_VargasTecniFour/upload.php");
                            task.setEachExceptionsHandler(new EachExceptionsHandler() {
                                @Override
                                public void handleIOException(IOException e) {
                                    Toast.makeText(getApplicationContext(),"No es posible conectar con el servidor.",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void handleMalformedURLException(MalformedURLException e) {
                                    Toast.makeText(getApplicationContext(),"Error de URL.",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void handleProtocolException(ProtocolException e) {
                                    Toast.makeText(getApplicationContext(),"Error. de Protocolo ",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                                    Toast.makeText(getApplicationContext(),"Error de Codificación.",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
                }
                catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),
                            "Algo equivocado al codificar fotos",Toast.LENGTH_SHORT).show();
                }
            }
        });

        fabGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text2Qr = editTextNombre_Cliente.getText().toString().trim();
                text2Qr = editTextTelefono_Cliente.getText().toString().trim();
                text2Qr = editTextMarca_Smartphone.getText().toString().trim();
                text2Qr = editTextModelo_Smartphone.getText().toString().trim();
                text2Qr = editTextImei1_Smartphone.getText().toString().trim();
                text2Qr = editTextImei1_Smartphone.getText().toString().trim();
                text2Qr = editTextObservacion.getText().toString().trim();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE,500,500);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    ivImageQr.setImageBitmap(bitmap);
                }
                catch (WriterException e){
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                    Toast.makeText(getApplicationContext(),"Algo equivocado al cargar fotos",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getApplicationContext(),"Algo equivocado al elegir fotos",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
