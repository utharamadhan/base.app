/**
 * 
 */
package id.base.app.site.captcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octo.captcha.service.CaptchaServiceException;

/**
 * @author Mardy
 *
 */
public class ImageCaptchaServlet extends HttpServlet {

	public void init(ServletConfig servletConfig) throws ServletException {
		 
        super.init(servletConfig);
 
    }
 
 
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
 
       byte[] captchaChallengeAsJpeg = null;
       // the output stream to render the captcha image as jpeg into
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
        // get the session id that will identify the generated captcha.
        //the same id must be used to validate the response, the session id is a good candidate!
        String captchaId = "324730C84E3478647CF92135124FAD0A";
        httpServletRequest.getSession().setAttribute("captchaId", captchaId);
        // call the ImageCaptchaService getChallenge method
            BufferedImage challenge =
                    CaptchaServiceSingleton.getInstance().getImageChallengeForID(captchaId,
                            httpServletRequest.getLocale());
 
            // a jpeg encoder
            ImageWriter imageWriter = (ImageWriter)ImageIO.getImageWritersBySuffix("jpeg").next();
            ImageOutputStream ios = ImageIO.createImageOutputStream(jpegOutputStream);
            imageWriter.setOutput(ios);
            IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(challenge), null);
            imageWriter.write(imageMetaData, new IIOImage(challenge, null, null), null);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (CaptchaServiceException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
 
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
 
        // flush it in the response
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
