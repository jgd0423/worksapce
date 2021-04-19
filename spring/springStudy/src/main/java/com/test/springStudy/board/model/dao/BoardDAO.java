package com.test.springStudy.board.model.dao;

import java.util.List;
import java.util.Map;

import com.test.springStudy.board.model.dto.BoardDTO;
import com.test.springStudy.board.model.dto.CommentDTO;

public interface BoardDAO {
	public int setInsert(BoardDTO dto);
	public int getMaxNum(String tbl);
	public int getMaxRefNo(String tbl);
	public int getMaxNoticeNo(String tbl);
	public int getAllRowsCount(String tbl, String search_option, String search_data);
	public List<BoardDTO> getPagingList(int startNum, int endNum, String tbl, String search_option, String search_data);
	public void setUpdateHit(int no);
	public BoardDTO getView(int no);
	public void setUpdateReLevel(BoardDTO dto);
	public int setUpdate(BoardDTO dto);
	public void setNoticeNoLargerThenCurrentNoticeNo(int no, String tbl);
	public int setDelete(BoardDTO dto);
	public int setInsertComment(CommentDTO dto);
	public int getAllCommentRowsCount(int no);
	public List<CommentDTO> getCommentPagingList(int startNum, int endNum, int no);
	public BoardDTO isUsingTable(String tbl);
	public int setUpdateComment(CommentDTO dto);
	public int setDeleteComment(int comment_no, String passwd);
}
