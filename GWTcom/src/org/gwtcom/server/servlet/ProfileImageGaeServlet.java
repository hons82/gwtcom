package org.gwtcom.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gwtcom.server.dao.ProfileImageDao;
import org.gwtcom.server.domain.ProfileImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;

public class ProfileImageGaeServlet implements HttpRequestHandler {

	@Autowired
	private ProfileImageDao _profilImageDao;

	public ProfileImageGaeServlet() {
		System.out.println("ProfileImageGaeServlet init");
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		ServletOutputStream oStream = response.getOutputStream();
		String requestid = request.getParameter("id");
		System.out.println("ProfileImageGaeServlet id:" + requestid);
		if (requestid != null && !requestid.isEmpty()) {
			// && requestid.matches("[0-9]*")
			ProfileImage profileImage = _profilImageDao.retrieve(requestid);
			if (profileImage != null && profileImage.getPicture() != null) {
				// TODO real content type
				response.setContentType("image/jpeg");
				response.setContentLength(profileImage.getPicture().length);

				oStream.write(profileImage.getPicture());

			}
		}
		response.setContentType("text/html");
		oStream.flush();
		oStream.close();
	}

	public void setProfilImageDao(ProfileImageDao profilImageDao) {
		_profilImageDao = profilImageDao;
	}

	public ProfileImageDao getProfilImageDao() {
		return _profilImageDao;
	}

}
