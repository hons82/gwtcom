package org.gwtcom.server.servlet;

import gwtupload.server.exceptions.UploadActionException;
import gwtupload.server.gae.AppEngineUploadAction;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.gwtcom.server.dao.ProfileImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;

import com.google.appengine.api.datastore.Blob;

public class FileUploadGaeServlet extends AppEngineUploadAction implements HttpRequestHandler{

	private static final long serialVersionUID = -2326059751035554124L;
	
	@Autowired
	private ProfileImageDao _profileImageDao;
	
	/**
	 * Maintain a list with received files and their content types
	 */
	Hashtable<String, File> receivedFiles = new Hashtable<String, File>();
	Hashtable<String, String> receivedContentTypes = new Hashtable<String, String>();

	public FileUploadGaeServlet() {
	    System.out.println("ImageUploadServlet init");
	}

	/**
	 * Override executeAction to save the received files in a custom place and
	 * delete this items from session.
	 */
	@Override
	public String executeAction(HttpServletRequest request,
	        List<FileItem> sessionFiles) throws UploadActionException {

	    for (FileItem item : sessionFiles) {
	        //CacheableFileItem item = (CacheableFileItem)fItem;

	        if (false == item.isFormField()) {
	            System.out.println("the name 1st:" + item.getFieldName());
	            try {
	                // You can also specify the temporary folder
	                InputStream imgStream = item.getInputStream();
	                Blob imageBlob = new Blob(IOUtils.toByteArray(imgStream));

	            } catch (Exception e) {
	                throw new UploadActionException(e.getMessage());
	            }

	        } else {

	            System.out.println("the name 2nd:" + item.getFieldName());

	            String name = item.getFieldName();
	            String value;
	            try {
	                InputStream is = item.getInputStream();

	                StringWriter writer = new StringWriter();

	                IOUtils.copy(is, writer,"UTF-8");

	                value = writer.toString(); 
	                writer.close();

	                System.out.println("parm name: " + name);
	                System.out.println("parm value: " + value + " **" + value.length());
	                System.out.println(item.getContentType());

	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                System.out.println("Error");
	                e.printStackTrace();
	            }
	        }

	        removeSessionFileItems(request);
	    }

	    return null;}

	public void setProfileImageDao(ProfileImageDao profilImageDao) {
		_profileImageDao = profilImageDao;
	}

	public ProfileImageDao getProfileImageDao() {
		return _profileImageDao;
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
