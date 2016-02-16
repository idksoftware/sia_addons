// dllmain.h : Declaration of module class.

class COverlayIconModule : public ATL::CAtlDllModuleT< COverlayIconModule >
{
public :
	DECLARE_LIBID(LIBID_OverlayIconLib)
	DECLARE_REGISTRY_APPID_RESOURCEID(IDR_OVERLAYICON, "{FB425F2C-5264-4A1F-80FB-128F1302586F}")
};

extern class COverlayIconModule _AtlModule;
