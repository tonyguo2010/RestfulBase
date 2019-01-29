package com.seakie;

import java.util.concurrent.ConcurrentHashMap;

public class Messages {
	private static ConcurrentHashMap<Integer, Message> content = null;
	
	static {
		content = new ConcurrentHashMap<Integer, Message>();
	}

	public static ConcurrentHashMap<Integer, Message> getAll() {
		return content;
	}

	public static boolean addMessage(Message message) {
		if (content.get(message.getId()) != null) {
			return false;
		} else {
			content.put(message.getId(), message);
			return true;
		}
	}

	public static boolean putMessage(Message message) {
		boolean result = false;
		
		if (content.get(message.getId()) != null) {
			result = true;
		}
		content.put(message.getId(), message);
		
		return result;
	}

	public static boolean updateMessage(int id, Message message) {
		boolean result = false;
		
		if (content.get(id) != null) {
			content.put(id, message);
			result = true;
		}
		
		return result;
	}

	public static Message find(int id) {
		return content.get(id);
	}

	public static void remove(int id) {
		content.remove(id);
	}

}
