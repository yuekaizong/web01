package kaizong.jee.web01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaizone.songmaya.jsyl.stock.data.KDo;
import kaizone.songmaya.jsyl.stock.data.KLineDo;
import kaizone.songmaya.jsyl.stock.data.MinuteDO;

import org.json.JSONObject;

public class StockServlet extends HttpServlet{
	
	public static final String FLAG ="flag";
	public static final String MINUTEDO ="minuteDo";
	public static final String KLINEDO ="klineDo";
	public static final String SYMBOL ="symbol";
	public static final String TIMESTART ="timestart";
	public static final String TIMEEND ="timeend";
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		String flag = req.getParameter(FLAG);
		if(MINUTEDO.equals(flag)){
			doMinute(req, resp);
		}
		else if(KLINEDO.equals(flag)){
			doKline(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	public void doMinute(HttpServletRequest req, HttpServletResponse resp){
		String symbol = req.getParameter(SYMBOL);
		String timestart = req.getParameter(TIMESTART);
		String timeend = req.getParameter(TIMEEND);
		MinuteDO minuteDO  = MinuteDO.produce(symbol, timestart, timeend);
		JSONObject jsonObject = null;
		try {
			jsonObject = MinuteDO.convertJson(minuteDO);
			PrintWriter out = resp.getWriter();
			out.println(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doKline(HttpServletRequest req, HttpServletResponse resp){
		String symbol = req.getParameter(SYMBOL);
		String timestart = req.getParameter(TIMESTART);
		String timeend = req.getParameter(TIMEEND);
		KDo result  = KDo.produce(symbol, timestart, timeend);
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
