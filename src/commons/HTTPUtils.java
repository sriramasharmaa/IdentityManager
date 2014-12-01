package commons;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class HTTPUtils {

	public HTTPUtils() {
		// TODO Auto-generated constructor stub
	}
	public static String buildHtmlEntityCode(String input) {
	    StringBuffer output = new StringBuffer(input.length() * 2);

	    int len = input.length();
	    int code,code1,code2,code3,code4;
	    char ch;

	    for( int i=0; i<len; ) {
	        code1 = input.codePointAt(i);
	        if( code1 >> 3 == 30 ){
	            code2 = input.codePointAt(i + 1);
	            code3 = input.codePointAt(i + 2);
	            code4 = input.codePointAt(i + 3);
	            code = ((code1 & 7) << 18) | ((code2 & 63) << 12) | ((code3 & 63) << 6) | ( code4 & 63 );
	            i += 4;
	            output.append("&#" + code + ";");
	        }
	        else if( code1 >> 4 == 14 ){
	            code2 = input.codePointAt(i + 1);
	            code3 = input.codePointAt(i + 2);
	            code = ((code1 & 15) << 12) | ((code2 & 63) << 6) | ( code3 & 63 );
	            i += 3;
	            output.append("&#" + code + ";");
	        }
	        else if( code1 >> 5 == 6 ){
	            code2 = input.codePointAt(i + 1);
	            code = ((code1 & 31) << 6) | ( code2 & 63 );
	            i += 2;
	            output.append("&#" + code + ";");
	        }
	        else {
	            code = code1;
	            i += 1;

	            ch = (char)code;
	            if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch >= '0' && ch <= '9') {
	                output.append(ch);
	            }
	            else {
	                output.append("&#" + code + ";");
	            }
	        }
	    }

	    return output.toString();
	}
	
	public static String returnChar(String fChar) throws IOException{
		StringBuffer buffer = new StringBuffer();
		Reader in = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(fChar.getBytes("ISO-8859-1"));
			InputStreamReader isr = new InputStreamReader(bais,"UTF-8");
			in = new BufferedReader(isr);
			int ch;
			while ((ch = in.read()) > -1) {
				buffer.append((char)ch);
			}
		}
		catch(Exception e){

		}
		in.close();
		return buffer.toString();

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
