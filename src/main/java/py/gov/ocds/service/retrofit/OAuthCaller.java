package py.gov.ocds.service.retrofit;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by diego on 01/05/17.
 */
public interface OAuthCaller {

  @POST("datos/api/oauth/token")
  Call<String> accesToken(@Header("Authorization") String authorization);
}
