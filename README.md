# 大冲锋服务器部署与使用文档
泄漏的源码用AI跑了下，目前只能进地图简单玩～

> 免责声明：本仓库当前仅为测试版本，主要用于复现/学习/联调。已修复内容以“我已修复范围”优先，其他功能仍有未补全特性，建议仅用于本地测试和研究，后续更新请按你自己的版本情况继续完善。
## 一、服务器配置

| 项目 | 值 |
|------|-----|
| 服务器 IP | **自定义**（在 start_all.sh 开头 `SERVER_IP` 变量配置，见第三节） |
| 客户端游戏连接端口 | `15000`（proxyserver） |
| 认证服务器端口 | `18080`（login_server.py，返回 token/游戏地址，可通过 `AUTH_PORT` 调整） |
| 信息服务器 | `8081`（jetty9，运行 bj.war） |
| 测试账号 | `test01` ~ `test05`（密码 `123456`） |

**测试账号对应角色**（已预建，含装备/背包数据）：test01 ~ test05，进入大厅/对战可直接用。

---

## 二、客户端连接配置（怎么配）

客户端通过**认证服务器**获取 token 和游戏地址，再连接游戏服务器。服务器 IP 由你自己决定，分三处配置：

**1. 服务器启动脚本 `start_all.sh`**
开头有 `SERVER_IP` 变量，改成你的服务器 IP（客户端连频道用）。

**2. Windows 登录脚本（推荐用 18080 版本）**
`start_test01_18080.bat` ~ `start_test05_18080.bat`  
或用 `start_client_18080.bat <账号> <客户端路径>`
每个脚本开头有：
```bat
set SERVER_IP=192.168.31.5
set SERVER_PORT=15000
set AUTH_PORT=18080
```
把 `SERVER_IP` 改成你的服务器 IP。脚本会用它启动客户端。

**3. 启动参数（FinalCombat.exe 传参格式）**
客户端通过认证服务器获取 token，启动参数格式（参考 launcher）：
```
FinalCombat.exe -info <账号> -login <token> -proxysvrip <服务器IP> -proxysvrport <15000>
```
bat 脚本会自动：请求 `http://服务器IP:18080/confirm` 获取 token，然后按上述格式启动客户端。  
如果你修改了端口，请同步改 `AUTH_PORT`。

认证服务器由 `login_server.py` 提供（start_all.sh 自动启动），`confirm` 接口返回：
```json
{"ok":true,"token":"...","game_ip":"你的IP","game_port":15000}
```

### 启动客户端（Windows）
把 `start_test01_18080.bat` ~ `start_test05_18080.bat` 复制到任意位置，双击运行即可（也可用命令行参数指定客户端 exe 路径）：

```bat
start_test01_18080.bat D:\FinalCombat\game\FinalCombat.exe
```

脚本只负责启动客户端，不修改任何系统配置。登录界面输入对应账号（test01~test05）和密码 123456。

---

## 三、启动服务器（Linux/WSL）

```bash
cd /home/wsxw40/下载/FinalCombatServer
AUTH_PORT=18080 INFO_PORT=18081 DUMMY_PORTS=28085,28086,28222 SERVER_PORT=15000 bash start_all.sh        # 一键启动全部服务
```

若你使用 Windows 下的 `start_server_18080.bat`，它会把以上参数自动带上。

Windows 快速入口（直接调用 WSL）：

```bat
start_server_18080.bat
start_test01_18080.bat D:\FinalCombat\game\FinalCombat.exe
quick_start_18080.bat D:\FinalCombat\game\FinalCombat.exe test01
check_server_18080.bat
stop_server_18080.bat
```

如需直接把当前工作版本上传到 GitHub，可直接执行：

```bat
upload_18080_to_github.ps1 -Branch main -Message "chore: add 18080 workflow scripts"
```

示例：

```bat
powershell -ExecutionPolicy Bypass -File .\upload_18080_to_github.ps1 -Branch main -Message "chore: add 18080 workflow scripts"
```

**服务端口**：
| 服务 | 端口 |
|------|------|
| info server (jetty) | 8081 |
| proxyserver (客户端入口) | 15000 |
| channelserver (新手区/自由区) | 9011/9012/9013 |
| matchingserver | 16000 |
| apexserver | 9003 |
| logserver | 11111 |
| dummy logserver (防 InfoLogger NPE) | 28085/28086/28222（默认） |

停止：`bash stop_all.sh`  
（Windows 下用 `stop_server_18080.bat` 一键停止）

---

## 四、已修复问题汇总

| 现象 | 根因 | 修复 |
|------|------|------|
| 建角色/登录返回 FAIL | 大量表及 5600+ 分片表缺列 | 按 resultMap 批量补列/建表 |
| 建角色 FK 失败 | SYS_CHARACTER 缺第 7 职业 | 补 ID=7 |
| 建角色列值数不匹配 | PlayerTrack insert 无列名 | 补显式列名 |
| 登录超时(20s+) | Redis 配置指向不可达外网 | WAR 内 applicationContext.xml 改 127.0.0.1:6379 |
| 进大厅 GetCharacter NPE | 职业6 被禁用但仍被引用 | 启用 SYS_CHARACTER 6 |
| 进大厅 GetCharacterList NPE | 生化 buff 物品 5302/5363/5389 缺失且无价格 | 补物品 + PAYMENT |
| 登录 medalSysItem NPE | SYS_ITEM 4479 勋章缺失 | 补物品 |
| 房间名过长(1字也不行) | SYS_CONFIG 缺 roomname/teamname 长度 | 补配置 |
| 频道灰色不可点 | 服务器未配频道服务器 | 为服务器1/5 各启动 channelserver |
| 仓库/背包空白 | getPlayerItemMap 过滤返回空 | 修复物品字段(IS_GIFT/GUN_PROPERTY 等 NULL) |
| LogClient NPE | 日志服务器不可达 | 指向本地 + dummy logserver |

---

## 五、已知问题 / 未解决

1. **火箭炮（Luncher 类型）客户端无法开火**
   - 现象：火箭兵主武器按左键无反应，客户端不发任何开火请求；副武器(散弹枪)正常
   - 诊断：`W_ID=17`（Luncher 投射物类型）客户端不识别，改成 `W_ID=5`（散弹枪）后能开火但无模型
   - 原因推测：客户端与服务器（bj.war + gameserver 源码）的 Luncher 武器数据格式可能版本不匹配
   - 当前处理：火箭炮保持 `W_ID=17`，发射问题未彻底解决

2. **仓库武器名显示 Temp**
   - 现象：部分武器名显示 "Temp"
   - 原因：客户端名称表（SN 消息翻译）加载异常或版本不匹配
   - 影响：武器基础功能正常，名字显示异常

3. **团队竞技/推车/占点模式无频道**
   - 服务器列表这几个模式频道数为 0（灰色不可进）
   - 解决：为对应 server-id 启动 channelserver

4. **客户端与服务器版本差异**
   - 客户端较新、服务器源码较旧（bj.war + gameserver），导致部分数据（如 Luncher 武器格式）不兼容
   - 医疗/工程职业的高品质武器需自行创建（已创建 5390/5391/5392）

---

## 六、数据库配置修改点（bj 库）

### SYS_CONFIG（房间名长度等）
```sql
INSERT INTO SYS_CONFIG (`KEY`, VALUE, CONF_NAME) VALUES
('roomname.minlength','1','房间名最小长度'),
('roomname.maxlength','12','房间名最大长度'),
('teamname.minlength','1','战队名最小长度'),
('teamname.maxlength','6','战队名最大长度'),
('ads.button','1','广告'),
('switch.exitopenexe','1','退出开关'),
('newer.buff.version','100','新手buff版本');
-- 缺失的 http.* / compete.* / client.switch 等也要补齐(参考 GetSysConfig.java)
```

### SYS_CHARACTER
```sql
-- 补第7职业
INSERT INTO SYS_CHARACTER (ID,...) SELECT 7,... FROM SYS_CHARACTER WHERE ID=4;
-- 启用职业6
UPDATE SYS_CHARACTER SET IS_DELETED='N' WHERE ID=6;
```

### SYS_ITEM / PAYMENT
```sql
-- 4479 勋章
INSERT INTO SYS_ITEM (ID,NAME,DISPLAY_NAME,TYPE,SUB_TYPE,LEVEL,IS_DELETED) VALUES (4479,'medal','勋章',5,1,1,'N');
-- 生化 buff 物品 + 价格
INSERT INTO SYS_ITEM (ID,NAME,DISPLAY_NAME,TYPE,SUB_TYPE,LEVEL,IS_DELETED,I_VALUE) VALUES
(5302,'bio_buff_1','生化人BUFF1',7,1,1,'N','1'),
(5363,'bio_buff_2','生化人BUFF2',7,1,1,'N','2'),
(5389,'bio_buff_3','生化人BUFF3',7,1,1,'N','3');
INSERT INTO PAYMENT (ITEM_ID,PAY_TYPE,UNIT_TYPE,COST,UNIT,FINISH_COST,FINISH_PAY_TYPE,IS_SHOW,LEVEL) VALUES
(5302,2,1,1600,1,0,0,0,0),(5363,2,1,1600,1,0,0,0,0),(5389,2,1,1600,1,0,0,0,0);
-- RESOURCE_STABLE/CHANGEABLE 不能为 NULL
UPDATE SYS_ITEM SET RESOURCE_STABLE='', RESOURCE_CHANGEABLE='' WHERE RESOURCE_STABLE IS NULL OR RESOURCE_CHANGEABLE IS NULL;
-- I_VALUE 不能为空(武器 Float.parseFloat 会 NFE)
UPDATE SYS_ITEM SET I_VALUE='0' WHERE I_VALUE IS NULL OR I_VALUE='';
```

### 缓存刷新（改完必须做）
```bash
printf "flush_all\r\n" | nc -w 1 127.0.0.1 11211   # 清 memcached
sudo systemctl restart jetty9                        # 重启 info server
```

### 职业/武器配置
```sql
-- 启用医疗(6)/工程(7)职业可选
UPDATE SYS_CHARACTER SET IS_DEFAULT='Y' WHERE ID IN (6,7);
-- 工程兵模型/碰撞体
UPDATE SYS_CHARACTER SET RESOURCE_NAME='engineer', CONTROLLER_HEIGHT=0.75, CONTROLLER_RADIUS=0.5 WHERE ID=7;
-- 医疗/工程高品质武器（自建）
INSERT INTO SYS_ITEM (ID,NAME,DISPLAY_NAME,W_ID,CHARACTER_ID) VALUES
(5390,'syringegun_hi','魅紫色医疗针枪',9,6),
(5391,'laser_hi','魅紫色治疗光枪',15,6),
(5392,'init_weapon_7a_hi','魅紫色工程枪',3,7);
-- 创建房间显示全部模式
UPDATE SERVER SET GAME_TYPE=1048575;  -- 位掩码全开
```

### 数据迁移（test01-05）
`migrate_test_accounts.sh` 把原 test 角色数据复制给 test01-05，并清理旧账户（test、840409603）。已执行完成。

---

## 七、WAR 修改要点（重要）
运行中的 info server 代码来自 `/usr/share/jetty9/webapps/bj.war`（不是 `/var/lib/bjapp`）。
Spring 加载的是 WAR 内 `WEB-INF/applicationContext.xml`（不是 classes/ 副本）。
修改流程：解包 WAR → 改文件 → `jar cfM` 重新打包 → 覆盖 bj.war → `systemctl restart jetty9`（确认 PID 变化）。
已修改：applicationContext.xml（Redis/log 指向本地）、PlayerTrack.xml（insert 列名）、新增 GetGuildTeamNotice servlet。
