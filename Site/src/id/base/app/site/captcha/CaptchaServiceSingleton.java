/**
 * 
 */
package id.base.app.site.captcha;

import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * @author Mardy
 *
 */
public class CaptchaServiceSingleton {

	private static ImageCaptchaService instance = new DefaultManageableImageCaptchaService();
	 
    public static ImageCaptchaService getInstance(){
        return instance;
    }
}
