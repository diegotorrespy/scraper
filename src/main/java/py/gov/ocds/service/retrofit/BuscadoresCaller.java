package py.gov.ocds.service.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.Map;

/**
 * Created by diego on 01/05/17.
 */
public interface BuscadoresCaller {

  @GET("datos/api/v2/doc/buscadores/licitaciones")
  Call<String> licitaciones(@QueryMap Map<String, String> opciones);
}
