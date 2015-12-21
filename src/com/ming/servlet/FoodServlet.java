package com.ming.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ming.entity.Food;
import com.ming.entity.FoodType;
import com.ming.factory.BeanFactory;
import com.ming.service.IFoodService;
import com.ming.service.IFoodTypeService;

/**
 * Servlet implementation class FoodServlet
 */
@WebServlet("/FoodServlet")
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 菜品
	IFoodService foodService = BeanFactory.getInstance("foodService", IFoodService.class);

	private Object url;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FoodServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

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
		} else if ("saveList".equals(methodName)) {
			saveList(request, response);
		}else if ("list".equals(methodName)) {
			list(request,response);
		}else if ("update".equals(methodName)) {
			update(request,response);
		}

	}

	/**
	 * 更新菜品
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int id = Integer.parseInt(request.getParameter("id").toString());
		Food food = foodService.findById(id);
		
		try {
			request.setAttribute("food", food);
			url=request.getRequestDispatcher("/sys/food/food_update.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
		goTo(request, response, url);
		
	}

	/**
	 * 列出所有菜品
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Food> foodList = foodService.getAll();
		
		try {
			request.setAttribute("foodList", foodList);
			url = request.getRequestDispatcher("/sys/food/food_list.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		goTo(request,response,url);
	}

	/**
	 * 存储列表展示
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void saveList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 菜类
		IFoodTypeService foodTypeServiceImpl = BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
		List<FoodType> listFoodType = foodTypeServiceImpl.getAll();

		try {
			request.setAttribute("listFoodType", listFoodType);

			url = request.getRequestDispatcher("/sys/food/food_save.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			url = "/error/error.jsp";
			throw new RuntimeException(e);
		}
		goTo(request, response, url);
	}

	/**
	 * 添加菜品
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		ServletFileUpload servletFileUpload = new ServletFileUpload();

		// 设置用于创建fileItem对象的工厂类
		servletFileUpload.setFileItemFactory(new DiskFileItemFactory());

		// 设置单个上传文件大小
		servletFileUpload.setFileSizeMax(30 * 1024 * 1024);

		// 设置总文件大小
		servletFileUpload.setSizeMax(100 * 1024 * 1024);

		// 判断是否是多文件上传
		if (servletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> items = servletFileUpload.parseRequest(request);
				Food food = new Food();
				for (FileItem fileItem : items) {
					if (fileItem.isFormField()) {
						// 普通表单处理
						String fieldName = fileItem.getFieldName();
						String value = fileItem.getString("utf-8");
						BeanUtils.setProperty(food, fieldName, value);
					} else {
						// 文件上传处理
						String name = fileItem.getName();
						String id = UUID.randomUUID().toString();
						name = id + "#" + name;

						// 得到存储路径
						String path = getServletContext().getRealPath("/upload");

						// 将上传的文件保存到upload文件中
						File file = new File(path, name);

						// 将图片路径封装到对象中
						food.setImg(path + "\\" + name);

						fileItem.write(file);

						// 删除临时目录
						fileItem.delete();
					}
				}
				foodService.save(food);
				url = request.getRequestDispatcher("/FoodServlet?method=list");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		// 调转
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

}
