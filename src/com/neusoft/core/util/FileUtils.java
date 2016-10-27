package com.neusoft.core.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class FileUtils {

	// 上传图片时允许的文件类型
	public static final String IMAGE_FILE_EXTENSION = ".jpg|.jpeg|.png|.gif|";

	// 上传Excel时允许的文件类型
	public static final String EXCEL_FILE_EXTENSION = ".xls|.xlsx|.csv|";

	// 可执行文件扩展名
	public static final String ATTACHMENT_EXTENSION = ".doc|.docx|.xls|.xlsx|.ppt|.pptx|.txt|.pdf|.zip|.rar|";

	public static final String BMP_FILE_EXTENSION = ".bmp|";
	public static final String GIF_FILE_EXTENSION = ".gif|";
	public static final String JPEG_FILE_EXTENSION = ".jpeg|.jpg|";
	public static final String HTML_FILE_EXTENSION = ".html|.htm|";
	public static final String TXT_FILE_EXTENSION = ".txt|";
	public static final String XML_FILE_EXTENSION = ".xml|";
	public static final String PDF_FILE_EXTENSION = ".pdf|";
	public static final String MSWORD_FILE_EXTENSION = ".doc|.docx|";
	public static final String MSEXCEL_FILE_EXTENSION = ".xls|.xlsx|";
	public static final String MSPOWERPOINT_FILE_EXTENSION = ".ppt|.pptx|";

	public static final long FILE_SIZE_30M = 30;

	public static boolean isImageFile(MultipartFile imgFile) {
		// 提取上传文件的类型
		String fileName = imgFile.getOriginalFilename();
		// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名 ，对扩展名进行小写转换
		String extName = fileName
				.substring(
						fileName.lastIndexOf(".") == -1 ? 0 : fileName
								.lastIndexOf(".")).toLowerCase();
		// 判断上传文件类型是否允许上传
		if (IMAGE_FILE_EXTENSION.toLowerCase().indexOf(extName.concat("|")) == -1) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isExcelFile(MultipartFile imgFile) {
		// 提取上传文件的类型
		String fileName = imgFile.getOriginalFilename();
		// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名 ，对扩展名进行小写转换
		String extName = fileName
				.substring(
						fileName.lastIndexOf(".") == -1 ? 0 : fileName
								.lastIndexOf(".")).toLowerCase();
		// 判断上传文件类型是否允许上传
		if (EXCEL_FILE_EXTENSION.toLowerCase().indexOf(extName.concat("|")) == -1) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isAttachment(MultipartFile imgFile) {
		if (imgFile != null) {
			// 提取上传文件的类型
			String fileName = imgFile.getOriginalFilename();
			// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名 ，对扩展名进行小写转换
			String extName = fileName.substring(
					fileName.lastIndexOf(".") == -1 ? 0 : fileName
							.lastIndexOf(".")).toLowerCase();
			// 判断上传文件类型是否允许上传
			if (ATTACHMENT_EXTENSION.toLowerCase().indexOf(extName.concat("|")) == -1) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * 判断上传文件大小是否允许上传
	 * 
	 * @param
	 * @return boolean
	 * @throws
	 * @author chechangying
	 */
	public static boolean isFileSize(MultipartFile file) {
		if (file != null) {
			long fileSize = file.getSize();
			// 判断上传文件大小是否允许上传
			if (fileSize <= FILE_SIZE_30M * 1024 * 1024) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * 文件上传公用方法
	 * 
	 * @param request
	 *            response
	 * @return
	 * @throws IllegalStateException
	 *             IOException
	 */
	public static void attachmentUpload(HttpServletRequest request,
			HttpServletResponse response) {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

			Iterator<String> iter = multipartRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multipartRequest.getFile((String) iter
						.next());
				// 判断上传文件类型是否允许上传
				if (!isAttachment(file)) {
					if (file != null) {
						String filename = "demo" + file.getOriginalFilename();
						String path = "D:/" + filename;
						File localfile = new File(path);
						try {
							file.transferTo(localfile);
						} catch (IllegalStateException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

}
