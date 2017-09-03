package py.gov.ocds.dao.iface;

/**
 * Created by diego on 06/05/17.
 */
public interface ScraperDao {

  /**
   * Se encarga de persistir un record package en la base de datos especificando un id unico.
   * @param id
   * @param record
   */
  void guardar(String id, String record);

  String getRecord(String id);

  void guardar(String id, String clave, String dato);



}
