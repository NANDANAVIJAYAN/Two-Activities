package com.example.twoactivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE="com.example.twoactivities.extra.MESSAGE";
    private static final String LOG_TAG=MainActivity.class.getSimpleName();
    private static final int TEXT_REQUEST=1;
    private EditText mMessageEditText;
    private TextView mReplyHeadtextView;
    private TextView mReplyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG,"--------");
        Log.d(LOG_TAG,"onCreate");
        mMessageEditText=findViewById(R.id.editText_main);
        mReplyHeadtextView=findViewById(R.id.text_header_reply);
        mReplyView=findViewById(R.id.text_message_reply);

        if(savedInstanceState != null){
            boolean isVisible=savedInstanceState.getBoolean("reply_visible");
            if(isVisible){
                mReplyHeadtextView.setVisibility(View.VISIBLE);
                mReplyView.setText(savedInstanceState.getString("reply_text"));
                mReplyView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG,"--------");
        Log.d(LOG_TAG,"onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG,"--------");
        Log.d(LOG_TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG,"--------");
        Log.d(LOG_TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,"--------");
        Log.d(LOG_TAG,"onDestroy");
    }

    public void launchSecondActivity(View view) {
        Log.d("Button_clicked","Button Clicked!");
        Intent intent=new Intent(this,SecondActivity.class);
        String message=mMessageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mReplyHeadtextView.getVisibility()==View.VISIBLE){
            outState.putBoolean("reply_visible",true);
            outState.putString("reply_text",mReplyView.getText().toString());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==TEXT_REQUEST){
            if(resultCode==RESULT_OK){
                String reply=data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadtextView.setVisibility(View.VISIBLE);
                mReplyView.setText(reply);
                mReplyView.setVisibility(View.VISIBLE);
            }
        }
    }

}