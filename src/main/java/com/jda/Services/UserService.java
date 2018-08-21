package com.jda.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jda.Model.Model;
import com.jda.dao.IUserDao;
import com.jda.dao.UserDao;
import com.jda.util.ApplicationMailer;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private DataSource dataSource;

	public boolean registerUser(Model userModel) {
		userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
		if (userDao.registerUser(userModel) > 0)
			return true;
		return false;
	}

	/*
	 * public Model loginUser(Model userModel) { String query =
	 * "select * from user where userName=? and password=?"; JdbcTemplate
	 * jdbcTemplate = new JdbcTemplate(dataSource); return
	 * jdbcTemplate.queryForObject(query, new
	 * Object[]{userModel.getUserName(),userModel.getPassword()},new RowMapper(){
	 * public Object mapRow(ResultSet resultSet,int rowNum) throws SQLException {
	 * Model user = new Model(); } })
	 */
	public Model loginUser(Model model) {
		Model userModel = (Model) userDao.getUserByEmail(model);

		if (userModel != null) {
			if (BCrypt.checkpw(model.getPassword(), userModel.getPassword())) {
					return userModel;
			}

			return null;
		}
		return null;
	}
	
	public boolean forgotPassword(Model model,String requestURL){
List<Model>	 UserM = (List<Model>) userDao.getUserByEmailId(model);
Model User = UserM.get(0);
	if(User!=null){
		
		String uuid=UUID.randomUUID().toString();
	String link = requestURL.substring(0, requestURL.lastIndexOf("/"))+"/ResetPassword?uudi="+uuid;
	if(userDao.insertUuid(uuid,model.getEmail()))
	{System.out.println("test");
		ApplicationMailer applicationMailer = new ApplicationMailer();
		if(applicationMailer.sendMail(link))
			return true;
		else
			return false;
	}
	}
	return false;
	}

	public boolean updatePass(Model model, String requestURL) {
		// TODO Auto-generated method stub
		
		String uuid = requestURL.substring(requestURL.lastIndexOf("=")+1, requestURL.length());
	if(userDao.updatePassword(model,uuid)>0)
		return true;
	
	return false;
		
}
}
