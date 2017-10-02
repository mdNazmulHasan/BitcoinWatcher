package najmul.bitcoinwatcher;

import android.Manifest;
import android.Manifest.permission;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import najmul.bitcoinwatcher.databinding.ActivityBitcoinBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BitcoinActivity extends AppCompatActivity {
  private static final int MY_PERMISSIONS_REQUEST_INTERNET = 1;
  BitcoinApi bitcoinApi;
  private ActivityBitcoinBinding activityBitcoinBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bitcoin);
    activityBitcoinBinding = DataBindingUtil.setContentView(this, R.layout.activity_bitcoin);
    checkInternetPermission();

  }

  private void checkInternetPermission() {
    if (ContextCompat.checkSelfPermission(this,
        permission.INTERNET)
        != PackageManager.PERMISSION_GRANTED) {
      if (ActivityCompat.shouldShowRequestPermissionRationale(this,
          permission.INTERNET)) {
      } else {
        ActivityCompat.requestPermissions(this,
            new String[]{permission.READ_CONTACTS},
            MY_PERMISSIONS_REQUEST_INTERNET);
      }
    }else{
      getData();
    }
  }

  private void getData() {
    bitcoinApi=RetrofitClient.getClient().create(BitcoinApi.class);
    Call<Bitcoin>bitcoinCall=bitcoinApi.getBitcoinData();
    bitcoinCall.enqueue(new Callback<Bitcoin>() {
      @Override public void onResponse(Call<Bitcoin> call, Response<Bitcoin> response) {
        Bitcoin bitcoin=response.body();
        activityBitcoinBinding.setVariable(najmul.bitcoinwatcher.BR.bitcoin,bitcoin);
      }

      @Override public void onFailure(Call<Bitcoin> call, Throwable t) {

      }
    });
  }

  @Override
  public void onRequestPermissionsResult(int requestCode,
      String permissions[], int[] grantResults) {
    switch (requestCode) {
      case MY_PERMISSIONS_REQUEST_INTERNET: {
        if (grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          reloadActivity();

        } else {
          reloadActivity();
        }
        return;
      }
    }
  }

  private void reloadActivity() {
    Intent intent=new Intent(this,BitcoinActivity.class);
    startActivity(intent);
  }
}
