package py.gov.ocds.scraper;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Permite la definición de parámetros para las consultas que así lo requieran
 */
public class Parametros {

  private Map<String, String> datos;

  private Parametros() {
    datos = new HashMap<>();
  }

  public static Parametros builder() {
    return new Parametros();
  }

  public Map<String, String> build() {
    return datos;
  }

  public Set<Entry<String, String>> entrySet() {
    return datos.entrySet();
  }

  public Object get(final String key) {
    return datos.get(key);
  }

  public Set<String> keySet() {
    return datos.keySet();
  }

  public Parametros put(final String key, final String value) {
    datos.put(key, value);
    return this;
  }

  public Parametros putAll(final Map<String, String> values) {
    datos.putAll(values);
    return this;
  }

  public boolean containsKey(final String key) {
    return datos.containsKey(key);
  }
}
