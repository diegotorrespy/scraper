package py.gov.ocds.service.impl;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.gov.ocds.aplicacion.Aplicacion;
import py.gov.ocds.service.interfaz.AutenticacionServiceInterface;
import retrofit2.Call;

import java.io.IOException;

/**
 * Created by diego on 01/05/17.
 */
public class AutenticacionService extends BaseService {

  private static final Logger logger = LoggerFactory.getLogger(AutenticacionService.class);

  AutenticacionServiceInterface service = retrofit.create(AutenticacionServiceInterface.class);

  public String accessToken() {

    try {

      Call<String> autenticacion = service.accesToken("Basic " + Aplicacion.REQUEST_TOKEN);
      JSONObject token = new JSONObject(autenticacion.execute().body().toString());

      return "Bearer " + token.getString("access_token");

    } catch (IOException e) {
      logger.error("Ocurrio un error en el servicio de autorizacion",e);
    }
    return null;
  }


}
