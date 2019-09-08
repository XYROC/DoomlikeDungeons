package jaredbgreat.dldungeons.planner.features;


/* 
 * Doomlike Dungeons by is licensed the MIT License
 * Copyright (c) 2014-2018 Jared Blackburn
 */	


import jaredbgreat.dldungeons.planner.Dungeon;
import jaredbgreat.dldungeons.rooms.Room;
import jaredbgreat.dldungeons.themes.Degree;

/**
 * A chance to add a pillar / column (1x1 wall with a different block) 
 * into a room.  
 * 
 * @author Jared Blackburn
 *
 */
public class Pillar extends FeatureAdder {

	public Pillar(Degree chance) {
		super(chance);
	}
	

	@Override
	public void buildFeature(Dungeon dungeon, Room room) {
		int pillarx1 = dungeon.random.nextInt(room.endX - room.beginX - 2) + 1;
		int pillarz1 = dungeon.random.nextInt(room.endZ - room.beginZ - 2) + 1;
		if(room.sym.halfX) pillarx1 = ((pillarx1 - 1) / 2) + 1;
		if(room.sym.halfZ) pillarz1 = ((pillarz1 - 1) / 2) + 1;
		int pillarx2 = room.endX - pillarx1;
		int pillarz2 = room.endZ - pillarz1;
		pillarx1 += room.beginX;
		pillarz1 += room.beginZ;
		switch (room.sym) {
		case NONE: break;
		case TR1:
			dungeon.map.setIsWall(true, pillarx1, pillarz1);
			dungeon.map.setIsWall(true, pillarz1, pillarx1);
			dungeon.map.setWall(room.pillarBlock, pillarx1, pillarz1);
			dungeon.map.setWall(room.pillarBlock, pillarz1, pillarx1);
			break;
		case TR2:
			dungeon.map.setIsWall(true, pillarx1, pillarz1);
			dungeon.map.setIsWall(true, pillarz1, pillarx1);
			dungeon.map.setWall(room.pillarBlock, pillarx2, pillarz1);
			dungeon.map.setWall(room.pillarBlock, pillarz2, pillarx1);
			break;
		case X:
			dungeon.map.setIsWall(true, pillarx1, pillarz1);
			dungeon.map.setIsWall(true, pillarx2, pillarz1);
			dungeon.map.setWall(room.pillarBlock, pillarx1, pillarz1);
			dungeon.map.setWall(room.pillarBlock, pillarx2, pillarz1);
			break;
		case Z:
			dungeon.map.setIsWall(true, pillarx1, pillarz1);
			dungeon.map.setIsWall(true, pillarx1, pillarz2);
			dungeon.map.setWall(room.pillarBlock, pillarx1, pillarz1);
			dungeon.map.setWall(room.pillarBlock, pillarx1, pillarz2);
			break;
		case XZ:
			dungeon.map.setIsWall(true, pillarx1, pillarz1);
			dungeon.map.setIsWall(true, pillarx1, pillarz2);
			dungeon.map.setIsWall(true, pillarx2, pillarz1);
			dungeon.map.setIsWall(true, pillarx2, pillarz2);
			dungeon.map.setWall(room.pillarBlock, pillarx1, pillarz1);
			dungeon.map.setWall(room.pillarBlock, pillarx1, pillarz2);
			dungeon.map.setWall(room.pillarBlock, pillarx2, pillarz1);
			dungeon.map.setWall(room.pillarBlock, pillarx2, pillarz2);
			break;
		case R:
			dungeon.map.setIsWall(true, pillarx1, pillarz1);
			dungeon.map.setIsWall(true, pillarx2, pillarz2);
			dungeon.map.setWall(room.pillarBlock, pillarx1, pillarz1);
			dungeon.map.setWall(room.pillarBlock, pillarx2, pillarz2);
			break;
		case SW:
			dungeon.map.setIsWall(true, pillarx1, pillarz1);
			dungeon.map.setIsWall(true, pillarx1, pillarz2);
			dungeon.map.setIsWall(true, pillarx2, pillarz1);
			dungeon.map.setIsWall(true, pillarx2, pillarz2);
			dungeon.map.setWall(room.pillarBlock, pillarx1, pillarz1);
			dungeon.map.setWall(room.pillarBlock, pillarx1, pillarz2);
			dungeon.map.setWall(room.pillarBlock, pillarx2, pillarz1);
			dungeon.map.setWall(room.pillarBlock, pillarx2, pillarz2);
		}
	}
}