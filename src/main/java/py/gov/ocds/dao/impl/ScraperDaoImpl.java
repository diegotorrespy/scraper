package py.gov.ocds.dao.impl;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.json.JSONObject;
import py.gov.ocds.factory.MongoClientFactory;

/**
 * Created by diego on 29/04/17.
 */
public class ScraperDaoImpl  {

  private MongoManager mongo = new MongoManager();

  public void guardar(String id, String clave, String dato) {
    DBObject doc = crearDocumento(id, clave, dato);
    mongo.insert(doc, "opendata", "tender");
  }

  public void guardar(String id, String record) {

    DBObject doc = mongo.createDocument(id, "record_package",record);
    mongoInsert(doc);
  }

  private DBObject crearDocumento(String id, String clave, String dato) {

    JSONObject documento = new JSONObject();
    documento.put("_id", id);
    documento.put(clave, new JSONObject(dato));

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

  public String getRecord(String id) {

    MongoClient mongo = MongoClientFactory.getMongoClient();
    DB dbManager = mongo.getDB("ocds");
    DBCollection colllection = dbManager.getCollection("data");

    BasicDBObject query = new BasicDBObject("record_package.records.0.compiledRelease.id",
        "155481-servicio-organizacion-apoyo-logistico");

    DBCursor cursor = colllection.find(query);
    String resultado = null;
    while (cursor.hasNext()) {
      resultado = cursor.next().toString();
    }

    mongo.close();

    return resultado;
  }

}
