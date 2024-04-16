package canlor.tp1robots.modelo.graficos;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HojaSprite {
    private final String PATH = "/resources/imagenes/robots.png";

    private BufferedImage hojaSprite;
    private final int spriteAncho;
    private final int spriteAlto;

    public HojaSprite() {
        this.spriteAncho = 16;
        this.spriteAlto = 16;

        try {
            this.hojaSprite = ImageIO.read(new File(PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage[] getSprites(int[] positions) {
        BufferedImage[] sprites = new BufferedImage[positions.length];
        for (int i = 0; i < positions.length; i++) {
            int index = positions[i];
            int columna = index % (hojaSprite.getWidth() / spriteAncho);
            sprites[i] = hojaSprite.getSubimage(columna * spriteAncho, 0, spriteAlto, spriteAlto);
        }
        return sprites;
    }
}
