package net.matmas.pnapi.xml;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author matmas
 */
public class IdFromObject {
	
	private int id = 0;
	private Map<Object, Integer> map = new HashMap<Object, Integer>();
	
	public String getId(Object object) {
		if (map.containsKey(object)) {
			return map.get(object).toString();
		}
		else {
			map.put(object, id);
			return Integer.toString(id++);
		}
	}
}
