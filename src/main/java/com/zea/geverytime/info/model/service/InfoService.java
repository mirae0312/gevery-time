package com.zea.geverytime.info.model.service;

import static com.zea.geverytime.common.JdbcTemplate.close;
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


}
