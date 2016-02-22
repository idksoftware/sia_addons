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



#if !defined(AFX_LAUNCH_H__70FB256B_4271_11D1_90D5_080009123456__INCLUDED_)
#define AFX_LAUNCH_H__70FB256B_4271_11D1_90D5_080009123456__INCLUDED_

//#include "regman.h"

#if _MSC_VER >= 1000
#pragma once
#endif // _MSC_VER >= 1000

class CExecuteProcess {
	//TCHAR *m_Command;
	//TCHAR *m_CommandLine;
	TCHAR *m_lpCurrentDirectory;
	PROCESS_INFORMATION proc_info;
	static STARTUPINFO startup_info;
public:
	CExecuteProcess() {};
	/*
	CExecuteProcess(TCHAR *aCommand,TCHAR *aCommadLine)
		{
			SetCommand(aCommand,aCommadLine);
		};

	void SetCommand(TCHAR *aCommand,TCHAR *aCommadLine)
		{
			//int l_cmdlen = (_tcslen(aCommand) * sizeof(TCHAR)) + sizeof(TCHAR);
			m_Command = new TCHAR(260*2);
			_tcscpy(m_Command,aCommand);

			m_CommandLine = new TCHAR(260 *2);//(_tcslen(aCommadLine) * sizeof(TCHAR)) + sizeof(TCHAR));
			_tcscpy(m_CommandLine,aCommadLine);
	
		};
	void SetCurrentDirectory(TCHAR *aDir)
	{	
		m_lpCurrentDirectory = new TCHAR((_tcslen(aDir) * sizeof(TCHAR)) + sizeof(TCHAR));
		_tcscpy(m_lpCurrentDirectory,aDir);
	};	
	*/
	int DoExec(TCHAR *aCommand,TCHAR *aCommadLine);

};

/*
class CExecuteRegisteredProcess : public CExecuteProcess, public CRegistery {
	void findPath(LPCTSTR aSubKey, LPCTSTR aArgStr);
public:
	CExecuteRegisteredProcess(LPCTSTR aName, LPCTSTR aArgStr);
};
*/


#endif // !defined(AFX_LAUNCH_H__70FB256B_4271_11D1_90D5_080009123456__INCLUDED_)

