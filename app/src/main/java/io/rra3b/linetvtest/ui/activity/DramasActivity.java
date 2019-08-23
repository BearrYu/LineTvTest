package io.rra3b.linetvtest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import io.rra3b.linetvtest.InjectorUtils;
import io.rra3b.linetvtest.R;
import io.rra3b.linetvtest.ui.base.BaseActivity;
import io.rra3b.linetvtest.ui.fragment.DramasFragment;
import io.rra3b.linetvtest.ui.fragment.SearchFragment;
import io.rra3b.linetvtest.ui.viewmodel.DramasViewModel;
import io.rra3b.linetvtest.ui.viewmodel.DramasViewModel.FragmentAction;

public class DramasActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dramas);

    DramasViewModel mViewModel = InjectorUtils.provideDramasViewModel(this);
    mViewModel.getLiveFragmentAction()
        .observe(this, this::handleFragmentAction);

    mViewModel.getLiveDramaToShow()
        .observe(this, this::showDramaDetail);

    getSupportFragmentManager().beginTransaction()
        .replace(R.id.a_dramas_flContainer, DramasFragment.newInstance(), DramasFragment.TAG)
        .commit();
  }

  private void handleFragmentAction(@FragmentAction int action) {
    switch (action) {
      case DramasViewModel.FRAGMENT_ACTION_BACK_TO_LIST:
        getSupportFragmentManager()
            .popBackStack(SearchFragment.TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        break;
      case DramasViewModel.FRAGMENT_ACTION_SEARCH:
        showSearchFragment();
        break;
      default:
        break;
    }
  }

  private void showSearchFragment() {
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.a_dramas_flContainer, SearchFragment.newInstance(), SearchFragment.TAG)
        .addToBackStack(SearchFragment.TAG)
        .commit();
    setTitle("Search Dramas");
  }

  private void showDramaDetail(int dramaId) {
    startActivity(new Intent()
        .setClass(this, DramaDetailActivity.class)
        .putExtra(DramaDetailActivity.EXT_DRAMA_ID, dramaId));
  }

  @Override
  public void onBackPressed() {
    if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
      getSupportFragmentManager().popBackStack();
      //.popBackStack(SearchFragment.TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    } else {
      super.onBackPressed();
    }
  }
}
