package com.base.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class PicCompression {
	/**
	 * 
	 * @param originalFile
	 *            原文件
	 * @param resizedFile
	 *            新文件
	 * @param newWidth
	 *            宽度
	 * @param quality
	 *            质量(0-1)
	 * @throws IOException
	 */
	public static void resize(File originalFile, File resizedFile, int newWidth) {

		try {
			BufferedImage src = ImageIO.read(originalFile); // 读入文件

			// 为等比压缩计算输出的宽高
			double rate = ((double) src.getWidth(null)) / (double) newWidth;

			int new_w = (int) (src.getWidth(null) / rate);
			int new_h = (int) (src.getHeight(null) / rate);

			Image image = src.getScaledInstance(new_w, new_h, Image.SCALE_AREA_AVERAGING);
			BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.SCALE_DEFAULT);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			ImageIO.write(tag, "JPEG", resizedFile);// 输出到文件流
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // Example usage

	public static void resize(File originalFile, File resizedFile) {

		try {
			BufferedImage src = ImageIO.read(originalFile); // 读入文件

			// 为等比压缩计算输出的宽高
			int new_w = src.getWidth(null);
			int new_h = src.getHeight(null);

			Image image = src.getScaledInstance(new_w, new_h, Image.SCALE_AREA_AVERAGING);
			BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			ImageIO.write(tag, "JPEG", resizedFile);// 输出到文件流
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // Example usage

	/**
	 * 压缩图片方法
	 * 
	 * @param oldFile
	 *            要压缩的图片路径
	 * @param newFile
	 *            压缩后添加的后缀名(在扩展名前添加,不会改变格式)
	 * @param width
	 *            压缩宽
	 * @param height
	 *            压缩高
	 * @param percentage
	 *            是否等比例压缩,true则宽高比自动调整
	 * @return
	 * @throws Exception
	 */
	public static void reduceImg(String oldFile, String newFile, int widthdist, int heightdist, boolean percentage) {
		try {
			System.out.println("oldFile:" + oldFile);
			File srcfile = new File(oldFile);
			System.out.println("srcfile:" + !srcfile.exists());
			if (!srcfile.exists()) {
				return;
			}
			Image src = javax.imageio.ImageIO.read(srcfile);
			System.out.println(src);
			if (percentage) {
				// 为等比压缩计算输出的宽高
				double rate1 = ((double) src.getWidth(null)) / (double) widthdist + 0.1;
				double rate2 = ((double) src.getHeight(null)) / (double) heightdist + 0.1;
				double rate = rate1 > rate2 ? rate1 : rate2;

				int new_w = (int) (src.getWidth(null) / rate);
				int new_h = (int) (src.getHeight(null) / rate);
				// 设定宽高
				BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);

				// 设定文件扩展名
				String filePrex = oldFile.substring(0, oldFile.lastIndexOf('.'));
				newFile = filePrex + newFile + oldFile.substring(filePrex.length());
				System.out.println(newFile);
				// 生成图片
				// 两种方法,效果与质量都相同,效率差不多
				// tag.getGraphics().drawImage(src.getScaledInstance(widthdist,heightdist,
				// Image.SCALE_SMOOTH), 0, 0, null);
				tag.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, Image.SCALE_AREA_AVERAGING), 0, 0, null);
				FileOutputStream out = new FileOutputStream(newFile);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();
			} else {
				// 设定宽高
				BufferedImage tag = new BufferedImage(widthdist, heightdist, BufferedImage.TYPE_INT_RGB);

				// 设定文件扩展名
				String filePrex = oldFile.substring(0, oldFile.lastIndexOf('.'));
				newFile = filePrex + newFile + oldFile.substring(filePrex.length());
				System.out.println(newFile);
				// 生成图片
				// 两种方法,效果与质量都相同,第二种效率比第一种高,约一倍
				// tag.getGraphics().drawImage(src.getScaledInstance(widthdist,
				// heightdist, Image.SCALE_SMOOTH), 0, 0, null);
				tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_AREA_AVERAGING), 0, 0, null);
				FileOutputStream out = new FileOutputStream(newFile);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 压缩图片方法 生成无后缀文件
	 * 
	 * @param oldFile
	 *            要压缩的图片路径
	 * @param newFile
	 *            压缩后添加的后缀名(在扩展名前添加,不会改变格式)
	 * @param width
	 *            压缩后的宽
	 * @param height
	 *            压缩后的高
	 * @param percentage
	 *            是否等比例压缩,true则宽高比自动调整
	 * @return
	 * @throws Exception
	 */
	public static void reduceUserIcon(String oldFile, String newFile, int widthdist, int heightdist, boolean percentage) {
		try {
			System.out.println("oldFile:" + oldFile);
			File srcfile = new File(oldFile);
			System.out.println("srcfile:" + !srcfile.exists());
			if (!srcfile.exists()) {
				return;
			}
			Image src = javax.imageio.ImageIO.read(srcfile);
			System.out.println(src);
			if (percentage) {
				// 为等比压缩计算输出的宽高
				double rate1 = ((double) src.getWidth(null)) / (double) widthdist + 0.1;
				double rate2 = ((double) src.getHeight(null)) / (double) heightdist + 0.1;
				double rate = rate1 > rate2 ? rate1 : rate2;

				int new_w = (int) (src.getWidth(null) / rate);
				int new_h = (int) (src.getHeight(null) / rate);
				// 设定宽高
				BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);

				// 设定文件扩展名
				String filePrex = oldFile.substring(0, oldFile.lastIndexOf('.'));
				newFile = filePrex + newFile;// +
												// oldFile.substring(filePrex.length());
				System.out.println(newFile);
				// 生成图片
				// 两种方法,效果与质量都相同,效率差不多
				// tag.getGraphics().drawImage(src.getScaledInstance(widthdist,heightdist,
				// Image.SCALE_SMOOTH), 0, 0, null);
				tag.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, Image.SCALE_AREA_AVERAGING), 0, 0, null);
				FileOutputStream out = new FileOutputStream(newFile);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();
			} else {
				// 设定宽高
				BufferedImage tag = new BufferedImage(widthdist, heightdist, BufferedImage.TYPE_INT_RGB);

				// 设定文件扩展名
				String filePrex = oldFile.substring(0, oldFile.lastIndexOf('.'));
				newFile = filePrex + newFile;// +
												// oldFile.substring(filePrex.length());
				System.out.println(newFile);
				// 生成图片
				// 两种方法,效果与质量都相同,第二种效率比第一种高,约一倍
				// tag.getGraphics().drawImage(src.getScaledInstance(widthdist,
				// heightdist, Image.SCALE_SMOOTH), 0, 0, null);
				tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_AREA_AVERAGING), 0, 0, null);
				FileOutputStream out = new FileOutputStream(newFile);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		PicCompression.resize(new File("D:\\pic\\1.jpg"), new File("D:\\pic\\11.jpg"));
		System.out.print("ok…");
	}
}