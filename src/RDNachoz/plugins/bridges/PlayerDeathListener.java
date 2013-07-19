/*    */ package RDNachoz.plugins.bridges;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ import org.kitteh.tag.TagAPI;
/*    */ 
/*    */ public class PlayerDeathListener
/*    */   implements Listener
/*    */ {
/*    */   private Bridges plugin;
/*    */ 
/*    */   public PlayerDeathListener(Bridges walls)
/*    */   {
/* 20 */     this.plugin = walls;
/*    */   }
/*    */   @EventHandler(priority=EventPriority.HIGHEST)
/*    */   public void PlayerDeathEvent(EntityDamageEvent event) {
/* 24 */     if ((event.getEntity() instanceof Player)) {
/* 25 */       Player player = (Player)event.getEntity();
/* 26 */       if ((this.plugin.playerarena.get(player) != null) && 
/* 27 */         (player.getHealth() <= event.getDamage()) && (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getStarted().booleanValue()) && (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getCounter().intValue() <= 0))
/*    */       {
/*    */         Location loc;
/* 28 */         if (this.plugin.arenas.containsKey(this.plugin.playerarena.get(player))) {
/* 29 */           this.plugin.teleportable.put(player, Boolean.valueOf(true));
/* 30 */           Iterator list = ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().keySet().iterator();
/* 31 */           while (list.hasNext()) {
/* 32 */             Player name = (Player)list.next();
/* 33 */             if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().get(player) == "red") {
/* 34 */               name.sendMessage(ChatColor.RED + player.getName() + ChatColor.AQUA + " has died!");
/*    */             }
/* 36 */             else if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().get(player) == "blue") {
/* 37 */               name.sendMessage(ChatColor.BLUE + player.getName() + ChatColor.AQUA + " has died!");
/*    */             }
/* 39 */             else if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().get(player) == "green") {
/* 40 */               name.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.AQUA + " has died!");
/*    */             }
/* 42 */             else if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().get(player) == "yellow") {
/* 43 */               name.sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.AQUA + " has died!");
/*    */             }
/*    */           }
/* 46 */           PlayerInventory inv = player.getInventory();
/* 47 */           loc = player.getLocation();
/* 48 */           for (ItemStack i : inv.getContents()) {
/* 49 */             if (i != null) {
/* 50 */               loc.getWorld().dropItemNaturally(loc, i);
/*    */             }
/*    */           }
/* 53 */           if (player.getInventory().getHelmet() != null) {
/* 54 */             loc.getWorld().dropItemNaturally(loc, player.getInventory().getHelmet());
/*    */           }
/* 56 */           if (player.getInventory().getChestplate() != null) {
/* 57 */             loc.getWorld().dropItemNaturally(loc, player.getInventory().getChestplate());
/*    */           }
/* 59 */           if (player.getInventory().getLeggings() != null) {
/* 60 */             loc.getWorld().dropItemNaturally(loc, player.getInventory().getLeggings());
/*    */           }
/* 62 */           if (player.getInventory().getBoots() != null) {
/* 63 */             loc.getWorld().dropItemNaturally(loc, player.getInventory().getBoots());
/*    */           }
/* 65 */           TagAPI.refreshPlayer(player);
/* 66 */           ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).setRandom(player);
/* 67 */           ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).Leave();
/* 68 */           ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().remove(player);
/* 69 */           player.teleport(((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getLose());
/* 70 */           event.setCancelled(true);
/* 71 */           player.setHealth(20);
/* 72 */           player.setFoodLevel(20);
/* 73 */           player.setFireTicks(0);
/* 74 */           ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).playerJoin();
/*    */         }
/* 76 */         if ((((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getTotal() == ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getRedplayers()) || (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getTotal() == ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getBlueplayers()) || (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getTotal() == ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getGreenplayers()) || (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getTotal() == ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getYellowplayers())) {
/* 77 */           Arena arena = (Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player));
/* 78 */           arena.broadcastWinner();
/* 79 */           for (Player play : ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().keySet()) {
/* 80 */             this.plugin.teleportable.put(play, Boolean.valueOf(true));
/* 81 */             arena.setRandom(play);
/* 82 */             arena.Leave();
/* 83 */             this.plugin.playerarena.remove(play);
/* 84 */             play.teleport(arena.getWin());
/* 85 */             play.setHealth(20);
/* 86 */             play.setFoodLevel(20);
/* 87 */             play.setFallDistance(0.0F);
/* 88 */             play.setFireTicks(0);
/*    */           }
/* 90 */           arena.getPlayerList().clear();
/* 91 */           ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).setCountToEnd(Integer.valueOf(0));
/*    */         }
/* 93 */         this.plugin.playerarena.remove(player);
/*    */       }
/*    */ 
/* 96 */       if ((this.plugin.arenas.containsKey(this.plugin.playerarena.get(player))) && (
/* 97 */         (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getCounter() == null) || (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getCounter().intValue() >= 0)))
/* 98 */         event.setCancelled(true);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.PlayerDeathListener
 * JD-Core Version:    0.6.2
 */