package py.gov.ocds.modulos;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.gov.ocds.dao.iface.TenderDao;
import py.gov.ocds.dao.impl.TenderDaoImpl;
import py.gov.ocds.service.iface.OcdsService;
import py.gov.ocds.service.impl.OcdsServiceImpl;

/**
 * Created by diego on 03/08/17.
 */
public class TenderScraper {

  private static final Logger logger = LoggerFactory.getLogger(TenderScraper.class);

  private OcdsService ocds = new OcdsServiceImpl();

  TenderDao tenderDao = new TenderDaoImpl();

  public void scrap(String accessToken, JSONArray procesos) {

    for (int i = 0; i < procesos.length(); i++) {
      Long idLlamado = procesos.getJSONObject(i).getLong("id_llamado");
      if (!procesos.getJSONObject(i).isNull("planificacion_id")) {

        String slug = procesos.getJSONObject(i).getString("planificacion_id");
        String tender = ocds.tender(accessToken, slug);
        if (tender != null) {
          tenderDao.guardar(idLlamado.toString(), tender);
        } else {
          logger.warn("No se pudo recuperar la informacion del tender para el id {} y slug {}",
              idLlamado, slug);
        }
      } else {
        logger.warn("Slug no encontrado para el id {}", idLlamado);
      }
    }
  }

}
