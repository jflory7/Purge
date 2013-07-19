/*    */ package RDNachoz.plugins.bridges.blockFileData;
/*    */ 
/*    */ import RDNachoz.plugins.bridges.Bridges;
/*    */ import lucariatias.plugins.walls.ObjectFileLib;
/*    */ 
/*    */ public class SaveDataRunnable
/*    */   implements Runnable
/*    */ {
/*    */   private ArenaBlocksAndInfo arenaData;
/*    */   private Bridges plugin;
/*    */   private String arenaName;
/*    */   private Integer fileNumber;
/*    */   private Integer minX;
/*    */   private Integer minY;
/*    */   private Integer minZ;
/*    */   private Integer maxX;
/*    */   private Integer maxY;
/*    */   private Integer maxZ;
/*    */ 
/*    */   public SaveDataRunnable(ArenaBlocksAndInfo arena, Bridges plugin, String arenaName, Integer fileNumber, Integer minX, Integer minY, Integer minZ, Integer maxX, Integer maxY, Integer maxZ)
/*    */   {
/* 22 */     this.arenaData = arena;
/* 23 */     this.plugin = plugin;
/* 24 */     this.arenaName = arenaName;
/* 25 */     this.fileNumber = fileNumber;
/* 26 */     this.maxX = maxX;
/* 27 */     this.maxY = maxY;
/* 28 */     this.maxZ = maxZ;
/* 29 */     this.minX = minX;
/* 30 */     this.minY = minY;
/* 31 */     this.minZ = minZ;
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/* 36 */     ObjectFileLib.saveObject(this.plugin, new ArenaData(this.arenaData.getX(), this.arenaData.getY(), this.arenaData.getZ(), this.arenaData.getWorld(), this.arenaData.getBlockId(), this.arenaData.getBlockData(), this.arenaData.getLines(), this.arenaData.getChests(), this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ), this.arenaName + " Blocks" + this.fileNumber);
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.blockFileData.SaveDataRunnable
 * JD-Core Version:    0.6.2
 */