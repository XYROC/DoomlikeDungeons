package jaredbgreat.dldungeons.nbt;

/* 
 * This mod is the creation and copyright (c) 2015 
 * of Jared Blackburn (JaredBGreat).
 * 
 * It is licensed under the creative commons 4.0 attribution license:
 * https://creativecommons.org/licenses/by/4.0/legalcode
*/	

import jaredbgreat.dldungeons.nbt.tags.ITag;
import jaredbgreat.dldungeons.nbt.tags.Tags;
import net.minecraft.item.ItemStack;

/**
 * This class should contain helper functions to deal with applying NBT 
 * tags to items.  Hopefully this will fix the potions, which were broken
 * when Minecraft 1.9 switched from coding potion effects as damage values 
 * to NBT, though I suspect it will be useful for other things as well.
 * 
 * Whether or not I might try to extend the use of this for other things 
 * is not yet determined as of starting this class (29 December 2016).
 * 
 * @author JaredBGreat (Jared Blackburn)
 */

public class NbtHelper {
	
	/**
	 * This will take a an NBT tag (stored as an ITag object) and apply it
	 * to the item stack.
	 * 
	 * I'm not yet sure if this is the best approach; this may very well
	 * change as I learn more about the use of NBT.
	 * 
	 * @param item
	 * @param tags
	 */
	public static void setNbtTag(ItemStack item, ITag tag) {
		tag.write(item.getTagCompound());
	}
	
	
	/**
	 * This will take a string and parse it into a tag and apply it to the
	 * item stack.
	 * 
	 * @param item
	 * @param tags
	 */
	public static void setNbtTag(ItemStack item, String label) {
		Tags.registry.get(label).write(item.getTagCompound());
	}
	
	
	/**
	 * Will take a tag label and return the corresponding NBT tag as an ITag
	 * object.
	 * 
	 * @param label
	 * @return
	 */
	public static ITag getTagFromLabel(String label) {
		return Tags.registry.get(label);
	}
	
	
	
}