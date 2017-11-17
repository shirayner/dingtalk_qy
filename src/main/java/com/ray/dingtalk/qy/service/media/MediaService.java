package com.ray.dingtalk.qy.service.media;

import java.io.File;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.ray.dingtalk.qy.util.CommonUtils;
import com.ray.dingtalk.qy.util.HttpHelper;
import com.ray.dingtalk.qy.util.ImageUtils;

/**
 * @desc :
 * 
 * @author: shirayner
 * @date : 2017年10月13日 下午4:41:08
 */
public class MediaService {

	private static final String UPLOAD_MEDIA_URL = "https://oapi.dingtalk.com/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	private static final String DOWNLOAD_MEDIA_URL = "https://oapi.dingtalk.com/media/downloadFile?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	private static boolean mark = false; // 默认不删除。当源图片不符合要求时，会产生一个新文件，则此时需要删除这个新文件。mark=true表示需要删除新文件

	/**
	 * @desc ：上传图片
	 * 
	 * @param accessToken
	 * @param type
	 * @param fileDir
	 * @return
	 * @throws Exception
	 *             JSONObject
	 */
	public static JSONObject uploadImage(String accessToken, String type, String fileDir) throws Exception {
		// 标记 是否需要删除文件
		System.out.println("mark_b:" + mark);
		// 1.创建本地文件
		String filePath = prepareFile(fileDir); // 根据源图片创建获取符合要求的副本图片，并返回其副本图片路径
		System.out.println("mark_e:" + mark);

		File file = new File(filePath);

		// 2.获取请求url
		String url = UPLOAD_MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
		System.out.println(url);
		// 3.发起GET请求，获取返回结果
		JSONObject jsonObject = HttpHelper.uploadMedia(url, file);
		System.out.println("jsonObject:" + jsonObject.toString());

		// 删除副本图片：若源图片不符合要求，则需要先根据源图片创建一个符合要求的副本图片。
		if (mark) {
			file.deleteOnExit();
			System.out.println("副本图片已删除");
		}
		// 4.解析结果，获取请求结果
		if (null != jsonObject) {
			// 5.请求成功,则返回jsonObject
			if (0 == jsonObject.getInteger("errcode")) {
				return jsonObject;
			}
			// 6.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:" + errCode + ", error message:" + errMsg);
			}
		}

		return null;

	}

	/**
	 * @desc ：上传语音和文件
	 * 
	 * @param accessToken
	 * @param type
	 * @param fileDir
	 * @return
	 * @throws Exception
	 *             JSONObject
	 */
	public static JSONObject uploadMedia(String accessToken, String type, String fileDir) throws Exception {

		String url = UPLOAD_MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
		File file = new File(fileDir);

		JSONObject jsonObject = HttpHelper.uploadMedia(url, file);
		System.out.println("jsonObject:" + jsonObject.toString());

		// 4.解析结果，获取请求结果
		if (null != jsonObject) {
			// 5.请求成功,则返回jsonObject
			if (0 == jsonObject.getInteger("errcode")) {
				return jsonObject;
			}
			// 6.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:" + errCode + ", error message:" + errMsg);
			}
		}

		return null;
	}

	public static void downloadMedia(String accessToken, String mediaId, String fileDir) throws Exception {

		// 1.获取请求url
		String url = DOWNLOAD_MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
		System.out.println(url);

		// 2.调用接口，发送请求，获取媒体文件
		// File file=HttpHelper.getFile(url, fileDir);
		File file = HttpHelper.downloadMedia(url, fileDir);

		System.out.println("fileDir:" + fileDir);

		System.out.println("file:" + file.getName());

	}

	/**
	 * @desc ：3.根据源文件创建获取符合要求的文件，并返回其文件路径 能确保图片为jpg格式，且图片最大为1M
	 * 
	 *       若原图不是jpg，则在此过程中产生了一个jpg图片，此图片需要在uploadMedia(String, String,
	 *       String)中进行销毁
	 * 
	 * @param fileDir
	 *            源图片路径
	 * @param mark
	 *            默认为false,不删除。当源图片不符合要求时，会产生一个新文件，则此时需要删除这个新文件。mark=true表示需要删除新文件
	 * @return String 符合要求的图片路径
	 */
	private static String prepareFile(String fileDir) {
		// 1.获取源文件名后缀
		String ext = CommonUtils.getFileNameExtension(fileDir); // 获取后缀名 //String fileDir="D:/img/2.png"; //1.23M

		File file = null;

		// 2.设置目的文件的文件名
		int lastIndex = fileDir.lastIndexOf(".");
		String nonceStr = UUID.randomUUID().toString();
		String newFileName = fileDir.substring(0, lastIndex) + "_" + nonceStr + ".jpg"; // 最终文件名=源文件所在文件夹路径+源文件名_随机串.jpg
		System.out.println(newFileName);
		// 2 若图片类型不是jpg,则将图片转为jpg
		if (!ext.equals("jpg")) {
			mark = true;
			System.out.println("prepareFile-mark:" + mark);
			// 2.2 将图片转换为JPG格式
			ImageUtils.convert(fileDir, "JPG", newFileName);

			// 2.3 根据转换后的本地jpg图片创建目的文件
			file = new File(newFileName);

			// 3. 若图片大于1M，则等比例缩放
			if (file.length() > 1048576) { // 1M=1048576
				int sacle = (int) (file.length()) / 1048576;
				ImageUtils.scale(newFileName, newFileName, sacle, false);// 测试OK

			}

			return newFileName;

		} else {
			file = new File(fileDir);

			// 3. 若图片大于1M，则等比例缩放
			if (file.length() > 1048576) { // 1M=1048576
				mark = true;
				int sacle = (int) (file.length()) / 1048576;
				ImageUtils.scale(fileDir, newFileName, sacle, false);// 测试OK

				return newFileName;
			}

			return fileDir;
		}

	}

}
