
// MyOverlayIcon.cpp : Implementation of CMyOverlayIcon

#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <algorithm>
#include "atlstr.h"
#include "MyOverlayIcon.h"

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

void CMyOverlayIcon::InsertFile(TCHAR *szFile)
{
	/*
	int len = _tcslen(szFile);
	int s = sizeof(TCHAR);
	TCHAR *l_szTmp = new TCHAR[(len + sizeof(TCHAR))];
	_tcscpy(l_szTmp, szFile);
	m_FileList.Add((TCHAR *)l_szTmp);
	*/
}
// CMyOverlayIcon
// IShellIconOverlayIdentifier::GetOverlayInfo
// returns The Overlay Icon Location to the system
STDMETHODIMP CMyOverlayIcon::GetOverlayInfo(
             LPWSTR pwszIconFile,
             int cchMax,
             int* pIndex,
             DWORD* pdwFlags)
{
  // Get our module's full path
  GetModuleFileNameW(_AtlBaseModule.GetModuleInstance(), pwszIconFile, cchMax);

  // Use first icon in the resource
  *pIndex=0; 

  *pdwFlags = ISIOI_ICONFILE | ISIOI_ICONINDEX;
  return S_OK;
}

// IShellIconOverlayIdentifier::GetPriority
// returns the priority of this overlay 0 being the highest. 
STDMETHODIMP CMyOverlayIcon::GetPriority(int* pPriority)
{
  // we want highest priority 
  *pPriority=0;
  return S_OK;
}

// IShellIconOverlayIdentifier::IsMemberOf
// Returns whether the object should have this overlay or not 
STDMETHODIMP CMyOverlayIcon::IsMemberOf(LPCWSTR pwszPath, DWORD dwAttrib)
{
  TCHAR   tcDrive[_MAX_DRIVE] = { 0 };
  TCHAR   tcDir[_MAX_DIR]     = { 0 };
  TCHAR   tcFname[_MAX_FNAME] = { 0 };
  TCHAR   tcExt[_MAX_EXT]     = { 0 };
  TCHAR   tcPath[_MAX_DIR + _MAX_DRIVE] = { 0 };
  USES_CONVERSION;
  bool isWorkspace = false;
  wchar_t *s = _wcsdup(pwszPath);
  HRESULT r = S_FALSE;
  
  _wcslwr(s);
  DWORD dwReturnCode = _tsplitpath_s(pwszPath, tcDrive, _MAX_DRIVE, tcDir, _MAX_DIR, tcFname, _MAX_FNAME, tcExt, _MAX_EXT );
 
  // Criteria
	wsprintf(tcPath, _T("%s%s\.sia"), tcDrive, tcDir);
	DWORD dwAttribSIA = GetFileAttributes(tcPath);
	isWorkspace = (dwAttribSIA != INVALID_FILE_ATTRIBUTES && (dwAttribSIA & FILE_ATTRIBUTE_DIRECTORY));
	if (isWorkspace) {   
		char buffer[_MAX_PATH + 1];
		TCHAR   fullFname[_MAX_FNAME];
		wsprintf(fullFname, _T("%s%s"), tcFname, tcExt);
		strcpy(buffer, T2A(tcPath));
		readFile(buffer);
		strcpy(buffer, T2A(fullFname));
		if (std::find(m_FileList.begin(), m_FileList.end(), buffer) != m_FileList.end())
		{
			r = S_OK;
		}
		else {
			r = S_FALSE;
		}
		/*
		int e = m_FileList.Find(fullFname);
		if (e == -1)
		{
			r = S_FALSE;
			
		} else
		{
			r = S_OK;
		}
		*/
	}
  free(s);

  return r;
}

bool CMyOverlayIcon::readFile(const char *p) {
	USES_CONVERSION;
	char path[1024];
	char buffer[1024];
	TCHAR szName [1024];
	sprintf(path, "%s\\cout.dat", p);
	std::ifstream fin(path);
  	if(fin.is_open())
	{
		while(!fin.eof())
		{
			fin.getline(buffer,1024);
			//_tcscpy(szName, A2T(buffer));
			m_FileList.push_back(buffer);
		}
	}
	fin.close();
	return true;
}