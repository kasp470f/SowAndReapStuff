package blox.ReapAndSow;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.block.data.Ageable;

public class Crop {
	public String CropName;
	public Material CropBlock;
	public Material CropItem;
	public Material CropSeed;
	
	public int CropYield;
	public int CropMinYield;
	public int CropMaxYield;
	public Material AdditionalDrops;
	
	public ChatColor GradeColor;
	public String Grade;
	
	private static Random rand = new Random();
	
	public Crop(Material block, Material item) {
		CropBlock = block;
		CropItem = item;
		
		CropName = block.name().substring(0, 1).toUpperCase() + block.name().substring(1).toLowerCase();
	}
	
	// Wheat and Beetroots
	public Crop(Material block, Material item, Material seed) {
		CropBlock = block;
		CropItem = item;
		CropSeed = seed;
		
		CropName = block.name().substring(0, 1).toUpperCase() + block.name().substring(1).toLowerCase();
	}
	
	public void SetYield(int minYield, int maxYield) {
		CropMaxYield = maxYield;
		CropMinYield = minYield;
	}
	
	public boolean ReadyForHarvest(Block cropBlock) {
		Ageable ageable = (Ageable) cropBlock.getBlockData();
		if (ageable.getAge() == ageable.getMaximumAge()) {
			return true;
		}
		return false;
	}
	
	private void CreateGrade() {
		int percentage = rand.nextInt(255);
		if (percentage <= 200) {
			Grade = "Common";
			GradeColor = ChatColor.GREEN;
		} else if (percentage <= 245) {
			Grade = "Rare";
			GradeColor = ChatColor.BLUE;
		} else if (percentage <= 254) {
			Grade = "Legendary";
			GradeColor = ChatColor.GOLD;
		} else {
			Grade = "Mythic";
			GradeColor = ChatColor.LIGHT_PURPLE;
		}
	}

	private void GenerateYield() {
		CropYield = rand.nextInt(CropMaxYield + 1 - CropMinYield) + CropMinYield;
	}
	
	public void DropCrop(World world, Block location) {
		CreateGrade();
		GenerateYield();
		ItemStack stack = new ItemStack(CropItem, CropYield);
		ItemMeta meta = stack.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		
		lore.add(ChatColor.DARK_GRAY + "Grade: " + GradeColor + Grade);
		meta.setDisplayName(GradeColor + CropName);
		meta.setLore(lore);
		stack.setItemMeta(meta);
		
		world.dropItemNaturally(location.getLocation(), stack);
		if(!CropSeed.equals(null)) {
			world.dropItemNaturally(location.getLocation(), new ItemStack(CropSeed, 1));
		}
	}
}
