package py.gov.ocds.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.gov.ocds.scraper.Parametros;
import py.gov.ocds.service.interfaz.LicitacionesServiceInterface;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by diego on 01/05/17.
 */
public class LicitacionesService extends BaseService{

  private static final Logger logger = LoggerFactory.getLogger(LicitacionesService.class);

  LicitacionesServiceInterface service = retrofit.create(LicitacionesServiceInterface.class);

  public JSONArray recuperarLicitaciones(Parametros criterios) {

    try {

      Call<String> licitaciones = service.licitaciones(criterios.build());

      Response res = licitaciones.execute();

      if (res.body() != null) {
        return listarLicitaciones(res.body().toString());
      }

    } catch(Exception e) {
      logger.error("Error al consultar el servicio de licitaciones");
    }
    return null;
  }

  public JSONArray listarLicitaciones(String jsonld)
  {
    JSONObject obj = new JSONObject(jsonld);

    JSONArray graph = obj.getJSONArray("@graph");
    JSONObject list = graph.getJSONObject(0);
    JSONArray licitaciones = list.getJSONArray("list");

    return licitaciones;
  }
}
