package kaizone.songmaya.jsyl.stock.data;

import org.json.JSONObject;

public class JSONResponse {
	public static final String SUCCESS = "success";
	public static final String MESSAGE = "message";
	public static final String RESPONSEDATE = "responseDate";
	public static final String RESPONSETIME = "responseTime";

	public boolean success;
	public String message;
	public String responseDate;
	public String responseTime;

	public static JSONResponse parse(JSONObject jsonObject) {
		JSONResponse response = new JSONResponse();
		response.success = jsonObject.optBoolean(JSONResponse.SUCCESS);
		response.message = jsonObject.optString(JSONResponse.MESSAGE);
		response.responseDate = jsonObject.optString(JSONResponse.RESPONSEDATE);
		response.responseTime = jsonObject.optString(JSONResponse.RESPONSETIME);
		return response;
	}

	public static JSONObject convertJson(JSONResponse obj) throws Exception {
		JSONObject json = new JSONObject();
		json.putOpt(JSONResponse.SUCCESS, obj.success);
		json.putOpt(JSONResponse.MESSAGE, obj.message);
		json.putOpt(JSONResponse.RESPONSEDATE, obj.responseDate);
		json.putOpt(JSONResponse.RESPONSETIME, obj.responseTime);
		return json;
	}

	public static JSONResponse parseJsonString(String jsonstring) {
		JSONObject jsonObject = null;
		JSONResponse response = new JSONResponse();
		try {
			jsonObject = new JSONObject(jsonstring);
			response.success = jsonObject.optBoolean(JSONResponse.SUCCESS);
			response.message = jsonObject.optString(JSONResponse.MESSAGE);
			response.responseDate = jsonObject
					.optString(JSONResponse.RESPONSEDATE);
			response.responseTime = jsonObject
					.optString(JSONResponse.RESPONSETIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public void setJSONResponse(JSONResponse response) {
		if (response == null)
			return;
		success = response.success;
		message = response.message;
		responseDate = response.responseDate;
		responseTime = response.responseTime;
	}

	public JSONResponse getJsonResponse() {
		return this;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(SUCCESS).append(":").append(success).append("\n");
		sb.append(MESSAGE).append(":").append(message).append("\n");
		return sb.toString();
	}
}
