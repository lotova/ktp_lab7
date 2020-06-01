package ktp_lab7;

import java.net.URLConnection;
import java.util.Objects;
/** ��������� ������ ������� URL-����� � ���� ������ ������ � ���
�������� (������� ��� ������ ����� ����� 0). 
��� ����� ���������� �������
����� ��� ������������� ��� [URL, depth]  */

public class URLDepthPair {

	private String URL; //����, �������������� ����
	private int  Depth=0; //����, ��������������� ������� ������
	/** ����������� */
	public URLDepthPair(String host, int depth_h) {
		URL = host;
		Depth = depth_h;
	}
	/**����� toString, ������� ������� ���������� ����.*/
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
