package jaredbgreat.dldungeons.configs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import jaredbgreat.dldungeons.Difficulty;
import jaredbgreat.dldungeons.Info;
import jaredbgreat.dldungeons.setup.Externalizer;
import jaredbgreat.dldungeons.themes.ThemeReader;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.registries.ForgeRegistries;

public class ConfigHandler {
	public static File configDir;
	private static File themesDir;
	private static File listsDir;
	private static File chestsDir;
	
	public static final int DEFAULT_SCALE  = 8;
	public static final int DEFAULT_MINXZ  = 16;
	public static final int DEFAULT_DIF    = 3;
	
	public static final int[] DEFAULT_DIMS = {0, -1};
	
	public static final boolean DEFAULT_WRITE_LISTS = false;
	public static final boolean DEFAULT_NATURAL_SPAWN = true;
	public static final boolean DEFAULT_OBEY_RULE = true;
	public static final boolean DEFAULT_POSITIVE_DIMS = true;
	public static final boolean DEFAULT_ANNOUNCE_COMMANDS = true;
	public static final boolean DEFAULT_THIN_SPAWNERS = true;
	
	// Vanilla loot will not be added in version of Mincraft 1.9+
	// Instead all dungeons will have some loot enchanted.
	public static final boolean DEFAULT_VANILLA_LOOT = false;	
	public static final boolean EASY_FIND = false;

	public static final boolean DISABLE_API = false;
	public static final boolean NO_MOB_CHANGES = false;
	
	public static boolean disableAPI = DISABLE_API;
	public static boolean noMobChanges = NO_MOB_CHANGES;
	
	public static final String[] NEVER_IN_BIOMES = new String[]{"END"};
	public static       String[] neverInBiomes   = NEVER_IN_BIOMES;
	public static HashSet<Type>  biomeExclusions = new HashSet<Type>();
	
	public static boolean writeLists = DEFAULT_WRITE_LISTS;	
	public static boolean naturalSpawn = DEFAULT_NATURAL_SPAWN;	
	public static boolean obeyRule = DEFAULT_OBEY_RULE;	
	public static boolean positiveDims = DEFAULT_POSITIVE_DIMS;
	
	public static boolean easyFind = EASY_FIND;
	
	public static boolean announceCommands = DEFAULT_ANNOUNCE_COMMANDS;
	public static boolean vanillaLoot = DEFAULT_VANILLA_LOOT;
	public static boolean thinSpawners = DEFAULT_THIN_SPAWNERS;	
		
	public static final boolean PROFILE = false;
	public static boolean profile;
	
	public static final boolean INSTALL_THEMES = true;
	public static       boolean installThemes = INSTALL_THEMES;
	
	public static final boolean INSTALL_CMD = true;
	public static       boolean installCmd = INSTALL_CMD;
	
	public static Difficulty difficulty;
	
	// All methods and data are static. 
	// There is no reason this should ever be instantiated.
	private ConfigHandler(){/*Do nothing*/}
	
	
	/**
	 * This will read the them file and apply the themes.
	 */
	public static void init() {
		// Get directories to use
		File[] dirs = MasterConfig.getUsedDirs();
		ThemeReader.setConfigDir(configDir = dirs[0]);
		ThemeReader.setThemesDir(themesDir = dirs[1]);
		listsDir  = dirs[2];
		chestsDir = dirs[3];
		
		// General configuration
		parseDiff(GeneralConfig.difficulty.get());
		openThemesDir();
	}
	
	
	/**
	 * This convert difficulty setting from an integer to a 
	 * Difficulty enum constant. 
	 * 
	 * @param diff
	 */
	public static void parseDiff(int diff) {
		switch(diff) {
			case 0:
				difficulty = Difficulty.NONE;
				break;
			case 1:
				difficulty = Difficulty.BABY;
				break;
			case 2:
				difficulty = Difficulty.NOOB;
				break;
			case 4:
				difficulty = Difficulty.HARD;
				break;
			case 5:
				difficulty = Difficulty.NUTS;
				break;
			case 3:
			default:
				difficulty = Difficulty.NORM;				
				break;
		}
	}
	
	

	/**
	 * This will output lists of blocks, items, and mobs know to the 
	 * game with their proper, unlocalized names.  This data is useful
	 * in editing theme files.
	 */
	public static void generateLists() {
		//if(!writeLists) return;
		listsDir = new File(configDir.toString() + File.separator + "lists");
		
		if(!listsDir.exists()) {
			listsDir.mkdir();
		} 		
		if(!listsDir.exists()) {
			System.out.println("[DLDUNGEONS] Warning: Could not create " + listsDir + ".");
		} else if (!listsDir.isDirectory()) {
			System.out.println("[DLDUNGEONS] Warning: " + listsDir 
					+ " is not a directory (folder); no themes loaded.");
		} else {		
			listMobs();
			listItems();
			listBlocks();
		}
	}
	
	
	/**
	 * This will list all mobs, using there unlocalized names, writing 
	 * the data to the file lists/mobs.txt.
	 */
	public static void listMobs() {	
		Set<ResourceLocation> mobrl = ForgeRegistries.ENTITIES.getKeys();
		ArrayList<String> mobs = new ArrayList<String>();
		for(ResourceLocation mob : mobrl) {
				mobs.add(mob.toString());
		}
		Collections.sort(mobs);
		
		BufferedWriter outstream = null;
		File moblist = new File(listsDir.toString() + File.separator + "entities.txt");
		if(moblist.exists()) moblist.delete(); 
		try {
			outstream = new BufferedWriter(new 
					FileWriter(moblist.toString()));			
			
			for(String mob : mobs){ 
				outstream.write(mob.toString());
				outstream.newLine();
			}
			
			if(outstream != null) outstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	/**
	 * This will list all items, using their complete unlocalized names 
	 * with mod id's, and write them the file lists/items.txt.  This 
	 * is useful for writing theme files.
	 */
	public static void listItems() {	
		BufferedWriter outstream = null;
		File itemlist = new File(listsDir.toString() + File.separator + "items.txt");
		if(itemlist.exists()) itemlist.delete(); 
		try {
			outstream = new BufferedWriter(new 
					FileWriter(itemlist.toString()));
			
			for(Item item : ForgeRegistries.ITEMS){ 
				String name = ForgeRegistries.ITEMS.getKey(item).toString();
				if(true) {
					outstream.write(name);
					outstream.newLine();
				}
			}
			
			if(outstream != null) outstream.close();
		} catch (IOException e) {
			System.err.println("[DLDUNGEONS] Error: Could not write file items.txt");
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * This will list all blocks using their correct, unlocalized names, complete with 
	 * mod id's, and write them to the file lists/blocks.txt.  This is useful for editing 
	 * theme files.
	 */
	public static void listBlocks() {	
		BufferedWriter outstream = null;
		File itemlist = new File(listsDir.toString() + File.separator + "blocks.txt");
		if(itemlist.exists()) itemlist.delete(); 
		try {
			outstream = new BufferedWriter(new 
					FileWriter(itemlist.toString()));	
			
			for(Block block : ForgeRegistries.BLOCKS){ 
				String name = ForgeRegistries.BLOCKS.getKey(block).toString();
				if(true) {;
					outstream.write(name);
					outstream.newLine();
				}
			}
			
			if(outstream != null) outstream.close();
		} catch (IOException e) {
			System.err.println("[DLDUNGEONS] Error: Could not write file blocks.txt");
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * This will open the theme's directory for some general housekeeping 
	 * purposes.  I does not read the theme files, as this called by init 
	 * during pre-init phase of mod loading, while themes are loaded 
	 * post-init to allow other mods a chance to load and register their 
	 * content.
	 */
	private static void openThemesDir() {
		Externalizer exporter;
		if(!themesDir.exists()) {
			themesDir.mkdir();
		} 		
		if(!themesDir.exists()) {
			System.out.println("[DLDUNGEONS] Warning: Could not create " + themesDir + ".");
		} else if (!themesDir.isDirectory()) {
			System.out.println("[DLDUNGEONS] Warning: " + themesDir 
					+ " is not a directory (folder); no themes loaded.");
		} else ThemeReader.setThemesDir(themesDir);
		File chests = new File(configDir.toString() + File.separator + "chest.cfg");
		if(!chests.exists()) {
			exporter = new Externalizer(configDir.toString() + File.separator);
			exporter.makeChestCfg();
		}
		File nbtconf = new File(configDir.toString() + File.separator + "nbt.cfg");
		if(!nbtconf.exists()) {
			exporter = new Externalizer(configDir.toString() + File.separator);
			exporter.makeNBTCfg();
		}
	}
	
	
	/**
	 * This will put biome types in the string array into the list of 
	 * types where no dungeons show everr generate.
	 * 
	 * @param array
	 */
	private static void processBiomeExclusions(String[] array) {
		for(String str : array) {
			str = str.toUpperCase();
			System.out.println("[DLDUNGEONS] adding " + str + " to excusion list");
			try { 
				Type value = Type.getType(str);
				if(value != null) {
					biomeExclusions.add(value);
				}
			} catch(Exception e) {
				System.err.println("[DLDUNGEONS] Error in config! " + str + " is not valid biome dictionary type!");
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * This will reload the config data; really just 
	 * wraps init.
	 */
	public static void reload() {
		init();
	}
}
