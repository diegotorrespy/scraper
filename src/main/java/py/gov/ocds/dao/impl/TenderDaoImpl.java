package py.gov.ocds.dao.impl;

import py.gov.ocds.dao.iface.TenderDao;

/**
 * Created by diego on 06/08/17.
 */
public class TenderDaoImpl implements TenderDao {

  private MongoManager mongo = new MongoManager();

  public void guardar(String id, String tender) {
    mongo.insert(mongo.createDocument(id, "tender", tender), "opendata", "tender");
  }
}
