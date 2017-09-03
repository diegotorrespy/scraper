package py.gov.ocds.scraper; /**
 * Created by diego on 03/04/17.
 */

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import py.gov.ocds.dao.interfaz.Dao;
import py.gov.ocds.dao.impl.ScraperDao;
import py.gov.ocds.service.impl.LicitacionesService;
import py.gov.ocds.service.impl.OCDSService;

public class Scraper {

  private static final Logger logger = LoggerFactory.getLogger(Scraper.class);

  public static void main(String[] args) {

    LicitacionesService licitaciones = new LicitacionesService();
    OCDSService ocds = new OCDSService();
    Dao dao = new ScraperDao();

    logger.warn("Recuperando licitaciones");
    JSONArray procesos = licitaciones.recuperarLicitaciones(Parametros.builder()
            .put("fecha_desde", "2010-01-01")
            .put("fecha_hasta", "2017-12-31")
            .put("tipo_fecha", "ENT")
            .put("tipo_licitacion", "tradicional")
            .put("show_pagination", "false"));

    for (int i = 0; i < procesos.length(); i++) {

      Long id_llamado = procesos.getJSONObject(i).getLong("id_llamado");
      String record = ocds.recordPackage(id_llamado.toString());

      if (record != null) {

        logger.debug("Guardando datos de {}", id_llamado);
        dao.saveFile(id_llamado.toString(), record);
      } else {

        logger.error("No se pudo recuperar el registro {}", id_llamado.toString());
      }
    }
  }

}





