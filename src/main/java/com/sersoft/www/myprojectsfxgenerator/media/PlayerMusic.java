//package com.sersoft.www.myprojectsfxgenerator.media;
//
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.MediaView;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class PlayerMusic {
//
//    static final MediaView view = new MediaView();
//    static Iterator<String> itr;
//    public static MediaPlayer mediaPlayer;
//    static Media media;
//    static MediaView mediaView;
//    public static MediaPlayer player;
//
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
////    public static void playerVideo() {
//////      //  String musicFile =getClass().getResource() "C:\\\\Users\\\\Anonyms\\\\Desktop\\\\MyProjectsFxGenerator\\\\src\\\\main\\\\resources\\\\music/Welcome.mp3";     // For example
////////String musicFile1 ="C:\\\\Users\\\\Anonyms\\\\Desktop\\\\MyProjectsFxGenerator\\\\src\\\\main\\\\resources\\\\music/relax1.mp3";
//////Media sound = new Media(new File(musicFile).toURI().toString());
//////MediaPlayer mediaPlayer = new MediaPlayer(sound);
//////mediaPlayer.play();
//////
//////        String path;
//////        path = "C:\\Users\\Anonyms\\Desktop\\MyProjectsFxGenerator\\src\\main\\resources\\music/Welcome.mp3";
//////        media = new Media(new File(path).toURI().toString());
//////        mediaPlayer = new MediaPlayer(media);
//////        mediaPlayer.setAutoPlay(true);
//////        mediaView = new MediaView(mediaPlayer);
//////        player.play();
//////URL resource = PlayerMusic.class.getResource("MyProjectsFxGenerator/src/main/resources/music/Welcome.mp3");
//////media=new Media(resource.toString());
//////mediaPlayer=new MediaPlayer(media);
//////mediaPlayer.play();
//////         InputStream fis = null;
//////        BufferedInputStream bis = null;
//////
//////        fis = PlayerMusic.class.getClassLoader().getResourceAsStream("resources/music/Welcome.mp3");
//////        bis = new BufferedInputStream(fis);
//////       
//////          //  FileInputStream input = new FileInputStream("HIPHOP.mp3"); 
//////           player = new PlayerMusic(input);
//////
//////            // start playing
//////            player.play();
//////
//////            // after 5 secs, pause
//////            Thread.sleep(5000);
//////            player.pause();     
//////
//////            // after 5 secs, resume
//////            Thread.sleep(5000);
//////            player.resume();
//////       
//////    
////    }
////public static void playerTest(){
////
//// String musicFile =PlayerMusic.class.getResource("file///Welcome.mp3").toString();     // For example
////
////Media sound = new Media(musicFile);
////MediaPlayer mediaPlayer = new MediaPlayer(sound);
////mediaPlayer.play();
////
////}
//    public static void playerInto() {
// //String path = PlayerMusic.class.getResource("resources/music/Welcome.mp3").toString();
// //PlayerMusic.class.getResource("file:///Users/Anonyms/Desktop/MyProjectsFxGenerator/src/main/resources/music/Welcome.mp3").toString();
//        List<String> list = new ArrayList<>();
//
//        list.add("file:///Users/Anonyms/Desktop/MyProjectsFxGenerator/src/main/resources/music/Welcome.mp3");//
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
//
//}
