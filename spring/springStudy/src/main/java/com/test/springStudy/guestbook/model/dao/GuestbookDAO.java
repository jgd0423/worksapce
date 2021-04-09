package com.test.springStudy.guestbook.model.dao;

import java.util.List;

import com.test.springStudy.guestbook.model.dto.GuestbookDTO;

public interface GuestbookDAO {
	public int setInsert(GuestbookDTO dto);
	public int getAllRowsCount(String search_option, String search_data);
	public List<GuestbookDTO> getPagingList(int startNum, int endNum, String search_option, String search_data);
	public GuestbookDTO getView(int no);
	public int setUpdate(GuestbookDTO dto);
	public int setDelete(GuestbookDTO dto);
}
