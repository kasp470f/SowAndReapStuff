package blox.ReapAndSow;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;

public class Crops {
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
	}
}
