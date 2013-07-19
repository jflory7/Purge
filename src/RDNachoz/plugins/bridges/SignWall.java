/*     */ package RDNachoz.plugins.bridges;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.Sign;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class SignWall
/*     */ {
/*  15 */   private Map<Integer, Location> signLocations = new HashMap();
/*     */   private Location startSign;
/*     */   private byte rotation;
/*     */   private String arena;
/*     */   private Integer min;
/*     */   private Location clickSign;
/*     */   private boolean started;
/*     */   private String progress;
/*     */   private Integer time;
/*     */ 
/*     */   public SignWall(Location startSign, byte rotation, String arena, Integer min, String progress)
/*     */   {
/*  26 */     this.startSign = startSign;
/*  27 */     this.rotation = rotation;
/*  28 */     this.arena = arena;
/*  29 */     this.min = min;
/*  30 */     this.progress = progress;
/*     */   }
/*     */ 
/*     */   public void Create() {
/*  34 */     Integer loops = Integer.valueOf(this.min.intValue() + 2);
/*  35 */     Location location = this.startSign;
/*  36 */     for (int i = 0; i <= loops.intValue(); i++) {
/*  37 */       if (this.rotation == 4) {
/*  38 */         location.setZ(location.getZ() + i);
/*     */       }
/*  40 */       else if (this.rotation == 2) {
/*  41 */         location.setX(location.getX() - i);
/*     */       }
/*  43 */       else if (this.rotation == 3) {
/*  44 */         location.setX(location.getX() + i);
/*     */       }
/*  46 */       else if (this.rotation == 5) {
/*  47 */         location.setZ(location.getZ() - i);
/*     */       }
/*  49 */       int X = (int)location.getX();
/*  50 */       int Y = (int)location.getY();
/*  51 */       int Z = (int)location.getZ();
/*  52 */       World world = location.getWorld();
/*  53 */       if (this.rotation == 4) {
/*  54 */         X++;
/*     */       }
/*  56 */       else if (this.rotation == 2) {
/*  57 */         Z++;
/*     */       }
/*  59 */       else if (this.rotation == 3) {
/*  60 */         Z--;
/*     */       }
/*  62 */       else if (this.rotation == 5) {
/*  63 */         X--;
/*     */       }
/*  65 */       if ((world.getBlockAt(X, Y, Z).getType() == Material.AIR) || (world.getBlockAt(X, Y, Z).isLiquid())) {
/*  66 */         world.getBlockAt(X, Y, Z).setType(Material.BRICK);
/*     */       }
/*  68 */       location.getBlock().setType(Material.WALL_SIGN);
/*  69 */       Sign signs = (Sign)location.getBlock().getState();
/*  70 */       signs.setRawData(this.rotation);
/*  71 */       if (i == 0) {
/*  72 */         signs.setLine(0, ChatColor.BOLD + "BukkitBridges");
/*  73 */         signs.setLine(1, ChatColor.DARK_GRAY + "Click to Join");
/*  74 */         signs.setLine(2, ChatColor.UNDERLINE + this.arena);
/*  75 */         Integer maxPlayers = Integer.valueOf(this.min.intValue() * 4);
/*  76 */         signs.setLine(3, ChatColor.DARK_GRAY + "0/" + maxPlayers);
/*  77 */         this.clickSign = location;
/*     */       }
/*  79 */       if (i == 1) {
/*  80 */         signs.setLine(0, ChatColor.RED + "Status");
/*  81 */         signs.setLine(1, ChatColor.BOLD + "Waiting");
/*  82 */         signs.setLine(2, "");
/*  83 */         signs.setLine(3, "");
/*     */       }
/*  85 */       if (i == 2) {
/*  86 */         signs.setLine(0, ChatColor.DARK_RED + ChatColor.BOLD + "Red" + ChatColor.WHITE + ":");
/*  87 */         signs.setLine(1, ChatColor.DARK_BLUE + ChatColor.BOLD + "Blue" + ChatColor.WHITE + ":");
/*  88 */         signs.setLine(2, ChatColor.DARK_GREEN + ChatColor.BOLD + "Green" + ChatColor.WHITE + ":");
/*  89 */         signs.setLine(3, ChatColor.YELLOW + ChatColor.BOLD + "Yellow" + ChatColor.WHITE + ":");
/*     */       }
/*  91 */       signs.update();
/*  92 */       if (this.rotation == 4) {
/*  93 */         location.setZ(location.getZ() - i);
/*     */       }
/*  95 */       else if (this.rotation == 2) {
/*  96 */         location.setX(location.getX() + i);
/*     */       }
/*  98 */       else if (this.rotation == 3) {
/*  99 */         location.setX(location.getX() - i);
/*     */       }
/* 101 */       else if (this.rotation == 5) {
/* 102 */         location.setZ(location.getZ() + i);
/*     */       }
/*     */     }
/* 105 */     Location loc = location;
/* 106 */     World world = loc.getWorld();
/* 107 */     int X = (int)loc.getX();
/* 108 */     int Y = (int)loc.getY();
/* 109 */     int Z = (int)loc.getZ();
/* 110 */     int i = 0;
/* 111 */     if (this.rotation == 3) {
/* 112 */       int loopForXPlus = (int)(this.startSign.getX() + loops.intValue());
/* 113 */       for (X = (int)this.startSign.getX(); X <= loopForXPlus; X++) {
/* 114 */         this.signLocations.put(Integer.valueOf(i), world.getBlockAt(X, Y, Z).getLocation());
/* 115 */         i++;
/*     */       }
/*     */     }
/* 118 */     else if (this.rotation == 2) {
/* 119 */       int loopForXMinus = (int)(this.startSign.getX() - loops.intValue());
/* 120 */       for (X = (int)this.startSign.getX(); X >= loopForXMinus; X--) {
/* 121 */         this.signLocations.put(Integer.valueOf(i), world.getBlockAt(X, Y, Z).getLocation());
/* 122 */         i++;
/*     */       }
/*     */     }
/* 125 */     else if (this.rotation == 4) {
/* 126 */       int loopForZPlus = (int)(this.startSign.getZ() + loops.intValue());
/* 127 */       for (Z = (int)this.startSign.getZ(); Z <= loopForZPlus; Z++) {
/* 128 */         this.signLocations.put(Integer.valueOf(i), world.getBlockAt(X, Y, Z).getLocation());
/* 129 */         i++;
/*     */       }
/*     */     }
/* 132 */     else if (this.rotation == 5) {
/* 133 */       int loopForZMinus = (int)(this.startSign.getZ() - loops.intValue());
/* 134 */       for (Z = (int)this.startSign.getZ(); Z >= loopForZMinus; Z--) {
/* 135 */         this.signLocations.put(Integer.valueOf(i), world.getBlockAt(X, Y, Z).getLocation());
/* 136 */         i++;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 141 */   public void updateSigns(Integer min, Integer total, Map<Player, String> players) { Iterator itr = this.signLocations.keySet().iterator();
/* 142 */     while (itr.hasNext()) {
/* 143 */       Integer num = (Integer)itr.next();
/* 144 */       int X = (int)((Location)this.signLocations.get(num)).getX();
/* 145 */       int Y = (int)((Location)this.signLocations.get(num)).getY();
/* 146 */       int Z = (int)((Location)this.signLocations.get(num)).getZ();
/* 147 */       World world = ((Location)this.signLocations.get(num)).getWorld();
/* 148 */       if (this.rotation == 4) {
/* 149 */         X++;
/*     */       }
/* 151 */       else if (this.rotation == 2) {
/* 152 */         Z++;
/*     */       }
/* 154 */       else if (this.rotation == 3) {
/* 155 */         Z--;
/*     */       }
/* 157 */       else if (this.rotation == 5) {
/* 158 */         X--;
/*     */       }
/* 160 */       if (world.getBlockAt(X, Y, Z).getType() == Material.AIR) {
/* 161 */         world.getBlockAt(X, Y, Z).setType(Material.BRICK);
/*     */       }
/* 163 */       ((Location)this.signLocations.get(num)).getBlock().setType(Material.WALL_SIGN);
/* 164 */       Sign sign = (Sign)((Location)this.signLocations.get(num)).getBlock().getState();
/* 165 */       sign.setRawData(this.rotation);
/* 166 */       sign.update();
/* 167 */       if (num.intValue() == 2) {
/* 168 */         sign.setLine(0, ChatColor.DARK_RED + ChatColor.BOLD + "Red" + ChatColor.WHITE + ":");
/* 169 */         sign.setLine(1, ChatColor.DARK_BLUE + ChatColor.BOLD + "Blue" + ChatColor.WHITE + ":");
/* 170 */         sign.setLine(2, ChatColor.DARK_GREEN + ChatColor.BOLD + "Green" + ChatColor.WHITE + ":");
/* 171 */         sign.setLine(3, ChatColor.YELLOW + ChatColor.BOLD + "Yellow" + ChatColor.WHITE + ":");
/*     */       }
/* 173 */       else if (num.intValue() == 1) {
/* 174 */         if (this.time != null) {
/* 175 */           if (this.started) {
/* 176 */             Integer minu = Integer.valueOf(0);
/* 177 */             Integer sec = this.time;
/* 178 */             while (sec.intValue() >= 60) {
/* 179 */               minu = Integer.valueOf(minu.intValue() + 1);
/* 180 */               sec = Integer.valueOf(sec.intValue() - 60);
/*     */             }
/* 182 */             sign.setLine(2, minu + ":" + String.format("%02d", new Object[] { sec }));
/*     */           }
/*     */         }
/*     */         else {
/* 186 */           sign.setLine(2, "");
/* 187 */           sign.update();
/*     */         }
/* 189 */         sign.setLine(0, ChatColor.RED + "Status");
/* 190 */         sign.setLine(1, ChatColor.BOLD + this.progress);
/*     */       }
/* 192 */       else if (num.intValue() == 0) {
/* 193 */         sign.setLine(0, ChatColor.BOLD + "BukkitBridges");
/* 194 */         sign.setLine(1, ChatColor.DARK_GRAY + "Click to Join");
/* 195 */         sign.setLine(2, ChatColor.UNDERLINE + this.arena);
/* 196 */         Integer maxPlayers = Integer.valueOf(min.intValue() * 4);
/* 197 */         sign.setLine(3, ChatColor.DARK_GRAY + total + "/" + maxPlayers);
/*     */       }
/* 199 */       if (num.intValue() > 2) {
/* 200 */         sign.setLine(0, "");
/* 201 */         sign.setLine(1, "");
/* 202 */         sign.setLine(2, "");
/* 203 */         sign.setLine(3, "");
/*     */       }
/* 205 */       sign.update();
/*     */     }
/* 207 */     Iterator itr2 = players.keySet().iterator();
/* 208 */     int red = 0;
/* 209 */     int blue = 0;
/* 210 */     int green = 0;
/* 211 */     int yellow = 0;
/* 212 */     while (itr2.hasNext()) {
/* 213 */       Player player = (Player)itr2.next();
/* 214 */       if (this.started) {
/* 215 */         if (players.get(player) == "red") {
/* 216 */           Sign sign = (Sign)((Location)this.signLocations.get(Integer.valueOf(red + 3))).getBlock().getState();
/* 217 */           sign.setLine(0, ChatColor.DARK_RED + player.getName());
/* 218 */           sign.update();
/* 219 */           red++;
/*     */         }
/* 221 */         else if (players.get(player) == "blue") {
/* 222 */           Sign sign = (Sign)((Location)this.signLocations.get(Integer.valueOf(blue + 3))).getBlock().getState();
/* 223 */           sign.setLine(1, ChatColor.DARK_BLUE + player.getName());
/* 224 */           sign.update();
/* 225 */           blue++;
/*     */         }
/* 227 */         else if (players.get(player) == "green") {
/* 228 */           Sign sign = (Sign)((Location)this.signLocations.get(Integer.valueOf(green + 3))).getBlock().getState();
/* 229 */           sign.setLine(2, ChatColor.DARK_GREEN + player.getName());
/* 230 */           sign.update();
/* 231 */           green++;
/*     */         }
/* 233 */         else if (players.get(player) == "yellow") {
/* 234 */           Sign sign = (Sign)((Location)this.signLocations.get(Integer.valueOf(yellow + 3))).getBlock().getState();
/* 235 */           sign.setLine(3, ChatColor.YELLOW + player.getName());
/* 236 */           sign.update();
/* 237 */           yellow++;
/*     */         }
/*     */       }
/*     */       else {
/* 241 */         Sign sign = (Sign)((Location)this.signLocations.get(Integer.valueOf(red + 3))).getBlock().getState();
/* 242 */         sign.setLine(blue, player.getName());
/* 243 */         if (blue == 3) {
/* 244 */           blue = 0;
/* 245 */           red++;
/*     */         }
/*     */         else {
/* 248 */           blue++;
/*     */         }
/* 250 */         sign.update();
/*     */       }
/*     */     } }
/*     */ 
/*     */   public void addPlayerSign(Integer min)
/*     */   {
/* 256 */     if (this.min.intValue() < min.intValue()) {
/* 257 */       int X = (int)((Location)this.signLocations.get(Integer.valueOf(this.min.intValue() + 2))).getX();
/* 258 */       int Y = (int)((Location)this.signLocations.get(Integer.valueOf(this.min.intValue() + 2))).getY();
/* 259 */       int Z = (int)((Location)this.signLocations.get(Integer.valueOf(this.min.intValue() + 2))).getZ();
/* 260 */       World world = ((Location)this.signLocations.get(Integer.valueOf(this.min.intValue() + 2))).getWorld();
/* 261 */       if (this.rotation == 4) {
/* 262 */         Z++;
/*     */       }
/* 264 */       else if (this.rotation == 2) {
/* 265 */         X--;
/*     */       }
/* 267 */       else if (this.rotation == 3) {
/* 268 */         X++;
/*     */       }
/* 270 */       else if (this.rotation == 5) {
/* 271 */         Z--;
/*     */       }
/* 273 */       this.min = Integer.valueOf(this.min.intValue() + 1);
/* 274 */       Location loc = world.getBlockAt(X, Y, Z).getLocation();
/* 275 */       this.signLocations.put(Integer.valueOf(this.min.intValue() + 2), loc);
/* 276 */       loc.getBlock().setType(Material.WALL_SIGN);
/* 277 */       Sign sign = (Sign)loc.getBlock().getState();
/* 278 */       sign.setRawData(this.rotation);
/* 279 */       sign.update();
/*     */     }
/*     */   }
/*     */ 
/*     */   public Location getStartSign() {
/* 284 */     return this.startSign;
/*     */   }
/*     */ 
/*     */   public void setStartSign(Location startSign) {
/* 288 */     this.startSign = startSign;
/*     */   }
/*     */ 
/*     */   public byte getRotation() {
/* 292 */     return this.rotation;
/*     */   }
/*     */ 
/*     */   public void setRotation(byte rotation) {
/* 296 */     this.rotation = rotation;
/*     */   }
/*     */ 
/*     */   public Location getClickSign() {
/* 300 */     return this.clickSign;
/*     */   }
/*     */ 
/*     */   public void setClickSign(Location clickSign) {
/* 304 */     this.clickSign = clickSign;
/*     */   }
/*     */ 
/*     */   public Map<Integer, Location> getSignLocations() {
/* 308 */     return this.signLocations;
/*     */   }
/*     */ 
/*     */   public boolean isStarted() {
/* 312 */     return this.started;
/*     */   }
/*     */ 
/*     */   public void setStarted(boolean started) {
/* 316 */     this.started = started;
/* 317 */     if ((started = 1) != 0) {
/* 318 */       Sign sign = (Sign)((Location)this.signLocations.get(Integer.valueOf(1))).getBlock().getState();
/* 319 */       sign.setLine(1, ChatColor.BOLD + "Starting");
/* 320 */       sign.update();
/*     */     }
/*     */     else {
/* 323 */       Sign sign = (Sign)((Location)this.signLocations.get(Integer.valueOf(1))).getBlock().getState();
/* 324 */       sign.setLine(1, ChatColor.BOLD + "Waiting");
/* 325 */       sign.update();
/*     */     }
/*     */   }
/*     */ 
/*     */   public Integer getTime() {
/* 330 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(Integer time) {
/* 334 */     this.time = time;
/*     */   }
/*     */ 
/*     */   public String getProgress() {
/* 338 */     return this.progress;
/*     */   }
/*     */ 
/*     */   public void setProgress(String progress) {
/* 342 */     this.progress = progress;
/*     */   }
/*     */ 
/*     */   public String getArena() {
/* 346 */     return this.arena;
/*     */   }
/*     */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.SignWall
 * JD-Core Version:    0.6.2
 */