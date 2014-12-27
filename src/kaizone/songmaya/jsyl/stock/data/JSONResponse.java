
package kaizone.songmaya.jsyl.stock.data;

import org.json.JSONObject;

public class JSONResponse {
    public static final String SUCCESS = "success";
    public static final String MESSAGE = "message";

    public boolean success;
    public String message;

    public static JSONResponse parse(JSONObject jsonObject) {
        JSONResponse response = new JSONResponse();
        response.success = jsonObject.optBoolean(JSONResponse.SUCCESS);
        response.message = jsonObject.optString(JSONResponse.MESSAGE);
        return response;
    }

    public static JSONResponse parseJsonString(String jsonstring) {
        JSONObject jsonObject = null;
        JSONResponse response = new JSONResponse();
        try {
            jsonObject = new JSONObject(jsonstring);
            response.success = jsonObject.optBoolean(JSONResponse.SUCCESS);
            response.message = jsonObject.optString(JSONResponse.MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public void setJSONResponse(JSONResponse response) {
        if(response == null)
            return;
        success = response.success;
        message = response.message;
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
