package py.gov.ocds.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.gov.ocds.service.interfaz.OCDSServiceInterface;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by diego on 01/05/17.
 */
public class OCDSService extends BaseService{

  private static final Logger logger = LoggerFactory.getLogger(OCDSService.class);

  OCDSServiceInterface service = retrofit.create(OCDSServiceInterface.class);

  public String recordPackage(String id) {
    String recordPackage = null;
    try {

      Call<String> recordPackageReq = null;
      Integer intentos = 0;
      do {

        intentos++;
        recordPackageReq = service.recordPackage(id);

        Response res = recordPackageReq.execute();

        if (res.body() != null) {
          recordPackage = res.body().toString();
        } else {
          recordPackage = null;
        }

      } while (recordPackage == null && intentos < 100);

      if (intentos >= 70) {
        logger.error("Error al intentar consultar el id {}. Intentos: {}", id, intentos);
      }

      return recordPackage;

    } catch (Exception e) {
      logger.error("Error al consultar el record package", e);
    }

    return recordPackage;
  }


}
