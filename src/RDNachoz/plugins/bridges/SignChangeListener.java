/*    */ package RDNachoz.plugins.bridges;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Sign;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.SignChangeEvent;
/*    */ 
/*    */ public class SignChangeListener
/*    */   implements Listener
/*    */ {
/*    */   private Bridges plugin;
/*    */ 
/*    */   public SignChangeListener(Bridges walls)
/*    */   {
/* 19 */     this.plugin = walls;
/*    */   }
/*    */ 
/*    */   @EventHandler
/*    */   public void onSignChangeEvent(SignChangeEvent event) {
/* 24 */     Player player = event.getPlayer();
/* 25 */     if ((player.hasPermission("bb.signwall")) && 
/* 26 */       (event.getLine(0).equalsIgnoreCase("[BB]")))
/* 27 */       if (event.getBlock().getType() == Material.WALL_SIGN) {
/* 28 */         if (event.getLine(1).equalsIgnoreCase("join")) {
/* 29 */           if (event.getLine(2) != null) {
/* 30 */             if (this.plugin.arenas.containsKey(event.getLine(2).toLowerCase())) {
/* 31 */               Sign sign = (Sign)event.getBlock().getState();
/* 32 */               String arena = event.getLine(2);
/* 33 */               Location loc = sign.getLocation();
/* 34 */               SignWall signWall = new SignWall(loc, sign.getRawData(), arena, ((Arena)this.plugin.arenas.get(arena.toLowerCase())).getMin(), "Waiting");
/* 35 */               Integer wall = Integer.valueOf(0);
/* 36 */               Integer num = Integer.valueOf(0);
/* 37 */               int totals = ((Arena)this.plugin.arenas.get(arena.toLowerCase())).getMin().intValue() * 4;
/* 38 */               event.setLine(0, ChatColor.BOLD + "BukkitBridges");
/* 39 */               event.setLine(1, ChatColor.DARK_GRAY + "Click to Join");
/* 40 */               event.setLine(2, ChatColor.UNDERLINE + arena);
/* 41 */               event.setLine(3, ChatColor.DARK_GRAY + "0/" + totals);
/* 42 */               Iterator itr = ((Arena)this.plugin.arenas.get(arena.toLowerCase())).getSignWalls().keySet().iterator();
/* 43 */               while (itr.hasNext()) {
/* 44 */                 num = (Integer)itr.next();
/* 45 */                 if (num.intValue() > wall.intValue()) {
/* 46 */                   wall = num;
/*    */                 }
/*    */               }
/* 49 */               Integer total = Integer.valueOf(num.intValue() + 1);
/* 50 */               ((Arena)this.plugin.arenas.get(arena.toLowerCase())).getSignWalls().put(total, signWall);
/* 51 */               ((SignWall)((Arena)this.plugin.arenas.get(arena.toLowerCase())).getSignWalls().get(total)).Create();
/*    */             }
/*    */             else {
/* 54 */               player.sendMessage(ChatColor.RED + "The arena could not be found!");
/*    */             }
/*    */           }
/*    */           else {
/* 58 */             player.sendMessage(ChatColor.RED + "You need to have put an arena name on line 3");
/*    */           }
/*    */         }
/*    */         else {
/* 62 */           player.sendMessage(ChatColor.RED + "Unknown second line on the sign!");
/*    */         }
/*    */       }
/*    */       else
/* 66 */         player.sendMessage(ChatColor.RED + "You sign must be put on the walls!");
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.SignChangeListener
 * JD-Core Version:    0.6.2
 */