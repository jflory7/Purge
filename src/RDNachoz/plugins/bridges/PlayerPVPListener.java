/*    */ package RDNachoz.plugins.bridges;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*    */ 
/*    */ public class PlayerPVPListener
/*    */   implements Listener
/*    */ {
/*    */   private Bridges plugin;
/*    */ 
/*    */   public PlayerPVPListener(Bridges walls)
/*    */   {
/* 15 */     this.plugin = walls;
/*    */   }
/*    */   @EventHandler(priority=EventPriority.HIGH)
/*    */   public void onPlayerPVPEvent(EntityDamageByEntityEvent event) {
/* 19 */     if (((event.getEntity() instanceof Player)) && 
/* 20 */       ((event.getDamager() instanceof Player))) {
/* 21 */       Player player = (Player)event.getEntity();
/* 22 */       Player attacker = (Player)event.getDamager();
/* 23 */       if ((this.plugin.playerarena.get(player) != null) && 
/* 24 */         ((event.getCause() == EntityDamageEvent.DamageCause.POISON) || (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) || (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) || (event.getCause() == EntityDamageEvent.DamageCause.CONTACT)) && 
/* 25 */         (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().get(attacker) != null) && 
/* 26 */         (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().get(attacker) == ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().get(player)))
/* 27 */         event.setCancelled(true);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.PlayerPVPListener
 * JD-Core Version:    0.6.2
 */