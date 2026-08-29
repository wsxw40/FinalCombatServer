package com.pde.uweb.bbs.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.pde.infor.common.asserts.Asserts;

/**
 * 
 * Server之间的通信。 该类实现与UC Server通信的所有接口函数
 * 
 * 
 * 
 * 
 */
public class BBSUCenterClient extends BBSDiscuzFunction {

	private static final Logger logger = Logger.getLogger(BBSUCenterClient.class);

	private Charset charset = Charset.forName("UTF-8");

	public static boolean IN_UC = true;
//	public static String UC_IP = "127.0.0.1";
//	public static String UC_API = "http://localhost/uc";
//	public static String UC_CONNECT = "";
//	public static String UC_KEY = "123456";
//	public static String UC_APPID = "2";
	public static String UC_CLIENT_VERSION = "1.6.0";
	public static String UC_CLIENT_RELEASE = "20110501";
	
	// 以下5项读配置文件
	private String ucIp;
	private String ucAPI;
	private String ucConnect;
	private String ucKey;
	private String ucAppId;
	
	public static String UC_ROOT = ""; // note 用户中心客户端的根目录 UC_CLIENTROOT
	public static String UC_DATADIR = UC_ROOT + "./data/"; // note 用户中心的数据缓存目录
	public static String UC_DATAURL = "UC_API" + "/data"; // note 用户中心的数据 URL
//	public static String UC_API_FUNC = "mysql".equals(ucConnect) ? "uc_api_mysql" : "uc_api_post";
	
	public static String[] uc_controls = {};

//	private boolean isInUC = true;

//	private String ucClientVersion = UC_CLIENT_VERSION;

	private String ucClientRelease = UC_CLIENT_RELEASE;

//	private String ucRoot = UC_ROOT;

//	private String ucDataDir = UC_DATADIR;

	private String ucApiFunc = "mysql".equals(this.ucConnect) ? "uc_api_mysql" : "uc_api_post";;

//	private String ucDataUrl = UC_DATAURL;

	
	
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		Asserts.assertExpression(this.ucAPI != null && this.ucAPI.length() != Integer.valueOf(0), "please enter uc api");
//		Asserts.assertExpression(this.ucKey != null && this.ucKey.length() != Integer.valueOf(0), "please enter uc key");
//		Asserts.assertExpression(this.ucIp != null && this.ucIp.length() != Integer.valueOf(0), "please enter uc ip");
//
//	}

	// static {
	// InputStream in = BBSUCenterClient.class.getClassLoader()
	// .getResourceAsStream("config.properties");
	// Properties properties = new Properties();
	// try {
	// properties.load(in);
	// UC_API = properties.getProperty("UC_API");
	// UC_IP = properties.getProperty("UC_IP");
	// UC_KEY = properties.getProperty("UC_KEY");
	// UC_APPID = properties.getProperty("UC_APPID");
	// UC_CONNECT = properties.getProperty("UC_CONNECT");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	protected String serialize(String arr, int htmlon) {

		return arr;
	}

	protected String unserialize(String s) {

		return s;
	}

	protected String addslashes(String string, int force, boolean strip) {

		return string;
	}

	protected String daddslashes(String string, int force) {
		return addslashes(string, force, false);
	}

	protected String stripslashes(String string) {

		return string;

	}

	@SuppressWarnings("unchecked")
	public String apiPost(String module, String action, Map<String, Object> arg) {
		StringBuffer s = new StringBuffer();
		String sep = "";
		// foreach(arg as k => v) {
		for (String k : arg.keySet()) {
			// k = (k);
			Object v = arg.get(k);
			k = this.urlEncoder(k);

			if (v.getClass().isAssignableFrom(Map.class)) {
				String s2 = "";
				String sep2 = "";
				// foreach(v as k2 => v2) {
				for (String k2 : ((Map<String, Object>) v).keySet()) {
					Object v2 = ((Map<String, Object>) v).get(k2);
					k2 = this.urlEncoder(k2);
					s2 += sep2 + "{" + k + "}[" + k2 + "]=" + this.urlEncoder(stripslashes(String.valueOf(v2)));
					sep2 = "&";
				}
				s.append(sep).append(s2);
			} else {
				s.append(sep).append(k).append("=").append(this.urlEncoder(stripslashes(String.valueOf(v))));
			}
			sep = "&";
		}
		String postdata = apiRequestData(module, action, s.toString(), "");

		return this.openSocket(this.ucAPI + "/index.php", 500000, postdata, "", true, this.ucIp, 20, true);
	}

	/**
	 * 构造发送给用户中心的请求数据
	 * 
	 * @param string
	 *            module 请求的模块
	 * @param string
	 *            action 请求的动作
	 * @param string
	 *            arg 参数（会加密的方式传送）
	 * @param string
	 *            extra 附加参数（传送时不加密）
	 * @return string
	 */
	protected String apiRequestData(String module, String action, String arg, String extra) {
		String input = this.apiInput(arg);
		String post = "m=" + module + "&a=" + action + "&inajax=2&release=" + this.ucClientRelease + "&input=" + input + "&appid="
				+ this.ucAppId + extra;
		return post;
	}

	protected String apiUrl(String module, String action, String arg, String extra) {
		String url = this.ucAPI + "/index.php?" + apiRequestData(module, action, arg, extra);
		return url;
	}

	public String apiInput(String data) {
		// String s = data;
		// String s =
		// urlencode(uc_authcode(data+"&agent="+md5(_SERVER["HTTP_USER_AGENT"])+"&time="+time(),
		// "ENCODE", UC_KEY));
		// String s =
		// urlencode(uc_authcode(data+"&agent="+md5("Java/1.5.0_01")+"&time="+time(),
		// "ENCODE", UC_KEY));
		String s = this.urlEncoder(this.authCode(data + "&agent=" + md5("") + "&time=" + time(), "ENCODE", this.ucKey));
		return s;
	}

	/**
	 * MYSQL 方式取指定的模块和动作的数据
	 * 
	 * @param string
	 *            model 请求的模块
	 * @param string
	 *            action 请求的动作
	 * @param string
	 *            args 参数（会加密的方式传送）
	 * @return mix
	 */
	public String apiMysql(String model, String action, Map<String, Object> args) {
		// global uc_controls;
		// if(empty(uc_controls[model])) {
		// include_once UC_ROOT.'./lib/db.class.php';
		// include_once UC_ROOT.'./model/base.php';
		// include_once UC_ROOT."./control/model.php";
		// eval("\\uc_controls['model'] = new {model}control();");
		// }
		if (action.charAt(0) != '_') {
			// args = uc_addslashes(args, 1, true);
			// action = "on"+action;
			// uc_controls[model]->input = args;
			// return uc_controls[model]->action(args);
			return null;
		} else {
			return "";
		}
	}

	/**
	 * 字符串加密以及解密函数
	 * 
	 * @param string
	 *            string 原文或者密文
	 * @param string
	 *            operation 操作(ENCODE | DECODE), 默认为 DECODE
	 * @param string
	 *            key 密钥
	 * @param int expiry 密文有效期, 加密时候有效， 单位 秒，0 为永久有效
	 * @return string 处理后的 原文或者 经过 base64_encode 处理后的密文
	 * 
	 * @example
	 * 
	 *          a = authcode('abc', 'ENCODE', 'key'); b = authcode(a, 'DECODE',
	 *          'key'); // b(abc)
	 * 
	 *          a = authcode('abc', 'ENCODE', 'key', 3600); b = authcode('abc',
	 *          'DECODE', 'key'); // 在一个小时内，b(abc)，否则 b 为空
	 */
	public String authCode(String string, String operation) {
		return authCode(string, operation, null);
	}

	public String authCode(String string, String operation, String key) {
		return authCode(string, operation, key, 0);
	}

	public String authCode(String string, String operation, String key, int expiry) {

		// note 随机密钥长度 取值 0-32;
		int ckey_length = 4;
		// note 加入随机密钥，可以令密文无任何规律，即便是原文和密钥完全相同，加密结果也会每次不同，增大破解难度。
		// note 取值越大，密文变动规律越大，密文变化 = 16 的 ckey_length 次方
		// note 当此值为 0 时，则不产生随机密钥

		key = md5(key != null ? key : this.ucKey);
		String keya = md5(substr(key, 0, 16));
		String keyb = md5(substr(key, 16, 16));
		String keyc = ckey_length > 0 ? (operation.equals("DECODE") ? substr(string, 0, ckey_length) : substr(md5(microtime()),
				-ckey_length)) : "";

		String cryptkey = keya + md5(keya + keyc);
		int key_length = cryptkey.length();

		string = operation.equals("DECODE") ? base64Decode(substr(string, ckey_length))
				: sprintf("%010d", expiry > 0 ? expiry + time() : 0) + substr(md5(string + keyb), 0, 16) + string;
		int string_length = string.length();

		StringBuffer result1 = new StringBuffer();

		int[] box = new int[256];
		for (int i = 0; i < 256; i++) {
			box[i] = i;
		}

		int[] rndkey = new int[256];
		for (int i = 0; i <= 255; i++) {
			rndkey[i] = (int) cryptkey.charAt(i % key_length);
		}

		int j = 0;
		for (int i = 0; i < 256; i++) {
			j = (j + box[i] + rndkey[i]) % 256;
			int tmp = box[i];
			box[i] = box[j];
			box[j] = tmp;
		}

		j = 0;
		int a = 0;
		for (int i = 0; i < string_length; i++) {
			a = (a + 1) % 256;
			j = (j + box[a]) % 256;
			int tmp = box[a];
			box[a] = box[j];
			box[j] = tmp;

			result1.append((char) (((int) string.charAt(i)) ^ (box[(box[a] + box[j]) % 256])));

		}

		if (operation.equals("DECODE")) {
			String result = result1.substring(0, result1.length());

			if ((Integer.parseInt(substr(result.toString(), 0, 10)) == 0 || Long.parseLong(substr(result.toString(), 0, 10)) - time() > 0)
					&& substr(result.toString(), 10, 16).equals(substr(md5(substr(result.toString(), 26) + keyb), 0, 16))) {
				return substr(result.toString(), 26);
			} else {
				return "";
			}
		} else {
			return keyc + this.base64Encode(result1.toString()).replaceAll("=", "");
		}
	}

	/**
	 * 远程打开URL
	 * 
	 * @param string
	 *            url 打开的url，　如 http://www.baidu.com/123.htm
	 * @param int limit 取返回的数据的长度
	 * @param string
	 *            post 要发送的 POST 数据，如uid=1&password=1234
	 * @param string
	 *            cookie 要模拟的 COOKIE 数据，如uid=123&auth=a2323sd2323
	 * @param bool
	 *            bysocket TRUE/FALSE 是否通过SOCKET打开
	 * @param string
	 *            ip IP地址
	 * @param int timeout 连接超时时间
	 * @param bool
	 *            block 是否为阻塞模式 defaul valuet:true
	 * @return 取到的字符串
	 */
	protected String openSocket(String url, int limit, String post, String cookie, boolean bysocket, String ip, int timeout, boolean block) {
		// long __times__ = isset(_GET["__times__"]) ? intval(_GET["__times__"])
		// + 1 : 1;
		// if(__times__ > 2) {
		// return "";
		// }
		url += url.indexOf("?") > 0 ? "&" : "?" + "__times__=1";

		return this.openSocketInner(url, limit, post, cookie, bysocket, ip, timeout, block);
	}

	/**
	 * 用socket连接远程api服务器
	 * 
	 * @param url
	 * @param limit
	 * @param post
	 * @param cookie
	 * @param bysocket
	 * @param ip
	 * @param timeout
	 * @param block
	 * @return
	 */

	protected String openSocketInner(String url, int limit, String post, String cookie, boolean bysocket, String ip, int timeout,
			boolean block) {

		String result = "";
		String host = "";
		String path = "";
		int port = 80;
		try {
			URL matches = new URL(url);
			host = matches.getHost();
			path = matches.getPath() != null ? matches.getPath() + (matches.getQuery() != null ? "?" + matches.getQuery() : "") : "/";
			if (matches.getPort() > 0)
				port = matches.getPort();
		} catch (MalformedURLException e1) {
			logger.error(e1);
		}

		StringBuffer out = new StringBuffer();
		if (post != null && post.length() > 0) {
			out.append("POST ").append(path).append(" HTTP/1.1\r\n");
			out.append("Accept: */*\r\n");
			out.append("Accept-Language: zh-cn\r\n");
			out.append("Content-Type: application/x-www-form-urlencoded\r\n");
			out.append("User-Agent: \r\n");
			out.append("Host: ").append(host).append("\r\n");
			out.append("Content-Length: ").append(post.length()).append("\r\n");
			out.append("Connection: Close\r\n");
			out.append("Cache-Control: no-cache\r\n");
			out.append("Cookie: \r\n\r\n");
			out.append(post);
		} else {
			out.append("GET path HTTP/1.1\r\n");
			out.append("Accept: */*\r\n");
			out.append("Accept-Language: zh-cn\r\n");
			out.append("User-Agent: Java/1.7.0_05\r\n");
			out.append("Host: host\r\n");
			out.append("Connection: Close\r\n");
			out.append("Cookie: cookie\r\n\r\n");
		}

		SocketChannel socketChannel = null;
		BufferedReader bufferedReader = null;
		try {

			InetSocketAddress remoterSocketAddress = new InetSocketAddress(ip != null && ip.length() > Integer.valueOf(10) ? ip : host,
					port);

			// 打开nio连接
			socketChannel = SocketChannel.open(remoterSocketAddress);

			// 远程没有相应的连接，直接返回空
			if (!socketChannel.isConnected()) {
				return "";
			}

			// 进行Nio 缓存数据处理
			ByteBuffer revBuffer = ByteBuffer.allocate(Integer.valueOf(1024));

			// socket发送远程请求数据
			socketChannel.write(charset.encode(out.toString()));

			while (socketChannel.read(revBuffer) != Integer.valueOf(-1)) {
				revBuffer.flip();
				// 读取响应数据，且进行解码
				result = this.charset.decode(revBuffer).toString();
				revBuffer.clear();
			}
			// 放入缓存流中进行，进行缓存数据处理，读取核心数据
			bufferedReader = new BufferedReader(new StringReader(result));

			// 通过行的形式过滤http响应头,discuz数据相应，头与数据体中间有空行(协议约定)，
			while (true) {
				String readLine = bufferedReader.readLine();
				if (readLine == null || readLine.length() == Integer.valueOf(0) || readLine == "\r\n" || readLine == "\n") {
					break;
				}
			}
			// 读取余下核心响应数据
			StringBuilder stringBuilder = new StringBuilder();
			while (true) {
				String coreData = bufferedReader.readLine();
				if (coreData == null || coreData.length() == 0) {
					break;
				}
				stringBuilder.append(coreData);
			}
			return stringBuilder.toString();

		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (socketChannel != null) {
				try {
					socketChannel.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
		return result;

		// Socket fp = new Socket(ip != null && ip.length() > 10 ? ip : host,
		// port);
		// if (!fp.isConnected()) {
		// // note errstr : errno \r\n
		// return "";
		// } else {
		//
		// OutputStream os = fp.getOutputStream();
		// os.write(out.toString().getBytes());
		//
		// InputStream ins = fp.getInputStream();
		//
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(ins));
		// while (true) {
		// String header = reader.readLine();
		// if (header == null || header.equals("") || header == "\r\n" || header
		// == "\n") {
		// break;
		// }
		// }
		//
		// while (true) {
		// String data = reader.readLine();
		// if (data == null || data.equals("")) {
		// break;
		// } else {
		// result += data;
		// }
		// }
		//
		// fp.close();
		// }
		// } catch (IOException e) {
		// logger.error(e);
		// }
		// return result;
	}

	public String isApp() {
		String result = this.callUserFunction(this.ucApiFunc, "app", "ls", null);
		return this.ucConnect.equals("mysql") ? result : unserialize(result);
	}

	/**
	 * 用户注册
	 * 
	 * @param string
	 *            username 用户名
	 * @param string
	 *            password 密码
	 * @param string
	 *            email Email
	 * @param int questionid 安全提问
	 * @param string
	 *            answer 安全提问答案
	 * @return int -1 : 用户名不合法 -2 : 包含不允许注册的词语 -3 : 用户名已经存在 -4 : email 格式有误 -5 :
	 *         email 不允许注册 -6 : 该 email 已经被注册 >1 : 表示成功，数值为 UID
	 */
	public String userRegister(String username, String password, String email) {
		return userRegister(username, password, email, "", "");
	}

	public String userRegister(String username, String password, String email, String questionid, String answer) {

		Map<String, Object> args = new HashMap<String, Object>();

		args.put("username", username);
		args.put("password", password);
		args.put("email", email);
		args.put("questionid", questionid);
		args.put("answer", answer);

		return this.callUserFunction(this.ucApiFunc, "user", "register", args);
	}

	/**
	 * 用户登陆检查
	 * 
	 * @param string
	 *            username 用户名/uid
	 * @param string
	 *            password 密码
	 * @param int isuid 是否为uid
	 * @param int checkques 是否使用检查安全问答
	 * @param int questionid 安全提问
	 * @param string
	 *            answer 安全提问答案
	 * @return array (uid/status, username, password, email) 数组第一项 1 : 成功 -1 :
	 *         用户不存在,或者被删除 -2 : 密码错
	 */
	public String userLogin(String username, String password) {

		return this.userLogin(username, password, 0, 0);

	}

	public String userLogin(String username, String password, int isuid, int checkques) {

		return this.userLogin(username, password, isuid, checkques, "", "");

	}

	public String userLogin(String username, String password, int isuid, int checkques, String questionid, String answer) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("username", username);
		args.put("password", password);
		args.put("isuid", isuid);
		args.put("checkques", checkques);
		args.put("questionid", questionid);
		args.put("answer", answer);

		String result = this.callUserFunction(this.ucApiFunc, "user", "login", args);

		return this.ucConnect.equals("mysql") ? result : this.unserialize(result);

	}

	/**
	 * 进入同步登录代码
	 * 
	 * @param int uid 用户ID
	 * @return string HTML代码
	 */
	public String userSynLogin(long uid) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("uid", uid);
		String result = this.apiPost("user", "synlogin", args);

		return result;
	}

	/**
	 * 进入同步登出代码
	 * 
	 * @return string HTML代码
	 */
	public String userSynLogout(long uid) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("uid", uid);
		return this.apiPost("user", "synlogout", args);
	}

	/**
	 * 取得用户数据
	 * 
	 * @param string
	 *            username 用户名
	 * @param int isuid 是否为UID
	 * @return array (uid, username, email)
	 */
	public String getUser(String username, int isuid) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("username", username);
		args.put("isuid", isuid);
		String result = this.callUserFunction(this.ucApiFunc, "user", "get_user", args);

		return this.ucConnect.equals("mysql") ? result : this.unserialize(result);
	}

	/**
	 * 编辑用户
	 * 
	 * @param username 用户名
	 * @param oldpw 旧密码
	 * @param newpw 新密码
	 * @param email Email
	 * @param ignoreoldpw 是否忽略旧密码, 1-忽略旧密码, 则不进行旧密码校验.0-校验旧密码
	 * @param questionid 安全提问
	 * @param answer 安全提问答案
	 * @return int 
	 * 	1 : 修改成功 0 : 没有任何修改 -1 : 旧密码不正确 -4 : email 格式有误 
	 * -5 : email 不允许注册 -6 : 该 email 已经被注册 -7 : 没有做任何修改 -8 : 受保护的用户，没有权限修改
	 */
	public String userEdit(String username, String oldpw, String newpw, String email, int ignoreoldpw, String questionid, String answer) {

		Map<String, Object> args = new HashMap<String, Object>();

		args.put("username", username);
		args.put("oldpw", oldpw);
		args.put("newpw", newpw);
		args.put("email", email);
		args.put("ignoreoldpw", ignoreoldpw);
		if (!Asserts.assertStringIsNull(questionid)) {
			args.put("questionid", questionid);			
		}
		if (!Asserts.assertStringIsNull(answer)) {
			args.put("answer", answer);
		}
		return callUserFunction(this.ucApiFunc, "user", "edit", args);
	}

	/**
	 * 删除用户
	 * 
	 * @param string
	 *            /array uid 用户的 UID
	 * @return int >0 : 成功 0 : 失败
	 */
	public String userDelete(String uid) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("uid", uid);

		return callUserFunction(this.ucApiFunc, "user", "delete", args);
	}

	/**
	 * 删除用户头像
	 * 
	 * @param string
	 *            /array uid 用户的 UID
	 */
	public String userDeleteAvatar(String uid) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("uid", uid);

		return this.apiPost("user", "deleteavatar", args);
	}

	/**
	 * @param isInUC
	 *            the isInUC to set
	 */
//	public void setInUC(boolean isInUC) {
//		this.isInUC = isInUC;
//	}

	/**
	 * @param ucIp
	 *            the ucIp to set
	 */
	public void setUcIp(String ucIp) {
		this.ucIp = ucIp;
	}

	/**
	 * @param ucAPI
	 *            the ucAPI to set
	 */
	public void setUcAPI(String ucAPI) {
		this.ucAPI = ucAPI;
	}

	/**
	 * @param ucConnect
	 *            the ucConnect to set
	 */
	public void setUcConnect(String ucConnect) {
		this.ucConnect = ucConnect;
	}

	/**
	 * @param ucKey
	 *            the ucKey to set
	 */
	public void setUcKey(String ucKey) {
		this.ucKey = ucKey;
	}

	/**
	 * @param ucAppId
	 *            the ucAppId to set
	 */
	public void setUcAppId(String ucAppId) {
		this.ucAppId = ucAppId;
	}

	/**
	 * @param ucClientVersion
	 *            the ucClientVersion to set
	 */
//	public void setUcClientVersion(String ucClientVersion) {
//		this.ucClientVersion = ucClientVersion;
//	}

	/**
	 * @param ucClientRelease
	 *            the ucClientRelease to set
	 */
	public void setUcClientRelease(String ucClientRelease) {
		this.ucClientRelease = ucClientRelease;
	}

	/**
	 * @param ucRoot
	 *            the ucRoot to set
	 */
//	public void setUcRoot(String ucRoot) {
//		this.ucRoot = ucRoot;
//	}

	/**
	 * @param ucDataDir
	 *            the ucDataDir to set
	 */
//	public void setUcDataDir(String ucDataDir) {
//		this.ucDataDir = ucDataDir;
//	}

	/**
	 * @param ucApiFunc
	 *            the ucApiFunc to set
	 */
	public void setUcApiFunc(String ucApiFunc) {
		this.ucApiFunc = ucApiFunc;
	}

	/**
	 * @param ucDataUrl
	 *            the ucDataUrl to set
	 */
//	public void setUcDataUrl(String ucDataUrl) {
//		this.ucDataUrl = ucDataUrl;
//	}

	public String getUcIp() {
		return ucIp;
	}

	public String getUcAPI() {
		return ucAPI;
	}

	public String getUcConnect() {
		return ucConnect;
	}

	public String getUcKey() {
		return ucKey;
	}

	public String getUcAppId() {
		return ucAppId;
	}

}
