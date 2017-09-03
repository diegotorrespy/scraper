package py.gov.ocds.service.impl;

import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.gov.ocds.factory.RetrofitBuilder;
import py.gov.ocds.service.iface.OcdsService;
import py.gov.ocds.service.retrofit.OcdsCaller;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by diego on 06/08/17.
 */
public class OcdsServiceImpl implements OcdsService {

  private static final Logger logger = LoggerFactory.getLogger(OcdsServiceImpl.class);

  OcdsCaller ocdsCaller = RetrofitBuilder.build().create(OcdsCaller.class);

  public String release(String accessToken, String id) {

    String recordPackage = null;
    try {
      Call<String> recordPackageReq = null;
      recordPackageReq = ocdsCaller.recordPackage("Bearer ".concat(accessToken), id);
      Response res = recordPackageReq.execute();

      if (HttpStatus.OK_200 == res.code()) {
        if (res.body() != null) {
          recordPackage = res.body().toString();
        } else {
          logger.error("Response Ok pero body nulo {}", id);
        }
      } else {
        logger.warn("Response code not ok: {}, id: {}", res.code(), id);
      }

      if (HttpStatus.NOT_FOUND_404 == res.code()) {
        logger.info("El record package no se pudo recuperar para le id {}", id);
      }
      if (HttpStatus.INTERNAL_SERVER_ERROR_500 == res.code()) {
        logger.error(
            "Ocurrio un error en el servidor al intentar recuperar el record package para le id {}",
            id);
      }

    } catch (IOException e) {
      logger.info("Error al intentar consultar el servicio de record-package para el id", id);
    }

    return recordPackage;

  }

  public String tender(String accessToken, String id) {
    String tender = null;
    try {

      Call<String> tenderReq = ocdsCaller.tender("Bearer ".concat(accessToken), id);

      Response res = tenderReq.execute();

      return parseResponse(res, id);

    } catch (IOException e) {
      logger.error("Error al consultar el servicio de tender", e);
    }

    return tender;
  }

  public String award(String accessToken, String id) {

    String award = null;
    try {

      Call<String> awardReq = ocdsCaller.award("Bearer ".concat(accessToken), id);
      Response res = awardReq.execute();

      return parseResponse(res, id);

    } catch (IOException e) {
      logger.error("Error al consultar el servicio de award", e);
    }
    return award;
  }

  private String parseResponse(Response res, String id) {
    String resultado = null;
    if (HttpStatus.OK_200 == res.code()) {
      if (res.body() != null) {
        resultado = res.body().toString();
      }
    } else if (HttpStatus.NOT_FOUND_404 == res.code()) {

    } else {
      logger.warn("Response code not ok: {}, id: {}", res.code(), id);
    }
    return resultado;
  }
}
