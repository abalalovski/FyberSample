package com.twodwarfs.fyber.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.twodwarfs.fyber.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Aleksandar Balalovski Balalovski
 * <p/>
 * Holder MainActivity, serves as representational container
 */

public class MainActivity extends AppCompatActivity {

    /** Really useless toolbar **/
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initUi();
    }

    private void initUi() {
        setSupportActionBar(mToolbar);
    }
}
