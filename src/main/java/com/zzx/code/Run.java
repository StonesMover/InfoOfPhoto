package com.zzx.code;

import java.util.Map;

import com.zzx.utils.BaiduMapUtil;
import com.zzx.utils.PictureUtil;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String PicturePath = "D:/332.jpg";
		String lng = "117.349993"; // 经度
		String lat = "35.249812"; // 纬度

		PictureUtil pictureUtil = new PictureUtil();
		Map<String, Object> pic = pictureUtil.readPic(PicturePath);

		System.out.println("拍摄设备: " + pic.get("Make") + " " + pic.get("Model"));
		System.out.println("照片名称: " + pic.get("File Name"));
		System.out.println("拍摄时间: " + pic.get("Date/Time"));

		// System.out.println(pic);
		BaiduMapUtil bMapUtil = new BaiduMapUtil();
		// 处理经纬度十分制

		// String jingdu = pic.get("GPS Latitude").toString();
		// String weidu = pic.get("GPS Longitude").toString();
		// String lng1 =
		// bMapUtil.latitude_and_longitude_convert_to_decimal_system(jingdu);
		// String lat1 =
		// bMapUtil.latitude_and_longitude_convert_to_decimal_system(weidu);
		Map<String, String> map = bMapUtil.getcitydetailbyjingwei(lng, lat);
		System.out.println(map);
		System.out.println("拍摄地点: " + map.get("address"));
	}
}
