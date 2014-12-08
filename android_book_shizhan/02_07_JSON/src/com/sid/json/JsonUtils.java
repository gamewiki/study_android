package com.sid.json;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class JsonUtils {

	/**
	 * gson1.6基于字符流处理
	 * @param Data
	 */
	public void parseJson(String Data){
		try {
			JsonReader reader = new JsonReader(new StringReader(Data));
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				while (reader.hasNext()) {
					String tagName = reader.nextName();
					if (tagName.equals("name")) {
						System.out.println("name is :"+reader.nextString());
					}else if (tagName.equals("age")) {
						System.out.println("age is :"+reader.nextInt());
					}
				}
				reader.endObject();
			}
			reader.endArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 对象转换（不支持json数组）
	 * @param Data
	 */
	public void parseUserFromJson(String Data){
		Gson gson = new Gson();
		User user = gson.fromJson(Data,User.class);
		System.out.println("user name is :"+user.getName());
		System.out.println("user age is :"+user.getAge());
	}
	
	public void parseUsersFromJson(String data){
		Type listType = new TypeToken<LinkedList<User>>(){}.getType();
		Gson gson = new Gson();
		LinkedList<User> users = gson.fromJson(data, listType);
		for (User user : users) {
			System.out.println("name is :"+user.getName());
			System.out.println("age is :"+user.getAge());
		}
	}
}
