/***************************************************
**
**	IIII DDDD   KKKK  KK 
**	 II  DD DD   KK  KK
**	 II  DD  DD  KK KK
**	 II  DD  DD  KKK
**	 II  DD  DD  KK KK
**	 II  DD DD   KK  KK
**	IIII DDDD   KKKK   KK
**
**
**   SSS         FF
**	S           F   T 
**	 SSS   OO   FF  TTT W   W  AAA  R RR   EEE
**      S O  O  F   T   W W W  AAAA RR  R EEEEE
**	    S O  O  F   T   W W W A   A R     E
**	 SSS   OO  FFF   TT  W W   AAAA R      EEE  
**
**									 L           D
**									 L    T      D
**									 L   TTT   DDD   
**									 L    T   D  D 
**									 LLLL  TT  DDD						
**
****************************************************
**
**	Filename	: DPL/CXLApplication.cpp
**	Author		: I.Ferguson
**	Version		: v1.0
**	Date		: 12-05-99
**
****************************************************
**
**	Classes:
**
**	CDPLRowCtrl implementation
**
****************************************************/

/** Copyright 1998 IDK Software Ltd **/

#include "StdAfx.h"
#include "launch.h"


STARTUPINFO CExecuteProcess::startup_info = { sizeof (STARTUPINFO), NULL, NULL, NULL, 0,
                                 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, 0, 0 };

static int AddSlashs(TCHAR *source, TCHAR *dest, int destSize)
{

	int c = _tcslen(source);
	int j = 0;

	TCHAR l_Slash = __TEXT('\\');

	for (int i = 0; i < c; i++ ) {
		if (source[i] == l_Slash ) {
			dest[j++] = l_Slash;     
				if (j >= destSize - 1 ) {
					dest[j] = '\0';
					return -1; // out if space;
				}
		}
		dest[j++] = source[i];
		if (j >= destSize - 1) {
			dest[j] = '\0';
			return -1; // out if space;
		}
	}
	dest[j] = '\0';
	return 0;
}

int	CExecuteProcess::DoExec(TCHAR *aCommand,TCHAR *aCommandLine)
{
	TCHAR lineStr[260];
	TCHAR line[256];
	TCHAR cmdLine[256];
	AddSlashs(aCommand,line,256);
	//AddSlashs(aCommand,cmdLine,256);
	_stprintf(lineStr, __TEXT("%s %s"), line, aCommandLine );

	

//	AddSlashs(lineStr,line,256);

	TCHAR l_wLine[260];
	int len = 260;
//	unsigned short *ret = AtlA2WHelper(l_wLine,line, len);

	int ret = CreateProcess(NULL, lineStr, NULL, NULL, FALSE,DETACHED_PROCESS /*| DEBUG_PROCESS */,
			NULL, NULL/*m_lpCurrentDirectory.GetBuffer(10)*/, &startup_info, &proc_info);
	if (!ret)
	{
		int error = GetLastError();
	}
	return ret;

}

/*
void CExecuteRegisteredProcess::findPath(LPCTSTR aName, LPCTSTR aArgStr)
{
	CString subKey;
	subKey.Format("Software\\IDK Software Ltd\\%s\\Settings",aName);
	CString entry = "Install Directory";
	CString path = "";

	HKEY hKey;
	long ok = RegOpenKeyEx(HKEY_CURRENT_USER,subKey,0,KEY_READ, &hKey);
	if (ok != ERROR_SUCCESS) {
		return;
	}
	DWORD dwType, dwCount;
	ok = RegQueryValueEx(hKey, entry, NULL, &dwType, NULL, &dwCount);
	if (ok == ERROR_SUCCESS && dwType == REG_SZ) {
		ok = RegQueryValueEx(hKey, entry, NULL, &dwType,
							(LPBYTE) path.GetBuffer(dwCount/sizeof(TCHAR)), &dwCount);
		path.ReleaseBuffer();
	} else {
		return;
	}
	RegCloseKey(hKey);
	SetCommand(path,aArgStr);
}


CExecuteRegisteredProcess::
CExecuteRegisteredProcess(LPCTSTR aName, LPCTSTR aArgStr)
	: CRegistery(aName)
{
	findPath(aName, aArgStr);

}
*/