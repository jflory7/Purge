/*    */ package RDNachoz.plugins.bridges.blockFileData;
/*    */ 
/*    */ import RDNachoz.plugins.bridges.Arena;
/*    */ import RDNachoz.plugins.bridges.Bridges;
/*    */ import RDNachoz.plugins.bridges.SignWall;
/*    */ import java.util.Map;
/*    */ import lucariatias.plugins.walls.ObjectFileLib;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.scheduler.BukkitScheduler;
/*    */ 
/*    */ public class LoadDataRunnable
/*    */   implements Runnable
/*    */ {
/*    */   private Bridges plugin;
/*    */   private String arena;
/*    */   private Integer fileNumber;
/*    */ 
/*    */   public LoadDataRunnable(Bridges plugin, String arena, Integer fileNumber)
/*    */   {
/* 16 */     this.arena = arena;
/* 17 */     this.plugin = plugin;
/* 18 */     this.fileNumber = fileNumber;
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/* 24 */     if (((Arena)this.plugin.arenas.get(this.arena)).getFiles().intValue() >= this.fileNumber.intValue()) {
/* 25 */       ArenaData ad = (ArenaData)ObjectFileLib.loadObject(this.plugin, this.arena + " Blocks" + this.fileNumber);
/*    */       try {
/* 27 */         Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new LoadDataManager(ad, this.plugin, this.arena));
/* 28 */         Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(this.plugin, new LoadDataRunnable(this.plugin, this.arena, Integer.valueOf(this.fileNumber.intValue() + 1)), 20L);
/*    */       } catch (Exception localException) {
/*    */       }
/*    */     }
/*    */     else {
/* 33 */       Arena arena = (Arena)this.plugin.arenas.get(this.arena);
/* 34 */       arena.setResetting(false);
/* 35 */       for (Integer number : arena.getSignWalls().keySet()) {
/* 36 */         ((SignWall)arena.getSignWalls().get(number)).setProgress("Waiting");
/* 37 */         ((SignWall)arena.getSignWalls().get(number)).setTime(null);
/* 38 */         ((SignWall)arena.getSignWalls().get(number)).updateSigns(arena.getMin(), arena.getTotal(), arena.getPlayerList());
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.blockFileData.LoadDataRunnable
 * JD-Core Version:    0.6.2
 */