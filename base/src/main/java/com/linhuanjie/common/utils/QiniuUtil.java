package com.linhuanjie.common.utils;

import cn.hutool.core.util.ReUtil;
import com.linhuanjie.common.constant.RegexConstant;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class QiniuUtil {

	private final static String myAccessKey = "7RrZcSn97vJaWiu-2g40IZ8dZszuQz4-H596iqRF";
	private final static String mySecretKey = "9TMc6YkqU4DX_VKhS08d9qjSyh8A18PdA9YXTifU";
	private final static String myBucket = "linhj";

	private final static UploadManager uploadManager = new UploadManager();
	private static final Logger log = LoggerFactory.getLogger(QiniuUtil.class);

	/**
	 * 创建bucket
	 * @param accessKey 七牛云个人中心的key
	 * @param secretKey  个人中心密码
	 * @param bucketName  空间名称
	 * @param storageArea 存储区域 （z0 华东， z1 华北， z2 华南， na0 北美， as0 东南亚）
	 */
	public static void createBucket(String accessKey,String secretKey,String bucketName,String storageArea) {
		Auth auth = Auth.create(accessKey,secretKey);
		String path = "/mkbucketv2/" + encode(bucketName.getBytes()) + "/region/"+ storageArea +"\n";
		String access_token = auth.sign(path);
		System.out.println(access_token);

		String url = "http://rs.qiniu.com/mkbucketv2/" + encode(bucketName.getBytes()) + "/region/" + storageArea;

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addHeader("Authorization", "QBox " + access_token).build();
		okhttp3.Response re = null;
		try {
			re = client.newCall(request).execute();
			if (re.isSuccessful() == true) {
				System.out.println(re.code());
				System.out.println(re.toString());
			} else {
				System.out.println(re.code());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 编码
	 *
	 * @param bstr
	 * @return String
	 */
	public static String encode(byte[] bstr) {
		return new sun.misc.BASE64Encoder().encode(bstr);
	}

	/**
	 * 测试
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		createBucket(myAccessKey, mySecretKey,myBucket,"z0");
	}

	/**
	 * 上传图片
	 * @param bytes 字节数组
	 * @param fileName 文件名称
	 * @return
	 */
	public static String upload(byte[] bytes, String fileName) {
		if (StringUtils.isBlank(fileName)){
			return "";
		}

		Auth auth = Auth.create(myAccessKey, mySecretKey);
		String accessToken = auth.uploadToken(myBucket);

		boolean isPicName = ReUtil.isMatch(RegexConstant.PIC, fileName);
		String suffix = "jpg";
		if (isPicName) {
			suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		}

		String key = MD5Utils.getMd5(fileName + System.currentTimeMillis()).toLowerCase() + "." + suffix;

		try {
			Response res = uploadManager.put(bytes, key, accessToken, null, null, true);

			//返回状态
			int statusCode = res.statusCode;
			String bodyString = res.bodyString();
			
			// 返回非成功状态，发预警
			if (statusCode != 200) {
				log.error("图片上传至七牛返回非200状态：" + bodyString);
			}
		} catch (QiniuException e) {
			Response res = e.response;
			
			// 请求失败时简单状态信息
			String resInfo = res.toString();
			String bodyString = "";
			
			try {
				bodyString = res.bodyString();// 响应的文本信息
			} catch (QiniuException e1) {
				throw new RuntimeException("图片上传至七牛失败：<br>response=" + resInfo + "，响应内容获取失败");
			}
			throw new RuntimeException("图片上传至七牛失败：<br>response=" + resInfo + ",bodyString=" + bodyString);
		}
		return "q6x5zf8im.bkt.clouddn.com" + key;
	}
	
	/**
	 * 上传图片至七牛
	 */
	public static String uploadPic(String imgUrl) throws IOException {
		InputStream in = null;
		ByteArrayOutputStream out = null;
		String fileName = "";
		
		try {
			URL url = new URL(imgUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			Integer status = con.getResponseCode();
			if (!new Integer(200).equals(status)) {
				log.info("上传图片返回非200的status:" + status + "-url" + imgUrl);
				return fileName;
			}

			in = con.getInputStream();
			out = new ByteArrayOutputStream();
			byte[] btArray = new byte[1024];
			int length = 0;
			while ((length = in.read(btArray)) != -1) {
				out.write(btArray, 0, length);
			}
			in.close();
			
			byte[] byteArray = out.toByteArray();
			fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
			fileName = upload(byteArray, fileName);
		} catch (Exception e) {
			log.info("上传图片失败" + imgUrl, e);
			throw e;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.error(e.getMessage(),e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					log.error(e.getMessage(),e);
				}
			}
		}
		
		return fileName;
	}

    public static String asynUpload(String imageUrl){
        Auth auth = Auth.create("aXn3u9xg6r_9qNAtY2l1LHPuAgVyspSX19Q093Rx", "5SVa1yw2gHygBRni-D-x8UrsuKskOa5qFMIneDXS");

        String accessToken = auth.uploadToken("woniutrip");
        BucketManager bucketManager = new BucketManager(auth);
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        try {
            DefaultPutRet fetch = bucketManager.fetch(imageUrl, "woniutrip", fileName);
        } catch (QiniuException e) {
            Response res = e.response;

            // 请求失败时简单状态信息
            String resInfo = res.toString();
            String bodyString = "";

            try {
                bodyString = res.bodyString();// 响应的文本信息
            } catch (QiniuException e1) {
                throw new RuntimeException("图片上传至七牛失败：<br>response=" + resInfo + "，响应内容获取失败");
            }
            throw new RuntimeException("图片上传至七牛失败：<br>response=" + resInfo + ",bodyString=" + bodyString);
        } catch (Throwable throwable){
			log.warn(throwable.toString());
		}
        return "http://cdn-ly.cdnmama.com/" + fileName;
    }

	// 删除七牛上的图片
	public static Boolean deleteImg(String imgUrl){
		Boolean result = false;
		Auth auth = Auth.create("aXn3u9xg6r_9qNAtY2l1LHPuAgVyspSX19Q093Rx", "5SVa1yw2gHygBRni-D-x8UrsuKskOa5qFMIneDXS");

		String accessToken = auth.uploadToken("woniutrip");
		BucketManager bucketMgr = new BucketManager(auth);
		//指定需要删除的文件，和文件所在的存储空间
		String bucketName = "woniutrip";
		try {
			bucketMgr.delete(bucketName, imgUrl);
			result = true;
		}catch (Exception e){

			try {
				if(StringUtils.isNotBlank(e.getMessage()) && !e.getMessage().contains("QiniuException")){
					log.info("删除七牛图片失败：imgUrl:{}",imgUrl,e.getMessage(),e);
				}
			} catch (Exception ex) {
				log.info("删除七牛图片失败2：imgUrl:{}",imgUrl,e.getMessage(),e);
			}
		}
		return result;
	}
}