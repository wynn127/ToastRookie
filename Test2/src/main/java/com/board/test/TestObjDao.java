package com.board.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TestObjDao {
	private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int insert(TestObj obj){
		String sql = "insert into visitor(email,password,content,registerTime) values(?,?,?,?)";
		Object[] args = {obj.getEmail(), obj.getPassword(), obj.getContent(), obj.getRegisterTime()};
		return jdbcTemplate.update(sql,args);
	}
	
	public TestObj getObj(String email){
		String sql = "select * from visitor where email like ?";
		
		RowMapper mapper = new RowMapper(){
			public Object mapRow(ResultSet rs, int rowNum)throws SQLException{
				TestObj obj = new TestObj();
				obj.setEmail(rs.getString("email"));
				obj.setPassword(rs.getString("password"));
				obj.setContent(rs.getString("content"));
				obj.setRegisterTime(rs.getString("registerTime"));
				return obj;
			}
		};
		Object[] args = {email};
		return (TestObj)jdbcTemplate.queryForObject(sql,args,mapper);
	}
	
	public List<TestObj> searchAll(){
		String sql = "select * from visitor";
		RowMapper mapper = new RowMapper(){
			public Object mapRow(ResultSet rs, int rowNum)throws SQLException{
				TestObj obj = new TestObj();
				obj.setEmail(rs.getString("email"));
				obj.setPassword(rs.getString("password"));
				obj.setContent(rs.getString("content"));
				obj.setRegisterTime(rs.getString("registerTime"));
				return obj;
			}
		};
		return jdbcTemplate.query(sql, mapper);
	}
}
