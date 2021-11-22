package blox.ReapAndSow;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlock implements Listener {
	
	@EventHandler
	public void onPlayerBreakBlock(BlockBreakEvent event) {
		Block blockBroken = event.getBlock();
		System.out.println(blockBroken.getType());
		World blockWorld = blockBroken.getWorld();
		if(Crops.AcceptableCrops.containsKey(blockBroken.getType())) {
			Crop cropBroken = Crops.AcceptableCrops.get(blockBroken.getType());
			
			//Remove crop
			event.setCancelled(true);
			if(cropBroken.ReadyForHarvest(blockBroken)) {
//				blockBroken.setType(Material.AIR);
				cropBroken.DropCrop(blockWorld, blockBroken);
			}
		}
	}
}
