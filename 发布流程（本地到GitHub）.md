# FinalCombatServer 发布到 GitHub（本地文件到云端）

适用场景：当前目录下所有变更已经是你希望分享/备份的版本。  

> 建议先把本次要发的范围整理到一个独立提交，避免把无关历史改动一并带上。

## 一、先确认仓库远端

在终端执行：

```bash
git remote -v
```

应看到你的仓库地址（例如 `origin  https://github.com/<user>/<repo>.git`）。

若未配置，执行：

```bash
git remote add origin https://github.com/<你的GitHub用户>/<仓库名>.git
```

## 二、检查本次改动（可复用）

```bash
git status
git diff -- README.md start_all.sh stop_all.sh check_status.sh logserver_dummy.py
```

## 三、提交（可先 dry-run）

```bash
git add README.md start_all.sh stop_all.sh check_status.sh \
  stop_server_18080.bat check_server_18080.bat start_server_18080.bat \
  start_client_18080.bat start_test01_18080.bat start_test02_18080.bat \
  start_test03_18080.bat start_test04_18080.bat start_test05_18080.bat \
  quick_start_18080.bat logserver_dummy.py

git commit -m "chore: add 18080 test workflow scripts and pid-based service control"
```

## 四、推送

```bash
git branch -M main
git push -u origin main
```

如你使用的是已有分支，可先 `git checkout` 对应分支再 push。

## 五、仅共享“本地不保留”说明（可在README保留）

如果只想让本地代码继续独立存在，不受后续本地删除影响：
- 删除本地文件不会影响已推送到 GitHub 的历史提交；
- 但若未来要恢复修改，需要重新 `git clone` 或 `git pull` 该仓库。

