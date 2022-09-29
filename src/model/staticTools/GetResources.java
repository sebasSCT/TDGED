package model.staticTools;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class GetResources
{

	// Cargar imagen opaca
	public static BufferedImage ciOpaca ( final String ruta )
	{
		// cargar imagen ruta, crear imagen buffer compatible, ubicar imagen
		// dentro de buffer.

		Image imagen = null;

		try
		{
			imagen = ImageIO.read(
					ClassLoader.class.getResource(getPath("/scr/resources/star_icon.png")));
		} catch ( IOException e )
		{
			e.printStackTrace();
		}

		// Obtener la configuracion gráfica del monitor que está siendo
		// utilizado
		GraphicsConfiguration gc = GraphicsEnvironment	.getLocalGraphicsEnvironment()
														.getDefaultScreenDevice()
														.getDefaultConfiguration();

		// Crear una imagen en el buffer con el mismo tamaño que la que se creo
		// anteriormente

		// Transparency.OPAQUE: para que java asuma que esta imagen nunca será
		// translucida y asi mejor la velocidad de carga
		BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null),
				imagen.getHeight(null), Transparency.OPAQUE);

		// Graphics, dibujar en el lugar donde queramos
		// aqui los Graphics dibujaran en imagen acelerada
		Graphics g = imagenAcelerada.getGraphics();

		// dibujar imagen dentro de imagenAcelerada
		g.drawImage(imagen, 0, 0, null);

		// eliminar objeto "g"
		g.dispose();

		return imagenAcelerada;

	}

	// Cargar Imagen Translucida
	public static BufferedImage ciTranslucida ( String ruta )
	{
		// cargar imagen ruta, crear imagen buffer compatible, ubicar imagen
		// dentro de buffer.

		Image imagen = null;
		File file = new File(getPath(ruta));

		try
		{
			imagen = ImageIO.read(file);
		} catch ( IOException e )
		{
			e.printStackTrace();
		}

		// Obtener la configuracion gráfica del monitor que está siendo
		// utilizado
		GraphicsConfiguration gc = GraphicsEnvironment	.getLocalGraphicsEnvironment()
														.getDefaultScreenDevice()
														.getDefaultConfiguration();

		// Crear una imagen en el buffer con el mismo tamaño que la que se creo
		// anteriormente
		BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null),
				imagen.getHeight(null), Transparency.TRANSLUCENT);

		// Graphics, dibujar en el lugar donde queramos
		// aqui los Graphics dibujaran en imagen acelerada
		Graphics g = imagenAcelerada.getGraphics();

		// dibujar imagen dentro de imagenAcelerada
		g.drawImage(imagen, 0, 0, null);

		// eliminar objeto "g"
		g.dispose();

		return imagenAcelerada;
	}

	public static String leerArchivoTxt ( String ruta )
	{
		String contenido = "";

		InputStream entradaBytes = ClassLoader.class.getResourceAsStream(ruta);

		BufferedReader lector = new BufferedReader(new InputStreamReader(entradaBytes));

		String linea;

		try
		{
			while ( (linea = lector.readLine()) != null )
			{
				contenido += linea;
			}
			// IO, excepcion para entrada o salida de datos
		} catch ( IOException e )
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if ( entradaBytes != null )
				{
					entradaBytes.close();
				}
				if ( lector != null )
				{
					lector.close();
				}
			} catch ( IOException ex )
			{
				ex.printStackTrace();
			}
		}

		return contenido;
	}

	public static Font cargarFuentes ( final String ruta, float size )
	{
		Font fuente = null;

		File f = new File(getPath(ruta));

		try
		{
			fuente = Font.createFont(Font.TRUETYPE_FONT, f);
		} catch ( FontFormatException e )
		{

			e.printStackTrace();
		} catch ( IOException e )
		{

			e.printStackTrace();
		}

		// 6f
		fuente = fuente.deriveFont(size);

		return fuente;

	}

	public static Clip cargarSonido ( final String ruta )
	{
		Clip clip = null;

		try
		{
			InputStream is = ClassLoader.class.getResourceAsStream(ruta);
			AudioInputStream ais = AudioSystem.getAudioInputStream(
					new BufferedInputStream(is));
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
		}

		catch ( Exception e )
		{
			e.printStackTrace();
		}

		return clip;
	}

	private static String getPath ( String ruta )
	{
		String raiz = System.getProperty("user.dir");
		String filePath = raiz + "/" + ruta;
		return filePath;
	}
}
