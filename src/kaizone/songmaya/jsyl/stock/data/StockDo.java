package kaizone.songmaya.jsyl.stock.data;

import java.util.Random;

import org.json.JSONObject;

public class StockDo extends JSONResponse{

	public static final String SYMBOL = "symbol ";  //股票代码
	public static final String NAME = "name";
	public static final String ATTR = "attr";
	public static final String FLAG = "flag";
	public static final String DATA = "data";
	public static final String TIMESTART = "timestart";
	public static final String TIMEEND = "timeend";

	public String symbol;
	public String name;
	public String flag;
	public String timestart;
	public String timeend;

	public static StockDo parseJsonString(String jsonstring) {
		JSONObject jsonObject = null;
		StockDo obj = new StockDo();
		try {
			jsonObject = new JSONObject(jsonstring);
			obj.symbol = jsonObject.optString(StockDo.SYMBOL);
			obj.name = jsonObject.optString(StockDo.NAME);
			obj.timestart = jsonObject.optString(StockDo.TIMESTART);
			obj.timeend = jsonObject.optString(StockDo.TIMEEND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public static StockDo parse(JSONObject jsonObject){
		StockDo obj = new StockDo();
		obj.symbol = jsonObject.optString(SYMBOL);
		obj.name = jsonObject.optString(NAME);
		obj.flag = jsonObject.optString(FLAG);
		obj.timestart = jsonObject.optString(TIMESTART);
		obj.timeend = jsonObject.optString(TIMEEND);
		return obj;
	}

	public static JSONObject convertJson(StockDo obj) throws Exception {
		JSONObject bodyjson = new JSONObject();
		bodyjson.putOpt(NAME, obj.name);
		bodyjson.putOpt(SYMBOL, obj.symbol);
		bodyjson.putOpt(FLAG, obj.flag);
		bodyjson.putOpt(TIMESTART, obj.timestart);
		bodyjson.putOpt(TIMEEND, obj.timeend);
		return bodyjson;
	}

	public void setStockDo(StockDo stockDo) {
	    symbol = stockDo.symbol;
		name = stockDo.name;
		flag = stockDo.flag;
		timestart = stockDo.timestart;
		timeend = stockDo.timeend;
	}

	public static class Entity {
		public String flag;

		public Entity() {
			flag = this.getClass().getSimpleName();
		}
	}

	public static int randomNext(int m) {
		Random rand = new Random();
		return rand.nextInt(m);
	}

	public static float randomNextOfScope(float d, float scope) {
		float d_2 = d * scope;
		Random rand = new Random();
		float d_3 = rand.nextInt((int) (d_2 * 100)) / 100f;
		return d + randomOrien() * d_3;
	}

	public static int randomOrien() {
		Random rand = new Random();
		int ori = rand.nextInt(2);
		if (ori == 1) {
			return 1;
		}
		return -1;
	}

	public static float randomRange(float a, float b) {
		int range = (int) Math.abs((a - b) * 100);
		Random random = new Random();
		int v = random.nextInt(range);
		float min = Math.min(a, b);
		float zuo = min + v / 100f;
		return zuo;
	}

}