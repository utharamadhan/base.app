package id.base.app.encryptor;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.binary.Base64;

import id.base.app.CharEncConsts;
import id.base.app.SystemConstant;

public final class EncodeDecode {

	private EncodeDecode() { }
	
	private static BinaryDecoder getDecoder() {
		return new Base64();
	}

	private static BinaryEncoder getEncoder() {
		return new Base64();
	}
	
	public static byte[] decode(final byte[] bytes) {
		byte[] _ret = (byte[]) null; // NOPMD by Mardy Jonathan
		try {
			_ret = EncodeDecode.getDecoder().decode(bytes);
		} catch (final Throwable t) { // NOPMD by Mardy Jonathan
			t.printStackTrace(); // NOPMD by Mardy Jonathan
		}
		
		return _ret;
	}

	public static String decodeString(final String data) {
		return new String(EncodeDecode.decode(data.getBytes(CharEncConsts.DEFAULT_CHARSET)));
	}
	
	public static byte[] encode(final byte[] bytes) {
		byte[] _ret = (byte[]) null; // NOPMD by Mardy Jonathan
		try {
			_ret = EncodeDecode.getEncoder().encode(bytes);
		} catch (final Throwable t) { // NOPMD by Mardy Jonathan
			t.printStackTrace(); // NOPMD by Mardy Jonathan
		}
		
		return _ret;
	}

	public static String encodeString(final String data) {
		return new String(EncodeDecode.encode(data.getBytes(CharEncConsts.DEFAULT_CHARSET)));
	}
	
	public static String getExt(String file){
		String[] nameFiles = file.split("\\.");
		return nameFiles[nameFiles.length-1];
	}
	
	public static String getBase64FromLink(String inputURL) throws Exception{
		 
		  try {
			String newUrl = inputURL.replace("\\", "/");
		    String ext = EncodeDecode.getExt(newUrl); 
		    URL imageURL = new URL(newUrl);
		    InputStream is = imageURL.openStream();

		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		   
		    byte[] byteChunk = new byte[4096]; 
		    int n;
		    while ( (n = is.read(byteChunk)) > 0 ) {
		      baos.write(byteChunk, 0, n);
		    }
		   
		    StringBuilder builder = new StringBuilder();
		    builder.append(String.format(SystemConstant.B64_PREFIX, ext));
		    String output = builder.toString()+new String(EncodeDecode.encode(baos.toByteArray()));
		   
		   
		    baos.close();
		    is.close();
		     
		    return output;
		     
		  } catch (Exception e) {
		      e.printStackTrace();
		      throw e;
		  }
		 
		}
	
	public static void main(final String[] args) {
		System.out.println(new String(encode("password".getBytes()))); // NOPMD by Mardy Jonathan
		System.out.println(new String(decode("cGFzc3dvcmQ=".getBytes()))); // NOPMD by Mardy Jonathan
	}
}
