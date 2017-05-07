package py.gov.ocds.service.interfaz;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by diego on 01/05/17.
 */
public interface OCDSServiceInterface {

  @GET("/datos/api/v2/doc/ocds/record-package/{id}")
  Call<String> recordPackage(@Path("id") String id);
}
