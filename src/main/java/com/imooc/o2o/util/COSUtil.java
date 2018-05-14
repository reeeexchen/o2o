package com.imooc.o2o.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;

/**
 * @Author:REX
 * @Date: Create in 11:07 2018/5/12
 */
public class COSUtil {
	private long appId = 1252833636;
	private String secretId = "AKIDoW82oP2GWbdVCn6gSF5av5NYZTCkGsWz";
	private String secretKey = "a4PHzZZTDmBRWXRDqIZt7CGAuTq7FD1h";
	private String bucketName = "o2o";

	public void uploadFile2Cos(String path) {
		// 初始化秘钥信息
		Credentials cred = new Credentials(appId, secretId, secretKey);
		// 初始化客户端配置
		ClientConfig clientConfig = new ClientConfig();
		// 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
		clientConfig.setRegion("gz");
		// 初始化cosClient
		COSClient cosClient = new COSClient(clientConfig, cred);

		// UPLOAD
		String localpath = "D:\\JavaDemo\\images\\" + path;
		String [] paths = path.split("\\\\");
		path = "";
		for(int i = 0;i < paths.length;i++){
			path += "/";
			path += paths[i];
		}
		System.out.println(path);


		UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, path, localpath);
		uploadFileRequest.setEnableShaDigest(false);

		String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
		System.out.println("UPLOAD-FILE-RET" + uploadFileRet);
		cosClient.shutdown();

	}
}
