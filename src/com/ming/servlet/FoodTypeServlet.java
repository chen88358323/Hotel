package com.ming.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ming.entity.FoodType;
import com.ming.factory.BeanFactory;
import com.ming.service.IFoodTypeService;

/**
 * Servlet implementation class FoodTypeServlet
 */
@WebServlet("/FoodTypeServlet")
public class FoodTypeServlet extends HttpServlet {
	// 调用的菜系
	IFoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);

	// IFoodTypeErvice foodTypeService =new FoodTypeServiceImpl();

	// 跳转
	private Object url;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String methodName = request.getParameter("method");

		if ("addFoodType".equals(methodName)) {
			addFoodType(request, response);
		} else if ("list".equals(methodName)) {
			list(request, response);
		} else if ("viewUpdate".equals(methodName)) {
			viewUpdate(request, response);
		} else if ("delete".equals(methodName)) {
			delete(request, response);
		} else if ("update".equals(methodName)) {
			update(request, response);
		}else if ("search".equals(methodName)) {
			search(request,response);
		}
	}

	
	/**
	 * 根据菜系名称查询
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// TODO Auto-generated method stub
			String foodTypeName = request.getParameter("foodTyepName");
			List<FoodType> foodList = foodTypeService.getAll(foodTypeName);
			request.setAttribute("foodList", foodList);
			url = request.getRequestDispatcher("/sys/type/searchList.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			url = "/sys/error/error.jsp";
		}
		goTo(request, response, url);
	}

	/**
	 * 更新菜系
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// TODO Auto-generated method stub
			int id = Integer.parseInt(request.getParameter("id"));
			String foodTypeName = request.getParameter("foodTypeName");
			FoodType ft = new FoodType();
			ft.setId(id);
			ft.setTypeName(foodTypeName);
			foodTypeService.update(ft);
			url = request.getRequestDispatcher("/FoodTypeServlet?method=list");
		} catch (Exception e) {
			// TODO: handle exception
			url = "/sys/error/error.jsp";
		}

		goTo(request, response, url);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			String id = request.getParameter("id");
			foodTypeService.delete(Integer.parseInt(id));
			url = request.getRequestDispatcher("/FoodTypeServlet?method=list");
		} catch (Exception e) {
			// TODO: handle exception
			url = "/sys/error/error.jsp";
			e.printStackTrace();
			throw new RuntimeException();
		}
		goTo(request, response, url);
	}

	private void viewUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// TODO Auto-generated method stub
			String id = request.getParameter("id");
			FoodType ft = foodTypeService.findById(Integer.parseInt(id));
			request.setAttribute("foodType", ft);
			url = request.getRequestDispatcher("/sys/type/foodtype_update.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			url = "/sys/error/error.jsp";
			e.printStackTrace();
			throw new RuntimeException();
		}
		goTo(request, response, url);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// TODO Auto-generated method stub

			// 调用service查询所有类别
			List<FoodType> list = foodTypeService.getAll();

			request.setAttribute("listFoodType", list);

			// 跳转到菜系列表页面
			url = request.getRequestDispatcher("/sys/type/foodtype_list.jsp");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			url = "/sys/error/error.jsp";
		}

		goTo(request, response, url);
	}

	/**
	 * 添加菜系
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addFoodType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// TODO Auto-generated method stub
			// １、获取请求数据封装
			String foodTypeName = request.getParameter("foodTypeName");

			FoodType ft = new FoodType();
			ft.setTypeName(foodTypeName);

			// 2、调用service处理业务
			foodTypeService.save(ft);
			// 3、跳转
			url = request.getRequestDispatcher("/FoodTypeServlet?method=list");
		} catch (Exception e) {
			// TODO: handle exception
			url = "/sys/error/error.jsp";
		}

		// 转发
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
