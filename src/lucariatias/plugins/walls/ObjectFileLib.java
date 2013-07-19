/*    */ package lucariatias.plugins.walls;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.Serializable;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class ObjectFileLib
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1001216325485243060L;
/*    */ 
/*    */   public static void saveObject(JavaPlugin plugin, Object object, String fileName)
/*    */   {
/*    */     try
/*    */     {
/* 21 */       if (!plugin.getDataFolder().exists()) {
/* 22 */         plugin.getDataFolder().mkdir();
/*    */       }
/* 24 */       File file = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + fileName);
/* 25 */       file.delete();
/* 26 */       file.createNewFile();
/* 27 */       FileOutputStream fos = new FileOutputStream(file);
/* 28 */       ObjectOutputStream oos = new ObjectOutputStream(fos);
/* 29 */       oos.writeObject(object);
/* 30 */       oos.close();
/*    */     } catch (IOException exception) {
/* 32 */       exception.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static Object loadObject(JavaPlugin plugin, String fileName) {
/*    */     try {
/* 38 */       File file = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + fileName);
/* 39 */       if (file.exists()) {
/* 40 */         FileInputStream fis = new FileInputStream(file);
/* 41 */         ObjectInputStream ois = new ObjectInputStream(fis);
/* 42 */         Object object = ois.readObject();
/* 43 */         ois.close();
/* 44 */         return object;
/*    */       }
/*    */     } catch (IOException exception) {
/* 47 */       exception.printStackTrace();
/*    */     } catch (ClassNotFoundException exception) {
/* 49 */       exception.printStackTrace();
/*    */     }
/* 51 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Johnnie\Desktop\Minecraft Related\MyPlugins\TheBridges.jar
 * Qualified Name:     lucariatias.plugins.walls.ObjectFileLib
 * JD-Core Version:    0.6.2
 */