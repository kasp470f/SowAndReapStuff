package blox.ReapAndSow;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		System.out.println("Hello world");
		
		getServer().getPluginManager().registerEvents(new BreakBlock(), this);
	}
}
