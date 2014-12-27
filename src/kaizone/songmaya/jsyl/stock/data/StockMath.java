
package kaizone.songmaya.jsyl.stock.data;

import java.util.Random;

import kaizone.android.b89.util.Utils;
import kaizone.songmaya.jsyl.stock.data.KDo.Boll;
import kaizone.songmaya.jsyl.stock.data.KDo.KEntity;
import kaizone.songmaya.jsyl.stock.data.KDo.Kdj;
import kaizone.songmaya.jsyl.stock.data.KDo.Macd;
import kaizone.songmaya.jsyl.stock.data.KDo.OBv;
import kaizone.songmaya.jsyl.stock.data.KDo.Rsi;
import kaizone.songmaya.jsyl.stock.data.KDo.Wr;
import kaizone.songmaya.jsyl.stock.data.KLineDo.K;

public class StockMath {

    public static Macd[] computeMacd(KEntity[] ks) {
        int len = ks.length;
        float tmp_ema12 = 0;
        float tmp_ema26 = 0;
        float tmp_dea = 0;
        Macd[] macds = new Macd[len];
        for (int i = len - 1; i >= 0; i--) {
            KEntity k = ks[i];
            Macd macd = new Macd();
            macd.qian_ema12 = tmp_ema12;
            macd.qian_ema26 = tmp_ema26;
            macd.qian_dea = tmp_dea;
            macd.ema12 = macd.qian_ema12 * 11 / 13 + k.shou * 2 / 13;
            macd.ema26 = macd.qian_ema26 * 25 / 27 + k.shou * 2 / 27;
            macd.diff = macd.ema12 - macd.ema26;
            macd.dea = macd.qian_dea * 8 / 10 + macd.diff * 2 / 10;
            macd.macd = 2 * (macd.diff - macd.dea);

            macd.diff = Utils.floatTo(macd.diff, 2);
            macd.dea = Utils.floatTo(macd.dea, 2);
            macd.macd = Utils.floatTo(macd.macd, 2);

            tmp_ema12 = macd.ema12;
            tmp_ema26 = macd.ema26;
            tmp_dea = macd.dea;
            macds[i] = macd;
        }
        return macds;
    }

    /**
     * WR1一般是6天买卖强弱指标； WR2一般是10天买卖强弱指标。 WR(N) = 100 * [ HIGH(N)-C ] / [
     * HIGH(N)-LOW(N) ] C：当日收盘价 HIGH(N)：N日内的最高价 LOW(n)：N日内的最低价
     * 
     * @param ks
     * @return
     */
    public static Wr[] computeWr(KEntity[] ks) {
        int len = ks.length;
        KEntity frist = ks[0];
        float high6 = frist.shou;
        float high10 = frist.shou;
        float low6 = frist.shou;
        float low10 = frist.shou;

        Wr[] wrs = new Wr[len];
        for (int i = 0; i < len; i++) {
            KEntity entity = ks[i];
            if (i < 6) {
                high6 = Math.max(high6, entity.shou);
                low6 = Math.min(low6, entity.shou);
            } else {
                for (int j = 0; j < 6; j++) {
                    int k = i - j;
                    KEntity qian = ks[k];
                    high6 = Math.max(high6, qian.shou);
                    low6 = Math.min(low6, qian.shou);
                }
            }

            if (i < 10) {
                high10 = Math.max(high10, entity.shou);
                low10 = Math.min(low10, entity.shou);
            } else {
                for (int j = 0; j < 10; j++) {
                    int k = i - j;
                    KEntity qian = ks[k];
                    high10 = Math.max(high10, qian.shou);
                    low10 = Math.min(low10, qian.shou);
                }
            }

            float wr1 = 100 * ((high6 - entity.shou) / (float) (high6 - low6));
            float wr2 = 100 * ((high10 - entity.shou) / (float) (high10 - low10));
            Wr wr = new Wr();
            wr.wr1 = wr1;
            wr.wr2 = wr2;
            wrs[i] = wr;
        }
        return wrs;
    }

    public static Boll[] computeBOLL(KEntity[] ks) {
        int len = ks.length;
        float sum = 0;
        float sum_f = 0;

        float mb = 0;
        float up = 0;
        float dn = 0;
        float qian_ma = 0;
        Boll[] bolls = new Boll[len];
        for (int i = 0; i < len; i++) {
            KEntity entity = ks[i];
            Boll boll = new Boll();

            sum += entity.shou;
            int n = i + 1;
            float ma = (sum / n);
            sum_f += (entity.shou - ma) * (entity.shou - ma);
            float md = (float) Math.sqrt((sum_f / n));

            mb = ma;
            up = mb + 2 * md;
            dn = mb - 2 * md;

            boll.mid = Utils.floatTo(qian_ma, 2);
            boll.upper = Utils.floatTo(up, 2);
            boll.lowper = Utils.floatTo(dn, 2);
            qian_ma = mb;
            bolls[i] = boll;
        }
        return bolls;
    }

    public static Kdj[] computeKDJ(KEntity[] ks) {
        int len = ks.length;

        float cn = 0;
        float ln = 0;
        float hn = 0;

        float qian_k = 0;
        float qian_d = 0;

        Kdj[] kdjs = new Kdj[len];
        for (int i = 0; i < len; i++) {
            KEntity entity = ks[i];

            cn = entity.shou;
            ln = Math.min(ln, entity.shou) == 0 ? entity.shou : Math.min(ln,
                    entity.shou);
            hn = Math.max(hn, entity.shou);

            float rsv = 0;
            if ((hn - ln) != 0) {
                rsv = ((cn - ln) / (hn - ln)) * 100;
            }

            if (qian_k == 0) {
                qian_k = 50;
            }//
            if (qian_d == 0) {
                qian_d = 50;
            }

            float k = (2 / 3f) * qian_k + (1 / 3f) * rsv;
            float d = (2 / 3f) * qian_d + (1 / 3f) * k;
            float j = (3 * k) - (2 * d);

            Kdj obj = new Kdj();
            obj.k = Utils.floatTo(k, 4);
            obj.d = Utils.floatTo(d, 4);
            obj.j = Utils.floatTo(j, 4);
            kdjs[i] = obj;

            qian_d = d;
            qian_k = k;
        }

        return kdjs;
    }

    public static OBv[] computeOBv(KEntity[] ks) {
        int len = ks.length;

        OBv[] obv = new OBv[len];
        long sum = 0;
        for (int i = 0; i < len; i++) {
            float scope = 0;
            String unit = null;
            ;
            if (i == 0) {
                sum = 0;
            } else {
                float ori = ks[i].shou - ks[i - 1].shou;
                if (ori < 0) {
                    sum -= ks[i].amount;
                } else {
                    sum += ks[i].amount;
                }

                if (sum / 1000000000 > 1) {
                    unit = "亿";
                    scope = Utils.floatTo(sum / 1000000000f, 1);
                }

                else if (sum / 10000 > 1) {
                    unit = "万";
                    scope = Utils.floatTo(sum / 10000, 1);
                }

                else {
                    unit = "手";
                    scope = sum;
                }
            }

            OBv obj = new OBv();
            obj.unit = unit;
            obj.scope = scope;
            obj.obv = sum;
            obv[i] = obj;
        }

        return obv;
    }

    public static Rsi[] computeRSI(KEntity[] ks) {
        int len = ks.length;
        float sum_zhang = 0;
        float sum_die = 0;

        Rsi[] rsis = new Rsi[len];

        float rsi6 = 0;
        float rsi12 = 0;
        float rsi24 = 0;
        for (int i = 0; i < len; i++) {
            if (i > 0 && i <= 6) {
                float fu = ks[i].shou - ks[i - 1].shou;
                if (fu >= 0) {
                    sum_zhang += fu;
                } else {
                    sum_die -= fu;
                }
                float mean_zhang = sum_zhang / i;
                float menu_die = sum_die / i;
                rsi6 = (mean_zhang / (mean_zhang + menu_die)) * 100;
                rsi12 = (mean_zhang / (mean_zhang + menu_die)) * 100;
                rsi24 = (mean_zhang / (mean_zhang + menu_die)) * 100;
            }//

            if (i > 6) {
                int j = i - 6;
                for (; j <= i; j++) {
                    float fu = ks[i].shou - ks[i - 1].shou;
                    if (fu >= 0) {
                        sum_zhang += fu;
                    } else {
                        sum_die -= fu;
                    }
                    float mean_zhang = sum_zhang / i;
                    float menu_die = sum_die / i;
                    rsi6 = (mean_zhang / (mean_zhang + menu_die)) * 100;
                    rsi12 = (mean_zhang / (mean_zhang + menu_die)) * 100;
                    rsi24 = (mean_zhang / (mean_zhang + menu_die)) * 100;
                }
            }

            if (i > 12) {
                int j = i - 12;
                for (; j <= i; j++) {
                    float fu = ks[i].shou - ks[i - 1].shou;
                    if (fu >= 0) {
                        sum_zhang += fu;
                    } else {
                        sum_die -= fu;
                    }
                    float mean_zhang = sum_zhang / i;
                    float menu_die = sum_die / i;
                    rsi12 = (mean_zhang / (mean_zhang + menu_die)) * 100;
                    rsi24 = (mean_zhang / (mean_zhang + menu_die)) * 100;
                }
            }

            if (i > 24) {
                int j = i - 24;
                for (; j <= i; j++) {
                    float fu = ks[i].shou - ks[i - 1].shou;
                    if (fu >= 0) {
                        sum_zhang += fu;
                    } else {
                        sum_die -= fu;
                    }
                    float mean_zhang = sum_zhang / i;
                    float menu_die = sum_die / i;
                    rsi24 = (mean_zhang / (mean_zhang + menu_die)) * 100;
                }
            }

            Rsi rsi = new Rsi();
            rsi.rsi6 = rsi6;
            rsi.rsi12 = rsi12;
            rsi.rsi24 = rsi24;
            rsis[i] = rsi;
        }
        return rsis;
    }

    public static Wr[] testWr(KEntity[] ks) {
        int len = ks.length;
        Wr[] wrs = new Wr[len];
        for (int i = 0; i < len; i++) {
            Wr wr = new Wr();
            Random random = new Random();
            wr.wr1 = Utils.floatTo(random.nextInt(100) + random.nextFloat(), 4);
            wr.wr2 = Utils.floatTo(random.nextInt(100) + random.nextFloat(), 4);
            wrs[i] = wr;
        }
        return wrs;
    }

}
