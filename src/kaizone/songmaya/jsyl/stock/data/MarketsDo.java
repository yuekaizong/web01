package kaizone.songmaya.jsyl.stock.data;

import java.text.SimpleDateFormat;
import java.util.Date;

import kaizone.android.b89.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class MarketsDo extends StockDo {
    public static final String FLAG = "marketsDo";
    
	public static final String MARKET_INDEX = "marketIndex";
	public static final String UP_LIST = "upList";
	public static final String DOWN_LIST = "downList";

	public StockDo.Info[] markets;
	public StockDo.Info[] upList;
	public StockDo.Info[] downList;

	public static JSONObject convertJson(MarketsDo obj) throws Exception {
		JSONObject json = JSONResponse.convertJson(obj);
		JSONObject bodyjson = StockDo.convertJson(obj);
		json.putOpt(StockDo.DATA, bodyjson);
		JSONObject entityjson = new JSONObject();
		bodyjson.put(StockDo.ATTR, entityjson);

		StockDo.Info[] markers = obj.markets;
		if (markers != null) {
			int len = markers.length;
			JSONArray jsonarray = new JSONArray();
			for (int i = 0; i < len; i++) {
				jsonarray.put(i, StockDo.Info.convertJson(markers[i]));
			}
			entityjson.put(MARKET_INDEX, jsonarray);
		}

		StockDo.Info[] uplist = obj.upList;
		if (uplist != null) {
			int len = uplist.length;
			JSONArray jsonarray = new JSONArray();
			for (int i = 0; i < len; i++) {
				jsonarray.put(i, StockDo.Info.convertJson(uplist[i]));
			}
			entityjson.put(UP_LIST, jsonarray);
		}

		StockDo.Info[] downlist = obj.downList;
		if (downlist != null) {
			int len = downlist.length;
			JSONArray jsonarray = new JSONArray();
			for (int i = 0; i < len; i++) {
				jsonarray.put(i, StockDo.Info.convertJson(downlist[i]));
			}
			entityjson.put(DOWN_LIST, jsonarray);
		}
		return json;
	}

	public static MarketsDo parseJson(String str) {
		MarketsDo obj = new MarketsDo();
		try {
			JSONObject jsonObject = new JSONObject(str);
			JSONResponse respDo = JSONResponse.parse(jsonObject);
			obj.setJSONResponse(respDo);

			JSONObject jsondata = jsonObject.getJSONObject(StockDo.DATA);
			if (jsondata != null) {
				StockDo stockDo = StockDo.parse(jsondata);
				obj.setStockDo(stockDo);

				JSONObject jsonattr = jsondata.optJSONObject(StockDo.ATTR);
				if (jsonattr != null) {
					JSONArray marketsjsonarray = jsonattr.getJSONArray(MARKET_INDEX);
					if (marketsjsonarray != null) {
						obj.markets = StockDo.Info.parse(marketsjsonarray);
					}

					JSONArray upjsonarray = jsonattr.getJSONArray(UP_LIST);
					if (upjsonarray != null) {
						obj.upList = StockDo.Info.parse(upjsonarray);
					}

					JSONArray downjsonarray = jsonattr.getJSONArray(DOWN_LIST);
					if (downjsonarray != null) {
						obj.downList = StockDo.Info.parse(downjsonarray);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static MarketsDo produce() {
		MarketsDo obj = new MarketsDo();
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
		obj.responseDate = dateformat.format(date);
		obj.responseTime = timeformat.format(date);
		obj.success = true;
		obj.message = "请求成功";

		int count = 10;
		StockDo.Info[] list = new Info[count];
		for (int i = 0; i < count; i++) {
			StockDo.Info info = new Info();
			String str = "portfolio" + i;
			info.prevClose = 20 + i * StockDo.randomOrien();
			info.lastTrade = StockDo.randomNextOfScope(info.prevClose, 0.1f);
			info.name = str;
			info.symbol = "00000" + i;
			info.change = Utils
					.floatTo00((float) ((info.lastTrade - info.prevClose) / info.prevClose));
			info.chg = Utils.floatTo(info.change / info.prevClose, 4);

			list[i] = info;
		}
		
		obj.markets = list;
		obj.upList = list;
		obj.downList = list;
		return obj;
	}
}
