package py.gov.ocds.modulos;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.gov.ocds.dao.iface.AwardDao;
import py.gov.ocds.dao.impl.AwardDaoImpl;
import py.gov.ocds.service.iface.OcdsService;
import py.gov.ocds.service.impl.OcdsServiceImpl;

/**
 * Created by diego on 03/08/17.
 */
public class AwardScraper {

  private static final Logger logger = LoggerFactory.getLogger(AwardScraper.class);

  private OcdsService ocds = new OcdsServiceImpl();

  AwardDao awardDao = new AwardDaoImpl();

  public void scrap(String accessToken, JSONArray procesos) {

    for (int i = 0; i < procesos.length(); i++) {
      Long idLlamado = procesos.getJSONObject(i).getLong("id_llamado");
      if (!procesos.getJSONObject(i).isNull("planificacion_id")) {

        String slug = procesos.getJSONObject(i).getString("planificacion_id");
        String award = ocds.award(accessToken, slug);
        if (award != null) {
          awardDao.guardar(idLlamado.toString(), award);
        } else {
          logger.warn("No se pudo recuperar la informacion del award para el id {} y slug {}",
              idLlamado, slug);
        }
      } else {
        logger.warn("Slug no encontrado para el id {}", idLlamado);
      }
    }
  }

}
