/*    */ package RDNachoz.plugins.bridges;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*    */ 
/*    */ public class PlayerFoodLevelChangeListner
/*    */   implements Listener
/*    */ {
/*    */   private Bridges plugin;
/*    */ 
/*    */   public PlayerFoodLevelChangeListner(Bridges walls)
/*    */   {
/* 13 */     this.plugin = walls;
/*    */   }
/*    */   @EventHandler
/*    */   public void onPlayerFoodChangeEvent(FoodLevelChangeEvent event) {
/* 17 */     if ((event.getEntity() instanceof Player)) {
/* 18 */       Player player = (Player)event.getEntity();
/* 19 */       if ((this.plugin.playerarena.get(player) != null) && (
/* 20 */         (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getCounter() == null) || (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getCounter().intValue() >= 0)))
/* 21 */         event.setCancelled(true);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.PlayerFoodLevelChangeListner
 * JD-Core Version:    0.6.2
 */