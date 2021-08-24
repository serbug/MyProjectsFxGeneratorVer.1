//package com.sersoft.www.myprojectsfxgenerator.media;
//
//import java.io.BufferedInputStream;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.MediaView;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.collections.MapChangeListener;
//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.AudioDevice;
//import javazoom.jl.player.Player;
//import javazoom.jl.player.advanced.AdvancedPlayer;
//import static sun.audio.AudioPlayer.player;

//public class PlayerMusic {

//    static final MediaView view = new MediaView();
//    static Iterator<String> itr;
//    public static MediaPlayer mediaPlayer;
//    static Media media;
//    static MediaView mediaView;
//    public static MediaPlayer player;

    


//    public static void play(String mediaFile) {
//        media = new Media(mediaFile);
//        player = new MediaPlayer(media);
//        view.setMediaPlayer(player);
//        player.play();
//        player.setOnEndOfMedia(new Runnable() {
//            @Override
//            public void run() {
//                player.stop();
//                if (itr.hasNext()) {
//                    //Plays the subsequent files
//                    play(itr.next());
//                }
//            }
//        });
//    }
//
//    public static void playerVideo() {
//
//        String path;
//        path = "src\\main\\resources\\music/welcome.mp4";
//        media = new Media(new File(path).toURI().toString());
//        mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setAutoPlay(true);
//        mediaView = new MediaView(mediaPlayer);
//
//    }
//
//    public static void playerInto() {
//
//        List<String> list = new ArrayList<>();
//
//        list.add("file:///Users/Anonyms/Desktop/MyProjectsFxGenerator/src/main/resources/music/Welcome.mp3");
//       // list.add("file:///media/iucosoft7/Ser/MyProjectsFxGenerator/src/main/resources/music/Welcome.mp3");
//        //list.addAll(list);
//
//        itr = list.iterator();
//        //Plays the first file
//
//        play(itr.next());
//
//    }
//
//    public static void playerMain() {
//        List<String> list = new ArrayList<>();
//
//        list.add("file:///Users/Anonyms/Desktop/MyProjectsFxGenerator/src/main/resources/music/relax1.mp3");
//        list.add("file:///Users/Anonyms/Desktop/MyProjectsFxGenerator/src/main/resources/music/relax2.mp3");
//        list.add("file:///Users/Anonyms/Desktop/MyProjectsFxGenerator/src/main/resources/music/relax3.mp3");
//
//        itr = list.iterator();
//        //Plays the first file
//
//        play(itr.next());
//    }


//    private final static int NOTSTARTED = 0;
//    private final static int PLAYING = 1;
//    private final static int PAUSED = 2;
//    private final static int FINISHED = 3;
//
//    // the player actually doing all the work
//    private final Player player;
//
//    // locking object used to communicate with player thread
//    private final Object playerLock = new Object();
//
//    // status variable what player thread is doing/supposed to do
//    private int playerStatus = NOTSTARTED;
//
//    public PausablePlayer(final InputStream inputStream) throws JavaLayerException {
//        this.player = new Player(inputStream);
//    }
//
//    public PausablePlayer(final InputStream inputStream, final AudioDevice audioDevice) throws JavaLayerException {
//        this.player = new Player(inputStream, audioDevice);
//    }
//
//    /**
//     * Starts playback (resumes if paused)
//     */
//    public void play() throws JavaLayerException {
//        synchronized (playerLock) {
//            switch (playerStatus) {
//                case NOTSTARTED:
//                    final Runnable r = new Runnable() {
//                        public void run() {
//                            playInternal();
//                        }
//                    };
//                    final Thread t = new Thread(r);
//                    t.setDaemon(true);
//                    t.setPriority(Thread.MAX_PRIORITY);
//                    playerStatus = PLAYING;
//                    t.start();
//                    break;
//                case PAUSED:
//                    resume();
////                    break;
////                default:
////                    break;
////            }
////        }
////    }
////
////    /**
////     * Pauses playback. Returns true if new state is PAUSED.
////     */
////    public boolean pause() {
////        synchronized (playerLock) {
////            if (playerStatus == PLAYING) {
////                playerStatus = PAUSED;
////            }
////            return playerStatus == PAUSED;
////        }
////    }
////
////    /**
////     * Resumes playback. Returns true if the new state is PLAYING.
////     */
////    public boolean resume() {
////        synchronized (playerLock) {
////            if (playerStatus == PAUSED) {
////                playerStatus = PLAYING;
////                playerLock.notifyAll();
////            }
////            return playerStatus == PLAYING;
////        }
////    }
////
////    /**
////     * Stops playback. If not playing, does nothing
////     */
////    public void stop() {
////        synchronized (playerLock) {
////            playerStatus = FINISHED;
////            playerLock.notifyAll();
////        }
////    }
////
////    private void playInternal() {
////        while (playerStatus != FINISHED) {
////            try {
////                if (!player.play(1)) {
////                    break;
////                }
////            } catch (final JavaLayerException e) {
////                break;
////            }
////            // check if paused or terminated
////            synchronized (playerLock) {
////                while (playerStatus == PAUSED) {
////                    try {
////                        playerLock.wait();
////                    } catch (final InterruptedException e) {
////                        // terminate player
////                        break;
////                    }
////                }
////            }
////        }
////        close();
////    }
////
////    /**
////     * Closes the player, regardless of current state.
////     */
////    public void close() {
////        synchronized (playerLock) {
////            playerStatus = FINISHED;
////        }
////        try {
////            player.close();
////        } catch (final Exception e) {
////            // ignore, we are terminating anyway
////        }
////    }
////
////    // demo how to use
////    public static void main(String[] argv) {
////        try {
////            FileInputStream input = new FileInputStream("myfile.mp3"); 
////            PausablePlayer player = new PausablePlayer(input);
////
////            // start playing
////            player.play();
////
////            // after 5 secs, pause
////            Thread.sleep(5000);
////            player.pause();     
////
////            // after 5 secs, resume
////            Thread.sleep(5000);
////            player.resume();
////        } catch (final Exception e) {
////            throw new RuntimeException(e);
////        }
////    }
//
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import javazoom.jl.player.Player;
//
//public class BackgroundMusic {
//
//    private String filename;
//    private static Player player;
//    Thread playMusic;
//    // constructor that takes the name of an MP3 file
//
//    public BackgroundMusic(String filename) {
//        this.filename = filename;
//    }
//
//    // play the MP3 file to the sound card
//    public void play() {
//        try {
//            FileInputStream fis = new FileInputStream(filename);
//            BufferedInputStream bis = new BufferedInputStream(fis);
//            player = new Player(bis);
//        } catch (Exception e) {
//            System.out.println("Problem playing file " + filename);
//            System.out.println(e);
//        }
//    }
//
//    public void start() {
//        play();
//        playMusic = new Thread(new PlayMusic());
//        playMusic.start();
//    }
//
//    public void stop() {
//        close();
//        playMusic = null;
//    }
//
//    public void close() {
//        if (player != null) {
//            player.close();
//        }
//    }
//
//    class PlayMusic implements Runnable {
//
//        public void run() {
//            try {
//                player.play();
//            }
//            catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//    }
//      public static void log(){
//        
//        try {
//            BackgroundMusic bm = new BackgroundMusic("C:\\\\Users\\\\Anonyms\\\\Desktop\\\\MyProjectsFxGenerator\\\\src\\\\main\\\\resources\\\\music/Welcome.mp3");
//            bm.start();
//            Thread.sleep(10000);
//            bm.stop();
//        } catch (InterruptedException ex) {
//            System.out.println(ex);
//        }
//    }
//}
//
//
//
//
//
//    
//  
//    
