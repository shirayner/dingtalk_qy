package com.ray.dingtalk.qy.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.dingtalk.qy.util.HttpHelper;


/**
 * Servlet implementation class UploadImgServlet
 */
@WebServlet("/UploadImgServlet")
public class UploadImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadImgServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.将请求、响应的编码均设置为UTF-8（防止中文乱码）  
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8"); 
		System.out.println("jrebel------------");
		
		//1.获取imgUrl
		String imgUrl = request.getParameter("imgUrl");
		System.out.println("imgUrl:"+imgUrl);

		//2.获取mediaId
		int beginIndex=imgUrl.indexOf("media/")+6;
		int lastIndex=imgUrl.indexOf("_");     //imgUrl:http://static.dingtalk.com/media/lADPBbCc1QwC00nNBlTNBDg_1080_1620.jpg
		String mediaId="@"+imgUrl.substring(beginIndex, lastIndex);
		System.out.println("mediaId:"+mediaId);
	
		//3.根据url下载图片
		String nonceStr=UUID.randomUUID().toString();
		String fileDir=request.getSession().getServletContext().getRealPath("").replaceAll("\\\\", "/")+"/img/"+mediaId+"_"+nonceStr+".jpg";
		//String fileDir="";
		System.out.println("fileDir:"+fileDir);
		try {
			HttpHelper.downloadMedia(imgUrl, fileDir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		/*	
		 * 方法二，经过下载多媒体文件接口
		 * 

		//3.根据mediaId下载图片到本地
		try {
			String accessToken = AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
			String nonceStr=UUID.randomUUID().toString();
			//设置文件存放路径
			//String fileDir="D:/img/downM1.jpg";
			//String fileDir=System.getProperty("user.dir").replaceAll("\\\\", "/")+"/WebContent/img/"+mediaId+"_"+nonceStr+".jpg";
			String fileDir=request.getSession().getServletContext().getRealPath("").replaceAll("\\\\", "/")+"/img/"+mediaId+"_"+nonceStr+".jpg";
			System.out.println("fileDir:"+fileDir);

			//执行请求
			MediaService.downloadMedia(accessToken, mediaId, fileDir);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */


/*

		//4.设置返回值
		String result="aaaaaaa";

		PrintWriter out = response.getWriter(); 
		out.print(result);  
		out.close();  
		out = null;  
*/




	}

}
