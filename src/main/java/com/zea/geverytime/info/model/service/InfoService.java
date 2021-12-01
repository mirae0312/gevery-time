package com.zea.geverytime.info.model.service;

import static com.zea.geverytime.common.JdbcTemplate.*;
import static com.zea.geverytime.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.zea.geverytime.common.model.vo.Attachment;
import com.zea.geverytime.info.model.dao.InfoDao;
import com.zea.geverytime.info.model.vo.Info;

public class InfoService {
	
	private InfoDao infoDao = new InfoDao();

	public List<Info> selectPopList(String board) {
		Connection conn = null;
		List<Info> popList = null;
		List<Attachment> attach = null;
		try {
			conn = getConnection();
			popList = infoDao.selectPopList(board, conn);	
			for(int i = 0; i < popList.size(); i++) {
				String code = popList.get(i).getCode();
				attach = infoDao.selectPopAttach(conn, code);
				System.out.println("service attach : " + attach);
				popList.get(i).setAttachments(attach);
			}

		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return popList;
	}

	public List<Info> selectAllList(String board, int start, int end) {
		Connection conn = null;
		List<Info> list = null;
		List<Attachment> attach = null;
		try {
			conn = getConnection();
			list = infoDao.selectAllList(board, conn, start, end);
			for(int i = 0; i < list.size(); i++) {
				String code = list.get(i).getCode();
				attach = infoDao.selectAllAttach(conn, code, start, end);
				System.out.println("[Service] AllAttach : " + attach);
				if(attach.isEmpty()) {
					Attachment infoAttach = new Attachment();
					infoAttach.setRenamedFilename("파일 없음");
					attach.add(infoAttach);
				}
				list.get(i).setAttachments(attach);
			}
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return list;
	}

	public int insertInfo(Info info) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = infoDao.insertInfo(conn, info);
			String code = infoDao.selectCode(conn, info);
			info.setCode(code);
			
			List<Attachment> attachments = info.getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					attach.setCode(code);
					result = infoDao.insertAttachment(conn, attach);
				}
			}
			if(result > 0)
				commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally{
			close(conn);
		}
		return result;
	}

	public Info selectBeforeWrite(String memberId) {
		Connection conn = null;
		Info info = null;
		try {
			conn = getConnection();
			info = infoDao.selectBeforeWrite(conn, memberId);
		} catch(Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return info;
	}

	public String checkInfoBoard(String memberId) {
		Connection conn = null;
		String check = "";
		try {
			conn = getConnection();
			check = infoDao.checkInfoBoard(conn, memberId);
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return check;
	}

	public Info selectOneView(String code) {
		Connection conn = null;
		Info info = null;
		try {
			conn = getConnection();
			info = infoDao.selectOneView(conn, code);
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return info;
	}

	public int updateReadCount(String code) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = infoDao.updateReadCount(conn, code);
			if(result > 0)
				commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}


}
