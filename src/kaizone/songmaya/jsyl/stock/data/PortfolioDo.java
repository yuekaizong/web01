
package kaizone.songmaya.jsyl.stock.data;

import java.text.SimpleDateFormat;
import java.util.Date;

import kaizone.android.b89.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class PortfolioDo extends StockDo {
    public static final String FLAG = "portfolioDo";

    public StockDo.Info[] list;

    public static JSONObject convertJson(PortfolioDo obj) throws Exception {
        JSONObject json = JSONResponse.convertJson(obj);
        JSONObject bodyjson = StockDo.convertJson(obj);
        json.putOpt(StockDo.DATA, bodyjson);
        JSONObject entityjson = new JSONObject();
        bodyjson.put(StockDo.ATTR, entityjson);

        StockDo.Info[] list = obj.list;
        if (list != null) {
            int len = list.length;
            JSONArray jsonarray = new JSONArray();
            for (int i = 0; i < len; i++) {
                jsonarray.put(i, StockDo.Info.convertJson(list[i]));
            }
            entityjson.put(StockDo.Info.FLAG, jsonarray);
        }
        return json;
    }

    public static PortfolioDo parseJson(String str) {
        PortfolioDo obj = new PortfolioDo();
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
                    JSONArray sjsonarray = jsonattr.optJSONArray(StockDo.Info.FLAG);
                    if (sjsonarray != null) {
                        final int len = sjsonarray.length();
                        StockDo.Info[] list = new StockDo.Info[len];
                        for (int i = 0; i < len; i++) {
                            list[i] = StockDo.Info.parse(sjsonarray.getJSONObject(i));
                        }
                        obj.list = list;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static PortfolioDo produce() {
        PortfolioDo obj = new PortfolioDo();
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
            info.chg = Utils.floatTo00(info.change / info.prevClose);

            list[i] = info;
        }
        obj.list = list;
        return obj;
    }

}
