import com.dropbox.core.v2.DbxClientV2;

import javax.sound.sampled.*;
import java.io.*;
 
/**
  * A sample program is to demonstrate how to record sound in Java
  * author: www.codejava.net
  */
public class JavaSoundRecorder
{
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    TargetDataLine line;
    DbxClientV2 client;
    AudioFormat format;

    public JavaSoundRecorder(DbxClientV2 client)
    {
        this.client = client;
        format = getAudioFormat();
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        // checks if system supports the data line
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Line not supported");
            System.exit(0);
        }
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(
            sampleRate,
            sampleSizeInBits,
            channels,
            signed,
            bigEndian
        );
        return format;
    }

    public void recordAudio(int milliseconds)
    {
        //TODO: 20201120_201454.wav
        String fileName = "C:/Users/User/Dropbox/Мой ПК (WIN-UHPATH57N2R)/Documents/20201128_121045.wav";
        File file = new File(fileName);
        start(file);
        stop(milliseconds, file);
    }

            /**
      * Captures the sound and record into a WAV file
      */
    void start(File file)
    {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    line.open(format);
                    line.start();   // start capturing
                    AudioInputStream ais = new AudioInputStream(line);
                    AudioSystem.write(ais, fileType, file);
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        };
        thread.start();
    }
 
            /**
      * Closes the target data line to finish capturing and recording
      */
    void stop(int milliseconds, File file)
    {
        Thread thread = new Thread(){
            @Override
            public void run()
            {
                try {
                    sleep(milliseconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                line.stop();
                line.close();

                //TODO: upload file to Dropbox
                //TODO: delete file
            }
        };
        thread.start();
    }
}