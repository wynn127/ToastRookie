package com.spring.board;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VisitorObjDao {
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DataSource dataSource;
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int insert(VisitorObj obj){
		String sql = "insert into visitor(email,password,content) values('"
				+ obj.getEmail()+"','"+obj.getPassword()+"','"+obj.getContent()+"')";
		//Object[] args = {obj.getEmail(), obj.getPassword(), obj.getContent(), obj.getRegisterTime()};
		return jdbcTemplate.update(sql);
	}
	
	public VisitorObj getObj(String email){
		try{
			VisitorObj result = null;
			String sql = "select * from visitor where email='"+email+"'";
			//String sql = "select * from visitor where email=?";
			RowMapper<VisitorObj> mapper = new RowMapper<VisitorObj>(){
				public VisitorObj mapRow(ResultSet rs, int rowNum)throws SQLException{
					VisitorObj obj = new VisitorObj();
					obj.setEmail(rs.getString("email"));
					obj.setPassword(rs.getString("password"));
					obj.setContent(rs.getString("content"));
					return obj;
				}
			};
			//Object[] obj = {email};
			result = jdbcTemplate.queryForObject(sql,mapper);
			return result;
			
		}catch(DataAccessException e){
			
		}
		return null;
	}
	
	public List<VisitorObj> searchAll(){
		String sql = "select v.email,password,content,registeredTime,modifiedTime "
				+ "from visitor v, visittime t where v.email=t.email order by registeredtime";
		RowMapper<VisitorObj> mapper = new RowMapper<VisitorObj>(){
			public VisitorObj mapRow(ResultSet rs, int rowNum)throws SQLException{
				VisitorObj obj = new VisitorObj();
				obj.setEmail(rs.getString("email"));
				obj.setPassword(rs.getString("password"));
				obj.setContent(rs.getString("content"));
				
				obj.setRegisteredTime(rs.getString("registeredTime"));
				obj.setModifiedTime(rs.getString("modifiedTime"));
				return obj;
			}
		};
		return jdbcTemplate.query(sql, mapper);
	}
	
	public int delete(String email){
		String sql = "delete from visitor where email='"+email+"'";
		return jdbcTemplate.update(sql);
	}
	
	public int modify(String email, String content){
		String sql = "update visitor set content='"+content+"' where email='"+email+"'";
		return jdbcTemplate.update(sql);
	}
}
