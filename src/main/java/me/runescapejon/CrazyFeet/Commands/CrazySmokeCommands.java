package me.runescapejon.CrazyFeet.Commands;

import java.util.ArrayList;
import java.util.Optional;

import me.runescapejon.CrazyFeet.utils.LanguageUtils;
import me.runescapejon.CrazyFeet.utils.Pair;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import me.runescapejon.CrazyFeet.CrazyFeet;

public class CrazySmokeCommands implements CommandExecutor {
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		final ArrayList<Player> cSmoke = CrazyFeet.getInstance().getCrazySmoke();

		Optional<Player> target = args.getOne("target");

		if (!target.isPresent()) {
			Player player = (Player) src;
			if (player.hasPermission("CrazyFeet.crazysmoke")) {
				if (cSmoke.contains(player)) {
					cSmoke.remove(player);
					player.sendMessage(LanguageUtils.getText("crazySmokeDisabled",
							new Pair<>("%PLAYER%", player.getName())));
					return CommandResult.success();
				} else {
					cSmoke.add(player);
					player.sendMessage(LanguageUtils.getText("crazySmokeEnabled",
							new Pair<>("%PLAYER%", player.getName())));
					return CommandResult.success();
				}
			}
		} else if (src.hasPermission("CrazyFeet.crazysmokeother")) {
			Player targ = target.get();

			if (cSmoke.contains(targ)) {
				cSmoke.remove(targ);
				targ.sendMessage(LanguageUtils.getText("crazySmokeDisabledByPlayer",
						new Pair<>("%PLAYER%", src.getName())));
				src.sendMessage(LanguageUtils.getText("crazySmokeDisabledForPlayer",
						new Pair<>("%PLAYER%", targ.getName())));
				return CommandResult.success();
			} else {
				cSmoke.add(targ);
				targ.sendMessage(LanguageUtils.getText("crazySmokeEnabledByPlayer",
						new Pair<>("%PLAYER%", src.getName())));
				src.sendMessage(LanguageUtils.getText("crazySmokeEnabledForPlayer",
						new Pair<>("%PLAYER%", targ.getName())));				return CommandResult.success();
			}
		}
		return CommandResult.success();
	}
}