package avatar.utils;

import java.util.*;

/**
 * ����list������,����map ��ѭ����ʱ����Ը��ݷ����˳������, ��ʵ��map����
 */
public class ListMap {

	List keyArr = null;
	Map hash = null;

	/** ������ */
	public ListMap() {

		keyArr = new LinkedList();
		hash = new HashMap();

	}

	public Object put(Object key, Object object) {
		Object haveObj = this.hash.get(key);
		if (haveObj == null) {
			keyArr.add(key);
		} else {
			keyArr.remove(key);
			keyArr.add(key);
		}
		this.hash.put(key, object);
		return haveObj;
	}

	public Object get(Object key) {
		return this.hash.get(key);
	}

	public List keys() {
		return this.keyArr;
	}

	public void clear() {
		this.keyArr.clear();
		this.hash.clear();
	}

	public Object clone() {
		ListMap tem = new ListMap();
		Iterator keyItr = keyArr.iterator();
		while (keyItr.hasNext()) {
			Object key = keyItr.next();
			tem.put(key, this.get(key));
		}
		return tem;
	}

	public boolean isEmpty() {
		return this.hash.isEmpty();
	}

	public Object remove(Object object) {
		Object reObj = this.hash.remove(object);
		this.keyArr.remove(object);
		return reObj;
	}

	public int size() {
		return this.keyArr.size();
	}

	public void putAll(ListMap listMap) {
		if (listMap == null)
			return;
		List temKeys = listMap.keyArr;
		for (int i = 0; i < temKeys.size(); i++) {
			this.put(temKeys.get(i), listMap.get(temKeys.get(i)));
		}
	}

}
