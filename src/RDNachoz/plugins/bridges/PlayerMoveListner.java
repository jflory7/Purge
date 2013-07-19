/*    */ package RDNachoz.plugins.bridges;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
/*    */ 
/*    */ public class PlayerMoveListner
/*    */   implements Listener
/*    */ {
/*    */   private Bridges plugin;
/*    */ 
/*    */   public PlayerMoveListner(Bridges walls)
/*    */   {
/* 14 */     this.plugin = walls;
/*    */   }
/*    */ 
/*    */   @EventHandler(priority=EventPriority.HIGHEST)
/*    */   public void onPlayerMove(PlayerMoveEvent event) {
/* 19 */     Player player = event.getPlayer();
/* 20 */     if ((this.plugin.arenas.containsKey(this.plugin.playerarena.get(player))) && 
/* 21 */       (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getCounter() != null) && 
/* 22 */       (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getCounter().intValue() <= 9) && (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getCounter().intValue() >= 0) && (
/* 23 */       (((Location)((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerLocation().get(player)).getX() != player.getLocation().getX()) || (((Location)((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerLocation().get(player)).getZ() != player.getLocation().getZ()))) {
/* 24 */       this.plugin.teleportable.put(player, Boolean.valueOf(true));
/* 25 */       player.teleport((Location)((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerLocation().get(player));
/* 26 */       this.plugin.teleportable.put(player, Boolean.valueOf(false));
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.PlayerMoveListner
 * JD-Core Version:    0.6.2
 */