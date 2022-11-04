package application;

import controller.DUController;
import model.staticTools.vars;

public class Loop
{

	private static boolean running = true, run = false;
	private static double delta;

	public static void startLoop ( DUController duc )
	{
		int aps = 0, fps = 0;

		// Equivalencia
		final int NS_POR_SEGUNDO = 1000000000;

		// actualizaciones por segundo objetivo
		final int APS_OBJETIVO = 60;
		// final int FPS_OBJETIVO = 60;

		// Descubrir cuantos nanosegundos deben transcurrir para que se
		// actualice segun el objetivo
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
		// final double NS_POR_FRAME = NS_POR_SEGUNDO / FPS_OBJETIVO;

		// Se atribuye una cantidad de tiempo en nanosegundos
		long referenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();

		double tiempoTranscurrido;

		// delta time
		delta = 0;

		int loops = 0;
		while ( running )
		{
			// toma el valor en nanosegundos diferente al anterior system nano
			final long inicioBucle = System.nanoTime();

			// en tiempoT se mide el tiempo entre inicio y referencia
			// el primer ciclo sera con referencia de afuera del bucle
			// luego se comparará con la diferencia dentro del bucle
			tiempoTranscurrido = inicioBucle - referenciaActualizacion;

			// Se le asigna el valor de inicio a referencia. para un nuevo
			// inicio del bucle
			referenciaActualizacion = inicioBucle;

			// almacenar la operacion en delta, cantidades pequeñas
			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
			vars.delta = delta;

			// ejecutar cuando delta sume 1 para actualizar delta
			while ( delta >= 1 && run )
			{
				// Actualizar-----------------
				duc.update();
				aps++;
				// -----------------

				// Dibujar------------------
				duc.draw();
				fps++;
				// ------------------

				delta--;
			}

			if ( System.nanoTime() - referenciaContador > NS_POR_SEGUNDO )
			{
				vars.FPS = fps;
				vars.APS = aps;

				aps = 0;
				fps = 0;

				// tomar el tiempo al finalizar este codigo para la comparacion
				// en el sigueinte ciclo
				referenciaContador = System.nanoTime();
			}

			// dentro para hacer una pantalla de carga
			loops = (loops >= 100) ? loops : loops + 1;
			run = (loops >= 100);

		}
	}

	public boolean getRunning ()
	{
		return running;
	}

	public double getDelta ()
	{
		return delta;
	}
}
