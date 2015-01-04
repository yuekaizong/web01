package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaizone.songmaya.jsyl.stock.data.KDo;
import kaizone.songmaya.jsyl.stock.data.KDo.Boll;
import kaizone.songmaya.jsyl.stock.data.KDo.Dmi;
import kaizone.songmaya.jsyl.stock.data.KDo.Kdj;
import kaizone.songmaya.jsyl.stock.data.KDo.Macd;
import kaizone.songmaya.jsyl.stock.data.KDo.OBv;
import kaizone.songmaya.jsyl.stock.data.KDo.Rsi;
import kaizone.songmaya.jsyl.stock.data.KDo.Wr;
import kaizone.songmaya.jsyl.stock.data.KLineDo;
import kaizone.songmaya.jsyl.stock.data.MinuteDO;
import kaizone.songmaya.jsyl.stock.data.StockDo;
import kaizone.songmaya.jsyl.stock.data.KDo.KEntity;

import org.json.JSONObject;

public class StockServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		String flag = req.getParameter(StockDo.FLAG);
		if (MinuteDO.FLAG.equals(flag)) {
			doMinute(req, resp);
		} else if (KDo.FLAG.equals(flag)) {
			doKline(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	public void doMinute(HttpServletRequest req, HttpServletResponse resp) {
		String symbol = req.getParameter(StockDo.SYMBOL);
		String timestart = req.getParameter(StockDo.TIMESTART);
		String timeend = req.getParameter(StockDo.TIMEEND);
		String typetext = req.getParameter(MinuteDO.TYPE);
		int type = 0;
		if (typetext != null) {
			type = Integer.valueOf(typetext);
		}
		MinuteDO minuteDO = MinuteDO.produce(type, symbol, timestart, timeend);
		JSONObject jsonObject = null;
		try {
			jsonObject = MinuteDO.convertJson(minuteDO);
			PrintWriter out = resp.getWriter();
			out.println(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doKline(HttpServletRequest req, HttpServletResponse resp) {
		String symbol = req.getParameter(StockDo.SYMBOL);
		String timestart = req.getParameter(StockDo.TIMESTART);
		String timeend = req.getParameter(StockDo.TIMEEND);

		boolean khas = Boolean.valueOf(req.getParameter(KEntity.FLAG));
		boolean macdhas = Boolean.valueOf(req.getParameter(Macd.FLAG));
		boolean dmihas = Boolean.valueOf(req.getParameter(Dmi.FLAG));
		boolean wrhas = Boolean.valueOf(req.getParameter(Wr.FLAG));
		boolean bollhas = Boolean.valueOf(req.getParameter(Boll.FLAG));
		boolean kdjhas = Boolean.valueOf(req.getParameter(Kdj.FLAG));
		boolean obvhas = Boolean.valueOf(req.getParameter(OBv.FLAG));
		boolean rsihas = Boolean.valueOf(req.getParameter(Rsi.FLAG));
		String typetext = req.getParameter(KDo.TYPE);
		int type = 0;
		if (typetext != null) {
			type = Integer.valueOf(typetext);
		}
		String fuquantext = req.getParameter(KDo.FUQUAN);
		int fuquan = 0;
		if (fuquantext != null) {
			fuquan = Integer.valueOf(fuquantext);
		}

		KDo result = KDo.produce(type, fuquan, symbol, timestart, timeend,
				khas, macdhas, dmihas, wrhas, bollhas, kdjhas, obvhas, rsihas);
		JSONObject jsonObject = null;
		try {
			jsonObject = KDo.convertJson(result);
			PrintWriter out = resp.getWriter();
			out.println(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
