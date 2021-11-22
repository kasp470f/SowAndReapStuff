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
	
	public ChatColor QualityColor;
	public String QualityName;
	
	private static Random rand = new Random();
	
	// Potato and Carrot
	public Crop(Material block, Material item) {
		CropBlock = block;
		CropItem = item;
		
		String[] nameOfItem = item.name().split("_");
		for(int i = 0; i < nameOfItem.length; i++) {
		 	nameOfItem[i] = nameOfItem[i].substring(0, 1).toUpperCase() + nameOfItem[i].substring(1).toLowerCase();
		}
		CropName = String.join(" ", nameOfItem);
		
	}
	
	// Wheat and Beetroots
	public Crop(Material block, Material item, Material seed) {
		CropBlock = block;
		CropItem = item;
		CropSeed = seed;
		
		CropName = item.name().substring(0, 1).toUpperCase() + item.name().substring(1).toLowerCase();
	}
	
	public void SetYield(int minYield, int maxYield) {
		CropMaxYield = maxYield;
		CropMinYield = minYield;
	}
	
	public boolean ReadyForHarvest(Block cropBlock) {
		if(cropBlock.getType() == Material.PUMPKIN || cropBlock.getType() == Material.MELON) {
			return true;
		} else {
			Ageable ageable = (Ageable) cropBlock.getBlockData();
			if (ageable.getAge() == ageable.getMaximumAge()) {
				return true;
			}
		}
		return false;
	}
	
	public void DropCrop(World world, Block location) {
		GenerateYield();
		ItemStack[] stacks = new ItemStack[CropYield];
		for (ItemStack stack : stacks) {
			CreateGrade();
			stack = new ItemStack(CropItem, 1);
			ItemMeta meta = stack.getItemMeta();
			ArrayList<String> lore = new ArrayList<String>();
			
			lore.add(ChatColor.DARK_GRAY + "Grade: " + QualityColor + QualityName);
			meta.setDisplayName(QualityColor + CropName);
			meta.setLore(lore);
			stack.setItemMeta(meta);
			
			world.dropItemNaturally(location.getLocation(), stack);
			if(CropSeed != null) {
				world.dropItemNaturally(location.getLocation(), new ItemStack(CropSeed, 1));
			}
		}
	}
	
	private void CreateGrade() {
		int percentage = rand.nextInt(1000);
		if (percentage <= 500) {
			QualityName = "Poor Quality";
			QualityColor = ChatColor.GRAY;
		} else if (percentage <= 650) {
			QualityName = "Decent Quality";
			QualityColor = ChatColor.GREEN;
		} else if (percentage <= 750) {
			QualityName = "Good Quality";
			QualityColor = ChatColor.DARK_AQUA;
		} else if (percentage <= 900) {
			QualityName = "Very Good Quality";
			QualityColor = ChatColor.BLUE;
		} else if (percentage <= 980) {
			QualityName = "Excellent Quality";
			QualityColor = ChatColor.LIGHT_PURPLE;
		} else if (percentage <= 998){
			QualityName = "Perfect Quality";
			QualityColor = ChatColor.GOLD;
		} else {
			QualityName = "Ancient";
			QualityColor = ChatColor.MAGIC;
		}
	}

	private void GenerateYield() {
		CropYield = rand.nextInt(CropMaxYield + 1 - CropMinYield) + CropMinYield;
	}
}
