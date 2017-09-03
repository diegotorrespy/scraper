package py.gov.ocds.factory;

import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.gov.ocds.modulos.AwardScraper;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by diego on 06/08/17.
 */

public class RetrofitBuilder {

  private static final Logger logger = LoggerFactory.getLogger(AwardScraper.class);

  public static Retrofit build() {

    try {
      Properties aplicacion = new Properties();
      aplicacion.load(RetrofitBuilder.class.getResourceAsStream("/aplicacion.properties"));

      OkHttpClient.Builder builder = new OkHttpClient.Builder();
      builder.connectTimeout(5, TimeUnit.MINUTES);
      builder.readTimeout(5, TimeUnit.MINUTES);
      OkHttpClient client = builder.build();

      Retrofit retrofit = new Retrofit.Builder().baseUrl(aplicacion.getProperty("request.base.url"))
          .addConverterFactory(ScalarsConverterFactory.create()).client(client).build();

      return retrofit;

    } catch (IOException e) {

      logger.error("Error al crear el objeto invocador retrofit");

      return null;
    }
  }
}
