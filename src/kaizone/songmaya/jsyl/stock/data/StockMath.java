
package kaizone.songmaya.jsyl.stock.data;

import java.util.Random;

import kaizone.android.b89.util.Utils;
import kaizone.songmaya.jsyl.stock.data.KDo.Boll;
import kaizone.songmaya.jsyl.stock.data.KDo.Dmi;
import kaizone.songmaya.jsyl.stock.data.KDo.KEntity;
import kaizone.songmaya.jsyl.stock.data.KDo.Kdj;
import kaizone.songmaya.jsyl.stock.data.KDo.Macd;
import kaizone.songmaya.jsyl.stock.data.KDo.OBv;
import kaizone.songmaya.jsyl.stock.data.KDo.Rsi;
import kaizone.songmaya.jsyl.stock.data.KDo.Wr;

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

    public static Wr[] computeWr(KEntity[] ks) {
        int len = ks.length;
        KEntity frist = ks[len - 1];
        final int n1 = 6;
        final int n2 = 10;

        float high6 = frist.shou;
        float high10 = frist.shou;
        float low6 = frist.shou;
        float low10 = frist.shou;

        Wr[] wrs = new Wr[len];
        int k = 0;
        for (int i = len - 1; i >= 0; i--) {
            KEntity entity = ks[i];

            high6 = Math.max(high6, entity.shou);
            low6 = Math.min(low6, entity.shou);
            high10 = Math.max(high10, entity.shou);
            low10 = Math.min(low10, entity.shou);

            if (k >= n1 - 1) {
                high6 = entity.shou;
                low6 = entity.shou;
                for (int j = n1 - 1; j >= 0; j--) {
                    KEntity qian = ks[i + j];
                    high6 = Math.max(high6, qian.shou);
                    low6 = Math.min(low6, qian.shou);
                }
            }

            if (k >= n2 - 1) {
                high10 = entity.shou;
                low10 = entity.shou;
                for (int j = n2 - 1; j >= 0; j--) {
                    KEntity qian = ks[i + j];
                    high10 = Math.max(high10, qian.shou);
                    low10 = Math.min(low10, qian.shou);
                }
            }

            float wr1 = 0;
            if (high6 != low6) {
                wr1 = 100 * ((high6 - entity.shou) / (float) (high6 - low6));
            }
            float wr2 = 0;
            if (high10 != low10) {
                wr2 = 100 * ((high10 - entity.shou) / (float) (high10 - low10));
            }
            Wr wr = new Wr();
            wr.wr1 = wr1;
            wr.wr2 = wr2;
            wrs[i] = wr;
            k++;
        }
        return wrs;
    }

    /**
     * WR1一般是6天买卖强弱指标； WR2一般是10天买卖强弱指标。 WR(N) = 100 * [ HIGH(N)-C ] / [
     * HIGH(N)-LOW(N) ] C：当日收盘价 HIGH(N)：N日内的最高价 LOW(n)：N日内的最低价
     * 
     * @param ks
     * @return
     */
    public static Wr[] testWr(KEntity[] ks) {
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
                high6 = entity.shou;
                low6 = entity.shou;
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
                high10 = entity.shou;
                low10 = entity.shou;
                for (int j = 0; j < 10; j++) {
                    int k = i - j;
                    KEntity qian = ks[k];
                    high10 = Math.max(high10, qian.shou);
                    low10 = Math.min(low10, qian.shou);
                }
            }

            float wr1 = 0;
            if (high6 != low6) {
                wr1 = 100 * ((high6 - entity.shou) / (float) (high6 - low6));
            }
            float wr2 = 0;
            if (high10 != low10) {
                wr2 = 100 * ((high10 - entity.shou) / (float) (high10 - low10));
            }
            Wr wr = new Wr();
            wr.wr1 = wr1;
            wr.wr2 = wr2;
            wrs[i] = wr;
        }
        return wrs;
    }

    public static Boll[] computeBOLL(KEntity[] ks) {
        if (ks == null)
            return null;
        int len = ks.length;
        float sum_f = 0;

        float mb = 0;
        float up = 0;
        float dn = 0;
        float qian_ma = 0;
        Boll[] bolls = new Boll[len];
        int k = 0;
        for (int i = len - 1; i >= 0; i--) {
            Boll boll = new Boll();
            KEntity entity = ks[i];
            if (k >= 19) {
                float sum = 0;
                for (int j = 0; j < 20; j++) {
                    KEntity obj = ks[i + j];
                    sum += obj.shou;
                }
                float ma = (sum / 20);
                sum_f = (entity.shou - ma) * (entity.shou - ma);
                float md = (float) Math.sqrt((sum_f / 20));

                mb = ma;
                up = mb + 2 * md;
                dn = mb - 2 * md;

                boll.mid = Utils.floatTo(qian_ma, 2);
                boll.upper = Utils.floatTo(up, 2);
                boll.lowper = Utils.floatTo(dn, 2);
                qian_ma = mb;
                bolls[i] = boll;
            } else {
                float sum = 0;
                KEntity obj = ks[i];
                sum += obj.shou;
                int n = i + 1;
                float ma = (sum / n);
                sum_f = (entity.shou - ma) * (entity.shou - ma);
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
            k++;
        }
        return bolls;
    }

    public static Boll[] testBOLL(KEntity[] ks) {
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
        int n = 9;
        int len = ks.length;

        float k = 0;
        float d = 0;
        float j = 0;

        float cn = ks[len - 1].shou;
        float ln = ks[len - 1].di;
        float hn = ks[len - 1].gao;

        float qian_k = 0;
        float qian_d = 0;

        Kdj[] kdjs = new Kdj[len];
        int index = 0;
        for (int a = len - 1; a >= 0; a--) {
            KEntity entity = ks[a];
            if (index >= n - 1) {
                ln = entity.di;
                hn = entity.gao;
                cn = entity.shou;
                for (int b = n - 1; b >= a; b--) {
                    KEntity obj = ks[a + b];
                    ln = Math.min(ln, obj.di);
                    hn = Math.max(hn, obj.gao);
                }
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

                k = (2 / 3f) * qian_k + (1 / 3f) * rsv;
                d = (2 / 3f) * qian_d + (1 / 3f) * k;
                j = (3 * k) - (2 * d);

            } else {
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

                k = (2 / 3f) * qian_k + (1 / 3f) * rsv;
                d = (2 / 3f) * qian_d + (1 / 3f) * k;
                j = (3 * k) - (2 * d);
            }

            Kdj obj = new Kdj();
            obj.k = Utils.floatTo(k, 4);
            obj.d = Utils.floatTo(d, 4);
            obj.j = Utils.floatTo(j, 4);
            kdjs[a] = obj;

            qian_d = d;
            qian_k = k;
            index++;
        }

        return kdjs;
    }

    public static Kdj[] testKDJ(KEntity[] ks) {
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
        for (int i = len - 1; i >= 0; i--) {
            float scope = 0;
            String unit = null;
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
        int k = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (k > 0 && k <= 6) {
                float fu = ks[i].shou - ks[i].prevClose;
                if (fu >= 0) {
                    sum_zhang += fu;
                } else {
                    sum_die -= fu;
                }
                float mean_zhang = sum_zhang / i;
                float menu_die = sum_die / i;
                float base = mean_zhang + menu_die;
                if (base == 0) {
                    base = 1;
                }
                rsi6 = (mean_zhang / base) * 100;
                rsi12 = (mean_zhang / base) * 100;
                rsi24 = (mean_zhang / base) * 100;
            }//

            if (k >= 5) {
                int j = i + 5;
                for (; j >= i; j--) {
                    float fu = ks[i].shou - ks[i].prevClose;
                    if (fu >= 0) {
                        sum_zhang += fu;
                    } else {
                        sum_die -= fu;
                    }
                }
                float mean_zhang = sum_zhang / 6;
                float menu_die = sum_die / 6;
                rsi6 = (mean_zhang / (mean_zhang + menu_die)) * 100;
                rsi12 = (mean_zhang / (mean_zhang + menu_die)) * 100;
                rsi24 = (mean_zhang / (mean_zhang + menu_die)) * 100;
            }

            if (k >= 11) {
                int j = i + 11;
                for (; j >= i; j--) {
                    float fu = ks[i].shou - ks[i].prevClose;
                    if (fu >= 0) {
                        sum_zhang += fu;
                    } else {
                        sum_die -= fu;
                    }
                }
                float mean_zhang = sum_zhang / 12;
                float menu_die = sum_die / 12;
                rsi12 = (mean_zhang / (mean_zhang + menu_die)) * 100;
                rsi24 = (mean_zhang / (mean_zhang + menu_die)) * 100;
            }

            if (k >= 23) {
                int j = i + 23;
                for (; j >= i; j--) {
                    float fu = ks[i].shou - ks[i].prevClose;
                    if (fu >= 0) {
                        sum_zhang += fu;
                    } else {
                        sum_die -= fu;
                    }
                }
                float mean_zhang = sum_zhang / 24;
                float menu_die = sum_die / 24;
                rsi24 = (mean_zhang / (mean_zhang + menu_die)) * 100;
            }

            Rsi rsi = new Rsi();
            rsi.rsi6 = rsi6;
            rsi.rsi12 = rsi12;
            rsi.rsi24 = rsi24;
            rsis[i] = rsi;
            k++;
        }
        return rsis;
    }

    public static Dmi[] testDmi(KEntity[] ks) {
        int len = ks.length;
        Dmi[] dmis = new Dmi[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            Dmi dmi = new Dmi();
            dmi.pdi = Utils
                    .floatTo(random.nextInt(100) + random.nextFloat(), 4);
            dmi.mdi = Utils
                    .floatTo(random.nextInt(100) + random.nextFloat(), 4);
            dmi.adx = Utils
                    .floatTo(random.nextInt(100) + random.nextFloat(), 4);
            dmi.adxr = Utils.floatTo(random.nextInt(100) + random.nextFloat(),
                    4);
            dmis[i] = dmi;
        }
        return dmis;
    }

    public static Dmi[] computeDmi(KEntity[] ks) {
        int n = 14;
        int len = ks.length;
        Dmi[] dmis = new Dmi[len];
        KEntity prevK = ks[len - 1];
        int k = 0;

        float prev_adx = 0;
        float h_di_sum = 0;
        float l_di_sum = 0;
        float h_dm_sum = 0;
        float l_dm_sum = 0;
        float tr_sum = 0;
        float dx_sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            Dmi dmi = new Dmi();
            if (k >= n - 1) {
                h_di_sum = 0;
                l_di_sum = 0;
                h_dm_sum = 0;
                l_dm_sum = 0;
                tr_sum = 0;
                dx_sum = 0;
                for (int j = n - 1; j >= 0; j--) {
                    int m = i + j;
                    KEntity obj = ks[m];
                    if (m >= len - 1) {
                        prevK = ks[i + j];
                    } else {
                        prevK = ks[i + j + 1];
                    }
                    float h_dm = obj.gao - prevK.gao;
                    if (h_dm <= 0) {
                        h_dm = 0;
                    }
                    float l_dm = prevK.di - obj.di;
                    if (l_dm <= 0) {
                        l_dm = 0;
                    }

                    if (h_dm > l_dm) {
                        l_dm = 0;
                    } else if (h_dm < l_dm) {
                        h_dm = 0;
                    } else {
                        h_dm = 0;
                        l_dm = 0;
                    }

                    float h_l = Math.abs(obj.gao - obj.di);
                    float h_pc = Math.abs(obj.gao - obj.prevClose);
                    float l_pc = Math.abs(obj.di - obj.prevClose);
                    float[] trs = new float[] {
                            h_l, h_pc, l_pc
                    };
                    trs = Utils.arraySortAsc(trs);
                    float tr = trs[trs.length - 1];
                    float h_di = (h_dm / tr) * 100;
                    float l_di = (l_dm / tr) * 100;

                    h_di_sum += h_di;
                    l_di_sum += l_di;
                    h_dm_sum += h_dm;
                    l_dm_sum += l_dm;
                    tr_sum += tr;
                }

                float h_di_n = (h_di_sum / n) / (tr_sum / n) * 100;
                float l_di_n = (l_di_sum / n) / (tr_sum / n) * 100;

                float di_dif = Math.abs(h_di_n - l_di_n);
                float di_sum = Math.abs(h_di_n + l_di_n);
                if (di_sum == 0) {
                    di_sum = 1;
                }
                float dx = (di_dif / di_sum) * 100;
                float adx = dx;
                float adxr = (adx + prev_adx) / 2;

                dmi.pdi = h_di_n;
                dmi.mdi = l_di_n;
                dmi.adx = adx;
                dmi.adxr = adxr;

                prev_adx = adx;
            } else {
                KEntity obj = ks[i];
                float h_dm = obj.gao - prevK.gao;
                if (h_dm <= 0) {
                    h_dm = 0;
                }

                float l_dm = prevK.di - obj.di;
                if (l_dm <= 0) {
                    l_dm = 0;
                }

                if (h_dm > l_dm) {
                    l_dm = 0;
                } else {
                    h_dm = 0;
                }

                float h_l = Math.abs(obj.gao - obj.di);
                float h_pc = Math.abs(obj.gao - obj.prevClose);
                float l_pc = Math.abs(obj.di - obj.prevClose);
                float[] trs = new float[] {
                        h_l, h_pc, l_pc
                };
                trs = Utils.arraySortAsc(trs);
                float tr = trs[trs.length - 1];
                float h_di = (h_dm / tr) * 100;
                float l_di = (l_dm / tr) * 100;

                h_di_sum += h_di;
                l_di_sum += l_di;
                h_dm_sum += h_dm;
                l_dm_sum += l_dm;
                tr_sum += tr;

                int num = k + 1;
                float h_di_n = (h_di_sum / num) / (tr_sum / num) * 100;
                float l_di_n = (l_di_sum / num) / (tr_sum / num) * 100;

                float di_dif = Math.abs(h_di_n - l_di_n);
                float di_sum = Math.abs(h_di_n + l_di_n);
                if (di_sum == 0) {
                    di_sum = 1;
                }
                float dx = (di_dif / di_sum) * 100;
                float adx = dx;
                float adxr = (adx + prev_adx) / 2;

                dmi.pdi = h_di_n;
                dmi.mdi = l_di_n;
                dmi.adx = adx;
                dmi.adxr = adxr;

                prev_adx = adx;
                prevK = ks[i];
            }
            dmis[i] = dmi;
            k++;
        }
        return dmis;
    }

    public static float[] testMA(KEntity[] ks, String flagMA) {
        int count = 0;
        float scope = 0.1f;
        float diff = 0.5f;
        final int len = ks.length;
        if (len > 20) {
            if (flagMA.equals(KDo.MA5)) {
                count = len - 5;
                diff = 0.5f;
            } else if (flagMA.equals(KDo.MA10)) {
                count = len - 10;
                diff = 1f;
            } else if (flagMA.equals(KDo.MA20)) {
                count = len - 20;
                diff = 1.5f;
            }
        } else {
            count = len;
        }

        float[] mas = new float[count];
        for (int i = 0; i < count; i++) {
            final float price = ks[i].shou - diff;
            mas[i] = Utils.floatTo(StockDo.randomNextOfScope(price, scope), 2);
        }
        return mas;
    }

}
