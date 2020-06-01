package ktp_lab7;

import java.net.URLConnection;
import java.util.Objects;
/** Программа должна хранить URL-адрес в виде строки вместе с его
глубиной (которая для начала будет равна 0). 
Вам будет необходимо создать
класс для представления пар [URL, depth]  */

public class URLDepthPair {

	private String URL; //поле, представляющее поле
	private int  Depth=0; //поле, предоставляющее глубину поиска
	/** конструктор */
	public URLDepthPair(String host, int depth_h) {
		URL = host;
		Depth = depth_h;
	}
	/**метод toString, который выводит содержимое пары.*/
	public String ToString () {
		return (URL + "," + Depth);
	}
	
    public String getURL() {
        return URL;
    }

    public int getDepth() {
        return Depth;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof URLDepthPair) {
            URLDepthPair o = (URLDepthPair)obj;
            return this.URL.equals(o.getURL());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

}
