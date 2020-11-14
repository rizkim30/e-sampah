package com.example.pkm;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import com.google.zxing.Result;
import android.view.View;


public class QRCode extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_code);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generate();
            }
        });
    }
    private void generate(){
        mScannerView= new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }
    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        String text, format;
        text = rawResult.getText().toString();
        format = rawResult.getBarcodeFormat().toString();
        Toast.makeText(this, "Text : " + text + "\n" + "format:" + format, Toast.LENGTH_SHORT).show();
    }
}

