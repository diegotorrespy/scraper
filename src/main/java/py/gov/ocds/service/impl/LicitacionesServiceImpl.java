package py.gov.ocds.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.gov.ocds.factory.RetrofitBuilder;
import py.gov.ocds.parametros.Parametros;
import py.gov.ocds.service.iface.LicitacionesService;
import py.gov.ocds.service.retrofit.BuscadoresCaller;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by diego on 06/08/17.
 */
public class LicitacionesServiceImpl implements LicitacionesService {

  private static final Logger logger = LoggerFactory.getLogger(LicitacionesServiceImpl.class);

  BuscadoresCaller service = RetrofitBuilder.build().create(BuscadoresCaller.class);

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
