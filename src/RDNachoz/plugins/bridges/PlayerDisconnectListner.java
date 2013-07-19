/*    */ package RDNachoz.plugins.bridges;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ import org.kitteh.tag.TagAPI;
/*    */ 
/*    */ public class PlayerDisconnectListner
/*    */   implements Listener
/*    */ {
/*    */   private Bridges plugin;
/*    */ 
/*    */   public PlayerDisconnectListner(Bridges walls)
/*    */   {
/* 15 */     this.plugin = walls;
/*    */   }
/*    */   @EventHandler
/*    */   public void OnPlayerDisconnect(PlayerQuitEvent event) {
/* 19 */     Player player = event.getPlayer();
/* 20 */     String arena = (String)this.plugin.playerarena.get(player);
/* 21 */     if (this.plugin.arenas.containsKey(this.plugin.playerarena.get(player))) {
/* 22 */       this.plugin.teleportable.put(player, Boolean.valueOf(true));
/* 23 */       if ((((Arena)this.plugin.arenas.get(arena)).getCounter() != null) && (((Arena)this.plugin.arenas.get(arena)).getCounter().intValue() < 10)) {
/* 24 */         Iterator list = ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().keySet().iterator();
/* 25 */         while (list.hasNext()) {
/* 26 */           Player name = (Player)list.next();
/* 27 */           if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().get(player) == "red") {
/* 28 */             name.sendMessage(ChatColor.RED + player.getName() + ChatColor.AQUA + " has left the arena");
/*    */           }
/* 30 */           else if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().get(player) == "blue") {
/* 31 */             name.sendMessage(ChatColor.BLUE + player.getName() + ChatColor.AQUA + " has left the arena");
/*    */           }
/* 33 */           else if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().get(player) == "green") {
/* 34 */             name.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.AQUA + " has left the arena");
/*    */           }
/* 36 */           else if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().get(player) == "yellow") {
/* 37 */             name.sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.AQUA + " has left the arena!");
/*    */           }
/*    */         }
/* 40 */         TagAPI.refreshPlayer(player);
/* 41 */         ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).setRandom(player);
/* 42 */         ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).Leave();
/* 43 */         ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().remove(player);
/* 44 */         player.setHealth(20);
/* 45 */         player.setFoodLevel(20);
/* 46 */         player.setFireTicks(0);
/* 47 */         if ((((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getTotal() == ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getRedplayers()) || (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getTotal() == ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getBlueplayers()) || (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getTotal() == ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getGreenplayers()) || (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getTotal() == ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getYellowplayers())) {
/* 48 */           Arena arenas = (Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player));
/* 49 */           arenas.broadcastWinner();
/* 50 */           for (Player play : ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().keySet()) {
/* 51 */             this.plugin.teleportable.put(play, Boolean.valueOf(true));
/* 52 */             arenas.setRandom(play);
/* 53 */             arenas.Leave();
/* 54 */             this.plugin.playerarena.remove(play);
/* 55 */             play.teleport(arenas.getWin());
/* 56 */             play.setHealth(20);
/* 57 */             play.setFoodLevel(20);
/* 58 */             play.setFallDistance(0.0F);
/* 59 */             play.setFireTicks(0);
/*    */           }
/* 61 */           arenas.getPlayerList().clear();
/* 62 */           ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).setCountToEnd(Integer.valueOf(0));
/*    */         }
/* 64 */         this.plugin.playerarena.remove(player);
/*    */       }
/*    */       else {
/* 67 */         ((Arena)this.plugin.arenas.get(arena)).getLobbyPlayers().remove(player);
/* 68 */         String arena2 = (String)this.plugin.playerarena.get(player);
/* 69 */         this.plugin.playerarena.remove(player);
/* 70 */         ((Arena)this.plugin.arenas.get(arena)).setRandom(player);
/* 71 */         ((Arena)this.plugin.arenas.get(arena)).Leave();
/* 72 */         player.teleport(((Arena)this.plugin.arenas.get(arena2)).getLose());
/* 73 */         ((Arena)this.plugin.arenas.get(arena2)).getPlayerList().remove(player);
/* 74 */         player.setHealth(20);
/* 75 */         player.setFoodLevel(20);
/* 76 */         player.setFireTicks(0);
/* 77 */         Iterator list = ((Arena)this.plugin.arenas.get(arena)).getPlayerList().keySet().iterator();
/* 78 */         if (list != null) {
/* 79 */           while (list.hasNext()) {
/* 80 */             Player name = (Player)list.next();
/* 81 */             if (name != null) {
/* 82 */               name.sendMessage(ChatColor.AQUA + player.getName() + " has left the lobby!");
/*    */             }
/*    */           }
/*    */         }
/* 86 */         if (((Arena)this.plugin.arenas.get(arena)).getTotal().intValue() <= 1) {
/* 87 */           ((Arena)this.plugin.arenas.get(arena)).setCounter(null);
/* 88 */           Iterator list2 = ((Arena)this.plugin.arenas.get(arena)).getPlayerList().keySet().iterator();
/* 89 */           if (list2 != null) {
/* 90 */             while (list2.hasNext()) {
/* 91 */               Player names = (Player)list2.next();
/* 92 */               names.sendMessage(ChatColor.RED + "Game stopped because there are not enough people");
/*    */             }
/*    */           }
/*    */         }
/* 96 */         player.teleport(((Arena)this.plugin.arenas.get(arena)).getLose());
/* 97 */         ((Arena)this.plugin.arenas.get(arena)).playerJoin();
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.PlayerDisconnectListner
 * JD-Core Version:    0.6.2
 */