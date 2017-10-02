package najmul.bitcoinwatcher;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 10/2/2017.
 */

public interface BitcoinApi {
  @GET("v1/bpi/currentprice.json") Call<Bitcoin>getBitcoinData();
}
