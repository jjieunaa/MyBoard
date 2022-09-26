package myboard.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myboard.handler.CommandHandler;

public class MVCController extends HttpServlet {

	// command(명령어), command처리객체(ListCommand, WriteCommand, UpdateCommand, DeleteCommand)
	Map<String, Object> commandMap = null;
	
	// 서블릿 최초 호출 시에 한 번만 수행
	@Override
	public void init() throws ServletException {
		// web.xml에 설정한 commandFile이라는 이름의 파라미터 변수의 값(properties 파일의 경로)을 가져옴
		String commandFile = getInitParameter("commandFile");
		// .properties 파일을 저장할 Properties 객체 생성
		Properties prop = new Properties();
		// 실제 서버 상의 .properties 파일의 경로를 획득
		String commandFilePath = getServletContext().getRealPath(commandFile);
		// .properties 파일에서 문자들을 읽어낼 파일리더
		FileReader fr = null;	
		try {
			// 파일리더 생성
			fr = new FileReader(commandFilePath);
			// properties 객체에 파일 내의 문자열이 String(키), String(값)의 형태로 저장
			prop.load(fr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// properties의 키들을 반복할 Iterator 획득
		Iterator it = prop.keySet().iterator();
		// commandMap을 생성
		commandMap = new HashMap<String, Object>();
		
		// 키가 있는 동안
		while (it.hasNext()) {
			// 명령어 획득
			String command = (String)it.next();
			// 명령어 처리 클래스의 문자열 획득
			String handlerClassStr = prop.getProperty(command);
			try {
				// 문자열에 해당하는 명령어 처리 클래스를 획득
				Class handlerClass = Class.forName(handlerClassStr);
				// 명령어 처리 객체를 획득
				CommandHandler handlerObj = (CommandHandler)handlerClass.newInstance();
				// 명령어와 명령어 처리 객체를 맵에 등록
				commandMap.put(command, handlerObj);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} // while
	} // init()
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 명령어 획득을 위해서 요청 URI 획득 예) /CommandMVC/list.do
		String command = req.getRequestURI();
		// 요청 URI가 Context Root로 시작하면 
		if (command.indexOf(req.getContextPath()) == 0) {
			// 명령어 추출
			command = command.substring(req.getContextPath().length()+1);
		}
		// *** handler 클래스의 객체(명령 처리 객체) 획득, 객체 지향의 다형성이 사용됨
		// ListCommand, WriteCommand, UpdateCommand, DeleteCommand 모두 CommandHandler 타입임
		// 그러므로 각각 타입으로 객체를 생성하는 게 아니라 상위 타입으로 객체를 생성함
		CommandHandler handler = (CommandHandler)commandMap.get(command);
		// 뷰 페이지
		String viewPage = null;
		try {
			// 뷰 페이지 반환
			viewPage = handler.process(req, resp);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (viewPage!=null) {
			// 뷰 페이지로 포워딩
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req, resp);
		}
	}
} // class
