package jaredbgreat.dldungeons.tputils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class Teleportation extends Teleporter {
	private final WorldServer world;
	private double x, y, z;

	public Teleportation(WorldServer world, double x, double y, double z) {
		super(world);
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	@Override
	public void placeInPortal(Entity entity, float rotationYaw) {
		world.getBlockState(new BlockPos((int)x, (int)y, (int)z));
		entity.setPosition(x, y, z);
		entity.motionX = 0.0;
		entity.motionY = 0.0;
		entity.motionZ = 0.0;		
	}
	
	
	public static void tpToDimension(EntityPlayer player, int dimension, 
			double x, double y, double z) {
		int startDimension = player.getEntityWorld().provider.getDimension();
		EntityPlayerMP playerMP = (EntityPlayerMP)player;		
		MinecraftServer server = player.getEntityWorld().getMinecraftServer();
		WorldServer worldServer = server.getWorld(dimension);
		if((worldServer == null) || (server == null)) {
			throw new IllegalArgumentException("ERROR: Trying to teleport to non-existent dimension " 
					+ dimension + "!");
//			System.err.println("ERROR: Trying to teleport to non-existent dimension " 
//								+ dimension + "!");
//			return;
		}
		worldServer.getMinecraftServer().getPlayerList()
			.transferPlayerToDimension(playerMP, dimension, new Teleportation(worldServer, x, y, z));
		player.setPositionAndUpdate(x, y, z);
	}
	
	
	public static void tpToDimension(EntityPlayer player, int dimension, 
			int ix, int iy, int iz) {
		double x = ix + 0.5, y = iy + 0.5, z = iz + 0.5;
		int startDimension = player.getEntityWorld().provider.getDimension();
		EntityPlayerMP playerMP = (EntityPlayerMP)player;		
		MinecraftServer server = player.getEntityWorld().getMinecraftServer();
		WorldServer worldServer = server.getWorld(dimension);
		if((worldServer == null) || (server == null)) {
			throw new IllegalArgumentException("ERROR: Trying to teleport to non-existent dimension " 
					+ dimension + "!");
//			System.err.println("ERROR: Trying to teleport to non-existent dimension " 
//								+ dimension + "!");
//			return;
		}
		worldServer.getMinecraftServer().getPlayerList()
			.transferPlayerToDimension(playerMP, dimension, new Teleportation(worldServer, x, y, z));
		player.setPositionAndUpdate(x, y, z);
	}

}
