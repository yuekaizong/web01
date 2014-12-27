
package kaizone.songmaya.jsyl.stock.data;

import java.math.BigDecimal;
import java.util.Random;

import kaizone.android.b89.util.Utils;

public class KLineDo extends StockDo {

    public KB kb;

    public static class KB {
        public int count;
        public B b;
        public K[] k;
        public float[] day;

    }

    public static class B {
        public float p0;
        public float p1;
        public float p2;
        public float p3;
        public float p4;

        public float[] ps;

        public B() {
        }

        public B(float _p0, float _p1, float _p2, float _p3, float _p4) {
            p0 = _p0;
            p1 = _p1;
            p2 = _p2;
            p3 = _p3;
            p4 = _p4;
            ps = new float[5];
            ps[0] = p0;
            ps[1] = p1;
            ps[2] = p2;
            ps[3] = p3;
            ps[4] = p4;
        }
    }

    public static class K {
        public String time;
        public float kai;
        public float gao;
        public float di;
        public float shou;
        public int lian;
        public boolean month = false;

        public float macd;
        public float diff;
        public float dea;

        public float ema12;
        public float ema26;
        public float qian_ema12;
        public float qian_ema26;
        public float qian_dea;

        public K() {
        }

        public K(float _kai, float _gao, float _di, float _shou) {
            kai = _kai;
            gao = _gao;
            di = _di;
            shou = _shou;
        }

        public K(float _kai, float _gao, float _di, float _shou, int _lian) {
            kai = _kai;
            gao = _gao;
            di = _di;
            shou = _shou;
            lian = _lian;
        }

        public K(float _kai, float _gao, float _di, float _shou, int _lian,
                String _time) {
            kai = _kai;
            gao = _gao;
            di = _di;
            shou = _shou;
            lian = _lian;
            time = _time;
            if (time.endsWith("01")) {
                month = true;
            }
        }
    }

    public static KLineDo parse() {
        KLineDo kLineDo = new KLineDo();

        K k1 = new K(12.70f, 13.04f, 12.05f, 12.90f);
        K k2 = new K(12.06f, 13.18f, 11.08f, 12.01f);
        K k3 = new K(12.45f, 13.19f, 10.08f, 12.10f);
        B b = new B(10.37f, 12.37f, 14.37f, 16.37f, 18.86f);
        KB kb = new KB();
        kb.k = new K[] {
                k1, k2, k3
        };
        kb.b = b;

        kLineDo.kb = kb;

        return kLineDo;

    }

    public static KLineDo produce(String symbol, String timestart, String timeend) {
        KLineDo kLineDo = new KLineDo();

        B b = new B(10.37f, 12.37f, 14.37f, 16.37f, 18.86f);
        final int count = 150;
        K[] tmp_arrays = new K[count];
        float[] tmp_days = new float[count];

        float low = 10.37f;
        float high = 18.86f;
        int range = (int) (high * 100 - low * 100);
        range *= 0.1;

        Random random = new Random();
        float tmp_shou = 0;
        String tmp_time = Utils.date2();
        for (int index = 0; index < count; index++) {
            float kai;
            float gao;
            float di;
            float shou;
            int lian;
            String time;

            // float r1 = convert(random.nextInt(range) / 100f);
            // float r2 = convert(random.nextInt(range) / 100f);
            // float r3 = convert(random.nextInt(range) / 100f);
            // float r4 = convert(random.nextInt(range) / 100f);

            if (index == 0) {
                tmp_shou = randomRange(low, high);
            }
            float r1 = tmp_shou;
            float r2 = randomNextOfScope(tmp_shou, 0.1f);
            float r3 = randomNextOfScope(tmp_shou, 0.1f);
            float r4 = randomNextOfScope(tmp_shou, 0.1f);

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

            kai = convert(kai);
            gao = convert(gao);
            shou = convert(shou);
            di = convert(di);

            lian = random.nextInt(20000);
            time = Utils.dateAfter(tmp_time, index);

            tmp_arrays[index] = new K(kai, gao, di, shou, lian, time);
            tmp_days[index] = shou;
        }

        KB kb = new KB();
        kb.b = b;
        kb.k = tmp_arrays;
        kb.k = computeMacd(kb.k);
        kb.day = tmp_days;

        kLineDo.kb = kb;

        return kLineDo;
    }

    public static K[] computeMacd(K[] ks) {
        int len = ks.length;
        float tmp_ema12 = 0;
        float tmp_ema26 = 0;
        float tmp_dea = 0;
        for (int i = len - 1; i >= 0; i--) {
            K k = ks[i];
            k.qian_ema12 = tmp_ema12;
            k.qian_ema26 = tmp_ema26;
            k.qian_dea = tmp_dea;
            k.ema12 = k.qian_ema12 * 11 / 13 + k.shou * 2 / 13;
            k.ema26 = k.qian_ema26 * 25 / 27 + k.shou * 2 / 27;
            k.diff = k.ema12 - k.ema26;
            k.dea = k.qian_dea * 8 / 10 + k.diff * 2 / 10;
            k.macd = 2 * (k.diff - k.dea);

            k.diff = Utils.floatTo00(k.diff);
            k.dea = Utils.floatTo00(k.dea);
            k.macd = Utils.floatTo00(k.macd);

            tmp_ema12 = k.ema12;
            tmp_ema26 = k.ema26;
            tmp_dea = k.dea;
        }
        return ks;
    }

    public static float convert(float value) {
        // long l1 = Math.round(value * 100); // 四舍五入
        // float ret = l1 / 100.0; // 注意：使用 100.0 而不是 100
        // return ret;
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, BigDecimal.ROUND_DOWN);
        return bd.floatValue();
    }

}
