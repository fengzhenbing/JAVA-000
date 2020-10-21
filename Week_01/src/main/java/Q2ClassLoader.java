import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Description
 * @Author fzb
 * @date 2020.10.16 12:20
 */
public class Q2ClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = Files.readAllBytes(Paths.get(Q2ClassLoader.class.getResource("").getPath()+ File.separator +"Hello.xlass"));
            byte[] newData = decodeClassData(data);
            return this.defineClass(name,newData,0,newData.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    /**
     * decode by  original = 255 - encode
     * @param data
     * @return newData
     */
    private byte[] decodeClassData(byte[] data) {
        byte[] newData = new byte[data.length];
        for(int i =0;i<data.length;i++){
             newData[i] = (byte) (255 - data[i]);
        }
        return newData;
    }


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class clazz  = new Q2ClassLoader().findClass("Hello");
        Object obj = clazz.newInstance();
        clazz.getDeclaredMethod("hello").invoke(obj);
    }


}
