package id.padiku.app.exception;

public class ExporterException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExporterException(String message) {
		super(message);
	}
	
	public ExporterException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
