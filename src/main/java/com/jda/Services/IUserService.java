package com.jda.Services;

import com.jda.Model.Model;

public interface IUserService {

	public boolean registerUser(Model userModel);

	public Model loginUser(Model model);

	public boolean forgotPassword(Model userModel, String requestURL);

	public boolean updatePass(Model model, String requestURL);
}
