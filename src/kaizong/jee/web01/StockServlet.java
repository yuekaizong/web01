package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import kaizone.songmaya.jsyl.stock.data.JSONResponse;
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
		} else if("config".equals(flag)){
			doConfig(req, resp);
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

		String requestDate = req.getParameter(KDo.REQUESTDATE);

		String counttext = req.getParameter(KDo.COUNT);
		int count = 0;
		if (fuquantext != null) {
			count = Integer.valueOf(counttext);
		}

		KDo result = KDo.produce(type, fuquan, symbol, timestart, timeend,
				requestDate, count, khas, macdhas, dmihas, wrhas, bollhas,
				kdjhas, obvhas, rsihas);
		JSONObject jsonObject = null;
		try {
			jsonObject = KDo.convertJson(result);
			PrintWriter out = resp.getWriter();
			out.println(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doConfig(HttpServletRequest req, HttpServletResponse resp){
		JSONResponse obj = new JSONResponse();
		obj.message = "请求成功";
		obj.success = true;
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
		obj.responseDate = dateformat.format(date);
		obj.responseTime = timeformat.format(date);
		
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONResponse.convertJson(obj);
			PrintWriter out = resp.getWriter();
			out.println(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
