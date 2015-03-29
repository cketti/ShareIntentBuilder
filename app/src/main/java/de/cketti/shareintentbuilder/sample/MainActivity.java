package de.cketti.shareintentbuilder.sample;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;

import de.cketti.shareintentbuilder.ShareIntentBuilder;


public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpClickListeners();
    }

    private void setUpClickListeners() {
        findViewById(R.id.button_share_text).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                shareText();
            }
        });
    }

    private void shareText() {
        ShareIntentBuilder.from(this)
                .text(getString(R.string.share_text))
                .share(getString(R.string.share_dialog_title));
    }
}
