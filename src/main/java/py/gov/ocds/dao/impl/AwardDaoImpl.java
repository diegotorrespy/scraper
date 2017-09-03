package py.gov.ocds.dao.impl;

import py.gov.ocds.dao.iface.AwardDao;

/**
 * Created by diego on 06/08/17.
 */
public class AwardDaoImpl implements AwardDao {

  private MongoManager mongo = new MongoManager();

  public void guardar(String id, String award) {
    mongo.insert(mongo.createDocument(id,"award", award), "opendata", "award");
  }
}
