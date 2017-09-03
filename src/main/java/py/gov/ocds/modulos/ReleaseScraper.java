package py.gov.ocds.modulos;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.gov.ocds.dao.iface.ReleaseDao;
import py.gov.ocds.dao.impl.ReleaseDaoImpl;
import py.gov.ocds.service.iface.OcdsService;
import py.gov.ocds.service.impl.OcdsServiceImpl;

/**
 * Created by diego on 03/08/17.
 */
public class ReleaseScraper {

  private static final Logger logger = LoggerFactory.getLogger(ReleaseScraper.class);

  private OcdsService ocds = new OcdsServiceImpl();
  private ReleaseDao releaseDao = new ReleaseDaoImpl();

  public void scrap(String accessToken,JSONArray procesos) {

    for (int i = 0; i < procesos.length(); i++) {

      Long idLlamado = procesos.getJSONObject(i).getLong("id_llamado");

      String release = ocds.release(accessToken,idLlamado.toString());

      if (release != null) {
        releaseDao.guardar(idLlamado.toString(), release);
      } else {
        logger.error("No se pudo recuperar el registro {}", idLlamado.toString());
      }
    }
  }
}
