package com.ming.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ming.entity.DinnerTable;
import com.ming.factory.BeanFactory;
import com.ming.service.IDinnerTableService;

/**
 * Servlet implementation class DinnerTableServlet
 */
@WebServlet("/DinnerTableServlet")
public class DinnerTableServlet extends HttpServlet {
	private IDinnerTableService dinnerTableService = BeanFactory.getInstance("dinnerTableService",
			IDinnerTableService.class);
	private Object url;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text.html;charset=utf-8");

		String methodName = request.getParameter("method");

		if ("save".equals(methodName)) {
			save(request, response);
		} else if ("list".equals(methodName)) {
			list(request, response);
		} else if ("update".equals(methodName)) {
			update(request, response);
		} else if ("delete".equals(methodName)) {
			delete(request, response);
		} else if ("search".equals(methodName)) {
			search(request, response);
		}
	}

	/**
	 * 根据餐桌状态搜索
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String keyName = request.getParameter("keyName");
		
		List<DinnerTable> dinnerTableList = null;
		if ("预定".equals(keyName)) {
			dinnerTableList = dinnerTableService.getStaAll(1);

			// 将查询结果封装到request域中
			request.setAttribute("dinnerTableList", dinnerTableList);

			url = request.getRequestDispatcher("/sys/table/dinnertable_list.jsp");
		} else if ("空闲".equals(keyName)) {
			dinnerTableList = dinnerTableService.getStaAll(0);

			// 将查询结果封装到request域中
			request.setAttribute("dinnerTableList", dinnerTableList);

			url = request.getRequestDispatcher("/sys/table/dinnertable_list.jsp");
		}
		goTo(request, response, url);
	}

	/**
	 * 删除餐桌
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 得到参数
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);

		try {
			// 删除餐桌
			dinnerTableService.delete(id);
			// 跳转到展示页面
			url = request.getRequestDispatcher("/DinnerTableServlet?method=list");
		} catch (Exception e) {
			// TODO: handle exception
			url = "/error/error.jsp";
			throw new RuntimeException(e);
		}
		goTo(request, response, url);
	}

	/**
	 * 预定或者退桌
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 得到参数
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);

		// 根据得到的id查询对应的餐桌
		DinnerTable dinnerTable = dinnerTableService.findById(id);

		try {
			// 更新餐桌信息
			dinnerTableService.update(dinnerTable);

			// 跳转到展示页面
			url = request.getRequestDispatcher("/DinnerTableServlet?method=list");
		} catch (Exception e) {
			// TODO: handle exception
			url = "/error/error.jsp";
			throw new RuntimeException(e);
		}
		goTo(request, response, url);

	}

	/**
	 * 列出所有餐桌
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			// 得到所有餐桌信息
			List<DinnerTable> dinnerTableList = dinnerTableService.getAll();

			// System.out.println("here:"+dinnerTableList);

			request.setAttribute("dinnerTableList", dinnerTableList);
			url = request.getRequestDispatcher("/sys/table/dinnertable_list.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			url = "/error/error.jsp";
			throw new RuntimeException(e);
		}
		goTo(request, response, url);
	}

	/**
	 * 添加方法
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// TODO Auto-generated method stub
			// 得到餐桌名字
			String tableName = request.getParameter("tableName");
			DinnerTable dinnerTable = new DinnerTable();
			dinnerTable.setTableName(tableName);

			// 保存餐桌信息
			dinnerTableService.save(dinnerTable);

			url = request.getRequestDispatcher("/DinnerTableServlet?method=list");
		} catch (Exception e) {
			// TODO: handle exception
			url = "/error/error.jsp";
			throw new RuntimeException(e);
		}
		goTo(request, response, url);
	}

	/**
	 * 跳转方法
	 * 
	 * @param request
	 * @param response
	 * @param url2
	 * @throws IOException
	 * @throws ServletException
	 */
	private void goTo(HttpServletRequest request, HttpServletResponse response, Object url2)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (url instanceof RequestDispatcher) {
			((RequestDispatcher) url).forward(request, response);
		} else if (url instanceof String) {
			response.sendRedirect(request.getContextPath() + "url");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
