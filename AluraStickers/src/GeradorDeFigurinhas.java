import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

public class GeradorDeFigurinhas {
    
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception{

        //InputStream InputStream = new FileInputStream(new File("entrada/filme.jpg"));
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs_1.jpg").openStream();
        BufferedImage original = ImageIO.read(inputStream);

        int largura = original.getWidth();
        int altura = original.getHeight();
        int novaAltura = altura + 220;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);


        Graphics2D graphics = (Graphics2D)novaImagem.getGraphics();
        graphics.drawImage(original, 0, 0, null);

        var fonte = new Font("Impact", Font.ITALIC, 100);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);


        var texto = "PHODA";
        int stringWidth = graphics.getFontMetrics().stringWidth(texto);
        int textPosX = ((largura/2)-(stringWidth)/2);
        int textPosY = novaAltura - 70;
        graphics.drawString(texto, textPosX, textPosY);
        
        FontRenderContext render = graphics.getFontRenderContext();
        var textLayout = new TextLayout(texto, fonte, render);

        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(textPosX, textPosY);
        graphics.setTransform(transform);

        var outlineStroke = new BasicStroke(largura * 0.009f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);
        
        ImageIO.write(novaImagem, "png", new File("saida/"+nomeArquivo));
    }
}
