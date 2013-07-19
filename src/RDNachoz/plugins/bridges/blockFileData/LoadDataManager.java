/*    */ package RDNachoz.plugins.bridges.blockFileData;
/*    */ 
/*    */ import RDNachoz.plugins.bridges.Bridges;
/*    */ 
/*    */ public class LoadDataManager
/*    */   implements Runnable
/*    */ {
/*    */   private ArenaData ad;
/*    */   private Bridges plugin;
/*    */   private String arena;
/*    */ 
/*    */   public LoadDataManager(ArenaData ad, Bridges plugin, String arena)
/*    */   {
/* 14 */     this.ad = ad;
/* 15 */     this.plugin = plugin;
/* 16 */     this.arena = arena;
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/* 21 */     ArenaBlocksAndInfo abai = new ArenaBlocksAndInfo(this.ad.getStartx(), this.ad.getStartY(), this.ad.getStartZ(), this.ad.getEndX(), this.ad.getEndY(), this.ad.getEndZ(), this.ad.getWorld(), this.arena, this.plugin, this.ad.getStartx(), this.ad.getStartY(), this.ad.getStartZ(), Integer.valueOf(-1));
/* 22 */     abai.setX(this.ad.getX());
/* 23 */     abai.setY(this.ad.getY());
/* 24 */     abai.setZ(this.ad.getZ());
/* 25 */     abai.setLines(this.ad.getLines());
/* 26 */     abai.setBlockData(this.ad.getBlockData());
/* 27 */     abai.setBlockIds(this.ad.getBlockId());
/* 28 */     abai.setChests(this.ad.getChests());
/* 29 */     abai.restoreBlocks();
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.blockFileData.LoadDataManager
 * JD-Core Version:    0.6.2
 */