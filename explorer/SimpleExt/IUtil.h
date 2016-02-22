//******************************************************************************************* 
// 
// Filename : Cabvw2.h 
// 
//Cab_MergeMenu 
// 
// Copyright 1994 - 1998 Microsoft Corporation. All rights reserved 
// 
//******************************************************************************************* 
 
 
 
#ifndef _ISHELL2_H_ 
#define _ISHELL2_H_ 
 
#ifdef __cplusplus 
extern "C" { 
#endif 
                              
 
UINT  Cab_MergeMenus(HMENU hmDst, HMENU hmSrc, UINT uInsert, UINT uIDAdjust, UINT uIDAdjustMax, ULONG uFlags); 
 
//=================================================================== 
// Cab_MergeMenu parameter 
// 
#define MM_ADDSEPARATOR		0x00000001L 
#define MM_SUBMENUSHAVEIDS	0x00000002L 
 
#define ARRAYSIZE(a)    (sizeof(a)/sizeof(a[0])) 
 
 
#ifdef __cplusplus 
} 
#endif 
                                            
 
#endif // _ISHELL2_H_ 
 
 
