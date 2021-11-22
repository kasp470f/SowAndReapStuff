package blox.ReapAndSow;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;

public class CropsList {
	// <BlockBroken, Corresponding Crop>
	public static Map<Material, Crop> AcceptableCrops;
	
	static {
		//List of Crops that are implemented
		AcceptableCrops = new HashMap<Material, Crop>();
		
		// Wheat
		Crop wheat = new Crop(Material.WHEAT, Material.WHEAT, Material.WHEAT_SEEDS);
		wheat.SetYield(1, 1);
		AcceptableCrops.put(Material.WHEAT, wheat);
		
		// Beetroot
		Crop beetroot = new Crop(Material.BEETROOTS, Material.BEETROOT, Material.BEETROOT_SEEDS);
		beetroot.SetYield(1, 1);
		AcceptableCrops.put(Material.BEETROOTS, beetroot);
		
		// Carrot
		Crop carrot = new Crop(Material.CARROTS, Material.CARROT);
		carrot.SetYield(2, 5);
		AcceptableCrops.put(Material.CARROTS, carrot);
		
		// Potato
		Crop potato = new Crop(Material.POTATOES, Material.POTATO);
		potato.SetYield(1, 5);
		AcceptableCrops.put(Material.POTATOES, potato);
		
		// Cocoa
		Crop cocoa = new Crop(Material.COCOA, Material.COCOA_BEANS);
		cocoa.SetYield(3, 3);
		AcceptableCrops.put(Material.COCOA, cocoa);
		
		// Pumpking
		Crop pumpkin = new Crop(Material.PUMPKIN, Material.PUMPKIN);
		pumpkin.SetYield(1, 1);
		AcceptableCrops.put(Material.PUMPKIN, pumpkin);
		
		// Melon
		Crop melon = new Crop(Material.MELON, Material.MELON_SLICE);
		melon.SetYield(3, 9);
		AcceptableCrops.put(Material.MELON, melon);
	}
}
