package com.ray.dingtalk.qy.util;

import java.io.File;
import java.util.UUID;

import org.junit.Test;

/**@desc  : 
 * 
 * @author: shirayner
 * @date  : 2017年10月17日 上午10:10:39
 */
public class ImageUtilsTest {

	/**
	 * @desc ：图片缩放
	 *   void
	 */
	@Test
	public void testScaleImage() {
		//ImageUtils.scale("D:/img/1.jpg", "D:/img/1_scale.jpg", 2, false);//测试OK

		ImageUtils.scale("D:/img/12.jpg", "D:/img/12.jpg", 2, false); //98.6 K


	}

	/**
	 * @desc ：图片类型转换
	 *   void
	 */
	@Test
	public void testConvertImage() {
		//ImageUtils.convert("D:/img/2.png", "JPG", "D:/img/2_convert.jpg");//测试OK
		//String fileDir="D:/img/2.png";
		//this.getFileNameExtension(fileDir);

	}


	/**
	 * @desc ：图片类型转换
	 *   void
	 */
	@Test
	public void testDeleteFile() {
		//ImageUtils.convert("D:/img/2.png", "JPG", "D:/img/2_convert.jpg");//测试OK
		String fileDir="D:/img/2_convert.jpg";
		File file=new File(fileDir);
		file.deleteOnExit();
		System.out.println();


	}

	@Test
	public void testUploadImage() {


		String fileDir="D:/img/2.png";   //1.23M

		//1.获取文件名后缀
		String ext=this.getFileNameExtension(fileDir); //获取后缀名
		
		File file=null;
		
		//2.若图片类型不是jpg,则将图片转为jpg
		String newFileName="";

		if(!ext.equals("jpg")) {
			
			int lastIndex=fileDir.lastIndexOf("."); 
			String nonceStr=UUID.randomUUID().toString();
			newFileName=fileDir.substring(0, lastIndex)+"_"+nonceStr+".jpg";
			
			System.out.println(newFileName);
			
			//将图片转换为JPG格式
			ImageUtils.convert(fileDir, "JPG", newFileName);
			
			//根据转换后的本地jpg图片创建
			file=new File(newFileName);
		}else {
			file=new File(fileDir);
		} 


		System.out.println(file.length());    //1296811B=1.23674M
		System.out.println(file.getName());



		if(file.length()>1048576) {       //1M=1048576
			int sacle=(int)(file.length())/1048576;
			ImageUtils.scale("D:/img/1.jpg", "D:/img/1_scale.jpg", sacle, false);//测试OK

		}


	}

	/**
	 * 获取文件名后缀
	 */
	public String getFileNameExtension(String fileName) {
		//一.方法一
		//String ext=fileName.split("\\.")[0];
		//System.out.println(ext);

		//二、方法二
		//1.获取最后一个点的下一个位置
		int beginIndex=fileName.lastIndexOf(".")+1;     //  D:/img/2.png
		//2.获取后缀名
		String ext=fileName.substring(beginIndex);     //  png

		return ext;
	}






}
