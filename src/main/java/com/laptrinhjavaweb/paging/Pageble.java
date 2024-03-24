package com.laptrinhjavaweb.paging;

import com.laptrinhjavaweb.sort.Sorter;

public interface Pageble {
	public Integer getPage();
	public Integer getLimit();
	public Integer getOffset();
	public Sorter getSorter();
}
