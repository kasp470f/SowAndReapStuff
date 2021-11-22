package blox.ReapAndSow;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CropEvent implements Listener {
	
	@EventHandler
	public void onPlayerBreakBlock(BlockBreakEvent event) {
		Block blockBroken = event.getBlock();
		//Check if player break farmland below crops
		if(blockBroken.getType() == Material.FARMLAND) {
			Block cropAbove = blockBroken.getRelative(BlockFace.UP, 1);
			if(CropsList.AcceptableCrops.containsKey(cropAbove.getType())) {
				CreateCrop(cropAbove);
			}
		// Check if Crop is an Acceptable Crop.
		} else if(CropsList.AcceptableCrops.containsKey(blockBroken.getType())) {
			CreateCrop(blockBroken);
		}
	}
	
	@EventHandler
	// Player stepping on crops
    public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.PHYSICAL) {
            Block blockSteppedOn = event.getClickedBlock();
            if(blockSteppedOn.getType() == Material.FARMLAND) {
            	Block cropAbove = blockSteppedOn.getRelative(BlockFace.UP, 1);
            	CreateCrop(cropAbove);
            }
		}
	}
	
	public void CreateCrop(Block blockBroken) {
		Crop cropBroken = CropsList.AcceptableCrops.get(blockBroken.getType());
		if(cropBroken.ReadyForHarvest(blockBroken)) {
			blockBroken.setType(Material.AIR);
			cropBroken.DropCrop(blockBroken.getWorld(), blockBroken);
		} else {
			blockBroken.setType(Material.AIR);
			cropBroken.DropSeed(blockBroken.getWorld(), blockBroken);
		}
	}
}
