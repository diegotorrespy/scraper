package py.gov.ocds.dao.impl;

import py.gov.ocds.dao.iface.ReleaseDao;

/**
 * Created by diego on 06/08/17.
 */
public class ReleaseDaoImpl implements ReleaseDao {

  private MongoManager mongo = new MongoManager();

  public void guardar(String id, String record) {

    mongo.insert(mongo.createDocument(id,"record_package", record), "opendata", "release");
  }

}
