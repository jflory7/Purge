/*    */ package RDNachoz.plugins.bridges;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/*    */ 
/*    */ public class PlayerCommandListner
/*    */   implements Listener
/*    */ {
/*    */   private Bridges plugin;
/* 16 */   boolean blocked = false;
/*    */ 
/*    */   public PlayerCommandListner(Bridges bridges)
/*    */   {
/* 14 */     this.plugin = bridges;
/*    */   }
/*    */ 
/*    */   @EventHandler(priority=EventPriority.HIGHEST)
/*    */   public void onPlayerSendCommandEvent(PlayerCommandPreprocessEvent event) {
/* 19 */     Player player = event.getPlayer();
/* 20 */     if (this.plugin.playerarena.get(player) != null) {
/* 21 */       String[] message = event.getMessage().split(" ", 2);
/* 22 */       String command = message[0].toLowerCase();
/* 23 */       this.plugin.allowedCommands.add("/bb");
/* 24 */       if (!this.plugin.allowedCommands.contains(command)) {
/* 25 */         event.setCancelled(true);
/* 26 */         player.sendMessage(ChatColor.RED + "That command is blocked in BukkitBridges!");
/*    */       }
/* 28 */       this.plugin.allowedCommands.remove("/bb");
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.PlayerCommandListner
 * JD-Core Version:    0.6.2
 */