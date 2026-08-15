#include <windows.h>
#include <windowsx.h>
#include <wininet.h>
#include <wincrypt.h>
#include <commctrl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>
#include <wchar.h>
#include <stdarg.h>

#pragma comment(lib, "wininet.lib")
#pragma comment(lib, "gdi32.lib")
#pragma comment(lib, "user32.lib")
#pragma comment(lib, "advapi32.lib")
#pragma comment(lib, "crypt32.lib")
#pragma comment(lib, "comctl32.lib")

#define API_HOST "login.knine.art"
#define API_PORT 443
#define VER "2026.08.07.4"
#define SID "s1"
#define MAX_RETRY 180
#define BUFFER_SIZE 4096
#define HASH_LENGTH 64
#define REG_KEY "Software\\GameLauncher"
#define REG_QQ "QQ"
#define REG_PASSWORD "Password"
#define TIMER_CLOSE 1001

// 自定义消息 - 用于线程向UI发送日志
#define WM_USER_LOG (WM_USER + 100)
#define WM_USER_LOGIN_DONE (WM_USER + 101)
#define WM_USER_LOGIN_ERROR (WM_USER + 102)
#define WM_USER_ENABLE_BTN (WM_USER + 103)
#define WM_USER_LUCKY_LOG (WM_USER + 104)
#define WM_USER_LUCKY_DONE (WM_USER + 105)
#define WM_USER_QUERY_LOGIN_DONE (WM_USER + 106)
#define WM_USER_QUERY_ENABLE (WM_USER + 107)

#define SAFE_FREE(ptr) do { if (ptr) { free(ptr); (ptr) = NULL; } } while(0)

// 窗口尺寸
#define WIN_WIDTH 400
#define WIN_HEIGHT 600
#define ID_UI_ANIM 2001

// 彩盒查询窗口尺寸
#define LUCK_WIN_WIDTH  960
#define LUCK_WIN_HEIGHT 620
#define LUCK_MAX_ROWS   20000
#define LUCK_MAX_AWARDS 20000
#define LUCK_PAGE_SIZE  50

#define ID_EDIT_DATE_FROM 1201
#define ID_EDIT_DATE_TO   1202
#define ID_BTN_QUERY      1210
#define ID_BTN_LUCKY      1211
#define ID_LIST_LUCKY     1220
#define ID_EDIT_LUCKY_LOG 1230

#define COL_BG_TOP        RGB(240, 246, 255)
#define COL_BG_BOTTOM     RGB(224, 234, 252)
#define COL_PANEL         RGB(255, 255, 255)
#define COL_PANEL_BORDER  RGB(212, 224, 243)
#define COL_INPUT_BG      RGB(255, 255, 255)
#define COL_TEXT          RGB(35, 55, 90)
#define COL_MUTED         RGB(105, 125, 160)
#define COL_ACCENT        RGB(64, 128, 245)
#define COL_ACCENT_DARK   RGB(45, 100, 210)
#define COL_BTN_TOP       RGB(82, 150, 250)
#define COL_BTN_BOTTOM    RGB(37, 96, 222)

#define ID_EDIT_QQ      1001
#define ID_EDIT_PWD     1002
#define ID_BTN_LOGINRUN 1003
#define ID_EDIT_LOG     1005
#define ID_BTN_CLOSE    1007

HWND g_hwnd = NULL;
HWND g_edQQ, g_edPwd, g_btnLoginRun, g_edLog, g_btnClose;
HBRUSH g_editBgBrush, g_logBgBrush, g_accentBrush, g_accentDimBrush, g_btnShineBrush, g_panelBrush;
HFONT g_fontNormal, g_fontSmall, g_fontTitle, g_fontLog, g_fontButton, g_fontDate;
int g_uiAnimTick = 0;
int g_uiAnimOn = 0;
int g_focusField = 0;

typedef struct {
    int hover;
    int pressed;
    WNDPROC oldProc;
} BtnState;
BtnState g_loginBtnState;
BtnState g_closeBtnState;
BtnState g_luckyBtnState;
HWND g_btnLucky;
BtnState g_queryBtnState;
char g_gamePath[MAX_PATH] = {0};
char g_exeDir[MAX_PATH] = {0};
char *g_token = NULL;
char *g_gameIP = NULL;
char *g_gamePort = NULL;
int g_gameExists = 0;
HANDLE g_loginThread = NULL;
volatile int g_loginRunning = 0;
volatile int g_closing = 0;

// ================= 彩盒查询数据 =================
typedef struct {
    long long id;
    char created_at[32];
    int level;
    char level_name[64];
    int pay_type;
    char pay_text[32];
    int multiple;
    long long medal_cost;
    long long draw_before;
    long long draw_after;
char status[16];
    long long medal_gain;      // 勋章返还
    int award_count;
    char award_summary[512];   // 本行获得的奖品摘要
    char award_detail[2048];   // 本行完整奖品明细（多行）
} LuckyRow;

typedef struct {
    char name[128];
    long long qty;
    int count;
} AwardAgg;

typedef struct {
    char name[128];
    char date[16];
    long long qty;
    int row;
} AwardEntry;

static LuckyRow *g_rows = NULL;
static int g_row_count = 0;
static int g_row_cap = 0;
static AwardAgg *g_awards = NULL;
static int g_award_count = 0;
static int g_award_cap = 0;
static AwardEntry *g_award_entries = NULL;
static int g_award_entry_count = 0;
static int g_award_entry_cap = 0;

static char *g_lucky_token = NULL;
static volatile int g_queryRunning = 0;
static HANDLE g_queryThread = NULL;
static HWND g_hwndLucky = NULL;
static int g_luckyTab = 0;   // 0记录明细 1按日期 2奖励汇总
static char g_dateFrom[16] = {0};
static char g_dateTo[16] = {0};
static HWND g_edDateFrom = NULL;
static HWND g_edDateTo = NULL;
static long long g_qTotalCount, g_qTotalMulti, g_qTotalCost, g_qTotalGain;
static int g_luckyLoginDone = 0;


// 窗口居中
void CenterWindow(HWND hWnd)
{
    RECT rc;
    GetWindowRect(hWnd, &rc);
    int width = rc.right - rc.left;
    int height = rc.bottom - rc.top;

    int screenWidth = GetSystemMetrics(SM_CXSCREEN);
    int screenHeight = GetSystemMetrics(SM_CYSCREEN);

    int x = (screenWidth - width) / 2;
    int y = (screenHeight - height) / 2;

    SetWindowPos(hWnd, NULL, x, y, 0, 0, SWP_NOSIZE | SWP_NOZORDER);
}

static wchar_t* Utf8ToWide(const char* src)
{
    if(!src) return NULL;
    int sz = MultiByteToWideChar(CP_UTF8,0,src,-1,NULL,0);
    if(sz <= 0) return NULL;
    wchar_t *buf = (wchar_t*)malloc(sizeof(wchar_t)*(sz+4));
    MultiByteToWideChar(CP_UTF8,0,src,-1,buf,sz);
    return buf;
}

static char* WideToUtf8(const wchar_t* src)
{
    if(!src) return NULL;
    int sz = WideCharToMultiByte(CP_UTF8,0,src,-1,NULL,0,NULL,NULL);
    if(sz <= 0) return NULL;
    char *buf = (char*)malloc(sz+4);
    WideCharToMultiByte(CP_UTF8,0,src,-1,buf,sz,NULL,NULL);
    return buf;
}

// 直接写入日志（UI线程专用）
void GuiLogDirect(const char* msg)
{
    wchar_t *wtext = Utf8ToWide(msg);
    if(wtext)
    {
        int len = GetWindowTextLengthW(g_edLog);
        SendMessageW(g_edLog, EM_SETSEL, len, len);
        SendMessageW(g_edLog, EM_REPLACESEL, 0, (LPARAM)wtext);
        free(wtext);
    }
}

// 线程安全的日志函数 - 通过PostMessage发送到UI线程
void GuiLog(const char* fmt,...)
{
    char temp[2048];
    va_list ap;
    va_start(ap,fmt);
    vsnprintf(temp,sizeof(temp)-1,fmt,ap);
    va_end(ap);

    // 窗口正在关闭时不再向UI发送消息
    if (g_closing) return;

    // 如果是UI线程，直接写入
    DWORD uiThreadId = GetWindowThreadProcessId(g_hwnd, NULL);
    if (uiThreadId == 0 || GetCurrentThreadId() == uiThreadId) {
        GuiLogDirect(temp);
    } else {
        // 子线程通过PostMessage发送
        char *msg = malloc(strlen(temp) + 1);
        if (msg) {
            strcpy(msg, temp);
            if (!PostMessageW(g_hwnd, WM_USER_LOG, 0, (LPARAM)msg))
                free(msg);
        }
    }
}

// 处理WM_USER_LOG消息 - UI线程执行
void HandleLogMessage(char* msg)
{
    GuiLogDirect(msg);
    free(msg);
}

int ReadRegistryString(const char* key, const char* value, char* output, DWORD size)
{
    HKEY hKey;
    if (RegOpenKeyA(HKEY_CURRENT_USER, key, &hKey) != ERROR_SUCCESS)
        return 0;
    DWORD type = REG_SZ;
    DWORD dataSize = size;
    int result = RegQueryValueExA(hKey, value, NULL, &type, (BYTE*)output, &dataSize);
    RegCloseKey(hKey);

    // 不探测运行环境：数据已是合法 UTF-8 则直接使用，否则按 GBK 转码（兼容旧数据）
    if (result == ERROR_SUCCESS && output[0])
    {
        wchar_t wbuf[256];
        int n = MultiByteToWideChar(CP_UTF8, MB_ERR_INVALID_CHARS, output, -1, wbuf, 256);
        if (n <= 0)
        {
            n = MultiByteToWideChar(936, 0, output, -1, wbuf, 256);
            if (n > 0)
                WideCharToMultiByte(CP_UTF8, 0, wbuf, -1, output, size, NULL, NULL);
        }
    }
    return (result == ERROR_SUCCESS);
}

void WriteRegistryString(const char* key, const char* value, const char* data)
{
    HKEY hKey;
    if (RegCreateKeyA(HKEY_CURRENT_USER, key, &hKey) == ERROR_SUCCESS)
    {
        // 统一按 UTF-8 写入，读取时自动兼容 GBK 旧数据
        RegSetValueExA(hKey, value, 0, REG_SZ, (BYTE*)data, (DWORD)strlen(data)+1);
        RegCloseKey(hKey);
    }
}

// 密码用DPAPI加密后以REG_BINARY写入注册表，避免明文落盘
static int WriteProtectedString(const char* key, const char* value, const char* data)
{
    HKEY hKey;
    if (RegCreateKeyA(HKEY_CURRENT_USER, key, &hKey) != ERROR_SUCCESS)
        return 0;
    DATA_BLOB in = { (DWORD)strlen(data) + 1, (BYTE*)data };
    DATA_BLOB out = { 0 };
    int ok = 0;
    if (CryptProtectData(&in, L"XiaoCheTui", NULL, NULL, NULL, 0, &out)) {
        ok = (RegSetValueExA(hKey, value, 0, REG_BINARY, out.pbData, out.cbData) == ERROR_SUCCESS);
        LocalFree(out.pbData);
    }
    RegCloseKey(hKey);
    return ok;
}

static int ReadProtectedString(const char* key, const char* value, char* output, DWORD size)
{
    HKEY hKey;
    int ok = 0;
    if (RegOpenKeyA(HKEY_CURRENT_USER, key, &hKey) != ERROR_SUCCESS)
        return 0;
    DWORD type = REG_BINARY;
    DWORD dataSize = 0;
    if (RegQueryValueExA(hKey, value, NULL, &type, NULL, &dataSize) == ERROR_SUCCESS && dataSize > 0)
    {
        BYTE *blob = (BYTE*)malloc(dataSize);
        if (blob) {
            if (RegQueryValueExA(hKey, value, NULL, &type, blob, &dataSize) == ERROR_SUCCESS) {
                DATA_BLOB in = { dataSize, blob };
                DATA_BLOB out = { 0 };
                if (CryptUnprotectData(&in, NULL, NULL, NULL, NULL, 0, &out)) {
                    if (out.cbData > 0) {
                        DWORD copy = out.cbData;
                        if (copy >= size) copy = size - 1;
                        memcpy(output, out.pbData, copy);
                        output[copy] = 0;
                        ok = 1;
                    }
                    LocalFree(out.pbData);
                }
            }
            free(blob);
        }
    }
    RegCloseKey(hKey);
    return ok;
}

typedef struct {
    char machine_id[HASH_LENGTH + 1];
    char mac_hash[HASH_LENGTH + 1];
    char board_uuid_hash[HASH_LENGTH + 1];
    char disk_serial_hash[HASH_LENGTH + 1];
} MachineInfo;

typedef struct {
    unsigned int state[8];
    unsigned long long bitlen;
    unsigned char data[64];
    unsigned int datalen;
} SHA256_CTX;

static const unsigned int sha256_k[64] = {
    0x428a2f98,0x71374491,0xb5c0fbcf,0xe9b5dba5,0x3956c25b,0x59f111f1,0x923f82a4,0xab1c5ed5,
    0xd807aa98,0x12835b01,0x243185be,0x550c7dc3,0x72be5d74,0x80deb1fe,0x9bdc06a7,0xc19bf174,
    0xe49b69c1,0xefbe4786,0x0fc19dc6,0x240ca1cc,0x2de92c6f,0x4a7484aa,0x5cb0a9dc,0x76f988da,
    0x983e5152,0xa831c66d,0xb00327c8,0xbf597fc7,0xc6e00bf3,0xd5a79147,0x06ca6351,0x14292967,
    0x27b70a85,0x2e1b2138,0x4d2c6dfc,0x53380d13,0x650a7354,0x766a0abb,0x81c2c92e,0x92722c85,
    0xa2bfe8a1,0xa81a664b,0xc24b8b70,0xc76c51a3,0xd192e819,0xd6990624,0xf40e3585,0x106aa070,
    0x19a4c116,0x1e376c08,0x2748774c,0x34b0bcb5,0x391c0cb3,0x4ed8aa4a,0x5b9cca4f,0x682e6ff3,
    0x748f82ee,0x78a5636f,0x84c87814,0x8cc70208,0x90befffa,0xa4506ceb,0xbef9a3f7,0xc67178f2
};

#define SHA256_ROTR(x,n) (((x) >> (n)) | ((x) << (32 - (n))))
#define SHA256_SHR(x,n) ((x) >> (n))

static void sha256_transform(SHA256_CTX *ctx, const unsigned char data[64])
{
    unsigned int w[64], a, b, c, d, e, f, g, h;
    for (int i = 0; i < 16; i++)
        w[i] = ((unsigned int)data[i*4] << 24) | ((unsigned int)data[i*4+1] << 16) |
               ((unsigned int)data[i*4+2] << 8) | (unsigned int)data[i*4+3];
    for (int i = 16; i < 64; i++) {
        unsigned int s0 = SHA256_ROTR(w[i-15],7) ^ SHA256_ROTR(w[i-15],18) ^ SHA256_SHR(w[i-15],3);
        unsigned int s1 = SHA256_ROTR(w[i-2],17) ^ SHA256_ROTR(w[i-2],19) ^ SHA256_SHR(w[i-2],10);
        w[i] = w[i-16] + s0 + w[i-7] + s1;
    }
    a = ctx->state[0]; b = ctx->state[1]; c = ctx->state[2]; d = ctx->state[3];
    e = ctx->state[4]; f = ctx->state[5]; g = ctx->state[6]; h = ctx->state[7];
    for (int i = 0; i < 64; i++) {
        unsigned int S1 = SHA256_ROTR(e,6) ^ SHA256_ROTR(e,11) ^ SHA256_ROTR(e,25);
        unsigned int ch = (e & f) ^ (~e & g);
        unsigned int temp1 = h + S1 + ch + sha256_k[i] + w[i];
        unsigned int S0 = SHA256_ROTR(a,2) ^ SHA256_ROTR(a,13) ^ SHA256_ROTR(a,22);
        unsigned int maj = (a & b) ^ (a & c) ^ (b & c);
        unsigned int temp2 = S0 + maj;
        h = g; g = f; f = e; e = d + temp1; d = c; c = b; b = a; a = temp1 + temp2;
    }
    ctx->state[0] += a; ctx->state[1] += b; ctx->state[2] += c; ctx->state[3] += d;
    ctx->state[4] += e; ctx->state[5] += f; ctx->state[6] += g; ctx->state[7] += h;
}

static void sha256_init(SHA256_CTX *ctx)
{
    ctx->bitlen = 0;
    ctx->datalen = 0;
    ctx->state[0]=0x6a09e667; ctx->state[1]=0xbb67ae85; ctx->state[2]=0x3c6ef372; ctx->state[3]=0xa54ff53a;
    ctx->state[4]=0x510e527f; ctx->state[5]=0x9b05688c; ctx->state[6]=0x1f83d9ab; ctx->state[7]=0x5be0cd19;
}

static void sha256_update(SHA256_CTX *ctx, const unsigned char *data, size_t len)
{
    for (size_t i = 0; i < len; i++) {
        ctx->data[ctx->datalen++] = data[i];
        if (ctx->datalen == 64) {
            sha256_transform(ctx, ctx->data);
            ctx->bitlen += 512;
            ctx->datalen = 0;
        }
    }
}

static void sha256_final(SHA256_CTX *ctx, unsigned char hash[32])
{
    unsigned int i = ctx->datalen;
    ctx->data[i++] = 0x80;
    if (i > 56) {
        while (i < 64) ctx->data[i++] = 0;
        sha256_transform(ctx, ctx->data);
        i = 0;
    }
    while (i < 56) ctx->data[i++] = 0;
    unsigned long long bitlen = ctx->bitlen + (unsigned long long)ctx->datalen * 8;
    ctx->data[56] = (unsigned char)(bitlen >> 56);
    ctx->data[57] = (unsigned char)(bitlen >> 48);
    ctx->data[58] = (unsigned char)(bitlen >> 40);
    ctx->data[59] = (unsigned char)(bitlen >> 32);
    ctx->data[60] = (unsigned char)(bitlen >> 24);
    ctx->data[61] = (unsigned char)(bitlen >> 16);
    ctx->data[62] = (unsigned char)(bitlen >> 8);
    ctx->data[63] = (unsigned char)bitlen;
    sha256_transform(ctx, ctx->data);
    for (i = 0; i < 8; i++) {
        hash[i*4]   = (unsigned char)(ctx->state[i] >> 24);
        hash[i*4+1] = (unsigned char)(ctx->state[i] >> 16);
        hash[i*4+2] = (unsigned char)(ctx->state[i] >> 8);
        hash[i*4+3] = (unsigned char)ctx->state[i];
    }
}

static void sha256_hex(const char* input, char* output)
{
    static const char hexdig[] = "0123456789abcdef";
    SHA256_CTX ctx;
    unsigned char digest[32];
    sha256_init(&ctx);
    sha256_update(&ctx, (const unsigned char*)input, strlen(input));
    sha256_final(&ctx, digest);
    for (int i = 0; i < 32; i++) {
        output[i*2] = hexdig[digest[i] >> 4];
        output[i*2+1] = hexdig[digest[i] & 15];
    }
    output[64] = 0;
}

static void hash_machine_field(const char* label, const char* source, char* output)
{
    char buf[512];
    snprintf(buf, sizeof(buf), "%s|%s", label, source);
    sha256_hex(buf, output);
}

// 纯模拟设备标识：不读取任何真实硬件信息
// 首次运行生成随机 ID 并保存在注册表，之后保持不变，虚拟机与实体机行为完全一致
static int get_simulated_device_id(char* out, size_t size)
{
    char saved[64] = {0};
    if (ReadRegistryString(REG_KEY, "DeviceID", saved, sizeof(saved)) && saved[0])
    {
        snprintf(out, size, "%s", saved);
        return 1;
    }

    char hex[33] = {0};
    unsigned char rnd[16] = {0};
    HCRYPTPROV prov = 0;
    BOOL ok = 0;
    if (CryptAcquireContextA(&prov, NULL, NULL, PROV_RSA_FULL, CRYPT_VERIFYCONTEXT))
    {
        ok = CryptGenRandom(prov, sizeof(rnd), rnd);
        CryptReleaseContext(prov, 0);
    }
    if (!ok)
    {
        // 极低概率的回退：用时间与随机种子模拟
        srand((unsigned)time(NULL) ^ (unsigned)GetTickCount());
        for (int i = 0; i < 16; i++)
            rnd[i] = (unsigned char)(rand() & 0xFF);
    }
    for (int i = 0; i < 16; i++)
        sprintf(hex + i * 2, "%02X", rnd[i]);

    HKEY hKey;
    if (RegCreateKeyA(HKEY_CURRENT_USER, REG_KEY, &hKey) == ERROR_SUCCESS)
    {
        RegSetValueExA(hKey, "DeviceID", 0, REG_SZ, (BYTE*)hex, (DWORD)strlen(hex) + 1);
        RegCloseKey(hKey);
    }
    snprintf(out, size, "%s", hex);
    return 1;
}

void init_machine_info(MachineInfo* info)
{
    char device[64] = {0};
    if (!get_simulated_device_id(device, sizeof(device)))
        strcpy(device, "sim-unknown");

    // 四个字段均由同一个模拟 ID 派生，保持请求结构不变
    hash_machine_field("machine_id", device, info->machine_id);
    hash_machine_field("mac_hash", device, info->mac_hash);
    hash_machine_field("board_uuid", device, info->board_uuid_hash);
    hash_machine_field("disk_serial", device, info->disk_serial_hash);
}

char* safe_strdup(const char* src)
{
    if(!src) return NULL;
    size_t l = strlen(src);
    char *p = malloc(l+1);
    memcpy(p,src,l+1);
    return p;
}

char* extract_json_string(const char* json, const char* key)
{
    char pattern[256];
    snprintf(pattern,sizeof(pattern),"\"%s\":\"",key);
    const char* p = strstr(json,pattern);
    if(!p) return NULL;
    p += strlen(pattern);
    const char* end = strchr(p,'"');
    if(!end) return NULL;
    int len = (int)(end-p);
    char *res = malloc(len+1);
    strncpy(res,p,len);
    res[len]=0;
    return res;
}

char* extract_json_number(const char* json, const char* key)
{
    char pattern[256];
    snprintf(pattern,sizeof(pattern),"\"%s\":",key);
    const char* p = strstr(json,pattern);
    if(!p) return NULL;
    p += strlen(pattern);
    while(*p==' '||*p=='\t')p++;
    if(*p=='-')p++;
    const char*s=p;
    while(*p&&(isdigit(*p)||*p=='.'))p++;
    int len=(int)(p-s);
    char *res=malloc(len+1);
    strncpy(res,s,len);
    res[len]=0;
    return res;
}

int extract_json_bool(const char* json, const char* key)
{
    char pattern[256];
    snprintf(pattern,sizeof(pattern),"\"%s\":",key);
    const char* p = strstr(json,pattern);
    if(!p) return 0;
    p += strlen(pattern);
    while(*p == ' ' || *p == '\t') p++;
    if(strncmp(p,"true",4)==0) return 1;
    return 0;
}

// ================= 彩盒查询 JSON 解析 =================

// 定位对象中指定 key 对应的值起始（处理嵌套时避免误匹配同名字段）
static const char* json_find_value(const char* obj, const char* key)
{
    char pattern[128];
    int keylen = snprintf(pattern, sizeof(pattern), "\"%s\"", key);
    const char* p = obj;
    while ((p = strstr(p, pattern)) != NULL) {
        const char* before = p - 1;
        while (before > obj && (*before==' '||*before=='\t'||*before=='\n'||*before=='\r')) before--;
        if (before >= obj && (*before=='{'||*before==',')) {
            p += keylen;
            while (*p==' '||*p=='\t'||*p=='\n'||*p=='\r'||*p==':') p++;
            return p;
        }
        p += keylen;
    }
    return NULL;
}

// 解析 JSON 字符串值（含常见转义），写入 out
static int json_str_value(const char* v, char* out, int outlen)
{
    if (!v || *v != '"') return 0;
    v++;
    int i = 0;
    while (*v && *v != '"' && i < outlen - 1) {
        if (*v == '\\') {
            v++;
            if (*v == 'n') out[i++] = '\n';
            else if (*v == 't') out[i++] = '\t';
            else if (*v == 'r') out[i++] = '\r';
            else if (*v == '"') out[i++] = '"';
            else if (*v == '\\') out[i++] = '\\';
            else if (*v) out[i++] = *v;
            v++;
        } else {
            out[i++] = *v++;
        }
    }
    out[i] = 0;
    return 1;
}

// 从指向 '{' 或 '[' 的位置找到配对的 '}' 或 ']'
static const char* json_match_bracket(const char* p)
{
    if (!p || (*p != '{' && *p != '[')) return NULL;
    char open = *p;
    char close = (*p == '{') ? '}' : ']';
    int depth = 0;
    int inStr = 0;
    while (*p) {
        if (inStr) {
            if (*p == '\\') { p += 2; continue; }
            if (*p == '"') inStr = 0;
        } else {
            if (*p == '"') inStr = 1;
            else if (*p == open) depth++;
            else if (*p == close) {
                depth--;
                if (depth == 0) return p;
            }
        }
        p++;
    }
    return NULL;
}

static void ensure_rows(int n)
{
    if (g_row_count + n <= g_row_cap) return;
    int newcap = g_row_cap ? g_row_cap : 512;
    while (newcap < g_row_count + n) newcap *= 2;
    LuckyRow* nr = realloc(g_rows, sizeof(LuckyRow) * newcap);
    if (nr) { g_rows = nr; g_row_cap = newcap; }
}

static void ensure_awards(int n)
{
    if (g_award_count + n <= g_award_cap) return;
    int newcap = g_award_cap ? g_award_cap : 512;
    while (newcap < g_award_count + n) newcap *= 2;
    AwardAgg* na = realloc(g_awards, sizeof(AwardAgg) * newcap);
    if (na) { g_awards = na; g_award_cap = newcap; }
}

// 解析单个 award 对象并聚合
static void parse_award(const char* obj)
{
    char name[128] = {0};
    char type[64] = {0};
    long long qty = 1;
    long long duration = 0;
    const char* v;
    if ((v = json_find_value(obj, "name"))) json_str_value(v, name, sizeof(name));
    if ((v = json_find_value(obj, "quantity"))) qty = strtoll(v, NULL, 10);
    if ((v = json_find_value(obj, "type"))) json_str_value(v, type, sizeof(type));
    if ((v = json_find_value(obj, "duration"))) duration = strtoll(v, NULL, 10);
    if (!name[0]) return;

    int i;
    for (i = 0; i < g_award_count; i++)
        if (strcmp(g_awards[i].name, name) == 0) break;
    if (i >= g_award_count) {
        ensure_awards(1);
        if (i >= g_award_cap) return;
        strncpy(g_awards[i].name, name, sizeof(g_awards[i].name) - 1);
        g_awards[i].qty = 0;
        g_awards[i].count = 0;
        g_award_count++;
    }
    g_awards[i].qty += qty;
    g_awards[i].count++;

    // 追加到按日期汇总条目表（奖励汇总按日期范围过滤用）
    if (g_award_entry_count >= g_award_entry_cap) {
        int nc = g_award_entry_cap ? g_award_entry_cap * 2 : 1024;
        AwardEntry *na = realloc(g_award_entries, sizeof(AwardEntry) * nc);
        if (na) { g_award_entries = na; g_award_entry_cap = nc; }
    }
    if (g_award_entry_count < g_award_entry_cap) {
        AwardEntry *ae = &g_award_entries[g_award_entry_count++];
        memset(ae, 0, sizeof(*ae));
        strncpy(ae->name, name, sizeof(ae->name) - 1);
        strncpy(ae->date, g_rows[g_row_count].created_at, 10);
        ae->qty = qty;
        ae->row = g_row_count;
    }

if (strstr(name, "勋章"))
        g_rows[g_row_count].medal_gain += qty;

    // 追加到本行奖品摘要
    LuckyRow* ar = &g_rows[g_row_count];
    if (ar->award_summary[0])
        strncat(ar->award_summary, "、", sizeof(ar->award_summary) - strlen(ar->award_summary) - 1);
    char ap[96];
    if (qty > 1)
        snprintf(ap, sizeof(ap), "%sx%lld", name, qty);
    else
        snprintf(ap, sizeof(ap), "%s", name);
    strncat(ar->award_summary, ap, sizeof(ar->award_summary) - strlen(ar->award_summary) - 1);

    // 追加到本行奖品明细
    char dl[256];
    if (duration > 0)
        snprintf(dl, sizeof(dl), "[%s] %s x%lld (%lld 天)\r\n", type[0] ? type : "奖励", name, qty, duration);
    else
        snprintf(dl, sizeof(dl), "[%s] %s x%lld\r\n", type[0] ? type : "奖励", name, qty);
    strncat(ar->award_detail, dl, sizeof(ar->award_detail) - strlen(ar->award_detail) - 1);
}

// 解析一行记录对象（awards 单独聚合）
static void parse_row(const char* obj)
{
    ensure_rows(1);
    if (g_row_count >= g_row_cap) return;
    LuckyRow* r = &g_rows[g_row_count];
    memset(r, 0, sizeof(LuckyRow));
    const char* v;
    if ((v = json_find_value(obj, "id"))) r->id = strtoll(v, NULL, 10);
    if ((v = json_find_value(obj, "created_at"))) json_str_value(v, r->created_at, sizeof(r->created_at));
    if ((v = json_find_value(obj, "level"))) r->level = atoi(v);
    if ((v = json_find_value(obj, "level_name"))) json_str_value(v, r->level_name, sizeof(r->level_name));
    if ((v = json_find_value(obj, "pay_type"))) r->pay_type = atoi(v);
    if ((v = json_find_value(obj, "pay_text"))) json_str_value(v, r->pay_text, sizeof(r->pay_text));
    if ((v = json_find_value(obj, "multiple"))) r->multiple = atoi(v);
    if ((v = json_find_value(obj, "medal_cost"))) r->medal_cost = strtoll(v, NULL, 10);
    if ((v = json_find_value(obj, "draw_before"))) r->draw_before = strtoll(v, NULL, 10);
    if ((v = json_find_value(obj, "draw_after"))) r->draw_after = strtoll(v, NULL, 10);
    if ((v = json_find_value(obj, "status"))) json_str_value(v, r->status, sizeof(r->status));

    if ((v = json_find_value(obj, "awards")) && *v == '[') {
        const char* p = v + 1;
        while (1) {
            while (*p==' '||*p=='\t'||*p=='\n'||*p=='\r'||*p==',') p++;
            if (*p==']' || !*p) break;
            if (*p=='{') {
                const char* end = json_match_bracket(p);
                if (!end) break;
                int len = (int)(end - p + 1);
                char* ao = malloc(len + 1);
                if (ao) {
                    memcpy(ao, p, len); ao[len] = 0;
                    parse_award(ao);
                    free(ao);
                    r->award_count++;
                }
                p = end + 1;
            } else {
                while (*p && *p != ',' && *p != ']') p++;
            }
        }
    }
    g_row_count++;
}

// 从响应中解析 rows 数组，返回本页记录数
static int parse_rows_array(const char* json)
{
    const char* v = json_find_value(json, "rows");
    if (!v || *v != '[') return 0;
    int before = g_row_count;
    const char* p = v + 1;
    while (1) {
        while (*p==' '||*p=='\t'||*p=='\n'||*p=='\r'||*p==',') p++;
        if (*p==']' || !*p) break;
        if (*p=='{') {
            const char* end = json_match_bracket(p);
            if (!end) break;
            int len = (int)(end - p + 1);
            char* obj = malloc(len + 1);
            if (obj) {
                memcpy(obj, p, len); obj[len] = 0;
                parse_row(obj);
                free(obj);
            }
            p = end + 1;
        } else {
            while (*p && *p != ',' && *p != ']') p++;
        }
    }
    return g_row_count - before;
}

// 重置查询数据
static void lucky_reset_data(void)
{
    g_row_count = 0;
    g_award_count = 0;
    g_award_entry_count = 0;
    g_qTotalCount = g_qTotalMulti = g_qTotalCost = g_qTotalGain = 0;
    SAFE_FREE(g_lucky_token);
}

char* https_post(const char* path, const char* body)
{
    HINTERNET hInternet = InternetOpenA("GameLauncher/1.0", INTERNET_OPEN_TYPE_DIRECT, NULL, NULL, 0);
    if(!hInternet) return NULL;
    DWORD timeout = 30000;
    InternetSetOptionA(hInternet, INTERNET_OPTION_CONNECT_TIMEOUT, &timeout, sizeof(timeout));
    InternetSetOptionA(hInternet, INTERNET_OPTION_RECEIVE_TIMEOUT, &timeout, sizeof(timeout));
    InternetSetOptionA(hInternet, INTERNET_OPTION_SEND_TIMEOUT, &timeout, sizeof(timeout));

    HINTERNET hConnect = InternetConnectA(hInternet, API_HOST, 443, NULL, NULL, INTERNET_SERVICE_HTTP,0,0);
    if(!hConnect){InternetCloseHandle(hInternet);return NULL;}
    DWORD flags = INTERNET_FLAG_SECURE | INTERNET_FLAG_IGNORE_CERT_CN_INVALID | INTERNET_FLAG_IGNORE_CERT_DATE_INVALID;
    HINTERNET hRequest = HttpOpenRequestA(hConnect,"POST",path,NULL,NULL,NULL,flags,0);
    if(!hRequest){InternetCloseHandle(hConnect);InternetCloseHandle(hInternet);return NULL;}
    const char* headers = "Content-Type: application/json\r\n";
    BOOL ok = HttpSendRequestA(hRequest,headers,(DWORD)strlen(headers),(LPVOID)body,(DWORD)strlen(body));
    if(!ok){InternetCloseHandle(hRequest);InternetCloseHandle(hConnect);InternetCloseHandle(hInternet);return NULL;}

    char buffer[BUFFER_SIZE];
    DWORD rd=0;
    char *resp = malloc(1); resp[0]=0;
    int total=1;
    while(InternetReadFile(hRequest,buffer,BUFFER_SIZE-1,&rd)&&rd>0)
    {
        buffer[rd]=0;
        total += (int)rd;
        char *newp = realloc(resp,total);
        resp=newp;
        strcat(resp,buffer);
    }
    InternetCloseHandle(hRequest);
    InternetCloseHandle(hConnect);
    InternetCloseHandle(hInternet);
    return resp;
}

// GET 请求，带 Bearer token（彩盒历史接口使用）
char* https_get_json(const char* path, const char* token)
{
    HINTERNET hInternet = InternetOpenA("GameLauncher/1.0", INTERNET_OPEN_TYPE_DIRECT, NULL, NULL, 0);
    if(!hInternet) return NULL;
    DWORD timeout = 30000;
    InternetSetOptionA(hInternet, INTERNET_OPTION_CONNECT_TIMEOUT, &timeout, sizeof(timeout));
    InternetSetOptionA(hInternet, INTERNET_OPTION_RECEIVE_TIMEOUT, &timeout, sizeof(timeout));
    InternetSetOptionA(hInternet, INTERNET_OPTION_SEND_TIMEOUT, &timeout, sizeof(timeout));

    HINTERNET hConnect = InternetConnectA(hInternet, API_HOST, 443, NULL, NULL, INTERNET_SERVICE_HTTP,0,0);
    if(!hConnect){InternetCloseHandle(hInternet);return NULL;}
    DWORD flags = INTERNET_FLAG_SECURE | INTERNET_FLAG_IGNORE_CERT_CN_INVALID | INTERNET_FLAG_IGNORE_CERT_DATE_INVALID;
    HINTERNET hRequest = HttpOpenRequestA(hConnect,"GET",path,NULL,NULL,NULL,flags,0);
    if(!hRequest){InternetCloseHandle(hConnect);InternetCloseHandle(hInternet);return NULL;}

    char headers[512];
    if (token)
        snprintf(headers, sizeof(headers), "Authorization: Bearer %s\r\n", token);
    else
        strcpy(headers, "");
    BOOL ok = HttpSendRequestA(hRequest, headers[0] ? headers : NULL, headers[0] ? (DWORD)strlen(headers) : 0, NULL, 0);
    if(!ok){InternetCloseHandle(hRequest);InternetCloseHandle(hConnect);InternetCloseHandle(hInternet);return NULL;}

    char buffer[BUFFER_SIZE];
    DWORD rd=0;
    char *resp = malloc(1); resp[0]=0;
    int total=1;
    while(InternetReadFile(hRequest,buffer,BUFFER_SIZE-1,&rd)&&rd>0)
    {
        buffer[rd]=0;
        total += (int)rd;
        char *newp = realloc(resp,total);
        resp=newp;
        strcat(resp,buffer);
    }
    InternetCloseHandle(hRequest);
    InternetCloseHandle(hConnect);
    InternetCloseHandle(hInternet);
    return resp;
}

char* build_request_body(const char* client_version, const char* qq, const char* password,
                          const char* ticket, const char* server_id, const MachineInfo* machine_info)
{
    int buffer_size = 4096;
    if (ticket) buffer_size += (int)strlen(ticket);
    if (server_id) buffer_size += (int)strlen(server_id);
    if (qq) buffer_size += (int)strlen(qq);
    if (password) buffer_size += (int)strlen(password);
    buffer_size += (int)(strlen(machine_info->machine_id) + strlen(machine_info->mac_hash) +
                   strlen(machine_info->board_uuid_hash) + strlen(machine_info->disk_serial_hash) + 512);
    char* body = malloc(buffer_size);
    if (!body) return NULL;
    if (ticket && server_id)
    {
        snprintf(body, buffer_size,
            "{\"client_version\":\"%s\",\"client_build\":\"FCLauncherSecure-1.4\","
            "\"login_ticket\":\"%s\",\"server_id\":\"%s\","
            "\"machine_id\":\"%s\",\"mac_hash\":\"%s\",\"board_uuid_hash\":\"%s\",\"disk_serial_hash\":\"%s\"}",
            client_version, ticket, server_id,
            machine_info->machine_id, machine_info->mac_hash,
            machine_info->board_uuid_hash, machine_info->disk_serial_hash);
    }
    else
    {
        snprintf(body, buffer_size,
            "{\"client_version\":\"%s\",\"client_build\":\"FCLauncherSecure-1.4\","
            "\"qq\":\"%s\",\"password\":\"%s\","
            "\"machine_id\":\"%s\",\"mac_hash\":\"%s\",\"board_uuid_hash\":\"%s\",\"disk_serial_hash\":\"%s\"}",
            client_version, qq, password,
            machine_info->machine_id, machine_info->mac_hash,
            machine_info->board_uuid_hash, machine_info->disk_serial_hash);
    }
    return body;
}

// 检查游戏文件是否存在
int CheckGameExists()
{
    g_gameExists = (GetFileAttributesA(g_gamePath) != INVALID_FILE_ATTRIBUTES);
    return g_gameExists;
}

static void RequestClose(void);

int LaunchGameWithDiagnostic(const char* gamePath, const char* workingDir, const char* cmdLine)
{
    GuiLog("[诊断] 游戏路径:%s\r\n",gamePath);
    if (GetFileAttributesA(gamePath) == INVALID_FILE_ATTRIBUTES){
        GuiLog("[错误]未找到游戏可执行文件\r\n");
        return 0;
    }
    STARTUPINFOA si = {0}; si.cb = sizeof(si);
    PROCESS_INFORMATION pi = {0};
    char* cmdLineCopy = _strdup(cmdLine);
    BOOL result = CreateProcessA(gamePath,cmdLineCopy,NULL,NULL,FALSE,CREATE_NO_WINDOW,NULL,workingDir,&si,&pi);
    if (!result)
    {
        DWORD err = GetLastError();
        GuiLog("[错误]CreateProcess失败 错误码:%lu\r\n",err);
        free(cmdLineCopy);
        return 0;
    }
    GuiLog("[诊断]游戏已启动，3秒之后关闭登录器\r\n",pi.dwProcessId);
    CloseHandle(pi.hProcess); CloseHandle(pi.hThread);
    free(cmdLineCopy);

    RequestClose();
    return 1;
}

// 登录线程函数
DWORD WINAPI LoginThreadProc(LPVOID lpParam)
{
    g_loginRunning = 1;
    
    char **params = (char**)lpParam;
    char *qq = params[0];
    char *pwd = params[1];
    int loginOK = 0;
    char *ticket = NULL;
    
    if(!qq || !pwd || strlen(qq) == 0)
    {
        GuiLog("[警告]请填写账号\r\n");
        g_loginRunning = 0;
        PostMessageW(g_hwnd, WM_USER_ENABLE_BTN, 1, 0);
        SAFE_FREE(qq); SAFE_FREE(pwd); free(params);
        return 0;
    }
    
    GuiLog("[信息]开始发起登录\r\n");

    MachineInfo mi; 
    init_machine_info(&mi);
    char *body = build_request_body(VER, qq, pwd, NULL, NULL, &mi);
    char *prepResp = https_post("/api/launcher/login/prepare", body);
    SAFE_FREE(body);

    if(!prepResp){
        GuiLog("[错误]登录准备请求失败\r\n");
        g_loginRunning = 0;
        PostMessageW(g_hwnd, WM_USER_ENABLE_BTN, 1, 0);
        SAFE_FREE(qq); SAFE_FREE(pwd); free(params);
        return 0;
    }
    if (g_closing) {
        SAFE_FREE(prepResp);
        g_loginRunning = 0;
        SAFE_FREE(qq); SAFE_FREE(pwd); free(params);
        return 0;
    }
    
    int ok = extract_json_bool(prepResp, "ok");
    if(!ok)
    {
        char *emsg = extract_json_string(prepResp, "message");
        GuiLog("[登录失败] %s\r\n", emsg ? emsg : "未知错误");
        SAFE_FREE(emsg);
        SAFE_FREE(prepResp);
        g_loginRunning = 0;
        PostMessageW(g_hwnd, WM_USER_ENABLE_BTN, 1, 0);
        SAFE_FREE(qq); SAFE_FREE(pwd); free(params);
        return 0;
    }
    
    ticket = extract_json_string(prepResp, "login_ticket");
    SAFE_FREE(prepResp);
    
    if(!ticket){
        GuiLog("[错误]未获取登录票据\r\n");
        g_loginRunning = 0;
        PostMessageW(g_hwnd, WM_USER_ENABLE_BTN, 1, 0);
        SAFE_FREE(qq); SAFE_FREE(pwd); free(params);
        return 0;
    }
    if (g_closing) {
        SAFE_FREE(ticket);
        g_loginRunning = 0;
        SAFE_FREE(qq); SAFE_FREE(pwd); free(params);
        return 0;
    }

    SAFE_FREE(g_token); SAFE_FREE(g_gameIP); SAFE_FREE(g_gamePort);
    g_token = NULL; g_gameIP = NULL; g_gamePort = NULL;

    for(int i = 0; i < MAX_RETRY && !g_closing; i++)
    {
        char *confirmBody = build_request_body(VER, NULL, NULL, ticket, SID, &mi);
        char *cResp = https_post("/api/launcher/login/confirm", confirmBody);
        SAFE_FREE(confirmBody);
        
        if (g_closing) {
            SAFE_FREE(cResp);
            break;
        }
        if(!cResp){
            GuiLog("[错误]确认请求失败\r\n");
            break;
        }
        
        int queue = extract_json_bool(cResp, "queued");
        if(queue)
        {
            char *pos = extract_json_number(cResp, "position");
            GuiLog("[排队]当前位置 %s (%d/%d)\r\n", pos ? pos : "?", i + 1, MAX_RETRY);
            SAFE_FREE(pos);
            for (int s = 0; s < 10 && !g_closing; s++) Sleep(100);
            SAFE_FREE(cResp);
            continue;
        }
        
        if(extract_json_bool(cResp, "ok"))
        {
            g_token = extract_json_string(cResp, "token");
            g_gameIP = extract_json_string(cResp, "game_ip");
            g_gamePort = extract_json_number(cResp, "game_port");
            if(g_token && g_gameIP && g_gamePort) {
                loginOK = 1;
                SAFE_FREE(cResp);
                break;
            }
        }
        SAFE_FREE(cResp);
        break;
    }
    
    SAFE_FREE(ticket);
    
    if(!loginOK){
        GuiLog("[错误]登录确认失败\r\n");
        g_loginRunning = 0;
        PostMessageW(g_hwnd, WM_USER_ENABLE_BTN, 1, 0);
        SAFE_FREE(qq); SAFE_FREE(pwd); free(params);
        return 0;
    }
    if (g_closing) {
        SAFE_FREE(g_token); SAFE_FREE(g_gameIP); SAFE_FREE(g_gamePort);
        SAFE_FREE(qq); SAFE_FREE(pwd); free(params);
        g_loginRunning = 0;
        return 0;
    }
    
    GuiLog("[成功]登录完成，正在启动游戏\r\n");

    // 保存密码（在UI线程处理）
    PostMessageW(g_hwnd, WM_USER_LOGIN_DONE, 1, 0);

    // 构建并启动游戏
    size_t cmdline_size = MAX_PATH * 2 + 2048;
    char* cmdLine = malloc(cmdline_size);
    snprintf(cmdLine, cmdline_size, "\"%s\" -info %s -login %s -proxysvrip %s -proxysvrport %s",
        g_gamePath, qq, g_token, g_gameIP, g_gamePort);
    
    LaunchGameWithDiagnostic(g_gamePath, g_exeDir, cmdLine);
    SAFE_FREE(cmdLine);
    SAFE_FREE(qq); SAFE_FREE(pwd); free(params);
    
    g_loginRunning = 0;
    return 0;
}

static void StartUiAnim(void);
static void StopUiAnim(void);

// ================= 彩盒查询 =================

void LuckyLogDirect(const char* msg)
{
    if (!g_hwndLucky || !IsWindow(g_hwndLucky)) return;
    wchar_t *wtext = Utf8ToWide(msg);
    if(wtext)
    {
        HWND ed = GetDlgItem(g_hwndLucky, ID_EDIT_LUCKY_LOG);
        if (ed) {
            int len = GetWindowTextLengthW(ed);
            SendMessageW(ed, EM_SETSEL, len, len);
            SendMessageW(ed, EM_REPLACESEL, 0, (LPARAM)wtext);
            SendMessageW(ed, EM_SCROLLCARET, 0, 0);
        }
        free(wtext);
    }
}

void LuckyLog(const char* fmt,...)
{
    char temp[2048];
    va_list ap;
    va_start(ap,fmt);
    vsnprintf(temp,sizeof(temp)-1,fmt,ap);
    va_end(ap);
    if (g_closing) return;
    DWORD uiThreadId = GetWindowThreadProcessId(g_hwndLucky, NULL);
    if (uiThreadId == 0 || GetCurrentThreadId() == uiThreadId) {
        LuckyLogDirect(temp);
    } else {
        char *msg = malloc(strlen(temp) + 1);
        if (msg) {
            strcpy(msg, temp);
            if (!PostMessageW(g_hwndLucky, WM_USER_LUCKY_LOG, 0, (LPARAM)msg))
                free(msg);
        }
    }
}

// 计算全局统计（概览/按日期/按勋章/奖励明细全部基于 g_rows 实时计算）
typedef struct {
    char key[64];
    long long count, multi, cost, gain;
} StatGroup;

static void compute_stats(void)
{
    g_qTotalCount = g_qTotalMulti = g_qTotalCost = g_qTotalGain = 0;
    for (int i = 0; i < g_row_count; i++) {
        LuckyRow *r = &g_rows[i];
        g_qTotalCount++;
        g_qTotalMulti += r->multiple;
        g_qTotalCost += r->medal_cost;
        g_qTotalGain += r->medal_gain;
    }
}

// 登录彩盒历史接口
static char* lucky_login(const char* qq, const char* pwd)
{
    char body[1024];
    snprintf(body, sizeof(body),
        "{\"qq\":\"%s\",\"password\":\"%s\",\"client_version\":\"%s\","
        "\"launcher_build_id\":\"FCLauncherSecure-1.4\","
        "\"launcher_hash\":\"2d72ef14b58a1b655ad64b4017b6ead17110addff6e677831377da44a1f88d79\"}",
        qq, pwd, VER);
    char *resp = https_post("/api/lucky-history/login", body);
    if (!resp) return NULL;
    if (!extract_json_bool(resp, "ok")) {
        char *emsg = extract_json_string(resp, "message");
        LuckyLog("[登录失败] %s\r\n", emsg ? emsg : "未知错误");
        SAFE_FREE(emsg);
        SAFE_FREE(resp);
        return NULL;
    }
    char *token = extract_json_string(resp, "token");
    SAFE_FREE(resp);
    return token;
}

// 翻页抓取全部记录（pageSize=50，循环到最后一页）
static int lucky_fetch_all(const char* token)
{
    int page = 1;
    int totalPages = 1;
    int lastTotal = 0;
    int guard = 0;

    while (page <= totalPages && !g_closing) {
        char path[128];
        snprintf(path, sizeof(path), "/api/lucky-history/list?page=%d&pageSize=%d", page, LUCK_PAGE_SIZE);

        char *resp = NULL;
        int retry = 0;
        while (retry < 6) {
            resp = https_get_json(path, token);
            if (resp) break;
            retry++;
            LuckyLog("[警告]第%d页请求失败，3秒后重试(%d/6)\r\n", page, retry);
            for (int s = 0; s < 30 && !g_closing; s++) Sleep(100);
        }
        if (!resp || g_closing) {
            SAFE_FREE(resp);
            break;
        }

        // 响应可能包含错误
        if (!extract_json_bool(resp, "ok")) {
            char *emsg = extract_json_string(resp, "message");
            LuckyLog("[错误]第%d页: %s\r\n", page, emsg ? emsg : "未知错误");
            SAFE_FREE(emsg);
            SAFE_FREE(resp);
            break;
        }

        int pages = 1;
        int total = 0;
        char *sp = extract_json_number(resp, "pages");
        char *st = extract_json_number(resp, "total");
        if (sp) { pages = atoi(sp); SAFE_FREE(sp); }
        if (st) { total = atoi(st); SAFE_FREE(st); }
        totalPages = pages;

        int added = parse_rows_array(resp);
        LuckyLog("第%d/%d页 解析 %d 条（累计 %d/%d）\r\n", page, totalPages, added, g_row_count, total);
        SAFE_FREE(resp);

        // 防止死循环保护
        if (added == 0) {
            if (++guard > 3) break;
        } else {
            guard = 0;
        }
        if (g_row_count >= lastTotal && lastTotal > 0 && totalPages <= page) break;
        lastTotal = total;

        page++;
        for (int s = 0; s < 4 && !g_closing; s++) Sleep(100);
    }
    return g_row_count;
}

DWORD WINAPI LuckyQueryThreadProc(LPVOID lpParam)
{
    char **params = (char**)lpParam;
    char *qq = params[0];
    char *pwd = params[1];
    g_queryRunning = 1;

    if(!qq || !pwd || strlen(qq) == 0) {
        LuckyLog("[警告]请填写账号\r\n");
        g_queryRunning = 0;
        PostMessageW(g_hwndLucky, WM_USER_QUERY_ENABLE, 1, 0);
        SAFE_FREE(qq); SAFE_FREE(pwd); free(params);
        return 0;
    }

    LuckyLog("[信息]开始登录彩盒历史接口...\r\n");
    g_lucky_token = lucky_login(qq, pwd);
    if (!g_lucky_token) {
        LuckyLog("[错误]彩盒历史登录失败\r\n");
        g_queryRunning = 0;
        PostMessageW(g_hwndLucky, WM_USER_QUERY_ENABLE, 1, 0);
        SAFE_FREE(qq); SAFE_FREE(pwd); free(params);
        return 0;
    }
    LuckyLog("[成功]登录成功，开始抓取记录...\r\n");

    int n = lucky_fetch_all(g_lucky_token);
    compute_stats();
    LuckyLog("[完成]共抓取 %d 条记录，正在刷新界面...\r\n", n);
    g_luckyLoginDone = 1;
    PostMessageW(g_hwndLucky, WM_USER_LUCKY_DONE, (WPARAM)n, 0);

    SAFE_FREE(qq); SAFE_FREE(pwd); free(params);
    g_queryRunning = 0;
    return 0;
}

void DoLuckyQuery(void)
{
    if (!g_hwndLucky || !IsWindow(g_hwndLucky)) return;
    if (g_queryRunning) {
        LuckyLog("[提示]查询正在进行中，请稍候...\r\n");
        return;
    }

    wchar_t bufQQ[128], bufPwd[128];
    GetWindowTextW(g_edQQ, bufQQ, 120);
    GetWindowTextW(g_edPwd, bufPwd, 120);
    char *qq = WideToUtf8(bufQQ);
    char *pwd = WideToUtf8(bufPwd);
    if(!qq || !pwd || strlen(qq) == 0) {
        LuckyLog("[警告]请填写账号\r\n");
        SAFE_FREE(qq); SAFE_FREE(pwd);
        return;
    }

    lucky_reset_data();
    char **params = malloc(sizeof(char*) * 2);
    params[0] = qq;
    params[1] = pwd;
    g_queryThread = CreateThread(NULL, 0, LuckyQueryThreadProc, params, 0, NULL);
    if (!g_queryThread) {
        LuckyLog("[错误]创建查询线程失败\r\n");
        SAFE_FREE(qq); SAFE_FREE(pwd); free(params);
    }
}

void DoLuckyRefresh(void)
{
    if (g_row_count <= 0) {
        LuckyLog("[提示]暂无数据，请先点击开始查询\r\n");
        return;
    }
    compute_stats();
    PostMessageW(g_hwndLucky, WM_USER_LUCKY_DONE, (WPARAM)g_row_count, 0);
}

void DoLoginAndLaunch()
{
    if (g_loginRunning) {
        GuiLog("[提示]登录正在进行中，请稍候...\r\n");
        return;
    }

    // 点击时再次检测游戏文件
    if (!CheckGameExists()) {
        GuiLog("[错误]未找到 FinalCombat.exe，请将登录器放在游戏目录下\r\n");
        return;
    }

    // 禁用按钮，防止重复点击
    EnableWindow(g_btnLoginRun, FALSE);
    
    // 获取QQ和密码
    wchar_t bufQQ[128], bufPwd[128];
    GetWindowTextW(g_edQQ, bufQQ, 120);
    GetWindowTextW(g_edPwd, bufPwd, 120);
    
    char *qq = WideToUtf8(bufQQ);
    char *pwd = WideToUtf8(bufPwd);
    
    if(!qq || !pwd || strlen(qq) == 0) {
        GuiLog("[警告]请填写账号\r\n");
        EnableWindow(g_btnLoginRun, TRUE);
        SAFE_FREE(qq); SAFE_FREE(pwd);
        return;
    }

    g_uiAnimTick = 0;
    StartUiAnim();
    
    // 创建线程参数
    char** params = malloc(sizeof(char*) * 2);
    params[0] = qq;
    params[1] = pwd;
    
    // 创建登录线程
    g_loginThread = CreateThread(NULL, 0, LoginThreadProc, params, 0, NULL);
    if (!g_loginThread) {
        GuiLog("[错误]创建登录线程失败\r\n");
        EnableWindow(g_btnLoginRun, TRUE);
        StopUiAnim();
        SAFE_FREE(qq); SAFE_FREE(pwd);
        free(params);
    }
}

static void GradientRect(HDC hdc, RECT *rc, COLORREF top, COLORREF bottom)
{
    TRIVERTEX v[2];
    GRADIENT_RECT g = {0, 1};
    v[0].x = rc->left; v[0].y = rc->top;
    v[0].Red = GetRValue(top) * 256;
    v[0].Green = GetGValue(top) * 256;
    v[0].Blue = GetBValue(top) * 256;
    v[0].Alpha = 0;
    v[1].x = rc->right; v[1].y = rc->bottom;
    v[1].Red = GetRValue(bottom) * 256;
    v[1].Green = GetGValue(bottom) * 256;
    v[1].Blue = GetBValue(bottom) * 256;
    v[1].Alpha = 0;
    if (GradientFill(hdc, v, 2, &g, 1, GRADIENT_FILL_RECT_V))
        return;

    // 兼容不支持GradientFill的环境（如部分Wine版本）
    int h = rc->bottom - rc->top;
    if (h <= 0) return;
    int r0 = GetRValue(top), g0 = GetGValue(top), b0 = GetBValue(top);
    int r1 = GetRValue(bottom), g1 = GetGValue(bottom), b1 = GetBValue(bottom);
    for (int y = 0; y < h; y++) {
        int t = (y * 256) / (h - 1);
        COLORREF c = RGB(r0 + ((r1 - r0) * t) / 256,
                         g0 + ((g1 - g0) * t) / 256,
                         b0 + ((b1 - b0) * t) / 256);
        HPEN pen = CreatePen(PS_SOLID, 1, c);
        HPEN old = (HPEN)SelectObject(hdc, pen);
        MoveToEx(hdc, rc->left, rc->top + y, NULL);
        LineTo(hdc, rc->right, rc->top + y);
        SelectObject(hdc, old);
        DeleteObject(pen);
    }
}

static void FillRoundRect(HDC hdc, RECT *rc, int radius, COLORREF color)
{
    HRGN rgn = CreateRoundRectRgn(rc->left, rc->top, rc->right + 1, rc->bottom + 1, radius, radius);
    if (!rgn) return;
    HBRUSH br = CreateSolidBrush(color);
    FillRgn(hdc, rgn, br);
    DeleteObject(br);
    DeleteObject(rgn);
}

static void FrameRoundRect(HDC hdc, RECT *rc, int radius, COLORREF color)
{
    HRGN rgn = CreateRoundRectRgn(rc->left, rc->top, rc->right + 1, rc->bottom + 1, radius, radius);
    if (!rgn) return;
    HBRUSH br = CreateSolidBrush(color);
    FrameRgn(hdc, rgn, br, 1, 1);
    DeleteObject(br);
    DeleteObject(rgn);
}

static void GradientRoundRect(HDC hdc, RECT *rc, int radius, COLORREF top, COLORREF bottom)
{
    HRGN rgn = CreateRoundRectRgn(rc->left, rc->top, rc->right + 1, rc->bottom + 1, radius, radius);
    if (!rgn) return;
    int saved = SaveDC(hdc);
    SelectClipRgn(hdc, rgn);
    GradientRect(hdc, rc, top, bottom);
    RestoreDC(hdc, saved);
    DeleteObject(rgn);
}

static void DrawLabel(HDC hdc, const wchar_t* text, int y)
{
    HFONT old = (HFONT)SelectObject(hdc, g_fontSmall);
    SetBkMode(hdc, TRANSPARENT);
    SetTextColor(hdc, COL_MUTED);
    RECT rc = {26, y, 376, y + 18};
    DrawTextW(hdc, text, -1, &rc, DT_LEFT | DT_SINGLELINE | DT_VCENTER);
    SelectObject(hdc, old);
}

static void DrawField(HDC hdc, RECT *rc, int focused)
{
    FillRoundRect(hdc, rc, 8, COL_INPUT_BG);
    FrameRoundRect(hdc, rc, 8, focused ? COL_ACCENT : COL_PANEL_BORDER);
    RECT hl = {rc->left + 2, rc->bottom - 2, rc->right - 2, rc->bottom};
    FillRect(hdc, &hl, focused ? g_accentDimBrush : g_panelBrush);
}

static void DrawWindowBackground(HDC hdc)
{
    RECT full = {0, 0, WIN_WIDTH, WIN_HEIGHT};
    FillRect(hdc, &full, (HBRUSH)GetStockObject(BLACK_BRUSH));
    GradientRect(hdc, &full, COL_BG_TOP, COL_BG_BOTTOM);

    HFONT old = (HFONT)SelectObject(hdc, g_fontTitle);
    SetBkMode(hdc, TRANSPARENT);
    SetTextColor(hdc, COL_TEXT);
    RECT title = {24, 22, 344, 76};
    DrawTextW(hdc, L"小撤退登录器(非官方)", -1, &title, DT_LEFT | DT_SINGLELINE | DT_VCENTER);
    SelectObject(hdc, old);

    RECT line = {24, 92, 376, 94};
    FillRect(hdc, &line, g_accentBrush);
    RECT line2 = {24, 94, 376, 95};
    FillRect(hdc, &line2, g_accentDimBrush);

    DrawLabel(hdc, L"账号", 118);
    DrawLabel(hdc, L"密码", 192);
    DrawLabel(hdc, L"运行日志", 388);

    RECT qq = {24, 136, 376, 176};
    RECT pwd = {24, 210, 376, 250};
    RECT log = {24, 408, 376, 560};
    DrawField(hdc, &qq, g_focusField == 1);
    DrawField(hdc, &pwd, g_focusField == 2);
    FillRoundRect(hdc, &log, 10, COL_PANEL);
    FrameRoundRect(hdc, &log, 10, COL_PANEL_BORDER);
}

static void DrawLoginButton(LPDRAWITEMSTRUCT dis)
{
    HDC hdc = dis->hDC;
    RECT rc = dis->rcItem;
    int w = rc.right - rc.left;
    int h = rc.bottom - rc.top;
    BOOL enabled = !(dis->itemState & ODS_DISABLED);
    BtnState *st = (BtnState*)GetWindowLongPtrW(dis->hwndItem, GWLP_USERDATA);
    int hover = st ? st->hover : 0;
    int pressed = st ? st->pressed : 0;

    HDC mem = CreateCompatibleDC(hdc);
    HBITMAP bmp = CreateCompatibleBitmap(hdc, w, h);
    HBITMAP oldBmp = (HBITMAP)SelectObject(mem, bmp);
    RECT r = {0, 0, w, h};

    // 清除内存画布，用窗口背景色填充圆角外的边角
    GradientRect(mem, &r, COL_BG_TOP, COL_BG_BOTTOM);

    COLORREF top, bottom, border, textColor;
    if (!enabled) {
        top = RGB(226, 232, 243); bottom = RGB(213, 221, 236);
        border = RGB(198, 208, 228); textColor = RGB(148, 160, 182);
    } else if (pressed) {
        top = RGB(33, 88, 208); bottom = RGB(45, 108, 232);
        border = RGB(120, 170, 255); textColor = RGB(255, 255, 255);
    } else if (hover) {
        top = RGB(96, 160, 252); bottom = RGB(48, 108, 232);
        border = RGB(140, 185, 255); textColor = RGB(255, 255, 255);
    } else {
        top = COL_BTN_TOP; bottom = COL_BTN_BOTTOM;
        border = RGB(110, 165, 250); textColor = RGB(255, 255, 255);
    }

    GradientRoundRect(mem, &r, 12, top, bottom);

    if (!enabled && g_loginRunning) {
        int shineW = w / 3;
        int pos = (g_uiAnimTick * 4) % (w + shineW) - shineW;
        RECT band = {pos, 0, pos + shineW, h};
        HRGN clip = CreateRoundRectRgn(r.left, r.top, r.right + 1, r.bottom + 1, 12, 12);
        int saved = SaveDC(mem);
        SelectClipRgn(mem, clip);
        FillRect(mem, &band, g_btnShineBrush);
        RestoreDC(mem, saved);
        DeleteObject(clip);
    }

    FrameRoundRect(mem, &r, 12, border);

    wchar_t label[32];
    if (g_loginRunning) {
        wcscpy_s(label, 32, L"登录中");
        int n = (int)wcslen(label);
        int dots = (g_uiAnimTick / 10) % 4;
        for (int i = 0; i < dots; i++) label[n++] = L'.';
        label[n] = 0;
    } else {
        wcscpy_s(label, 32, L"开始游戏");
    }

    HFONT oldFont = (HFONT)SelectObject(mem, g_fontButton);
    SetBkMode(mem, TRANSPARENT);
    SetTextColor(mem, textColor);
    RECT textRc = {0, 0, w, h};
    DrawTextW(mem, label, -1, &textRc, DT_CENTER | DT_SINGLELINE | DT_VCENTER);

    RECT triRc = {0, 0, w, h};
    DrawTextW(mem, label, -1, &triRc, DT_CENTER | DT_SINGLELINE | DT_VCENTER | DT_CALCRECT);
    int triX = (w - (triRc.right - triRc.left)) / 2 - 22;
    int cy = h / 2;
    POINT tri[3] = {{triX, cy - 7}, {triX, cy + 7}, {triX + 12, cy}};
    HBRUSH triBrush = CreateSolidBrush(textColor);
    HPEN triPen = CreatePen(PS_SOLID, 1, textColor);
    HBRUSH oldBrush = (HBRUSH)SelectObject(mem, triBrush);
    HPEN oldPen = (HPEN)SelectObject(mem, triPen);
    Polygon(mem, tri, 3);
    SelectObject(mem, oldBrush);
    SelectObject(mem, oldPen);
    DeleteObject(triBrush);
    DeleteObject(triPen);

    SelectObject(mem, oldFont);
    BitBlt(hdc, 0, 0, w, h, mem, 0, 0, SRCCOPY);
    SelectObject(mem, oldBmp);
    DeleteObject(bmp);
    DeleteDC(mem);
}

static void DrawCloseButton(LPDRAWITEMSTRUCT dis)
{
    HDC hdc = dis->hDC;
    RECT rc = dis->rcItem;
    BtnState *st = (BtnState*)GetWindowLongPtrW(dis->hwndItem, GWLP_USERDATA);
    int hover = st ? st->hover : 0;
    int pressed = st ? st->pressed : 0;

    HDC mem = CreateCompatibleDC(hdc);
    HBITMAP bmp = CreateCompatibleBitmap(hdc, rc.right, rc.bottom);
    HBITMAP oldBmp = (HBITMAP)SelectObject(mem, bmp);
    RECT r = {0, 0, rc.right, rc.bottom};
    FillRect(mem, &r, (HBRUSH)GetStockObject(BLACK_BRUSH));
    GradientRect(mem, &r, COL_BG_TOP, COL_BG_BOTTOM);

    RECT circle = {r.left + 2, r.top + 2, r.right - 2, r.bottom - 2};
    FillRoundRect(mem, &circle, 8, pressed ? RGB(209, 219, 237) : hover ? RGB(214, 228, 250) : COL_PANEL);
    if (hover || pressed)
        FrameRoundRect(mem, &circle, 8, RGB(140, 175, 235));

    HPEN pen = CreatePen(PS_SOLID, 2, (hover || pressed) ? COL_ACCENT : RGB(150, 165, 190));
    HPEN oldPen = (HPEN)SelectObject(mem, pen);
    MoveToEx(mem, r.left + 10, r.top + 10, NULL);
    LineTo(mem, r.right - 10, r.bottom - 10);
    MoveToEx(mem, r.right - 10, r.top + 10, NULL);
    LineTo(mem, r.left + 10, r.bottom - 10);
    SelectObject(mem, oldPen);
    DeleteObject(pen);

    BitBlt(hdc, 0, 0, rc.right, rc.bottom, mem, 0, 0, SRCCOPY);
    SelectObject(mem, oldBmp);
    DeleteObject(bmp);
    DeleteDC(mem);
}

static void RequestClose(void)
{
    SetTimer(g_hwnd, TIMER_CLOSE, 3000, NULL);
}

static void StartUiAnim(void)
{
    if (!g_uiAnimOn) {
        g_uiAnimOn = 1;
        SetTimer(g_hwnd, ID_UI_ANIM, 33, NULL);
    }
}

static void StopUiAnim(void)
{
    if (g_uiAnimOn && !g_loginRunning) {
        g_uiAnimOn = 0;
        KillTimer(g_hwnd, ID_UI_ANIM);
    }
}

LRESULT CALLBACK BtnSubclassProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam)
{
    BtnState *st = (BtnState*)GetWindowLongPtrW(hwnd, GWLP_USERDATA);
    switch (msg)
    {
        case WM_MOUSEMOVE:
            if (st && !st->hover) {
                st->hover = 1;
                TRACKMOUSEEVENT tme = {sizeof(tme), TME_LEAVE, hwnd, 0};
                TrackMouseEvent(&tme);
                InvalidateRect(hwnd, NULL, FALSE);
            }
            break;
        case WM_MOUSELEAVE:
            if (st) { st->hover = 0; st->pressed = 0; }
            InvalidateRect(hwnd, NULL, FALSE);
            break;
        case WM_LBUTTONDOWN:
            if (st) st->pressed = 1;
            InvalidateRect(hwnd, NULL, FALSE);
            break;
        case WM_LBUTTONUP:
            if (st) st->pressed = 0;
            InvalidateRect(hwnd, NULL, FALSE);
            break;
        case WM_SETCURSOR:
            if (IsWindowEnabled(hwnd)) {
                SetCursor(LoadCursor(NULL, IDC_HAND));
                return TRUE;
            }
            break;
    }
    return CallWindowProcW(st ? st->oldProc : DefWindowProcW, hwnd, msg, wParam, lParam);
}

// ================= 彩盒查询窗口 =================

static void GradientRect(HDC hdc, RECT *rc, COLORREF top, COLORREF bottom);
static void FillRoundRect(HDC hdc, RECT *rc, int radius, COLORREF color);
static void FrameRoundRect(HDC hdc, RECT *rc, int radius, COLORREF color);
static void DrawCloseButton(LPDRAWITEMSTRUCT dis);
static void StartUiAnim(void);

typedef struct {
HWND hwnd;
    HWND btnTab[3];
    HWND list;
    HWND edLog;
HWND btnQuery;
    int tabs;
} LuckyUi;
static LuckyUi g_luckyUi;

static const wchar_t* LuckyTabNames[] = {
    L"记录明细", L"按日期", L"奖励汇总"
};

// 把 UTF-8 记录写入 ListView 行
static int LvInsertUTF8(HWND lv, const char** cols, int ncols)
{
    LVITEMW item = {0};
    item.mask = LVIF_TEXT;
    item.iItem = SendMessageW(lv, LVM_GETITEMCOUNT, 0, 0);
    item.iSubItem = 0;
    wchar_t *w0 = Utf8ToWide(cols[0]);
    item.pszText = w0 ? w0 : L"";
    int idx = (int)SendMessageW(lv, LVM_INSERTITEMW, 0, (LPARAM)&item);
    free(w0);
    for (int i = 1; i < ncols; i++) {
        wchar_t *w = Utf8ToWide(cols[i]);
        LVITEMW sub = {0};
        sub.mask = LVIF_TEXT;
        sub.iItem = idx;
        sub.iSubItem = i;
        sub.pszText = w ? w : L"";
        SendMessageW(lv, LVM_SETITEMTEXTW, (WPARAM)idx, (LPARAM)&sub);
        free(w);
    }
    return idx;
}

static void LuckySetColumns(int tab)
{
    static const wchar_t* cols0[] = { L"时间", L"彩盒", L"支付", L"倍数", L"勋章支出", L"勋章返还", L"奖品" };
    static const wchar_t* cols1[] = { L"日期", L"次数", L"折算单抽", L"勋章支出", L"勋章返还", L"净消耗" };
    static const wchar_t* cols2[] = { L"奖励名称", L"数量", L"概率" };
    const wchar_t* const* cols = cols0;
    int n = 7;
    switch (tab) {
        case 0: cols = cols0; n = 7; break;
        case 1: cols = cols1; n = 6; break;
        case 2: cols = cols2; n = 3; break;
    }
    RECT rc;
    GetClientRect(g_luckyUi.list, &rc);
    int total = 0;
    int numCnt = 0;
    int wide = 150;                 // 日期/时间/奖品 列宽
    int num = 95;                   // 数字列宽（最多6位数）
    if (tab == 0) {
        // 明细：时间收窄，彩盒加宽，奖品拉到最长
        int tW = 120;                 // 时间（150收窄20%）
        int boxW = 86;                // 彩盒（48加宽80%）
        int narrow = 48;              // 支付/倍数
        int medalW = 77;              // 勋章支出/返还（48加宽60%）
        int otherW = tW + boxW + narrow * 2 + medalW * 2;
        int prizeW = (rc.right - rc.left) - otherW - 2;
        if (prizeW < 200) prizeW = 200;
        int cxs[7] = { tW, boxW, narrow, narrow, medalW, medalW, prizeW };
        for (int i = 0; i < n; i++) {
            LVCOLUMNW col = {0};
            col.mask = LVCF_TEXT | LVCF_WIDTH;
            col.pszText = (wchar_t*)cols[i];
            col.cx = cxs[i];
            SendMessageW(g_luckyUi.list, LVM_INSERTCOLUMNW, i, (LPARAM)&col);
        }
        return;
    }
    for (int i = 0; i < n; i++) {
        int isWide = (tab == 1 && i == 0) || (tab == 2 && i == 0);
        if (isWide) total += wide; else numCnt++;
    }
    int avail = (rc.right - rc.left) - total - (n - numCnt) * 2;
    if (numCnt > 0 && avail > num * numCnt) {
        int extra = (avail - num * numCnt) / numCnt;
        num += extra;
    }
    if (tab == 2) { wide = 460; num = 220; }
    for (int i = 0; i < n; i++) {
        LVCOLUMNW col = {0};
        col.mask = LVCF_TEXT | LVCF_WIDTH;
        col.pszText = (wchar_t*)cols[i];
        int isWide = (tab == 1 && i == 0) || (tab == 2 && i == 0);
        col.cx = isWide ? wide : num;
        SendMessageW(g_luckyUi.list, LVM_INSERTCOLUMNW, i, (LPARAM)&col);
    }
}

// 从日期输入框读取当前起止日期到 g_dateFrom / g_dateTo
static void LuckyGetDateRange(void)
{
    wchar_t wbuf[32];
    if (g_edDateFrom && IsWindow(g_edDateFrom)) {
        GetWindowTextW(g_edDateFrom, wbuf, 32);
        char *s = WideToUtf8(wbuf);
        if (s) { strncpy(g_dateFrom, s, sizeof(g_dateFrom) - 1); g_dateFrom[sizeof(g_dateFrom) - 1] = 0; free(s); }
    }
    if (g_edDateTo && IsWindow(g_edDateTo)) {
        GetWindowTextW(g_edDateTo, wbuf, 32);
        char *s = WideToUtf8(wbuf);
        if (s) { strncpy(g_dateTo, s, sizeof(g_dateTo) - 1); g_dateTo[sizeof(g_dateTo) - 1] = 0; free(s); }
    }
}

// 判断记录日期是否落在起止日期范围内（YYYY-MM-DD 文本比较）
static int LuckyRowInRange(LuckyRow *r)
{
    if (!r->created_at[0]) return 1;
    char d[16];
    strncpy(d, r->created_at, 10); d[10] = 0;
    if (g_dateFrom[0] && strcmp(d, g_dateFrom) < 0) return 0;
    if (g_dateTo[0] && strcmp(d, g_dateTo) > 0) return 0;
    return 1;
}

// 打开窗口时设置默认日期范围（今天往前 14 天）
static void LuckySetDefaultDates(void)
{
    if (!g_edDateFrom || !g_edDateTo) return;
    time_t now = time(NULL);
    struct tm tmNow;
    localtime_s(&tmNow, &now);
    time_t fromTime = now - 14 * 86400;
    struct tm tmFrom;
    localtime_s(&tmFrom, &fromTime);
    wchar_t wfrom[16], wto[16];
    swprintf(wfrom, 16, L"%04d-%02d-%02d", tmFrom.tm_year + 1900, tmFrom.tm_mon + 1, tmFrom.tm_mday);
    swprintf(wto, 16, L"%04d-%02d-%02d", tmNow.tm_year + 1900, tmNow.tm_mon + 1, tmNow.tm_mday);
    SetWindowTextW(g_edDateFrom, wfrom);
    SetWindowTextW(g_edDateTo, wto);
}

static void LuckyPopulate(void)
{
    if (!g_luckyUi.list) return;
    HWND lv = g_luckyUi.list;
    SendMessageW(lv, WM_SETREDRAW, FALSE, 0);
    ListView_DeleteAllItems(lv);
    for (int i = 0; i < 8; i++)
        ListView_DeleteColumn(lv, 0);

    int tab = g_luckyTab;
    LuckySetColumns(tab);

    // 读取日期范围输入框
    LuckyGetDateRange();

    char buf[8][64];
    const char* cols[8];

    if (tab == 0) {
        // 记录明细
        for (int i = 0; i < g_row_count; i++) {
            LuckyRow *r = &g_rows[i];
            if (!LuckyRowInRange(r)) continue;
            const char* cols[7];
            cols[0] = r->created_at;
            cols[1] = r->level_name[0] ? r->level_name : "-";
            cols[2] = r->pay_text[0] ? r->pay_text : "-";
            snprintf(buf[0], sizeof(buf[0]), "%d", r->multiple);
            snprintf(buf[1], sizeof(buf[1]), "%lld", r->medal_cost);
            snprintf(buf[2], sizeof(buf[2]), "%lld", r->medal_gain);
            cols[3] = buf[0]; cols[4] = buf[1]; cols[5] = buf[2]; cols[6] = r->award_summary[0] ? r->award_summary : "-";
            LvInsertUTF8(lv, cols, 7);
        }
    }
    else if (tab == 1) {
        // 按日期聚合
        int cap = 64;
        char dateKeys[64][16];
        long long dcount[64] = {0}, dmulti[64] = {0}, dcost[64] = {0}, dgain[64] = {0};
        int dn = 0;
        for (int i = 0; i < g_row_count; i++) {
            LuckyRow *r = &g_rows[i];
            char d[16];
            strncpy(d, r->created_at, 10); d[10] = 0;
            if (!LuckyRowInRange(r)) continue;
            int j;
            for (j = 0; j < dn; j++) if (strcmp(dateKeys[j], d) == 0) break;
            if (j >= dn && dn < cap) { strcpy(dateKeys[dn], d); dn++; }
            if (j >= dn) continue;
            dcount[j]++; dmulti[j] += r->multiple; dcost[j] += r->medal_cost; dgain[j] += r->medal_gain;
        }
        for (int k = 0; k < dn; k++) {
            const char* cols[6];
            cols[0] = dateKeys[k];
            snprintf(buf[0], sizeof(buf[0]), "%lld", dcount[k]);
            snprintf(buf[1], sizeof(buf[1]), "%lld", dmulti[k]);
            snprintf(buf[2], sizeof(buf[2]), "%lld", dcost[k]);
            snprintf(buf[3], sizeof(buf[3]), "%lld", dgain[k]);
            snprintf(buf[4], sizeof(buf[4]), "%lld", dcost[k] - dgain[k]);
            cols[1] = buf[0]; cols[2] = buf[1]; cols[3] = buf[2]; cols[4] = buf[3]; cols[5] = buf[4];
            LvInsertUTF8(lv, cols, 6);
        }
    }
else if (tab == 2) {
        // 奖励汇总：按日期范围重新聚合
        // 每行固定排除一个与倍率相同的勋章（倍率5排除勋章x5；倍率1排除一个勋章x1，多的保留）
        char *skip = NULL;
        if (g_award_entry_count > 0 && g_row_count > 0) {
            skip = calloc(g_award_entry_count, 1);
            if (skip) {
                for (int i = 0; i < g_row_count; i++) {
                    for (int k = 0; k < g_award_entry_count; k++) {
                        AwardEntry *e = &g_award_entries[k];
                        if (e->row != i || skip[k]) continue;
                        if (strstr(e->name, "勋章") && e->qty == g_rows[i].multiple) {
                            skip[k] = 1;
                            break;
                        }
                    }
                }
            }
        }
        AwardAgg *tmp = NULL;
        int tmpCap = 0, tmpN = 0;
        for (int i = 0; i < g_award_entry_count; i++) {
            if (skip && skip[i]) continue;
            AwardEntry *e = &g_award_entries[i];
            if (e->date[0]) {
                if (g_dateFrom[0] && strcmp(e->date, g_dateFrom) < 0) continue;
                if (g_dateTo[0] && strcmp(e->date, g_dateTo) > 0) continue;
            }
            int j;
            for (j = 0; j < tmpN; j++) if (strcmp(tmp[j].name, e->name) == 0) break;
            if (j >= tmpN) {
                if (tmpN >= tmpCap) {
                    int nc = tmpCap ? tmpCap * 2 : 256;
                    AwardAgg *nt = realloc(tmp, sizeof(AwardAgg) * nc);
                    if (!nt) { free(tmp); tmp = NULL; tmpN = 0; tmpCap = 0; break; }
                    tmp = nt; tmpCap = nc;
                }
                memset(&tmp[tmpN], 0, sizeof(AwardAgg));
                strncpy(tmp[tmpN].name, e->name, sizeof(tmp[tmpN].name) - 1);
                tmpN++;
            }
            if (j < tmpN) { tmp[j].qty += e->qty; tmp[j].count++; }
        }
        long long totalQty = 0;
        for (int i = 0; i < tmpN; i++) totalQty += tmp[i].qty;
        // 按概率从高到低排序（totalQty 相同，按数量降序即等价于按概率降序）
        for (int a = 0; a < tmpN - 1; a++) {
            for (int b = a + 1; b < tmpN; b++) {
                if (tmp[a].qty < tmp[b].qty) {
                    AwardAgg t = tmp[a]; tmp[a] = tmp[b]; tmp[b] = t;
                }
            }
        }
        for (int i = 0; i < tmpN; i++) {
            snprintf(buf[0], sizeof(buf[0]), "%lld", tmp[i].qty);
            snprintf(buf[1], sizeof(buf[1]), "%.2f%%", totalQty ? (double)tmp[i].qty * 100.0 / totalQty : 0.0);
            const char* cols[3];
            cols[0] = tmp[i].name; cols[1] = buf[0]; cols[2] = buf[1];
            LvInsertUTF8(lv, cols, 3);
        }
        free(skip);
        free(tmp);
    }
    SendMessageW(lv, WM_SETREDRAW, TRUE, 0);
    InvalidateRect(lv, NULL, TRUE);
}

static void LuckyUpdateTabs(void)
{
    for (int i = 0; i < 3; i++) {
        if (g_luckyUi.btnTab[i])
            InvalidateRect(g_luckyUi.btnTab[i], NULL, TRUE);
    }
}

// 彩盒查询窗口绘制
static void LuckyDrawTheme(LPDRAWITEMSTRUCT dis, const wchar_t* label, int active)
{
    HDC hdc = dis->hDC;
    RECT rc = dis->rcItem;
    HDC mem = CreateCompatibleDC(hdc);
    HBITMAP bmp = CreateCompatibleBitmap(hdc, rc.right - rc.left, rc.bottom - rc.top);
    HBITMAP oldBmp = (HBITMAP)SelectObject(mem, bmp);
    RECT r = {0, 0, rc.right - rc.left, rc.bottom - rc.top};
    GradientRect(mem, &r, COL_BG_TOP, COL_BG_BOTTOM);
    FillRoundRect(mem, &r, 8, active ? COL_ACCENT : COL_PANEL);
    FrameRoundRect(mem, &r, 8, active ? COL_ACCENT_DARK : COL_PANEL_BORDER);
    HFONT oldFont = (HFONT)SelectObject(mem, g_fontSmall);
    SetBkMode(mem, TRANSPARENT);
    SetTextColor(mem, active ? RGB(255,255,255) : COL_MUTED);
    DrawTextW(mem, label, -1, &r, DT_CENTER | DT_SINGLELINE | DT_VCENTER);
    SelectObject(mem, oldFont);
    BitBlt(hdc, 0, 0, rc.right - rc.left, rc.bottom - rc.top, mem, 0, 0, SRCCOPY);
    SelectObject(mem, oldBmp);
    DeleteObject(bmp);
    DeleteDC(mem);
}

static void LuckyPaint(HWND hWnd, HDC hdc)
{
    RECT full = {0, 0, LUCK_WIN_WIDTH, LUCK_WIN_HEIGHT};
    GradientRect(hdc, &full, COL_BG_TOP, COL_BG_BOTTOM);

    HFONT old = (HFONT)SelectObject(hdc, g_fontTitle);
    SetBkMode(hdc, TRANSPARENT);
    SetTextColor(hdc, COL_ACCENT_DARK);
    RECT title = {20, 12, 360, 60};
    DrawTextW(hdc, L"彩盒历史记录查询", -1, &title, DT_LEFT | DT_VCENTER | DT_SINGLELINE);
    SelectObject(hdc, (HFONT)g_fontSmall);

    RECT line = {20, 70, LUCK_WIN_WIDTH - 20, 72};
    FillRect(hdc, &line, g_accentBrush);

    // 日期范围标签（透明背景，大号字体直接绘制）
    HFONT oldDate = (HFONT)SelectObject(hdc, g_fontDate);
    SetBkMode(hdc, TRANSPARENT);
    SetTextColor(hdc, COL_MUTED);
    RECT rFrom = {24, 80, 120, 106};
    DrawTextW(hdc, L"日期范围：", -1, &rFrom, DT_LEFT | DT_SINGLELINE | DT_VCENTER);
    RECT rSep = {242, 80, 262, 106};
    DrawTextW(hdc, L"至", -1, &rSep, DT_CENTER | DT_SINGLELINE | DT_VCENTER);
    SelectObject(hdc, oldDate);

    // Tab 区域背景
    RECT tabBar = {20, 120, LUCK_WIN_WIDTH - 20, 152};
    FillRoundRect(hdc, &tabBar, 8, RGB(230, 238, 250));
}

static void LuckyDrawActionBtn(LPDRAWITEMSTRUCT dis, const wchar_t* label, int big)
{
    HDC hdc = dis->hDC;
    RECT rc = dis->rcItem;
    int w = rc.right - rc.left;
    int h = rc.bottom - rc.top;
    BOOL enabled = !(dis->itemState & ODS_DISABLED);
    BtnState *st = (BtnState*)GetWindowLongPtrW(dis->hwndItem, GWLP_USERDATA);
    int hover = st ? st->hover : 0;
    int pressed = st ? st->pressed : 0;

    HDC mem = CreateCompatibleDC(hdc);
    HBITMAP bmp = CreateCompatibleBitmap(hdc, w, h);
    HBITMAP oldBmp = (HBITMAP)SelectObject(mem, bmp);
    RECT r = {0, 0, w, h};
    GradientRect(mem, &r, COL_BG_TOP, COL_BG_BOTTOM);

    COLORREF top, bottom, border, textColor;
    if (!enabled) {
        top = RGB(226, 232, 243); bottom = RGB(213, 221, 236);
        border = RGB(198, 208, 228); textColor = RGB(148, 160, 182);
    } else if (pressed) {
        top = RGB(33, 88, 208); bottom = RGB(45, 108, 232);
        border = RGB(120, 170, 255); textColor = RGB(255, 255, 255);
    } else if (hover) {
        top = RGB(96, 160, 252); bottom = RGB(48, 108, 232);
        border = RGB(140, 185, 255); textColor = RGB(255, 255, 255);
    } else {
        top = COL_BTN_TOP; bottom = COL_BTN_BOTTOM;
        border = RGB(110, 165, 250); textColor = RGB(255, 255, 255);
    }
    GradientRoundRect(mem, &r, 10, top, bottom);
    FrameRoundRect(mem, &r, 10, border);
    HFONT oldFont = (HFONT)SelectObject(mem, big ? g_fontButton : g_fontSmall);
    SetBkMode(mem, TRANSPARENT);
    SetTextColor(mem, textColor);
    RECT textRc = {0, 0, w, h};
    DrawTextW(mem, label, -1, &textRc, DT_CENTER | DT_SINGLELINE | DT_VCENTER);
    SelectObject(mem, oldFont);
    BitBlt(hdc, 0, 0, w, h, mem, 0, 0, SRCCOPY);
    SelectObject(mem, oldBmp);
    DeleteObject(bmp);
    DeleteDC(mem);
}

// tab 按钮切页 + 消息
static LRESULT CALLBACK LuckyWndProc(HWND hWnd, UINT msg, WPARAM wParam, LPARAM lParam)
{
    switch (msg)
    {
        case WM_USER_LUCKY_LOG:
        {
            LuckyLogDirect((char*)lParam);
            free((char*)lParam);
            break;
        }
        case WM_USER_LUCKY_DONE:
        {
            if (g_luckyUi.btnQuery) EnableWindow(g_luckyUi.btnQuery, TRUE);
            LuckyPopulate();
            LuckyUpdateTabs();
            break;
        }
        case WM_USER_QUERY_ENABLE:
        {
            if (g_luckyUi.btnQuery) EnableWindow(g_luckyUi.btnQuery, TRUE);
            LuckyUpdateTabs();
            break;
        }
        case WM_NCHITTEST:
        {
            LRESULT ht = DefWindowProcW(hWnd, msg, wParam, lParam);
            if (ht == HTCLIENT) {
                POINT pt = {GET_X_LPARAM(lParam), GET_Y_LPARAM(lParam)};
                ScreenToClient(hWnd, &pt);
                if (pt.y >= 0 && pt.y < 76 && pt.x < LUCK_WIN_WIDTH - 60)
                    return HTCAPTION;
            }
            return ht;
        }
        case WM_COMMAND:
        {
            WORD id = LOWORD(wParam);
            if (id >= 1301 && id <= 1303) {
                g_luckyTab = id - 1301;
                LuckyPopulate();
                LuckyUpdateTabs();
            }
else if (id == ID_BTN_QUERY) {
                DoLuckyQuery();
                if (g_luckyUi.btnQuery) EnableWindow(g_luckyUi.btnQuery, FALSE);
            } else if (id == ID_BTN_CLOSE) {
                PostMessageW(hWnd, WM_CLOSE, 0, 0);
            }
            break;
        }
        case WM_NOTIFY:
        {
            NMHDR *nm = (NMHDR*)lParam;
            if (nm->idFrom == ID_LIST_LUCKY && nm->code == LVN_ITEMCHANGED) {
                NMLISTVIEW *nmlv = (NMLISTVIEW*)lParam;
                if ((nmlv->uNewState & LVIS_SELECTED) && g_luckyTab == 0 && nmlv->iItem >= 0) {
                    // 明细行号 -> 对应 g_rows 索引
                    int target = -1;
                    int cnt = 0;
                    for (int i = 0; i < g_row_count; i++) {
                        LuckyRow *r = &g_rows[i];
                        if (!LuckyRowInRange(r)) continue;
                        if (cnt == nmlv->iItem) { target = i; break; }
                        cnt++;
                    }
                    if (target >= 0) {
                        LuckyRow *r = &g_rows[target];
                        char detail[4096];
                        int pos = 0;
                        pos += snprintf(detail + pos, sizeof(detail) - pos, "记录ID：%lld\r\n", r->id);
                        pos += snprintf(detail + pos, sizeof(detail) - pos, "时间：%s    彩盒：%s    支付：%s    抽数：%d\r\n",
                            r->created_at, r->level_name[0] ? r->level_name : "-", r->pay_text[0] ? r->pay_text : "-", r->multiple);
                        if (r->medal_cost > 0)
                            pos += snprintf(detail + pos, sizeof(detail) - pos, "消耗：勋章 x%lld    状态：%s\r\n", r->medal_cost, r->status[0] ? r->status : "-");
                        else
                            pos += snprintf(detail + pos, sizeof(detail) - pos, "状态：%s\r\n", r->status[0] ? r->status : "-");
                        pos += snprintf(detail + pos, sizeof(detail) - pos, "获得奖品：\r\n");
                        if (r->award_detail[0])
                            pos += snprintf(detail + pos, sizeof(detail) - pos, "%s", r->award_detail);
                        else
                            pos += snprintf(detail + pos, sizeof(detail) - pos, "无\r\n");
                        HWND ed = GetDlgItem(hWnd, ID_EDIT_LUCKY_LOG);
                        if (ed) {
                            wchar_t *wt = Utf8ToWide(detail);
                            if (wt) { SetWindowTextW(ed, wt); free(wt); }
                        }
                    }
                }
            }
            return DefWindowProcW(hWnd, msg, wParam, lParam);
        }
        case WM_CTLCOLOREDIT:
        {
            HDC hdc = (HDC)wParam;
            SetBkColor(hdc, COL_INPUT_BG);
            SetTextColor(hdc, COL_TEXT);
            return (LRESULT)g_editBgBrush;
        }
        case WM_PAINT:
        {
            PAINTSTRUCT ps;
            HDC hdc = BeginPaint(hWnd, &ps);
            HDC mem = CreateCompatibleDC(hdc);
            HBITMAP bmp = CreateCompatibleBitmap(hdc, LUCK_WIN_WIDTH, LUCK_WIN_HEIGHT);
            HBITMAP oldBmp = (HBITMAP)SelectObject(mem, bmp);
            PatBlt(mem, 0, 0, LUCK_WIN_WIDTH, LUCK_WIN_HEIGHT, BLACKNESS);
            LuckyPaint(hWnd, mem);
            BitBlt(hdc, 0, 0, LUCK_WIN_WIDTH, LUCK_WIN_HEIGHT, mem, 0, 0, SRCCOPY);
            SelectObject(mem, oldBmp);
            DeleteObject(bmp);
            DeleteDC(mem);
            EndPaint(hWnd, &ps);
            break;
        }
        case WM_ERASEBKGND:
            return 1;
        case WM_DRAWITEM:
        {
            LPDRAWITEMSTRUCT dis = (LPDRAWITEMSTRUCT)lParam;
            if (dis->CtlType == ODT_BUTTON) {
                if (dis->CtlID >= 1301 && dis->CtlID <= 1303) {
                    LuckyDrawTheme(dis, LuckyTabNames[dis->CtlID - 1301], g_luckyTab == dis->CtlID - 1301);
                    return TRUE;
                }
if (dis->CtlID == ID_BTN_QUERY) {
        LuckyDrawActionBtn(dis, L"开始查询", 0);
    }
                if (dis->CtlID == ID_BTN_CLOSE) {
                    DrawCloseButton(dis);
                    return TRUE;
                }
            }
            return FALSE;
        }
        case WM_CLOSE:
            DestroyWindow(hWnd);
            break;
        case WM_DESTROY:
        {
            KillTimer(hWnd, TIMER_CLOSE);
            KillTimer(hWnd, ID_UI_ANIM);
            g_hwndLucky = NULL;
            break;
        }
        default:
            return DefWindowProcW(hWnd, msg, wParam, lParam);
    }
    return 0;
}

static void CreateLuckyWindow(HWND hOwner)
{
    if (g_hwndLucky && IsWindow(g_hwndLucky)) {
        SetForegroundWindow(g_hwndLucky);
        return;
    }

    WNDCLASSEXW wc = {0};
    wc.cbSize = sizeof(WNDCLASSEXW);
    wc.lpfnWndProc = LuckyWndProc;
    wc.hInstance = GetModuleHandle(NULL);
    wc.lpszClassName = L"LuckyQueryWin";
    wc.hCursor = LoadCursor(NULL, IDC_ARROW);
    wc.hbrBackground = (HBRUSH)(COLOR_WINDOW + 1);
    RegisterClassExW(&wc);

    g_hwndLucky = CreateWindowExW(0, L"LuckyQueryWin", L"彩盒历史记录查询",
        WS_POPUP | WS_CLIPCHILDREN | WS_VISIBLE,
        CW_USEDEFAULT, CW_USEDEFAULT, LUCK_WIN_WIDTH, LUCK_WIN_HEIGHT, NULL, NULL, wc.hInstance, NULL);

    if (!g_hwndLucky) return;
    CenterWindow(g_hwndLucky);

    RECT rc = {0, 0, LUCK_WIN_WIDTH, LUCK_WIN_HEIGHT};

    // 关闭按钮
    HWND btnClose = CreateWindowExW(0, L"button", L"",
        WS_CHILD|WS_VISIBLE|BS_OWNERDRAW, LUCK_WIN_WIDTH - 48, 10, 34, 34,
        g_hwndLucky, (HMENU)ID_BTN_CLOSE, NULL, NULL);
    g_closeBtnState.oldProc = (WNDPROC)GetWindowLongPtrW(btnClose, GWLP_WNDPROC);
    SetWindowLongPtrW(btnClose, GWLP_WNDPROC, (LONG_PTR)BtnSubclassProc);
    SetWindowLongPtrW(btnClose, GWLP_USERDATA, (LONG_PTR)&g_closeBtnState);

// 日期范围选择（标签在 LuckyPaint 中绘制，大号字体）
    g_edDateFrom = CreateWindowExW(0, L"edit", L"",
        WS_CHILD|WS_VISIBLE|WS_TABSTOP|ES_AUTOHSCROLL, 122, 78, 116, 30,
        g_hwndLucky, (HMENU)ID_EDIT_DATE_FROM, NULL, NULL);
    g_edDateTo = CreateWindowExW(0, L"edit", L"",
        WS_CHILD|WS_VISIBLE|WS_TABSTOP|ES_AUTOHSCROLL, 264, 78, 116, 30,
        g_hwndLucky, (HMENU)ID_EDIT_DATE_TO, NULL, NULL);
    SendMessageW(g_edDateFrom, WM_SETFONT, (WPARAM)g_fontDate, TRUE);
    SendMessageW(g_edDateTo, WM_SETFONT, (WPARAM)g_fontDate, TRUE);
    LuckySetDefaultDates();

// 查询按钮（移到日期范围行右侧）
    g_luckyUi.btnQuery = CreateWindowExW(0, L"button", L"开始查询",
        WS_CHILD|WS_VISIBLE|BS_OWNERDRAW, LUCK_WIN_WIDTH - 130, 78, 110, 30, g_hwndLucky, (HMENU)ID_BTN_QUERY, NULL, NULL);
    SendMessageW(g_luckyUi.btnQuery, WM_SETFONT, (WPARAM)g_fontSmall, TRUE);
    g_queryBtnState.oldProc = (WNDPROC)GetWindowLongPtrW(g_luckyUi.btnQuery, GWLP_WNDPROC);
    SetWindowLongPtrW(g_luckyUi.btnQuery, GWLP_WNDPROC, (LONG_PTR)BtnSubclassProc);
    SetWindowLongPtrW(g_luckyUi.btnQuery, GWLP_USERDATA, (LONG_PTR)&g_queryBtnState);

    // 3 个 Tab 按钮（查询按钮已移走，占满整行）
    int tabW = (LUCK_WIN_WIDTH - 48) / 3;
    static BtnState tabStates[3];
    for (int i = 0; i < 3; i++) {
        g_luckyUi.btnTab[i] = CreateWindowExW(0, L"button", L"",
            WS_CHILD|WS_VISIBLE|BS_OWNERDRAW, 24 + i * tabW, 122, tabW - 8, 28,
            g_hwndLucky, (HMENU)(UINT_PTR)(1301 + i), NULL, NULL);
        SendMessageW(g_luckyUi.btnTab[i], WM_SETFONT, (WPARAM)g_fontSmall, TRUE);
        tabStates[i].oldProc = (WNDPROC)GetWindowLongPtrW(g_luckyUi.btnTab[i], GWLP_WNDPROC);
        SetWindowLongPtrW(g_luckyUi.btnTab[i], GWLP_WNDPROC, (LONG_PTR)BtnSubclassProc);
        SetWindowLongPtrW(g_luckyUi.btnTab[i], GWLP_USERDATA, (LONG_PTR)&tabStates[i]);
    }

    // ListView
    g_luckyUi.list = CreateWindowExW(WS_EX_CLIENTEDGE, WC_LISTVIEWW, L"",
        WS_CHILD|WS_VISIBLE|LVS_REPORT|LVS_SINGLESEL|LVS_SHOWSELALWAYS,
        20, 158, LUCK_WIN_WIDTH - 40, 300, g_hwndLucky, (HMENU)ID_LIST_LUCKY, NULL, NULL);
    SendMessageW(g_luckyUi.list, WM_SETFONT, (WPARAM)g_fontSmall, TRUE);
    ListView_SetExtendedListViewStyle(g_luckyUi.list, LVS_EX_FULLROWSELECT | LVS_EX_GRIDLINES | LVS_EX_DOUBLEBUFFER);

    // 详情/日志框
    g_luckyUi.edLog = CreateWindowExW(0, L"edit", L"",
        WS_CHILD|WS_VISIBLE|ES_MULTILINE|ES_READONLY|WS_VSCROLL|ES_AUTOVSCROLL,
        20, 464, LUCK_WIN_WIDTH - 40, 148, g_hwndLucky, (HMENU)ID_EDIT_LUCKY_LOG, NULL, NULL);
    SendMessageW(g_luckyUi.edLog, WM_SETFONT, (WPARAM)g_fontSmall, TRUE);

// 登录成功后自动开启查询

    if (g_luckyLoginDone) {
        LuckyLog("[信息]数据已加载，可直接查看统计\r\n");
    }
    if (g_row_count > 0) {
        LuckyPopulate();
        LuckyUpdateTabs();
    }
}

LRESULT CALLBACK WndProc(HWND hWnd, UINT msg, WPARAM wParam, LPARAM lParam)
{
    switch(msg)
    {
        case WM_USER_LOG:
        {
            HandleLogMessage((char*)lParam);
            break;
        }
        case WM_USER_LOGIN_DONE:
        {
            wchar_t bufQQ[128], bufPwd[128];
            GetWindowTextW(g_edQQ, bufQQ, 120);
            GetWindowTextW(g_edPwd, bufPwd, 120);
            char *qq = WideToUtf8(bufQQ);
            char *pwd = WideToUtf8(bufPwd);
            if(qq && pwd) {
                WriteRegistryString(REG_KEY, REG_QQ, qq);
                WriteProtectedString(REG_KEY, REG_PASSWORD, pwd);
            }
            SAFE_FREE(qq); SAFE_FREE(pwd);
            break;
        }
        case WM_USER_ENABLE_BTN:
        {
            EnableWindow(g_btnLoginRun, (BOOL)wParam);
            StopUiAnim();
            InvalidateRect(g_btnLoginRun, NULL, TRUE);
            break;
        }
        case WM_CREATE:
        {
            LOGFONTW lf;

            ZeroMemory(&lf,sizeof(lf));
            lf.lfHeight = -30;
            lf.lfWeight = FW_SEMIBOLD;
            lf.lfCharSet = DEFAULT_CHARSET;
            wcscpy_s(lf.lfFaceName, _countof(lf.lfFaceName), L"Segoe UI");
            g_fontTitle = CreateFontIndirectW(&lf);

            ZeroMemory(&lf,sizeof(lf));
            lf.lfHeight = -16;
            lf.lfWeight = FW_NORMAL;
            lf.lfCharSet = DEFAULT_CHARSET;
            wcscpy_s(lf.lfFaceName, _countof(lf.lfFaceName), L"Segoe UI");
            g_fontNormal = CreateFontIndirectW(&lf);

ZeroMemory(&lf,sizeof(lf));
            lf.lfHeight = -12;
            lf.lfWeight = FW_NORMAL;
            lf.lfCharSet = DEFAULT_CHARSET;
            wcscpy_s(lf.lfFaceName, _countof(lf.lfFaceName), L"Segoe UI");
            g_fontSmall = CreateFontIndirectW(&lf);

            ZeroMemory(&lf,sizeof(lf));
            lf.lfHeight = -12;
            lf.lfWeight = FW_NORMAL;
            lf.lfCharSet = DEFAULT_CHARSET;
            wcscpy_s(lf.lfFaceName, _countof(lf.lfFaceName), L"Segoe UI");
            g_fontLog = CreateFontIndirectW(&lf);

            ZeroMemory(&lf,sizeof(lf));
            lf.lfHeight = -16;
            lf.lfWeight = FW_SEMIBOLD;
            lf.lfCharSet = DEFAULT_CHARSET;
            wcscpy_s(lf.lfFaceName, _countof(lf.lfFaceName), L"Segoe UI");
            g_fontButton = CreateFontIndirectW(&lf);

            ZeroMemory(&lf,sizeof(lf));
            lf.lfHeight = -18;
            lf.lfWeight = FW_NORMAL;
            lf.lfCharSet = DEFAULT_CHARSET;
            wcscpy_s(lf.lfFaceName, _countof(lf.lfFaceName), L"Segoe UI");
            g_fontDate = CreateFontIndirectW(&lf);

            g_editBgBrush = CreateSolidBrush(COL_INPUT_BG);
            g_logBgBrush = CreateSolidBrush(COL_INPUT_BG);
            g_accentBrush = CreateSolidBrush(COL_ACCENT);
            g_accentDimBrush = CreateSolidBrush(RGB(160, 190, 246));
            g_btnShineBrush = CreateSolidBrush(RGB(216, 232, 252));
            g_panelBrush = CreateSolidBrush(COL_PANEL);

            g_edQQ = CreateWindowExW(0, L"edit", L"",
                WS_CHILD|WS_VISIBLE|WS_TABSTOP|ES_AUTOHSCROLL, 28, 140, 344, 32,
                hWnd, (HMENU)ID_EDIT_QQ, NULL, NULL);
            g_edPwd = CreateWindowExW(0, L"edit", L"",
                WS_CHILD|WS_VISIBLE|WS_TABSTOP|ES_PASSWORD|ES_AUTOHSCROLL, 28, 214, 344, 32,
                hWnd, (HMENU)ID_EDIT_PWD, NULL, NULL);

            g_btnLoginRun = CreateWindowExW(0, L"button", L"",
                WS_CHILD|WS_VISIBLE|WS_TABSTOP|BS_OWNERDRAW, 24, 266, 352, 46,
                hWnd, (HMENU)ID_BTN_LOGINRUN, NULL, NULL);
            g_loginBtnState.oldProc = (WNDPROC)GetWindowLongPtrW(g_btnLoginRun, GWLP_WNDPROC);
            SetWindowLongPtrW(g_btnLoginRun, GWLP_WNDPROC, (LONG_PTR)BtnSubclassProc);
            SetWindowLongPtrW(g_btnLoginRun, GWLP_USERDATA, (LONG_PTR)&g_loginBtnState);

            g_btnLucky = CreateWindowExW(0, L"button", L"",
                WS_CHILD|WS_VISIBLE|WS_TABSTOP|BS_OWNERDRAW, 24, 320, 352, 42,
                hWnd, (HMENU)ID_BTN_LUCKY, NULL, NULL);
            g_luckyBtnState.oldProc = (WNDPROC)GetWindowLongPtrW(g_btnLucky, GWLP_WNDPROC);
            SetWindowLongPtrW(g_btnLucky, GWLP_WNDPROC, (LONG_PTR)BtnSubclassProc);
            SetWindowLongPtrW(g_btnLucky, GWLP_USERDATA, (LONG_PTR)&g_luckyBtnState);

            g_btnClose = CreateWindowExW(0, L"button", L"",
                WS_CHILD|WS_VISIBLE|BS_OWNERDRAW, WIN_WIDTH - 52, 6, 40, 40,
                hWnd, (HMENU)ID_BTN_CLOSE, NULL, NULL);
            g_closeBtnState.oldProc = (WNDPROC)GetWindowLongPtrW(g_btnClose, GWLP_WNDPROC);
            SetWindowLongPtrW(g_btnClose, GWLP_WNDPROC, (LONG_PTR)BtnSubclassProc);
            SetWindowLongPtrW(g_btnClose, GWLP_USERDATA, (LONG_PTR)&g_closeBtnState);

            g_edLog = CreateWindowExW(0, L"edit", L"",
                WS_CHILD|WS_VISIBLE|ES_MULTILINE|ES_READONLY|WS_VSCROLL|ES_AUTOVSCROLL, 28, 412, 344, 144,
                hWnd, (HMENU)ID_EDIT_LOG, NULL, NULL);

            SendMessageW(g_edQQ, WM_SETFONT, (WPARAM)g_fontNormal, TRUE);
            SendMessageW(g_edPwd, WM_SETFONT, (WPARAM)g_fontNormal, TRUE);
            SendMessageW(g_edLog, WM_SETFONT, (WPARAM)g_fontLog, TRUE);

            GetModuleFileNameA(NULL, g_gamePath, MAX_PATH);
            char* slash = strrchr(g_gamePath, '\\');
            if(slash){*slash=0; strcpy(g_exeDir, g_gamePath);}
            strcat(g_gamePath, "\\FinalCombat.exe");
            
            // 启动时检测游戏文件并显示提示
            CheckGameExists();
            if (g_gameExists) {
                GuiLogDirect("[信息]登录器就绪，请输入账号密码开始游戏\r\n");
            } else {
                GuiLogDirect("[错误]未找到 FinalCombat.exe，请将登录器放在游戏目录下\r\n");
            }

            char qq[64]={0}, pwd[64]={0};
            int hasQ = ReadRegistryString(REG_KEY, REG_QQ, qq, sizeof(qq));
            int hasP = ReadProtectedString(REG_KEY, REG_PASSWORD, pwd, sizeof(pwd));
            if (!hasP && ReadRegistryString(REG_KEY, REG_PASSWORD, pwd, sizeof(pwd))) {
                if (WriteProtectedString(REG_KEY, REG_PASSWORD, pwd)) {
                    HKEY hKey;
                    if (RegOpenKeyA(HKEY_CURRENT_USER, REG_KEY, &hKey) == ERROR_SUCCESS) {
                        RegDeleteValueA(hKey, REG_PASSWORD);
                        RegCloseKey(hKey);
                    }
                    hasP = 1;
                } else {
                    pwd[0] = 0;
                }
            }
            if(hasQ && strlen(qq) > 0)
            {
                wchar_t *wqq = Utf8ToWide(qq);
                SetWindowTextW(g_edQQ, wqq); 
                free(wqq);
            }
            if(hasP && strlen(pwd) > 0)
            {
                wchar_t *wpwd = Utf8ToWide(pwd);
                SetWindowTextW(g_edPwd, wpwd); 
                free(wpwd);
            }
            break;
        }
        case WM_NCCREATE:
        {
            SetWindowPos(hWnd, NULL, 0, 0, WIN_WIDTH, WIN_HEIGHT,
                SWP_NOMOVE | SWP_NOZORDER);
            return DefWindowProcW(hWnd, msg, wParam, lParam);
        }
        case WM_NCHITTEST:
        {
            LRESULT ht = DefWindowProcW(hWnd, msg, wParam, lParam);
            if (ht == HTCLIENT) {
                POINT pt = {GET_X_LPARAM(lParam), GET_Y_LPARAM(lParam)};
                ScreenToClient(hWnd, &pt);
                if (pt.y >= 0 && pt.y < 100 && pt.x < WIN_WIDTH - 60)
                    return HTCAPTION;
            }
            return ht;
        }
        case WM_TIMER:
        {
            if(wParam == TIMER_CLOSE)
            {
                KillTimer(hWnd, TIMER_CLOSE);
                PostMessage(hWnd, WM_CLOSE, 0, 0);
            }
            else if(wParam == ID_UI_ANIM)
            {
                g_uiAnimTick++;
                InvalidateRect(g_btnLoginRun, NULL, FALSE);
            }
            break;
        }
        case WM_CTLCOLOREDIT:
        {
            HDC hdc = (HDC)wParam;
            SetBkColor(hdc, COL_INPUT_BG);
            SetTextColor(hdc, (HWND)lParam == g_edLog ? RGB(74, 92, 122) : COL_TEXT);
            return (LRESULT)g_editBgBrush;
        }
        case WM_CTLCOLORSTATIC:
        {
            HDC hdc = (HDC)wParam;
            SetBkColor(hdc, COL_INPUT_BG);
            SetTextColor(hdc, COL_TEXT);
            return (LRESULT)g_logBgBrush;
        }
        case WM_COMMAND:
        {
            WORD code = HIWORD(wParam);
            if (code == EN_SETFOCUS || code == EN_KILLFOCUS) {
                HWND f = GetFocus();
                g_focusField = (f == g_edQQ) ? 1 : (f == g_edPwd) ? 2 : 0;
                InvalidateRect(hWnd, NULL, FALSE);
            }
            if(LOWORD(wParam) == ID_BTN_LOGINRUN)
                DoLoginAndLaunch();
            else if(LOWORD(wParam) == ID_BTN_LUCKY)
                CreateLuckyWindow(hWnd);
            else if(LOWORD(wParam) == ID_BTN_CLOSE)
                PostMessageW(hWnd, WM_CLOSE, 0, 0);
            break;
        }
        case WM_PAINT:
        {
            PAINTSTRUCT ps;
            HDC hdc = BeginPaint(hWnd, &ps);
            HDC mem = CreateCompatibleDC(hdc);
            HBITMAP bmp = CreateCompatibleBitmap(hdc, WIN_WIDTH, WIN_HEIGHT);
            HBITMAP oldBmp = (HBITMAP)SelectObject(mem, bmp);
            PatBlt(mem, 0, 0, WIN_WIDTH, WIN_HEIGHT, BLACKNESS);
            DrawWindowBackground(mem);
            BitBlt(hdc, 0, 0, WIN_WIDTH, WIN_HEIGHT, mem, 0, 0, SRCCOPY);
            SelectObject(mem, oldBmp);
            DeleteObject(bmp);
            DeleteDC(mem);
            EndPaint(hWnd, &ps);
            break;
        }
        case WM_ERASEBKGND:
            return 1;
        case WM_DRAWITEM:
        {
            LPDRAWITEMSTRUCT dis = (LPDRAWITEMSTRUCT)lParam;
            if (dis->CtlType == ODT_BUTTON) {
                if (dis->CtlID == ID_BTN_LOGINRUN) DrawLoginButton(dis);
                else if (dis->CtlID == ID_BTN_LUCKY) LuckyDrawActionBtn(dis, L"彩盒查询", 1);
                else if (dis->CtlID == ID_BTN_CLOSE) DrawCloseButton(dis);
                return TRUE;
            }
            return FALSE;
        }
        case WM_CLOSE:
        {
            g_closing = 1;
            DestroyWindow(hWnd);
            break;
        }
        case WM_DESTROY:
        {
            KillTimer(hWnd, TIMER_CLOSE);
            StopUiAnim();
            if (g_loginThread) {
                WaitForSingleObject(g_loginThread, INFINITE);
                CloseHandle(g_loginThread);
                g_loginThread = NULL;
            }
            SAFE_FREE(g_token);
            SAFE_FREE(g_gameIP);
            SAFE_FREE(g_gamePort);
            DeleteObject(g_fontNormal);
            DeleteObject(g_fontSmall);
            DeleteObject(g_fontTitle);
            DeleteObject(g_fontLog);
            DeleteObject(g_fontButton);
            DeleteObject(g_fontDate);
            DeleteObject(g_editBgBrush);
            DeleteObject(g_logBgBrush);
            DeleteObject(g_accentBrush);
            DeleteObject(g_accentDimBrush);
            DeleteObject(g_btnShineBrush);
            DeleteObject(g_panelBrush);
            PostQuitMessage(0);
            break;
        }
        default:
            return DefWindowProcW(hWnd, msg, wParam, lParam);
    }
    return 0;
}

int WINAPI WinMain(HINSTANCE hInst, HINSTANCE hPrev, LPSTR cmd, int show)
{
    INITCOMMONCONTROLSEX icc;
    icc.dwSize = sizeof(icc);
    icc.dwICC = ICC_LISTVIEW_CLASSES;
    InitCommonControlsEx(&icc);

    WNDCLASSEXW wc = {0};
    wc.cbSize        = sizeof(WNDCLASSEXW);
    wc.lpfnWndProc   = WndProc;
    wc.hInstance     = hInst;
    wc.lpszClassName = L"GameLauncherWin";
    wc.lpszMenuName  = NULL;
    wc.hCursor       = LoadCursor(NULL, IDC_ARROW);
    wc.hbrBackground = (HBRUSH)(COLOR_WINDOW + 1);
    RegisterClassExW(&wc);

    WNDCLASSEXW wc2 = {0};
    wc2.cbSize        = sizeof(WNDCLASSEXW);
    wc2.lpfnWndProc   = LuckyWndProc;
    wc2.hInstance     = hInst;
    wc2.lpszClassName = L"LuckyQueryWin";
    wc2.lpszMenuName  = NULL;
    wc2.hCursor       = LoadCursor(NULL, IDC_ARROW);
    wc2.hbrBackground = (HBRUSH)(COLOR_WINDOW + 1);
    RegisterClassExW(&wc2);

    g_hwnd = CreateWindowExW(0, L"GameLauncherWin", L"小撤退登录器(非官方)",
        WS_POPUP | WS_CLIPCHILDREN,
        CW_USEDEFAULT, CW_USEDEFAULT, WIN_WIDTH, WIN_HEIGHT, NULL, NULL, hInst, NULL);

    HRGN roundRgn = CreateRoundRectRgn(0, 0, WIN_WIDTH + 1, WIN_HEIGHT + 1, 16, 16);
    if (roundRgn)
        SetWindowRgn(g_hwnd, roundRgn, TRUE);
    
    // 窗口居中
    CenterWindow(g_hwnd);
    
    ShowWindow(g_hwnd, show); 
    UpdateWindow(g_hwnd);

    MSG msg;
    while(GetMessage(&msg, NULL, 0, 0))
    {
        TranslateMessage(&msg);
        DispatchMessage(&msg);
    }
    if (g_queryThread) {
        WaitForSingleObject(g_queryThread, INFINITE);
        CloseHandle(g_queryThread);
    }
    SAFE_FREE(g_rows);
    SAFE_FREE(g_awards);
    SAFE_FREE(g_award_entries);
    SAFE_FREE(g_lucky_token);
    return (int)msg.wParam;
}

