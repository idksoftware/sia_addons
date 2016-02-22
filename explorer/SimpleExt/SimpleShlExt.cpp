// SimpleShlExt.cpp : Implementation of CSimpleShlExt

#include "stdafx.h"
#include <time.h>
#include "launch.h"
#include "resource.h"
#include "SimpleExt.h"
#include "SimpleShlExt.h"

#define APPNAME "notepad.exe"
#define SYSTEM_OPTIONS "Software\\IDK Software\\SIA 1.0"




/////////////////////////////////////////////////////////////////////////////
// CSimpleShlExt

STDMETHODIMP CSimpleShlExt::Initialize (
    LPCITEMIDLIST pidlFolder, LPDATAOBJECT pDataObj, HKEY hProgID )
{
	FORMATETC fmt = { CF_HDROP, NULL, DVASPECT_CONTENT, -1, TYMED_HGLOBAL };
	STGMEDIUM stg = { TYMED_HGLOBAL };
	HDROP     hDrop;

    // Look for CF_HDROP data in the data object.
    if ( FAILED( pDataObj->GetData ( &fmt, &stg ) ))
        {
        // Nope! Return an "invalid argument" error back to Explorer.
        return E_INVALIDARG;
        }

    // Get a pointer to the actual data.
    hDrop = (HDROP) GlobalLock ( stg.hGlobal );

    // Make sure it worked.
    if ( NULL == hDrop )
        return E_INVALIDARG;

    // Sanity check - make sure there is at least one filename.
	m_cbFiles = DragQueryFile ( hDrop, 0xFFFFFFFF, NULL, 0 );
	HRESULT hr = S_OK;

    if ( 0 == m_cbFiles )
        {
        GlobalUnlock ( stg.hGlobal );
        ReleaseStgMedium ( &stg );
        return E_INVALIDARG;
        }

    // Get the name of the first file and store it in our member variable m_szFile.
    if ( 0 == DragQueryFile ( hDrop, 0, m_szFile, MAX_PATH ) )
        hr = E_INVALIDARG;


	for (int i = 0;i < m_cbFiles;i++) { 
        DragQueryFile(reinterpret_cast<HDROP>(stg.hGlobal), i, m_szFile, MAX_PATH);

		InsertFile(m_szFile);

	}
    GlobalUnlock ( stg.hGlobal );
    ReleaseStgMedium ( &stg );

    return hr;
}

void ShortFilename(TCHAR *pPath, TCHAR *fname)
{
	
	TCHAR drive[_MAX_DRIVE];
	TCHAR dir[_MAX_DIR];
	TCHAR ext[_MAX_EXT];

	_tsplitpath(pPath, drive, dir, fname, ext );
	size_t len = _tcslen(fname);
	if (len)
	{
		// File selected
		lstrcat(fname, ext);
	}
	else
	{
		// Directory selected
		_tcscpy(fname,pPath);
	}
   
}

void CSimpleShlExt::InsertFile(TCHAR *szFile)
{
	int len = _tcslen(szFile);
	int s = sizeof(TCHAR);
	TCHAR *l_szTmp = new TCHAR[(len + sizeof(TCHAR))];
	_tcscpy(l_szTmp, szFile);
	m_FileList.Add((TCHAR *)l_szTmp);
}

void CSimpleShlExt::addCommandID(CommandID cmd) {
	m_CmdList[m_nMenus] = cmd;
	m_nMenus++;
}

STDMETHODIMP CSimpleShlExt::QueryContextMenu (
    HMENU hmenu, UINT indexMenu, UINT uidFirstCmd,
    UINT uidLastCmd, UINT uFlags )
{
	TCHAR   tcDrive[_MAX_DRIVE] = { 0 };
	TCHAR   tcDir[_MAX_DIR]     = { 0 };
	TCHAR   tcFname[_MAX_FNAME] = { 0 };
	TCHAR   tcExt[_MAX_EXT]     = { 0 };
	TCHAR   tcPath[_MAX_DIR + _MAX_DRIVE] = { 0 };
	m_isWorkspace = false;
	m_isDrive = false;
    // If the flags include CMF_DEFAULTONLY then we shouldn't do anything.
    if ( uFlags & CMF_DEFAULTONLY )
        return MAKE_HRESULT ( SEVERITY_SUCCESS, FACILITY_NULL, 0 );
	
	// First, create and populate a submenu.
    HMENU hSubmenu = CreatePopupMenu();
    UINT uID = uidFirstCmd;
	if (m_cbFiles > 0) { // find where in the file system
		DWORD dwReturnCode = _tsplitpath_s( m_FileList[0], tcDrive, _MAX_DRIVE, tcDir, _MAX_DIR, tcFname, _MAX_FNAME, tcExt, _MAX_EXT );
		if (strcmp(tcExt, ".sia") == 0 && strlen(tcFname) == 0) {
			// selected the ".sia"
			return MAKE_HRESULT ( SEVERITY_SUCCESS, FACILITY_NULL, 0 );
		}
		if (m_cbFiles == 1) {
			DWORD dwAttrib = GetFileAttributes(m_FileList[0]);
			m_isOneDir = (dwAttrib & FILE_ATTRIBUTE_DIRECTORY);
			
			if (strcmp(tcDir, "\\") == 0 && strlen(tcFname) == 0) {
				m_isDrive = true;
			}
			if (m_isOneDir) {
				// check if the root workspace
				sprintf(tcPath, "%s\\.sia", m_FileList[0]);
				DWORD dwAttribSIA = GetFileAttributes(tcPath);
				if (dwAttribSIA) {
					m_isWorkspace = (dwAttribSIA != INVALID_FILE_ATTRIBUTES && (dwAttribSIA & FILE_ATTRIBUTE_DIRECTORY));
				}
			} else {
				// one file which may be in the workspace
				sprintf(tcPath, "%s%s\\.sia", tcDrive, tcDir);
				DWORD dwAttribSIA = GetFileAttributes(tcPath);
				if (dwAttribSIA) {
					m_isWorkspace = (dwAttribSIA != INVALID_FILE_ATTRIBUTES && (dwAttribSIA & FILE_ATTRIBUTE_DIRECTORY));
				}
			}

		} else {
			TCHAR *l_Filename = m_FileList[0];
			DWORD dwReturnCode = _tsplitpath_s( l_Filename, tcDrive, _MAX_DRIVE, tcDir, _MAX_DIR, tcFname, _MAX_FNAME, tcExt, _MAX_EXT );
			if( dwReturnCode != 0 ) {
				return MAKE_HRESULT ( SEVERITY_SUCCESS, FACILITY_NULL, 0 );
			}
			
			sprintf(tcPath, "%s%s.sia", tcDrive, tcDir);
			DWORD dwAttribSIA = GetFileAttributes(tcPath);
			if (dwAttribSIA) {
				m_isWorkspace = (dwAttribSIA != INVALID_FILE_ATTRIBUTES && (dwAttribSIA & FILE_ATTRIBUTE_DIRECTORY));
			}
		}

		
	}
	m_nMenus = 0;
	if (m_cbFiles == 0) {
		InsertMenu ( hSubmenu, 3, MF_BYPOSITION, uID++, _T("&About") );
		addCommandID(About);
	} else if (m_cbFiles == 1) {
		if (m_isOneDir) {
			if (m_isWorkspace) {
				InsertMenu ( hSubmenu, 0, MF_BYPOSITION, uID++, _T("&Scan folder") );
				addCommandID(ScanDrive);
				InsertMenu ( hSubmenu, 3, MF_BYPOSITION, uID++, _T("&About") );
				addCommandID(About);
			} else if (m_isDrive) { // is a Drive
				InsertMenu ( hSubmenu, 0, MF_BYPOSITION, uID++, _T("&Import drive") );
				addCommandID(ScanDrive);
				InsertMenu ( hSubmenu, 3, MF_BYPOSITION, uID++, _T("&About") );
				addCommandID(About);
			} else { // single directory out side workspace 
				InsertMenu ( hSubmenu, 0, MF_BYPOSITION, uID++, _T("&Import...") );
				addCommandID(Import);
				InsertMenu ( hSubmenu, 1, MF_BYPOSITION, uID++, _T("&Export to here") );
				addCommandID(Export);
				InsertMenu ( hSubmenu, 3, MF_BYPOSITION, uID++, _T("&About") );
				addCommandID(About);
			}
		} else { // single file
			
			if (m_isWorkspace) { // inside workspace
				// later InsertMenu ( hSubmenu, 1, MF_BYPOSITION, uID++, _T("&Export from...") );
				//addCommandID(Export);
				InsertMenu ( hSubmenu, 4, MF_BYPOSITION, uID++, _T("&Check-in") );
				addCommandID(Checkin);
				InsertMenu ( hSubmenu, 5, MF_BYPOSITION, uID++, _T("&Check-out") );
				addCommandID(Checkout);
				InsertMenu ( hSubmenu, 2, MF_BYPOSITION, uID++, _T("&Properties") );
				addCommandID(Properties);
			} else {
				InsertMenu ( hSubmenu, 0, MF_BYPOSITION, uID++, _T("&Import...") );
				addCommandID(Import);
			}
			InsertMenu ( hSubmenu, 3, MF_BYPOSITION, uID++, _T("&About") );
			addCommandID(About);
		}
	} else { // more than one one files or directories
 		
		
		
		if (m_isWorkspace) { // inside workspace.
			// later InsertMenu ( hSubmenu, 1, MF_BYPOSITION, uID++, _T("&Export from...") );
			//addCommandID(Export);
			InsertMenu ( hSubmenu, 4, MF_BYPOSITION, uID++, _T("&Check-in") );
			addCommandID(Checkin);
			InsertMenu ( hSubmenu, 5, MF_BYPOSITION, uID++, _T("&Check-out") );
			addCommandID(Checkout);
			InsertMenu ( hSubmenu, 2, MF_BYPOSITION, uID++, _T("&Properties") );
			addCommandID(Properties);
		} else {
			InsertMenu ( hSubmenu, 0, MF_BYPOSITION, uID++, _T("&Import") );
			addCommandID(Import);
		}
		InsertMenu ( hSubmenu, 3, MF_BYPOSITION, uID++, _T("&About") );
		addCommandID(About);
	}
    // Insert the submenu into the ctx menu provided by Explorer.
	MENUITEMINFO mii = { sizeof(MENUITEMINFO) };

    mii.fMask = MIIM_SUBMENU | MIIM_STRING | MIIM_ID;
    mii.wID = uID++;
    mii.hSubMenu = hSubmenu;
    mii.dwTypeData = _T("Image Archive");

    InsertMenuItem ( hmenu, indexMenu, TRUE, &mii );

	/*
	InsertMenu(hmenu, indexMenu++, MF_BYPOSITION | MF_SEPARATOR, 0, NULL); 
	
	if (m_cbFiles == 0) {

		InsertMenu(hmenu, indexMenu++, MF_STRING | MF_BYPOSITION, idCmd++, __TEXT("Open SIA"));
	
	} else if (m_cbFiles == 1) {
		// If only one file append it to the Menu item.

		TCHAR l_Filename[_MAX_FNAME];

		ShortFilename(m_szFile, l_Filename);

		_tcscpy(l_szFileMenuItem, l_CheckInLable);
		_tcscat(l_szFileMenuItem,l_Filename);

		InsertMenu(hmenu, indexMenu++, MF_STRING | MF_BYPOSITION, idCmd++, l_szFileMenuItem);
	} else {
		InsertMenu(hmenu, indexMenu++, MF_STRING | MF_BYPOSITION, idCmd++, __TEXT("Copy to SIA"));

	}

	InsertMenu(hmenu, indexMenu++, MF_BYPOSITION | MF_SEPARATOR, 0, NULL); 
	*/
	return MAKE_HRESULT(SEVERITY_SUCCESS, FACILITY_NULL, uID - uidFirstCmd);
	
}

/*
STDMETHODIMP CSimpleShlExt::GetCommandString (
    UINT idCmd, UINT uFlags, UINT* pwReserved, LPSTR pszName, UINT cchMax )
{
*/
HRESULT STDMETHODCALLTYPE CSimpleShlExt::GetCommandString( 
            /* [annotation][in] */ 
            __in  UINT_PTR idCmd,
            /* [annotation][in] */ 
            __in  UINT uFlags,
            /* [annotation][in] */ 
            __reserved  UINT *pReserved,
            /* [annotation][out] */ 
            __out_awcount(!(uType & GCS_UNICODE), cchMax)  LPSTR pszName,
            /* [annotation][in] */ 
            __in  UINT cchMax)
{
USES_CONVERSION;

    // Check idCmd, it must be 0 since we have only one menu item.
    if ( m_nMenus < idCmd )
        return E_INVALIDARG;

    // If Explorer is asking for a help string, copy our string into the
    // supplied buffer.
    if ( uFlags & GCS_HELPTEXT )
        {
        LPCTSTR szText = _T("This is the simple shell extension's help");

        if ( uFlags & GCS_UNICODE )
            {
            // We need to cast pszName to a Unicode string, and then use the
            // Unicode string copy API.
            lstrcpynW ( (LPWSTR) pszName, T2CW(szText), cchMax );
            }
        else
            {
            // Use the ANSI string copy API to return the help string.
            lstrcpynA ( pszName, T2CA(szText), cchMax );
            }

        return S_OK;
        }

    return E_INVALIDARG;
}

STDMETHODIMP CSimpleShlExt::InvokeCommand ( LPCMINVOKECOMMANDINFO pCmdInfo )
{
	USES_CONVERSION;
	
    // If lpVerb really points to a string, ignore this function call and bail out.
    if ( 0 != HIWORD( pCmdInfo->lpVerb ) )
        return E_INVALIDARG;

    // Get the command index - the only valid one is 0.
    int optIdx = LOWORD( pCmdInfo->lpVerb);
    CommandID cmdID = m_CmdList[optIdx];
	switch(cmdID) {
	case Update:
		if (launch("Update") == false) {
			return E_INVALIDARG;
		}
		break;
	case Import:
		if (launch("Import") == false) {
			return E_INVALIDARG;
		}
		break;
	case Export:
		if (launch("Export") == false) {
			return E_INVALIDARG;
		}
		break;
	case Properties:
		if (launch("Properties") == false) {
			return E_INVALIDARG;
		}
		break;
	case About:
		if (launch("About") == false) {
			return E_INVALIDARG;
		}
		break;
	case Checkin:
		if (launch("Checkin") == false) {
			return E_INVALIDARG;
		}
		break;
	case Checkout:
		if (launch("Checkout") == false) {
			return E_INVALIDARG;
		}
		break;
	case ScanDrive:
		if (launch("ScanDrive") == false) {
			return E_INVALIDARG;
		}
		break;
	case Settings:
		if (launch("Settings") == false) {
			return E_INVALIDARG;
		}
		break;
	case Validate:
		if (launch("Validate") == false) {
			return E_INVALIDARG;
		}
		break;
	default:
		return E_INVALIDARG;
	}
	
	//Start();
	return S_OK;
}

bool CSimpleShlExt::launch(const char *regKey) {
	GetRegValues(regKey);
	
	int lRet;
	if (m_cbFiles == 0) {
		//::MessageBox (0,__TEXT("SIA"),__TEXT("No files selected"), MB_OK);


	} else if (m_cbFiles == 1) {
		
		TCHAR *aItem = m_FileList[0];
		TCHAR l_Cmd[260];
		sprintf(l_Cmd, "%s\\%s",m_szExePath, m_szCmd);
		
		CExecuteProcess ep;
		lRet = ep.DoExec(l_Cmd,aItem);
		if (!lRet) // If the function succeeds, the return value is nonzero.
		{
				// If unsuccessfuly open the key
			::MessageBox(0, __TEXT("Sorry? SIA Installation incomplete.\r")
							__TEXT("Please re-install FtpCOPY.\r")
							__TEXT("If the problem persists, please contact IDK Software Ltd."),
				__TEXT("FtpCOPY"), MB_OK);
			return S_OK;
		}
		

	} else if (m_cbFiles > 1) {
		
		char buffer[260];
		char l_TmpName[260];
		char l_TmpPath[260];
		char l_TmpFileAndPath[260];
		
		strcpy(l_TmpPath, T2CA(m_szArgFilePath));
		time_t timeStamp;
		time(&timeStamp);
		itoa (timeStamp,buffer,10);
		//lstrcpynA(l_TmpName, T2CA(szValue), 260 );
		char *l_TmpFileStr = tmpnam(l_TmpName);
		//AtlW2AHelper(buffer, szValue, pdwCount);

		sprintf(l_TmpFileAndPath, "%s\\%sdst.dat",l_TmpPath,buffer);
		
		FILE *m_pFileHandle = fopen(l_TmpFileAndPath, "w");
		if (!m_pFileHandle)
		{
			// If unsuccessfuly found the temp directory
			::MessageBox(0,__TEXT("SIA"),
				__TEXT("SIA Unable to open file"), MB_OK);
			return S_OK;
		}
		int c = m_FileList.GetSize();
		
		for (int i = 0; i < c; i++) {
			TCHAR *aItem = m_FileList[i];
			
			int len = _tcslen(aItem);
			if (sizeof(TCHAR) == 2) { // UNICODE
				//AtlW2AHelper(buffer, aItem, len);
				buffer[len] = '\n';
				buffer[len+1] = '\0';
				fwrite(buffer,1,strlen(buffer),m_pFileHandle);
			} else {
				strcpy(buffer, T2CA(aItem));
				buffer[len] = '\n';
				buffer[len+1] = '\0';
				fwrite(buffer,1,strlen(buffer),m_pFileHandle);
			}	
		}

		fclose(m_pFileHandle);

		// Make the command arg
		sprintf(buffer, "-f \"%s\"",l_TmpFileAndPath);
		
		// Make the command
		TCHAR l_CmdArg[260];
		strcpy(l_CmdArg, A2T(buffer));
		TCHAR l_Cmd[260];
		
		sprintf(l_Cmd, "%s\\%s",m_szExePath, m_szCmd);
		
		CExecuteProcess ep;
		
		lRet = ep.DoExec(l_Cmd,l_CmdArg);
		if (!lRet)
		{
				// If unsuccessfuly open the key
			::MessageBox(0, __TEXT("Sorry? SIA Installation incomplete.\r")
							__TEXT("Please re-install FtpCOPY.\r")
							__TEXT("If the problem persists, please contact IDK Software Ltd."),
				__TEXT("SIA"), MB_OK);
			return S_OK;
		}
		
	} else {
		::MessageBox (0,__TEXT("SIA"),__TEXT("Error"), MB_OK);
	}
	return true;
}

bool CSimpleShlExt::GetRegValues(const char *currCmd) {
	CRegKey hKey;
	long lRet;
	//
	//	Open the registery to extract the directory X:/Temp
	//	and the path to DSDoorMan
	//
	lRet = hKey.Open(HKEY_CURRENT_USER,
				_T(SYSTEM_OPTIONS));

	TCHAR szValue[260];
	LPCTSTR lpszValueName;
	DWORD pdwCount;
	
	

	if (lRet != ERROR_SUCCESS)
	{
		// If unsuccessfuly open the key
		::MessageBox(0, __TEXT("Sorry? SIA Installation incomplete.\r")
						__TEXT("Please re-install SIA.\r")
						__TEXT("If the problem persists, please contact IDK Software Ltd."),
			__TEXT("Simple Image Archive"), MB_OK);
		return true;
	}
	pdwCount = 20;
	lRet = hKey.QueryValue(m_szCmd, A2CT(currCmd), &pdwCount);
	if (lRet != ERROR_SUCCESS)
	{
		// If unsuccessfuly found the temp directory
		::MessageBox(0,__TEXT("Simple Image Archive"),
			__TEXT("Simple Image Archive Installation incomplete"), MB_OK);
		return false;
	}
	pdwCount = 260;
	lRet = hKey.QueryValue(m_szExePath, __TEXT("ExePath"), &pdwCount);
	if (lRet != ERROR_SUCCESS)
	{
		// If unsuccessfuly found the temp directory
		::MessageBox(0,__TEXT("Simple Image Archive"),
			__TEXT("Simple Image Archive Installation incomplete"), MB_OK);
		return false;
	}

	pdwCount = 260;
	lRet = hKey.QueryValue(m_szArgFilePath, __TEXT("TempPath"), &pdwCount);
	if (lRet != ERROR_SUCCESS)
	{
		// If unsuccessfuly found the temp directory
		::MessageBox(0,__TEXT("Simple Image Archive"),
			__TEXT("Simple Image Archive Installation incomplete"), MB_OK);
		return false;
	}
	return true;
}