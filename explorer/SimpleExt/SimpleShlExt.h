// SimpleShlExt.h : Declaration of the CSimpleShlExt

#ifndef __SIMPLESHLEXT_H_
#define __SIMPLESHLEXT_H_

#include "LinkList.h"
/////////////////////////////////////////////////////////////////////////////
// CSimpleShlExt

/*
class CommandInfo {
public:
	typedef enum {
		Update,
		Import,
		Export,
		Properties,
		About,
		Checkin,
		Checkout
	} CommandID; 
private:
	CommandID m_id;
	char m_regKey[15];
public:
	CommandInfo(CommandID id, const char *regKey) {
		m_id = id;
		strncpy(m_regKey, regKey, 14);
	}
};
*/


class ATL_NO_VTABLE CSimpleShlExt : 

    public CComObjectRootEx<CComSingleThreadModel>,
    public CComCoClass<CSimpleShlExt, &CLSID_SimpleShlExt>,
    public IShellExtInit,
    public IContextMenu
{
public:
	typedef enum {
		Update,
		Import,
		Export,
		Properties,
		About,
		Checkin,
		Checkout,
		ScanDrive,
		Settings,
		Validate
	} CommandID; 
private:
	bool m_isWorkspace;
	bool m_isOneDir;
	bool m_isDrive;
	bool m_bFirst;
	UINT m_cbFiles;
	UINT m_nMenus;
	CommandID m_CmdList[10];
	CSimpleArray<TCHAR *> m_FileList;
public:
    CSimpleShlExt() {
		m_bFirst = true;
		m_cbFiles = -1;
	}
	void addCommandID(CommandID cmd);
    DECLARE_REGISTRY_RESOURCEID(IDR_SIMPLESHLEXT)

    BEGIN_COM_MAP(CSimpleShlExt)
        COM_INTERFACE_ENTRY(IShellExtInit)
        COM_INTERFACE_ENTRY(IContextMenu)
    END_COM_MAP()

public:
    // IShellExtInit
    STDMETHODIMP Initialize(LPCITEMIDLIST, LPDATAOBJECT, HKEY);

    // IContextMenu

    //STDMETHODIMP GetCommandString(UINT, UINT, UINT*, LPSTR, UINT);
	HRESULT STDMETHODCALLTYPE GetCommandString( 
            /* [annotation][in] */ 
            __in  UINT_PTR idCmd,
            /* [annotation][in] */ 
            __in  UINT uType,
            /* [annotation][in] */ 
            __reserved  UINT *pReserved,
            /* [annotation][out] */ 
            __out_awcount(!(uType & GCS_UNICODE), cchMax)  LPSTR pszName,
            /* [annotation][in] */ 
            __in  UINT cchMax);
    STDMETHODIMP InvokeCommand(LPCMINVOKECOMMANDINFO);
    STDMETHODIMP QueryContextMenu(HMENU, UINT, UINT, UINT, UINT);
	void InsertFile(TCHAR *szFile);
	bool launch(const char *regKey); 
	bool GetRegValues(const char *currCmd);
protected:
    TCHAR m_szFile [MAX_PATH];
	TCHAR m_szArgFilePath [MAX_PATH];
	TCHAR m_szExePath [MAX_PATH];
	TCHAR m_szCmd[20];
};

#endif //__SIMPLESHLEXT_H_
