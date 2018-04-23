package by.htp.trucking.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.trucking.controller.command.Command;
import by.htp.trucking.controller.command.impl.CreateOrder;
import by.htp.trucking.controller.command.impl.CreateUser;
import by.htp.trucking.controller.command.impl.EditUser;
import by.htp.trucking.controller.command.impl.ForgotPassword;
import by.htp.trucking.controller.command.impl.HistoryOrder;
import by.htp.trucking.controller.command.impl.Localization;
import by.htp.trucking.controller.command.impl.SignIn;
import by.htp.trucking.controller.command.impl.SignOut;
import by.htp.trucking.controller.command.impl.ToCreateOrder;
import by.htp.trucking.controller.command.impl.ToCreateUser;
import by.htp.trucking.controller.command.impl.ToEditUser;
import by.htp.trucking.controller.command.impl.ToHistoryOrder;
import by.htp.trucking.controller.command.impl.ToMainUser;
import by.htp.trucking.controller.command.impl.MainPage;

class CommandProvider {
	
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.MAIN_PAGE, new MainPage());
		commands.put(CommandName.SIGN_IN, new SignIn());
		commands.put(CommandName.SIGN_OUT, new SignOut());
		commands.put(CommandName.GET_PAGE_TO_CREATE_USER, new ToCreateUser());
		commands.put(CommandName.GET_PAGE_TO_MAIN_USER, new ToMainUser());
		commands.put(CommandName.GET_PAGE_TO_CREATE_ORDER, new ToCreateOrder());
		commands.put(CommandName.GET_PAGE_TO_HISTORY_ORDER, new ToHistoryOrder());
		commands.put(CommandName.GET_PAGE_TO_EDIT_USER, new ToEditUser());
		commands.put(CommandName.CREATE_USER, new CreateUser());
		commands.put(CommandName.EDIT_USER, new EditUser());
		commands.put(CommandName.CREATE_ORDER, new CreateOrder());
		commands.put(CommandName.HISTORY_ORDER, new HistoryOrder());
		commands.put(CommandName.FORGOT_PASSWORD, new ForgotPassword());
		commands.put(CommandName.LOCALIZATION, new Localization());
	}
	
	public Command getCommand (String commandName) {
		CommandName commandNameEnum = CommandName.valueOf(commandName.toUpperCase());
		Command command = commands.get(commandNameEnum);
		
		return command;
	}
	
}
