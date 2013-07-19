/*     */ package RDNachoz.plugins.bridges.blockFileData;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ArenaData
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3827864816525437344L;
/*  11 */   private Map<Integer, Integer> blockId = new HashMap();
/*  12 */   private Map<Integer, Integer> blockData = new HashMap();
/*  13 */   private Map<Integer, String[]> lines = new HashMap();
/*  14 */   private Map<Integer, ChestData> chests = new HashMap();
/*     */   private String world;
/*  16 */   private Map<Integer, Integer> x = new HashMap();
/*  17 */   private Map<Integer, Integer> y = new HashMap();
/*  18 */   private Map<Integer, Integer> z = new HashMap();
/*     */   private Integer startx;
/*     */   private Integer startY;
/*     */   private Integer startZ;
/*     */   private Integer endX;
/*     */   private Integer endY;
/*     */   private Integer endZ;
/*     */ 
/*     */   public ArenaData(Map<Integer, Integer> x, Map<Integer, Integer> y, Map<Integer, Integer> z, String world, Map<Integer, Integer> blockIds, Map<Integer, Integer> blockData, Map<Integer, String[]> lines, Map<Integer, ChestData> chests, Integer startX, Integer startY, Integer startZ, Integer endX, Integer endY, Integer endZ)
/*     */   {
/*  28 */     this.blockId = blockIds;
/*  29 */     this.blockData = blockData;
/*  30 */     this.lines = lines;
/*  31 */     this.chests = chests;
/*  32 */     this.world = world;
/*  33 */     this.x = x;
/*  34 */     this.y = y;
/*  35 */     this.z = z;
/*  36 */     this.startx = startX;
/*  37 */     this.startY = startY;
/*  38 */     this.startZ = startZ;
/*  39 */     this.endX = endX;
/*  40 */     this.endY = endY;
/*  41 */     this.endZ = endZ;
/*     */   }
/*     */ 
/*     */   public Map<Integer, Integer> getBlockId() {
/*  45 */     return this.blockId;
/*     */   }
/*     */ 
/*     */   public void setBlockId(Map<Integer, Integer> blockId) {
/*  49 */     this.blockId = blockId;
/*     */   }
/*     */ 
/*     */   public Map<Integer, Integer> getBlockData() {
/*  53 */     return this.blockData;
/*     */   }
/*     */ 
/*     */   public void setBlockData(Map<Integer, Integer> blockData) {
/*  57 */     this.blockData = blockData;
/*     */   }
/*     */ 
/*     */   public Map<Integer, String[]> getLines() {
/*  61 */     return this.lines;
/*     */   }
/*     */ 
/*     */   public void setLines(Map<Integer, String[]> lines) {
/*  65 */     this.lines = lines;
/*     */   }
/*     */ 
/*     */   public Map<Integer, ChestData> getChests() {
/*  69 */     return this.chests;
/*     */   }
/*     */ 
/*     */   public void setChests(Map<Integer, ChestData> chests) {
/*  73 */     this.chests = chests;
/*     */   }
/*     */ 
/*     */   public String getWorld() {
/*  77 */     return this.world;
/*     */   }
/*     */ 
/*     */   public void setWorld(String world) {
/*  81 */     this.world = world;
/*     */   }
/*     */ 
/*     */   public Map<Integer, Integer> getX() {
/*  85 */     return this.x;
/*     */   }
/*     */ 
/*     */   public void setX(Map<Integer, Integer> x) {
/*  89 */     this.x = x;
/*     */   }
/*     */ 
/*     */   public Map<Integer, Integer> getY() {
/*  93 */     return this.y;
/*     */   }
/*     */ 
/*     */   public void setY(Map<Integer, Integer> y) {
/*  97 */     this.y = y;
/*     */   }
/*     */ 
/*     */   public Map<Integer, Integer> getZ() {
/* 101 */     return this.z;
/*     */   }
/*     */ 
/*     */   public void setZ(Map<Integer, Integer> z) {
/* 105 */     this.z = z;
/*     */   }
/*     */ 
/*     */   public Integer getStartx() {
/* 109 */     return this.startx;
/*     */   }
/*     */ 
/*     */   public void setStartx(Integer startx) {
/* 113 */     this.startx = startx;
/*     */   }
/*     */ 
/*     */   public Integer getStartY() {
/* 117 */     return this.startY;
/*     */   }
/*     */ 
/*     */   public void setStartY(Integer startY) {
/* 121 */     this.startY = startY;
/*     */   }
/*     */ 
/*     */   public Integer getEndX() {
/* 125 */     return this.endX;
/*     */   }
/*     */ 
/*     */   public void setEndX(Integer endX) {
/* 129 */     this.endX = endX;
/*     */   }
/*     */ 
/*     */   public Integer getStartZ() {
/* 133 */     return this.startZ;
/*     */   }
/*     */ 
/*     */   public void setStartZ(Integer startZ) {
/* 137 */     this.startZ = startZ;
/*     */   }
/*     */ 
/*     */   public Integer getEndY() {
/* 141 */     return this.endY;
/*     */   }
/*     */ 
/*     */   public void setEndY(Integer endY) {
/* 145 */     this.endY = endY;
/*     */   }
/*     */ 
/*     */   public Integer getEndZ() {
/* 149 */     return this.endZ;
/*     */   }
/*     */ 
/*     */   public void setEndZ(Integer endZ) {
/* 153 */     this.endZ = endZ;
/*     */   }
/*     */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.blockFileData.ArenaData
 * JD-Core Version:    0.6.2
 */