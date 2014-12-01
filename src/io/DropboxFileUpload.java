package io;
	 import com.dropbox.client2.DropboxAPI;  
import com.dropbox.client2.DropboxAPI.DropboxFileInfo;  
import com.dropbox.client2.DropboxAPI.Entry;  
import com.dropbox.client2.exception.DropboxException;  
import com.dropbox.client2.session.AccessTokenPair;  
import com.dropbox.client2.session.AppKeyPair;  
import com.dropbox.client2.session.RequestTokenPair;  
import com.dropbox.client2.session.Session.AccessType;  
import com.dropbox.client2.session.WebAuthSession;  
import com.dropbox.client2.session.WebAuthSession.WebAuthInfo;  
import java.awt.Desktop;  
import java.awt.HeadlessException;
import java.io.ByteArrayInputStream;  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;  
import javax.swing.JOptionPane;  
import io.FileOperations;
	 /**  
	  *  
	  * @author pranay  
	  */  
	 public class DropboxFileUpload {
	  // private static final String APP_KEY = "3jjicbndzki5idz";  
	   private static final String APP_SECRET = "xlmamtrc18tojzo";  
	   private static final String APP_KEY = "t2mnzcqj0tybtvcx";
	   private static final AccessType ACCESS_TYPE = AccessType.APP_FOLDER;  
	   private static DropboxAPI<WebAuthSession> mDBApi;  
	   public  DropboxFileUpload(String passFile) throws Exception  {  
	     try {
			AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);  
			 WebAuthSession session = new WebAuthSession(appKeys, ACCESS_TYPE);  
			 WebAuthInfo authInfo = session.getAuthInfo();  
			 RequestTokenPair pair = authInfo.requestTokenPair;  
			 String url = authInfo.url;  
			 Desktop.getDesktop().browse(new URL(url).toURI());  

			 JOptionPane.showMessageDialog(null, "Press ok to continue once you have authenticated.");  
			 session.retrieveWebAccessToken(pair);  
			 AccessTokenPair tokens = session.getAccessTokenPair();  
			 System.out.println("Use this token pair in future so you don't have to re-authenticate each time:");  
			 System.out.println("Key token: " + tokens.key);  
			 System.out.println("Secret token: " + tokens.secret);  
			 mDBApi = new DropboxAPI<WebAuthSession>(session);  
			 System.out.println();  
			 System.out.print("Uploading file...");  
			 String fileContents = new String(FileOperations.read(new File(passFile)));
			 ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContents.getBytes());  
			 Entry newEntry = mDBApi.putFile("/pass.log", inputStream, fileContents.length(), null, null);  
			 System.out.println("Done. \nRevision of file: " + newEntry.rev);  
			 FileOutputStream outputStream = null;  
			 File file = new File("c:/temp/file.txt");  
			 outputStream = new FileOutputStream(file);  
			 DropboxFileInfo info = mDBApi.getFile("/pass.log", null, outputStream, null);
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	 }   
	 } 


