package com.skishop.servlets.product;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.skishop.dao.product.ProductColorDao;
import com.skishop.dao.product.ProductDao;
import com.skishop.dao.product.ProductTypeDao;
import com.skishop.entity.Product;
import com.skishop.entity.ProductType;

/**
 * Servlet implementation class ProductAddServlet
 */
@WebServlet("/addProduct")
public class ProductAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductTypeDao ptd=new ProductTypeDao();
		List<ProductType> list=ptd.findAll();
		request.setAttribute("pts", list);
		request.setAttribute("action", "add");
		request.getRequestDispatcher("addproduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String name="";
		String color="";
		String pt="";
		String path="";
		try {
			List<FileItem> items = upload.parseRequest(request);
			for(FileItem fi:items){
				if(fi.isFormField()){
					if(fi.getFieldName().equals("pt"))
						pt=fi.getString();
					if(fi.getFieldName().equals("name"))
						name=fi.getString();
					if(fi.getFieldName().equals("color"))
						color=fi.getString();
				}else{
					String itemPath=this.getServletContext().getRealPath("/upload");
					File dir=new File(itemPath);
					if(!dir.exists()){
						dir.mkdir();
					}
					String extName=fi.getName().substring(fi.getName().lastIndexOf("."), fi.getName().length());
					String fileName=new Date().getTime()+extName;
					fi.write(new File(itemPath+"/"+fileName));
					path="upload/"+fileName;
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//保存数据
		Product p=new Product();
		p.setName(name);
		p.setProductTypeId(Integer.parseInt(pt));
		p.setListimg(path);
		ProductDao pd=new ProductDao();
		pd.addProduct(p);
		
		String colors[]=color.split(",");
		ProductColorDao pcd=new ProductColorDao();
		for(String c:colors){
			pcd.addColor();
		}
		response.sendRedirect("addProduct");
		
	}

}
