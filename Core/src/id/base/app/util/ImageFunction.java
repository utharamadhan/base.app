package id.base.app.util;

import id.base.app.SystemConstant;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class ImageFunction {

	public static BufferedImage resize(File file, int w, int h) throws IOException{
		BufferedImage in = ImageIO.read(file);
		int type = in.getType() == 0? BufferedImage.TYPE_INT_ARGB : in.getType();
		double outputAspect = 1.0*w/h;
        double inputAspect = 1.0*in.getWidth()/in.getHeight();
        if (outputAspect > inputAspect) {
        	h = (int)(w/inputAspect);
        } else {
        	w = (int)(h*inputAspect);
        }
        BufferedImage bi = new BufferedImage(w, h, type);
        Graphics2D g2 = bi.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(in, 0, 0, w, h, null);
        g2.dispose();
        return bi;
	}
	
	public static BufferedImage crop(File file, int w, int h) throws IOException {
		BufferedImage in = ImageIO.read(file);
		int type = in.getType() == 0? BufferedImage.TYPE_INT_ARGB : in.getType();
        int x = in.getWidth()/2 - w/2;
        int y = in.getHeight()/2 - h/2;
        
        BufferedImage clipping = new BufferedImage(w, h, type);  
        Graphics2D area = (Graphics2D) clipping.getGraphics().create();  
        area.drawImage(in, 0, 0, clipping.getWidth(), clipping.getHeight(), x, y, x + clipping.getWidth(),  
            y + clipping.getHeight(), null);  
        area.dispose();
        return clipping;
    }
	
	public static String createThumbnails(String imageURL, int w, int h) throws IOException{
		String fileSystemURL = SystemConstant.FILE_STORAGE + imageURL.substring(SystemConstant.IMAGE_SHARING_URL.length(), imageURL.length());
		File file = new File(fileSystemURL);
		BufferedImage bi = resize(file, w, h);
		String ext = fileSystemURL.substring(fileSystemURL.lastIndexOf(".")+1);
        String out = fileSystemURL.replaceFirst(".([a-z]+)$", "_temp." + ext);
        ImageIO.write(bi, ext, new File(out));
        File fileCrop = new File(out);
        BufferedImage biCrop = crop(fileCrop, w, h);
        String outCrop = fileSystemURL.replaceFirst(".([a-z]+)$", "_thumb." + ext);
        ImageIO.write(biCrop, ext, new File(outCrop));
        Path path = Paths.get(out);
        Files.deleteIfExists(path);
        return imageURL.replaceFirst(".([a-z]+)$", "_thumb." + ext);
	}
	
}
