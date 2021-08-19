package com.zzx.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class BaiduMapUtil {
	public static Map<String, String> getcitydetailbyjingwei(String jing, String wei) {
		Map<String, Object> map = null;
		Map<String, Object> map2 = null;
		Map<String, Object> map3 = null;
		Map<String, String> map4 = new HashMap<String, String>();
		String url = "https://api.map.baidu.com/reverse_geocoding/v3/?ak=CHRcGG4l74naK5mMfjNttRrzGhWhv3kX&output=json&coordtype=wgs84ll&location="
				+ jing + "," + wei;

		String url2 = "https://restapi.amap.com/v3/geocode/regeo?output=json&location=" + jing + "," + wei
				+ "&key=307490ffbd3a513f9f24e62dfe486fb0&radius=1000&extensions=all";
		System.out.println(url2);
		try {
			HttpClient client = HttpClientBuilder.create().build();// 构建一个Client
			HttpGet get = new HttpGet(url2.toString()); // 构建一个GET请求
			HttpResponse response = client.execute(get);// 提交GET请求
			HttpEntity result = response.getEntity();// 拿到返回的HttpResponse的"实体"
			System.out.println(result);
			String content = EntityUtils.toString(result);
			map = JsonUtil.parseJSON2Map(content); // 通过下面的函数将json转化为map

			// 第二层
			map2 = JsonUtil.parseJSON2Map(map.get("result").toString());
			// 第三层
			map3 = JsonUtil.parseJSON2Map(map2.get("location").toString());

			String address = map2.get("formatted_address").toString();
			String lng = map3.get("lng").toString();
			String lat = map3.get("lat").toString();
			map4.put("address", address);
			map4.put("lng", lng);
			map4.put("lat", lat);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取地址失败");
		}
		return map4;
	}

	private static Map<String, String> toHashMap(Object object) {
		HashMap<String, String> map = new HashMap<String, String>();
		JSONObject jsonObject = JSONObject.fromObject(object);
		Iterator it = jsonObject.keys();
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			String value = (String) jsonObject.get(key);
			map.put(key, value);
		}
		return map;
	}

	/***
	 * 经纬度坐标格式转换
	 * 
	 * @param Gps
	 */
	public String latitude_and_longitude_convert_to_decimal_system(String Gps) {
		String a = Gps.split("°")[0].replace(" ", "");
		String b = Gps.split("°")[1].split("'")[0].replace(" ", "");
		String c = Gps.split("°")[1].split("'")[1].replace(" ", "").replace("\"", "");
		double gps_dou = Double.parseDouble(a) + Double.parseDouble(b) / 60 + Double.parseDouble(c) / 60 / 60;
		String gps_dou1 = gps_dou + "";
		return gps_dou1;
	}
}
