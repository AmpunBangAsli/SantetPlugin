package santetplugin.santetplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("santet").setExecutor(new Commands(this));
        this.getCommand("santet-custom").setExecutor(new CustomCommandSantet(this));
        this.getCommand("santet-ban").setExecutor(new BanCommandSantet(this));
        this.getCommand("santet-unban").setExecutor(new PardonBanCommandSantet());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
