/*     */ package RDNachoz.plugins.bridges;
/*     */ 
/*     */ import RDNachoz.plugins.bridges.blockFileData.ArenaFileManager;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.block.Sign;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.kitteh.tag.TagAPI;
/*     */ 
/*     */ public class Arena
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5169004947980681473L;
/*     */   private boolean resetting;
/*     */   private Integer files;
/*     */   private String name;
/*     */   private Integer countToEnd;
/*     */   private Location loc1;
/*     */   private Location loc2;
/*  32 */   private Map<Integer, Location> blocksLocation = new HashMap();
/*  33 */   private Map<Integer, BlockState> blockstype = new HashMap();
/*  34 */   private Map<Integer, Location> redspawns = new HashMap();
/*  35 */   private Map<Integer, Location> bluespawns = new HashMap();
/*  36 */   private Map<Integer, Location> yellowspawns = new HashMap();
/*  37 */   private Map<Integer, Location> greenspawns = new HashMap();
/*  38 */   private Set<Player> lobbyPlayers = new HashSet();
/*     */   private Integer blocknumber;
/*     */   private Boolean started;
/*     */   private Location win;
/*     */   private Location lose;
/*     */   private Integer reds;
/*     */   private Integer blues;
/*     */   private Integer yellows;
/*     */   private Integer greens;
/*     */   private Location playerLoc;
/*     */   private Integer min;
/*  49 */   private Map<Player, String> playerList = new HashMap();
/*     */   private Integer redplayers;
/*     */   private Integer blueplayers;
/*     */   private Integer greenplayers;
/*     */   private Integer yellowplayers;
/*     */   private Integer counter;
/*     */   private Player random;
/*  56 */   private Map<Integer, Location> drop = new HashMap();
/*     */   private Integer drops;
/*     */   private Integer total;
/*     */   private Location tempLoc;
/*     */   private Location tempLoc1;
/*     */   private String temp;
/*     */   private Location lobby;
/*  63 */   private Map<Player, Location> playerLocation = new HashMap();
/*  64 */   private Map<Player, String> deadplayers = new HashMap();
/*  65 */   private Map<Location, ItemStack[]> chests = new HashMap();
/*  66 */   private Map<Player, ItemStack[]> playerInventories = new HashMap();
/*  67 */   private Map<Integer, Location> droploc1 = new HashMap();
/*  68 */   private Map<Integer, Location> droploc2 = new HashMap();
/*  69 */   private Map<Player, ItemStack[]> aurmor = new HashMap();
/*  70 */   private Map<Player, Integer> exp = new HashMap();
/*  71 */   private Map<Location, String> signLine0 = new HashMap();
/*  72 */   private Map<Location, String> signLine1 = new HashMap();
/*  73 */   private Map<Location, String> signLine2 = new HashMap();
/*  74 */   private Map<Location, String> signLine3 = new HashMap();
/*  75 */   private Map<Integer, Location> saveregion = new HashMap();
/*  76 */   private Map<Integer, Location> saveregion1 = new HashMap();
/*     */   private Integer saveTotal;
/*     */   private Integer num;
/*     */   private boolean showing;
/*     */   private Integer showcount;
/*  81 */   private Map<Location, BlockState> showStore = new HashMap();
/*     */   private Player seeing;
/*     */   private Integer totalDrops;
/*     */   private Integer dropTime;
/*  85 */   private Map<Integer, String> messages = new HashMap();
/*  86 */   private Map<Integer, SignWall> signWalls = new HashMap();
/*     */ 
/*     */   public Arena(String name, Location loc1, Location loc2, Integer blocknumber, Boolean started, Location lose, Location win, Location playerLoc, Integer reds, Integer greens, Integer blues, Integer yellows, Integer min, Integer redplayers, Integer blueplayers, Integer greenplayers, Integer yellowplayers, Integer counter, Player random, Integer drops, Integer total, Location tempLoc, Location tempLoc1, String temp, Location lobby, Integer saveTotal, boolean showing, Integer totalDrops, Bridges walls)
/*     */   {
/*  90 */     this.name = name;
/*  91 */     this.loc1 = loc1;
/*  92 */     this.loc2 = loc2;
/*  93 */     this.blocknumber = blocknumber;
/*  94 */     this.started = started;
/*  95 */     this.win = win;
/*  96 */     this.lose = lose;
/*  97 */     this.playerLoc = playerLoc;
/*  98 */     this.greens = greens;
/*  99 */     this.yellows = yellows;
/* 100 */     this.blues = blues;
/* 101 */     this.reds = reds;
/* 102 */     this.min = min;
/* 103 */     this.redplayers = redplayers;
/* 104 */     this.blueplayers = blueplayers;
/* 105 */     this.yellowplayers = yellowplayers;
/* 106 */     this.greenplayers = greenplayers;
/* 107 */     this.counter = counter;
/* 108 */     this.random = random;
/* 109 */     this.drops = drops;
/* 110 */     this.total = total;
/* 111 */     this.tempLoc = tempLoc;
/* 112 */     this.tempLoc1 = tempLoc1;
/* 113 */     this.temp = temp;
/* 114 */     this.lobby = lobby;
/* 115 */     this.saveTotal = saveTotal;
/* 116 */     this.showing = showing;
/* 117 */     this.totalDrops = totalDrops;
/* 118 */     this.resetting = false;
/*     */   }
/*     */ 
/*     */   public void playerJoin()
/*     */   {
/* 123 */     Iterator signs = this.signWalls.keySet().iterator();
/* 124 */     while (signs.hasNext())
/* 125 */       ((SignWall)this.signWalls.get(signs.next())).updateSigns(this.min, this.total, this.playerList);
/*     */   }
/*     */ 
/*     */   public void playerAdd(String addedPlayer) {
/* 129 */     Iterator signs = this.signWalls.keySet().iterator();
/* 130 */     while (signs.hasNext())
/* 131 */       ((SignWall)this.signWalls.get(signs.next())).updateSigns(this.min, this.total, this.playerList);
/*     */   }
/*     */ 
/*     */   public void countToTheEnd()
/*     */   {
/* 136 */     if (this.countToEnd != null) {
/* 137 */       this.countToEnd = Integer.valueOf(this.countToEnd.intValue() + 1);
/* 138 */       if (this.countToEnd.intValue() == 5) {
/* 139 */         End();
/* 140 */         this.countToEnd = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void RestoreBlocks() {
/* 146 */     this.started = Boolean.valueOf(false);
/* 147 */     ArenaFileManager.loadArenaFiles(this.name, Integer.valueOf(1));
/*     */   }
/*     */   public void counter() {
/* 150 */     if (this.counter != null) {
/* 151 */       if (getCounter().intValue() == 10) {
/* 152 */         onGameStart();
/* 153 */         Iterator itr = this.signWalls.keySet().iterator();
/* 154 */         while (itr.hasNext()) {
/* 155 */           Integer num = (Integer)itr.next();
/* 156 */           ((SignWall)this.signWalls.get(num)).setProgress("Starting");
/* 157 */           ((SignWall)this.signWalls.get(num)).setStarted(true);
/*     */         }
/*     */       }
/* 160 */       if (getCounter().intValue() == 60) {
/* 161 */         Iterator list = this.playerList.keySet().iterator();
/* 162 */         while (list.hasNext()) {
/* 163 */           Player name = (Player)list.next();
/* 164 */           if (getPlayerList().get(name) != null) {
/* 165 */             name.sendMessage(ChatColor.BLUE + "Game starting in 1 min!");
/*     */           }
/*     */         }
/*     */       }
/* 169 */       if (getCounter().intValue() == 30) {
/* 170 */         Iterator list = this.playerList.keySet().iterator();
/* 171 */         while (list.hasNext()) {
/* 172 */           Player name = (Player)list.next();
/* 173 */           if (getPlayerList().get(name) != null) {
/* 174 */             name.sendMessage(ChatColor.BLUE + "Game starting in 30 seconds!");
/*     */           }
/*     */         }
/*     */       }
/* 178 */       else if ((getCounter().intValue() <= 10) && (getCounter().intValue() >= 1)) {
/* 179 */         Iterator list = this.playerList.keySet().iterator();
/* 180 */         while (list.hasNext()) {
/* 181 */           Player name = (Player)list.next();
/* 182 */           if (getPlayerList().get(name) != null) {
/* 183 */             name.sendMessage(ChatColor.BLUE + "Game starting in " + this.counter + " seconds!");
/*     */           }
/*     */         }
/*     */       }
/* 187 */       else if (getCounter().intValue() == 0) {
/* 188 */         this.started = Boolean.valueOf(true);
/* 189 */         Iterator list = this.playerList.keySet().iterator();
/* 190 */         while (list.hasNext()) {
/* 191 */           Player name = (Player)list.next();
/* 192 */           if (getPlayerList().get(name) != null) {
/* 193 */             name.sendMessage(ChatColor.BLUE + "Goooo!");
/* 194 */             name.setGameMode(GameMode.SURVIVAL);
/*     */           }
/*     */         }
/* 197 */         Iterator itr = this.signWalls.keySet().iterator();
/* 198 */         while (itr.hasNext()) {
/* 199 */           Integer num = (Integer)itr.next();
/* 200 */           ((SignWall)this.signWalls.get(num)).setProgress("Forming in:");
/*     */         }
/*     */       }
/* 203 */       if (this.counter.intValue() == -getDropTime().intValue()) {
/* 204 */         Iterator itr = this.signWalls.keySet().iterator();
/* 205 */         while (itr.hasNext()) {
/* 206 */           Integer num = (Integer)itr.next();
/* 207 */           ((SignWall)this.signWalls.get(num)).setProgress("Bridges Formed");
/* 208 */           ((SignWall)this.signWalls.get(num)).setTime(null);
/*     */         }
/* 210 */         Dropwalls();
/* 211 */         Iterator list = this.playerList.keySet().iterator();
/* 212 */         while (list.hasNext()) {
/* 213 */           Player name = (Player)list.next();
/* 214 */           if (getPlayerList().get(name) != null) {
/* 215 */             name.sendMessage(ChatColor.RED + ChatColor.BOLD + "Bridges Have Formed!");
/*     */           }
/*     */         }
/*     */       }
/* 219 */       if ((this.messages.containsKey(Integer.valueOf(this.dropTime.intValue() + this.counter.intValue()))) && (getCounter().intValue() < 0) && (getCounter().intValue() > -getDropTime().intValue())) {
/* 220 */         Integer seconds = Integer.valueOf(this.dropTime.intValue() + this.counter.intValue());
/* 221 */         Integer mins = Integer.valueOf(0);
/* 222 */         while (seconds.intValue() >= 60) {
/* 223 */           seconds = Integer.valueOf(seconds.intValue() - 60);
/* 224 */           mins = Integer.valueOf(mins.intValue() + 1);
/*     */         }
/* 226 */         if ((mins.intValue() != 0) && (seconds.intValue() != 0)) {
/* 227 */           Iterator list = this.playerList.keySet().iterator();
/* 228 */           while (list.hasNext()) {
/* 229 */             Player name = (Player)list.next();
/* 230 */             if (getPlayerList().get(name) != null) {
/* 231 */               name.sendMessage(ChatColor.RED + "Bridges forming in " + mins + " minutes and " + seconds + " seconds");
/*     */             }
/*     */           }
/*     */         }
/* 235 */         else if ((mins.intValue() == 0) && (seconds.intValue() != 0)) {
/* 236 */           Iterator list = this.playerList.keySet().iterator();
/* 237 */           while (list.hasNext()) {
/* 238 */             Player name = (Player)list.next();
/* 239 */             if (getPlayerList().get(name) != null) {
/* 240 */               name.sendMessage(ChatColor.RED + "Bridges forming in " + seconds + " seconds");
/*     */             }
/*     */           }
/*     */         }
/* 244 */         else if ((mins.intValue() != 0) && (seconds.intValue() == 0)) {
/* 245 */           Iterator list = this.playerList.keySet().iterator();
/* 246 */           while (list.hasNext()) {
/* 247 */             Player name = (Player)list.next();
/* 248 */             if (getPlayerList().get(name) != null) {
/* 249 */               name.sendMessage(ChatColor.RED + "Bridges forming in " + mins + " minutes");
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 254 */       this.counter = Integer.valueOf(this.counter.intValue() - 1);
/* 255 */       Iterator itr = this.signWalls.keySet().iterator();
/* 256 */       while (itr.hasNext()) {
/* 257 */         Integer num = (Integer)itr.next();
/* 258 */         if ((this.counter.intValue() > -this.dropTime.intValue()) && (this.counter.intValue() < 0)) {
/* 259 */           ((SignWall)this.signWalls.get(num)).setTime(Integer.valueOf(this.dropTime.intValue() + this.counter.intValue()));
/* 260 */           ((SignWall)this.signWalls.get(num)).updateSigns(this.min, this.total, this.playerList);
/*     */         }
/*     */         else {
/* 263 */           ((SignWall)this.signWalls.get(num)).setTime(null);
/* 264 */           ((SignWall)this.signWalls.get(num)).updateSigns(this.min, this.total, this.playerList);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void Dropwalls() {
/* 271 */     Iterator list = this.drop.keySet().iterator();
/* 272 */     while (list.hasNext()) {
/* 273 */       Integer name = (Integer)list.next();
/* 274 */       World world = ((Location)this.drop.get(name)).getWorld();
/* 275 */       world.getBlockAt((Location)this.drop.get(name)).setTypeId(4);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void Leave()
/*     */   {
/* 281 */     if (this.playerList.get(this.random) == "red") {
/* 282 */       this.redplayers = Integer.valueOf(this.redplayers.intValue() - 1);
/*     */     }
/* 284 */     else if (this.playerList.get(this.random) == "blue") {
/* 285 */       this.blueplayers = Integer.valueOf(this.blueplayers.intValue() - 1);
/*     */     }
/* 287 */     else if (this.playerList.get(this.random) == "green") {
/* 288 */       this.greenplayers = Integer.valueOf(this.greenplayers.intValue() - 1);
/*     */     }
/* 290 */     else if (this.playerList.get(this.random) == "yellow") {
/* 291 */       this.yellowplayers = Integer.valueOf(this.yellowplayers.intValue() - 1);
/*     */     }
/* 293 */     this.total = Integer.valueOf(this.total.intValue() - 1);
/* 294 */     this.random.setGameMode(GameMode.SURVIVAL);
/* 295 */     Iterator itr = this.signWalls.keySet().iterator();
/* 296 */     while (itr.hasNext()) {
/* 297 */       Integer num = (Integer)itr.next();
/* 298 */       ((SignWall)this.signWalls.get(num)).updateSigns(this.min, this.total, this.playerList);
/*     */     }
/* 300 */     this.random.getInventory().setContents((ItemStack[])this.playerInventories.get(this.random));
/* 301 */     this.random.getInventory().setArmorContents((ItemStack[])this.aurmor.get(this.random));
/* 302 */     this.random.updateInventory();
/* 303 */     this.random.teleport(this.lose);
/* 304 */     this.random.setLevel(((Integer)this.exp.get(this.random)).intValue());
/* 305 */     this.random.setFallDistance(0.0F);
/* 306 */     this.random.setFireTicks(0);
/*     */   }
/*     */   public void onGameStart() {
/* 309 */     this.lobbyPlayers.clear();
/* 310 */     Iterator list = this.playerList.keySet().iterator();
/* 311 */     while (list.hasNext()) {
/* 312 */       Player name = (Player)list.next();
/* 313 */       Integer temp = Integer.valueOf(Math.min(Math.min(this.redplayers.intValue(), this.greenplayers.intValue()), Math.min(this.blueplayers.intValue(), this.yellowplayers.intValue())));
/* 314 */       if (temp == this.redplayers) {
/* 315 */         this.redplayers = Integer.valueOf(this.redplayers.intValue() + 1);
/* 316 */         TagAPI.refreshPlayer(name);
/* 317 */         name.teleport((Location)this.redspawns.get(this.redplayers));
/* 318 */         this.playerLocation.put(name, (Location)this.redspawns.get(this.redplayers));
/* 319 */         this.playerList.put(name, "red");
/* 320 */         name.sendMessage(ChatColor.RED + "You are on team red!");
/* 321 */         Iterator itr = this.signWalls.keySet().iterator();
/* 322 */         while (itr.hasNext()) {
/* 323 */           Integer num = (Integer)itr.next();
/* 324 */           Sign sign = (Sign)((Location)((SignWall)this.signWalls.get(num)).getSignLocations().get(Integer.valueOf(this.redplayers.intValue() + 2))).getBlock().getState();
/* 325 */           sign.setLine(0, ChatColor.DARK_RED + name.getName());
/* 326 */           sign.update();
/*     */         }
/*     */       }
/* 329 */       else if (temp == this.blueplayers) {
/* 330 */         this.blueplayers = Integer.valueOf(this.blueplayers.intValue() + 1);
/* 331 */         TagAPI.refreshPlayer(name);
/* 332 */         name.teleport((Location)this.bluespawns.get(this.blueplayers));
/* 333 */         this.playerLocation.put(name, (Location)this.bluespawns.get(this.blueplayers));
/* 334 */         this.playerList.put(name, "blue");
/* 335 */         name.sendMessage(ChatColor.DARK_BLUE + "You are on team blue!");
/* 336 */         Iterator itr = this.signWalls.keySet().iterator();
/* 337 */         while (itr.hasNext()) {
/* 338 */           Integer num = (Integer)itr.next();
/* 339 */           Sign sign = (Sign)((Location)((SignWall)this.signWalls.get(num)).getSignLocations().get(Integer.valueOf(this.blueplayers.intValue() + 2))).getBlock().getState();
/* 340 */           sign.setLine(1, ChatColor.DARK_BLUE + name.getName());
/* 341 */           sign.update();
/*     */         }
/*     */       }
/* 344 */       else if (temp == this.greenplayers) {
/* 345 */         this.greenplayers = Integer.valueOf(this.greenplayers.intValue() + 1);
/* 346 */         TagAPI.refreshPlayer(name);
/* 347 */         name.teleport((Location)this.greenspawns.get(this.greenplayers));
/* 348 */         this.playerLocation.put(name, (Location)this.greenspawns.get(this.greenplayers));
/* 349 */         this.playerList.put(name, "green");
/* 350 */         name.sendMessage(ChatColor.GREEN + "You are on team green!");
/* 351 */         Iterator itr = this.signWalls.keySet().iterator();
/* 352 */         while (itr.hasNext()) {
/* 353 */           Integer num = (Integer)itr.next();
/* 354 */           Sign sign = (Sign)((Location)((SignWall)this.signWalls.get(num)).getSignLocations().get(Integer.valueOf(this.greenplayers.intValue() + 2))).getBlock().getState();
/* 355 */           sign.setLine(2, ChatColor.DARK_GREEN + name.getName());
/* 356 */           sign.update();
/*     */         }
/*     */       }
/* 359 */       else if (temp == this.yellowplayers) {
/* 360 */         this.yellowplayers = Integer.valueOf(this.yellowplayers.intValue() + 1);
/* 361 */         TagAPI.refreshPlayer(name);
/* 362 */         name.teleport((Location)this.yellowspawns.get(this.yellowplayers));
/* 363 */         this.playerLocation.put(name, (Location)this.yellowspawns.get(this.yellowplayers));
/* 364 */         this.playerList.put(name, "yellow");
/* 365 */         name.sendMessage(ChatColor.YELLOW + "You are on team yellow!");
/* 366 */         Iterator itr = this.signWalls.keySet().iterator();
/* 367 */         while (itr.hasNext()) {
/* 368 */           Integer num = (Integer)itr.next();
/* 369 */           Sign sign = (Sign)((Location)((SignWall)this.signWalls.get(num)).getSignLocations().get(Integer.valueOf(this.yellowplayers.intValue() + 2))).getBlock().getState();
/* 370 */           sign.setLine(3, ChatColor.YELLOW + name.getName());
/* 371 */           sign.update();
/*     */         }
/*     */       }
/* 374 */       name.setHealth(20);
/* 375 */       name.setFoodLevel(20);
/* 376 */       name.setFireTicks(0);
/*     */     }
/*     */   }
/*     */ 
/* 380 */   public void CreateDropLocation() { Location start = this.tempLoc;
/* 381 */     Location end = this.tempLoc1;
/* 382 */     World world = this.tempLoc1.getWorld();
/* 383 */     Integer minX = Integer.valueOf((int)Math.min(start.getX(), end.getX()));
/* 384 */     Integer maxX = Integer.valueOf((int)Math.max(start.getX(), end.getX()));
/* 385 */     Integer minY = Integer.valueOf((int)Math.min(start.getY(), end.getY()));
/* 386 */     Integer maxY = Integer.valueOf((int)Math.max(start.getY(), end.getY()));
/* 387 */     Integer minZ = Integer.valueOf((int)Math.min(start.getZ(), end.getZ()));
/* 388 */     Integer maxZ = Integer.valueOf((int)Math.max(start.getZ(), end.getZ()));
/* 389 */     this.totalDrops = Integer.valueOf(this.totalDrops.intValue() + 1);
/* 390 */     this.droploc1.put(this.totalDrops, this.tempLoc);
/* 391 */     this.droploc2.put(this.totalDrops, this.tempLoc1);
/* 392 */     for (int x = minX.intValue(); x <= maxX.intValue(); x++)
/* 393 */       for (int y = minY.intValue(); y <= maxY.intValue(); y++)
/* 394 */         for (int z = minZ.intValue(); z <= maxZ.intValue(); z++) {
/* 395 */           this.drops = Integer.valueOf(this.drops.intValue() + 1);
/* 396 */           this.drop.put(this.drops, world.getBlockAt(x, y, z).getLocation());
/*     */         }
/*     */   }
/*     */ 
/*     */   public void broadcastWinner()
/*     */   {
/* 402 */     if (this.redplayers == this.total) {
/* 403 */       Bukkit.broadcastMessage(ChatColor.RED + "Red team has won the arena on arena " + getName());
/*     */     }
/* 405 */     else if (this.blueplayers == this.total) {
/* 406 */       Bukkit.broadcastMessage(ChatColor.BLUE + "Blue team has won the arena on arena " + getName());
/*     */     }
/* 408 */     else if (this.greenplayers == this.total) {
/* 409 */       Bukkit.broadcastMessage(ChatColor.GREEN + "Green team has won the arena on arena " + getName());
/*     */     }
/* 411 */     else if (this.yellowplayers == this.total)
/* 412 */       Bukkit.broadcastMessage(ChatColor.YELLOW + "Yellow team has won the arena on arena " + getName());
/*     */   }
/*     */ 
/*     */   public void End()
/*     */   {
/* 417 */     this.playerList.clear();
/* 418 */     this.redplayers = Integer.valueOf(0);
/* 419 */     this.blueplayers = Integer.valueOf(0);
/* 420 */     this.greenplayers = Integer.valueOf(0);
/* 421 */     this.yellowplayers = Integer.valueOf(0);
/* 422 */     this.started = Boolean.valueOf(false);
/* 423 */     this.total = Integer.valueOf(0);
/* 424 */     RestoreBlocks();
/* 425 */     this.counter = null;
/* 426 */     this.blocksLocation.clear();
/* 427 */     this.blockstype.clear();
/* 428 */     this.playerList.clear();
/* 429 */     Iterator itr = this.signWalls.keySet().iterator();
/* 430 */     while (itr.hasNext()) {
/* 431 */       Integer num = (Integer)itr.next();
/* 432 */       ((SignWall)this.signWalls.get(num)).setTime(null);
/* 433 */       ((SignWall)this.signWalls.get(num)).updateSigns(this.min, this.total, this.playerList);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void showRegion() {
/* 438 */     this.showing = true;
/* 439 */     this.showcount = Integer.valueOf(5);
/* 440 */     Player player = this.seeing;
/* 441 */     World world = ((Location)this.saveregion.get(this.num)).getWorld();
/* 442 */     for (int x = (int)((Location)this.saveregion1.get(this.num)).getX(); x <= (int)((Location)this.saveregion.get(this.num)).getX(); x++) {
/* 443 */       this.showStore.put(world.getBlockAt(x, (int)((Location)this.saveregion.get(this.num)).getY(), (int)((Location)this.saveregion.get(this.num)).getZ()).getLocation(), world.getBlockAt(x, (int)((Location)this.saveregion.get(this.num)).getY(), (int)((Location)this.saveregion.get(this.num)).getZ()).getState());
/* 444 */       this.showStore.put(world.getBlockAt(x, (int)((Location)this.saveregion.get(this.num)).getY(), (int)((Location)this.saveregion1.get(this.num)).getZ()).getLocation(), world.getBlockAt(x, (int)((Location)this.saveregion.get(this.num)).getY(), (int)((Location)this.saveregion1.get(this.num)).getZ()).getState());
/* 445 */       this.showStore.put(world.getBlockAt(x, (int)((Location)this.saveregion1.get(this.num)).getY(), (int)((Location)this.saveregion.get(this.num)).getZ()).getLocation(), world.getBlockAt(x, (int)((Location)this.saveregion1.get(this.num)).getY(), (int)((Location)this.saveregion.get(this.num)).getZ()).getState());
/* 446 */       this.showStore.put(world.getBlockAt(x, (int)((Location)this.saveregion1.get(this.num)).getY(), (int)((Location)this.saveregion1.get(this.num)).getZ()).getLocation(), world.getBlockAt(x, (int)((Location)this.saveregion1.get(this.num)).getY(), (int)((Location)this.saveregion1.get(this.num)).getZ()).getState());
/* 447 */       player.sendBlockChange(world.getBlockAt(x, (int)((Location)this.saveregion1.get(this.num)).getY(), (int)((Location)this.saveregion1.get(this.num)).getZ()).getLocation(), Material.GLOWSTONE, (byte)0);
/* 448 */       player.sendBlockChange(world.getBlockAt(x, (int)((Location)this.saveregion1.get(this.num)).getY(), (int)((Location)this.saveregion.get(this.num)).getZ()).getLocation(), Material.GLOWSTONE, (byte)0);
/* 449 */       player.sendBlockChange(world.getBlockAt(x, (int)((Location)this.saveregion.get(this.num)).getY(), (int)((Location)this.saveregion1.get(this.num)).getZ()).getLocation(), Material.GLOWSTONE, (byte)0);
/* 450 */       player.sendBlockChange(world.getBlockAt(x, (int)((Location)this.saveregion.get(this.num)).getY(), (int)((Location)this.saveregion.get(this.num)).getZ()).getLocation(), Material.GLOWSTONE, (byte)0);
/*     */     }
/* 452 */     for (int y = (int)((Location)this.saveregion1.get(this.num)).getY(); y <= (int)((Location)this.saveregion.get(this.num)).getY(); y++) {
/* 453 */       if (world.getBlockAt((int)((Location)this.saveregion.get(this.num)).getX(), y, (int)((Location)this.saveregion.get(this.num)).getZ()).getType() != Material.GLOWSTONE) {
/* 454 */         this.showStore.put(world.getBlockAt((int)((Location)this.saveregion.get(this.num)).getX(), y, (int)((Location)this.saveregion.get(this.num)).getZ()).getLocation(), world.getBlockAt((int)((Location)this.saveregion.get(this.num)).getX(), y, (int)((Location)this.saveregion.get(this.num)).getZ()).getState());
/*     */       }
/* 456 */       if (world.getBlockAt((int)((Location)this.saveregion.get(this.num)).getX(), y, (int)((Location)this.saveregion1.get(this.num)).getZ()).getType() != Material.GLOWSTONE) {
/* 457 */         this.showStore.put(world.getBlockAt((int)((Location)this.saveregion.get(this.num)).getX(), y, (int)((Location)this.saveregion1.get(this.num)).getZ()).getLocation(), world.getBlockAt((int)((Location)this.saveregion.get(this.num)).getX(), y, (int)((Location)this.saveregion1.get(this.num)).getZ()).getState());
/*     */       }
/* 459 */       if (world.getBlockAt((int)((Location)this.saveregion1.get(this.num)).getX(), y, (int)((Location)this.saveregion.get(this.num)).getZ()).getType() != Material.GLOWSTONE) {
/* 460 */         this.showStore.put(world.getBlockAt((int)((Location)this.saveregion1.get(this.num)).getX(), y, (int)((Location)this.saveregion.get(this.num)).getZ()).getLocation(), world.getBlockAt((int)((Location)this.saveregion1.get(this.num)).getX(), y, (int)((Location)this.saveregion.get(this.num)).getZ()).getState());
/*     */       }
/* 462 */       if (world.getBlockAt((int)((Location)this.saveregion1.get(this.num)).getX(), y, (int)((Location)this.saveregion1.get(this.num)).getZ()).getType() != Material.GLOWSTONE) {
/* 463 */         this.showStore.put(world.getBlockAt((int)((Location)this.saveregion1.get(this.num)).getX(), y, (int)((Location)this.saveregion1.get(this.num)).getZ()).getLocation(), world.getBlockAt((int)((Location)this.saveregion1.get(this.num)).getX(), y, (int)((Location)this.saveregion1.get(this.num)).getZ()).getState());
/*     */       }
/* 465 */       player.sendBlockChange(world.getBlockAt((int)((Location)this.saveregion1.get(this.num)).getX(), y, (int)((Location)this.saveregion1.get(this.num)).getZ()).getLocation(), Material.GLOWSTONE, (byte)0);
/* 466 */       player.sendBlockChange(world.getBlockAt((int)((Location)this.saveregion1.get(this.num)).getX(), y, (int)((Location)this.saveregion.get(this.num)).getZ()).getLocation(), Material.GLOWSTONE, (byte)0);
/* 467 */       player.sendBlockChange(world.getBlockAt((int)((Location)this.saveregion.get(this.num)).getX(), y, (int)((Location)this.saveregion1.get(this.num)).getZ()).getLocation(), Material.GLOWSTONE, (byte)0);
/* 468 */       player.sendBlockChange(world.getBlockAt((int)((Location)this.saveregion.get(this.num)).getX(), y, (int)((Location)this.saveregion.get(this.num)).getZ()).getLocation(), Material.GLOWSTONE, (byte)0);
/*     */     }
/* 470 */     for (int z = (int)((Location)this.saveregion1.get(this.num)).getZ(); z <= (int)((Location)this.saveregion.get(this.num)).getZ(); z++) {
/* 471 */       if (world.getBlockAt((int)((Location)this.saveregion.get(this.num)).getX(), (int)((Location)this.saveregion1.get(this.num)).getY(), z).getType() != Material.GLOWSTONE) {
/* 472 */         this.showStore.put(world.getBlockAt((int)((Location)this.saveregion.get(this.num)).getX(), (int)((Location)this.saveregion.get(this.num)).getY(), z).getLocation(), world.getBlockAt((int)((Location)this.saveregion.get(this.num)).getX(), (int)((Location)this.saveregion.get(this.num)).getY(), z).getState());
/*     */       }
/* 474 */       if (world.getBlockAt((int)((Location)this.saveregion.get(this.num)).getX(), (int)((Location)this.saveregion1.get(this.num)).getY(), z).getType() != Material.GLOWSTONE) {
/* 475 */         this.showStore.put(world.getBlockAt((int)((Location)this.saveregion.get(this.num)).getX(), (int)((Location)this.saveregion1.get(this.num)).getY(), z).getLocation(), world.getBlockAt((int)((Location)this.saveregion.get(this.num)).getX(), (int)((Location)this.saveregion1.get(this.num)).getY(), z).getState());
/*     */       }
/* 477 */       if (world.getBlockAt((int)((Location)this.saveregion1.get(this.num)).getX(), (int)((Location)this.saveregion1.get(this.num)).getY(), z).getType() != Material.GLOWSTONE) {
/* 478 */         this.showStore.put(world.getBlockAt((int)((Location)this.saveregion1.get(this.num)).getX(), (int)((Location)this.saveregion.get(this.num)).getY(), z).getLocation(), world.getBlockAt((int)((Location)this.saveregion1.get(this.num)).getX(), (int)((Location)this.saveregion.get(this.num)).getY(), z).getState());
/*     */       }
/* 480 */       if (world.getBlockAt((int)((Location)this.saveregion1.get(this.num)).getX(), (int)((Location)this.saveregion1.get(this.num)).getY(), z).getType() != Material.GLOWSTONE) {
/* 481 */         this.showStore.put(world.getBlockAt((int)((Location)this.saveregion1.get(this.num)).getX(), (int)((Location)this.saveregion1.get(this.num)).getY(), z).getLocation(), world.getBlockAt((int)((Location)this.saveregion1.get(this.num)).getX(), (int)((Location)this.saveregion1.get(this.num)).getY(), z).getState());
/*     */       }
/* 483 */       player.sendBlockChange(world.getBlockAt((int)((Location)this.saveregion1.get(this.num)).getX(), (int)((Location)this.saveregion1.get(this.num)).getY(), z).getLocation(), Material.GLOWSTONE, (byte)0);
/* 484 */       player.sendBlockChange(world.getBlockAt((int)((Location)this.saveregion1.get(this.num)).getX(), (int)((Location)this.saveregion.get(this.num)).getY(), z).getLocation(), Material.GLOWSTONE, (byte)0);
/* 485 */       player.sendBlockChange(world.getBlockAt((int)((Location)this.saveregion.get(this.num)).getX(), (int)((Location)this.saveregion1.get(this.num)).getY(), z).getLocation(), Material.GLOWSTONE, (byte)0);
/* 486 */       player.sendBlockChange(world.getBlockAt((int)((Location)this.saveregion.get(this.num)).getX(), (int)((Location)this.saveregion.get(this.num)).getY(), z).getLocation(), Material.GLOWSTONE, (byte)0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void saveRegionRestore() {
/* 491 */     if (this.showcount != null) {
/* 492 */       this.showcount = Integer.valueOf(this.showcount.intValue() - 1);
/* 493 */       if (this.showcount.intValue() == 0) {
/* 494 */         this.showcount = null;
/* 495 */         this.showing = false;
/* 496 */         Iterator location = this.showStore.keySet().iterator();
/* 497 */         while (location.hasNext()) {
/* 498 */           Location loc = (Location)location.next();
/* 499 */           Player player = this.seeing;
/* 500 */           player.sendBlockChange(loc, ((BlockState)this.showStore.get(loc)).getType(), (byte)0);
/*     */         }
/* 502 */         this.showStore.clear();
/* 503 */         this.seeing = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 508 */   public void ShowArena() { World world = this.loc2.getWorld();
/* 509 */     Integer maxX = Integer.valueOf(Math.max((int)this.loc1.getX(), (int)this.loc2.getX()));
/* 510 */     Integer minX = Integer.valueOf(Math.min((int)this.loc1.getX(), (int)this.loc2.getX()));
/* 511 */     Integer maxY = Integer.valueOf(Math.max((int)this.loc1.getY(), (int)this.loc2.getY()));
/* 512 */     Integer minY = Integer.valueOf(Math.min((int)this.loc1.getY(), (int)this.loc2.getY()));
/* 513 */     Integer maxZ = Integer.valueOf(Math.max((int)this.loc1.getZ(), (int)this.loc2.getZ()));
/* 514 */     Integer minZ = Integer.valueOf(Math.min((int)this.loc1.getZ(), (int)this.loc2.getZ()));
/* 515 */     Location loc3 = world.getBlockAt(maxX.intValue(), maxY.intValue(), maxZ.intValue()).getLocation();
/* 516 */     Location loc4 = world.getBlockAt(minX.intValue(), minY.intValue(), minZ.intValue()).getLocation();
/* 517 */     this.showing = true;
/* 518 */     this.showcount = Integer.valueOf(5);
/* 519 */     Player player = this.seeing;
/* 520 */     for (int x = (int)loc4.getX(); x <= (int)loc3.getX(); x++) {
/* 521 */       this.showStore.put(world.getBlockAt(x, (int)loc3.getY(), (int)loc3.getZ()).getLocation(), world.getBlockAt(x, (int)loc3.getY(), (int)loc3.getZ()).getState());
/* 522 */       this.showStore.put(world.getBlockAt(x, (int)loc3.getY(), (int)loc4.getZ()).getLocation(), world.getBlockAt(x, (int)loc3.getY(), (int)loc4.getZ()).getState());
/* 523 */       this.showStore.put(world.getBlockAt(x, (int)loc4.getY(), (int)loc3.getZ()).getLocation(), world.getBlockAt(x, (int)loc4.getY(), (int)loc3.getZ()).getState());
/* 524 */       this.showStore.put(world.getBlockAt(x, (int)loc4.getY(), (int)loc4.getZ()).getLocation(), world.getBlockAt(x, (int)loc4.getY(), (int)loc4.getZ()).getState());
/* 525 */       player.sendBlockChange(world.getBlockAt(x, (int)loc3.getY(), (int)loc3.getZ()).getLocation(), Material.GLOWSTONE, (byte)0);
/* 526 */       player.sendBlockChange(world.getBlockAt(x, (int)loc3.getY(), (int)loc4.getZ()).getLocation(), Material.GLOWSTONE, (byte)0);
/* 527 */       player.sendBlockChange(world.getBlockAt(x, (int)loc4.getY(), (int)loc3.getZ()).getLocation(), Material.GLOWSTONE, (byte)0);
/* 528 */       player.sendBlockChange(world.getBlockAt(x, (int)loc4.getY(), (int)loc4.getZ()).getLocation(), Material.GLOWSTONE, (byte)0);
/*     */     }
/*     */ 
/* 531 */     for (int y = (int)loc4.getY(); y <= (int)loc3.getY(); y++) {
/* 532 */       if (world.getBlockAt((int)loc3.getX(), y, (int)loc3.getZ()).getType() != Material.GLOWSTONE) {
/* 533 */         this.showStore.put(world.getBlockAt((int)loc3.getX(), y, (int)loc3.getZ()).getLocation(), world.getBlockAt((int)loc3.getX(), y, (int)loc3.getZ()).getState());
/*     */       }
/* 535 */       if (world.getBlockAt((int)loc3.getX(), y, (int)loc4.getZ()).getType() != Material.GLOWSTONE) {
/* 536 */         this.showStore.put(world.getBlockAt((int)loc3.getX(), y, (int)loc4.getZ()).getLocation(), world.getBlockAt((int)loc3.getX(), y, (int)loc4.getZ()).getState());
/*     */       }
/* 538 */       if (world.getBlockAt((int)loc4.getX(), y, (int)loc3.getZ()).getType() != Material.GLOWSTONE) {
/* 539 */         this.showStore.put(world.getBlockAt((int)loc4.getX(), y, (int)loc3.getZ()).getLocation(), world.getBlockAt((int)loc4.getX(), y, (int)loc3.getZ()).getState());
/*     */       }
/* 541 */       if (world.getBlockAt((int)loc4.getX(), y, (int)loc4.getZ()).getType() != Material.GLOWSTONE) {
/* 542 */         this.showStore.put(world.getBlockAt((int)loc4.getX(), y, (int)loc4.getZ()).getLocation(), world.getBlockAt((int)loc4.getX(), y, (int)loc4.getZ()).getState());
/*     */       }
/* 544 */       player.sendBlockChange(world.getBlockAt((int)loc4.getX(), y, (int)loc3.getZ()).getLocation(), Material.GLOWSTONE, (byte)0);
/* 545 */       player.sendBlockChange(world.getBlockAt((int)loc4.getX(), y, (int)loc4.getZ()).getLocation(), Material.GLOWSTONE, (byte)0);
/* 546 */       player.sendBlockChange(world.getBlockAt((int)loc3.getX(), y, (int)loc3.getZ()).getLocation(), Material.GLOWSTONE, (byte)0);
/* 547 */       player.sendBlockChange(world.getBlockAt((int)loc3.getX(), y, (int)loc4.getZ()).getLocation(), Material.GLOWSTONE, (byte)0);
/*     */     }
/* 549 */     for (int z = (int)loc4.getZ(); z <= (int)loc3.getZ(); z++) {
/* 550 */       if (world.getBlockAt((int)loc3.getX(), (int)loc3.getY(), z).getType() != Material.GLOWSTONE) {
/* 551 */         this.showStore.put(world.getBlockAt((int)loc3.getX(), (int)loc3.getY(), z).getLocation(), world.getBlockAt((int)loc3.getX(), (int)loc3.getY(), z).getState());
/*     */       }
/* 553 */       if (world.getBlockAt((int)loc3.getX(), (int)loc4.getY(), z).getType() != Material.GLOWSTONE) {
/* 554 */         this.showStore.put(world.getBlockAt((int)loc3.getX(), (int)loc4.getY(), z).getLocation(), world.getBlockAt((int)loc3.getX(), (int)loc4.getY(), z).getState());
/*     */       }
/* 556 */       if (world.getBlockAt((int)loc4.getX(), (int)loc3.getY(), z).getType() != Material.GLOWSTONE) {
/* 557 */         this.showStore.put(world.getBlockAt((int)loc4.getX(), (int)loc3.getY(), z).getLocation(), world.getBlockAt((int)loc4.getX(), (int)loc3.getY(), z).getState());
/*     */       }
/* 559 */       if (world.getBlockAt((int)loc4.getX(), (int)loc4.getY(), z).getType() != Material.GLOWSTONE) {
/* 560 */         this.showStore.put(world.getBlockAt((int)loc4.getX(), (int)loc4.getY(), z).getLocation(), world.getBlockAt((int)loc4.getX(), (int)loc4.getY(), z).getState());
/*     */       }
/* 562 */       player.sendBlockChange(world.getBlockAt((int)loc3.getX(), (int)loc3.getY(), z).getLocation(), Material.GLOWSTONE, (byte)0);
/* 563 */       player.sendBlockChange(world.getBlockAt((int)loc3.getX(), (int)loc4.getY(), z).getLocation(), Material.GLOWSTONE, (byte)0);
/* 564 */       player.sendBlockChange(world.getBlockAt((int)loc4.getX(), (int)loc3.getY(), z).getLocation(), Material.GLOWSTONE, (byte)0);
/* 565 */       player.sendBlockChange(world.getBlockAt((int)loc4.getX(), (int)loc4.getY(), z).getLocation(), Material.GLOWSTONE, (byte)0);
/*     */     } }
/*     */ 
/*     */   public Integer getTotalDrops() {
/* 569 */     return this.totalDrops;
/*     */   }
/*     */   public void setTotalDrops(Integer totalDrops) {
/* 572 */     this.totalDrops = totalDrops;
/*     */   }
/*     */   public Integer getDropTime() {
/* 575 */     return this.dropTime;
/*     */   }
/*     */   public void setDropTime(Integer dropTime) {
/* 578 */     this.dropTime = dropTime;
/*     */   }
/*     */   public Map<Integer, String> getMessages() {
/* 581 */     return this.messages;
/*     */   }
/*     */   public void setMessages(Map<Integer, String> messages) {
/* 584 */     this.messages = messages;
/*     */   }
/*     */   public Map<Integer, SignWall> getSignWalls() {
/* 587 */     return this.signWalls;
/*     */   }
/*     */   public void setSignWalls(Map<Integer, SignWall> signWalls) {
/* 590 */     this.signWalls = signWalls;
/*     */   }
/*     */   public Set<Player> getLobbyPlayers() {
/* 593 */     return this.lobbyPlayers;
/*     */   }
/*     */   public void setLobbyPlayers(Set<Player> lobbyPlayers) {
/* 596 */     this.lobbyPlayers = lobbyPlayers;
/*     */   }
/*     */   public Integer getCountToEnd() {
/* 599 */     return this.countToEnd;
/*     */   }
/*     */   public void setCountToEnd(Integer countToEnd) {
/* 602 */     this.countToEnd = countToEnd;
/*     */   }
/*     */   public String getName() {
/* 605 */     return this.name;
/*     */   }
/*     */   public void setName(String name) {
/* 608 */     this.name = name;
/*     */   }
/*     */   public Location getLoc1() {
/* 611 */     return this.loc1;
/*     */   }
/*     */   public void setLoc1(Location loc1) {
/* 614 */     this.loc1 = loc1;
/*     */   }
/*     */   public Location getLoc2() {
/* 617 */     return this.loc2;
/*     */   }
/*     */   public void setLoc2(Location loc2) {
/* 620 */     this.loc2 = loc2;
/*     */   }
/*     */   public Map<Integer, Location> getBlocksLocation() {
/* 623 */     return this.blocksLocation;
/*     */   }
/*     */   public void setBlocksLocation(Map<Integer, Location> blocksLocation) {
/* 626 */     this.blocksLocation = blocksLocation;
/*     */   }
/*     */   public Map<Integer, BlockState> getBlockstype() {
/* 629 */     return this.blockstype;
/*     */   }
/*     */   public void setBlockstype(Map<Integer, BlockState> blockstype) {
/* 632 */     this.blockstype = blockstype;
/*     */   }
/*     */   public Integer getBlocknumber() {
/* 635 */     return this.blocknumber;
/*     */   }
/*     */   public void setBlocknumber(Integer blocknumber) {
/* 638 */     this.blocknumber = blocknumber;
/*     */   }
/*     */   public Location getTempLoc() {
/* 641 */     return this.tempLoc;
/*     */   }
/*     */   public void setTempLoc(Location tempLoc) {
/* 644 */     this.tempLoc = tempLoc;
/*     */   }
/*     */   public Location getTempLoc1() {
/* 647 */     return this.tempLoc1;
/*     */   }
/*     */   public void setTempLoc1(Location tempLoc1) {
/* 650 */     this.tempLoc1 = tempLoc1;
/*     */   }
/*     */   public String getTemp() {
/* 653 */     return this.temp;
/*     */   }
/*     */   public void setTemp(String temp) {
/* 656 */     this.temp = temp;
/*     */   }
/*     */   public Location getLobby() {
/* 659 */     return this.lobby;
/*     */   }
/*     */   public void setLobby(Location lobby) {
/* 662 */     this.lobby = lobby;
/*     */   }
/*     */   public void addRedSpawn() {
/* 665 */     this.reds = Integer.valueOf(this.reds.intValue() + 1);
/* 666 */     this.redspawns.put(this.reds, this.playerLoc);
/*     */   }
/*     */   public void addBlueSpawn() {
/* 669 */     this.blues = Integer.valueOf(this.blues.intValue() + 1);
/* 670 */     this.bluespawns.put(this.blues, this.playerLoc);
/*     */   }
/*     */   public void addGreenSpawn() {
/* 673 */     this.greens = Integer.valueOf(this.greens.intValue() + 1);
/* 674 */     this.greenspawns.put(this.greens, this.playerLoc);
/*     */   }
/*     */   public void addYellowSpawn() {
/* 677 */     this.yellows = Integer.valueOf(this.yellows.intValue() + 1);
/* 678 */     this.yellowspawns.put(this.yellows, this.playerLoc);
/*     */   }
/*     */   public void min() {
/* 681 */     this.min = Integer.valueOf(Math.min(Math.min(this.reds.intValue(), this.blues.intValue()), Math.min(this.yellows.intValue(), this.greens.intValue())));
/*     */   }
/*     */   public Boolean getStarted() {
/* 684 */     return this.started;
/*     */   }
/*     */   public void setStarted(Boolean started) {
/* 687 */     this.started = started;
/*     */   }
/*     */   public Location getWin() {
/* 690 */     return this.win;
/*     */   }
/*     */   public void setWin(Location win) {
/* 693 */     this.win = win;
/*     */   }
/*     */   public Location getLose() {
/* 696 */     return this.lose;
/*     */   }
/*     */   public void setLose(Location lose) {
/* 699 */     this.lose = lose;
/*     */   }
/*     */   public Map<Integer, Location> getRedspawns() {
/* 702 */     return this.redspawns;
/*     */   }
/*     */   public void setRedspawns(Map<Integer, Location> redspawns) {
/* 705 */     this.redspawns = redspawns;
/*     */   }
/*     */   public Map<Integer, Location> getBluespawns() {
/* 708 */     return this.bluespawns;
/*     */   }
/*     */   public void setBluespawns(Map<Integer, Location> bluespawns) {
/* 711 */     this.bluespawns = bluespawns;
/*     */   }
/*     */   public Map<Integer, Location> getYellowspawns() {
/* 714 */     return this.yellowspawns;
/*     */   }
/*     */   public void setYellowspawns(Map<Integer, Location> yellowspawns) {
/* 717 */     this.yellowspawns = yellowspawns;
/*     */   }
/*     */   public Map<Integer, Location> getGreenspawns() {
/* 720 */     return this.greenspawns;
/*     */   }
/*     */   public void setGreenspawns(Map<Integer, Location> greenspawns) {
/* 723 */     this.greenspawns = greenspawns;
/*     */   }
/*     */   public Integer getReds() {
/* 726 */     return this.reds;
/*     */   }
/*     */   public void setReds(Integer reds) {
/* 729 */     this.reds = reds;
/*     */   }
/*     */   public Integer getBlues() {
/* 732 */     return this.blues;
/*     */   }
/*     */   public void setBlues(Integer blues) {
/* 735 */     this.blues = blues;
/*     */   }
/*     */   public Integer getYellows() {
/* 738 */     return this.yellows;
/*     */   }
/*     */   public void setYellows(Integer yellows) {
/* 741 */     this.yellows = yellows;
/*     */   }
/*     */   public Integer getGreens() {
/* 744 */     return this.greens;
/*     */   }
/*     */   public void setGreens(Integer greens) {
/* 747 */     this.greens = greens;
/*     */   }
/*     */   public Location getPlayerLoc() {
/* 750 */     return this.playerLoc;
/*     */   }
/*     */   public void setPlayerLoc(Location playerLoc) {
/* 753 */     this.playerLoc = playerLoc;
/*     */   }
/*     */   public Integer getMin() {
/* 756 */     return this.min;
/*     */   }
/*     */   public void setMin(Integer min) {
/* 759 */     this.min = min;
/*     */   }
/*     */   public Map<Player, String> getPlayerList() {
/* 762 */     return this.playerList;
/*     */   }
/*     */   public void setPlayerList(Map<Player, String> playerList) {
/* 765 */     this.playerList = playerList;
/*     */   }
/*     */   public Integer getRedplayers() {
/* 768 */     return this.redplayers;
/*     */   }
/*     */   public void setRedplayers(Integer redplayers) {
/* 771 */     this.redplayers = redplayers;
/*     */   }
/*     */   public Integer getBlueplayers() {
/* 774 */     return this.blueplayers;
/*     */   }
/*     */   public void setBlueplayers(Integer blueplayers) {
/* 777 */     this.blueplayers = blueplayers;
/*     */   }
/*     */   public Integer getGreenplayers() {
/* 780 */     return this.greenplayers;
/*     */   }
/*     */   public void setGreenplayers(Integer greenplayers) {
/* 783 */     this.greenplayers = greenplayers;
/*     */   }
/*     */   public Integer getYellowplayers() {
/* 786 */     return this.yellowplayers;
/*     */   }
/*     */   public void setYellowplayers(Integer yellowplayers) {
/* 789 */     this.yellowplayers = yellowplayers;
/*     */   }
/*     */   public Integer getNum() {
/* 792 */     return this.num;
/*     */   }
/*     */   public void setNum(Integer num) {
/* 795 */     this.num = num;
/*     */   }
/*     */   public boolean isShowing() {
/* 798 */     return this.showing;
/*     */   }
/*     */   public void setShowing(boolean showing) {
/* 801 */     this.showing = showing;
/*     */   }
/*     */   public Integer getShowcount() {
/* 804 */     return this.showcount;
/*     */   }
/*     */   public void setShowcount(Integer showcount) {
/* 807 */     this.showcount = showcount;
/*     */   }
/*     */   public Map<Location, BlockState> getShowStore() {
/* 810 */     return this.showStore;
/*     */   }
/*     */   public void setShowStore(Map<Location, BlockState> showStore) {
/* 813 */     this.showStore = showStore;
/*     */   }
/*     */ 
/*     */   public void setSeeing(Player seeing) {
/* 817 */     this.seeing = seeing;
/*     */   }
/*     */ 
/*     */   public Player getSeeing() {
/* 821 */     return this.seeing;
/*     */   }
/*     */   public Player getRandom() {
/* 824 */     return this.random;
/*     */   }
/*     */   public void setRandom(Player random) {
/* 827 */     this.random = random;
/*     */   }
/*     */   public Map<Integer, Location> getDrop() {
/* 830 */     return this.drop;
/*     */   }
/*     */   public void setDrop(Map<Integer, Location> drop) {
/* 833 */     this.drop = drop;
/*     */   }
/*     */   public Integer getDrops() {
/* 836 */     return this.drops;
/*     */   }
/*     */   public void setDrops(Integer drops) {
/* 839 */     this.drops = drops;
/*     */   }
/*     */   public Integer getTotal() {
/* 842 */     return this.total;
/*     */   }
/*     */   public void setTotal(Integer total) {
/* 845 */     this.total = total;
/*     */   }
/*     */   public Map<Player, Location> getPlayerLocation() {
/* 848 */     return this.playerLocation;
/*     */   }
/*     */   public void setPlayerLocation(Map<Player, Location> playerLocation) {
/* 851 */     this.playerLocation = playerLocation;
/*     */   }
/*     */   public Map<Player, String> getDeadplayers() {
/* 854 */     return this.deadplayers;
/*     */   }
/*     */   public void setDeadplayers(Map<Player, String> deadplayers) {
/* 857 */     this.deadplayers = deadplayers;
/*     */   }
/*     */ 
/*     */   public Map<Location, ItemStack[]> getChests() {
/* 861 */     return this.chests;
/*     */   }
/*     */   public void setChests(Map<Location, ItemStack[]> chests) {
/* 864 */     this.chests = chests;
/*     */   }
/*     */   public Map<Player, ItemStack[]> getPlayerInventories() {
/* 867 */     return this.playerInventories;
/*     */   }
/*     */   public void setPlayerInventories(Map<Player, ItemStack[]> playerInventories) {
/* 870 */     this.playerInventories = playerInventories;
/*     */   }
/*     */   public Map<Integer, Location> getDroploc1() {
/* 873 */     return this.droploc1;
/*     */   }
/*     */   public void setDroploc1(Map<Integer, Location> droploc1) {
/* 876 */     this.droploc1 = droploc1;
/*     */   }
/*     */   public Map<Integer, Location> getDroploc2() {
/* 879 */     return this.droploc2;
/*     */   }
/*     */   public void setDroploc2(Map<Integer, Location> droploc2) {
/* 882 */     this.droploc2 = droploc2;
/*     */   }
/*     */   public Map<Player, ItemStack[]> getAurmor() {
/* 885 */     return this.aurmor;
/*     */   }
/*     */   public void setAurmor(Map<Player, ItemStack[]> aurmor) {
/* 888 */     this.aurmor = aurmor;
/*     */   }
/*     */   public Map<Player, Integer> getExp() {
/* 891 */     return this.exp;
/*     */   }
/*     */   public void setExp(Map<Player, Integer> exp) {
/* 894 */     this.exp = exp;
/*     */   }
/*     */   public Map<Location, String> getSignLine0() {
/* 897 */     return this.signLine0;
/*     */   }
/*     */   public void setSignLine0(Map<Location, String> signLine0) {
/* 900 */     this.signLine0 = signLine0;
/*     */   }
/*     */   public Map<Location, String> getSignLine1() {
/* 903 */     return this.signLine1;
/*     */   }
/*     */   public void setSignLine1(Map<Location, String> signLine1) {
/* 906 */     this.signLine1 = signLine1;
/*     */   }
/*     */   public Map<Location, String> getSignLine2() {
/* 909 */     return this.signLine2;
/*     */   }
/*     */   public void setSignLine2(Map<Location, String> signLine2) {
/* 912 */     this.signLine2 = signLine2;
/*     */   }
/*     */   public Map<Location, String> getSignLine3() {
/* 915 */     return this.signLine3;
/*     */   }
/*     */   public void setSignLine3(Map<Location, String> signLine3) {
/* 918 */     this.signLine3 = signLine3;
/*     */   }
/*     */   public Map<Integer, Location> getSaveregion() {
/* 921 */     return this.saveregion;
/*     */   }
/*     */   public void setSaveregion(Map<Integer, Location> saveregion) {
/* 924 */     this.saveregion = saveregion;
/*     */   }
/*     */   public Map<Integer, Location> getSaveregion1() {
/* 927 */     return this.saveregion1;
/*     */   }
/*     */   public void setSaveregion1(Map<Integer, Location> saveregion1) {
/* 930 */     this.saveregion1 = saveregion1;
/*     */   }
/*     */   public Integer getSaveTotal() {
/* 933 */     return this.saveTotal;
/*     */   }
/*     */   public void setSaveTotal(Integer saveTotal) {
/* 936 */     this.saveTotal = saveTotal;
/*     */   }
/*     */   public Integer getCounter() {
/* 939 */     return this.counter;
/*     */   }
/*     */   public void setCounter(Integer counter) {
/* 942 */     this.counter = counter;
/*     */   }
/*     */   public void JoinRed() {
/* 945 */     this.playerList.put(this.random, "Red");
/*     */   }
/*     */   public void JoinBlue() {
/* 948 */     this.playerList.put(this.random, "Blue");
/*     */   }
/*     */   public void JoinGreen() {
/* 951 */     this.playerList.put(this.random, "Green");
/*     */   }
/*     */   public void JoinYellow() {
/* 954 */     this.playerList.put(this.random, "Yellow");
/*     */   }
/*     */   public Integer getFiles() {
/* 957 */     return this.files;
/*     */   }
/*     */   public void setFiles(Integer files) {
/* 960 */     this.files = files;
/*     */   }
/*     */   public boolean isResetting() {
/* 963 */     return this.resetting;
/*     */   }
/*     */   public void setResetting(boolean resetting) {
/* 966 */     this.resetting = resetting;
/*     */   }
/*     */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.Arena
 * JD-Core Version:    0.6.2
 */