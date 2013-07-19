/*    */ package RDNachoz.plugins.bridges.blockFileData;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.material.MaterialData;
/*    */ 
/*    */ public class ChestData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 6457563082520945086L;
/* 12 */   private Map<Integer, Integer> type = new HashMap();
/* 13 */   private Map<Integer, Integer> amount = new HashMap();
/* 14 */   private Map<Integer, Integer> durariblitiy = new HashMap();
/* 15 */   private Map<Integer, Integer> data = new HashMap();
/*    */ 
/*    */   public ChestData(ItemStack[] iss) {
/* 18 */     Integer num = Integer.valueOf(0);
/* 19 */     for (ItemStack is : iss) {
/* 20 */       if (is != null) {
/* 21 */         this.type.put(num, Integer.valueOf(is.getTypeId()));
/* 22 */         this.amount.put(num, Integer.valueOf(is.getAmount()));
/* 23 */         this.durariblitiy.put(num, Integer.valueOf(is.getDurability()));
/* 24 */         this.data.put(num, Integer.valueOf(is.getData().getData()));
/*    */       }
/* 26 */       num = Integer.valueOf(num.intValue() + 1);
/*    */     }
/*    */   }
/*    */ 
/*    */   public ItemStack[] getChestData()
/*    */   {
/* 32 */     ItemStack[] iss = new ItemStack[27];
/* 33 */     for (Integer num : this.type.keySet()) {
/* 34 */       int damage = ((Integer)this.durariblitiy.get(num)).intValue();
/* 35 */       int data = ((Integer)this.data.get(num)).intValue();
/* 36 */       iss[num.intValue()] = new ItemStack(((Integer)this.type.get(num)).intValue(), ((Integer)this.amount.get(num)).intValue(), (short)damage, Byte.valueOf((byte)data));
/*    */     }
/* 38 */     return iss;
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.blockFileData.ChestData
 * JD-Core Version:    0.6.2
 */