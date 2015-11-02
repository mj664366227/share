package com.share.core.util;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 图片工具类
 * @author ruan
 */
public class ImageUtil {
	private final static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

	/**
	 * 根据尺寸图片居中裁剪
	 * @author ruan 
	 * @param src 图片源地址
	 * @param dest 图片目标地址
	 * @param w 宽
	 * @param h 高
	 */
	public static void cutCenterImage(String src, String dest, int w, int h) {
		try {
			String suffix = src.substring(src.lastIndexOf(".") + 1);
			Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(suffix);
			ImageReader reader = (ImageReader) iterator.next();
			InputStream in = new FileInputStream(src);
			ImageInputStream iis = ImageIO.createImageInputStream(in);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			int imageIndex = 0;
			Rectangle rect = new Rectangle((reader.getWidth(imageIndex) - w) / 2, (reader.getHeight(imageIndex) - h) / 2, w, h);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ImageIO.write(bi, suffix, new File(dest));
		} catch (Exception e) {
			logger.error("", e);
		}

	}

	/**
	 * 图片裁剪二分之一 
	 * @author ruan 
	 * @param src 图片源地址
	 * @param dest 图片目标地址
	 */
	public static void cutHalfImage(String src, String dest) {
		try {
			String suffix = src.substring(src.lastIndexOf(".") + 1);
			Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(suffix);
			ImageReader reader = (ImageReader) iterator.next();
			InputStream in = new FileInputStream(src);
			ImageInputStream iis = ImageIO.createImageInputStream(in);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			int imageIndex = 0;
			int width = reader.getWidth(imageIndex) / 2;
			int height = reader.getHeight(imageIndex) / 2;
			Rectangle rect = new Rectangle(width / 2, height / 2, width, height);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ImageIO.write(bi, suffix, new File(dest));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 图片裁剪通用接口 
	 * @author ruan 
	 * @param src 图片源地址
	 * @param dest 图片目标地址
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 宽
	 * @param h 高
	 */
	public static void cutImage(String src, String dest, int x, int y, int w, int h) {
		try {
			String suffix = src.substring(src.lastIndexOf(".") + 1);
			Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(suffix);
			ImageReader reader = (ImageReader) iterator.next();
			InputStream in = new FileInputStream(src);
			ImageInputStream iis = ImageIO.createImageInputStream(in);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			Rectangle rect = new Rectangle(x, y, w, h);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ImageIO.write(bi, suffix, new File(dest));
		} catch (Exception e) {
			logger.error("", e);
		}

	}

	/**
	 * 图片缩放
	 * @author ruan 
	 * @param src 图片源地址
	 * @param dest 图片目标地址
	 * @param w 宽
	 * @param h 高
	 */
	public static void zoomImage(String src, String dest, int w, int h) {
		try {
			double wr = 0, hr = 0;
			File srcFile = new File(src);
			File destFile = new File(dest);
			BufferedImage bufImg = ImageIO.read(srcFile);
			Image Itemp = bufImg.getScaledInstance(w, h, BufferedImage.SCALE_SMOOTH);
			wr = w * 1.0 / bufImg.getWidth();
			hr = h * 1.0 / bufImg.getHeight();
			AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
			Itemp = ato.filter(bufImg, null);
			ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 图片缩放(只压质量)
	 * @author ruan 
	 * @param src 图片源地址
	 * @param dest 图片目标地址
	 */
	public static void zoomImage(String src, String dest) {
		try {
			double wr = 0, hr = 0;
			File srcFile = new File(src);
			File destFile = new File(dest);
			BufferedImage bufImg = ImageIO.read(srcFile);
			Image Itemp = bufImg.getScaledInstance(bufImg.getWidth(), bufImg.getHeight(), BufferedImage.SCALE_SMOOTH);
			wr = bufImg.getWidth() * 1.0 / bufImg.getWidth();
			hr = bufImg.getHeight() * 1.0 / bufImg.getHeight();
			AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
			Itemp = ato.filter(bufImg, null);
			ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile);
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}