package santetplugin.santetplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitScheduler;

import static org.bukkit.potion.PotionType.SPEED;

public class Commands implements CommandExecutor {
    int count = 5;

    Main plugin;

    public Commands(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("santet")) {
            if(sender.hasPermission("santetplugin.santet")){
                if(args.length > 0){
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 50));
                        target.getWorld().strikeLightningEffect(target.getLocation());
                        sender.sendMessage("§e§lSANTET §2/ §6Sukses Meng-Santet " + target.getName() + "!");
                        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                        scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                            @Override
                            public void run() {
                                target.kickPlayer("§e§lSANTET §2/ §cKamu Telah Di Santet Oleh " + sender.getName() + "!");
                                System.out.println("§e§lSANTETLOGS §2/ §6Logs: §4" + target.getName() + " §6Telah Di Santet Oleh " + sender.getName());
                                for (Player p : Bukkit.getOnlinePlayers()){
                                    if(p.hasPermission("santetplugin.logs")) {
                                        p.sendMessage("§e§lSANTETLOGS §2/ §6Logs: §4" + target.getName() + " §6Telah Di Santet Oleh " + sender.getName());
                                    }
                                }
                            }
                        }, 5 * 20);
                    } else {
                        sender.sendMessage("§e§lSANTET §2/ §6Player §c" + args[0] + " §6Tidak Online!");
                    }
                } else {
                    sender.sendMessage("§e§lSANTET §2/ §cCara Pakai: /santet <player>");
                }
            } else {
                sender.sendMessage("§e§lSANTET §2/ §cKamu Tidak Memiliki Access!");
                System.out.println("§e§lSANTETALERT §2/ §c" + sender.getName() + " §6Mencoba Untuk Menggunakan /santet Namun Dia Tidak Memiliki Permission!");
            }
        }



        return true;
    }
}
