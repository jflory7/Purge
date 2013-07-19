/*    */ package RDNachoz.plugins.bridges;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ 
/*    */ public class PlayerBreakBlockEvent
/*    */   implements Listener
/*    */ {
/*    */   private Bridges plugin;
/*    */ 
/*    */   public PlayerBreakBlockEvent(Bridges bridges)
/*    */   {
/* 14 */     this.plugin = bridges;
/*    */   }
/*    */   @EventHandler(priority=EventPriority.HIGH)
/*    */   public void onPlayerBreakBlockEvent(BlockBreakEvent event) {
/* 18 */     Player player = event.getPlayer();
/* 19 */     if (this.plugin.playerarena.get(player) != null)
/* 20 */       if ((((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getCounter() == null) || (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getCounter().intValue() > 0)) {
/* 21 */         event.setCancelled(true);
/*    */       }
/*    */       else {
/* 24 */         boolean brek = true;
/* 25 */         Iterator itr = ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getSaveregion().keySet().iterator();
/* 26 */         while (itr.hasNext()) {
/* 27 */           if (itr != null) {
/* 28 */             Integer num = (Integer)itr.next();
/* 29 */             if ((event.getBlock().getLocation().getX() <= ((Location)((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getSaveregion().get(num)).getX()) && 
/* 30 */               (event.getBlock().getLocation().getY() <= ((Location)((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getSaveregion().get(num)).getY()) && 
/* 31 */               (event.getBlock().getLocation().getZ() <= ((Location)((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getSaveregion().get(num)).getZ()) && 
/* 32 */               (event.getBlock().getLocation().getX() >= ((Location)((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getSaveregion1().get(num)).getX()) && 
/* 33 */               (event.getBlock().getLocation().getY() >= ((Location)((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getSaveregion1().get(num)).getY()) && 
/* 34 */               (event.getBlock().getLocation().getZ() >= ((Location)((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getSaveregion1().get(num)).getZ()))
/*    */             {
/* 36 */               brek = false;
/*    */             }
/*    */           }
/*    */         }
/* 40 */         event.setCancelled(brek);
/*    */       }
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.PlayerBreakBlockEvent
 * JD-Core Version:    0.6.2
 */