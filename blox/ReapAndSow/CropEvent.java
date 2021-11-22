package blox.ReapAndSow;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CropEvent implements Listener {
	
	@EventHandler
	public void onPlayerBreakBlock(BlockBreakEvent event) {
		Block blockBroken = event.getBlock();
		World blockWorld = blockBroken.getWorld();
		if(CropsList.AcceptableCrops.containsKey(blockBroken.getType())) {
			Crop cropBroken = CropsList.AcceptableCrops.get(blockBroken.getType());
			
			//Remove crop
			event.setCancelled(true);
			if(cropBroken.ReadyForHarvest(blockBroken)) {
//				blockBroken.setType(Material.AIR);
				cropBroken.DropCrop(blockWorld, blockBroken);
			}
		}
	}
}
