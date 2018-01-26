package services;

import java.util.ArrayList;
import java.util.List;
import net.sf.ehcache.CacheManager;
import utils.HibernateUtil;

public class TestsService {

	
	public static String[] checkIfLevelTwoIsUp(){
		try {
		StringBuilder tmp = new StringBuilder();
		ArrayList<String> arrayString = new ArrayList<String>();
		
		tmp.append("# OF HIT" + HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCacheHitCount()+"<br>\n");
		tmp.append("# OF MISS" + HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCacheMissCount()+"<br>\n");
		tmp.append("# OF PUT" + HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCachePutCount()+"<br>\n");
		
		
		List<CacheManager> tempManagers = CacheManager.ALL_CACHE_MANAGERS;
		tmp.append("# of CMs : " + tempManagers.size()+"<br>\n");
		for (CacheManager tempCM : tempManagers) {
			tmp.append("Name: " + tempCM.getName()+"<br>\n");
			tmp.append("Disk path: " + tempCM.getDiskStorePath()+"<br>\n");
			tmp.append("Status: " + tempCM.getStatus()+"<br>\n");
			tmp.append("Cache names : <br>\n");
			String[] cacheNames = HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCacheRegionNames();
			for (int i = 0; i < cacheNames.length; i++) {
				 tmp.append("SECOND    -> "+cacheNames[i]+"<br>\n");
			}
			tmp.setLength(tmp.length() - 1);
			
			arrayString.add(tmp.toString());
			System.out.println(tmp.toString());
			tmp = new StringBuilder();
			
		}

		String[] tmpTab = new String[arrayString.size()];
		return arrayString.toArray(tmpTab);
		}catch(Exception ignored) {}
		return new String[]{"Avez-vous crée un contact ?<br>"};
	
	}
}
