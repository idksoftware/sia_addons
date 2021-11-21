// MyOverlayIcon.h : Declaration of the CMyOverlayIcon

#pragma once
#include "resource.h"       // main symbols
#include "OverlayIcon_i.h"
#include <shlobj.h>
#include <comdef.h>
#include <string>
#include <vector>


#if defined(_WIN32_WCE) && !defined(_CE_DCOM) && !defined(_CE_ALLOW_SINGLE_THREADED_OBJECTS_IN_MTA)
#error "Single-threaded COM objects are not properly supported on Windows CE platform, such as the Windows Mobile platforms that do not include full DCOM support. Define _CE_ALLOW_SINGLE_THREADED_OBJECTS_IN_MTA to force ATL to support creating single-thread COM object's and allow use of it's single-threaded COM object implementations. The threading model in your rgs file was set to 'Free' as that is the only threading model supported in non DCOM Windows CE platforms."
#endif

using namespace ATL;


// CMyOverlayIcon

class ATL_NO_VTABLE CMyOverlayIcon :
	public CComObjectRootEx<CComSingleThreadModel>,
	public CComCoClass<CMyOverlayIcon, &CLSID_MyOverlayIcon>,
	public IShellIconOverlayIdentifier,
	public IDispatchImpl<IMyOverlayIcon, &IID_IMyOverlayIcon, &LIBID_OverlayIconLib, /*wMajor =*/ 1, /*wMinor =*/ 0>
{
public:
	CMyOverlayIcon()
	{
	}

	STDMETHOD(GetOverlayInfo)(LPWSTR pwszIconFile, 
					int cchMax,int *pIndex,DWORD* pdwFlags);
	STDMETHOD(GetPriority)(int* pPriority);
	STDMETHOD(IsMemberOf)(LPCWSTR pwszPath,DWORD dwAttrib);


DECLARE_REGISTRY_RESOURCEID(IDR_MYOVERLAYICON)


BEGIN_COM_MAP(CMyOverlayIcon)
	COM_INTERFACE_ENTRY(IMyOverlayIcon)
	COM_INTERFACE_ENTRY(IDispatch)
	COM_INTERFACE_ENTRY(IShellIconOverlayIdentifier)
END_COM_MAP()



	DECLARE_PROTECT_FINAL_CONSTRUCT()

	HRESULT FinalConstruct()
	{
		return S_OK;
	}

	void FinalRelease()
	{
	}

public:
	void InsertFile(TCHAR *szFile);
private:
	//CSimpleArray<TCHAR *> m_FileList;
	std::vector<std::string> m_FileList;
	bool readFile(const char *path);
	bool GetRegValues();
	TCHAR m_szWorkspacePath[MAX_PATH];
};

OBJECT_ENTRY_AUTO(__uuidof(MyOverlayIcon), CMyOverlayIcon)
