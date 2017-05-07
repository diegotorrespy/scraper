package py.gov.ocds.factory;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by diego on 06/05/17.
 */
public class MongoClientFactory {

  public static MongoClient getMongoClient()
  {
    try {

      MongoClient mongo = new MongoClient();
      return mongo;

    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return null;
  }

}
