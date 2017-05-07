package py.gov.ocds.dao.impl;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.json.JSONObject;
import py.gov.ocds.dao.interfaz.Dao;
import py.gov.ocds.factory.MongoClientFactory;

/**
 * Created by diego on 29/04/17.
 */
public class ScraperDao implements Dao {

  public void guardar(String id, String record) {

    DBObject doc = crearDocumento(id, record);
    mongoInsert(doc);
  }

  private DBObject crearDocumento(String id, String record) {

    JSONObject recordPackage = new JSONObject(record);

    JSONObject documento = new JSONObject();
    documento.put("_id", id);
    documento.put("record_package", recordPackage);

    return (DBObject) JSON.parse(documento.toString());
  }

  private WriteResult mongoInsert(DBObject doc) {
    MongoClient mongo = MongoClientFactory.getMongoClient();
    DB dbManager = mongo.getDB("opendata");
    DBCollection colllection = dbManager.getCollection("ocds");
    WriteResult result = colllection.insert(doc);
    mongo.close();

    return result;
  }
}
