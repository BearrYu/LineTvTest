package io.rra3b.linetvtest.ui.base;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

  //region @Override setContentView(...);
  @Override
  public void setContentView(int layoutResID) {
    super.setContentView(layoutResID);
    ButterKnife.bind(this);
  }

  @Override
  public void setContentView(View view) {
    super.setContentView(view);
    ButterKnife.bind(this);
  }

  @Override
  public void setContentView(View view, LayoutParams params) {
    super.setContentView(view, params);
    ButterKnife.bind(this);
  }
  //endregion
}
