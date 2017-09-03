package py.gov.ocds.service.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by diego on 01/05/17.
 */
public interface OcdsCaller {

  @GET("/datos/api/v2/doc/ocds/record-package/{id}") Call<String> recordPackage(
      @Header("Authorization") String authorization, @Path("id") String id);

  @GET("/datos/api/v2/doc/ocds/tender/{id}") Call<String> tender(
      @Header("Authorization") String authorization, @Path("id") String id);

  @GET("/datos/api/v2/doc/ocds/award/{id}") Call<String> award(
      @Header("Authorization") String authorization, @Path("id") String id);
}
