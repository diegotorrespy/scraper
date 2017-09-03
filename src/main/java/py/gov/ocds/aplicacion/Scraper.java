package py.gov.ocds.aplicacion;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.gov.ocds.modulos.ReleaseScraper;
import py.gov.ocds.parametros.Parametros;
import py.gov.ocds.service.iface.AutenticacionService;
import py.gov.ocds.service.iface.LicitacionesService;
import py.gov.ocds.service.impl.AutenticacionServiceImpl;
import py.gov.ocds.service.impl.LicitacionesServiceImpl;

/**
 * Created by diego on 06/08/17.
 */
public class Scraper {

  private static final Logger logger = LoggerFactory.getLogger(Scraper.class);

  public static void main(String[] args) {

    LicitacionesService licitaciones = new LicitacionesServiceImpl();
    ReleaseScraper releases = new ReleaseScraper();
    AutenticacionService autenticaciones = new AutenticacionServiceImpl();

    for (int i = 0; i < 10; i++) {

      String offset = String.valueOf(i * 4000);
      String limit = String.valueOf((((i + 1) * 4000) - 1));

      logger.info("Recuperando licitaciones");
      JSONArray procesos = licitaciones.recuperarLicitaciones(
          Parametros.builder().put("fecha_desde", "2017-01-01").put("fecha_hasta", "2017-07-31")
              .put("tipo_fecha", "ENT").put("tipo_licitacion", "tradicional").put("offset", offset)
              .put("limit", limit));

      releases.scrap(autenticaciones.accessToken(), procesos);

    }
  }
}
