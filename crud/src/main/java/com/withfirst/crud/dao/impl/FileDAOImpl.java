package com.withfirst.crud.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.withfirst.crud.dao.FileDAO;
import com.withfirst.crud.vo.FileVO;

@Repository
public class FileDAOImpl implements FileDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static String nameSpace = "FileMapper";
	private static String INSERTFILE = nameSpace + ".insertFile";
	private static String SELECTFILE = nameSpace + ".selectFile";
	private static String DELETEFILE = nameSpace + ".deleteFile";
	private static String DOWNLOADFILE = nameSpace + ".downloadFile";
	
	@Override
	public void insertFile(FileVO file) {
		sqlSession.insert(INSERTFILE, file);
	}

	@Override
	public List<FileVO> selectFile(int board_no) {
		return sqlSession.selectList(SELECTFILE, board_no);
	}

	@Override
	public void deleteFile(int file_id) {
		sqlSession.delete(DELETEFILE, file_id);
	}

	@Override
	public FileVO downloadFile(int file_id) {
		return sqlSession.selectOne(DOWNLOADFILE, file_id);
	}

}
