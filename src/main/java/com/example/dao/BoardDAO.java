package com.example.dao;

import com.example.bean.BoardVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.swing.tree.TreePath;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class BoardDAO {

	private JdbcTemplate template;

	public void setTemplate(JdbcTemplate template){
		this.template = template;
	}

	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;


	private final String BOARD_INSERT = "insert into BOARD(category,title, writer, content) values (?,?,?,?)";
	private final String BOARD_UPDATE = "update BOARD set category=?,title=?, writer=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete from BOARD  where seq=?";
	private final String BOARD_GET = "select * from BOARD  where seq=?";
	private final String BOARD_LIST = "select * from BOARD order by seq desc";

	public int insertBoard(BoardVO vo) {
		return template.update(BOARD_INSERT,new
				Object[]{vo.getCategory(),vo.getTitle(),vo.getWriter(),vo.getContent()});
	}

	public int deleteBoard(int id) {
		return template.update(BOARD_DELETE,new
				Object[]{id});
	}

	public int updateBoard(BoardVO vo) {
		return template.update(BOARD_UPDATE,new
				Object[]{vo.getCategory(),vo.getTitle(),vo.getWriter(),vo.getContent(),vo.getSeq()});
	}

	public BoardVO getBoard(int seq){
		return template.queryForObject(BOARD_GET,
				new Object[] {seq},
				new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
	}

	public List<BoardVO> getBoardList() {
		return template.query(BOARD_LIST, new RowMapper<BoardVO>(){

			@Override
			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException{
				BoardVO data = new BoardVO();
				data.setSeq(rs.getInt("seq"));
				data.setCategory(rs.getString("category"));
				data.setTitle(rs.getString("title"));
				data.setRegdate(rs.getDate("regdate"));
				data.setWriter(rs.getString("writer"));
				return data;
			}
		});
	}

}



//	public int insertBoard(BoardVO vo) {
//		System.out.println("===> JDBC로 insertBoard() 기능 처리");
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(BOARD_INSERT);
//			stmt.setString(1, vo.getPhoto());
//			stmt.setString(2, vo.getCategory());
//			stmt.setString(3, vo.getTitle());
//			stmt.setString(4, vo.getWriter());
//			stmt.setString(5, vo.getContent());
//			stmt.executeUpdate();
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}
//
//	// 글 삭제
//	public void deleteBoard(BoardVO vo) {
//		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(BOARD_DELETE);
//			stmt.setInt(1, vo.getSeq());
//			stmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	public int updateBoard(BoardVO vo) {
//		System.out.println("===> JDBC로 updateBoard() 기능 처리");
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(BOARD_UPDATE);
//			stmt.setString(1, vo.getPhoto());
//			stmt.setString(2, vo.getCategory());
//			stmt.setString(3, vo.getTitle());
//			stmt.setString(4, vo.getWriter());
//			stmt.setString(5, vo.getContent());
//			stmt.setInt(6, vo.getSeq());
//
//
//			System.out.println(vo.getPhoto()+"-"+vo.getCategory()+"-"+vo.getTitle() + "-" + vo.getWriter() + "-" + vo.getContent() + "-" + vo.getSeq());
//			stmt.executeUpdate();
//			return 1;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}

//	public BoardVO getBoard(int seq) {
//		BoardVO one = new BoardVO();
//		System.out.println("===> JDBC로 getBoard() 기능 처리");
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(BOARD_GET);
//			stmt.setInt(1, seq);
//			rs = stmt.executeQuery();
//			if(rs.next()) {
//				one.setSeq(rs.getInt("seq"));
//				one.setPhoto(rs.getString("photo"));
//				one.setCategory(rs.getString("category"));
//				one.setTitle(rs.getString("title"));
//				one.setWriter(rs.getString("writer"));
//				one.setContent(rs.getString("content"));
//				one.setCnt(rs.getInt("cnt"));
//			}
//			rs.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return one;
//	}

//	public List<BoardVO> getBoardList(){
//		List<BoardVO> list = new ArrayList<BoardVO>();
//		System.out.println("===> JDBC로 getBoardList() 기능 처리");
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(BOARD_LIST);
//			rs = stmt.executeQuery();
//			while(rs.next()) {
//				BoardVO one = new BoardVO();
//				one.setSeq(rs.getInt("seq"));
//				one.setPhoto(rs.getString("photo"));
//				one.setCategory(rs.getString("category"));
//				one.setTitle(rs.getString("title"));
//				one.setWriter(rs.getString("writer"));
//				one.setContent(rs.getString("content"));
//				one.setRegdate(rs.getDate("regdate"));
//				one.setCnt(rs.getInt("cnt"));
//				list.add(one);
//			}
//			rs.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

//	public String getPhotoFilename(int seq) {
//		String filename = null;
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(BOARD_GET);
//			stmt.setInt(1, seq);
//			rs = stmt.executeQuery();
//			if (rs.next()) {
//				filename = rs.getString("photo");
//			}
//			rs.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("==> JDBC로 getPhotoFilename() 기능처리");
//		return filename;
//	}
//}
