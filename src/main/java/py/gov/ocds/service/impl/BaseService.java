package py.gov.ocds.service.impl;

import okhttp3.OkHttpClient;
import py.gov.ocds.aplicacion.Aplicacion;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by diego on 06/05/17.
 */
public abstract class BaseService {

  protected Retrofit retrofit = new Retrofit.Builder()
          .baseUrl(Aplicacion.BASE_URL)
          .addConverterFactory(ScalarsConverterFactory.create())
          .client(getClient())
          .build();

  protected OkHttpClient getClient() {

    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.connectTimeout(5, TimeUnit.MINUTES);
    builder.readTimeout(5, TimeUnit.MINUTES);

    return builder.build();
  }
}
