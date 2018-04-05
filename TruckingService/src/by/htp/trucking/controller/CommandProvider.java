package by.htp.trucking.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.trucking.controller.command.Command;
import by.htp.trucking.controller.command.impl.CreateUserPage;
import by.htp.trucking.controller.command.impl.ForgotPasswordPage;
import by.htp.trucking.controller.command.impl.LoginPage;
import by.htp.trucking.controller.command.impl.MainPage;

class CommandProvider {
	
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.MAIN_PAGE, new MainPage());
		commands.put(CommandName.LOGIN_PAGE, new LoginPage());
		commands.put(CommandName.CREATE_USER_PAGE, new CreateUserPage());
		commands.put(CommandName.FORGOT_PASSWORD_PAGE, new ForgotPasswordPage());
	}
	
	public Command getCommand (String commandName) {
		CommandName commandNameEnum = CommandName.valueOf(commandName.toUpperCase());
		Command command = commands.get(commandNameEnum);
		
		return command;
	}
	
}
