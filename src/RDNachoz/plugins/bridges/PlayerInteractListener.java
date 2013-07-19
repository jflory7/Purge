/*    */ package RDNachoz.plugins.bridges;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ 
/*    */ public class PlayerInteractListener
/*    */   implements Listener
/*    */ {
/*    */   private Bridges plugin;
/*    */ 
/*    */   public PlayerInteractListener(Bridges walls)
/*    */   {
/* 19 */     this.plugin = walls;
/*    */   }
/*    */ 
/*    */   @EventHandler(priority=EventPriority.HIGHEST)
/*    */   public void PlayerInteractEvent(PlayerInteractEvent event)
/*    */   {
/* 25 */     if ((this.plugin.playerarena.get(event.getPlayer()) == null) && 
/* 26 */       (event.getPlayer().getItemInHand().getType().equals(Material.CLAY_BRICK)) && 
/* 27 */       (event.getPlayer().hasPermission("bb.create"))) {
/* 28 */       if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
/* 29 */         event.setCancelled(true);
/* 30 */         this.plugin.location1.put(event.getPlayer(), event.getClickedBlock().getLocation());
/* 31 */         event.getPlayer().sendMessage(ChatColor.DARK_GREEN + "Point 1 set!");
/*    */       }
/* 33 */       else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
/* 34 */         event.setCancelled(true);
/* 35 */         this.plugin.location2.put(event.getPlayer(), event.getClickedBlock().getLocation());
/* 36 */         event.getPlayer().sendMessage(ChatColor.DARK_GREEN + "Point 2 set!");
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 41 */     if (((event.getAction() == Action.LEFT_CLICK_BLOCK) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
/* 42 */       (event.getClickedBlock().getType() == Material.WALL_SIGN)) {
/* 43 */       boolean aSign = false;
/* 44 */       String arenaName = null;
/* 45 */       Integer thing = Integer.valueOf(0);
/* 46 */       Iterator arenas = this.plugin.arenas.keySet().iterator();
/*    */       Iterator wallsSigns;
/* 47 */       for (; (arenas.hasNext()) && (!aSign); 
/* 50 */         (wallsSigns.hasNext()) && (!aSign))
/*    */       {
/* 48 */         String arena = (String)arenas.next();
/* 49 */         wallsSigns = ((Arena)this.plugin.arenas.get(arena)).getSignWalls().keySet().iterator();
/* 50 */         continue;
/* 51 */         Integer num = (Integer)wallsSigns.next();
/* 52 */         if ((event.getClickedBlock().getLocation().getX() == ((SignWall)((Arena)this.plugin.arenas.get(arena)).getSignWalls().get(num)).getClickSign().getX()) && 
/* 53 */           (event.getClickedBlock().getLocation().getY() == ((SignWall)((Arena)this.plugin.arenas.get(arena)).getSignWalls().get(num)).getClickSign().getY()) && 
/* 54 */           (event.getClickedBlock().getLocation().getZ() == ((SignWall)((Arena)this.plugin.arenas.get(arena)).getSignWalls().get(num)).getClickSign().getZ())) {
/* 55 */           aSign = true;
/* 56 */           thing = num;
/* 57 */           arenaName = arena;
/*    */         }
/*    */ 
/*    */       }
/*    */ 
/* 63 */       if (aSign) {
/* 64 */         Player player = event.getPlayer();
/* 65 */         event.setCancelled(true);
/* 66 */         if ((event.getPlayer().getItemInHand().getType() == Material.CLAY_BRICK) && (event.getPlayer().hasPermission("bb.signwall"))) {
/* 67 */           ((Arena)this.plugin.arenas.get(arenaName)).getSignWalls().remove(thing);
/* 68 */           event.getPlayer().sendMessage(ChatColor.RED + "Sign Walls has been removed!");
/*    */         }
/* 70 */         else if (player.hasPermission("bb.signjoin")) {
/* 71 */           if (this.plugin.playerarena.get(player) == null) {
/* 72 */             if (!((Arena)this.plugin.arenas.get(arenaName)).getStarted().booleanValue()) {
/* 73 */               if ((((Arena)this.plugin.arenas.get(arenaName)).getLose() != null) && (((Arena)this.plugin.arenas.get(arenaName)).getWin() != null) && (((Arena)this.plugin.arenas.get(arenaName)).getLobby() != null)) {
/* 74 */                 if (((Arena)this.plugin.arenas.get(arenaName)).getMin().intValue() > 0) {
/* 75 */                   if (((Arena)this.plugin.arenas.get(arenaName)).getDropTime() != null) {
/* 76 */                     Integer num = Integer.valueOf(((Arena)this.plugin.arenas.get(arenaName)).getMin().intValue() + ((Arena)this.plugin.arenas.get(arenaName)).getMin().intValue() + ((Arena)this.plugin.arenas.get(arenaName)).getMin().intValue() + ((Arena)this.plugin.arenas.get(arenaName)).getMin().intValue());
/* 77 */                     if (((Arena)this.plugin.arenas.get(arenaName)).getTotal().intValue() < num.intValue()) {
/* 78 */                       player.teleport(((Arena)this.plugin.arenas.get(arenaName)).getLobby());
/* 79 */                       this.plugin.playerarena.put(player, arenaName);
/* 80 */                       this.plugin.teleportable.put(player, Boolean.valueOf(false));
/* 81 */                       ((Arena)this.plugin.arenas.get(arenaName)).setTotal(Integer.valueOf(((Arena)this.plugin.arenas.get(arenaName)).getTotal().intValue() + 1));
/* 82 */                       player.sendMessage(ChatColor.GRAY + "Joined arena " + arenaName + "!");
/* 83 */                       ((Arena)this.plugin.arenas.get(arenaName)).getPlayerList().put(player, "lobby");
/* 84 */                       ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().put(player, "lobby");
/* 85 */                       ((Arena)this.plugin.arenas.get(arenaName)).getPlayerInventories().put(player, player.getInventory().getContents());
/* 86 */                       ((Arena)this.plugin.arenas.get(arenaName)).getAurmor().put(player, player.getInventory().getArmorContents());
/* 87 */                       Integer tempExp = Integer.valueOf(player.getLevel());
/* 88 */                       ((Arena)this.plugin.arenas.get(arenaName)).getExp().put(player, tempExp);
/* 89 */                       player.getInventory().clear();
/* 90 */                       player.getInventory().setArmorContents(null);
/* 91 */                       player.updateInventory();
/* 92 */                       player.setHealth(20);
/* 93 */                       player.setFoodLevel(20);
/* 94 */                       ((Arena)this.plugin.arenas.get(arenaName)).getLobbyPlayers().add(player);
/* 95 */                       player.setLevel(0);
/* 96 */                       player.setExp(0.0F);
/* 97 */                       player.setGameMode(GameMode.ADVENTURE);
/* 98 */                       ((Arena)this.plugin.arenas.get(arenaName)).playerJoin();
/* 99 */                       ((Arena)this.plugin.arenas.get(arenaName)).playerAdd(player.getName());
/* 100 */                       Iterator list2 = ((Arena)this.plugin.arenas.get(arenaName)).getPlayerList().keySet().iterator();
/* 101 */                       while (list2.hasNext()) {
/* 102 */                         Player names = (Player)list2.next();
/* 103 */                         Integer min4 = Integer.valueOf(((Arena)this.plugin.arenas.get(arenaName)).getMin().intValue() * 4);
/* 104 */                         names.sendMessage(ChatColor.DARK_PURPLE + player.getName() + " has joined the lobby. " + ((Arena)this.plugin.arenas.get(arenaName)).getTotal() + "/" + min4);
/*    */                       }
/* 106 */                       if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getTotal().intValue() == 2) {
/* 107 */                         ((Arena)this.plugin.arenas.get(arenaName)).setCounter(Integer.valueOf(80));
/* 108 */                         Iterator list = ((Arena)this.plugin.arenas.get(arenaName)).getPlayerList().keySet().iterator();
/* 109 */                         while (list.hasNext()) {
/* 110 */                           Player names = (Player)list.next();
/* 111 */                           names.sendMessage(ChatColor.BLUE + "Game starting soon!");
/*    */                         }
/*    */                       }
/*    */                     }
/*    */                     else {
/* 116 */                       player.sendMessage(ChatColor.RED + "This arena is full. Try again later!");
/*    */                     }
/*    */                   }
/*    */                   else {
/* 120 */                     player.sendMessage(ChatColor.RED + "This arena does not have a set Time yet!");
/*    */                   }
/*    */                 }
/*    */                 else {
/* 124 */                   player.sendMessage(ChatColor.RED + "This arena has not got any spawns for teams");
/*    */                 }
/*    */               }
/*    */               else {
/* 128 */                 player.sendMessage(ChatColor.RED + "This arena has not been set up yet!");
/*    */               }
/*    */             }
/*    */             else {
/* 132 */               player.sendMessage(ChatColor.RED + "That Game has already started!");
/*    */             }
/*    */           }
/*    */           else
/* 136 */             player.sendMessage(ChatColor.RED + "You can't join 2 arenas!");
/*    */         }
/*    */         else
/*    */         {
/* 140 */           player.sendMessage(ChatColor.RED + "You do not have permission to join the walls like this!");
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.PlayerInteractListener
 * JD-Core Version:    0.6.2
 */