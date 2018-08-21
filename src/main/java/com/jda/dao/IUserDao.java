package com.jda.dao;

import java.util.List;

import com.jda.Model.Model;

public interface IUserDao {

	public int registerUser(Model userModel);

	public Object getUserByEmail(Model model);

	public boolean insertUuid(String uuid, String userName);

	public List<Model> getUserByEmailId(Model model);

	public int updatePassword(Model model, String uuid);
}
