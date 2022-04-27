package com.tilegi.codereader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SaveInfoActivity extends AppCompatActivity {

    //Radio Button Tanımlamaları
    private RadioGroup radioGroupSag;
    private RadioGroup radioGroupSol;
    private RadioGroup radioGroupJant;
    private RadioGroup radioGroupLastik;
    private RadioGroup radioGroupEbat;


    private String registerName;
    private String rimType;
    private String tireType;
    private String sizeName;



//    private RadioButton radioButtonSagOn;
//    private RadioButton radioButtonSagOrta;
//    private RadioButton radioButtonSagArka;
//    private RadioButton radioButtonSolOn;
//    private RadioButton radioButtonSolOrta;
//    private RadioButton radioButtonSolArka;


    private EditText saveName;
    private EditText rimInside;
    private EditText rimOut;
    private EditText tireNumber;
    //kullancağım edit textleri tanımladım


    private Button rimButton1;
    private Button rimButton2;
    private Button tireButton3;
    private Button saveButton;
    private Button showListButton;
    //kullancağım butoınları tanımladım

    private ImageView mPreviewIV;
    //fotosunu çektiğimiz resmi kullanmak için imageview tanımladım

    private static  final int CAMERA_REQUEST_CODE=200;
    private static  final int STORAGE_REQUEST_CODE=400;
    private static  final int IMAGE_PICK_GALLERY_CODE=1000;
    private static  final int IMAGE_PICK_CAMERA_CODE=1001;
    //görüntü almak için kullanılan kodlar

    List<InfoModel> infoModelList; //kaydettiğimiz bilgileri liste şeklinde tutmak için bir model listesi oluşturdum

    // InfoModel veysel = new InfoModel(); // yeni bir model üretmek için tanımladığımız değer


    String cameraPermission[]; //kamera izni almak için kullancağım
    String storagePermission[]; //galeri izni almak için kullancağım

    Uri imageUri; //kameradan veya galeriden alınan görüntünün pathini tutarkeee.


    //if (binding.edtFileName.text.isNotEmpty()) {

    //try {

    //repo.addNote(Note(binding.edtFileName.text.toString(),
    // binding.edtNoteText.text.toString()))
    // } catch (e: Exception) {
    // showToast("File Write Failed")
    //  }

    // binding.edtFileName.text.clear()
    //  binding.edtNoteText.text.clear()
    //} else {
    // showToast("Please provide a Filename")
    // }





    int edit; //butona bastığıhnda algılanan yazıyı hangi editextte yazacağını belirlemek için atadığım değer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_info);

        radioGroupSag = findViewById(R.id.radioGroupSag);
        radioGroupSol = findViewById(R.id.radioGroupSol);
        radioGroupJant = findViewById(R.id.radioGroupJant);
        radioGroupLastik = findViewById(R.id.radioGroupLastik);
        radioGroupEbat = findViewById(R.id.radioGroupEbat);

//
//        radioButtonSagOn = findViewById(R.id.radio_sag_on);
//        radioButtonSagOrta = findViewById(R.id.radio_sag_orta);
//        radioButtonSagArka = findViewById(R.id.radio_sag_arka);
//        radioButtonSolOn = findViewById(R.id.radio_sol_on);
//        radioButtonSolOrta = findViewById(R.id.radio_sol_orta);
//        radioButtonSolArka = findViewById(R.id.radio_sol_arka);


        saveName=findViewById(R.id.saveName);
        rimInside=findViewById(R.id.rimNameIn);
        rimOut=findViewById(R.id.rimNameOut);
        tireNumber=findViewById(R.id.tireName);
        rimButton1=findViewById(R.id.rimButton1);
        rimButton2=findViewById(R.id.rimButton2);
        tireButton3=findViewById(R.id.tireButton3);
        saveButton=findViewById(R.id.saveButton);
        showListButton=findViewById(R.id.showListButton);
        mPreviewIV = new ImageView(this);
        infoModelList = new ArrayList<>();
        loadData();
//        mPreviewIV=findViewById(R.id.imageIV);


        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE };
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE };



        radioGroupSag.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio_sag_on:
                        Log.d("","radio_sag_on");
                        registerName = "Sağ Ön";
                        radioGroupSol.clearCheck();
                        break;
                    case R.id.radio_sag_orta:
                        Log.d("","radio_sag_orta");
                        registerName = "Sağ Orta";
                        radioGroupSol.clearCheck();
                        break;
                    case R.id.radio_sag_arka:
                        Log.d("","radio_sag_arka");
                        registerName = "Sağ Arka";
                        radioGroupSol.clearCheck();
                        break;
                }
            }
        });
        radioGroupSol.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio_sol_on:
                        Log.d("","radio_sol_on");
                        registerName = "Sol Ön";
                        radioGroupSag.clearCheck();
                        break;
                    case R.id.radio_sol_orta:
                        Log.d("","radio_sol_orta");
                        registerName = "Sol Orta";
                        radioGroupSag.clearCheck();
                        break;
                    case R.id.radio_sol_arka:
                        Log.d("","radio_sol_arka");
                        registerName = "Sol Arka";
                        radioGroupSag.clearCheck();
                        break;
                }
            }
        });
        radioGroupJant.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio_jant:
                        Log.d("","radio_jant");
                        rimType = "Jantsa(117756)";
                        break;
                    case R.id.radio_ratex:
                        Log.d("","radio_ratex");
                        rimType = "Radex";
                        break;
                    case R.id.radio_cemmerz:
                        Log.d("","radio_cemmerz");
                        rimType = "Lemmerz";
                        break;
                }
            }
        });
        radioGroupLastik.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio_prielli:
                        Log.d("","radio_prielli");
                        tireType = "Pirelli";
                        break;
                    case R.id.radio_brigston:
                        Log.d("","radio_brigston");
                        tireType = "Bridgestone";
                        break;
                    case R.id.radio_goodyear:
                        Log.d("","radio_goodyear");
                        tireType = "Goodyear";
                        break;
                }
            }
        });
        radioGroupEbat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio_ebat1:
                        sizeName = "385/55";
                        break;
                    case R.id.radio_ebat2:
                        sizeName = "385/65";
                        break;
                    case R.id.radio_ebat3:
                        sizeName = "445/45";
                        break;
                }
            }
        });



        rimButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit = 1;
                openCamera();
            }
        });
        rimButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit = 2;
                openCamera();
            }
        });
        tireButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit = 3;
                openCamera();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoModelList.add(new InfoModel(
                        saveName.getText().toString(),
                        registerName,
                        rimType,
                        tireType,
                        sizeName,
                        rimInside.getText().toString(),
                        rimOut.getText().toString(),
                        tireNumber.getText().toString()
                ));
                closeKeyboard();
                Toast.makeText(SaveInfoActivity.this, "Bilgiler Eklendi", Toast.LENGTH_SHORT).show();
                saveName.getText().clear();
                rimInside.getText().clear();
                rimOut.getText().clear();
                tireNumber.getText().clear();
                radioGroupEbat.clearCheck();
                radioGroupLastik.clearCheck();
                radioGroupJant.clearCheck();
                radioGroupSag.clearCheck();
                radioGroupSol.clearCheck();
                registerName="";
                rimType="";
                tireType="";
                sizeName="";



            }
        });

        showListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaveInfoActivity.this, InfoList.class);
                startActivity(intent);
                finish();
            }
        });

    }

//   void clearSag(){
//       if(radioButtonSagOn.isChecked()||
//               radioButtonSagOrta.isChecked()||
//               radioButtonSagArka.isChecked()
//       ){
//           radioButtonSagOn.setChecked(false);
//           radioButtonSagOrta.setChecked(false);
//           radioButtonSagArka.setChecked(false);}
//    }
//   void clearSol(){
//       if(radioButtonSolOn.isChecked()||
//               radioButtonSolOrta.isChecked()||
//               radioButtonSolArka.isChecked()
//       ){
//           radioButtonSolOn.setChecked(false);
//           radioButtonSolOrta.setChecked(false);
//           radioButtonSolArka.setChecked(false);}
//    }
    private void closeKeyboard()
    {
        // this will give us the view
        // which is currently focus
        // in this layout
        View view = this.getCurrentFocus();

        // if nothing is currently
        // focus then this will protect
        // the app from crash
        if (view != null) {

            // now assign the system
            // service to InputMethodManager
            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager
                    .hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
        }
    }

    private EditText setEditText(){
        //bu fonksiyon butondan gelen değere göre yazıyı hangi edittexte yazacağını belirdliyort
        switch (edit){
            case 1:
                return rimInside;
            case 2:
                return rimOut;
            case 3:
                return tireNumber;
            default: return rimInside;
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    private void saveData() {
        Log.d("infosave",infoModelList.size()+"");
        SharedPreferences sharedPreferences = getSharedPreferences("ModelName",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(infoModelList);
        editor.putString("model list",json);
        editor.apply();
    }
    private void loadData() {
        Log.d("infoLoad",infoModelList.size()+"");
        SharedPreferences sharedPreferences = getSharedPreferences("ModelName",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("model list",null);
        Type type= new TypeToken<ArrayList<InfoModel>>(){}.getType();
        infoModelList=gson.fromJson(json,type);
        if (infoModelList==null){
            infoModelList=new ArrayList<>();
        }
    }

    private void pickGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);

        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_GALLERY_CODE);
    }
    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this,storagePermission,STORAGE_REQUEST_CODE);
    }
    private boolean checkStoragePermission() {
        boolean result=  ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this,cameraPermission,CAMERA_REQUEST_CODE);
    }
    private void pickCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"NewPic");
        values.put(MediaStore.Images.Media.DESCRIPTION,"Image to Text");
        imageUri= getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(cameraIntent,IMAGE_PICK_GALLERY_CODE);
    }
    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1=  ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }


    private void openCamera(){
        if(!checkCameraPermission()){
            requestCameraPermission();

        }else {
            pickCamera();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && writeStorageAccepted) {
                        pickCamera();
                    } else {
                        Toast.makeText(this, "İstek Reddedildi!", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case STORAGE_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean writeStorageAccepted = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    if (writeStorageAccepted) {
                        pickGallery();
                    } else {
                        Toast.makeText(this, "İstek Reddedildi!", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                if(data!=null)
                    CropImage.activity(data.getData()).setGuidelines(CropImageView.Guidelines.ON).start(this);
                else
                    CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON).start(this);
            }
            if (requestCode == IMAGE_PICK_CAMERA_CODE ) {
                CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON).start(this);
                Toast.makeText(this, "data Null IMAGE_PICK_CAMERA_CODE", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                mPreviewIV.setImageURI(resultUri);

                BitmapDrawable bitmapDrawable = (BitmapDrawable) mPreviewIV.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap(); // aldığımız fotografı bitmapa dönüştürdük
                TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext()).build(); //text recognşizer ürettik
                if (!recognizer.isOperational()) {
                    Toast.makeText(this, "Hata", Toast.LENGTH_SHORT).show();
                } else {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items = recognizer.detect(frame);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < items.size(); i++) {
                        TextBlock myItem = items.valueAt(i);
                        sb.append(myItem.getValue());
                        sb.append("\n");
                    }
                    setEditText().setText(sb.toString());
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }
    }
}