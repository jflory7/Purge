/*      */ package RDNachoz.plugins.bridges;
/*      */ 
/*      */ import RDNachoz.plugins.bridges.blockFileData.ArenaFileManager;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.bukkit.ChatColor;
/*      */ import org.bukkit.GameMode;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.World;
/*      */ import org.bukkit.block.Block;
/*      */ import org.bukkit.command.Command;
/*      */ import org.bukkit.command.CommandExecutor;
/*      */ import org.bukkit.command.CommandSender;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.inventory.PlayerInventory;
/*      */ import org.bukkit.inventory.meta.ItemMeta;
/*      */ import org.kitteh.tag.TagAPI;
/*      */ 
/*      */ public class BridgesCommand
/*      */   implements CommandExecutor
/*      */ {
/*      */   private Bridges plugin;
/*      */   private int num;
/*      */ 
/*      */   public BridgesCommand(Bridges bb)
/*      */   {
/*   26 */     this.plugin = bb;
/*      */   }
/*      */ 
/*      */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*      */   {
/*   31 */     if ((sender instanceof Player)) {
/*   32 */       if (args.length == 0) {
/*   33 */         sender.sendMessage(ChatColor.GREEN + "BukkitBridges by RDNachoz, version 1.1.0.1");
/*      */       }
/*   35 */       else if (args[0].equalsIgnoreCase("help")) {
/*   36 */         if (sender.hasPermission("bb.join")) {
/*   37 */           sender.sendMessage(ChatColor.RED + "/bb Join <ArenaName>  " + ChatColor.GREEN + "Joins an arena");
/*      */         }
/*   39 */         if (sender.hasPermission("bb.leave")) {
/*   40 */           sender.sendMessage(ChatColor.RED + "/bb Leave" + ChatColor.GREEN + "  Leaves the arena you are in");
/*      */         }
/*   42 */         if (sender.hasPermission("bb.list")) {
/*   43 */           sender.sendMessage(ChatColor.RED + "/bb List " + ChatColor.GREEN + " Lists the arenas that have been made");
/*      */         }
/*   45 */         if (sender.hasPermission("bb.create")) {
/*   46 */           sender.sendMessage(ChatColor.RED + "/bb Create <ArenaName>  " + ChatColor.GREEN + "Creates an arena");
/*   47 */           sender.sendMessage(ChatColor.RED + "/bb AddSpawn <Colour> <ArenaName>  " + ChatColor.GREEN + "Adds a spawn point for an arena");
/*   48 */           sender.sendMessage(ChatColor.RED + "/bb SetWarp <WarpName> <ArenaName>  " + ChatColor.GREEN + "Sets a warp for an arena");
/*   49 */           sender.sendMessage(ChatColor.RED + "/bb Tool" + ChatColor.GREEN + "  Give you the region selecting tool");
/*   50 */           sender.sendMessage(ChatColor.RED + "/bb BuildRegion <ArenaName>  " + ChatColor.GREEN + "Adds a region people can build in while in the walls");
/*   51 */           sender.sendMessage(ChatColor.RED + "/bb DropLocation <ArenaName>  " + ChatColor.GREEN + "Adds blocks that will cause the walls to drop");
/*      */         }
/*   53 */         if (sender.hasPermission("bb.delete")) {
/*   54 */           sender.sendMessage(ChatColor.RED + "/bb Delete <ArenaName>  " + ChatColor.GREEN + "Deletes an arena");
/*      */         }
/*   56 */         if (sender.hasPermission("bb.stop")) {
/*   57 */           sender.sendMessage(ChatColor.RED + "/bb Stop [ArenaName]  " + ChatColor.GREEN + "Stops an arena or all of the arenas");
/*      */         }
/*   59 */         if (sender.hasPermission("bb.start")) {
/*   60 */           sender.sendMessage(ChatColor.RED + "/bb Start [ArenaName]  " + ChatColor.GREEN + "Starts an arena");
/*      */         }
/*   62 */         if (sender.hasPermission("bb.info")) {
/*   63 */           sender.sendMessage(ChatColor.RED + "/bb Info <ArenaName>  " + ChatColor.GREEN + "Shows the infomation about an arena");
/*      */         }
/*   65 */         if (sender.hasPermission("bb.listregions")) {
/*   66 */           sender.sendMessage(ChatColor.RED + "/bb ListRegions <RegionName> <ArenaName>  " + ChatColor.GREEN + "Lists the arenas regions of that type");
/*      */         }
/*   68 */         if (sender.hasPermission("bb.showregions")) {
/*   69 */           sender.sendMessage(ChatColor.RED + "/bb ShowRegions <RegionName> [Region-ID] <ArenaName>" + ChatColor.GREEN + "  Shows regions with glowstone");
/*      */         }
/*   71 */         if (sender.hasPermission("bb.commands")) {
/*   72 */           sender.sendMessage(ChatColor.RED + "/bb AllowedCommands <Operator> [command] <ArenaName>  " + ChatColor.GREEN + "Shows the infomation about an arena");
/*      */         }
/*   74 */         if ((sender.hasPermission("bb.save")) || (sender.hasPermission("walls.save.config"))) {
/*   75 */           sender.sendMessage(ChatColor.RED + "/bb Save Config  " + ChatColor.GREEN + "Saves the config");
/*      */         }
/*   77 */         if ((sender.hasPermission("bb.save")) || (sender.hasPermission("walls.save.arena"))) {
/*   78 */           sender.sendMessage(ChatColor.RED + "/bb Save Arena <ArenaName>  " + ChatColor.GREEN + "Saves arena into a file");
/*      */         }
/*   80 */         if (sender.hasPermission("bb.players.othergames")) {
/*   81 */           sender.sendMessage(ChatColor.RED + "/bb Players [ArenaName]" + ChatColor.GREEN + "  Gets players in the arenas");
/*      */         }
/*   83 */         else if (sender.hasPermission("bb.players")) {
/*   84 */           sender.sendMessage(ChatColor.RED + "/bb Players" + ChatColor.GREEN + "  Gets the players that are in the arena with you");
/*      */         }
/*   86 */         if ((!sender.hasPermission("bb.create")) && (!sender.hasPermission("bb.list")) && (!sender.hasPermission("bb.join")) && (!sender.hasPermission("bb.delete")) && (!sender.hasPermission("bb.stop")) && (!sender.hasPermission("bb.start")) && (!sender.hasPermission("bb.info")) && (!sender.hasPermission("bb.listregions")) && (!sender.hasPermission("bb.showregions")) && (!sender.hasPermission("bb.players")) && (!sender.hasPermission("bb.players.othergames"))) {
/*   87 */           sender.sendMessage(ChatColor.RED + "You can't use any commands from this plugin");
/*      */         }
/*      */       }
/*   90 */       else if (args[0].equalsIgnoreCase("create")) {
/*   91 */         if (sender.hasPermission("bb.create")) {
/*   92 */           if (args.length >= 2) {
/*   93 */             if (this.plugin.arenas.containsKey(args[1].toLowerCase())) {
/*   94 */               sender.sendMessage(ChatColor.RED + "Arena " + args[1].toLowerCase() + " already exists");
/*      */             }
/*   96 */             else if (this.plugin.location1.get((Player)sender) != null) {
/*   97 */               if (this.plugin.location2.get((Player)sender) != null) {
/*   98 */                 Player player = (Player)sender;
/*   99 */                 if (((Location)this.plugin.location1.get((Player)sender)).getWorld() == player.getWorld()) {
/*  100 */                   if (((Location)this.plugin.location2.get((Player)sender)).getWorld() == player.getWorld()) {
/*  101 */                     Arena arena = new Arena(args[1].toLowerCase(), (Location)this.plugin.location1.get((Player)sender), (Location)this.plugin.location2.get((Player)sender), Integer.valueOf(0), Boolean.valueOf(false), null, null, null, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), null, null, Integer.valueOf(0), Integer.valueOf(0), null, null, null, null, Integer.valueOf(0), false, Integer.valueOf(0), this.plugin);
/*  102 */                     this.plugin.arenas.put(arena.getName(), arena);
/*  103 */                     sender.sendMessage(ChatColor.GREEN + arena.getName() + " has been created!");
/*  104 */                     sender.sendMessage(ChatColor.GOLD + "Saving the arena to a file!");
/*  105 */                     ArenaFileManager.setPlugin(this.plugin);
/*  106 */                     ArenaFileManager.saveToAFile(args[1].toLowerCase(), (Location)this.plugin.location1.get((Player)sender), (Location)this.plugin.location2.get((Player)sender));
/*  107 */                     sender.sendMessage(ChatColor.GOLD + "Saved the arena!");
/*      */                   } else {
/*  109 */                     sender.sendMessage(ChatColor.RED + "Both your points need to be in the same world as you");
/*      */                   }
/*      */                 }
/*  112 */                 else sender.sendMessage(ChatColor.RED + "Both your points need to be in the same world as you");
/*      */               }
/*      */               else
/*      */               {
/*  116 */                 sender.sendMessage(ChatColor.RED + "You need to have set both points");
/*      */               }
/*      */             }
/*  119 */             else sender.sendMessage(ChatColor.RED + "You need to have set both points");
/*      */           }
/*      */           else
/*      */           {
/*  123 */             sender.sendMessage(ChatColor.RED + "Use the command like this. " + ChatColor.GREEN + "/Walls Create <arenaname>");
/*      */           }
/*      */         }
/*  126 */         else sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */ 
/*      */       }
/*  129 */       else if (args[0].equalsIgnoreCase("stop")) {
/*  130 */         if (sender.hasPermission("bb.stop")) {
/*  131 */           if (args.length == 1) {
/*  132 */             Iterator list = this.plugin.arenas.keySet().iterator();
/*  133 */             while (list.hasNext()) {
/*  134 */               String name = (String)list.next();
/*  135 */               if (!((Arena)this.plugin.arenas.get(name)).getPlayerList().isEmpty()) {
/*  136 */                 Iterator players = ((Arena)this.plugin.arenas.get(name)).getPlayerList().keySet().iterator();
/*  137 */                 while (players.hasNext()) {
/*  138 */                   Player player = (Player)players.next();
/*  139 */                   ((Arena)this.plugin.arenas.get(name)).setRandom(player);
/*  140 */                   ((Arena)this.plugin.arenas.get(name)).Leave();
/*  141 */                   this.plugin.playerarena.remove(player);
/*  142 */                   player.teleport(((Arena)this.plugin.arenas.get(name)).getLose());
/*      */                 }
/*      */               }
/*  145 */               ((Arena)this.plugin.arenas.get(name)).RestoreBlocks();
/*  146 */               ((Arena)this.plugin.arenas.get(name)).setRedplayers(Integer.valueOf(0));
/*  147 */               ((Arena)this.plugin.arenas.get(name)).setBlueplayers(Integer.valueOf(0));
/*  148 */               ((Arena)this.plugin.arenas.get(name)).setGreenplayers(Integer.valueOf(0));
/*  149 */               ((Arena)this.plugin.arenas.get(name)).setYellowplayers(Integer.valueOf(0));
/*  150 */               ((Arena)this.plugin.arenas.get(name)).setTotal(Integer.valueOf(0));
/*  151 */               ((Arena)this.plugin.arenas.get(name)).setStarted(Boolean.valueOf(false));
/*  152 */               ((Arena)this.plugin.arenas.get(name)).getPlayerList().clear();
/*  153 */               ((Arena)this.plugin.arenas.get(name)).setCounter(null);
/*  154 */               sender.sendMessage(ChatColor.RED + "All arenas been stopped");
/*      */             }
/*      */           }
/*  157 */           else if (args.length >= 2) {
/*  158 */             if (this.plugin.arenas.containsKey(args[1].toLowerCase())) {
/*  159 */               if (((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getStarted().booleanValue()) {
/*  160 */                 String arena = args[1].toLowerCase();
/*  161 */                 Iterator players = ((Arena)this.plugin.arenas.get(arena)).getPlayerList().keySet().iterator();
/*  162 */                 while (players.hasNext()) {
/*  163 */                   Player player = (Player)players.next();
/*  164 */                   ((Arena)this.plugin.arenas.get(arena)).setRandom(player);
/*  165 */                   ((Arena)this.plugin.arenas.get(arena)).Leave();
/*  166 */                   this.plugin.playerarena.remove(player);
/*  167 */                   player.teleport(((Arena)this.plugin.arenas.get(arena)).getLose());
/*      */                 }
/*  169 */                 ((Arena)this.plugin.arenas.get(arena)).RestoreBlocks();
/*  170 */                 ((Arena)this.plugin.arenas.get(arena)).setRedplayers(Integer.valueOf(0));
/*  171 */                 ((Arena)this.plugin.arenas.get(arena)).setBlueplayers(Integer.valueOf(0));
/*  172 */                 ((Arena)this.plugin.arenas.get(arena)).setGreenplayers(Integer.valueOf(0));
/*  173 */                 ((Arena)this.plugin.arenas.get(arena)).setYellowplayers(Integer.valueOf(0));
/*  174 */                 ((Arena)this.plugin.arenas.get(arena)).setTotal(Integer.valueOf(0));
/*  175 */                 ((Arena)this.plugin.arenas.get(arena)).setStarted(Boolean.valueOf(false));
/*  176 */                 ((Arena)this.plugin.arenas.get(arena)).getPlayerList().clear();
/*  177 */                 ((Arena)this.plugin.arenas.get(arena)).setCounter(null);
/*  178 */                 sender.sendMessage(ChatColor.RED + "Arena " + args[1].toLowerCase() + " has been stopped");
/*      */               } else {
/*  180 */                 sender.sendMessage(ChatColor.RED + args[1].toLowerCase() + " has not started so you can't stop it");
/*      */               }
/*      */             }
/*      */             else
/*  184 */               sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */           }
/*      */           else
/*      */           {
/*  188 */             sender.sendMessage(ChatColor.RED + "Use the command like this. " + ChatColor.GREEN + "/bb Stop [Arenaname]");
/*      */           }
/*      */         }
/*      */         else {
/*  192 */           sender.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
/*      */         }
/*      */       }
/*  195 */       else if ((args[0].equalsIgnoreCase("start")) || (args[0].equalsIgnoreCase("forcestart"))) {
/*  196 */         if (sender.hasPermission("bb.start")) {
/*  197 */           Player player = (Player)sender;
/*  198 */           if (args.length == 1) {
/*  199 */             if (this.plugin.playerarena.get(player) != null) {
/*  200 */               if (this.plugin.arenas.containsKey(((String)this.plugin.playerarena.get(player)).toLowerCase())) {
/*  201 */                 if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getTotal().intValue() >= 2) {
/*  202 */                   String arena = (String)this.plugin.playerarena.get(player);
/*  203 */                   sender.sendMessage(ChatColor.AQUA + "starting the arena " + arena);
/*  204 */                   ((Arena)this.plugin.arenas.get(arena)).setCounter(Integer.valueOf(10));
/*      */                 }
/*      */                 else {
/*  207 */                   sender.sendMessage(ChatColor.RED + "You need 2 people to start a game");
/*      */                 }
/*      */               }
/*      */               else
/*  211 */                 sender.sendMessage(ChatColor.RED + "Error. Deleted arena.");
/*      */             }
/*      */             else
/*      */             {
/*  215 */               sender.sendMessage(ChatColor.RED + "You are not in an arena so can't use the command like that");
/*  216 */               sender.sendMessage("Use the command like this. " + ChatColor.GREEN + "/bb Start <arenaname>");
/*      */             }
/*      */           }
/*  219 */           else if (args.length >= 2)
/*  220 */             if (this.plugin.arenas.containsKey(args[1].toLowerCase())) {
/*  221 */               if (!((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getStarted().booleanValue()) {
/*  222 */                 if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getTotal().intValue() >= 2) {
/*  223 */                   sender.sendMessage(ChatColor.AQUA + "starting the arena " + args[1].toLowerCase());
/*  224 */                   ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).setCounter(Integer.valueOf(10));
/*      */                 }
/*      */                 else {
/*  227 */                   sender.sendMessage(ChatColor.RED + "You need 2 people to start a game");
/*      */                 }
/*      */               }
/*  230 */               else sender.sendMessage(ChatColor.RED + args[1].toLowerCase() + " has already started");
/*      */             }
/*      */             else
/*  233 */               sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */         }
/*      */         else
/*      */         {
/*  237 */           sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */         }
/*      */       }
/*  240 */       else if (args[0].equalsIgnoreCase("setwarp")) {
/*  241 */         if (sender.hasPermission("bb.create")) {
/*  242 */           if (args.length >= 3) {
/*  243 */             Player player = (Player)sender;
/*  244 */             if (args[1].equalsIgnoreCase("win")) {
/*  245 */               if (this.plugin.arenas.containsKey(args[2].toLowerCase())) {
/*  246 */                 ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).setWin(player.getLocation());
/*  247 */                 sender.sendMessage(ChatColor.GREEN + "Win point has been set for " + args[2].toLowerCase());
/*      */               }
/*      */               else {
/*  250 */                 sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */               }
/*      */ 
/*      */             }
/*  254 */             else if (args[1].equalsIgnoreCase("lose")) {
/*  255 */               if (this.plugin.arenas.containsKey(args[2].toLowerCase())) {
/*  256 */                 ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).setLose(player.getLocation());
/*  257 */                 sender.sendMessage(ChatColor.GREEN + "Lose point has been set for " + args[2].toLowerCase());
/*      */               }
/*      */               else {
/*  260 */                 sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */               }
/*      */             }
/*  263 */             else if (args[1].equalsIgnoreCase("lobby")) {
/*  264 */               if (this.plugin.arenas.containsKey(args[2].toLowerCase())) {
/*  265 */                 ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).setLobby(player.getLocation());
/*  266 */                 sender.sendMessage(ChatColor.GREEN + "Lobby point has been set for " + args[2].toLowerCase());
/*      */               }
/*      */               else {
/*  269 */                 sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */               }
/*      */             }
/*      */             else
/*  273 */               sender.sendMessage(ChatColor.RED + "That can't be a Warp. " + ChatColor.GREEN + "It can be: Win, Lose, Lobby");
/*      */           }
/*      */           else
/*      */           {
/*  277 */             sender.sendMessage(ChatColor.RED + "Use the command like this. " + ChatColor.GREEN + "/bb SetWarp <WarpName> <ArenaName>");
/*      */           }
/*      */         }
/*      */       }
/*  281 */       else if (args[0].equalsIgnoreCase("addspawn")) {
/*  282 */         if (sender.hasPermission("bb.create")) {
/*  283 */           if (args.length == 3) {
/*  284 */             Player player = (Player)sender;
/*  285 */             if (this.plugin.arenas.containsKey(args[2].toLowerCase())) {
/*  286 */               if (args[1].equalsIgnoreCase("red")) {
/*  287 */                 ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).setPlayerLoc(player.getLocation());
/*  288 */                 ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).addRedSpawn();
/*  289 */                 sender.sendMessage(ChatColor.DARK_RED + "Spawn " + ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getReds() + " spawn set in " + args[2].toLowerCase());
/*  290 */                 ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).min();
/*  291 */                 Iterator itr = ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getSignWalls().keySet().iterator();
/*  292 */                 while (itr.hasNext()) {
/*  293 */                   Integer num = (Integer)itr.next();
/*  294 */                   ((SignWall)((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getSignWalls().get(num)).addPlayerSign(((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getMin());
/*      */                 }
/*      */               }
/*  297 */               else if (args[1].equalsIgnoreCase("blue")) {
/*  298 */                 ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).setPlayerLoc(player.getLocation());
/*  299 */                 ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).addBlueSpawn();
/*  300 */                 sender.sendMessage(ChatColor.DARK_BLUE + "Spawn " + ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getBlues() + " spawn set in " + args[2].toLowerCase());
/*  301 */                 ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).min();
/*  302 */                 Iterator itr = ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getSignWalls().keySet().iterator();
/*  303 */                 while (itr.hasNext()) {
/*  304 */                   Integer num = (Integer)itr.next();
/*  305 */                   ((SignWall)((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getSignWalls().get(num)).addPlayerSign(((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getMin());
/*      */                 }
/*      */               }
/*  308 */               else if (args[1].equalsIgnoreCase("green")) {
/*  309 */                 ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).setPlayerLoc(player.getLocation());
/*  310 */                 ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).addGreenSpawn();
/*  311 */                 sender.sendMessage(ChatColor.DARK_GREEN + "Spawn " + ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getGreens() + " spawn set in " + args[2].toLowerCase());
/*  312 */                 ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).min();
/*  313 */                 Iterator itr = ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getSignWalls().keySet().iterator();
/*  314 */                 while (itr.hasNext()) {
/*  315 */                   Integer num = (Integer)itr.next();
/*  316 */                   ((SignWall)((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getSignWalls().get(num)).addPlayerSign(((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getMin());
/*      */                 }
/*      */               }
/*  319 */               else if (args[1].equalsIgnoreCase("yellow")) {
/*  320 */                 ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).setPlayerLoc(player.getLocation());
/*  321 */                 ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).addYellowSpawn();
/*  322 */                 sender.sendMessage(ChatColor.YELLOW + "Spawn " + ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getYellows() + " spawn set in " + args[2].toLowerCase());
/*  323 */                 ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).min();
/*  324 */                 Iterator itr = ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getSignWalls().keySet().iterator();
/*  325 */                 while (itr.hasNext()) {
/*  326 */                   Integer num = (Integer)itr.next();
/*  327 */                   ((SignWall)((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getSignWalls().get(num)).addPlayerSign(((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getMin());
/*      */                 }
/*      */               }
/*      */               else {
/*  331 */                 sender.sendMessage(ChatColor.RED + "That can't be a team colour. " + ChatColor.GREEN + "It can be: Red, Blue, Green, Yellow");
/*      */               }
/*      */             }
/*  334 */             else sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */           }
/*      */           else
/*      */           {
/*  338 */             sender.sendMessage(ChatColor.RED + "Use the command like this. " + ChatColor.GREEN + "/bb AddSpawn <Colour> <ArenaName>");
/*      */           }
/*      */         }
/*      */         else
/*  342 */           sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */       }
/*      */       else
/*      */       {
/*      */         Iterator list;
/*  345 */         if (args[0].equalsIgnoreCase("join")) {
/*  346 */           if (sender.hasPermission("bb.join")) {
/*  347 */             if (this.plugin.playerarena.get((Player)sender) == null) {
/*  348 */               if (args.length == 2) {
/*  349 */                 String arena = args[1].toLowerCase();
/*  350 */                 if (this.plugin.arenas.containsKey(arena)) {
/*  351 */                   if (!((Arena)this.plugin.arenas.get(arena)).getStarted().booleanValue()) {
/*  352 */                     if ((((Arena)this.plugin.arenas.get(arena)).getLose() != null) && (((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getWin() != null) && (((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getLobby() != null)) {
/*  353 */                       if (((Arena)this.plugin.arenas.get(arena)).getMin().intValue() > 0) {
/*  354 */                         if (((Arena)this.plugin.arenas.get(arena)).getDropTime() != null) {
/*  355 */                           Player player = (Player)sender;
/*  356 */                           Integer num = Integer.valueOf(((Arena)this.plugin.arenas.get(arena)).getMin().intValue() + ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getMin().intValue() + ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getMin().intValue() + ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getMin().intValue());
/*  357 */                           if (((Arena)this.plugin.arenas.get(arena)).getTotal().intValue() < num.intValue()) {
/*  358 */                             player.teleport(((Arena)this.plugin.arenas.get(arena)).getLobby());
/*  359 */                             this.plugin.playerarena.put(player, arena);
/*  360 */                             this.plugin.teleportable.put(player, Boolean.valueOf(false));
/*  361 */                             ((Arena)this.plugin.arenas.get(arena)).setTotal(Integer.valueOf(((Arena)this.plugin.arenas.get(arena)).getTotal().intValue() + 1));
/*  362 */                             sender.sendMessage(ChatColor.GRAY + "Joined arena " + arena + "!");
/*  363 */                             ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getPlayerList().put(player, "lobby");
/*  364 */                             ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().put(player, "lobby");
/*  365 */                             ((Arena)this.plugin.arenas.get(arena)).getPlayerInventories().put(player, player.getInventory().getContents());
/*  366 */                             ((Arena)this.plugin.arenas.get(arena)).getAurmor().put(player, player.getInventory().getArmorContents());
/*  367 */                             Integer tempExp = Integer.valueOf(player.getLevel());
/*  368 */                             ((Arena)this.plugin.arenas.get(arena)).getExp().put(player, tempExp);
/*  369 */                             player.getInventory().clear();
/*  370 */                             player.getInventory().setArmorContents(null);
/*  371 */                             player.updateInventory();
/*  372 */                             player.setLevel(0);
/*  373 */                             ((Arena)this.plugin.arenas.get(arena)).getLobbyPlayers().add(player);
/*  374 */                             player.setExp(0.0F);
/*  375 */                             player.setHealth(20);
/*  376 */                             player.setFoodLevel(20);
/*  377 */                             player.setGameMode(GameMode.ADVENTURE);
/*  378 */                             ((Arena)this.plugin.arenas.get(arena)).playerJoin();
/*  379 */                             ((Arena)this.plugin.arenas.get(arena)).playerAdd(player.getName());
/*  380 */                             Iterator list2 = ((Arena)this.plugin.arenas.get(arena)).getPlayerList().keySet().iterator();
/*  381 */                             while (list2.hasNext()) {
/*  382 */                               Player names = (Player)list2.next();
/*  383 */                               Integer min4 = Integer.valueOf(((Arena)this.plugin.arenas.get(arena)).getMin().intValue() * 4);
/*  384 */                               names.sendMessage(ChatColor.DARK_PURPLE + sender.getName() + " has joined the lobby. " + ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getTotal() + "/" + min4);
/*      */                             }
/*  386 */                             if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getTotal().intValue() == 2) {
/*  387 */                               ((Arena)this.plugin.arenas.get(arena)).setCounter(Integer.valueOf(80));
/*  388 */                               list = ((Arena)this.plugin.arenas.get(arena)).getPlayerList().keySet().iterator();
/*  389 */                               while (list.hasNext()) {
/*  390 */                                 Player names = (Player)list.next();
/*  391 */                                 names.sendMessage(ChatColor.BLUE + "Game starting soon!");
/*      */                               }
/*      */                             }
/*      */                           }
/*      */                           else {
/*  396 */                             sender.sendMessage(ChatColor.RED + "This arena is full. Try again later!");
/*      */                           }
/*      */                         }
/*      */                         else {
/*  400 */                           sender.sendMessage(ChatColor.RED + "This arena does not have a set Time yet!");
/*      */                         }
/*      */                       }
/*      */                       else
/*  404 */                         sender.sendMessage(ChatColor.RED + "This arena has not got any spawns for teams");
/*      */                     }
/*      */                     else {
/*  407 */                       sender.sendMessage(ChatColor.RED + "This arena has not been set up yet!");
/*      */                     }
/*      */                   }
/*      */                   else {
/*  411 */                     sender.sendMessage(ChatColor.RED + "That Game has already started!");
/*      */                   }
/*      */                 }
/*      */                 else
/*  415 */                   sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */               }
/*      */               else
/*      */               {
/*  419 */                 sender.sendMessage(ChatColor.RED + "Use the command like this. " + ChatColor.GREEN + "/bb Join <ArenaName>");
/*      */               }
/*      */             }
/*      */             else {
/*  423 */               sender.sendMessage(ChatColor.RED + "You can't join an arena while in an arena!");
/*      */             }
/*      */           }
/*      */           else {
/*  427 */             sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */           }
/*      */         }
/*  430 */         else if ((args[0].equalsIgnoreCase("list")) || (args[0].equalsIgnoreCase("listarenas"))) {
/*  431 */           if (sender.hasPermission("bb.list")) {
/*  432 */             Integer arenas = Integer.valueOf(0);
/*  433 */             Iterator count = this.plugin.arenas.keySet().iterator();
/*  434 */             while (count.hasNext()) {
/*  435 */               count.next();
/*  436 */               arenas = Integer.valueOf(arenas.intValue() + 1);
/*      */             }
/*  438 */             sender.sendMessage(ChatColor.BLUE + "There are " + arenas + " arenas");
/*  439 */             Iterator list = this.plugin.arenas.keySet().iterator();
/*  440 */             while (list.hasNext()) {
/*  441 */               String name = (String)list.next();
/*  442 */               sender.sendMessage(ChatColor.DARK_GREEN + name);
/*      */             }
/*      */           }
/*      */           else {
/*  446 */             sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */           }
/*      */         }
/*  449 */         else if ((args[0].equalsIgnoreCase("bridgeregion")) || (args[0].equalsIgnoreCase("bridgeregions")) || (args[0].equalsIgnoreCase("bridgelocation")) || (args[0].equalsIgnoreCase("bridgelocations"))) {
/*  450 */           if (sender.hasPermission("walls.create")) {
/*  451 */             if (args.length >= 2) {
/*  452 */               if (this.plugin.arenas.containsKey(args[1].toLowerCase())) {
/*  453 */                 if (this.plugin.location1.get((Player)sender) != null) {
/*  454 */                   if (this.plugin.location2.get((Player)sender) != null) {
/*  455 */                     Player player = (Player)sender;
/*  456 */                     if (((Location)this.plugin.location1.get((Player)sender)).getWorld() == player.getWorld()) {
/*  457 */                       if (((Location)this.plugin.location2.get((Player)sender)).getWorld() == player.getWorld()) {
/*  458 */                         ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).setTempLoc((Location)this.plugin.location1.get(player));
/*  459 */                         ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).setTempLoc1((Location)this.plugin.location2.get(player));
/*  460 */                         Integer num = Integer.valueOf(((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getTotalDrops().intValue() + 1);
/*  461 */                         sender.sendMessage(ChatColor.GOLD + "Bridge Location number " + num + " set for arena " + args[1].toLowerCase());
/*  462 */                         ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).CreateDropLocation();
/*      */                       } else {
/*  464 */                         sender.sendMessage(ChatColor.RED + "Both your points need to be in the same world as you");
/*      */                       }
/*      */                     }
/*  467 */                     else sender.sendMessage(ChatColor.RED + "Both your points need to be in the same world as you"); 
/*      */                   }
/*      */                   else
/*      */                   {
/*  470 */                     sender.sendMessage(ChatColor.RED + "You need to have set both points");
/*      */                   }
/*      */                 }
/*  473 */                 else sender.sendMessage(ChatColor.RED + "You need to have set both points");
/*      */               }
/*      */               else
/*      */               {
/*  477 */                 sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */               }
/*      */             }
/*      */             else {
/*  481 */               sender.sendMessage(ChatColor.RED + "Use the command like this. " + ChatColor.GREEN + "/bb BridgeLocation <ArenaName>");
/*      */             }
/*      */           }
/*      */           else {
/*  485 */             sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */           }
/*      */         }
/*  488 */         else if (args[0].equalsIgnoreCase("leave")) {
/*  489 */           if (sender.hasPermission("bb.leave")) {
/*  490 */             Player player = (Player)sender;
/*  491 */             String arena = (String)this.plugin.playerarena.get(player);
/*  492 */             if (this.plugin.arenas.containsKey(this.plugin.playerarena.get(player))) {
/*  493 */               sender.sendMessage(ChatColor.RED + "You left the arena!");
/*  494 */               this.plugin.teleportable.put(player, Boolean.valueOf(true));
/*  495 */               if ((((Arena)this.plugin.arenas.get(arena)).getCounter() != null) && (((Arena)this.plugin.arenas.get(arena)).getCounter().intValue() < 10)) {
/*  496 */                 Iterator list = ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().keySet().iterator();
/*  497 */                 while (list.hasNext()) {
/*  498 */                   Player name = (Player)list.next();
/*  499 */                   if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().get(player) == "red") {
/*  500 */                     name.sendMessage(ChatColor.RED + player.getName() + ChatColor.AQUA + " has left the arena");
/*      */                   }
/*  502 */                   else if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().get(player) == "blue") {
/*  503 */                     name.sendMessage(ChatColor.BLUE + player.getName() + ChatColor.AQUA + " has left the arena");
/*      */                   }
/*  505 */                   else if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().get(player) == "green") {
/*  506 */                     name.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.AQUA + " has left the arena");
/*      */                   }
/*  508 */                   else if (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().get(player) == "yellow") {
/*  509 */                     name.sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.AQUA + " has left the arena!");
/*      */                   }
/*      */                 }
/*  512 */                 TagAPI.refreshPlayer(player);
/*  513 */                 ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).setRandom(player);
/*  514 */                 ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).Leave();
/*  515 */                 ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().remove(player);
/*  516 */                 player.setHealth(20);
/*  517 */                 player.setFoodLevel(20);
/*  518 */                 player.setFireTicks(0);
/*  519 */                 if ((((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getTotal() == ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getRedplayers()) || (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getTotal() == ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getBlueplayers()) || (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getTotal() == ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getGreenplayers()) || (((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getTotal() == ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getYellowplayers())) {
/*  520 */                   Arena arenas = (Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player));
/*  521 */                   arenas.broadcastWinner();
/*  522 */                   for (Player play : ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().keySet()) {
/*  523 */                     this.plugin.teleportable.put(play, Boolean.valueOf(true));
/*  524 */                     arenas.setRandom(play);
/*  525 */                     arenas.Leave();
/*  526 */                     this.plugin.playerarena.remove(play);
/*  527 */                     play.teleport(arenas.getWin());
/*  528 */                     play.setHealth(20);
/*  529 */                     play.setFoodLevel(20);
/*  530 */                     play.setFallDistance(0.0F);
/*  531 */                     play.setFireTicks(0);
/*      */                   }
/*  533 */                   arenas.getPlayerList().clear();
/*  534 */                   ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).setCountToEnd(Integer.valueOf(0));
/*      */                 }
/*  536 */                 this.plugin.playerarena.remove(player);
/*      */               }
/*      */               else {
/*  539 */                 ((Arena)this.plugin.arenas.get(arena)).getLobbyPlayers().remove(player);
/*  540 */                 if (((Arena)this.plugin.arenas.get(arena)).getTotal().intValue() <= 1) {
/*  541 */                   Iterator list = ((Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player))).getPlayerList().keySet().iterator();
/*  542 */                   while (list.hasNext()) {
/*  543 */                     Player name = (Player)list.next();
/*  544 */                     name.sendMessage(ChatColor.AQUA + player.getName() + " has left the lobby!");
/*      */                   }
/*      */                 }
/*  547 */                 ((Arena)this.plugin.arenas.get(arena)).setRandom(player);
/*  548 */                 ((Arena)this.plugin.arenas.get(arena)).Leave();
/*  549 */                 player.setHealth(20);
/*  550 */                 player.setFoodLevel(20);
/*  551 */                 player.setFireTicks(0);
/*  552 */                 if (((Arena)this.plugin.arenas.get(arena)).getTotal().intValue() >= 1) {
/*  553 */                   Iterator list3 = ((Arena)this.plugin.arenas.get(arena)).getPlayerList().keySet().iterator();
/*  554 */                   while (list3.hasNext()) {
/*  555 */                     Player names = (Player)list3.next();
/*  556 */                     names.sendMessage(ChatColor.AQUA + player.getName() + " has left the lobby!");
/*      */                   }
/*      */                 }
/*  559 */                 this.plugin.playerarena.remove(player);
/*  560 */                 ((Arena)this.plugin.arenas.get(arena)).getPlayerList().remove(player);
/*  561 */                 if (((Arena)this.plugin.arenas.get(arena)).getTotal().intValue() == 1) {
/*  562 */                   ((Arena)this.plugin.arenas.get(arena)).setCounter(null);
/*  563 */                   if (((Arena)this.plugin.arenas.get(arena)).getTotal().intValue() >= 1) {
/*  564 */                     Iterator list2 = ((Arena)this.plugin.arenas.get(arena)).getPlayerList().keySet().iterator();
/*  565 */                     while (list2.hasNext()) {
/*  566 */                       Player names = (Player)list2.next();
/*  567 */                       names.sendMessage(ChatColor.RED + "Game stopped because there are not enough people");
/*      */                     }
/*      */                   }
/*      */                 }
/*  571 */                 player.setGameMode(GameMode.SURVIVAL);
/*  572 */                 player.teleport(((Arena)this.plugin.arenas.get(arena)).getLose());
/*  573 */                 ((Arena)this.plugin.arenas.get(arena)).playerJoin();
/*      */               }
/*      */             }
/*      */             else {
/*  577 */               sender.sendMessage(ChatColor.RED + "You are not in any arena!");
/*      */             }
/*      */           }
/*      */         }
/*  581 */         else if ((args[0].equalsIgnoreCase("buildregions")) || (args[0].equalsIgnoreCase("buildregion")) || (args[0].equalsIgnoreCase("buildlocation")) || (args[0].equalsIgnoreCase("buildlocations"))) {
/*  582 */           if (sender.hasPermission("bb.create")) {
/*  583 */             if (args.length >= 2) {
/*  584 */               Player player = (Player)sender;
/*  585 */               if (this.plugin.arenas.containsKey(args[1].toLowerCase())) {
/*  586 */                 if ((this.plugin.location1.get(player) != null) && (this.plugin.location2.get(player) != null)) {
/*  587 */                   ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).setSaveTotal(Integer.valueOf(((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getSaveTotal().intValue() + 1));
/*  588 */                   Location start1 = (Location)this.plugin.location1.get(player);
/*  589 */                   Location start2 = (Location)this.plugin.location2.get(player);
/*  590 */                   Integer maxX = Integer.valueOf(Math.max((int)start1.getX(), (int)start2.getX()));
/*  591 */                   Integer minX = Integer.valueOf(Math.min((int)start1.getX(), (int)start2.getX()));
/*  592 */                   Integer maxY = Integer.valueOf(Math.max((int)start1.getY(), (int)start2.getY()));
/*  593 */                   Integer minY = Integer.valueOf(Math.min((int)start1.getY(), (int)start2.getY()));
/*  594 */                   Integer maxZ = Integer.valueOf(Math.max((int)start1.getZ(), (int)start2.getZ()));
/*  595 */                   Integer minZ = Integer.valueOf(Math.min((int)start1.getZ(), (int)start2.getZ()));
/*  596 */                   World world = ((Location)this.plugin.location1.get(player)).getWorld();
/*  597 */                   Location end1 = world.getBlockAt(maxX.intValue(), maxY.intValue(), maxZ.intValue()).getLocation();
/*  598 */                   Location end2 = world.getBlockAt(minX.intValue(), minY.intValue(), minZ.intValue()).getLocation();
/*  599 */                   ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getSaveregion().put(((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getSaveTotal(), end1);
/*  600 */                   ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getSaveregion1().put(((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getSaveTotal(), end2);
/*  601 */                   sender.sendMessage(ChatColor.GOLD + "Added build region number " + ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getSaveTotal());
/*      */                 }
/*      */                 else {
/*  604 */                   sender.sendMessage(ChatColor.RED + "You need to have set both points");
/*      */                 }
/*      */               }
/*      */               else
/*  608 */                 sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */             }
/*      */             else
/*      */             {
/*  612 */               sender.sendMessage(ChatColor.RED + "Use the command like this. " + ChatColor.GREEN + "/bb BuildRegion <ArenaName>");
/*      */             }
/*      */           }
/*      */           else {
/*  616 */             sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */           }
/*      */         }
/*  619 */         else if ((args[0].equalsIgnoreCase("tool")) || (args[0].equalsIgnoreCase("wand"))) {
/*  620 */           if (sender.hasPermission("bb.create")) {
/*  621 */             Player player = (Player)sender;
/*  622 */             ItemStack is = new ItemStack(Material.CLAY_BRICK, 1);
/*  623 */             ItemMeta im = is.getItemMeta();
/*  624 */             im.setDisplayName(ChatColor.GOLD + ChatColor.BOLD + "Bridges Tool");
/*  625 */             is.setItemMeta(im);
/*  626 */             player.getInventory().addItem(new ItemStack[] { is });
/*  627 */             sender.sendMessage(ChatColor.GOLD + "Here's that very useful tool");
/*      */           }
/*      */           else {
/*  630 */             sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */           }
/*      */         }
/*  633 */         else if ((args[0].equalsIgnoreCase("info")) || (args[0].equalsIgnoreCase("arenainfo"))) {
/*  634 */           if (sender.hasPermission("bb.info")) {
/*  635 */             if (args.length >= 2) {
/*  636 */               if (this.plugin.arenas.containsKey(args[1].toLowerCase())) {
/*  637 */                 sender.sendMessage(ChatColor.GREEN + "Arena name: " + ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getName());
/*  638 */                 sender.sendMessage(ChatColor.RED + "Reds: " + ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getReds());
/*  639 */                 sender.sendMessage(ChatColor.BLUE + "Blues: " + ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getBlues());
/*  640 */                 sender.sendMessage(ChatColor.GREEN + "Greens: " + ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getGreens());
/*  641 */                 sender.sendMessage(ChatColor.YELLOW + "Yellows: " + ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getYellows());
/*  642 */                 if (((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getWin() == null) {
/*  643 */                   sender.sendMessage(ChatColor.GRAY + "WinPoint: False");
/*      */                 }
/*      */                 else {
/*  646 */                   sender.sendMessage(ChatColor.GRAY + "WinPoint: True");
/*      */                 }
/*  648 */                 if (((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getLose() == null) {
/*  649 */                   sender.sendMessage(ChatColor.GRAY + "LosePoint: False");
/*      */                 }
/*      */                 else {
/*  652 */                   sender.sendMessage(ChatColor.GRAY + "LosePoint: True");
/*      */                 }
/*  654 */                 if (((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getLobby() == null) {
/*  655 */                   sender.sendMessage(ChatColor.GRAY + "LobbyPoint: False");
/*      */                 }
/*      */                 else {
/*  658 */                   sender.sendMessage(ChatColor.GRAY + "LobbyPoint: True");
/*      */                 }
/*  660 */                 Integer num = Integer.valueOf(0);
/*  661 */                 Iterator location = ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getDroploc1().keySet().iterator();
/*  662 */                 while (location.hasNext()) {
/*  663 */                   num = Integer.valueOf(num.intValue() + 1);
/*  664 */                   location.next();
/*      */                 }
/*  666 */                 sender.sendMessage(ChatColor.GRAY + "Bridge Locations: " + num);
/*  667 */                 sender.sendMessage(ChatColor.GRAY + "Build Locations: " + ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getSaveregion().size());
/*  668 */                 if ((((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getDropTime() == null) || (((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getDropTime().intValue() == 0)) {
/*  669 */                   sender.sendMessage(ChatColor.DARK_GRAY + "Time: Not Set");
/*      */                 }
/*      */                 else {
/*  672 */                   sender.sendMessage(ChatColor.DARK_GRAY + "Time: " + ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getDropTime() + " second(s)");
/*      */                 }
/*  674 */                 Iterator broadcasts = ((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getMessages().keySet().iterator();
/*  675 */                 String message = "";
/*  676 */                 while (broadcasts.hasNext()) {
/*  677 */                   message = message + broadcasts.next() + ", ";
/*      */                 }
/*  679 */                 sender.sendMessage(ChatColor.DARK_GRAY + "BroadCasts: " + message);
/*      */               }
/*      */               else {
/*  682 */                 sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */               }
/*      */             }
/*      */             else {
/*  686 */               sender.sendMessage(ChatColor.RED + "Use the command like this. " + ChatColor.GREEN + "/bb Info <ArenaName>");
/*      */             }
/*      */           }
/*      */           else {
/*  690 */             sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */           }
/*      */         }
/*  693 */         else if ((args[0].equalsIgnoreCase("delete")) || (args[0].equalsIgnoreCase("del"))) {
/*  694 */           if (sender.hasPermission("bb.delete")) {
/*  695 */             if (args.length >= 2) {
/*  696 */               if (this.plugin.arenas.containsKey(args[1].toLowerCase())) {
/*  697 */                 this.plugin.arenas.remove(args[1].toLowerCase());
/*  698 */                 this.plugin.DeleteArena = args[1].toLowerCase();
/*  699 */                 this.plugin.DeleteArena();
/*  700 */                 sender.sendMessage(ChatColor.AQUA + "Arena Deleted!");
/*      */               }
/*      */               else {
/*  703 */                 sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */               }
/*      */             }
/*      */             else {
/*  707 */               sender.sendMessage(ChatColor.RED + "Use the command like this. " + ChatColor.GREEN + "/bb Delete <ArenaName>");
/*      */             }
/*      */           }
/*      */           else {
/*  711 */             sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */           }
/*      */         }
/*  714 */         else if ((args[0].equalsIgnoreCase("listregions")) || (args[0].equalsIgnoreCase("listregion"))) {
/*  715 */           if (sender.hasPermission("bb.listregions")) {
/*  716 */             if (args.length >= 3) {
/*  717 */               if (this.plugin.arenas.containsKey(args[2].toLowerCase())) {
/*  718 */                 if ((args[1].equalsIgnoreCase("buildregions")) || (args[1].equalsIgnoreCase("buildregion")) || (args[1].equalsIgnoreCase("buildregions"))) {
/*  719 */                   if (!((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getSaveregion().isEmpty()) {
/*  720 */                     Iterator locations = ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getSaveregion().keySet().iterator();
/*  721 */                     while (locations.hasNext()) {
/*  722 */                       Integer loc = (Integer)locations.next();
/*  723 */                       sender.sendMessage(ChatColor.RED + loc + ":");
/*  724 */                       sender.sendMessage(ChatColor.GOLD + "Point1- X: " + ((Location)((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getSaveregion().get(loc)).getX() + " Y: " + ((Location)((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getSaveregion().get(loc)).getY() + " Z: " + ((Location)((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getSaveregion().get(loc)).getZ());
/*  725 */                       sender.sendMessage(ChatColor.GOLD + "Point2- X: " + ((Location)((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getSaveregion1().get(loc)).getX() + " Y: " + ((Location)((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getSaveregion1().get(loc)).getY() + " Z: " + ((Location)((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getSaveregion1().get(loc)).getZ());
/*      */                     }
/*      */                   }
/*      */                   else {
/*  729 */                     sender.sendMessage(ChatColor.RED + "There are no build regions yet");
/*      */                   }
/*      */                 }
/*  732 */                 else if ((args[1].equalsIgnoreCase("bridgeregions")) || (args[1].equalsIgnoreCase("bridgeregion")) || (args[1].equalsIgnoreCase("bridgeregions"))) {
/*  733 */                   if (!((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getDrop().isEmpty()) {
/*  734 */                     Iterator locations = ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getDroploc1().keySet().iterator();
/*  735 */                     while (locations.hasNext()) {
/*  736 */                       Integer loc = (Integer)locations.next();
/*  737 */                       sender.sendMessage(ChatColor.RED + loc + ":");
/*  738 */                       sender.sendMessage(ChatColor.GOLD + "Point1- X: " + ((Location)((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getDroploc1().get(loc)).getX() + " Y:" + ((Location)((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getDroploc1().get(loc)).getY() + " Z:" + ((Location)((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getDroploc1().get(loc)).getZ());
/*  739 */                       sender.sendMessage(ChatColor.GOLD + "Point2- X: " + ((Location)((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getDroploc2().get(loc)).getX() + " Y:" + ((Location)((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getDroploc2().get(loc)).getY() + " Z:" + ((Location)((Arena)this.plugin.arenas.get(args[2].toLowerCase())).getDroploc2().get(loc)).getZ());
/*      */                     }
/*      */                   }
/*      */                   else {
/*  743 */                     sender.sendMessage(ChatColor.RED + "There are no bridge regions yet");
/*      */                   }
/*      */                 }
/*      */                 else {
/*  747 */                   sender.sendMessage(ChatColor.RED + "Unknown region name.  " + ChatColor.GREEN + "Choose from: Build, Bridge");
/*      */                 }
/*      */               }
/*      */               else {
/*  751 */                 sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */               }
/*      */             }
/*      */             else {
/*  755 */               sender.sendMessage(ChatColor.RED + "Use the command like this. " + ChatColor.GREEN + "/bb ListRegions <RegionName> <ArenaName>");
/*      */             }
/*      */           }
/*      */           else {
/*  759 */             sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */           }
/*      */         }
/*  762 */         else if ((args[0].equalsIgnoreCase("showregions")) || (args[0].equalsIgnoreCase("showregion")) || (args[0].equalsIgnoreCase("showlocation")) || (args[0].equalsIgnoreCase("showlocations"))) {
/*  763 */           if (sender.hasPermission("bb.showregions")) {
/*  764 */             if (args.length >= 3) {
/*  765 */               if ((args[1].equalsIgnoreCase("buildlocations")) || (args[1].equalsIgnoreCase("buildregion")) || (args[0].equalsIgnoreCase("buildregion")) || (args[0].equalsIgnoreCase("buildregions"))) {
/*  766 */                 if (args.length >= 4) {
/*  767 */                   if (this.plugin.arenas.containsKey(args[3].toLowerCase())) {
/*      */                     try {
/*  769 */                       this.num = Integer.parseInt(args[2]);
/*      */                     } catch (NumberFormatException e) {
/*  771 */                       sender.sendMessage(ChatColor.RED + "The Region-ID is going to be a number");
/*      */                     }
/*  773 */                     if (((Arena)this.plugin.arenas.get(args[3].toLowerCase())).getSaveregion().containsKey(Integer.valueOf(this.num))) {
/*  774 */                       if (!((Arena)this.plugin.arenas.get(args[3].toLowerCase())).isShowing()) {
/*  775 */                         sender.sendMessage(ChatColor.GOLD + "Showing the region...");
/*  776 */                         ((Arena)this.plugin.arenas.get(args[3].toLowerCase())).setSeeing((Player)sender);
/*  777 */                         ((Arena)this.plugin.arenas.get(args[3].toLowerCase())).setNum(Integer.valueOf(this.num));
/*  778 */                         ((Arena)this.plugin.arenas.get(args[3].toLowerCase())).showRegion();
/*      */                       }
/*      */                       else {
/*  781 */                         sender.sendMessage(ChatColor.RED + "A region is already being shown");
/*      */                       }
/*      */                     }
/*      */                     else
/*  785 */                       sender.sendMessage(ChatColor.RED + "Unknown region ID");
/*      */                   }
/*      */                   else
/*      */                   {
/*  789 */                     sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */                   }
/*      */                 }
/*      */                 else {
/*  793 */                   sender.sendMessage(ChatColor.RED + "Use the command like this. " + ChatColor.GREEN + "/bb ShowRegion Build <Build-Region-ID> <ArenaName>");
/*      */                 }
/*      */               }
/*  796 */               else if ((args[1].equalsIgnoreCase("arena")) || (args[1].equalsIgnoreCase("arenaregion")) || (args[1].equalsIgnoreCase("arenalocation")) || (args[1].equalsIgnoreCase("arenalocations")) || (args[1].equalsIgnoreCase("arenaregions"))) {
/*  797 */                 if (this.plugin.arenas.containsKey(args[2].toLowerCase())) {
/*  798 */                   ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).setSeeing((Player)sender);
/*  799 */                   sender.sendMessage(ChatColor.GOLD + "Showing the region...");
/*  800 */                   ((Arena)this.plugin.arenas.get(args[2].toLowerCase())).ShowArena();
/*      */                 }
/*      */                 else {
/*  803 */                   sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */                 }
/*      */               }
/*  806 */               else if ((args[1].equalsIgnoreCase("bridgelocation")) || (args[1].equalsIgnoreCase("bridgelocations")) || (args[1].equalsIgnoreCase("bridgeregion")) || (args[1].equalsIgnoreCase("bridgeregions"))) {
/*  807 */                 this.plugin.arenas.containsKey(args[3].toLowerCase());
/*      */               }
/*      */               else
/*      */               {
/*  812 */                 sender.sendMessage(ChatColor.RED + "Unknown region name.  " + ChatColor.GREEN + "Choose from: BuildRegion, ArenaRegion");
/*      */               }
/*      */             }
/*      */             else {
/*  816 */               sender.sendMessage(ChatColor.RED + "Use the command like this. " + ChatColor.GREEN + "/Walls ShowRegion <Region Name> [Region-ID] <ArenaName>");
/*      */             }
/*      */           }
/*      */           else {
/*  820 */             sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */           }
/*      */         }
/*  823 */         else if (args[0].equalsIgnoreCase("time")) {
/*  824 */           if (sender.hasPermission("bb.time")) {
/*  825 */             if (args.length >= 2) {
/*  826 */               if (args[1].equalsIgnoreCase("set")) {
/*  827 */                 if (args.length >= 4) {
/*  828 */                   if (this.plugin.arenas.containsKey(args[3].toLowerCase())) {
/*  829 */                     Integer time = Integer.valueOf(0);
/*      */                     try {
/*  831 */                       time = Integer.valueOf(Integer.parseInt(args[2]));
/*      */                     } catch (NumberFormatException e) {
/*  833 */                       sender.sendMessage(ChatColor.RED + "Needs to be a whole number");
/*      */                     }
/*  835 */                     if (time.intValue() > 0) {
/*  836 */                       ((Arena)this.plugin.arenas.get(args[3].toLowerCase())).setDropTime(time);
/*  837 */                       Integer seconds = time;
/*  838 */                       Integer min = Integer.valueOf(0);
/*  839 */                       while (seconds.intValue() >= 60) {
/*  840 */                         seconds = Integer.valueOf(seconds.intValue() - 60);
/*  841 */                         min = Integer.valueOf(min.intValue() + 1);
/*      */                       }
/*  843 */                       if ((min.intValue() != 0) && (seconds.intValue() == 0)) {
/*  844 */                         sender.sendMessage(ChatColor.GOLD + "Total time set to " + min + " minute(s) for the arena " + args[3].toLowerCase());
/*      */                       }
/*  846 */                       else if ((seconds.intValue() != 0) && (min.intValue() == 0)) {
/*  847 */                         sender.sendMessage(ChatColor.GOLD + "Total time set to " + seconds + " second(s) for the arena " + args[3].toLowerCase());
/*      */                       }
/*  849 */                       else if ((seconds.intValue() != 0) && (min.intValue() != 0))
/*  850 */                         sender.sendMessage(ChatColor.GOLD + "Total time set to " + min + " minute(s) and " + seconds + " second(s) for the arena " + args[3].toLowerCase());
/*      */                     }
/*      */                     else
/*      */                     {
/*  854 */                       sender.sendMessage(ChatColor.RED + "The time you need to set must be 1 second or more");
/*      */                     }
/*      */                   }
/*      */                   else {
/*  858 */                     sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */                   }
/*      */                 }
/*      */                 else {
/*  862 */                   sender.sendMessage(ChatColor.RED + "Use the command like this " + ChatColor.GREEN + "/Walls Time Set <Time> <ArenaName>");
/*      */                 }
/*      */               }
/*      */               else {
/*  866 */                 sender.sendMessage(ChatColor.RED + "Unknown operator. Choose for" + ChatColor.GREEN + " Set");
/*      */               }
/*      */             }
/*      */             else {
/*  870 */               sender.sendMessage(ChatColor.RED + "Use the command like this " + ChatColor.GREEN + "/bb Time <Operator> [Value] <ArenaName>");
/*      */             }
/*      */           }
/*      */           else {
/*  874 */             sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */           }
/*      */         }
/*  877 */         else if ((args[0].equalsIgnoreCase("broadcast")) || (args[0].equalsIgnoreCase("broadcasts"))) {
/*  878 */           if (sender.hasPermission("bb.broadcast")) {
/*  879 */             if (args.length >= 2) {
/*  880 */               if (args[1].equalsIgnoreCase("add")) {
/*  881 */                 if (args.length >= 4) {
/*  882 */                   if (this.plugin.arenas.containsKey(args[3].toLowerCase())) {
/*  883 */                     Integer time = Integer.valueOf(0);
/*      */                     try {
/*  885 */                       time = Integer.valueOf(Integer.parseInt(args[2]));
/*      */                     } catch (NumberFormatException e) {
/*  887 */                       sender.sendMessage(ChatColor.RED + "Needs to be a whole number");
/*      */                     }
/*  889 */                     if (!((Arena)this.plugin.arenas.get(args[3].toLowerCase())).getMessages().containsKey(time)) {
/*  890 */                       if (((Arena)this.plugin.arenas.get(args[3].toLowerCase())).getDropTime().intValue() > time.intValue()) {
/*  891 */                         if (time.intValue() > 0) {
/*  892 */                           ((Arena)this.plugin.arenas.get(args[3].toLowerCase())).getMessages().put(time, "");
/*  893 */                           Integer seconds = time;
/*  894 */                           Integer min = Integer.valueOf(0);
/*  895 */                           while (seconds.intValue() >= 60) {
/*  896 */                             seconds = Integer.valueOf(seconds.intValue() - 60);
/*  897 */                             min = Integer.valueOf(min.intValue() + 1);
/*      */                           }
/*  899 */                           if ((min.intValue() != 0) && (seconds.intValue() == 0)) {
/*  900 */                             sender.sendMessage(ChatColor.GOLD + "A broadcast has been set to " + min + " minute(s) for the arena " + args[3].toLowerCase());
/*      */                           }
/*  902 */                           else if ((seconds.intValue() != 0) && (min.intValue() == 0)) {
/*  903 */                             sender.sendMessage(ChatColor.GOLD + "A broadcast has been set to " + seconds + " second(s) for the arena " + args[3].toLowerCase());
/*      */                           }
/*  905 */                           else if ((seconds.intValue() != 0) && (min.intValue() != 0))
/*  906 */                             sender.sendMessage(ChatColor.GOLD + "A broadcast has been set to " + min + " minute(s) and " + seconds + " second(s) for the arena " + args[3].toLowerCase());
/*      */                         }
/*      */                         else
/*      */                         {
/*  910 */                           sender.sendMessage(ChatColor.RED + "The time you want to add must be 1 second or more");
/*      */                         }
/*      */                       }
/*      */                       else {
/*  914 */                         sender.sendMessage(ChatColor.RED + "You can't have a message that is going to be before the game starts, Make the number smaller");
/*      */                       }
/*      */                     }
/*      */                     else
/*  918 */                       sender.sendMessage(ChatColor.RED + "You are already broadcasting a message then!");
/*      */                   }
/*      */                   else
/*      */                   {
/*  922 */                     sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */                   }
/*      */                 }
/*      */                 else {
/*  926 */                   sender.sendMessage(ChatColor.RED + "Use the command like this " + ChatColor.GREEN + "/bb Broadcast Add <Time> <ArenaName>");
/*      */                 }
/*      */               }
/*  929 */               else if ((args[1].equalsIgnoreCase("remove")) || (args[1].equalsIgnoreCase("delete"))) {
/*  930 */                 if (args.length >= 4) {
/*  931 */                   if (this.plugin.arenas.containsKey(args[3].toLowerCase())) {
/*  932 */                     Integer timer = Integer.valueOf(0);
/*      */                     try {
/*  934 */                       timer = Integer.valueOf(Integer.parseInt(args[2]));
/*      */                     } catch (NumberFormatException e) {
/*  936 */                       sender.sendMessage(ChatColor.RED + "The time that you put in must be a whole number");
/*      */                     }
/*  938 */                     if (((Arena)this.plugin.arenas.get(args[3].toLowerCase())).getMessages().containsKey(timer)) {
/*  939 */                       ((Arena)this.plugin.arenas.get(args[3].toLowerCase())).getMessages().remove(timer);
/*  940 */                       Integer seconds = timer;
/*  941 */                       Integer min = Integer.valueOf(0);
/*  942 */                       while (seconds.intValue() >= 60) {
/*  943 */                         seconds = Integer.valueOf(seconds.intValue() - 60);
/*  944 */                         min = Integer.valueOf(min.intValue() + 1);
/*      */                       }
/*  946 */                       if ((min.intValue() != 0) && (seconds.intValue() == 0)) {
/*  947 */                         sender.sendMessage(ChatColor.GOLD + "Deleted Broadcast at the time " + min + " minute(s) for the arena " + args[3].toLowerCase());
/*      */                       }
/*  949 */                       else if ((seconds.intValue() != 0) && (min.intValue() == 0)) {
/*  950 */                         sender.sendMessage(ChatColor.GOLD + "Deleted Broadcast at the time " + seconds + " second(s) for the arena " + args[3].toLowerCase());
/*      */                       }
/*  952 */                       else if ((seconds.intValue() != 0) && (min.intValue() != 0))
/*  953 */                         sender.sendMessage(ChatColor.GOLD + "Deleted Broadcast at the time " + min + " minute(s) and " + seconds + " second(s) for the arena " + args[3].toLowerCase());
/*      */                     }
/*      */                     else
/*      */                     {
/*  957 */                       sender.sendMessage(ChatColor.RED + "There is no broadcast at that time so can't delete it!");
/*      */                     }
/*      */                   }
/*      */                   else {
/*  961 */                     sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */                   }
/*      */                 }
/*      */                 else {
/*  965 */                   sender.sendMessage(ChatColor.RED + "Use the command like this " + ChatColor.GREEN + "/bb Broadcasts Remove <Time> <ArenaName>");
/*      */                 }
/*      */               }
/*      */               else {
/*  969 */                 sender.sendMessage(ChatColor.RED + "Unknown operator. Choose for" + ChatColor.GREEN + " Add, Remove");
/*      */               }
/*      */             }
/*      */             else {
/*  973 */               sender.sendMessage(ChatColor.RED + "Use the command like this " + ChatColor.GREEN + "/bb Broadcast <Operator> [Value] <ArenaName>");
/*      */             }
/*      */           }
/*      */           else {
/*  977 */             sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */           }
/*      */         }
/*  980 */         else if ((args[0].equalsIgnoreCase("allowedcommands")) || (args[0].equals("allowedcommand")) || (args[0].equalsIgnoreCase("allowedcommands")) || (args[0].equalsIgnoreCase("allowedcommand")) || (args[0].equalsIgnoreCase("ac"))) {
/*  981 */           if (sender.hasPermission("bb.commands")) {
/*  982 */             if (args.length >= 2) {
/*  983 */               if (args[1].equalsIgnoreCase("add")) {
/*  984 */                 if (args.length == 3) {
/*  985 */                   if (!this.plugin.allowedCommands.contains(args[2].toLowerCase())) {
/*  986 */                     sender.sendMessage(ChatColor.GOLD + "The command " + args[2].toLowerCase() + " has been allowed in the walls!");
/*  987 */                     this.plugin.allowedCommands.add(args[2].toLowerCase());
/*      */                   }
/*      */                   else {
/*  990 */                     sender.sendMessage(ChatColor.RED + "That command has already been blocked");
/*      */                   }
/*      */                 }
/*      */                 else {
/*  994 */                   sender.sendMessage(ChatColor.RED + "Use the command like this " + ChatColor.GREEN + "/bb AllowedCommand Add <AllowedCommand>");
/*      */                 }
/*      */               }
/*  997 */               else if (args[1].equalsIgnoreCase("list")) {
/*  998 */                 Iterator allowedCommand = this.plugin.allowedCommands.iterator();
/*  999 */                 String commands = ChatColor.DARK_GRAY + "Allowed Commands: ";
/* 1000 */                 while (allowedCommand.hasNext()) {
/* 1001 */                   commands = commands + (String)allowedCommand.next() + ChatColor.WHITE + ", " + ChatColor.DARK_GRAY;
/*      */                 }
/* 1003 */                 sender.sendMessage(commands);
/*      */               }
/* 1005 */               else if ((args[1].equalsIgnoreCase("remove")) || (args[1].equalsIgnoreCase("delete"))) {
/* 1006 */                 if (args.length == 3) {
/* 1007 */                   if (this.plugin.allowedCommands.contains(args[2].toLowerCase())) {
/* 1008 */                     sender.sendMessage(ChatColor.GOLD + "The command " + args[2].toLowerCase() + " has been blocked again!");
/* 1009 */                     this.plugin.allowedCommands.remove(args[2].toLowerCase());
/*      */                   }
/*      */                   else {
/* 1012 */                     sender.sendMessage(ChatColor.RED + "That command has not been allowed so you can't block it again");
/*      */                   }
/*      */                 }
/*      */                 else
/* 1016 */                   sender.sendMessage(ChatColor.RED + "Use the command like this " + ChatColor.GREEN + "/bb AllowedCommand Remove <AllowedCommand>");
/*      */               }
/*      */               else
/*      */               {
/* 1020 */                 sender.sendMessage(ChatColor.RED + "Unknown operator. Choose for" + ChatColor.GREEN + " Add, Remove, List");
/*      */               }
/*      */             }
/*      */             else {
/* 1024 */               sender.sendMessage(ChatColor.RED + "Use the command like this " + ChatColor.GREEN + "/Walls AllowedCommand <Operator> [Value]");
/*      */             }
/*      */           }
/*      */           else {
/* 1028 */             sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */           }
/*      */         }
/* 1031 */         else if ((args[0].equalsIgnoreCase("players")) || (args[0].equalsIgnoreCase("player"))) {
/* 1032 */           if (args.length == 1) {
/* 1033 */             if (sender.hasPermission("bb.players")) {
/* 1034 */               Player player = (Player)sender;
/* 1035 */               Arena arena = (Arena)this.plugin.arenas.get(this.plugin.playerarena.get(player));
/* 1036 */               if (this.plugin.playerarena.get((Player)sender) != null)
/*      */               {
/*      */                 String greens;
/* 1037 */                 if (arena.getStarted().booleanValue()) {
/* 1038 */                   String reds = ChatColor.RED + "Reds: " + ChatColor.DARK_GRAY + " ";
/* 1039 */                   String blues = ChatColor.BLUE + "Blues: " + ChatColor.DARK_GRAY + " ";
/* 1040 */                   greens = ChatColor.GREEN + "Greens: " + ChatColor.DARK_GRAY + " ";
/* 1041 */                   String yellows = ChatColor.YELLOW + "Yellows: " + ChatColor.DARK_GRAY + " ";
/* 1042 */                   Iterator itr = arena.getPlayerList().keySet().iterator();
/* 1043 */                   while (itr.hasNext()) {
/* 1044 */                     Player play = (Player)itr.next();
/* 1045 */                     if (arena.getPlayerList().get(play) == "red") {
/* 1046 */                       reds = reds + play.getName() + ", ";
/*      */                     }
/* 1048 */                     if (arena.getPlayerList().get(play) == "blue") {
/* 1049 */                       blues = blues + play.getName() + ", ";
/*      */                     }
/* 1051 */                     if (arena.getPlayerList().get(play) == "green") {
/* 1052 */                       greens = greens + play.getName() + ", ";
/*      */                     }
/* 1054 */                     if (arena.getPlayerList().get(play) == "yellow") {
/* 1055 */                       yellows = yellows + play.getName() + ", ";
/*      */                     }
/*      */                   }
/* 1058 */                   sender.sendMessage(reds);
/* 1059 */                   sender.sendMessage(blues);
/* 1060 */                   sender.sendMessage(greens);
/* 1061 */                   sender.sendMessage(yellows);
/*      */                 }
/*      */                 else {
/* 1064 */                   String lobbyPlayers = ChatColor.GOLD + "Lobby: " + ChatColor.DARK_GRAY;
/* 1065 */                   for (Player p : arena.getLobbyPlayers()) {
/* 1066 */                     lobbyPlayers = lobbyPlayers + p.getName();
/*      */                   }
/* 1068 */                   sender.sendMessage(lobbyPlayers);
/*      */                 }
/*      */               }
/*      */               else {
/* 1072 */                 sender.sendMessage(ChatColor.RED + "You are not in an arena. Please state an arena name!");
/*      */               }
/*      */             }
/*      */             else {
/* 1076 */               sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */             }
/*      */ 
/*      */           }
/* 1080 */           else if (sender.hasPermission("bb.players.othergames")) {
/* 1081 */             if (this.plugin.arenas.containsKey(args[1].toLowerCase()))
/*      */             {
/*      */               String blues;
/* 1082 */               if (((Arena)this.plugin.arenas.get(args[1].toLowerCase())).getStarted().booleanValue()) {
/* 1083 */                 Arena arena = (Arena)this.plugin.arenas.get(args[1].toLowerCase());
/* 1084 */                 Iterator itr = arena.getPlayerList().keySet().iterator();
/* 1085 */                 String reds = ChatColor.RED + "Reds: " + ChatColor.DARK_GRAY + " ";
/* 1086 */                 blues = ChatColor.BLUE + "Blues: " + ChatColor.DARK_GRAY + " ";
/* 1087 */                 String greens = ChatColor.GREEN + "Greens: " + ChatColor.DARK_GRAY + " ";
/* 1088 */                 String yellows = ChatColor.YELLOW + "Yellows: " + ChatColor.DARK_GRAY + " ";
/* 1089 */                 while (itr.hasNext()) {
/* 1090 */                   Player play = (Player)itr.next();
/* 1091 */                   if (arena.getPlayerList().get(play) == "red") {
/* 1092 */                     reds = reds + play.getName() + ", ";
/*      */                   }
/* 1094 */                   if (arena.getPlayerList().get(play) == "blue") {
/* 1095 */                     blues = blues + play.getName() + ", ";
/*      */                   }
/* 1097 */                   if (arena.getPlayerList().get(play) == "green") {
/* 1098 */                     greens = greens + play.getName() + ", ";
/*      */                   }
/* 1100 */                   if (arena.getPlayerList().get(play) == "yellow") {
/* 1101 */                     yellows = yellows + play.getName() + ", ";
/*      */                   }
/*      */                 }
/* 1104 */                 sender.sendMessage(reds);
/* 1105 */                 sender.sendMessage(blues);
/* 1106 */                 sender.sendMessage(greens);
/* 1107 */                 sender.sendMessage(yellows);
/*      */               }
/*      */               else {
/* 1110 */                 Arena arena = (Arena)this.plugin.arenas.get(args[1].toLowerCase());
/* 1111 */                 String lobbyPlayers = ChatColor.GOLD + "Lobby: " + ChatColor.DARK_GRAY;
/* 1112 */                 for (Player p : arena.getLobbyPlayers()) {
/* 1113 */                   lobbyPlayers = lobbyPlayers + p.getName();
/*      */                 }
/* 1115 */                 sender.sendMessage(lobbyPlayers);
/*      */               }
/*      */             }
/*      */             else {
/* 1119 */               sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */             }
/*      */           }
/*      */           else {
/* 1123 */             sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */           }
/*      */ 
/*      */         }
/* 1127 */         else if (args[0].equalsIgnoreCase("save")) {
/* 1128 */           if ((sender.hasPermission("bb.save")) || (sender.hasPermission("bb.save.config")) || (sender.hasPermission("bb.save.arena"))) {
/* 1129 */             if (args.length >= 2) {
/* 1130 */               if (args[1].equalsIgnoreCase("config")) {
/* 1131 */                 if ((sender.hasPermission("bb.save")) || (sender.hasPermission("bb.save.config"))) {
/* 1132 */                   this.plugin.SaveConfig();
/* 1133 */                   sender.sendMessage(ChatColor.GREEN + "Saved the config!");
/*      */                 }
/*      */                 else {
/* 1136 */                   sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */                 }
/*      */               }
/* 1139 */               else if (args[1].equalsIgnoreCase("arena")) {
/* 1140 */                 if (args.length >= 3) {
/* 1141 */                   if (this.plugin.arenas.containsKey(args[2].toLowerCase())) {
/* 1142 */                     sender.sendMessage(ChatColor.GOLD + "Saving the arena!");
/* 1143 */                     Arena arena = (Arena)this.plugin.arenas.get(args[2].toLowerCase());
/* 1144 */                     ArenaFileManager.saveToAFile(args[2].toLowerCase(), arena.getLoc1(), arena.getLoc2());
/* 1145 */                     sender.sendMessage(ChatColor.GOLD + "Saved the arena!");
/*      */                   }
/*      */                   else {
/* 1148 */                     sender.sendMessage(ChatColor.RED + "That arena does not exist");
/*      */                   }
/*      */                 }
/*      */                 else {
/* 1152 */                   sender.sendMessage(ChatColor.RED + "Use the command like this " + ChatColor.GREEN + "/bb Save Arena <ArenaName>");
/*      */                 }
/*      */               }
/*      */               else {
/* 1156 */                 sender.sendMessage(ChatColor.RED + "Unknown file to save, Choose from: " + ChatColor.GREEN + "Config, Arena");
/*      */               }
/*      */             }
/*      */             else {
/* 1160 */               sender.sendMessage(ChatColor.RED + "Use the command like this " + ChatColor.GREEN + "/Walls Save <Config/Arena> [ArenaName]");
/*      */             }
/*      */           }
/*      */           else {
/* 1164 */             sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
/*      */           }
/*      */         }
/*      */         else
/* 1168 */           sender.sendMessage(ChatColor.RED + "Unknown sub-command. Type /bb help for more info!");
/*      */       }
/*      */     }
/*      */     else {
/* 1172 */       sender.sendMessage(ChatColor.GREEN + "Only players can use that command");
/*      */     }
/* 1174 */     return true;
/*      */   }
/*      */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.BridgesCommand
 * JD-Core Version:    0.6.2
 */