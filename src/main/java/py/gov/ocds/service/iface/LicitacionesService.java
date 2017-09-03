package py.gov.ocds.service.iface;

import org.json.JSONArray;
import py.gov.ocds.parametros.Parametros;

/**
 * Created by diego on 06/08/17.
 */
public interface LicitacionesService {

  JSONArray recuperarLicitaciones(Parametros criterios);

}
