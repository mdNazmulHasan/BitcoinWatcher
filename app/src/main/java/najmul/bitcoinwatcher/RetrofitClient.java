package najmul.bitcoinwatcher;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 10/2/2017.
 */

public class RetrofitClient {
  public static Retrofit getClient(){
    return new Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }
}
