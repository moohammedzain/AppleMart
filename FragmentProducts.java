package com.example.applemart;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;
import java.util.UUID;

import static androidx.media.MediaBrowserServiceCompat.RESULT_OK;


public class FragmentProducts extends Fragment {
    EditText prodID,prodName,prodDiscription,prodPrice,prodDiscount,prodQuantity,prodUnit;
    Button addProduct;
    ImageView prodImage;
    Uri filePath;
    FirebaseStorage storage;
    StorageReference storageReference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_products, null);
        addProduct = (Button) rootView.findViewById(R.id.add_product_button);
        prodImage = (ImageView) rootView.findViewById(R.id.product_image);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        prodImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }


        });

        return rootView;
    }
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data!=null && data.getData()!=null ){
             filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),filePath);
                prodImage.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
           }
    }
    private void uploadImage() {
        if (filePath != null) {
            final ProgressDialog Pd = new ProgressDialog(getContext());
            Pd.setTitle("Uploading....");
            Pd.show();
            StorageReference reference = storageReference.child("images/s" + UUID.randomUUID().toString());
            reference.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Pd.dismiss();
                    Toast.makeText(getActivity(), "Image Uploaded", Toast.LENGTH_SHORT);
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                    double progress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    Pd.setMessage("Uploaded"+(int)progress+"%");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                System.out.println(exception);
                exception.printStackTrace();
                }
            });
        }
    }
}
