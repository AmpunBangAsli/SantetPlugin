package santetplugin.santetplugin;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PardonBanCommandSantet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("santet-unban")){
            if(sender.hasPermission("santetplugin.santetunban")) {
                if(args.length > 0) {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                    if(target.isBanned() && target.hasPlayedBefore()){
                        Bukkit.getBanList(BanList.Type.NAME).pardon(target.getName());
                        sender.sendMessage("§e§lSANTET §2/ §aSukses Unban " + target.getName());
                        System.out.println("§e§lSANTETLOGS §2/ §aUnbanned " + target.getName());
                        System.out.println("§e§lSANTETLOGS §2/ §eRequested By: " + sender.getName());
                        for (Player p : Bukkit.getOnlinePlayers()){
                            if(p.hasPermission("santetplugin.logs")){
                                p.sendMessage("§e§lSANTET §2/ §aUnbanned " + target.getName());
                                p.sendMessage("§e§lSANTET §2/ §eRequested By: " + sender.getName());
                            }
                        }
                    } else {
                        sender.sendMessage("§e§lSANTET §2/ §cPlayer Itu Tidak Di Ban!");
                    }
                } else {
                    sender.sendMessage("§e§lSANTET §2/ §cCara Pakai: /santet-unban <bannedplayername>");
                }
            } else {
                sender.sendMessage("§e§lSANTET §2/ §cKamu Tidak Memiliki Akses!");
            }
        }



        return true;
    }
}
