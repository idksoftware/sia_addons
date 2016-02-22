/*
**	Linked Lists
*/

#if !defined(CLinkList_h)
#define CLinkList_h

template <typename TYPE>	class	CLinkList
{
	struct CLLink
	{
		CLLink	* m_pNext ;
		CLLink	* m_pPrev ;
		TYPE	m_Obj ;

		CLLink	(TYPE obj)
		{
			m_pNext = m_pPrev = 0 ;
			m_Obj = obj ;
		}

		~CLLink	(void)
		{
		}
	} ;

	CLLink	* m_pList ;
	CLLink	* m_pLast ;
	CLLink	* m_pCurrent ;
	int		m_nNoitems ;
public:

	CLinkList	(void)
	{
		m_pList = m_pLast = m_pCurrent = 0 ;
		m_nNoitems = 0 ;
	}

	~CLinkList	(void)
	{

		for (m_pCurrent = m_pList ; m_pCurrent ; )
		{
			CLLink	*l_tmp = m_pCurrent->m_pNext;
			delete (m_pCurrent) ;
			m_pCurrent = l_tmp;
		}
	}

	void	Rewind	(void)
	{
		m_pCurrent = m_pList ;
	}

	TYPE	Get	(void)
	{
		return m_pCurrent ? m_pCurrent->m_Obj : 0 ;
	}

	bool	Next	(void)
	{
		m_pCurrent = m_pCurrent->m_pNext ;
		return m_pCurrent ? true : false ;
	}

	int		Count	(void)
	{
		return m_nNoitems ;
	}

	bool	Insert	(TYPE obj)
	{
		if (!obj)
		{
			return false;
		}

		CLLink	* nlnk = new CLLink(obj) ;

		if (m_pList == 0)
			m_pList = m_pLast = m_pCurrent = nlnk ;
		else
		{
			nlnk->m_pPrev = m_pLast ;
			m_pLast->m_pNext = nlnk ;
			m_pLast = nlnk ;
		}

		m_nNoitems++ ;
		return true ;
	}

	bool	Delete		(TYPE obj)
	{
		//	match obj pointer to one on list

		CLLink	* lnk = m_pList ;
		CLLink	* plnk ;

		if (!obj || !lnk)
			return false ;

		// The first in the list
		if (lnk->m_Obj == obj)
		{
			m_pList = lnk->m_pNext ;
			if (lnk == m_pCurrent) {
			// If current == lnk call next
			// so m_pCurrent is valid
				Next();
			}
			m_nNoitems-- ;
			delete lnk;
			return true ;
		}

		for (plnk = lnk, lnk = lnk->m_pNext ; lnk ; plnk = lnk, lnk = lnk->m_pNext)
		{
			if (lnk->m_Obj == obj)
			{
				if (m_pLast == lnk)
				{
				// The last in the list
					plnk->m_pNext = 0 ;
					m_pLast = plnk;
				
					if (lnk == m_pCurrent) {
					// If current == lnk call next
					// so m_pCurrent is valid
						Next();
					}
					m_nNoitems-- ;
					delete lnk;
					return true ;	
				} else {
					plnk->m_pNext = lnk->m_pNext ;
					lnk->m_pNext->m_pPrev = plnk ;
					if (lnk == m_pCurrent) {
					// If current == lnk call next
					// so m_pCurrent is valid
						Next();
					}
					m_nNoitems-- ;
					delete lnk;
					return true ;
				}
			}
		}

		return false ;
	}

	bool	DeleteAll		()
	{
		//	match obj pointer to one on list

		for (m_pCurrent = m_pList ; m_pCurrent ; )
		{
			CLLink	*l_tmp = m_pCurrent->m_pNext;
			delete (m_pCurrent) ;
			m_pCurrent = l_tmp;
		}

		return true ;
	}

	TYPE	* Harden	(int * nNumOfItems)
	{
		TYPE	* array ;
		int		count ;

		array = new TYPE[Count()] ;
		if (!array)
			return 0 ;

		Rewind() ;
		for (count = 0 ; count < Count() ; count++)
		{
			array[count] = m_pCurrent->m_Obj ;
			Fetch() ;
		}

		if (nNumOfItems)
			*nNumOfItems = Count() ;

		return array ;
	}
} ;

#endif