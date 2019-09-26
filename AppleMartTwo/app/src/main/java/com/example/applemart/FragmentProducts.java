package com.example.applemart;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static androidx.media.MediaBrowserServiceCompat.RESULT_OK;


public class FragmentProducts extends Fragment {
    EditText prodID,prodName,prodDiscription,prodPrice,prodDiscount,prodQuantity,prodUnit;
    Button addProduct;
    ImageView prodImage;

    Uri filePath;
    ContentResolver cr;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_products, null);
        addProduct = (Button) rootView.findViewById(R.id.add_product_button);
        prodImage = (ImageView) rootView.findViewById(R.id.product_image);
        prodImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }



        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        return rootView;
    }


    private void uploadImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data!=null && data.getData()!=null ){
             filePath = data.getData();
            ContentResolver contentResolver = ;
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filePath);
        }
    }
}
