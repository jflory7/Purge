/*    */ package RDNachoz.plugins.bridges;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockEvent;
/*    */ 
/*    */ public class BlockListener
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onBlockEvent(BlockEvent event)
/*    */   {
/* 12 */     Bukkit.broadcastMessage("Hi");
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     RDNachoz.plugins.bridges.BlockListener
 * JD-Core Version:    0.6.2
 */