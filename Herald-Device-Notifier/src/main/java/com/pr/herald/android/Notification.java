package com.pr.herald.android;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pr.herald.contants.Constants;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class Notification 
{
	private static final String API_KEY = "AIzaSyDWvpor6gQhoWkCJdoiTW7vzJd6-Sf1SYc";
	private static final String FCM_URL = "https://fcm.googleapis.com/fcm/send";
	
	private List<String> deviceTokens;
	private String msgTitle;
	private String msgBody;

	Logger log = Logger.getLogger(this.getClass());
	
	public Notification(List<String> deviceTokens, String msgTitle, String msgBody) {
		super();
		this.deviceTokens = deviceTokens;
		this.msgTitle = msgTitle;
		this.msgBody = msgBody;
	}


	@SuppressWarnings("unchecked")
	public JSONObject send() throws IOException, ParseException
	{
		log.info("--- Excecuting method : send() ---");
		OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse(Constants.MEDIA_TYPE);
        JSONObject obj = new JSONObject();
        JSONObject msgObject = new JSONObject();
        msgObject.put("title", msgTitle);
        msgObject.put("body", msgBody);
//        msgObject.put("icon", ANDROID_NOTIFICATION_ICON);
//        msgObject.put("color", ANDROID_NOTIFICATION_COLOR);

        obj.put("registration_ids", deviceTokens);
        obj.put("notification",msgObject);

        RequestBody body = RequestBody.create(mediaType, obj.toString());
        Request request = new Request.Builder().url(FCM_URL).post(body)
        							 .addHeader("content-type", Constants.MEDIA_TYPE)
        							 .addHeader("authorization", "key="+API_KEY).build();

        log.info("--- Sending actual notifications ---");
        Response response = client.newCall(request).execute();
        log.info("--- Sending notifications success ---");
        
        JSONParser parser = new JSONParser();
        JSONObject resp = (JSONObject) parser.parse(response.body().string());
        
        return resp;
	}
}
