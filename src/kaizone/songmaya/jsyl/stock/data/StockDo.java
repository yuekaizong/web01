
package kaizone.songmaya.jsyl.stock.data;

import java.util.Random;

import org.json.JSONObject;

public class StockDo extends JSONResponse {

    public static final String SYMBOL = "symbol"; // 股票代码
    public static final String NAME = "name";
    public static final String ATTR = "attr";
    public static final String FLAG = "flag";
    public static final String DATA = "data";
    public static final String TIMESTART = "timestart";
    public static final String TIMEEND = "timeend";
    
    public static final String LASTTRADE = "lastTrade";
    public static final String CHANGE = "change";
    public static final String CHG = "chg";
    public static final String OPEN = "open";
    public static final String PREVCLOSE = "prevClose";
    public static final String AMPLITUDE = "amplitude";
    public static final String TURNOVER = "turnover";
    public static final String VOLUME = "volume";
    public static final String ZUIDI = "zuiDi";
    public static final String ZUIGAO = "zuiGao";
    public static final String SHIYINLV = "shiYinLv";
    public static final String PINGJIASHU = "pingJiaShu";
    public static final String DIEJIASHU = "dieJiaShu";
    public static final String LENGTH = "length";

    public String symbol;
    public String name;
    public String flag;
    public String timestart;
    public String timeend;
    
    public int type;
    public float lastTrade; // 最新
    public float chg; // 涨跌幅 % Chg
    public float change; // 涨跌额
    public float open; // 今开
    public float prevClose; // 昨收
    public float amplitude; // 振幅
    public String turnover; // 成交额
    public String volume; // 成交量
    public float zuiDi;
    public float zuiGao;

    public float shiYinLv;
    public float pingJiaShu;
    public float dieJiaShu;
    
    public int length;  //data总长度

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

    public static StockDo parse(JSONObject jsonObject) {
        StockDo obj = new StockDo();
        obj.symbol = jsonObject.optString(SYMBOL);
        obj.name = jsonObject.optString(NAME);
        obj.flag = jsonObject.optString(FLAG);
        obj.timestart = jsonObject.optString(TIMESTART);
        obj.timeend = jsonObject.optString(TIMEEND);
        
        obj.lastTrade = (float) jsonObject.optDouble(LASTTRADE);
        obj.chg = (float) jsonObject.optDouble(CHG);
        obj.change = (float) jsonObject.optDouble(CHANGE);
        obj.open = (float) jsonObject.optDouble(OPEN);
        obj.prevClose = (float) jsonObject.optDouble(PREVCLOSE);
        obj.amplitude = (float) jsonObject.optDouble(AMPLITUDE);
        obj.turnover = jsonObject.optString(TURNOVER);
        obj.volume = jsonObject.optString(VOLUME);

        obj.zuiDi = (float) jsonObject.optDouble(ZUIDI);
        obj.zuiGao = (float) jsonObject.optDouble(ZUIGAO);
        obj.shiYinLv = (float) jsonObject.optDouble(SHIYINLV);
        obj.pingJiaShu = (float) jsonObject.optDouble(PINGJIASHU);
        obj.dieJiaShu = (float) jsonObject.optDouble(DIEJIASHU);
        obj.length = jsonObject.optInt(LENGTH);
        return obj;
    }

    public static JSONObject convertJson(StockDo obj) throws Exception {
        JSONObject bodyjson = new JSONObject();
        bodyjson.putOpt(NAME, obj.name);
        bodyjson.putOpt(SYMBOL, obj.symbol);
        bodyjson.putOpt(FLAG, obj.flag);
        bodyjson.putOpt(TIMESTART, obj.timestart);
        bodyjson.putOpt(TIMEEND, obj.timeend);
        bodyjson.putOpt(LASTTRADE, obj.lastTrade);
        bodyjson.putOpt(CHG, obj.chg);
        bodyjson.putOpt(CHANGE, obj.change);
        bodyjson.putOpt(OPEN, obj.open);
        bodyjson.putOpt(PREVCLOSE, obj.prevClose);
        bodyjson.putOpt(AMPLITUDE, obj.amplitude);
        bodyjson.putOpt(TURNOVER, obj.turnover);
        bodyjson.putOpt(VOLUME, obj.volume);
        bodyjson.putOpt(ZUIDI, obj.zuiDi);
        bodyjson.putOpt(ZUIGAO, obj.zuiGao);
        bodyjson.putOpt(LENGTH, obj.length);
        return bodyjson;
    }

    public void setStockDo(StockDo stockDo) {
        symbol = stockDo.symbol;
        name = stockDo.name;
        flag = stockDo.flag;
        timestart = stockDo.timestart;
        timeend = stockDo.timeend;
        type = stockDo.type;
        lastTrade = stockDo.lastTrade;
        chg = stockDo.chg;
        change = stockDo.change;
        open = stockDo.open;
        prevClose = stockDo.prevClose;
        amplitude = stockDo.amplitude;
        turnover = stockDo.turnover;
        volume = stockDo.volume;
        zuiDi = stockDo.zuiDi;
        zuiGao = stockDo.zuiGao;
        length = stockDo.length;
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
