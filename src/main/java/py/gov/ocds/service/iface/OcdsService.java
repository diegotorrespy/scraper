package py.gov.ocds.service.iface;

/**
 * Created by diego on 06/08/17.
 */
public interface OcdsService {

  String release(String accessToken, String id);
  String tender(String accessToken, String id);
  String award(String accessToken, String id);

}
