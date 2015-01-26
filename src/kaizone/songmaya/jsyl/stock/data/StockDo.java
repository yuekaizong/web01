
package kaizone.songmaya.jsyl.stock.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import kaizone.android.b89.util.Utils;

import org.json.JSONObject;

public class StockDo extends JSONResponse {

    public static final String SYMBOL = "symbol"; // 股票代码
    public static final String NAME = "name";
    public static final String ATTR = "attr";
    public static final String FLAG = "flag";
    public static final String DATA = "data";
    public static final String TIMESTART = "timestart";
    public static final String TIMEEND = "timeend";

    public static final String TYPE = "type";
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
    public static final String UNIT = "unit";
    public static final String LENGTH = "length";

    public String flag;
    public String timestart;
    public String timeend;
    public float unit;
    public int length; // data总长度

    public Info info;

    public static class Info {

        public static final String FLAG = "info";

        public String symbol;
        public String name;
        public int type; // 类别 股票=0 指数=1
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

        public static Info parse(JSONObject jsonObject) {
            Info obj = new Info();
            obj.symbol = jsonObject.optString(SYMBOL);
            obj.name = jsonObject.optString(NAME);

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
            return obj;
        }

        public static JSONObject convertJson(Info obj) throws Exception {
            JSONObject bodyjson = new JSONObject();
            bodyjson.putOpt(NAME, obj.name);
            bodyjson.putOpt(SYMBOL, obj.symbol);
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
            bodyjson.putOpt(TYPE, obj.type);
            return bodyjson;
        }

        public void setStock(Info stockDo) {
            symbol = stockDo.symbol;
            name = stockDo.name;
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
        }

        public void fillStock(Info stockDo) {
            symbol = (symbol == null ? stockDo.symbol : symbol);
            name = (name == null ? stockDo.name : name);
            type = (type == 0 ? stockDo.type : type);
            lastTrade = (lastTrade == 0 ? stockDo.lastTrade : lastTrade);
            chg = (chg == 0 ? stockDo.chg : chg);
            change = (change == 0 ? stockDo.change : change);
            open = (open == 0 ? stockDo.open : open);
            prevClose = (prevClose == 0 ? stockDo.prevClose : prevClose);
            amplitude = (amplitude == 0 ? stockDo.amplitude : amplitude);
            turnover = (turnover == null ? stockDo.turnover : turnover);
            volume = (volume == null ? stockDo.volume : volume);
            zuiDi = (zuiDi == 0 ? stockDo.zuiDi : zuiDi);
            zuiGao = (zuiGao == 0 ? stockDo.zuiGao : zuiGao);
        }
    }

    public static StockDo parseJsonString(String jsonstring) {
        JSONObject jsonObject = null;
        StockDo obj = new StockDo();
        try {
            jsonObject = new JSONObject(jsonstring);
            obj.timestart = jsonObject.optString(StockDo.TIMESTART);
            obj.timeend = jsonObject.optString(StockDo.TIMEEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static StockDo parse(JSONObject jsonObject) {
        StockDo obj = new StockDo();
        obj.flag = jsonObject.optString(FLAG);
        obj.timestart = jsonObject.optString(TIMESTART);
        obj.timeend = jsonObject.optString(TIMEEND);
        obj.unit = jsonObject.optInt(UNIT);
        obj.length = jsonObject.optInt(LENGTH);
        JSONObject stockJson = jsonObject.optJSONObject(Info.FLAG);
        if (stockJson != null) {
            obj.info = Info.parse(stockJson);
        }
        return obj;
    }

    public static JSONObject convertJson(StockDo obj) {
        JSONObject bodyjson = new JSONObject();
        try {
            bodyjson.put(FLAG, obj.flag);
            bodyjson.put(TIMESTART, obj.timestart);
            bodyjson.put(TIMEEND, obj.timeend);
            bodyjson.put(UNIT, obj.unit);
            bodyjson.put(LENGTH, obj.length);
            if (obj.info != null) {
                bodyjson.put(Info.FLAG, Info.convertJson(obj.info));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bodyjson;
    }

    public void setStockDo(StockDo stockDo) {
        flag = stockDo.flag;
        timestart = stockDo.timestart;
        timeend = stockDo.timeend;
        unit = stockDo.unit;
        length = stockDo.length;
        info = stockDo.info;
    }

    public void fillStockDo(StockDo stockDo) {
        flag = (flag == null ? stockDo.flag : flag);
        timestart = (timestart == null ? stockDo.timestart : timestart);
        timeend = (timestart == null ? stockDo.timestart : timestart);
        unit = (unit == 0 ? stockDo.unit : unit);
        length = (length == 0 ? stockDo.length : length);
        info = (info == null ? stockDo.info : info);
        info.fillStock(stockDo.info);
        fillJSONResponse(stockDo);
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

    public static StockDo createStockDo(String symbol, int unit, float open, float lastTrade,
            float prevClose, String volume, String turnover, float high, float low) {
        StockDo sdo = new StockDo();
        Info obj = new Info();
        obj.symbol = symbol;
        obj.type = 0;
        obj.open = open;
        obj.lastTrade = lastTrade;
        obj.prevClose = prevClose;
        obj.volume = volume;
        obj.turnover = turnover;
        obj.zuiGao = high;
        obj.zuiDi = low;
        obj.change = obj.lastTrade - obj.prevClose;
        obj.change = Utils.floatTo(obj.change, 2);
        obj.chg = obj.change / obj.prevClose;
        obj.chg = Utils.floatTo(obj.chg, 4);
        obj.amplitude = Utils.floatTo((obj.zuiGao - obj.zuiDi) / obj.prevClose, 4);
        sdo.info = obj;
        sdo.success = true;
        sdo.message = "请求成功";
        sdo.unit = unit;

        Date date = new Date();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
        sdo.responseDate = dateformat.format(date);
        sdo.responseTime = timeformat.format(date);

        //
        if (symbol.equals("000001")) {
            obj.name = "上证指数";
            obj.type = 1;
        } else if (symbol.equals("399001")) {
            obj.name = "深证成指";
            obj.type = 1;
        } else if (symbol.equals("399006")) {
            obj.name = "创业板指";
            obj.type = 1;
        } else {
            obj.name = "星星点点";
            obj.type = 0;
        }
        return sdo;
    }

}
