/*    */ package RDNachoz.plugins.bridges.blockFileData;
/*    */ 
/*    */ import RDNachoz.plugins.bridges.Bridges;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.scheduler.BukkitScheduler;
/*    */ 
/*    */ public class CheckForFallingItems
/*    */   implements Runnable
/*    */ {
/*    */   private Bridges plugin;
/*    */   private Location loc;
/*    */ 
/*    */   public CheckForFallingItems(Bridges plugin, Location loc)
/*    */   {
/* 15 */     this.plugin = plugin;
/* 16 */     this.loc = loc;
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/* 21 */     if (this.loc.getBlock().getType() != Material.AIR) {
/* 22 */       this.loc.getBlock().setType(Material.AIR);
/* 23 */       Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new CheckForFallingItems(this.plugin, this.loc), 20L);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.blockFileData.CheckForFallingItems
 * JD-Core Version:    0.6.2
 */