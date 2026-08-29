package com.pearl.o2o.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class BinaryUtil {
	//FCW
	public static byte[] toBytas(Object... datas) {
		List<Byte> list = new ArrayList<>();
		byte[] arr = new byte[0];
		for (Object data : datas) {
			arr = new byte[0];
			if (data.getClass().equals(byte.class)) {
				arr = toByta((byte) data);
			} else if (data.getClass().equals(char.class)) {
				arr = toByta((char) data);
			} else if (data.getClass().equals(short.class)) {
				arr = toByta((short) data);
			} else if (data.getClass().equals(int.class)) {
				arr = toByta((int) data);
			} else if (data.getClass().equals(float.class)) {
				arr = toByta((float) data);
			} else if (data.getClass().equals(String.class)) {
				arr = toByta(data.toString());
			} else if (data.getClass().equals(int[].class)) {
				arr = toByta((int[]) data);
			}
			for (byte b : arr) {
				list.add(b);
			}
		}
		arr = new byte[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}

	public static byte[] toByta(byte data) {
		return new byte[] { data };
	}

	public static byte[] toByta(char data) {
		return new byte[] { (byte) ((data >> 0) & 0xff), (byte) ((data >> 8) & 0xff), };
	}

	public static byte[] toByta(short data) {
		return new byte[] { (byte) ((data >> 0) & 0xff), (byte) ((data >> 8) & 0xff), };
	}

	public static byte[] toByta(int data) {
		return new byte[] { (byte) ((data >> 0) & 0xff), (byte) ((data >> 8) & 0xff), (byte) ((data >> 16) & 0xff), (byte) ((data >> 24) & 0xff), };
	}

	public static byte[] toByta(int[] data) {
		if (data == null)
			return null;
		// ----------
		byte[] byts = new byte[data.length * 4];
		for (int i = 0; i < data.length; i++)
			System.arraycopy(toByta(data[i]), 0, byts, i * 4, 4);
		return byts;
	}

	public static byte[] toByta(float data) {
		return toByta(Float.floatToRawIntBits(data));
	}

	public static byte[] toByta(String data) {
		if (data == null) {
			return null;
		} else {
			int length;
			try {
				length = data.getBytes("UTF-8").length;
				byte[] byts = new byte[length + 4];
				System.arraycopy(toByta(length), 0, byts, 0, 4);
				System.arraycopy(data.getBytes("UTF-8"), 0, byts, 4, length);
				return byts;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public static short toShort(byte[] data) {
		if (data.length < 2) {
			throw new IllegalArgumentException("the byte's length is error when try to convert to short");
		}
		int result = data[1] & 0xff;
		result = (result << 8) | (data[0] & 0xff);

		return (short) result;
	}

	public static char toChar(byte[] data) {
		if (data.length < 2) {
			throw new IllegalArgumentException("the byte's length is error when try to convert to char");
		}
		int result = data[1] & 0xff;
		result = (result << 8) | (data[0] & 0xff);

		return (char) result;
	}

	public static int toInt(byte[] data) {
		if (data.length < 4) {
			throw new IllegalArgumentException("the byte's length is error when try to convert to int");
		}
		int result = data[3] & 0xff;
		result = ((result << 8) | (data[2] & 0xff));
		result = ((result << 8) | (data[1] & 0xff));
		result = ((result << 8) | (data[0] & 0xff));

		return result;
	}

	public static float toFloat(byte[] data) {
		int temp = toInt(data);
		return Float.intBitsToFloat(temp);
	}

	public static String toString(byte[] data) {
		if (data.length < 4) {
			throw new IllegalArgumentException("the byte's length is error when try to convert to String");
		}
		int strLength = data[3] & 0xff;
		strLength = ((strLength << 8) | (data[2] & 0xff));
		strLength = ((strLength << 8) | (data[1] & 0xff));
		strLength = ((strLength << 8) | (data[0] & 0xff));

		if (data.length - 4 != strLength) {
			throw new IllegalArgumentException("the byte's length is error when try to convert to String");
		}
		try {
			return new String(data, 4, data.length - 4, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static int getLength(byte data) {
		return 1;
	}

	public static int getLength(byte[] data) {
		return data.length;
	}

	public static int getLength(short data) {
		return 2;
	}

	public static int getLength(int data) {
		return 4;
	}

	public static int getLength(String data) {
		if (data == null) {
			return 4;
		} else {
			return data.getBytes(Charset.forName(Constants.ENCODING)).length + 4;
		}
	}

	public static int getLength(String... datas) {
		int length = 0;
		for (String data : datas) {
			length += getLength(data);
		}
		return length;
	}

	/**
	 * return the list contain all the bit which was '1' in a byte
	 * @param b
	 * @return
	 */
	public static List<Integer> mask(byte b, int length) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < length; i++) {
			if ((b & (1 << i)) != 0) {
				list.add(i + 1);
			}
		}
		return list;
	}
}