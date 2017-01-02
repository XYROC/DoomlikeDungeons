package jaredbgreat.dldungeons.nbt.tags;

/* 
 * This mod is the creation and copyright (c) 2015 
 * of Jared Blackburn (JaredBGreat).
 * 
 * It is licensed under the creative commons 4.0 attribution license: 
 * https://creativecommons.org/licenses/by/4.0/legalcode
*/		


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import net.minecraft.nbt.NBTTagCompound;

public class NBTCompound extends ITag {
	public final List<ITag> data;  // The data carried by the tag in the NBT
	
	
	/**
	 * The constructor for use with text data.
	 * 
	 * @param label
	 * @param name
	 * @param data
	 */
	NBTCompound(String label, String name, String data) {
		super(label, name);
		this.data  = new ArrayList<ITag>();
		parseData(data);
	}

	@Override
	public void write(NBTTagCompound cmp) {
		NBTTagCompound out = cmp.getCompoundTag(name);
		for(ITag child : data) {
			child.write(out);
		}
	}
	
	
	private void parseData(String in) {
		StringTokenizer tokens = new StringTokenizer(in, ",");
		while(tokens.hasMoreTokens()) {
			data.add(Tags.registry.get(tokens.nextToken()));
		}
	}
}