package com.imooc.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author:REX
 * @Date: Create in 14:56 2018/3/17
 */
public class ImageUtil {

	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();

	public static String generateThumbnail(File thumbnail,String targetAddr){
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		try{
			Thumbnails.of(thumbnail)
					.size(200,200)
					.watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath + "/watermark.png")),0.25f)
					.outputQuality(0.8f)
					.toFile(dest);
		}catch (IOException e){
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		return relativeAddr;
	}

	/**
	 * 创建目标路径所涉及到的目录
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if(!dirPath.exists()){
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取输入文件流的拓展名
	 * @param cFile
	 * @return
	 */
	private static String getFileExtension(File cFile) {
		String originalFileName = cFile.getName();
		return originalFileName.substring(originalFileName.lastIndexOf("."));
	}

	/**
	 * 生成随机文件名，当前年月日时分秒+五位随机数
	 * @return
	 */
	private static String getRandomFileName() {
//		获取随机五位数
		int rannum = r.nextInt(89999) + 10000;
		String nowTimeStr = sdf.format(new Date());
		return nowTimeStr + rannum;
	}

	public static void main(String[] args) throws IOException {
		Thumbnails.of(new File("src/main/resources/test.png"))
				.size(500,500).watermark(Positions.BOTTOM_RIGHT,
				ImageIO.read(new File(basePath + "/watermark.png")),
				0.25f).outputQuality(0.8f).toFile("src/main/resources/test1.png");
	}
}
