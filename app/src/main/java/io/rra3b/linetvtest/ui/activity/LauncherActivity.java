package io.rra3b.linetvtest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import io.rra3b.linetvtest.R;

public class LauncherActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launcher);

    startActivity(new Intent().setClass(this, DramasActivity.class));
    finish();
  }
}
