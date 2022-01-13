package santetplugin.santetplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

public class CustomCommandSantet implements CommandExecutor {

    Main plugin;

    public CustomCommandSantet(Main plugin){
        this.plugin = plugin;
    }

    public static boolean isInt(String arg, String s, CommandSender sender) {
        try {
            Integer.parseInt(s);
            Integer.parseInt(arg);
        } catch (NumberFormatException nfe) {
            sender.sendMessage("§e§lSANTET §2/ §cMasukkan Angka Yang Benar!");
            return false;
        }
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("santet-custom")) {
            if(sender.hasPermission("santetplugin.santetcustom")){
                if(args.length >= 3){
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if(isInt(args[1], args[2], sender)){
                            target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.parseInt(args[1]) * 20, 50));
                            target.getWorld().strikeLightningEffect(target.getLocation());
                            sender.sendMessage("§e§lSANTET §2/ §6Sukses Meng-Santet " + target.getName() + " Durasi Effect Blindness: " + args[1] + " Dengan Kick Delay: " + args[2]);
                            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                            scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                                @Override
                                public void run() {
                                    target.kickPlayer("§e§lSANTET §2/ §cKamu Telah Di Santet Oleh " + sender.getName() + "!");
                                    System.out.println("§e§lSANTETLOGS §2/ §6Logs: §4" + target.getName() + " §6Telah Di Santet Oleh " + sender.getName() + " Durasi Effect Blindness: " + args[1] + " seconds Kick Delay: " + args[2] + " seconds");
                                    for (Player p : Bukkit.getOnlinePlayers()){
                                        if(p.hasPermission("santetplugin.logs")) {
                                            p.sendMessage("§e§lSANTETLOGS §2/ §6Logs: §4" + target.getName() + " §6Telah Di Santet Oleh " + sender.getName() + " Durasi Effect Blindness: " + args[1] + " seconds Kick Delay: " + args[2] + " seconds");
                                        }
                                    }
                                }
                            }, Integer.parseInt(args[2]) * 20);
                        }
                    } else {
                        sender.sendMessage("§e§lSANTET §2/ §6Player §c" + args[0] + " §6Tidak Online!");
                    }
                } else {
                    sender.sendMessage("§e§lSANTET §2/ §cCara Pakai: /santet-custom <player> <blindness time(int)> <kick delay(int)>");
                }
            } else {
                sender.sendMessage("§e§lSANTET §2/ §cKamu Tidak Memiliki Access!");
                System.out.println("§e§lSANTETALERT §2/ §c" + sender.getName() + " §6Mencoba Untuk Menggunakan /santet Namun Dia Tidak Memiliki Permission!");
            }
        }

        return true;
    }
}
