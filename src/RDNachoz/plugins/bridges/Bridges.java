/*     */ package RDNachoz.plugins.bridges;
/*     */ 
/*     */ import RDNachoz.plugins.bridges.blockFileData.ArenaFileManager;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Logger;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.command.PluginCommand;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ import org.bukkit.scheduler.BukkitScheduler;
/*     */ 
/*     */ public class Bridges extends JavaPlugin
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 19204106755649487L;
/*     */   protected Logger log;
/*     */   public static Plugin plugin;
/*  25 */   public Map<String, Arena> arenas = new HashMap();
/*  26 */   public Map<Player, Location> location1 = new HashMap();
/*  27 */   public Map<Player, Location> location2 = new HashMap();
/*  28 */   public Map<Player, String> playerarena = new HashMap();
/*  29 */   public Map<Integer, Location> tempredspawnsx = new HashMap();
/*  30 */   public Map<Player, Boolean> teleportable = new HashMap();
/*  31 */   public List<String> allowedCommands = new ArrayList();
/*     */   public String arenaworld;
/*     */   public int ponex;
/*     */   public int poney;
/*     */   public int ponez;
/*     */   public int ptwox;
/*     */   public int ptwoy;
/*     */   public int ptwoz;
/*     */   public String lobbyWorld;
/*     */   public int lobbyx;
/*     */   public int lobbyy;
/*     */   public int lobbyz;
/*     */   public int winx;
/*     */   public int winy;
/*     */   public int winz;
/*     */   public String winWorld;
/*     */   public int losex;
/*     */   public int losey;
/*     */   public int losez;
/*     */   public String loseWorld;
/*     */   public int redx;
/*     */   public int redy;
/*     */   public int redz;
/*     */   public String redWorld;
/*     */   public int bluex;
/*     */   public int bluey;
/*     */   public int bluez;
/*     */   public String blueWorld;
/*     */   public int greenx;
/*     */   public int greeny;
/*     */   public int greenz;
/*     */   public String greenWorld;
/*     */   public int yellowx;
/*     */   public int yellowy;
/*     */   public int yellowz;
/*     */   public String yellowWorld;
/*     */   public int loc11;
/*     */   public int loc12;
/*     */   public int loc13;
/*     */   public int loc21;
/*     */   public int loc22;
/*     */   public int loc23;
/*     */   public int save11;
/*     */   public int save12;
/*     */   public int save13;
/*     */   public int save21;
/*     */   public int save22;
/*     */   public int save23;
/*     */   public boolean update;
/*     */   public World saveWorld;
/*     */   public World locWorld;
/*     */   public String DeleteArena;
/*     */   public boolean checkForUpdates;
/*     */ 
/*     */   public void onEnable()
/*     */   {
/*  87 */     plugin = this;
/*  88 */     ArenaFileManager.setPlugin(this);
/*  89 */     saveDefaultConfig();
/*  90 */     LoadarenaConfig();
/*  91 */     UpdatearenaConfig();
/*  92 */     getCommand("bb").setExecutor(new BridgesCommand(this));
/*  93 */     getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
/*  94 */     getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
/*  95 */     getServer().getPluginManager().registerEvents(new PlayerTagEvent(this), this);
/*  96 */     getServer().getPluginManager().registerEvents(new PlayerPlaceBlock(this), this);
/*  97 */     getServer().getPluginManager().registerEvents(new PlayerMoveListner(this), this);
/*  98 */     getServer().getPluginManager().registerEvents(new PlayerBreakBlockEvent(this), this);
/*  99 */     getServer().getPluginManager().registerEvents(new PlayerDisconnectListner(this), this);
/* 100 */     getServer().getPluginManager().registerEvents(new PlayerTeleportListner(this), this);
/* 101 */     getServer().getPluginManager().registerEvents(new PlayerFoodLevelChangeListner(this), this);
/* 102 */     getServer().getPluginManager().registerEvents(new SignChangeListener(this), this);
/* 103 */     getServer().getPluginManager().registerEvents(new PlayerPVPListener(this), this);
/* 104 */     getServer().getPluginManager().registerEvents(new PlayerCommandListner(this), this);
/* 105 */     for (String arena : this.arenas.keySet()) {
/* 106 */       ArenaFileManager.loadArenaFiles(arena, Integer.valueOf(1));
/*     */     }
/* 108 */     getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
/*     */     {
/*     */       public void run() {
/* 111 */         Iterator list = Bridges.this.arenas.keySet().iterator();
/* 112 */         while (list.hasNext()) {
/* 113 */           String name = (String)list.next();
/* 114 */           ((Arena)Bridges.this.arenas.get(name)).counter();
/* 115 */           ((Arena)Bridges.this.arenas.get(name)).saveRegionRestore();
/* 116 */           ((Arena)Bridges.this.arenas.get(name)).countToTheEnd();
/*     */         }
/*     */       }
/*     */     }
/*     */     , 0L, 20L);
/*     */   }
/*     */   public void onDisable() {
/* 123 */     SaveConfig();
/* 124 */     endGames();
/*     */   }
/*     */   public void LoadarenaConfig() {
/* 127 */     if (getConfig().getConfigurationSection("arenas") != null) {
/* 128 */       Iterator iterator = getConfig().getConfigurationSection("arenas").getKeys(false).iterator();
/* 129 */       while (iterator.hasNext()) {
/* 130 */         String arena = (String)iterator.next();
/* 131 */         this.arenaworld = getConfig().getString("arenas." + arena + ".location1.World");
/* 132 */         this.ponex = getConfig().getInt("arenas." + arena + ".location1.X");
/* 133 */         this.poney = getConfig().getInt("arenas." + arena + ".location1.Y");
/* 134 */         this.ponez = getConfig().getInt("arenas." + arena + ".location1.Z");
/* 135 */         this.ptwox = getConfig().getInt("arenas." + arena + ".location2.X");
/* 136 */         this.ptwoy = getConfig().getInt("arenas." + arena + ".location2.Y");
/* 137 */         this.ptwoz = getConfig().getInt("arenas." + arena + ".location2.Z");
/* 138 */         Arena arenaname = new Arena(arena, Bukkit.getServer().getWorld(this.arenaworld).getBlockAt(this.ponex, this.poney, this.ponez).getLocation(), Bukkit.getServer().getWorld(this.arenaworld).getBlockAt(this.ptwox, this.ptwoy, this.ptwoz).getLocation(), null, Boolean.valueOf(false), null, null, null, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), null, null, Integer.valueOf(0), Integer.valueOf(0), null, null, null, null, Integer.valueOf(0), false, Integer.valueOf(0), this);
/* 139 */         this.arenas.put(arena, arenaname);
/*     */       }
/*     */     }
/*     */     try {
/* 143 */       getConfig().save(getDataFolder() + System.getProperty("file.separator") + "config.yml");
/*     */     } catch (IOException e) {
/* 145 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/* 149 */   public void UpdatearenaConfig() { this.checkForUpdates = getConfig().getBoolean("CheckForUpdates");
/* 150 */     if (getConfig().getList("AllowedCommands") != null) {
/* 151 */       this.allowedCommands = getConfig().getStringList("AllowedCommands");
/*     */     }
/* 153 */     if (getConfig().getConfigurationSection("arenas") != null) {
/* 154 */       Iterator iterator = this.arenas.keySet().iterator();
/* 155 */       while (iterator.hasNext()) {
/* 156 */         String arena = (String)iterator.next();
/* 157 */         if (getConfig().getConfigurationSection("arenas." + arena + ".broadcasts") != null) {
/* 158 */           Iterator itr = getConfig().getConfigurationSection("arenas." + arena + ".broadcasts").getKeys(false).iterator();
/* 159 */           while (itr.hasNext()) {
/*     */             try {
/* 161 */               ((Arena)this.arenas.get(arena)).getMessages().put(Integer.valueOf(Integer.parseInt((String)itr.next())), "");
/*     */             } catch (Exception e) {
/* 163 */               getConfig().set("arenas." + arena + ".broadcasts", null);
/* 164 */               ((Arena)this.arenas.get(arena)).getMessages().clear();
/*     */             }
/*     */           }
/*     */         }
/* 168 */         ((Arena)this.arenas.get(arena)).setLoc1(Bukkit.getServer().getWorld(this.arenaworld).getBlockAt(this.ponex, this.poney, this.ponez).getLocation());
/* 169 */         ((Arena)this.arenas.get(arena)).setLoc2(Bukkit.getServer().getWorld(this.arenaworld).getBlockAt(this.ptwox, this.ptwoy, this.ptwoz).getLocation());
/* 170 */         if (getConfig().getConfigurationSection("arenas." + arena + ".lobby") != null) {
/* 171 */           this.lobbyx = getConfig().getInt("arenas." + arena + ".lobby.X");
/* 172 */           this.lobbyy = getConfig().getInt("arenas." + arena + ".lobby.Y");
/* 173 */           this.lobbyz = getConfig().getInt("arenas." + arena + ".lobby.Z");
/* 174 */           this.lobbyWorld = getConfig().getString("arenas." + arena + ".lobby.World");
/* 175 */           ((Arena)this.arenas.get(arena)).setLobby(Bukkit.getServer().getWorld(this.lobbyWorld).getBlockAt(this.lobbyx, this.lobbyy, this.lobbyz).getLocation());
/* 176 */           ((Arena)this.arenas.get(arena)).getLobby().setPitch(getConfig().getInt("arenas." + arena + ".lobby.Pitch"));
/* 177 */           ((Arena)this.arenas.get(arena)).getLobby().setYaw(getConfig().getInt("arenas." + arena + ".lobby.Yaw"));
/*     */         }
/* 179 */         if (getConfig().getConfigurationSection("arenas." + arena + ".win") != null) {
/* 180 */           this.winx = getConfig().getInt("arenas." + arena + ".win.X");
/* 181 */           this.winy = getConfig().getInt("arenas." + arena + ".win.Y");
/* 182 */           this.winz = getConfig().getInt("arenas." + arena + ".win.Z");
/* 183 */           this.winWorld = getConfig().getString("arenas." + arena + ".win.World");
/* 184 */           ((Arena)this.arenas.get(arena)).setWin(Bukkit.getServer().getWorld(this.winWorld).getBlockAt(this.winx, this.winy, this.winz).getLocation());
/* 185 */           ((Arena)this.arenas.get(arena)).getWin().setPitch(getConfig().getInt("arenas." + arena + ".win.Pitch"));
/* 186 */           ((Arena)this.arenas.get(arena)).getWin().setYaw(getConfig().getInt("arenas." + arena + ".win.Yaw"));
/*     */         }
/* 188 */         if (getConfig().getConfigurationSection("arenas." + arena + ".lose") != null) {
/* 189 */           this.losex = getConfig().getInt("arenas." + arena + ".lose.X");
/* 190 */           this.losey = getConfig().getInt("arenas." + arena + ".lose.Y");
/* 191 */           this.losez = getConfig().getInt("arenas." + arena + ".lose.Z");
/* 192 */           this.loseWorld = getConfig().getString("arenas." + arena + ".lose.World");
/* 193 */           ((Arena)this.arenas.get(arena)).setLose(Bukkit.getServer().getWorld(this.loseWorld).getBlockAt(this.losex, this.losey, this.losez).getLocation());
/* 194 */           ((Arena)this.arenas.get(arena)).getLose().setPitch(getConfig().getInt("arenas." + arena + ".lose.Pitch"));
/* 195 */           ((Arena)this.arenas.get(arena)).getLose().setYaw(getConfig().getInt("arenas." + arena + ".lose.Yaw"));
/*     */         }
/* 197 */         Integer countred = Integer.valueOf(0);
/* 198 */         if (getConfig().getConfigurationSection("arenas." + arena + ".redspawns") != null) {
/* 199 */           Iterator red = getConfig().getConfigurationSection("arenas." + arena + ".redspawns").getKeys(false).iterator();
/* 200 */           while (red.hasNext()) {
/* 201 */             String num = (String)red.next();
/* 202 */             countred = Integer.valueOf(countred.intValue() + 1);
/* 203 */             ((Arena)this.arenas.get(arena)).setReds(Integer.valueOf(((Arena)this.arenas.get(arena)).getReds().intValue() + 1));
/* 204 */             this.redx = getConfig().getInt("arenas." + arena + ".redspawns." + num + ".X");
/* 205 */             this.redy = getConfig().getInt("arenas." + arena + ".redspawns." + num + ".Y");
/* 206 */             this.redz = getConfig().getInt("arenas." + arena + ".redspawns." + num + ".Z");
/* 207 */             this.redWorld = getConfig().getString("arenas." + arena + ".redspawns." + num + ".World");
/* 208 */             Location tempLoc = Bukkit.getServer().getWorld(this.redWorld).getBlockAt(this.redx, this.redy, this.redz).getLocation();
/* 209 */             tempLoc.setPitch(getConfig().getInt("arenas." + arena + ".redspawns." + num + ".Pitch"));
/* 210 */             tempLoc.setYaw(getConfig().getInt("arenas." + arena + ".redspawns." + num + ".Yaw"));
/* 211 */             ((Arena)this.arenas.get(arena)).getRedspawns().put(countred, tempLoc);
/*     */           }
/*     */         }
/* 214 */         if (getConfig().getConfigurationSection("arenas." + arena + ".bluespawns") != null) {
/* 215 */           Integer countblue = Integer.valueOf(0);
/* 216 */           Iterator blue = getConfig().getConfigurationSection("arenas." + arena + ".bluespawns").getKeys(false).iterator();
/* 217 */           while (blue.hasNext()) {
/* 218 */             String num = (String)blue.next();
/* 219 */             countblue = Integer.valueOf(countblue.intValue() + 1);
/* 220 */             ((Arena)this.arenas.get(arena)).setBlues(Integer.valueOf(((Arena)this.arenas.get(arena)).getBlues().intValue() + 1));
/* 221 */             this.bluex = getConfig().getInt("arenas." + arena + ".bluespawns." + num + ".X");
/* 222 */             this.bluey = getConfig().getInt("arenas." + arena + ".bluespawns." + num + ".Y");
/* 223 */             this.bluez = getConfig().getInt("arenas." + arena + ".bluespawns." + num + ".Z");
/* 224 */             this.blueWorld = getConfig().getString("arenas." + arena + ".bluespawns." + num + ".World");
/* 225 */             Location tempLoc = Bukkit.getServer().getWorld(this.blueWorld).getBlockAt(this.bluex, this.bluey, this.bluez).getLocation();
/* 226 */             tempLoc.setPitch(getConfig().getInt("arenas." + arena + ".bluespawns." + num + ".Pitch"));
/* 227 */             tempLoc.setYaw(getConfig().getInt("arenas." + arena + ".bluespawns." + num + ".Yaw"));
/* 228 */             ((Arena)this.arenas.get(arena)).getBluespawns().put(countblue, tempLoc);
/*     */           }
/*     */         }
/* 231 */         Integer countgreen = Integer.valueOf(0);
/* 232 */         if (getConfig().getConfigurationSection("arenas." + arena + ".greenspawns") != null) {
/* 233 */           Iterator green = getConfig().getConfigurationSection("arenas." + arena + ".greenspawns").getKeys(false).iterator();
/* 234 */           while (green.hasNext()) {
/* 235 */             String num = (String)green.next();
/* 236 */             countgreen = Integer.valueOf(countgreen.intValue() + 1);
/* 237 */             ((Arena)this.arenas.get(arena)).setGreens(Integer.valueOf(((Arena)this.arenas.get(arena)).getGreens().intValue() + 1));
/* 238 */             this.greenx = getConfig().getInt("arenas." + arena + ".greenspawns." + num + ".X");
/* 239 */             this.greeny = getConfig().getInt("arenas." + arena + ".greenspawns." + num + ".Y");
/* 240 */             this.greenz = getConfig().getInt("arenas." + arena + ".greenspawns." + num + ".Z");
/* 241 */             this.greenWorld = getConfig().getString("arenas." + arena + ".greenspawns." + num + ".World");
/* 242 */             Location tempLoc = Bukkit.getServer().getWorld(this.greenWorld).getBlockAt(this.greenx, this.greeny, this.greenz).getLocation();
/* 243 */             tempLoc.setPitch(getConfig().getInt("arenas." + arena + ".greenspawns." + num + ".Pitch"));
/* 244 */             tempLoc.setYaw(getConfig().getInt("arenas." + arena + ".greenspawns." + num + ".Yaw"));
/* 245 */             ((Arena)this.arenas.get(arena)).getGreenspawns().put(countgreen, tempLoc);
/*     */           }
/*     */         }
/* 248 */         Integer countyellow = Integer.valueOf(0);
/* 249 */         if (getConfig().getConfigurationSection("arenas." + arena + ".yellowspawns") != null) {
/* 250 */           Iterator yellow = getConfig().getConfigurationSection("arenas." + arena + ".yellowspawns").getKeys(false).iterator();
/* 251 */           while (yellow.hasNext()) {
/* 252 */             String num = (String)yellow.next();
/* 253 */             countyellow = Integer.valueOf(countyellow.intValue() + 1);
/* 254 */             ((Arena)this.arenas.get(arena)).setYellows(Integer.valueOf(((Arena)this.arenas.get(arena)).getYellows().intValue() + 1));
/* 255 */             this.yellowx = getConfig().getInt("arenas." + arena + ".yellowspawns." + num + ".X");
/* 256 */             this.yellowy = getConfig().getInt("arenas." + arena + ".yellowspawns." + num + ".Y");
/* 257 */             this.yellowz = getConfig().getInt("arenas." + arena + ".yellowspawns." + num + ".Z");
/* 258 */             this.yellowWorld = getConfig().getString("arenas." + arena + ".yellowspawns." + num + ".World");
/* 259 */             Location tempLoc = Bukkit.getServer().getWorld(this.yellowWorld).getBlockAt(this.yellowx, this.yellowy, this.yellowz).getLocation();
/* 260 */             tempLoc.setPitch(getConfig().getInt("arenas." + arena + ".yellowspawns." + num + ".Pitch"));
/* 261 */             tempLoc.setYaw(getConfig().getInt("arenas." + arena + ".yellowspawns." + num + ".Yaw"));
/* 262 */             ((Arena)this.arenas.get(arena)).getYellowspawns().put(countyellow, tempLoc);
/*     */           }
/*     */         }
/* 265 */         if (getConfig().getConfigurationSection("arenas." + arena + ".droplocations") != null) {
/* 266 */           Iterator droplocations = getConfig().getConfigurationSection("arenas." + arena + ".droplocations").getKeys(false).iterator();
/* 267 */           while (droplocations.hasNext()) {
/* 268 */             String droplocation = (String)droplocations.next();
/* 269 */             this.loc11 = getConfig().getInt("arenas." + arena + ".droplocations." + droplocation + ".location1.X");
/* 270 */             this.loc12 = getConfig().getInt("arenas." + arena + ".droplocations." + droplocation + ".location1.Y");
/* 271 */             this.loc13 = getConfig().getInt("arenas." + arena + ".droplocations." + droplocation + ".location1.Z");
/* 272 */             this.loc21 = getConfig().getInt("arenas." + arena + ".droplocations." + droplocation + ".location2.X");
/* 273 */             this.loc22 = getConfig().getInt("arenas." + arena + ".droplocations." + droplocation + ".location2.Y");
/* 274 */             this.loc23 = getConfig().getInt("arenas." + arena + ".droplocations." + droplocation + ".location2.Z");
/* 275 */             this.locWorld = Bukkit.getServer().getWorld(getConfig().getString("arenas." + arena + ".droplocations." + droplocation + ".World"));
/* 276 */             ((Arena)this.arenas.get(arena)).setTempLoc(this.locWorld.getBlockAt(this.loc11, this.loc12, this.loc13).getLocation());
/* 277 */             ((Arena)this.arenas.get(arena)).setTempLoc1(this.locWorld.getBlockAt(this.loc21, this.loc22, this.loc23).getLocation());
/* 278 */             ((Arena)this.arenas.get(arena)).CreateDropLocation();
/*     */           }
/*     */         }
/* 281 */         if (getConfig().getConfigurationSection("arenas." + arena + ".buildlocations") != null) {
/* 282 */           Integer count = Integer.valueOf(0);
/* 283 */           ((Arena)this.arenas.get(arena)).setDrops(Integer.valueOf(0));
/* 284 */           Iterator buildlocations = getConfig().getConfigurationSection("arenas." + arena + ".buildlocations").getKeys(false).iterator();
/* 285 */           while (buildlocations.hasNext()) {
/* 286 */             String num = (String)buildlocations.next();
/* 287 */             count = Integer.valueOf(count.intValue() + 1);
/* 288 */             this.save11 = getConfig().getInt("arenas." + arena + ".buildlocations." + num + ".location1.X");
/* 289 */             this.save12 = getConfig().getInt("arenas." + arena + ".buildlocations." + num + ".location1.Y");
/* 290 */             this.save13 = getConfig().getInt("arenas." + arena + ".buildlocations." + num + ".location1.Z");
/* 291 */             this.save21 = getConfig().getInt("arenas." + arena + ".buildlocations." + num + ".location2.X");
/* 292 */             this.save22 = getConfig().getInt("arenas." + arena + ".buildlocations." + num + ".location2.Y");
/* 293 */             this.save23 = getConfig().getInt("arenas." + arena + ".buildlocations." + num + ".location2.Z");
/* 294 */             this.saveWorld = Bukkit.getServer().getWorld(getConfig().getString("arenas." + arena + ".buildlocations." + num + ".locationWorld"));
/* 295 */             ((Arena)this.arenas.get(arena)).getSaveregion().put(count, this.saveWorld.getBlockAt(this.save11, this.save12, this.save13).getLocation());
/* 296 */             ((Arena)this.arenas.get(arena)).getSaveregion1().put(count, this.saveWorld.getBlockAt(this.save21, this.save22, this.save23).getLocation());
/* 297 */             ((Arena)this.arenas.get(arena)).setSaveTotal(Integer.valueOf(((Arena)this.arenas.get(arena)).getSaveTotal().intValue() + 1));
/*     */           }
/*     */         }
/* 300 */         ((Arena)this.arenas.get(arena)).setDropTime(Integer.valueOf(getConfig().getInt("arenas." + arena + ".time")));
/* 301 */         ((Arena)this.arenas.get(arena)).min();
/* 302 */         if (getConfig().getConfigurationSection("arenas." + arena + ".SignWalls") != null) {
/* 303 */           Iterator itr = getConfig().getConfigurationSection("arenas." + arena + ".SignWalls").getKeys(false).iterator();
/* 304 */           while (itr.hasNext()) {
/* 305 */             String configNum = (String)itr.next();
/* 306 */             Integer num = Integer.valueOf(Integer.parseInt(configNum));
/* 307 */             String arenaName = getConfig().getString("arenas." + arena + ".SignWalls." + configNum + ".Arena");
/* 308 */             int X = getConfig().getInt("arenas." + arena + ".SignWalls." + configNum + ".X");
/* 309 */             int Y = getConfig().getInt("arenas." + arena + ".SignWalls." + configNum + ".Y");
/* 310 */             int Z = getConfig().getInt("arenas." + arena + ".SignWalls." + configNum + ".Z");
/* 311 */             byte Rotation = (byte)getConfig().getInt("arenas." + arena + ".SignWalls." + configNum + ".Rotation");
/* 312 */             World world = Bukkit.getServer().getWorld(getConfig().getString("arenas." + arena + ".SignWalls." + configNum + ".World"));
/* 313 */             SignWall signwall = new SignWall(world.getBlockAt(X, Y, Z).getLocation(), Rotation, arenaName, ((Arena)this.arenas.get(arena)).getMin(), "Waiting");
/* 314 */             ((Arena)this.arenas.get(arena)).getSignWalls().put(num, signwall);
/* 315 */             ((SignWall)((Arena)this.arenas.get(arena)).getSignWalls().get(num)).Create();
/*     */           }
/*     */         }
/* 318 */         ((Arena)this.arenas.get(arena)).setFiles(Integer.valueOf(getConfig().getInt("arenas." + arena + ".Files")));
/*     */       }
/*     */       try {
/* 321 */         getConfig().save(getDataFolder() + System.getProperty("file.separator") + "config.yml");
/*     */       } catch (IOException e) {
/* 323 */         e.printStackTrace();
/*     */       }
/*     */     } }
/*     */ 
/*     */   public void SaveConfig() {
/* 328 */     getConfig().set("AllowedCommands", this.allowedCommands);
/* 329 */     Iterator iterator = this.arenas.keySet().iterator();
/* 330 */     while (iterator.hasNext()) {
/* 331 */       String arena = (String)iterator.next();
/* 332 */       if (((Arena)this.arenas.get(arena)).getDropTime() != null) {
/* 333 */         getConfig().set("arenas." + arena + ".time", ((Arena)this.arenas.get(arena)).getDropTime());
/*     */       }
/* 335 */       if (!((Arena)this.arenas.get(arena)).getMessages().isEmpty()) {
/* 336 */         Iterator itr = ((Arena)this.arenas.get(arena)).getMessages().keySet().iterator();
/* 337 */         while (itr.hasNext()) {
/* 338 */           getConfig().set("arenas." + arena + ".broadcasts." + itr.next(), "");
/*     */         }
/*     */       }
/* 341 */       getConfig().set("arenas." + arena + ".location1.X", Double.valueOf(((Arena)this.arenas.get(arena)).getLoc1().getX()));
/* 342 */       getConfig().set("arenas." + arena + ".location1.Y", Double.valueOf(((Arena)this.arenas.get(arena)).getLoc1().getY()));
/* 343 */       getConfig().set("arenas." + arena + ".location1.Z", Double.valueOf(((Arena)this.arenas.get(arena)).getLoc1().getZ()));
/* 344 */       getConfig().set("arenas." + arena + ".location1.World", ((Arena)this.arenas.get(arena)).getLoc1().getWorld().getName());
/* 345 */       getConfig().set("arenas." + arena + ".location2.X", Double.valueOf(((Arena)this.arenas.get(arena)).getLoc2().getX()));
/* 346 */       getConfig().set("arenas." + arena + ".location2.Y", Double.valueOf(((Arena)this.arenas.get(arena)).getLoc2().getY()));
/* 347 */       getConfig().set("arenas." + arena + ".location2.Z", Double.valueOf(((Arena)this.arenas.get(arena)).getLoc2().getZ()));
/* 348 */       if (((Arena)this.arenas.get(arena)).getLobby() != null) {
/* 349 */         getConfig().set("arenas." + arena + ".lobby.X", Double.valueOf(((Arena)this.arenas.get(arena)).getLobby().getX()));
/* 350 */         getConfig().set("arenas." + arena + ".lobby.Y", Double.valueOf(((Arena)this.arenas.get(arena)).getLobby().getY()));
/* 351 */         getConfig().set("arenas." + arena + ".lobby.Z", Double.valueOf(((Arena)this.arenas.get(arena)).getLobby().getZ()));
/* 352 */         getConfig().set("arenas." + arena + ".lobby.Pitch", Float.valueOf(((Arena)this.arenas.get(arena)).getLobby().getPitch()));
/* 353 */         getConfig().set("arenas." + arena + ".lobby.Yaw", Float.valueOf(((Arena)this.arenas.get(arena)).getLobby().getYaw()));
/* 354 */         getConfig().set("arenas." + arena + ".lobby.World", ((Arena)this.arenas.get(arena)).getLobby().getWorld().getName());
/*     */       }
/* 356 */       if (((Arena)this.arenas.get(arena)).getWin() != null) {
/* 357 */         getConfig().set("arenas." + arena + ".win.X", Double.valueOf(((Arena)this.arenas.get(arena)).getWin().getX()));
/* 358 */         getConfig().set("arenas." + arena + ".win.Y", Double.valueOf(((Arena)this.arenas.get(arena)).getWin().getY()));
/* 359 */         getConfig().set("arenas." + arena + ".win.Z", Double.valueOf(((Arena)this.arenas.get(arena)).getWin().getZ()));
/* 360 */         getConfig().set("arenas." + arena + ".win.Pitch", Float.valueOf(((Arena)this.arenas.get(arena)).getWin().getPitch()));
/* 361 */         getConfig().set("arenas." + arena + ".win.Yaw", Float.valueOf(((Arena)this.arenas.get(arena)).getWin().getYaw()));
/* 362 */         getConfig().set("arenas." + arena + ".win.World", ((Arena)this.arenas.get(arena)).getWin().getWorld().getName());
/*     */       }
/* 364 */       if (((Arena)this.arenas.get(arena)).getLose() != null) {
/* 365 */         getConfig().set("arenas." + arena + ".lose.X", Double.valueOf(((Arena)this.arenas.get(arena)).getLose().getX()));
/* 366 */         getConfig().set("arenas." + arena + ".lose.Y", Double.valueOf(((Arena)this.arenas.get(arena)).getLose().getY()));
/* 367 */         getConfig().set("arenas." + arena + ".lose.Z", Double.valueOf(((Arena)this.arenas.get(arena)).getLose().getZ()));
/* 368 */         getConfig().set("arenas." + arena + ".lose.Pitch", Float.valueOf(((Arena)this.arenas.get(arena)).getLose().getPitch()));
/* 369 */         getConfig().set("arenas." + arena + ".lose.Yaw", Float.valueOf(((Arena)this.arenas.get(arena)).getLose().getYaw()));
/* 370 */         getConfig().set("arenas." + arena + ".lose.World", ((Arena)this.arenas.get(arena)).getLose().getWorld().getName());
/*     */       }
/* 372 */       Iterator red = ((Arena)this.arenas.get(arena)).getRedspawns().keySet().iterator();
/* 373 */       while (red.hasNext()) {
/* 374 */         Integer num = (Integer)red.next();
/* 375 */         getConfig().set("arenas." + arena + ".redspawns." + num + ".X", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getRedspawns().get(num)).getX()));
/* 376 */         getConfig().set("arenas." + arena + ".redspawns." + num + ".Y", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getRedspawns().get(num)).getY()));
/* 377 */         getConfig().set("arenas." + arena + ".redspawns." + num + ".Z", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getRedspawns().get(num)).getZ()));
/* 378 */         getConfig().set("arenas." + arena + ".redspawns." + num + ".Pitch", Float.valueOf(((Location)((Arena)this.arenas.get(arena)).getRedspawns().get(num)).getPitch()));
/* 379 */         getConfig().set("arenas." + arena + ".redspawns." + num + ".Yaw", Float.valueOf(((Location)((Arena)this.arenas.get(arena)).getRedspawns().get(num)).getYaw()));
/* 380 */         getConfig().set("arenas." + arena + ".redspawns." + num + ".World", ((Location)((Arena)this.arenas.get(arena)).getRedspawns().get(num)).getWorld().getName());
/*     */       }
/* 382 */       Iterator blue = ((Arena)this.arenas.get(arena)).getBluespawns().keySet().iterator();
/* 383 */       while (blue.hasNext()) {
/* 384 */         Integer num = (Integer)blue.next();
/* 385 */         getConfig().set("arenas." + arena + ".bluespawns." + num + ".X", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getBluespawns().get(num)).getX()));
/* 386 */         getConfig().set("arenas." + arena + ".bluespawns." + num + ".Y", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getBluespawns().get(num)).getY()));
/* 387 */         getConfig().set("arenas." + arena + ".bluespawns." + num + ".Z", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getBluespawns().get(num)).getZ()));
/* 388 */         getConfig().set("arenas." + arena + ".bluespawns." + num + ".Pitch", Float.valueOf(((Location)((Arena)this.arenas.get(arena)).getBluespawns().get(num)).getPitch()));
/* 389 */         getConfig().set("arenas." + arena + ".bluespawns." + num + ".Yaw", Float.valueOf(((Location)((Arena)this.arenas.get(arena)).getBluespawns().get(num)).getYaw()));
/* 390 */         getConfig().set("arenas." + arena + ".bluespawns." + num + ".World", ((Location)((Arena)this.arenas.get(arena)).getBluespawns().get(num)).getWorld().getName());
/*     */       }
/* 392 */       Iterator green = ((Arena)this.arenas.get(arena)).getGreenspawns().keySet().iterator();
/* 393 */       while (green.hasNext()) {
/* 394 */         Integer num = (Integer)green.next();
/* 395 */         getConfig().set("arenas." + arena + ".greenspawns." + num + ".X", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getGreenspawns().get(num)).getX()));
/* 396 */         getConfig().set("arenas." + arena + ".greenspawns." + num + ".Y", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getGreenspawns().get(num)).getY()));
/* 397 */         getConfig().set("arenas." + arena + ".greenspawns." + num + ".Z", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getGreenspawns().get(num)).getZ()));
/* 398 */         getConfig().set("arenas." + arena + ".greenspawns." + num + ".Pitch", Float.valueOf(((Location)((Arena)this.arenas.get(arena)).getGreenspawns().get(num)).getPitch()));
/* 399 */         getConfig().set("arenas." + arena + ".greenspawns." + num + ".Yaw", Float.valueOf(((Location)((Arena)this.arenas.get(arena)).getGreenspawns().get(num)).getYaw()));
/* 400 */         getConfig().set("arenas." + arena + ".greenspawns." + num + ".World", ((Location)((Arena)this.arenas.get(arena)).getGreenspawns().get(num)).getWorld().getName());
/*     */       }
/* 402 */       Iterator yellow = ((Arena)this.arenas.get(arena)).getYellowspawns().keySet().iterator();
/* 403 */       while (yellow.hasNext()) {
/* 404 */         Integer num = (Integer)yellow.next();
/* 405 */         getConfig().set("arenas." + arena + ".yellowspawns." + num + ".X", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getYellowspawns().get(num)).getX()));
/* 406 */         getConfig().set("arenas." + arena + ".yellowspawns." + num + ".Y", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getYellowspawns().get(num)).getY()));
/* 407 */         getConfig().set("arenas." + arena + ".yellowspawns." + num + ".Z", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getYellowspawns().get(num)).getZ()));
/* 408 */         getConfig().set("arenas." + arena + ".yellowspawns." + num + ".Pitch", Float.valueOf(((Location)((Arena)this.arenas.get(arena)).getYellowspawns().get(num)).getPitch()));
/* 409 */         getConfig().set("arenas." + arena + ".yellowspawns." + num + ".Yaw", Float.valueOf(((Location)((Arena)this.arenas.get(arena)).getYellowspawns().get(num)).getYaw()));
/* 410 */         getConfig().set("arenas." + arena + ".yellowspawns." + num + ".World", ((Location)((Arena)this.arenas.get(arena)).getYellowspawns().get(num)).getWorld().getName());
/*     */       }
/* 412 */       Iterator drops = ((Arena)this.arenas.get(arena)).getDroploc1().keySet().iterator();
/* 413 */       while (drops.hasNext()) {
/* 414 */         Integer num = (Integer)drops.next();
/* 415 */         if (num != null) {
/* 416 */           getConfig().set("arenas." + arena + ".droplocations." + num + ".location1.X", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getDroploc1().get(num)).getX()));
/* 417 */           getConfig().set("arenas." + arena + ".droplocations." + num + ".location1.Y", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getDroploc1().get(num)).getY()));
/* 418 */           getConfig().set("arenas." + arena + ".droplocations." + num + ".location1.Z", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getDroploc1().get(num)).getZ()));
/* 419 */           getConfig().set("arenas." + arena + ".droplocations." + num + ".location2.X", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getDroploc2().get(num)).getX()));
/* 420 */           getConfig().set("arenas." + arena + ".droplocations." + num + ".location2.Y", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getDroploc2().get(num)).getY()));
/* 421 */           getConfig().set("arenas." + arena + ".droplocations." + num + ".location2.Z", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getDroploc2().get(num)).getZ()));
/* 422 */           getConfig().set("arenas." + arena + ".droplocations." + num + ".World", ((Location)((Arena)this.arenas.get(arena)).getDroploc1().get(num)).getWorld().getName());
/*     */         }
/*     */       }
/* 425 */       Iterator location1 = ((Arena)this.arenas.get(arena)).getSaveregion().keySet().iterator();
/* 426 */       while (location1.hasNext()) {
/* 427 */         Integer num = (Integer)location1.next();
/* 428 */         if (num != null) {
/* 429 */           getConfig().set("arenas." + arena + ".buildlocations." + num + ".location1.X", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getSaveregion().get(num)).getX()));
/* 430 */           getConfig().set("arenas." + arena + ".buildlocations." + num + ".location1.Y", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getSaveregion().get(num)).getY()));
/* 431 */           getConfig().set("arenas." + arena + ".buildlocations." + num + ".location1.Z", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getSaveregion().get(num)).getZ()));
/* 432 */           getConfig().set("arenas." + arena + ".buildlocations." + num + ".location2.X", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getSaveregion1().get(num)).getX()));
/* 433 */           getConfig().set("arenas." + arena + ".buildlocations." + num + ".location2.Y", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getSaveregion1().get(num)).getY()));
/* 434 */           getConfig().set("arenas." + arena + ".buildlocations." + num + ".location2.Z", Double.valueOf(((Location)((Arena)this.arenas.get(arena)).getSaveregion1().get(num)).getZ()));
/* 435 */           getConfig().set("arenas." + arena + ".buildlocations." + num + ".locationWorld", ((Location)((Arena)this.arenas.get(arena)).getSaveregion().get(num)).getWorld().getName());
/*     */         }
/*     */       }
/* 438 */       Iterator arenas = ((Arena)this.arenas.get(arena)).getSignWalls().keySet().iterator();
/* 439 */       while (arenas.hasNext()) {
/* 440 */         Integer num = (Integer)arenas.next();
/* 441 */         getConfig().set("arenas." + arena + ".SignWalls." + num + ".X", Double.valueOf(((SignWall)((Arena)this.arenas.get(arena)).getSignWalls().get(num)).getStartSign().getX()));
/* 442 */         getConfig().set("arenas." + arena + ".SignWalls." + num + ".Y", Double.valueOf(((SignWall)((Arena)this.arenas.get(arena)).getSignWalls().get(num)).getStartSign().getY()));
/* 443 */         getConfig().set("arenas." + arena + ".SignWalls." + num + ".Z", Double.valueOf(((SignWall)((Arena)this.arenas.get(arena)).getSignWalls().get(num)).getStartSign().getZ()));
/* 444 */         getConfig().set("arenas." + arena + ".SignWalls." + num + ".Rotation", Byte.valueOf(((SignWall)((Arena)this.arenas.get(arena)).getSignWalls().get(num)).getRotation()));
/* 445 */         getConfig().set("arenas." + arena + ".SignWalls." + num + ".World", ((SignWall)((Arena)this.arenas.get(arena)).getSignWalls().get(num)).getStartSign().getWorld().getName());
/* 446 */         getConfig().set("arenas." + arena + ".SignWalls." + num + ".Arena", ((SignWall)((Arena)this.arenas.get(arena)).getSignWalls().get(num)).getArena());
/*     */       }
/* 448 */       if (((Arena)this.arenas.get(arena)).getFiles() != null)
/* 449 */         getConfig().set("arenas." + arena + ".Files", ((Arena)this.arenas.get(arena)).getFiles());
/*     */       try
/*     */       {
/* 452 */         getConfig().save(getDataFolder() + System.getProperty("file.separator") + "config.yml");
/*     */       } catch (IOException e) {
/* 454 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 457 */     getConfig().set("CheckForUpdates", Boolean.valueOf(this.checkForUpdates));
/*     */   }
/*     */   public void DeleteArena() {
/* 460 */     if ((getConfig().getConfigurationSection("").contains("arenas")) && 
/* 461 */       (getConfig().getConfigurationSection("arenas").contains(this.DeleteArena))) {
/* 462 */       Iterator iterator = getConfig().getConfigurationSection("arenas").getKeys(false).iterator();
/* 463 */       int chocolate = 0;
/* 464 */       while (iterator.hasNext()) {
/* 465 */         chocolate++;
/* 466 */         iterator.next();
/*     */       }
/* 468 */       if (chocolate != 1) {
/* 469 */         getConfig().set("arenas." + this.DeleteArena, null);
/*     */         try {
/* 471 */           getConfig().save(getDataFolder() + System.getProperty("file.separator") + "config.yml");
/*     */         } catch (IOException e) {
/* 473 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */       else {
/* 477 */         getConfig().set("arenas", null);
/*     */         try {
/* 479 */           getConfig().save(getDataFolder() + System.getProperty("file.separator") + "config.yml");
/*     */         } catch (IOException e) {
/* 481 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void endGames() {
/* 488 */     Iterator list = this.arenas.keySet().iterator();
/* 489 */     while (list.hasNext()) {
/* 490 */       String name = (String)list.next();
/* 491 */       if (!((Arena)this.arenas.get(name)).getPlayerList().isEmpty()) {
/* 492 */         Iterator players = ((Arena)this.arenas.get(name)).getPlayerList().keySet().iterator();
/* 493 */         while (players.hasNext()) {
/* 494 */           Player player = (Player)players.next();
/* 495 */           ((Arena)this.arenas.get(name)).setRandom(player);
/* 496 */           ((Arena)this.arenas.get(name)).Leave();
/* 497 */           player.teleport(((Arena)this.arenas.get(name)).getLose());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.Bridges
 * JD-Core Version:    0.6.2
 */