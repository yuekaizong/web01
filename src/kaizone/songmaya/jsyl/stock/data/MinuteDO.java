
package kaizone.songmaya.jsyl.stock.data;

import java.text.SimpleDateFormat;
import java.util.Date;

import kaizone.android.b89.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class MinuteDO extends StockDo {

    public static final String FLAG = "minuteDo";
    public static final String ID = "id";
    public static final String TIME = "time";
    public static final String NOW = "now";
    public static final String CHANGE = "change";
    public static final String AMOUNT = "amount";
    public static final String MEAN = "mean";
    public static final String ISNODE = "isnode";
    public static final String LASTTRADE = "lastTrade";
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
    public static final String PRICE = "price";
    public static final String TYPE = "type";
    public static final String FIELDS = "fields";

    public static final int TYPE_TODAY = 0;
    public static final int TYPE_5DAY = 5;

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

    public MEntity[] ms;
    public TradeEntity[] trades;
    public Period period;

    public static JSONObject convertJson(MinuteDO obj) throws Exception {
        JSONObject json = new JSONObject();
        json.putOpt(JSONResponse.SUCCESS, obj.success);
        json.putOpt(JSONResponse.MESSAGE, obj.message);
        json.putOpt(JSONResponse.RESPONSEDATE, obj.responseDate);
        json.putOpt(JSONResponse.RESPONSETIME, obj.responseTime);

        JSONObject bodyjson = new JSONObject();
        json.putOpt(StockDo.DATA, bodyjson);

        bodyjson.put(StockDo.NAME, obj.name);
        bodyjson.put(StockDo.SYMBOL, obj.symbol);
        bodyjson.put(StockDo.TIMESTART, obj.timestart);
        bodyjson.put(StockDo.TIMEEND, obj.timeend);

        JSONObject entityjson = new JSONObject();
        bodyjson.put(StockDo.ATTR, entityjson);

        entityjson.putOpt(TYPE, obj.type);
        entityjson.putOpt(LASTTRADE, obj.lastTrade);
        entityjson.putOpt(CHG, obj.chg);
        entityjson.putOpt(CHANGE, obj.change);
        entityjson.putOpt(OPEN, obj.open);
        entityjson.putOpt(PREVCLOSE, obj.prevClose);
        entityjson.putOpt(AMPLITUDE, obj.amplitude);
        entityjson.putOpt(TURNOVER, obj.turnover);
        entityjson.putOpt(VOLUME, obj.volume);
        entityjson.putOpt(ZUIDI, obj.zuiDi);
        entityjson.putOpt(ZUIGAO, obj.zuiGao);

        MEntity[] ms = obj.ms;
        if (ms != null) {
            int len = ms.length;
            JSONArray jsonarray = new JSONArray();
            for (int i = 0; i < len; i++) {
                jsonarray.put(i, MEntity.convertJson(ms[i]));
            }
            entityjson.put(MEntity.FLAG, jsonarray);
        }

        TradeEntity ts[] = obj.trades;
        if (ts != null) {
            int len = ts.length;
            JSONArray jsonarray = new JSONArray();
            for (int i = 0; i < len; i++) {
                jsonarray.put(i, TradeEntity.convertJson(ts[i]));
            }
            entityjson.put(TradeEntity.FLAG, jsonarray);
        }

        Period period = obj.period;
        if (period != null) {
            JSONObject jsonObject = Period.convertJson(period);
            entityjson.put(Period.FLAG, jsonObject);
        }
        return json;
    }

    public static MinuteDO parseJson(String str) {
        MinuteDO mdo = new MinuteDO();
        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONResponse respDo = JSONResponse.parse(jsonObject);
            mdo.setJSONResponse(respDo);

            JSONObject jsondata = jsonObject.getJSONObject(StockDo.DATA);
            if (jsondata != null) {
                StockDo stockDo = StockDo.parse(jsondata);
                mdo.setStockDo(stockDo);

                JSONObject jsonattr = jsondata.optJSONObject(StockDo.ATTR);
                if (jsonattr != null) {
                    mdo.type = jsonattr.optInt(TYPE);
                    mdo.lastTrade = (float) jsonattr.optDouble(LASTTRADE);
                    mdo.chg = (float) jsonattr.optDouble(CHG);
                    mdo.change = (float) jsonattr.optDouble(CHANGE);
                    mdo.open = (float) jsonattr.optDouble(OPEN);
                    mdo.prevClose = (float) jsonattr.optDouble(PREVCLOSE);
                    mdo.amplitude = (float) jsonattr.optDouble(AMPLITUDE);
                    mdo.turnover = jsonattr.optString(TURNOVER);
                    mdo.volume = jsonattr.optString(VOLUME);

                    mdo.zuiDi = (float) jsonattr.optDouble(ZUIDI);
                    mdo.zuiGao = (float) jsonattr.optDouble(ZUIGAO);
                    mdo.shiYinLv = (float) jsonattr.optDouble(SHIYINLV);
                    mdo.pingJiaShu = (float) jsonattr.optDouble(PINGJIASHU);
                    mdo.dieJiaShu = (float) jsonattr.optDouble(DIEJIASHU);

                    JSONArray mjsonarray = jsonattr.optJSONArray(MEntity.FLAG);
                    if (mjsonarray != null) {
                        final int len = mjsonarray.length();
                        MEntity[] ms = new MEntity[len];
                        for (int i = 0; i < len; i++) {
                            ms[i] = MEntity.parse(mjsonarray.getJSONObject(i));
                        }
                        mdo.ms = ms;
                    }

                    JSONArray tradesjsonarray = jsonattr.optJSONArray(TradeEntity.FLAG);
                    if (tradesjsonarray != null) {
                        final int len = tradesjsonarray.length();
                        TradeEntity[] array = new TradeEntity[len];
                        for (int i = 0; i < len; i++) {
                            array[i] = TradeEntity.parse(tradesjsonarray.getJSONObject(i));
                        }
                        mdo.trades = array;
                    }

                    JSONObject periodjson = jsonattr.optJSONObject(Period.FLAG);
                    if (periodjson != null) {
                        mdo.period = Period.parse(periodjson);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mdo;
    }

    public static class MEntity {
        public static final String FLAG = "M";

        public String time;
        public float now;
        public float change; // 涨跌
        public int amount; // 成交
        public float mean; // 平均
        public boolean isnode;

        public static JSONObject convertJson(MEntity obj) throws Exception {
            JSONObject bodyjson = new JSONObject();
            bodyjson.putOpt(TIME, obj.time);
            bodyjson.putOpt(NOW, obj.now);
            bodyjson.putOpt(CHANGE, obj.change);
            bodyjson.putOpt(AMOUNT, obj.amount);
            bodyjson.putOpt(MEAN, obj.mean);
            bodyjson.putOpt(ISNODE, obj.isnode);
            return bodyjson;
        }

        public static MEntity parse(JSONObject jsonObject) {
            MEntity obj = new MEntity();
            try {
                obj.time = jsonObject.optString(TIME);
                obj.now = (float) jsonObject.optDouble(NOW);
                obj.change = Float.valueOf(jsonObject.optString(CHANGE));
                obj.amount = jsonObject.optInt(AMOUNT);
                obj.mean = Float.valueOf(jsonObject.optString(MEAN));
                obj.isnode = jsonObject.optBoolean(ISNODE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    public static class TradeEntity {
        public static final String FLAG = "trades";
        public int id;
        public float price;
        public int amount;

        public static JSONObject convertJson(TradeEntity obj) throws Exception {
            JSONObject bodyjson = new JSONObject();
            bodyjson.putOpt(ID, obj.id);
            bodyjson.putOpt(PRICE, obj.price);
            bodyjson.putOpt(AMOUNT, obj.amount);
            return bodyjson;
        }

        public static TradeEntity parse(JSONObject jsonObject) {
            TradeEntity obj = new TradeEntity();
            try {
                obj.id = jsonObject.optInt(ID);
                obj.price = Float.valueOf(jsonObject.optString(PRICE));
                obj.amount = jsonObject.optInt(AMOUNT);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    public static class Period {
        public static final String FLAG = "Period";
        public String type;
        public String[] fields;

        public static JSONObject convertJson(Period obj) throws Exception {
            JSONObject bodyjson = new JSONObject();
            bodyjson.putOpt(TYPE, obj.type);
            String[] array = obj.fields;
            if (array != null) {
                int len = array.length;
                JSONArray jsonarray = new JSONArray();
                for (int i = 0; i < len; i++) {
                    jsonarray.put(i, array[i]);
                }
                bodyjson.putOpt(FIELDS, jsonarray);
            }
            return bodyjson;
        }

        public static Period parse(JSONObject jsonObject) {
            Period obj = new Period();
            try {
                obj.type = jsonObject.optString(TYPE);
                JSONArray jsonarray = jsonObject.optJSONArray(FIELDS);
                if (jsonarray != null) {
                    int len = jsonarray.length();
                    String[] array = new String[len];
                    for (int i = 0; i < len; i++) {
                        array[i] = jsonarray.getString(i);
                    }
                    obj.fields = array;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    public static MinuteDO produce(int type, String symbol, String timestart,
            String timeend) {
        int count = 180;
        MinuteDO mDo = new MinuteDO();

        float price = 10.37f;
        mDo.prevClose = price;

        MEntity[] tmp_m = new MEntity[count];

        String time = Utils.time2();
        float price_high = 0;
        float price_low = 0;
        float cj_sum = 0;
        long cje_sum = 0;
        Date date = new Date();

        for (int i = 0; i < count; i++) {
            MEntity m = new MEntity();
            m.time = Utils.timeAfter(time, i);
            if (i == 0) {
                m.now = randomNextOfScope(price, 0.1f);
            }
            m.now = Utils.floatTo(randomNextOfScope(price, 0.05f), 2);
            m.amount = randomNext(10000);
            m.mean = Utils.floatTo(randomNextOfScope((price-0.01f), 0.01f), 2);
            m.change = Utils.floatTo00((float) ((m.now - price) / price));
            tmp_m[i] = m;

            price_high = Math.max(price_high, m.now);
            if (i == 0) {
                price_low = m.now;
            } else {
                price_low = Math.min(price_low, m.now);
            }
            cj_sum += m.amount;
            cje_sum += (m.now * m.amount);
        }
        mDo.ms = tmp_m;
        mDo.type = type;

        if (mDo.type != TYPE_5DAY) {
            mDo.open = tmp_m[0].now;
            mDo.lastTrade = tmp_m[count - 1].now;
            mDo.change = tmp_m[count - 1].now - mDo.prevClose;
            mDo.change = Utils.floatTo(mDo.change, 2);
            mDo.chg = mDo.change / mDo.prevClose;
            mDo.chg = Utils.floatTo(mDo.chg, 4);
            mDo.volume = "" + cj_sum;
            mDo.turnover = "" + cje_sum;
            mDo.zuiGao = price_high;
            mDo.zuiDi = price_low;
            mDo.amplitude = Utils.floatTo((price_high - price_low)
                    / mDo.prevClose, 4);

            TradeEntity[] trades = new TradeEntity[10];
            for (int i = 0; i < 10; i++) {
                int k = i;
                if (i >= 5)
                    k = i + 1;
                TradeEntity trade = new TradeEntity();
                trade.id = k - 5;

                trade.amount = randomNext(10000);
                trade.price = Utils.floatTo(randomNextOfScope(price, 0.05f), 2);
                trades[i] = trade;
            }
            mDo.trades = trades;

            Period period = new Period();
            period.fields = new String[] {
                    "09:30", "10:30", "11:30/13:00", "14:00", "15:00"
            };
            period.type = "time";

            mDo.period = period;
            mDo.timestart = Utils.date2();
            mDo.timeend = Utils.date2();
        } else {
            Period period = new Period();
            String today = Utils.date3();

            period.fields = new String[] {
                    Utils.dateAfter3(today, -4),
                    Utils.dateAfter3(today, -3),
                    Utils.dateAfter3(today, -2),
                    Utils.dateAfter3(today, -1),
                    Utils.dateAfter3(today, 0),
            };
            
            period.type = "date";
            
            mDo.period = period;
            mDo.timeend = Utils.date2();
            mDo.timestart = Utils.dateAfter(mDo.timeend, -4);
        }

        mDo.name = "星星点点";
        mDo.symbol = symbol;
        mDo.success = true;
        mDo.message = "请求成功";

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
        mDo.responseDate = dateformat.format(date);
        mDo.responseTime = timeformat.format(date);

        return mDo;
    }

}
