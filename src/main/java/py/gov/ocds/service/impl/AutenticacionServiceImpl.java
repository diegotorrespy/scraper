package py.gov.ocds.service.impl;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.gov.ocds.factory.RetrofitBuilder;
import py.gov.ocds.service.iface.AutenticacionService;
import py.gov.ocds.service.retrofit.OAuthCaller;
import retrofit2.Call;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by diego on 06/08/17.
 */
public class AutenticacionServiceImpl implements AutenticacionService {

  private static final Logger logger = LoggerFactory.getLogger(AutenticacionServiceImpl.class);

  OAuthCaller service = RetrofitBuilder.build().create(OAuthCaller.class);

  public String accessToken() {

    Properties aplicacion = new Properties();

    try {

      Call<String> autenticacion = service.accesToken("Basic " + aplicacion.getProperty("request.token"));
      JSONObject token = new JSONObject(autenticacion.execute().body().toString());

      return token.getString("access_token");

    } catch (IOException e) {
      logger.error("Ocurrio un error en el servicio de autorizacion", e);
    }
    return null;
  }

}
