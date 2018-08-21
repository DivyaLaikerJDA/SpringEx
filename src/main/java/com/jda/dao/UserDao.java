package com.jda.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.jda.Model.Model;

@Repository
public class UserDao implements IUserDao {

	@Autowired
	private DataSource dataSource;

	public int registerUser(Model userModel) {
		try {
			Connection connection = dataSource.getConnection();
			String query = "insert into user values(?,?,?,?,?,?,?)";
			Object object[] = new Object[] { 0, userModel.getFirstName(), userModel.getLastName(), userModel.getUserName(),
			      userModel.getEmail(), userModel.getGender(), userModel.getPassword() };
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			int id = jdbcTemplate.update(query, object);
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	@SuppressWarnings("unchecked")
	public Model getUserByEmail(Model userModel) {
		try {
			Connection con = dataSource.getConnection();
			String insertQuery = "Select * from user where username=?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			List<Model> listOfModel = jdbcTemplate.query(insertQuery,
			      new Object[] { userModel.getUserName()}, new RowMapper() {

				      public Object mapRow(ResultSet resultSet, int rownum) throws SQLException {
					      Model user = new Model();
					      user.setFirstName(resultSet.getString(2));
					      user.setLastName(resultSet.getString(3));
					      user.setUserName(resultSet.getString(4));
					      user.setEmail(resultSet.getString(5));
					      user.setGender(resultSet.getString(6));
					      user.setPassword(resultSet.getString(7));

					      return user;
				      }

			      });
			return listOfModel.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Model> getUserByEmailId(Model userModel) {
		try {
			Connection con = dataSource.getConnection();
			String insertQuery = "Select * from user where email=?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			List<Model> listOfModel = jdbcTemplate.query(insertQuery,
			      new Object[] { userModel.getEmail()}, new RowMapper() {

				      public Object mapRow(ResultSet resultSet, int rownum) throws SQLException {
					      Model user = new Model();
					      user.setFirstName(resultSet.getString(2));
					      user.setLastName(resultSet.getString(3));
					      user.setUserName(resultSet.getString(4));
					      user.setEmail(resultSet.getString(5));
					      user.setGender(resultSet.getString(6));
					      user.setPassword(resultSet.getString(7));

					      return user;
				      }

			      });
			return listOfModel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean insertUuid(String uuid, String email) {
	int id = 0;
		
		try{
			Connection connection = dataSource.getConnection();
String query = "update user set uuid=? where email=?";
Object[] object = new Object[]{uuid,email};
JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
id=jdbcTemplate.update(query, object);
if(id>0)
	return true;
else
	return false;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public int updatePassword(Model model, String uuid) {
		// TODO Auto-generated method stub
		try{
		Connection con = dataSource.getConnection();
		String insertQuery = "update  user set password=? where uuid=?";
		Object args[]=new Object[]{model.getPassword(),uuid};
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.update(insertQuery,args);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
}
