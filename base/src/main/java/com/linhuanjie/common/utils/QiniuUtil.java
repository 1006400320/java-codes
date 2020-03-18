package com.linhuanjie.common.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QiniuUtil {

	private final static UploadManager uploadManager = new UploadManager();
	private static final Logger log = LoggerFactory.getLogger(QiniuUtil.class);
	
	public static String upload(byte[] bytes, String fileName) {
		
		Auth auth = Auth.create("aXn3u9xg6r_9qNAtY2l1LHPuAgVyspSX19Q093Rx", "5SVa1yw2gHygBRni-D-x8UrsuKskOa5qFMIneDXS");

		String accessToken = auth.uploadToken("woniutrip");
		String suffix = "";
		if (StringUtils.isNotBlank(fileName)) {
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
		return "http://cdn-ly.cdnmama.com/" + key;
	}
	
	/*public static String upload(String file64) {
		
		//未明原因，有些请求会带header
		if(file64.startsWith("data:image/png;base64,")) file64 = file64.replace("data:image/png;base64,", "");
		
		Auth auth = Auth.create("aXn3u9xg6r_9qNAtY2l1LHPuAgVyspSX19Q093Rx", "5SVa1yw2gHygBRni-D-x8UrsuKskOa5qFMIneDXS");
		String accessToken = auth.uploadToken("woniutrip");
		
		if(StringUs.isBlank(file64)){
			throw new RuntimeException("接受图片异常");
		}
		
		String img = "";
	    try{
	        CloseableHttpClient httpclient = HttpClients.createDefault();
	        
	        HttpPost post = new HttpPost("http://upload.qiniu.com/putb64/-1");
	        post.addHeader("Content-Type", "application/octet-stream");
	        post.addHeader("Authorization", "UpToken " + accessToken);
	        post.setEntity(new StringEntity(file64));
	        
	        CloseableHttpResponse response = httpclient.execute(post);
	        
	        String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
	        
	        JSONObject json = JSONObject.parseObject(responseBody);
	        
	        String key = json.getString("key");
	        
	        if(StringUs.isNotBlank(key)){
	        	img = AdminConstant.QINIU_PREFIX + key;
	        }else{
	        	throw new RuntimeException("【base64图片上传至七牛失败】responseBody=" + responseBody);
	        }
	        
	        response.close();//释放资源
	    }catch(Exception e){
	        throw new RuntimeException("【base64图片上传至七牛失败】", e);
	    }
	    
    	return img;
	}*/
	
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

	public static void main(String[] args) throws InterruptedException {
//		Auth auth = Auth.create("aXn3u9xg6r_9qNAtY2l1LHPuAgVyspSX19Q093Rx", "5SVa1yw2gHygBRni-D-x8UrsuKskOa5qFMIneDXS");
//
//		String accessToken = auth.uploadToken("woniutrip");
//		BucketManager bucketMgr = new BucketManager(auth);
//		//指定需要删除的文件，和文件所在的存储空间
//		String bucketName = "woniutrip";
//		String  key = "mFA1tH.png";
//		try {
//			bucketMgr.delete(bucketName, key);
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//		System.out.println("结束了");


		// 商品详细介绍图片。。。90704
		String tempImgs = "";

        Long start = System.currentTimeMillis();
        List<String> successDel = new ArrayList<>();
        List<String> failDel = new ArrayList<>();
        String[] imgs = tempImgs.split(",");
        for (String img : imgs) {
            Thread.sleep(1000);
            if(QiniuUtil.deleteImg(img)){
                successDel.add(img);
            } else {
                failDel.add(img);
            }
        }
        System.out.println("successDel:" + StringUtils.join(successDel, ",") + "共" + imgs.length + "条，耗时："+ (System.currentTimeMillis()-start) + "ms");
        System.out.println("failDel:" + StringUtils.join(failDel, ","));

//		CountDownLatch begin = new CountDownLatch(1);
//		CountDownLatch end = new CountDownLatch(10);

		/*Long start = System.currentTimeMillis();
		final List<String> successDel = new ArrayList<>();
		final List<String> failDel = new ArrayList<>();
		String[] imgs = tempImgs.split(",");
		for (final String img : imgs) {
			ExecutorService executorService = Executors.newFixedThreadPool(10);
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					if(QiniuUtil.deleteImg(img)){
						successDel.add(img);
					} else {
						failDel.add(img);
					}
				}
			});

			begin.countDown();
			try{
				//等待
				end.await();
			}catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				System.out.println("successDel:" + StringUtils.join(successDel, ",") + "共" + imgs.length + "条，耗时："+ (System.currentTimeMillis()-start) + "ms");
				System.out.println("failDel:" + StringUtils.join(failDel, ","));
			}
			executorService.shutdown();
		}
*/


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