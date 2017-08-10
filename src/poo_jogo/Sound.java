package poo_jogo;

import javax.sound.sampled.*;

public class Sound {
	
	private Clip clip;
	
	public static Sound sound1 = new Sound ("../sounds/music1.wav");   // m�sicas //
	
	public static Sound jump_s = new Sound ("../sounds/jump5.wav");  // efeitos // 
	public static Sound duck_s = new Sound ("../sounds/duck.wav");
	
	
	public Sound (String fileName) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream (Sound.class.getResource(fileName));
			clip=AudioSystem.getClip();
			clip.open(ais);
			
		}catch (Exception e) {
			e.printStackTrace();		
		}	
	}
	public void play() {
        try {
            if (clip != null) {
               new Thread() {
               public void run() {

                        synchronized (clip) {
                            clip.stop();
                            clip.setFramePosition(0);
                            clip.start();
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void stop(){
        if(clip == null) return;
        clip.stop();
    }
 
   public void loop() {
	   	try {
	   		  if (clip != null) {
                new Thread() {
                    public void run() {
                        synchronized (clip) {
                            clip.stop();
                            clip.setFramePosition(0);
                            clip.loop(Clip.LOOP_CONTINUOUSLY);
                       }
                    }
                }.start();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public boolean isActive(){

        return clip.isActive();
   }

}
