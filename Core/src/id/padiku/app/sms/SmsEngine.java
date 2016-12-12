package id.padiku.app.sms;

public interface SmsEngine {
	void sendSms(String number, Long smsTemplate, final Object objModel);
}
