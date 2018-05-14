package com.imooc.o2o;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;

/**
 * @Author:REX
 * @Date: Create in 10:46 2018/5/12
 */
public class DemoCos {
	public static void main(String[] args) {
		long appId = 1252833636;
		String secretId = "AKIDoW82oP2GWbdVCn6gSF5av5NYZTCkGsWz";
		String secretKey = "a4PHzZZTDmBRWXRDqIZt7CGAuTq7FD1h";
		// 设置要操作的bucket
		String bucketName = "o2o";
		// 初始化秘钥信息
		Credentials cred = new Credentials(appId, secretId, secretKey);
		// 初始化客户端配置
		ClientConfig clientConfig = new ClientConfig();
		// 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
		clientConfig.setRegion("gz");
		// 初始化cosClient
		COSClient cosClient = new COSClient(clientConfig, cred);
		//UPLOAD
		String cospath = "/test.gif";
		String localpath = "D:\\JavaDemo\\images\\test\\timg.gif";
		UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName,cospath, localpath);
		uploadFileRequest.setEnableShaDigest(false);

		String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
		System.out.println("upload file ret" + uploadFileRet);
		cosClient.shutdown();

	}
}
