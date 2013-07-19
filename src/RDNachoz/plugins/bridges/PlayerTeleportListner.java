/*    */ package RDNachoz.plugins.bridges;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerTeleportEvent;
/*    */ 
/*    */ public class PlayerTeleportListner
/*    */   implements Listener
/*    */ {
/*    */   private Bridges plugin;
/*    */ 
/*    */   public PlayerTeleportListner(Bridges walls)
/*    */   {
/* 13 */     this.plugin = walls;
/*    */   }
/*    */   @EventHandler(priority=EventPriority.HIGHEST)
/*    */   public void playerTeleport(PlayerTeleportEvent event) {
/* 17 */     Player player = event.getPlayer();
/* 18 */     if ((this.plugin.playerarena.get(player) != null) && (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getCounter() != null) && (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getCounter().intValue() != 10) && 
/* 19 */       (!((Boolean)this.plugin.teleportable.get(player)).booleanValue()))
/* 20 */       event.setCancelled(true);
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.PlayerTeleportListner
 * JD-Core Version:    0.6.2
 */