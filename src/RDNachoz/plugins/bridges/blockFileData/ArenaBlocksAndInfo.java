/*     */ package RDNachoz.plugins.bridges.blockFileData;
/*     */ 
/*     */ import RDNachoz.plugins.bridges.Bridges;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.Chest;
/*     */ import org.bukkit.block.Sign;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.scheduler.BukkitScheduler;
/*     */ 
/*     */ public class ArenaBlocksAndInfo
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3896531915356967448L;
/*     */   private Bridges plugin;
/*  20 */   private Map<Integer, Integer> blockId = new HashMap();
/*  21 */   private Map<Integer, Integer> blockData = new HashMap();
/*  22 */   private Map<Integer, String[]> lines = new HashMap();
/*  23 */   private Map<Integer, ChestData> chests = new HashMap();
/*     */   private String world;
/*     */   private Integer startX;
/*     */   private Integer startY;
/*     */   private Integer startZ;
/*     */   private Integer count;
/*     */   private String name;
/*     */   private Integer fileNumber;
/*  31 */   private Map<Integer, Integer> x = new HashMap();
/*  32 */   private Map<Integer, Integer> y = new HashMap();
/*  33 */   private Map<Integer, Integer> z = new HashMap();
/*     */ 
/*     */   public ArenaBlocksAndInfo(Integer loc1x, Integer loc1y, Integer loc1z, Integer loc2x, Integer loc2y, Integer loc2z, String world, String arenaName, Bridges plugin, Integer x, Integer y, Integer z, Integer fileNumber) {
/*  36 */     this.world = world;
/*  37 */     this.plugin = plugin;
/*  38 */     this.startX = x;
/*  39 */     this.startY = y;
/*  40 */     this.startZ = z;
/*  41 */     this.count = Integer.valueOf(-1);
/*  42 */     this.name = arenaName;
/*  43 */     this.fileNumber = fileNumber;
/*  44 */     if (fileNumber.intValue() != -1)
/*  45 */       updateInfo(loc1x, loc1y, loc1z, loc2x, loc2y, loc2z);
/*     */   }
/*     */ 
/*     */   public void updateInfo(Integer loc1x, Integer loc1y, Integer loc1z, Integer loc2x, Integer loc2y, Integer loc2z)
/*     */   {
/*  50 */     Integer num = Integer.valueOf(0);
/*  51 */     Integer minX = Integer.valueOf(Math.min(loc1x.intValue(), loc2x.intValue()));
/*  52 */     Integer maxX = Integer.valueOf(Math.max(loc1x.intValue(), loc2x.intValue()));
/*  53 */     Integer minY = Integer.valueOf(Math.min(loc1y.intValue(), loc2y.intValue()));
/*  54 */     Integer maxY = Integer.valueOf(Math.max(loc1y.intValue(), loc2y.intValue()));
/*  55 */     Integer minZ = Integer.valueOf(Math.min(loc1z.intValue(), loc2z.intValue()));
/*  56 */     Integer maxZ = Integer.valueOf(Math.max(loc1z.intValue(), loc2z.intValue()));
/*  57 */     for (int y = minY.intValue(); y <= maxY.intValue(); y++)
/*  58 */       for (int x = minX.intValue(); x <= maxX.intValue(); x++)
/*  59 */         for (int z = minZ.intValue(); z <= maxZ.intValue(); z++) {
/*  60 */           if ((Bukkit.getWorld(this.world).getBlockAt(x, y, z).getLocation().getBlockX() == Bukkit.getWorld(this.world).getBlockAt(this.startX.intValue(), this.startY.intValue(), this.startZ.intValue()).getLocation().getBlockX()) && (Bukkit.getWorld(this.world).getBlockAt(x, y, z).getLocation().getBlockY() == Bukkit.getWorld(this.world).getBlockAt(this.startX.intValue(), this.startY.intValue(), this.startZ.intValue()).getLocation().getBlockY()) && (Bukkit.getWorld(this.world).getBlockAt(x, y, z).getLocation().getBlockZ() == Bukkit.getWorld(this.world).getBlockAt(this.startX.intValue(), this.startY.intValue(), this.startZ.intValue()).getLocation().getBlockZ())) {
/*  61 */             this.count = Integer.valueOf(0);
/*     */           }
/*  63 */           if (this.count.intValue() != -1)
/*  64 */             if (this.count.intValue() <= 20000) {
/*  65 */               this.x.put(num, Integer.valueOf(x));
/*  66 */               this.y.put(num, Integer.valueOf(y));
/*  67 */               this.z.put(num, Integer.valueOf(z));
/*  68 */               this.count = Integer.valueOf(this.count.intValue() + 1);
/*  69 */               num = Integer.valueOf(num.intValue() + 1);
/*  70 */               this.blockId.put(num, Integer.valueOf(Bukkit.getWorld(this.world).getBlockTypeIdAt(x, y, z)));
/*  71 */               this.blockData.put(num, Integer.valueOf(Bukkit.getWorld(this.world).getBlockAt(x, y, z).getData()));
/*  72 */               if (Bukkit.getWorld(this.world).getBlockAt(x, y, z).getType() == Material.CHEST) {
/*  73 */                 Chest chest = (Chest)Bukkit.getWorld(this.world).getBlockAt(x, y, z).getState();
/*  74 */                 ItemStack[] is = chest.getBlockInventory().getContents();
/*  75 */                 ChestData cd = new ChestData(is);
/*  76 */                 this.chests.put(num, cd);
/*     */               }
/*  78 */               else if ((Bukkit.getWorld(this.world).getBlockAt(x, y, z).getType() == Material.SIGN_POST) || (Bukkit.getWorld(this.world).getBlockAt(x, y, z).getType() == Material.WALL_SIGN)) {
/*  79 */                 Sign sign = (Sign)Bukkit.getWorld(this.world).getBlockAt(x, y, z).getState();
/*  80 */                 this.lines.put(num, sign.getLines());
/*     */               }
/*  82 */               if ((x == maxX.intValue()) && (y == maxY.intValue()) && (z == maxZ.intValue())) {
/*  83 */                 this.plugin.getServer().getScheduler().runTaskAsynchronously(this.plugin, new SaveDataRunnable(this, this.plugin, this.name, this.fileNumber, minX, minY, minZ, maxX, maxY, maxZ));
/*  84 */                 this.plugin = null;
/*  85 */                 this.count = Integer.valueOf(-1);
/*  86 */                 ArenaFileManager.setFilesForAnArena(this.fileNumber, this.name);
/*     */               }
/*     */             }
/*     */             else
/*     */             {
/*  91 */               this.plugin.getServer().getScheduler().runTaskAsynchronously(this.plugin, new SaveDataRunnable(this, this.plugin, this.name, this.fileNumber, minX, minY, minZ, maxX, maxY, maxZ));
/*  92 */               new ArenaBlocksAndInfo(loc1x, loc1y, loc1z, loc2x, loc2y, loc2z, this.world, this.name, this.plugin, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z), Integer.valueOf(this.fileNumber.intValue() + 1));
/*  93 */               return;
/*     */             }
/*     */         }
/*     */   }
/*     */ 
/*     */   public void restoreBlocks()
/*     */   {
/* 100 */     for (Integer num : this.x.keySet()) {
/* 101 */       World world = Bukkit.getWorld(this.world);
/* 102 */       Location loc = world.getBlockAt(((Integer)this.x.get(num)).intValue(), ((Integer)this.y.get(num)).intValue(), ((Integer)this.z.get(num)).intValue()).getLocation();
/* 103 */       loc.getBlock().setType(Material.getMaterial(((Integer)this.blockId.get(Integer.valueOf(num.intValue() + 1))).intValue()));
/* 104 */       int data = ((Integer)this.blockData.get(Integer.valueOf(num.intValue() + 1))).intValue();
/* 105 */       loc.getBlock().setData((byte)data);
/* 106 */       if (loc.getBlock().getType() == Material.CHEST) {
/* 107 */         Chest chest = (Chest)loc.getBlock().getState();
/* 108 */         chest.getBlockInventory().setContents(((ChestData)this.chests.get(Integer.valueOf(num.intValue() + 1))).getChestData());
/*     */       }
/* 110 */       if ((loc.getBlock().getType() == Material.SIGN_POST) || (loc.getBlock().getType() == Material.WALL_SIGN)) {
/* 111 */         Sign sign = (Sign)loc.getBlock().getState();
/* 112 */         sign.setLine(0, ((String[])this.lines.get(Integer.valueOf(num.intValue() + 1)))[0]);
/* 113 */         sign.setLine(1, ((String[])this.lines.get(Integer.valueOf(num.intValue() + 1)))[1]);
/* 114 */         sign.setLine(2, ((String[])this.lines.get(Integer.valueOf(num.intValue() + 1)))[2]);
/* 115 */         sign.setLine(3, ((String[])this.lines.get(Integer.valueOf(num.intValue() + 1)))[3]);
/* 116 */         sign.update();
/*     */       }
/* 118 */       if (loc.getBlock().getType() == Material.AIR)
/* 119 */         Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new CheckForFallingItems(this.plugin, loc), 15L);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setX(Map<Integer, Integer> x)
/*     */   {
/* 125 */     this.x = x;
/*     */   }
/*     */ 
/*     */   public void setY(Map<Integer, Integer> y) {
/* 129 */     this.y = y;
/*     */   }
/*     */ 
/*     */   public void setZ(Map<Integer, Integer> z) {
/* 133 */     this.z = z;
/*     */   }
/*     */ 
/*     */   public void setChests(Map<Integer, ChestData> chests) {
/* 137 */     this.chests = chests;
/*     */   }
/*     */ 
/*     */   public void setBlockIds(Map<Integer, Integer> blockIds) {
/* 141 */     this.blockId = blockIds;
/*     */   }
/*     */ 
/*     */   public void setBlockData(Map<Integer, Integer> blockData) {
/* 145 */     this.blockData = blockData;
/*     */   }
/*     */ 
/*     */   public void setLines(Map<Integer, String[]> lines) {
/* 149 */     this.lines = lines;
/*     */   }
/*     */ 
/*     */   public String getWorld() {
/* 153 */     return this.world;
/*     */   }
/*     */ 
/*     */   public Map<Integer, Integer> getBlockId() {
/* 157 */     return this.blockId;
/*     */   }
/*     */ 
/*     */   public Map<Integer, Integer> getBlockData() {
/* 161 */     return this.blockData;
/*     */   }
/*     */ 
/*     */   public Map<Integer, String[]> getLines() {
/* 165 */     return this.lines;
/*     */   }
/*     */ 
/*     */   public Map<Integer, ChestData> getChests() {
/* 169 */     return this.chests;
/*     */   }
/*     */ 
/*     */   public Map<Integer, Integer> getX() {
/* 173 */     return this.x;
/*     */   }
/*     */ 
/*     */   public Map<Integer, Integer> getY() {
/* 177 */     return this.y;
/*     */   }
/*     */ 
/*     */   public Map<Integer, Integer> getZ() {
/* 181 */     return this.z;
/*     */   }
/*     */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.blockFileData.ArenaBlocksAndInfo
 * JD-Core Version:    0.6.2
 */