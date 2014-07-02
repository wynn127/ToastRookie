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
public class TimeObjDao {
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DataSource dataSource;
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int insert(TimeObj obj){
		String sql = "insert into visittime(email,modifiedTime) values('"
				+ obj.getEmail()+"',";
		if(obj.getModifiedTime() == "") sql+="null";
		else sql+="'"+obj.getModifiedTime()+"'";
		sql += ')';
		return jdbcTemplate.update(sql);
	}
	
	public TimeObj getObj(String email){
		String sql = "select * from visittime where email='"+email+"'";
		RowMapper<TimeObj> mapper = new RowMapper<TimeObj>(){
			public TimeObj mapRow(ResultSet rs, int rowNum)throws SQLException{
				TimeObj obj = new TimeObj();
				obj.setEmail(rs.getString("email"));
				obj.setModifiedTime(rs.getString("modifiedtime"));
				return obj;
			}
		};
		return jdbcTemplate.queryForObject(sql,mapper);
	}
	
	public List<TimeObj> searchAll(){
		String sql = "select * from visittime";
		RowMapper<TimeObj> mapper = new RowMapper<TimeObj>(){
			public TimeObj mapRow(ResultSet rs, int rowNum)throws SQLException{
				TimeObj obj = new TimeObj();
				obj.setEmail(rs.getString("email"));
				obj.setModifiedTime(rs.getString("modifiedtime"));
				return obj;
			}
		};
		return jdbcTemplate.query(sql, mapper);
	}
	
	public int delete(String email){
		String sql = "delete from visittime where email='"+email+"'";
		return jdbcTemplate.update(sql);
	}
	
	public int modify(String email, String mTime){
		String sql = "update visittime set modifiedtime='"+mTime+"' where email='"+email+"'";
		return jdbcTemplate.update(sql);
	}
}
