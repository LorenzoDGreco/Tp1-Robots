package canlor.tp1robots.modelo.graficos;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Esta clase se encarga de subdividir las imagenes
 * y darselas a las Entidades correspondientes
 */
public class HojaSprite {
    private final static String PATH = "src/main/resources/imagenes/robots.png";
    private final static int SPRITE_ANCHO = 16;
    private final static int SPRITE_ALTO = 16;

    private BufferedImage hojaSprite;

    /**
     * Constructor para la HojaSprite
     */
    public HojaSprite() {
        try {
            this.hojaSprite = ImageIO.read(new File(PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo se encarga de dividir la imagen principal
     * en solo las que la Entidad correspondiente necesite
     *
     * @param posiciones Las imagenes que se desean obtener
     * @return           Array de las imagenes obtenidas
     */
    public BufferedImage[] getSprites(int[] posiciones) {
        BufferedImage[] sprites = new BufferedImage[posiciones.length];
        for (int i = 0; i < posiciones.length; i++) {
            int index = posiciones[i];
            int columna = index % (hojaSprite.getWidth() / SPRITE_ANCHO);
            sprites[i] = hojaSprite.getSubimage(columna * SPRITE_ANCHO, 0, SPRITE_ALTO, SPRITE_ALTO);
        }
        return sprites;
    }
}