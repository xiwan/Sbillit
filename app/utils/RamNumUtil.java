package utils;

import java.util.Random;

public class RamNumUtil {
	private String random;
	
	private RamNumUtil() {
		init();
	}

	public static RamNumUtil Instance() {
        return new RamNumUtil();
    }
	
	public String getRandom() {
        return this.random;
    }
	
	private void init() {
		Random random = new Random();
		String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;  
        }
	    this.random = sRand;
	}
}
