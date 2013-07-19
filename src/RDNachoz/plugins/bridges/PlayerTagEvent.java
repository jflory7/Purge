/*    */ package RDNachoz.plugins.bridges;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.kitteh.tag.PlayerReceiveNameTagEvent;
/*    */ 
/*    */ public class PlayerTagEvent
/*    */   implements Listener
/*    */ {
/*    */   private Bridges plugin;
/*    */ 
/*    */   public PlayerTagEvent(Bridges walls)
/*    */   {
/* 13 */     this.plugin = walls;
/*    */   }
/*    */ 
/*    */   @EventHandler
/*    */   public void onNameTag(PlayerReceiveNameTagEvent event) {
/* 18 */     if (this.plugin.playerarena.get(event.getPlayer()) != null)
/* 19 */       if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(event.getPlayer()))).getPlayerList().get(event.getNamedPlayer()) == null) {
/* 20 */         event.setTag(ChatColor.RESET + event.getNamedPlayer().getName());
/*    */       }
/* 22 */       else if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(event.getPlayer()))).getPlayerList().get(event.getNamedPlayer()) == "red") {
/* 23 */         event.setTag(ChatColor.RED + event.getNamedPlayer().getName());
/*    */       }
/* 25 */       else if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(event.getPlayer()))).getPlayerList().get(event.getNamedPlayer()) == "blue") {
/* 26 */         event.setTag(ChatColor.BLUE + event.getNamedPlayer().getName());
/*    */       }
/* 28 */       else if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(event.getPlayer()))).getPlayerList().get(event.getNamedPlayer()) == "green") {
/* 29 */         event.setTag(ChatColor.GREEN + event.getNamedPlayer().getName());
/*    */       }
/* 31 */       else if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(event.getPlayer()))).getPlayerList().get(event.getNamedPlayer()) == "yellow")
/* 32 */         event.setTag(ChatColor.YELLOW + event.getNamedPlayer().getName());
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.PlayerTagEvent
 * JD-Core Version:    0.6.2
 */