package com.ming.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ming.entity.Orders;
import com.ming.factory.BeanFactory;
import com.ming.service.IOrdersService;

/**
 * Servlet implementation class OredersServlet
 */
@WebServlet("/OredersServlet")
public class OredersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IOrdersService ordersService = BeanFactory.getInstance("ordersService", IOrdersService.class);
	private Object url;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String methodName = request.getParameter("method");
		if ("list".equals(methodName)) {
			list(request, response);
		}
	}

	/**
	 * 展示列表概要
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Orders> ordersList = ordersService.getAll();
		try {
			request.setAttribute("ordersList", ordersList);
			url=request.getRequestDispatcher("/sys/orders/orders_list.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
		goTo(request, response, url);
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

	/**
	 * 跳转方法
	 * 
	 * @param request
	 * @param response
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */

	private void goTo(HttpServletRequest request, HttpServletResponse response, Object url)
			throws ServletException, IOException {
		if (url instanceof RequestDispatcher) {
			((RequestDispatcher) url).forward(request, response);
		} else if (url instanceof String) {
			response.sendRedirect(request.getContextPath() + "url");
		}
	}

}
