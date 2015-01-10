
package kaizone.songmaya.jsyl.stock.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import kaizone.android.b89.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class KDo extends StockDo {

    public static final String FLAG = "klineDo";
    public static final String TIME = "time";
    public static final String KAI = "kai";
    public static final String GAO = "gao";
    public static final String DI = "di";
    public static final String SHOU = "shou";
    public static final String AMOUNT = "amount";
    public static final String ISNODE = "isnode";
    // public static final String MACD = "Macd";
    // public static final String Dmi = "Dmi";
    // public static final String Wr = "Wr";
    // public static final String Boll = "Boll";
    // public static final String Kdj = "Kdj";
    // public static final String Rsi = "Rsi";
    public static final String MA5 = "ma5";
    public static final String MA10 = "ma10";
    public static final String MA20 = "ma20";

    public static final String DIFF = "diff";
    public static final String DEA = "dea";
    public static final String MACD = "macd";

    public static final String OBV = "obv";

    public static final String PDI = "pdi";
    public static final String MDI = "mdi";
    public static final String ADX = "adx";
    public static final String ADXR = "adxr";

    public static final String UPPER = "upper";
    public static final String MID = "mid";
    public static final String LOWPER = "lowper";

    public static final String WR1 = "wr1";
    public static final String WR2 = "wr2";

    public static final String RSI6 = "rsi6";
    public static final String RSI12 = "rsi12";
    public static final String RSI24 = "rsi24";

    public static final String K = "k";
    public static final String D = "d";
    public static final String J = "j";

    public static final String TYPE = "type";
    public static final String FUQUAN = "fuquan";
    public static final String COUNT = "count";
    public static final String REQUESTDATE = "requestDate";

    public static final int TYPE_K_DAY = 0;
    public static final int TYPE_K_WEEK = 5;
    public static final int TYPE_K_MONTH = 30;

    public static final int FUQUAN_NO = 0;
    public static final int FUQUAN_BEFORE = 1;
    public static final int FUQUAN_AFTER = 2;

    public KEntity[] karray;

    public Macd[] macd;
    public Dmi[] dmi;
    public Wr[] wr;
    public Boll[] boll;
    public Kdj[] kdj;
    public Rsi[] rsi;
    public OBv[] obv;
    public float[] ma5;
    public float[] ma10;
    public float[] ma20;
    public int type;
    public int fuquan;
    public int count;
    public String requestDate;

    public static JSONObject convertJson(KDo obj) throws Exception {
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
        bodyjson.put(KDo.TYPE, obj.type);
        bodyjson.put(KDo.FUQUAN, obj.fuquan);
        bodyjson.put(KDo.COUNT, obj.count);
        bodyjson.put(KDo.REQUESTDATE, obj.requestDate);

        JSONObject entityjson = new JSONObject();

        KEntity[] ks = obj.karray;
        if (ks != null) {
            int klen = ks.length;
            JSONArray k_jsonarray = new JSONArray();
            for (int i = 0; i < klen; i++) {
                k_jsonarray.put(i, KEntity.convertJson(ks[i]));
            }
            entityjson.put(KEntity.FLAG, k_jsonarray);
        }

        Macd[] macds = obj.macd;
        if (macds != null) {
            int macdslen = macds.length;
            JSONArray macd_jsonarray = new JSONArray();
            for (int i = 0; i < macdslen; i++) {
                macd_jsonarray.put(i, Macd.convertJson(macds[i]));
            }
            entityjson.put(Macd.FLAG, macd_jsonarray);
        }

        Boll[] bolls = obj.boll;
        if (bolls != null) {
            int bolllen = bolls.length;
            JSONArray boll_jsonarray = new JSONArray();
            for (int i = 0; i < bolllen; i++) {
                boll_jsonarray.put(i, Boll.convertJson(bolls[i]));
            }
            entityjson.put(Boll.FLAG, boll_jsonarray);
        }

        Dmi[] dmis = obj.dmi;
        if (dmis != null) {
            int dmilen = dmis.length;
            JSONArray dmi_jsonarray = new JSONArray();
            for (int i = 0; i < dmilen; i++) {
                dmi_jsonarray.put(i, Dmi.convertJson(dmis[i]));
            }
            entityjson.put(Dmi.FLAG, dmi_jsonarray);
        }

        Kdj[] kdjs = obj.kdj;
        if (kdjs != null) {
            int kdjlen = kdjs.length;
            JSONArray kdj_jsonarray = new JSONArray();
            for (int i = 0; i < kdjlen; i++) {
                kdj_jsonarray.put(i, Kdj.convertJson(kdjs[i]));
            }
            entityjson.put(Kdj.FLAG, kdj_jsonarray);
        }

        Rsi[] rsis = obj.rsi;
        if (rsis != null) {
            int rsilen = rsis.length;
            JSONArray rsi_jsonarray = new JSONArray();
            for (int i = 0; i < rsilen; i++) {
                rsi_jsonarray.put(i, Rsi.convertJson(rsis[i]));
            }
            entityjson.put(Rsi.FLAG, rsi_jsonarray);
        }

        Wr[] wrs = obj.wr;
        if (wrs != null) {
            int wrlen = wrs.length;
            JSONArray wr_jsonarray = new JSONArray();
            for (int i = 0; i < wrlen; i++) {
                wr_jsonarray.put(i, Wr.convertJson(wrs[i]));
            }
            entityjson.put(Wr.FLAG, wr_jsonarray);
        }

        OBv[] obvs = obj.obv;
        if (obvs != null) {
            int len = obvs.length;
            JSONArray obv_jsonarray = new JSONArray();
            for (int i = 0; i < len; i++) {
                obv_jsonarray.put(i, OBv.convertJson(obvs[i]));
            }
            entityjson.put(OBv.FLAG, obv_jsonarray);
        }

        float[] ma5s = obj.ma5;
        if (ma5s != null) {
            int len = ma5s.length;
            JSONArray jsonarray = new JSONArray();
            for (int i = 0; i < len; i++) {
                jsonarray.put(i, ma5s[i]);
            }
            entityjson.put(MA5, jsonarray);
        }
        
        float[] ma10s = obj.ma10;
        if (ma10s != null) {
            int len = ma10s.length;
            JSONArray jsonarray = new JSONArray();
            for (int i = 0; i < len; i++) {
                jsonarray.put(i, ma10s[i]);
            }
            entityjson.put(MA10, jsonarray);
        }
        
        float[] ma20s = obj.ma20;
        if (ma20s != null) {
            int len = ma20s.length;
            JSONArray jsonarray = new JSONArray();
            for (int i = 0; i < len; i++) {
                jsonarray.put(i, ma20s[i]);
            }
            entityjson.put(MA20, jsonarray);
        }

        bodyjson.put(StockDo.ATTR, entityjson);
        return json;
    }

    public static KDo parseJson(String str) {
        KDo kdo = new KDo();
        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONResponse respDo = JSONResponse.parse(jsonObject);
            kdo.setJSONResponse(respDo);

            JSONObject jsondata = jsonObject.getJSONObject(StockDo.DATA);
            if (jsondata != null) {
                StockDo stockDo = StockDo.parse(jsondata);
                kdo.setStockDo(stockDo);

                JSONObject jsonattr = jsondata.optJSONObject(StockDo.ATTR);
                if (jsonattr != null) {
                    kdo.type = jsonattr.optInt(TYPE);
                    kdo.fuquan = jsonattr.optInt(FUQUAN);
                    JSONArray kjsonarray = jsonattr.optJSONArray(KEntity.FLAG);
                    if (kjsonarray != null) {
                        final int len = kjsonarray.length();
                        KEntity[] array = new KEntity[len];
                        for (int i = 0; i < len; i++) {
                            array[i] = KEntity.parse(kjsonarray.getJSONObject(i));
                        }
                        kdo.karray = array;
                    }

                    JSONArray macdjsonarray = jsonattr.optJSONArray(Macd.FLAG);
                    if (macdjsonarray != null) {
                        final int len = macdjsonarray.length();
                        Macd[] array = new Macd[len];
                        for (int i = 0; i < len; i++) {
                            array[i] = Macd.parse(macdjsonarray.getJSONObject(i));
                        }
                        kdo.macd = array;
                    }

                    JSONArray dmijsonarray = jsonattr.optJSONArray(Dmi.FLAG);
                    if (dmijsonarray != null) {
                        final int len = dmijsonarray.length();
                        Dmi[] array = new Dmi[len];
                        for (int i = 0; i < len; i++) {
                            array[i] = Dmi.parse(dmijsonarray.getJSONObject(i));
                        }
                        kdo.dmi = array;
                    }

                    JSONArray wrjsonarray = jsonattr.optJSONArray(Wr.FLAG);
                    if (wrjsonarray != null) {
                        final int len = wrjsonarray.length();
                        Wr[] array = new Wr[len];
                        for (int i = 0; i < len; i++) {
                            array[i] = Wr.parse(wrjsonarray.getJSONObject(i));
                        }
                        kdo.wr = array;
                    }

                    JSONArray bolljsonarray = jsonattr.optJSONArray(Boll.FLAG);
                    if (bolljsonarray != null) {
                        final int len = bolljsonarray.length();
                        Boll[] array = new Boll[len];
                        for (int i = 0; i < len; i++) {
                            array[i] = Boll.parse(bolljsonarray.getJSONObject(i));
                        }
                        kdo.boll = array;
                    }

                    JSONArray kdjjsonarray = jsonattr.optJSONArray(Kdj.FLAG);
                    if (kdjjsonarray != null) {
                        final int len = kdjjsonarray.length();
                        Kdj[] array = new Kdj[len];
                        for (int i = 0; i < len; i++) {
                            array[i] = Kdj.parse(kdjjsonarray.getJSONObject(i));
                        }
                        kdo.kdj = array;
                    }

                    JSONArray obvjsonarray = jsonattr.optJSONArray(OBv.FLAG);
                    if (obvjsonarray != null) {
                        final int len = obvjsonarray.length();
                        OBv[] array = new OBv[len];
                        for (int i = 0; i < len; i++) {
                            array[i] = OBv.parse(obvjsonarray.getJSONObject(i));
                        }
                        kdo.obv = array;
                    }

                    JSONArray rsijsonarray = jsonattr.optJSONArray(Rsi.FLAG);
                    if (rsijsonarray != null) {
                        final int len = rsijsonarray.length();
                        Rsi[] array = new Rsi[len];
                        for (int i = 0; i < len; i++) {
                            array[i] = Rsi.parse(rsijsonarray.getJSONObject(i));
                        }
                        kdo.rsi = array;
                    }

                    JSONArray ma5jsonarray = jsonattr.optJSONArray(MA5);
                    if (ma5jsonarray != null) {
                        final int len = ma5jsonarray.length();
                        float[] array = new float[len];
                        for (int i = 0; i < len; i++) {
                            array[i] = (float) (ma5jsonarray.optDouble(i, 0f));
                        }
                        kdo.ma5 = array;
                    }

                    JSONArray ma10jsonarray = jsonattr.optJSONArray(MA10);
                    if (ma10jsonarray != null) {
                        final int len = ma10jsonarray.length();
                        float[] array = new float[len];
                        for (int i = 0; i < len; i++) {
                            array[i] = (float) (ma10jsonarray.optDouble(i, 0f));
                        }
                        kdo.ma10 = array;
                    }

                    JSONArray ma20jsonarray = jsonattr.optJSONArray(MA20);
                    if (ma20jsonarray != null) {
                        final int len = ma20jsonarray.length();
                        float[] array = new float[len];
                        for (int i = 0; i < len; i++) {
                            array[i] = (float) (ma20jsonarray.optDouble(i, 0f));
                        }
                        kdo.ma20 = array;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kdo;
    }

    public static class KEntity extends Entity {
        public static final String FLAG = "K";
        public String time;
        public float kai;
        public float gao;
        public float di;
        public float shou;
        public int amount;
        public boolean isnode;

        public static JSONObject convertJson(KEntity k) throws Exception {
            JSONObject bodyjson = new JSONObject();
            bodyjson.putOpt(TIME, k.time);
            bodyjson.putOpt(GAO, k.gao);
            bodyjson.putOpt(KAI, k.kai);
            bodyjson.putOpt(DI, k.di);
            bodyjson.putOpt(SHOU, k.shou);
            bodyjson.putOpt(AMOUNT, k.amount);
            bodyjson.putOpt(ISNODE, k.isnode);

            // if (k.macd != null) {
            // bodyjson.putOpt(k.macd.flag, Macd.convertJson(k.macd));
            // }
            // if (k.dmi != null) {
            // bodyjson.putOpt(k.dmi.flag, Dmi.convertJson(k.dmi));
            // }
            // if (k.wr != null) {
            // bodyjson.putOpt(k.wr.flag, Wr.convertJson(k.wr));
            // }
            // if (k.boll != null) {
            // bodyjson.putOpt(k.boll.flag, Boll.convertJson(k.boll));
            // }
            // if (k.kdj != null) {
            // bodyjson.putOpt(k.kdj.flag, Kdj.convertJson(k.kdj));
            // }
            // if (k.rsi != null) {
            // bodyjson.putOpt(k.rsi.flag, Rsi.convertJson(k.rsi));
            // }
            return bodyjson;
        }

        public static KEntity parse(JSONObject jsonObject) {
            KEntity obj = new KEntity();
            try {
                obj.time = jsonObject.optString(TIME);
                obj.kai = Float.valueOf(jsonObject.optString(KAI));
                obj.gao = Float.valueOf(jsonObject.optString(GAO));
                obj.di = Float.valueOf(jsonObject.optString(DI));
                obj.shou = Float.valueOf(jsonObject.optString(SHOU));
                obj.amount = jsonObject.optInt(AMOUNT);
                obj.isnode = jsonObject.optBoolean(ISNODE);
                // JSONObject jsonmacd = jsonObject.getJSONObject(Macd.FLAG);
                // obj.macd = Macd.parse(jsonmacd);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }

    }

    public static class Macd extends Entity {
        public static final String FLAG = "macd";

        public float diff;
        public float dea;
        public float macd;

        // temp
        public float ema12;
        public float ema26;
        public float qian_ema12;
        public float qian_ema26;
        public float qian_dea;

        public static JSONObject convertJson(Macd macd) throws Exception {
            JSONObject bodyjson = new JSONObject();
            bodyjson.putOpt(DIFF, macd.diff);
            bodyjson.putOpt(DEA, macd.dea);
            bodyjson.putOpt(MACD, macd.macd);
            return bodyjson;
        }

        public static Macd parse(JSONObject jsonObject) {
            Macd obj = new Macd();
            try {
                obj.diff = Float.valueOf(jsonObject.optString(DIFF));
                obj.dea = Float.valueOf(jsonObject.optString(DEA));
                obj.macd = Float.valueOf(jsonObject.optString(MACD));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    public static class Dmi extends Entity {
        public static final String FLAG = "dmi";

        public float pdi;
        public float mdi;
        public float adx;
        public float adxr;

        public static JSONObject convertJson(Dmi dmi) throws Exception {
            JSONObject bodyjson = new JSONObject();
            bodyjson.putOpt(PDI, dmi.pdi);
            bodyjson.putOpt(MDI, dmi.mdi);
            bodyjson.putOpt(ADX, dmi.adx);
            bodyjson.putOpt(ADXR, dmi.adxr);
            return bodyjson;
        }

        public static Dmi parse(JSONObject jsonObject) {
            Dmi obj = new Dmi();
            try {
                obj.pdi = Float.valueOf(jsonObject.optString(PDI));
                obj.mdi = Float.valueOf(jsonObject.optString(MDI));
                obj.adx = Float.valueOf(jsonObject.optString(ADX));
                obj.adxr = Float.valueOf(jsonObject.optString(ADXR));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    public static class Wr extends Entity {
        public static final String FLAG = "wr";

        public float wr1;
        public float wr2;

        public static JSONObject convertJson(Wr wr) throws Exception {
            JSONObject bodyjson = new JSONObject();
            bodyjson.putOpt(WR1, wr.wr1);
            bodyjson.putOpt(WR2, wr.wr2);
            return bodyjson;
        }

        public static Wr parse(JSONObject jsonObject) {
            Wr obj = new Wr();
            try {
                obj.wr1 = Float.valueOf(jsonObject.optString(WR1));
                obj.wr2 = Float.valueOf(jsonObject.optString(WR2));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    public static class Boll extends Entity {
        public static final String FLAG = "boll";
        public float upper;
        public float mid;
        public float lowper;

        public static JSONObject convertJson(Boll boll) throws Exception {
            JSONObject bodyjson = new JSONObject();
            bodyjson.putOpt(UPPER, boll.upper);
            bodyjson.putOpt(MID, boll.mid);
            bodyjson.putOpt(LOWPER, boll.lowper);
            return bodyjson;
        }

        public static Boll parse(JSONObject jsonObject) {
            Boll obj = new Boll();
            try {
                obj.upper = Float.valueOf(jsonObject.optString(UPPER));
                obj.mid = Float.valueOf(jsonObject.optString(MID));
                obj.lowper = Float.valueOf(jsonObject.optString(LOWPER));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    public static class Kdj extends Entity {
        public static final String FLAG = "kdj";

        public float k;
        public float d;
        public float j;

        public static JSONObject convertJson(Kdj kdj) throws Exception {
            JSONObject bodyjson = new JSONObject();
            bodyjson.putOpt(K, kdj.k);
            bodyjson.putOpt(D, kdj.d);
            bodyjson.putOpt(J, kdj.j);
            return bodyjson;
        }

        public static Kdj parse(JSONObject jsonObject) {
            Kdj obj = new Kdj();
            try {
                obj.k = Float.valueOf(jsonObject.optString(K));
                obj.d = Float.valueOf(jsonObject.optString(D));
                obj.j = Float.valueOf(jsonObject.optString(J));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    public static class OBv extends Entity {
        public static final String FLAG = "obv";
        public long obv;
        public float scope;
        public String unit;

        public static JSONObject convertJson(OBv obj) throws Exception {
            JSONObject bodyjson = new JSONObject();
            bodyjson.putOpt(OBV, obj.obv);
            return bodyjson;
        }

        public static OBv parse(JSONObject jsonObject) {
            OBv obj = new OBv();
            try {
                obj.obv = Long.valueOf(jsonObject.optString(OBV));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    public static class Rsi extends Entity {
        public static final String FLAG = "rsi";

        public float rsi6;
        public float rsi12;
        public float rsi24;

        public static JSONObject convertJson(Rsi rsi) throws Exception {
            JSONObject bodyjson = new JSONObject();
            bodyjson.putOpt(RSI6, rsi.rsi6);
            bodyjson.putOpt(RSI12, rsi.rsi12);
            bodyjson.putOpt(RSI24, rsi.rsi24);
            return bodyjson;
        }

        public static Rsi parse(JSONObject jsonObject) {
            Rsi obj = new Rsi();
            try {
                obj.rsi6 = Float.valueOf(jsonObject.optString(RSI6));
                obj.rsi12 = Float.valueOf(jsonObject.optString(RSI12));
                obj.rsi24 = Float.valueOf(jsonObject.optString(RSI24));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    public static KDo produce(int type, int fuquan, String symbol,
            String timestart, String timeend, String requestDate, int count,
            boolean khas, boolean macdhas, boolean dmihas, boolean wrhas,
            boolean bollhas, boolean kdjhas, boolean obvhas, boolean rsihas) {
        KDo kDo = new KDo();
        if (requestDate == null || "".equals(requestDate) || "null".equals(requestDate)) {
            requestDate = Utils.date2();
        }

        KEntity[] tmp_data = new KEntity[count];

        float low = 10.37f;
        float high = 18.86f;
        Random random = new Random();
        float tmp_shou = 0;
        String tmp_date = requestDate;

        for (int index = 0; index < count; index++) {
            float kai;
            float gao;
            float di;
            float shou;
            int lian;
            String time;
            boolean isnode = false;

            // float r1 = convert(random.nextInt(range) / 100f);
            // float r2 = convert(random.nextInt(range) / 100f);
            // float r3 = convert(random.nextInt(range) / 100f);
            // float r4 = convert(random.nextInt(range) / 100f);

            if (index == 0) {
                tmp_shou = StockDo.randomRange(low, high);
            }
            float r1 = tmp_shou;
            float r2 = StockDo.randomNextOfScope(tmp_shou, 0.1f);
            float r3 = StockDo.randomNextOfScope(tmp_shou, 0.1f);
            float r4 = StockDo.randomNextOfScope(tmp_shou, 0.1f);

            float[] temp = new float[] {
                    r1, r2, r3, r4
            };

            int i, j, k;
            for (i = 0; i < 4; i++) {
                k = i;
                for (j = i + 1; j < 4; j++) {
                    if (temp[k] > temp[j])
                        k = j;
                }
                if (i != k) {
                    float tmp;
                    tmp = temp[i];
                    temp[i] = temp[k];
                    temp[k] = tmp;
                }
            }

            gao = temp[3];
            di = temp[0];

            int next = random.nextInt(2);
            if (next == 0) {
                kai = temp[1];
                shou = temp[2];
            } else {
                kai = temp[2];
                shou = temp[1];
            }

            tmp_shou = shou;

            kai = Utils.floatTo00(kai);
            gao = Utils.floatTo00(gao);
            shou = Utils.floatTo00(shou);
            di = Utils.floatTo00(di);

            lian = random.nextInt(20000);
            time = Utils.dateAfter(tmp_date, -index);

            if (time.endsWith("01")) {
                isnode = true;
            }

            KEntity item = new KEntity();
            item.kai = kai;
            item.gao = gao;
            item.di = di;
            item.shou = shou;
            item.time = time;
            item.amount = lian;
            item.isnode = isnode;

            tmp_data[index] = item;
        }
        kDo.type = type;
        kDo.fuquan = fuquan;
        if (khas) {
            kDo.karray = tmp_data;
        }
        if (macdhas) {
            kDo.macd = StockMath.computeMacd(tmp_data);
        }
        if (bollhas) {
            kDo.boll = StockMath.computeBOLL(tmp_data);
        }
        if (kdjhas) {
            kDo.kdj = StockMath.computeKDJ(tmp_data);
        }
        if (wrhas) {
            kDo.wr = StockMath.testWr(tmp_data);
        }
        if (rsihas) {
            kDo.rsi = StockMath.computeRSI(tmp_data);
        }
        if (obvhas) {
            kDo.obv = StockMath.computeOBv(tmp_data);
        }
        if (dmihas) {
        	kDo.dmi = StockMath.testDmi(tmp_data);
        }
        
        // kDo.ma5 = StockMath.testMA(tmp_data, KDo.MA5);
        // kDo.ma10 = StockMath.testMA(tmp_data, KDo.MA10);
        // kDo.ma20 = StockMath.testMA(tmp_data, KDo.MA20);

        kDo.name = "星星点点";
        kDo.symbol = symbol;
        kDo.success = true;
        kDo.message = "请求成功";
        kDo.timestart = tmp_data[count - 1].time;
        kDo.timeend = tmp_data[0].time;
        kDo.count = count;

        Date date = new Date();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
        kDo.responseDate = dateformat.format(date);
        kDo.responseTime = timeformat.format(date);

        return kDo;

    }

}
