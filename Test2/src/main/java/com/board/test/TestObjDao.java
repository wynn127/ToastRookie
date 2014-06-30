package com.board.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TestObjDao {
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DataSource dataSource;
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int insert(TestObj obj){
		String sql = "insert into visitor(email,[password],content,registerTime) values('"
				+ obj.getEmail()+"','"+obj.getPassword()+"','"+obj.getContent()+"','"+obj.getRegisterTime()+"')";
		//Object[] args = {obj.getEmail(), obj.getPassword(), obj.getContent(), obj.getRegisterTime()};
		return jdbcTemplate.update(sql);
	}
	
	public TestObj getObj(String email){
		String sql = "select * from visitor where email='"+email+"'";
		//String sql = "select * from visitor where email=?";
		RowMapper<TestObj> mapper = new RowMapper<TestObj>(){
			public TestObj mapRow(ResultSet rs, int rowNum)throws SQLException{
				TestObj obj = new TestObj();
				obj.setEmail(rs.getString("email"));
				obj.setPassword(rs.getString("password"));
				obj.setContent(rs.getString("content"));
				obj.setRegisterTime(rs.getString("registertime"));
				return obj;
			}
		};
		//Object[] obj = {email};
		return jdbcTemplate.queryForObject(sql,mapper);
	}
	
	public List<TestObj> searchAll(){
		String sql = "select v.email,password,content,registerTime,modifiedTime "
				+ "from visitor v, visittime t where v.email=t.email order by registertime";
		RowMapper mapper = new RowMapper(){
			public Object mapRow(ResultSet rs, int rowNum)throws SQLException{
				TestObj obj = new TestObj();
				obj.setEmail(rs.getString("email"));
				obj.setPassword(rs.getString("password"));
				obj.setContent(rs.getString("content"));
				obj.setRegisterTime(rs.getString("registerTime"));
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
