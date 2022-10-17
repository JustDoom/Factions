package com.massivecraft.factions.commands;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class FCommandPowerBoost extends FBaseCommand {

	public FCommandPowerBoost() {
		aliases.add("powerboost");
		aliases.add("powboost");
		aliases.add("pboost");
		aliases.add("pb");
		
		senderMustBePlayer = false;
		
		requiredParameters.add("p|f|player|faction");
		requiredParameters.add("name");
		requiredParameters.add("#");
		
		helpDescription = "show player power info";
	}
	
	@Override
	public boolean hasPermission(CommandSender sender) {
		return true;
	}
	
	@Override
	public void perform() {
		System.out.println("perform");
		String type = this.argAsString(0).toLowerCase();
		System.out.println("HELLO  TYPE: " + type);
		boolean doPlayer = true;
		if (type.equals("f") || type.equals("faction"))
		{
			doPlayer = false;
		}
		else if (!type.equals("p") && !type.equals("player"))
		{
			sendMessage("<b>You must specify \"p\" or \"player\" to target a player or \"f\" or \"faction\" to target a faction.");
			sendMessage("<b>ex. /f powerboost p SomePlayer 0.5  -or-  /f powerboost f SomeFaction -5");
			return;
		}

		Double targetPower = this.argAsDouble(2);
		if (targetPower == null)
		{
			sendMessage("<b>You must specify a valid numeric value for the power bonus/penalty amount.");
			return;
		}

		String target;

		if (doPlayer)
		{
			FPlayer targetPlayer = this.argAsBestFPlayerMatch(1);
			if (targetPlayer == null) return;
			targetPlayer.setPowerBoost(targetPower);
			target = "Player \""+targetPlayer.getName()+"\"";
		}
		else
		{
			Faction targetFaction = this.argAsFaction(1);
			if (targetFaction == null) return;
			targetFaction.setPowerBoost(targetPower);
			target = "Faction \""+targetFaction.getTag()+"\"";
		}

		sendMessage(target+" now has a power bonus/penalty of "+targetPower+" to min and max power levels.");
		//if (!senderIsConsole) TODO: fix this
			//P.p.log(fme.getName()+" has set the power bonus/penalty for "+target+" to "+targetPower+".");

//		FPlayer target;
//		if (parameters.size() > 0) {
//			if (!Factions.hasPermViewAnyPower(player)) {
//				me.sendMessage("You do not have the appropriate permission to view another player's power level.");
//				return;
//			}
//			target = findFPlayer(parameters.get(0), false);
//		} else if (!(sender instanceof Player)) {
//			sendMessage("From the console, you must specify a player (f power <player name>).");
//			return;
//		} else {
//			target = me;
//		}
//
//		if (target == null) {
//			return;
//		}
//
//		// if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
//		if (!payForCommand(Conf.econCostPower)) {
//			return;
//		}
//
//		sendMessage(target.getNameAndRelevant(me)+Conf.colorChrome+" - Power / Maxpower: "+Conf.colorSystem+target.getPowerRounded()+" / "+target.getPowerMaxRounded());
	}
	
}
