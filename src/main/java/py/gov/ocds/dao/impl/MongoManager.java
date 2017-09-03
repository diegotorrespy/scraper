package py.gov.ocds.dao.impl;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.json.JSONObject;
import py.gov.ocds.factory.MongoClientFactory;

/**
 * Created by diego on 06/08/17.
 */
public class MongoManager {

  public DBObject createDocument(String id, String key, String data) {

    JSONObject documento = new JSONObject();
    documento.put("_id", id);
    documento.put(key, new JSONObject(data));

    return (DBObject) JSON.parse(documento.toString());
  }

  public WriteResult insert(DBObject doc, String dbname, String collection) {
    MongoClient mongo = MongoClientFactory.getMongoClient();
    DB dbManager = mongo.getDB(dbname);
    DBCollection colllection = dbManager.getCollection(collection);
    WriteResult result = colllection.insert(doc);
    mongo.close();

    return result;
  }

}
