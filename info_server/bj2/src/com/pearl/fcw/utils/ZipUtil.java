package com.pearl.fcw.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 对JSON字符串压缩效率高，对非JSON字符串解压有失真(回车失真)
 */
public class ZipUtil {

    /**
	 * 使用zip进行压缩
	 * @param str 压缩前的文本
	 * @return 返回压缩后的文本
	 */
    public static final String zip(String str) {
        if (str == null)
            return null;
        byte[] compressed;
        ByteArrayOutputStream out = null;
        ZipOutputStream zout = null;
        String compressedStr = null;
        try {
            out = new ByteArrayOutputStream();
            zout = new ZipOutputStream(out);
            zout.putNextEntry(new ZipEntry("0"));
            zout.write(str.getBytes());
            zout.closeEntry();
            compressed = out.toByteArray();
            compressedStr = Base64.getEncoder().encodeToString(compressed);
            //    compressedStr = new sun.misc.BASE64Encoder().encodeBuffer(compressed);
        } catch (IOException e) {
            compressed = null;
        } finally {
            if (zout != null) {
                try {
                    zout.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return compressedStr;
    }

    /**
	 * 使用zip进行解压缩
	 * @param compressed 压缩后的文本
	 * @return 解压后的字符串
	 */
    public static final String unzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        ZipInputStream zin = null;
        String decompressed = null;
        try {
            //    byte[] compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            byte[] compressed = Base64.getDecoder().decode(compressedStr);
            out = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(compressed);
            zin = new ZipInputStream(in);
            zin.getNextEntry();
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            decompressed = null;
        } finally {
            if (zin != null) {
                try {
                    zin.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return decompressed;
    }

    /**
	 * 将实体转为Json字符串后再压缩
	 * @param o
	 * @return
	 */
    public static String zipJson(Object o) {
        return zip(JsonUtil.writeAsString(o));
    }

    /**
	 * 将字符串解压为Json字符串后，再转实体
	 * @param str
	 * @param clazz
	 * @return
	 */
    public static <T> T unzipJson(String str, Class<T> clazz) {
        return JsonUtil.readValue(unzip(str), clazz);
    }

    /**
	 * 将字符串解压为Json字符串后，再转List
	 * @param str
	 * @param clazz
	 * @return
	 */
    public static List<?> unzipJsonList(String str, Class<?> clazz) {
        return JsonUtil.readValueList(unzip(str), clazz);
    }

	/**
	 * 使用GZip进行压缩，可和C#交互
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String gzip(String str) throws Exception {
		if (null == str || str.trim() == "") {
			return "";
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		try {
			gzip.write(str.getBytes("UTF-8"));
			gzip.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			gzip.close();
		}
		byte[] bArr = out.toByteArray();
		out.close();
		BASE64Encoder base64Encoder = new BASE64Encoder();
		return base64Encoder.encode(bArr);
	}

	/**
	 * 使用GZip解压缩，可和C#交互
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String unGzip(String str) throws Exception {
		if (null == str || str.trim() == "") {
			return "";
		}
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] bArr = base64Decoder.decodeBuffer(str);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(bArr);
		GZIPInputStream gzip = new GZIPInputStream(in);
		try {
			byte[] buffer = new byte[255];
			int n = 0;
			while ((n = gzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			gzip.close();
		}
		in.close();
		out.close();
		return out.toString("UTF-8");
	}

	public static void main(String[] args) throws Exception {
		FileInputStream in = null;
		InputStreamReader ir=null;
		BufferedReader buf = null;
		String str = null;
		try {
			in = new FileInputStream(new File("d:\\0.txt"));
			ir=new InputStreamReader(in,"UTF-8");
			buf = new BufferedReader(ir);
			str = buf.lines().collect(Collectors.joining("\n"));
			System.out.println(str.length());
			System.out.println(System.currentTimeMillis());
			str = unGzip(str);
			System.out.println(System.currentTimeMillis());
			System.out.println(str.length());
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != buf) {
				buf.close();
			}
			if (null != ir) {
				ir.close();
			}
			if (null != in) {
				in.close();
			}
		}
	}
}
