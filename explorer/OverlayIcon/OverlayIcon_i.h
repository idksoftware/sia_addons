

/* this ALWAYS GENERATED file contains the definitions for the interfaces */


 /* File created by MIDL compiler version 8.01.0622 */
/* at Tue Jan 19 03:14:07 2038
 */
/* Compiler settings for OverlayIcon.idl:
    Oicf, W1, Zp8, env=Win64 (32b run), target_arch=AMD64 8.01.0622 
    protocol : all , ms_ext, c_ext, robust
    error checks: allocation ref bounds_check enum stub_data 
    VC __declspec() decoration level: 
         __declspec(uuid()), __declspec(selectany), __declspec(novtable)
         DECLSPEC_UUID(), MIDL_INTERFACE()
*/
/* @@MIDL_FILE_HEADING(  ) */



/* verify that the <rpcndr.h> version is high enough to compile this file*/
#ifndef __REQUIRED_RPCNDR_H_VERSION__
#define __REQUIRED_RPCNDR_H_VERSION__ 500
#endif

#include "rpc.h"
#include "rpcndr.h"

#ifndef __RPCNDR_H_VERSION__
#error this stub requires an updated version of <rpcndr.h>
#endif /* __RPCNDR_H_VERSION__ */

#ifndef COM_NO_WINDOWS_H
#include "windows.h"
#include "ole2.h"
#endif /*COM_NO_WINDOWS_H*/

#ifndef __OverlayIcon_i_h__
#define __OverlayIcon_i_h__

#if defined(_MSC_VER) && (_MSC_VER >= 1020)
#pragma once
#endif

/* Forward Declarations */ 

#ifndef __IMyOverlayIcon_FWD_DEFINED__
#define __IMyOverlayIcon_FWD_DEFINED__
typedef interface IMyOverlayIcon IMyOverlayIcon;

#endif 	/* __IMyOverlayIcon_FWD_DEFINED__ */


#ifndef __MyOverlayIcon_FWD_DEFINED__
#define __MyOverlayIcon_FWD_DEFINED__

#ifdef __cplusplus
typedef class MyOverlayIcon MyOverlayIcon;
#else
typedef struct MyOverlayIcon MyOverlayIcon;
#endif /* __cplusplus */

#endif 	/* __MyOverlayIcon_FWD_DEFINED__ */


/* header files for imported files */
#include "oaidl.h"
#include "ocidl.h"
#include "shobjidl.h"

#ifdef __cplusplus
extern "C"{
#endif 


#ifndef __IMyOverlayIcon_INTERFACE_DEFINED__
#define __IMyOverlayIcon_INTERFACE_DEFINED__

/* interface IMyOverlayIcon */
/* [unique][nonextensible][dual][uuid][object] */ 


EXTERN_C const IID IID_IMyOverlayIcon;

#if defined(__cplusplus) && !defined(CINTERFACE)
    
    MIDL_INTERFACE("C6932E06-5135-4B7E-BD9E-CA90C0C6095B")
    IMyOverlayIcon : public IDispatch
    {
    public:
    };
    
    
#else 	/* C style interface */

    typedef struct IMyOverlayIconVtbl
    {
        BEGIN_INTERFACE
        
        HRESULT ( STDMETHODCALLTYPE *QueryInterface )( 
            IMyOverlayIcon * This,
            /* [in] */ REFIID riid,
            /* [annotation][iid_is][out] */ 
            _COM_Outptr_  void **ppvObject);
        
        ULONG ( STDMETHODCALLTYPE *AddRef )( 
            IMyOverlayIcon * This);
        
        ULONG ( STDMETHODCALLTYPE *Release )( 
            IMyOverlayIcon * This);
        
        HRESULT ( STDMETHODCALLTYPE *GetTypeInfoCount )( 
            IMyOverlayIcon * This,
            /* [out] */ UINT *pctinfo);
        
        HRESULT ( STDMETHODCALLTYPE *GetTypeInfo )( 
            IMyOverlayIcon * This,
            /* [in] */ UINT iTInfo,
            /* [in] */ LCID lcid,
            /* [out] */ ITypeInfo **ppTInfo);
        
        HRESULT ( STDMETHODCALLTYPE *GetIDsOfNames )( 
            IMyOverlayIcon * This,
            /* [in] */ REFIID riid,
            /* [size_is][in] */ LPOLESTR *rgszNames,
            /* [range][in] */ UINT cNames,
            /* [in] */ LCID lcid,
            /* [size_is][out] */ DISPID *rgDispId);
        
        /* [local] */ HRESULT ( STDMETHODCALLTYPE *Invoke )( 
            IMyOverlayIcon * This,
            /* [annotation][in] */ 
            _In_  DISPID dispIdMember,
            /* [annotation][in] */ 
            _In_  REFIID riid,
            /* [annotation][in] */ 
            _In_  LCID lcid,
            /* [annotation][in] */ 
            _In_  WORD wFlags,
            /* [annotation][out][in] */ 
            _In_  DISPPARAMS *pDispParams,
            /* [annotation][out] */ 
            _Out_opt_  VARIANT *pVarResult,
            /* [annotation][out] */ 
            _Out_opt_  EXCEPINFO *pExcepInfo,
            /* [annotation][out] */ 
            _Out_opt_  UINT *puArgErr);
        
        END_INTERFACE
    } IMyOverlayIconVtbl;

    interface IMyOverlayIcon
    {
        CONST_VTBL struct IMyOverlayIconVtbl *lpVtbl;
    };

    

#ifdef COBJMACROS


#define IMyOverlayIcon_QueryInterface(This,riid,ppvObject)	\
    ( (This)->lpVtbl -> QueryInterface(This,riid,ppvObject) ) 

#define IMyOverlayIcon_AddRef(This)	\
    ( (This)->lpVtbl -> AddRef(This) ) 

#define IMyOverlayIcon_Release(This)	\
    ( (This)->lpVtbl -> Release(This) ) 


#define IMyOverlayIcon_GetTypeInfoCount(This,pctinfo)	\
    ( (This)->lpVtbl -> GetTypeInfoCount(This,pctinfo) ) 

#define IMyOverlayIcon_GetTypeInfo(This,iTInfo,lcid,ppTInfo)	\
    ( (This)->lpVtbl -> GetTypeInfo(This,iTInfo,lcid,ppTInfo) ) 

#define IMyOverlayIcon_GetIDsOfNames(This,riid,rgszNames,cNames,lcid,rgDispId)	\
    ( (This)->lpVtbl -> GetIDsOfNames(This,riid,rgszNames,cNames,lcid,rgDispId) ) 

#define IMyOverlayIcon_Invoke(This,dispIdMember,riid,lcid,wFlags,pDispParams,pVarResult,pExcepInfo,puArgErr)	\
    ( (This)->lpVtbl -> Invoke(This,dispIdMember,riid,lcid,wFlags,pDispParams,pVarResult,pExcepInfo,puArgErr) ) 


#endif /* COBJMACROS */


#endif 	/* C style interface */




#endif 	/* __IMyOverlayIcon_INTERFACE_DEFINED__ */



#ifndef __OverlayIconLib_LIBRARY_DEFINED__
#define __OverlayIconLib_LIBRARY_DEFINED__

/* library OverlayIconLib */
/* [version][uuid] */ 


EXTERN_C const IID LIBID_OverlayIconLib;

EXTERN_C const CLSID CLSID_MyOverlayIcon;

#ifdef __cplusplus

class DECLSPEC_UUID("804E2640-B965-4D6D-BD3A-695950484BCC")
MyOverlayIcon;
#endif
#endif /* __OverlayIconLib_LIBRARY_DEFINED__ */

/* Additional Prototypes for ALL interfaces */

/* end of Additional Prototypes */

#ifdef __cplusplus
}
#endif

#endif


