package at.gotzi.api.command;

import java.util.Properties;

public record GCommandContext(String cmd, String[] args, Properties properties) {

}
