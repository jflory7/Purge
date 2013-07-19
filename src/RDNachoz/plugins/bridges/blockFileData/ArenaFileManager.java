/*    */ package RDNachoz.plugins.bridges.blockFileData;
/*    */ 
/*    */ import RDNachoz.plugins.bridges.Arena;
/*    */ import RDNachoz.plugins.bridges.Bridges;
/*    */ import RDNachoz.plugins.bridges.SignWall;
/*    */ import java.io.Serializable;
/*    */ import java.util.Map;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.scheduler.BukkitScheduler;
/*    */ 
/*    */ public class ArenaFileManager
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -3280620778831356769L;
/*    */   private static Bridges plugin;
/*    */ 
/*    */   public static void setPlugin(Bridges plugin)
/*    */   {
/* 18 */     plugin = plugin;
/*    */   }
/*    */ 
/*    */   public static void saveToAFile(String arenaName, Location loc1, Location loc2) {
/* 22 */     Integer minX = Integer.valueOf((int)Math.min(loc1.getX(), loc2.getX()));
/* 23 */     Integer minY = Integer.valueOf((int)Math.min(loc1.getY(), loc2.getY()));
/* 24 */     Integer minZ = Integer.valueOf((int)Math.min(loc1.getZ(), loc2.getZ()));
/* 25 */     new ArenaBlocksAndInfo(Integer.valueOf(loc1.getBlockX()), Integer.valueOf(loc1.getBlockY()), Integer.valueOf(loc1.getBlockZ()), Integer.valueOf(loc2.getBlockX()), Integer.valueOf(loc2.getBlockY()), Integer.valueOf(loc2.getBlockZ()), loc1.getWorld().getName(), arenaName.toLowerCase(), plugin, Integer.valueOf(minX.intValue()), Integer.valueOf(minY.intValue()), Integer.valueOf(minZ.intValue()), Integer.valueOf(1));
/*    */   }
/*    */ 
/*    */   public static void loadArenaFiles(String arenas, Integer num) {
/* 29 */     Arena arena = (Arena)plugin.arenas.get(arenas);
/* 30 */     arena.setResetting(true);
/* 31 */     for (Integer number : arena.getSignWalls().keySet()) {
/* 32 */       ((SignWall)arena.getSignWalls().get(number)).setProgress("Resetting");
/* 33 */       ((SignWall)arena.getSignWalls().get(number)).setTime(null);
/* 34 */       ((SignWall)arena.getSignWalls().get(number)).updateSigns(arena.getMin(), arena.getTotal(), arena.getPlayerList());
/*    */     }
/* 36 */     Bukkit.getServer().getScheduler().runTaskAsynchronously(plugin, new LoadDataRunnable(plugin, arenas, num));
/*    */   }
/*    */ 
/*    */   public static void setFilesForAnArena(Integer files, String arena) {
/* 40 */     ((Arena)plugin.arenas.get(arena)).setFiles(files);
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.blockFileData.ArenaFileManager
 * JD-Core Version:    0.6.2
 */