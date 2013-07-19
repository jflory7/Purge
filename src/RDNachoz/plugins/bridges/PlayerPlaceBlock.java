/*    */ package RDNachoz.plugins.bridges;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockPlaceEvent;
/*    */ 
/*    */ public class PlayerPlaceBlock
/*    */   implements Listener
/*    */ {
/*    */   private Bridges plugin;
/*    */ 
/*    */   public PlayerPlaceBlock(Bridges walls)
/*    */   {
/* 14 */     this.plugin = walls;
/*    */   }
/*    */   @EventHandler
/*    */   public void onPlayerPlaceBlockEvent(BlockPlaceEvent event) {
/* 18 */     Player player = event.getPlayer();
/* 19 */     if (this.plugin.playerarena.get(player) != null) {
/* 20 */       boolean brek = true;
/* 21 */       Iterator itr = ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getSaveregion().keySet().iterator();
/* 22 */       while (itr.hasNext()) {
/* 23 */         if (itr != null) {
/* 24 */           Integer num = (Integer)itr.next();
/* 25 */           if ((event.getBlock().getLocation().getX() <= ((Location)((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getSaveregion().get(num)).getX()) && 
/* 26 */             (event.getBlock().getLocation().getY() <= ((Location)((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getSaveregion().get(num)).getY()) && 
/* 27 */             (event.getBlock().getLocation().getZ() <= ((Location)((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getSaveregion().get(num)).getZ()) && 
/* 28 */             (event.getBlock().getLocation().getX() >= ((Location)((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getSaveregion1().get(num)).getX()) && 
/* 29 */             (event.getBlock().getLocation().getY() >= ((Location)((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getSaveregion1().get(num)).getY()) && 
/* 30 */             (event.getBlock().getLocation().getZ() >= ((Location)((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getSaveregion1().get(num)).getZ())) {
/* 31 */             brek = false;
/*    */           }
/*    */         }
/*    */       }
/* 35 */       event.setCancelled(brek);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.PlayerPlaceBlock
 * JD-Core Version:    0.6.2
 */