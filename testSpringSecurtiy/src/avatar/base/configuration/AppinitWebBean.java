/*
 此类存放系统运行的各种信息
 */

package avatar.base.configuration;

import java.util.*;


public class AppinitWebBean {
  private static final String ID = "id";
  private static final String VALUE = "name";
  private static final String HASHMAP = "ht";
  private static HashMap htOPtions = new HashMap();
  private static HashMap htInitKeyValues = new HashMap();

  private static HashMap mappings = null;

  //得到列表的HashMap
  public static HashMap getOption(String strName) {
    HashMap ht = (HashMap) htOPtions.get(strName + HASHMAP);
    if (ht == null) {
      ht = new HashMap();
    }
    return ht;
  }

  public static String[] getOptionID(String strName) {
    String[] id = (String[]) htOPtions.get(strName + ID);
    return id;
  }

  public static String[] getOptionValue(String strName) {
    String[] value = (String[]) htOPtions.get(strName + VALUE);
    return value;
  }

  public static void addOption(String strName, String[] id, String[] value) {
    htOPtions.put(strName + ID, id);
    htOPtions.put(strName + VALUE, value);

    HashMap ht = new HashMap();
    for (int i = 0; i < id.length; i++) {
      ht.put(id[i], value[i]);
    }
    htOPtions.put(strName + HASHMAP, ht);
  }

  public static void removeOption(String strName) {
    htOPtions.remove(strName + ID);
    htOPtions.remove(strName + VALUE);
  }

  public static void removeAllOptions() {
    htOPtions.clear();
  }

  /////////////////////////
  //InitKeyValues
  public static void addInitKeyValue(String strId, String strValue) {
    htInitKeyValues.put(strId, strValue);
  }

  public static void removeInitKeyValue(String strId) {
    htInitKeyValues.remove(strId);
  }

  public static void removeAllInitKeyValues() {
    htInitKeyValues.clear();
  }

  public static String getInitKeyValue(String strId) {
    return (String) htInitKeyValues.get(strId);
  }

  //得到转向的映射
  public static HashMap getMappings() {
    return mappings;
  }

  public static void setMappings(HashMap mappings) {
    AppinitWebBean.mappings = mappings;
  }
  public static String getOptionValueById(String tagName, String id) {
       if (id == null) {
           return "";
       }
       HashMap hs = getOption(tagName);
       if (hs.get(id) != null) {
         return (String) hs.get(id);
       }
       else {
         return "";
       }

   }

}
